package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.LinkedList;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
/* compiled from: NameResolverImpl.kt */
/* loaded from: classes4.dex */
public final class NameResolverImpl implements NameResolver {
    private final ProtoBuf.QualifiedNameTable qualifiedNames;
    private final ProtoBuf.StringTable strings;

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.values().length];

        static {
            $EnumSwitchMapping$0[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$0[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE.ordinal()] = 2;
            $EnumSwitchMapping$0[ProtoBuf.QualifiedNameTable.QualifiedName.Kind.LOCAL.ordinal()] = 3;
        }
    }

    public NameResolverImpl(@NotNull ProtoBuf.StringTable strings, @NotNull ProtoBuf.QualifiedNameTable qualifiedNames) {
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        Intrinsics.checkParameterIsNotNull(qualifiedNames, "qualifiedNames");
        this.strings = strings;
        this.qualifiedNames = qualifiedNames;
    }

    private final Triple<List<String>, List<String>, Boolean> traverseIds(int i) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        boolean z = false;
        while (i != -1) {
            ProtoBuf.QualifiedNameTable.QualifiedName proto = this.qualifiedNames.getQualifiedName(i);
            ProtoBuf.StringTable stringTable = this.strings;
            Intrinsics.checkExpressionValueIsNotNull(proto, "proto");
            String string = stringTable.getString(proto.getShortName());
            ProtoBuf.QualifiedNameTable.QualifiedName.Kind kind = proto.getKind();
            if (kind == null) {
                Intrinsics.throwNpe();
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i2 == 1) {
                linkedList2.addFirst(string);
            } else if (i2 == 2) {
                linkedList.addFirst(string);
            } else if (i2 == 3) {
                linkedList2.addFirst(string);
                z = true;
            }
            i = proto.getParentQualifiedName();
        }
        return new Triple<>(linkedList, linkedList2, Boolean.valueOf(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getQualifiedClassName(int i) {
        String joinToString$default;
        String joinToString$default2;
        Triple<List<String>, List<String>, Boolean> traverseIds = traverseIds(i);
        List<String> component1 = traverseIds.component1();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(traverseIds.component2(), ".", null, null, 0, null, null, 62, null);
        if (component1.isEmpty()) {
            return joinToString$default;
        }
        StringBuilder sb = new StringBuilder();
        joinToString$default2 = CollectionsKt___CollectionsKt.joinToString$default(component1, "/", null, null, 0, null, null, 62, null);
        sb.append(joinToString$default2);
        sb.append('/');
        sb.append(joinToString$default);
        return sb.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    @NotNull
    public String getString(int i) {
        String string = this.strings.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "strings.getString(index)");
        return string;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int i) {
        return traverseIds(i).getThird().booleanValue();
    }
}
