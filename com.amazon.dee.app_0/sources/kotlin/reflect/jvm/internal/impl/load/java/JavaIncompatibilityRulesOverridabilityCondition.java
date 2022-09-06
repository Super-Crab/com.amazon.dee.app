package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.Pair;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
/* loaded from: classes3.dex */
public final class JavaIncompatibilityRulesOverridabilityCondition implements ExternalOverridabilityCondition {
    public static final Companion Companion = new Companion(null);

    /* compiled from: JavaIncompatibilityRulesOverridabilityCondition.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        private final boolean isPrimitiveCompareTo(FunctionDescriptor functionDescriptor) {
            if (functionDescriptor.getValueParameters().size() != 1) {
                return false;
            }
            DeclarationDescriptor mo11607getContainingDeclaration = functionDescriptor.mo11607getContainingDeclaration();
            if (!(mo11607getContainingDeclaration instanceof ClassDescriptor)) {
                mo11607getContainingDeclaration = null;
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) mo11607getContainingDeclaration;
            if (classDescriptor != null) {
                List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "f.valueParameters");
                Object single = CollectionsKt.single((List<? extends Object>) valueParameters);
                Intrinsics.checkExpressionValueIsNotNull(single, "f.valueParameters.single()");
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((ValueParameterDescriptor) single).getType().mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor2 = (ClassDescriptor) mo12085getDeclarationDescriptor;
                if (classDescriptor2 != null) {
                    return KotlinBuiltIns.isPrimitiveClass(classDescriptor) && Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), DescriptorUtilsKt.getFqNameSafe(classDescriptor2));
                }
            }
            return false;
        }

        private final JvmType mapValueParameterType(FunctionDescriptor functionDescriptor, ValueParameterDescriptor valueParameterDescriptor) {
            if (!MethodSignatureMappingKt.forceSingleValueParameterBoxing(functionDescriptor) && !isPrimitiveCompareTo(functionDescriptor)) {
                KotlinType type = valueParameterDescriptor.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "valueParameterDescriptor.type");
                return MethodSignatureMappingKt.mapToJvmType(type);
            }
            KotlinType type2 = valueParameterDescriptor.getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "valueParameterDescriptor.type");
            return MethodSignatureMappingKt.mapToJvmType(TypeUtilsKt.makeNullable(type2));
        }

        public final boolean doesJavaOverrideHaveIncompatibleValueParameterKinds(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor) {
            List<Pair> zip;
            Intrinsics.checkParameterIsNotNull(superDescriptor, "superDescriptor");
            Intrinsics.checkParameterIsNotNull(subDescriptor, "subDescriptor");
            if ((subDescriptor instanceof JavaMethodDescriptor) && (superDescriptor instanceof FunctionDescriptor)) {
                JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) subDescriptor;
                FunctionDescriptor functionDescriptor = (FunctionDescriptor) superDescriptor;
                boolean z = javaMethodDescriptor.getValueParameters().size() == functionDescriptor.getValueParameters().size();
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("External overridability condition with CONFLICTS_ONLY should not be run with different value parameters size");
                }
                SimpleFunctionDescriptor mo11613getOriginal = javaMethodDescriptor.mo11613getOriginal();
                Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "subDescriptor.original");
                List<ValueParameterDescriptor> valueParameters = mo11613getOriginal.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "subDescriptor.original.valueParameters");
                FunctionDescriptor mo11613getOriginal2 = functionDescriptor.mo11613getOriginal();
                Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal2, "superDescriptor.original");
                List<ValueParameterDescriptor> valueParameters2 = mo11613getOriginal2.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "superDescriptor.original.valueParameters");
                zip = CollectionsKt___CollectionsKt.zip(valueParameters, valueParameters2);
                for (Pair pair : zip) {
                    ValueParameterDescriptor subParameter = (ValueParameterDescriptor) pair.component1();
                    ValueParameterDescriptor superParameter = (ValueParameterDescriptor) pair.component2();
                    Intrinsics.checkExpressionValueIsNotNull(subParameter, "subParameter");
                    boolean z2 = mapValueParameterType((FunctionDescriptor) subDescriptor, subParameter) instanceof JvmType.Primitive;
                    Intrinsics.checkExpressionValueIsNotNull(superParameter, "superParameter");
                    if (z2 != (mapValueParameterType(functionDescriptor, superParameter) instanceof JvmType.Primitive)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final boolean isIncompatibleInAccordanceWithBuiltInOverridabilityRules(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor) {
        if ((callableDescriptor instanceof CallableMemberDescriptor) && (callableDescriptor2 instanceof FunctionDescriptor) && !KotlinBuiltIns.isBuiltIn(callableDescriptor2)) {
            BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
            FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableDescriptor2;
            Name name = functionDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "subDescriptor.name");
            if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
                BuiltinMethodsWithDifferentJvmName builtinMethodsWithDifferentJvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE;
                Name name2 = functionDescriptor.getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "subDescriptor.name");
                if (!builtinMethodsWithDifferentJvmName.getSameAsRenamedInJvmBuiltin(name2)) {
                    return false;
                }
            }
            CallableMemberDescriptor overriddenSpecialBuiltin = SpecialBuiltinMembers.getOverriddenSpecialBuiltin((CallableMemberDescriptor) callableDescriptor);
            boolean isHiddenToOvercomeSignatureClash = functionDescriptor.isHiddenToOvercomeSignatureClash();
            boolean z = callableDescriptor instanceof FunctionDescriptor;
            FunctionDescriptor functionDescriptor2 = (FunctionDescriptor) (!z ? null : callableDescriptor);
            if ((functionDescriptor2 == null || isHiddenToOvercomeSignatureClash != functionDescriptor2.isHiddenToOvercomeSignatureClash()) && (overriddenSpecialBuiltin == null || !functionDescriptor.isHiddenToOvercomeSignatureClash())) {
                return true;
            }
            if ((classDescriptor instanceof JavaClassDescriptor) && functionDescriptor.getInitialSignatureDescriptor() == null && overriddenSpecialBuiltin != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(classDescriptor, overriddenSpecialBuiltin)) {
                if ((overriddenSpecialBuiltin instanceof FunctionDescriptor) && z && BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FunctionDescriptor) overriddenSpecialBuiltin) != null) {
                    String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 2, null);
                    FunctionDescriptor mo11613getOriginal = ((FunctionDescriptor) callableDescriptor).mo11613getOriginal();
                    Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "superDescriptor.original");
                    if (Intrinsics.areEqual(computeJvmDescriptor$default, MethodSignatureMappingKt.computeJvmDescriptor$default(mo11613getOriginal, false, false, 2, null))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Contract getContract() {
        return ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(superDescriptor, "superDescriptor");
        Intrinsics.checkParameterIsNotNull(subDescriptor, "subDescriptor");
        if (isIncompatibleInAccordanceWithBuiltInOverridabilityRules(superDescriptor, subDescriptor, classDescriptor)) {
            return ExternalOverridabilityCondition.Result.INCOMPATIBLE;
        }
        if (Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(superDescriptor, subDescriptor)) {
            return ExternalOverridabilityCondition.Result.INCOMPATIBLE;
        }
        return ExternalOverridabilityCondition.Result.UNKNOWN;
    }
}
