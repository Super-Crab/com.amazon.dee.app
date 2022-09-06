package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: inlineClassManglingRules.kt */
/* loaded from: classes4.dex */
public final class InlineClassManglingRulesKt {
    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), DescriptorUtils.RESULT_FQ_NAME);
    }

    public static final boolean isInlineClassThatRequiresMangling(@NotNull DeclarationDescriptor isInlineClassThatRequiresMangling) {
        Intrinsics.checkParameterIsNotNull(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        return InlineClassesUtilsKt.isInlineClass(isInlineClassThatRequiresMangling) && !isDontMangleClass((ClassDescriptor) isInlineClassThatRequiresMangling);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(@NotNull KotlinType kotlinType) {
        ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
            mo12085getDeclarationDescriptor = null;
        }
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) mo12085getDeclarationDescriptor;
        if (typeParameterDescriptor != null) {
            return requiresFunctionNameMangling(TypeUtilsKt.getRepresentativeUpperBound(typeParameterDescriptor));
        }
        return false;
    }

    private static final boolean requiresFunctionNameMangling(@NotNull KotlinType kotlinType) {
        return isInlineClassThatRequiresMangling(kotlinType) || isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType);
    }

    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(@NotNull CallableMemberDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (!(descriptor instanceof ClassConstructorDescriptor)) {
            descriptor = null;
        }
        ClassConstructorDescriptor classConstructorDescriptor = (ClassConstructorDescriptor) descriptor;
        if (classConstructorDescriptor == null || Visibilities.isPrivate(classConstructorDescriptor.getVisibility())) {
            return false;
        }
        ClassDescriptor constructedClass = classConstructorDescriptor.getConstructedClass();
        Intrinsics.checkExpressionValueIsNotNull(constructedClass, "constructorDescriptor.constructedClass");
        if (constructedClass.isInline() || DescriptorUtils.isSealedClass(classConstructorDescriptor.getConstructedClass())) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "constructorDescriptor.valueParameters");
        if (valueParameters.isEmpty()) {
            return false;
        }
        for (ValueParameterDescriptor it2 : valueParameters) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            KotlinType type = it2.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
            if (requiresFunctionNameMangling(type)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(@NotNull KotlinType isInlineClassThatRequiresMangling) {
        Intrinsics.checkParameterIsNotNull(isInlineClassThatRequiresMangling, "$this$isInlineClassThatRequiresMangling");
        ClassifierDescriptor mo12085getDeclarationDescriptor = isInlineClassThatRequiresMangling.mo12131getConstructor().mo12085getDeclarationDescriptor();
        return mo12085getDeclarationDescriptor != null && isInlineClassThatRequiresMangling(mo12085getDeclarationDescriptor);
    }
}
