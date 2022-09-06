package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer$simpleType$1 extends Lambda implements Function1<ProtoBuf.Type, List<? extends ProtoBuf.Type.Argument>> {
    final /* synthetic */ TypeDeserializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeDeserializer$simpleType$1(TypeDeserializer typeDeserializer) {
        super(1);
        this.this$0 = typeDeserializer;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final List<ProtoBuf.Type.Argument> mo12165invoke(@NotNull ProtoBuf.Type collectAllArguments) {
        DeserializationContext deserializationContext;
        List<ProtoBuf.Type.Argument> plus;
        Intrinsics.checkParameterIsNotNull(collectAllArguments, "$this$collectAllArguments");
        List<ProtoBuf.Type.Argument> argumentList = collectAllArguments.getArgumentList();
        Intrinsics.checkExpressionValueIsNotNull(argumentList, "argumentList");
        deserializationContext = this.this$0.c;
        ProtoBuf.Type outerType = ProtoTypeTableUtilKt.outerType(collectAllArguments, deserializationContext.getTypeTable());
        List<ProtoBuf.Type.Argument> mo12165invoke = outerType != null ? mo12165invoke(outerType) : null;
        if (mo12165invoke == null) {
            mo12165invoke = CollectionsKt__CollectionsKt.emptyList();
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection) argumentList, (Iterable) mo12165invoke);
        return plus;
    }
}
