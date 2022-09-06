package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
/* compiled from: typeParameterUtils.kt */
/* loaded from: classes2.dex */
final class TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3 extends Lambda implements Function1<DeclarationDescriptor, Sequence<? extends TypeParameterDescriptor>> {
    public static final TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3 INSTANCE = new TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3();

    TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Sequence<TypeParameterDescriptor> mo12165invoke(@NotNull DeclarationDescriptor it2) {
        Sequence<TypeParameterDescriptor> asSequence;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) it2).getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "(it as CallableDescriptor).typeParameters");
        asSequence = CollectionsKt___CollectionsKt.asSequence(typeParameters);
        return asSequence;
    }
}
