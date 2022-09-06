package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
/* compiled from: methodSignatureBuilding.kt */
/* loaded from: classes3.dex */
public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String escapeClassName(String str) {
        if (str.length() > 1) {
            return Matrix.MATRIX_TYPE_RANDOM_LT + str + ';';
        }
        return str;
    }

    @NotNull
    public final String[] constructors(@NotNull String... signatures) {
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        ArrayList arrayList = new ArrayList(signatures.length);
        for (String str : signatures) {
            arrayList.add("<init>(" + str + ")V");
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @NotNull
    public final LinkedHashSet<String> inClass(@NotNull String internalName, @NotNull String... signatures) {
        Intrinsics.checkParameterIsNotNull(internalName, "internalName");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (String str : signatures) {
            linkedHashSet.add(internalName + '.' + str);
        }
        return linkedHashSet;
    }

    @NotNull
    public final LinkedHashSet<String> inJavaLang(@NotNull String name, @NotNull String... signatures) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        return inClass(javaLang(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    @NotNull
    public final LinkedHashSet<String> inJavaUtil(@NotNull String name, @NotNull String... signatures) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        return inClass(javaUtil(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    @NotNull
    public final String javaFunction(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/util/function/" + name;
    }

    @NotNull
    public final String javaLang(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/lang/" + name;
    }

    @NotNull
    public final String javaUtil(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/util/" + name;
    }

    @NotNull
    public final String jvmDescriptor(@NotNull String name, @NotNull List<String> parameters, @NotNull String ret) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(ret, "ret");
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append('(');
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(parameters, "", null, null, 0, null, SignatureBuildingComponents$jvmDescriptor$1.INSTANCE, 30, null);
        sb.append(joinToString$default);
        sb.append(')');
        sb.append(escapeClassName(ret));
        return sb.toString();
    }

    @NotNull
    public final String signature(@NotNull ClassDescriptor classDescriptor, @NotNull String jvmDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        Intrinsics.checkParameterIsNotNull(jvmDescriptor, "jvmDescriptor");
        return signature(MethodSignatureMappingKt.getInternalName(classDescriptor), jvmDescriptor);
    }

    @NotNull
    public final String signature(@NotNull String internalName, @NotNull String jvmDescriptor) {
        Intrinsics.checkParameterIsNotNull(internalName, "internalName");
        Intrinsics.checkParameterIsNotNull(jvmDescriptor, "jvmDescriptor");
        return internalName + '.' + jvmDescriptor;
    }
}
