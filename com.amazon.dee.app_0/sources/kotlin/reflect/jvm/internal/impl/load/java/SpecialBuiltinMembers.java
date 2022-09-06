package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: specialBuiltinMembers.kt */
@JvmName(name = "SpecialBuiltinMembers")
/* loaded from: classes3.dex */
public final class SpecialBuiltinMembers {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName child(@NotNull FqName fqName, String str) {
        FqName child = fqName.child(Name.identifier(str));
        Intrinsics.checkExpressionValueIsNotNull(child, "child(Name.identifier(name))");
        return child;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName childSafe(@NotNull FqNameUnsafe fqNameUnsafe, String str) {
        FqName safe = fqNameUnsafe.child(Name.identifier(str)).toSafe();
        Intrinsics.checkExpressionValueIsNotNull(safe, "child(Name.identifier(name)).toSafe()");
        return safe;
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(@NotNull CallableMemberDescriptor doesOverrideBuiltinWithDifferentJvmName) {
        Intrinsics.checkParameterIsNotNull(doesOverrideBuiltinWithDifferentJvmName, "$this$doesOverrideBuiltinWithDifferentJvmName");
        return getOverriddenBuiltinWithDifferentJvmName(doesOverrideBuiltinWithDifferentJvmName) != null;
    }

    @Nullable
    public static final String getJvmMethodNameIfSpecial(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        CallableMemberDescriptor propertyIfAccessor;
        Name jvmName;
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "callableMemberDescriptor");
        CallableMemberDescriptor overriddenBuiltinThatAffectsJvmName = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        if (overriddenBuiltinThatAffectsJvmName == null || (propertyIfAccessor = DescriptorUtilsKt.getPropertyIfAccessor(overriddenBuiltinThatAffectsJvmName)) == null) {
            return null;
        }
        if (propertyIfAccessor instanceof PropertyDescriptor) {
            return BuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyIfAccessor);
        }
        if ((propertyIfAccessor instanceof SimpleFunctionDescriptor) && (jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) propertyIfAccessor)) != null) {
            return jvmName.asString();
        }
        return null;
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        if (KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
            return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        }
        return null;
    }

    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenBuiltinWithDifferentJvmName(@NotNull T getOverriddenBuiltinWithDifferentJvmName) {
        Intrinsics.checkParameterIsNotNull(getOverriddenBuiltinWithDifferentJvmName, "$this$getOverriddenBuiltinWithDifferentJvmName");
        if (BuiltinMethodsWithDifferentJvmName.INSTANCE.getORIGINAL_SHORT_NAMES().contains(getOverriddenBuiltinWithDifferentJvmName.getName()) || BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES$descriptors_jvm().contains(DescriptorUtilsKt.getPropertyIfAccessor(getOverriddenBuiltinWithDifferentJvmName).getName())) {
            if ((getOverriddenBuiltinWithDifferentJvmName instanceof PropertyDescriptor) || (getOverriddenBuiltinWithDifferentJvmName instanceof PropertyAccessorDescriptor)) {
                return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenBuiltinWithDifferentJvmName, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$1.INSTANCE, 1, null);
            }
            if (!(getOverriddenBuiltinWithDifferentJvmName instanceof SimpleFunctionDescriptor)) {
                return null;
            }
            return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenBuiltinWithDifferentJvmName, false, SpecialBuiltinMembers$getOverriddenBuiltinWithDifferentJvmName$2.INSTANCE, 1, null);
        }
        return null;
    }

    @Nullable
    public static final <T extends CallableMemberDescriptor> T getOverriddenSpecialBuiltin(@NotNull T getOverriddenSpecialBuiltin) {
        Intrinsics.checkParameterIsNotNull(getOverriddenSpecialBuiltin, "$this$getOverriddenSpecialBuiltin");
        T t = (T) getOverriddenBuiltinWithDifferentJvmName(getOverriddenSpecialBuiltin);
        if (t != null) {
            return t;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = getOverriddenSpecialBuiltin.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        if (builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return (T) DescriptorUtilsKt.firstOverridden$default(getOverriddenSpecialBuiltin, false, SpecialBuiltinMembers$getOverriddenSpecialBuiltin$2.INSTANCE, 1, null);
        }
        return null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(@NotNull ClassDescriptor hasRealKotlinSuperClassWithOverrideOf, @NotNull CallableDescriptor specialCallableDescriptor) {
        Intrinsics.checkParameterIsNotNull(hasRealKotlinSuperClassWithOverrideOf, "$this$hasRealKotlinSuperClassWithOverrideOf");
        Intrinsics.checkParameterIsNotNull(specialCallableDescriptor, "specialCallableDescriptor");
        DeclarationDescriptor mo11607getContainingDeclaration = specialCallableDescriptor.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration != null) {
            SimpleType defaultType = ((ClassDescriptor) mo11607getContainingDeclaration).getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "(specialCallableDescript…ssDescriptor).defaultType");
            ClassDescriptor superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(hasRealKotlinSuperClassWithOverrideOf);
            while (true) {
                boolean z = false;
                if (superClassDescriptor == null) {
                    return false;
                }
                if (!(superClassDescriptor instanceof JavaClassDescriptor)) {
                    if (TypeCheckingProcedure.findCorrespondingSupertype(superClassDescriptor.getDefaultType(), defaultType) != null) {
                        z = true;
                    }
                    if (z) {
                        return !KotlinBuiltIns.isBuiltIn(superClassDescriptor);
                    }
                }
                superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(superClassDescriptor);
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        }
    }

    public static final boolean isFromJava(@NotNull CallableMemberDescriptor isFromJava) {
        Intrinsics.checkParameterIsNotNull(isFromJava, "$this$isFromJava");
        return DescriptorUtilsKt.getPropertyIfAccessor(isFromJava).mo11607getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(@NotNull CallableMemberDescriptor isFromJavaOrBuiltins) {
        Intrinsics.checkParameterIsNotNull(isFromJavaOrBuiltins, "$this$isFromJavaOrBuiltins");
        return isFromJava(isFromJavaOrBuiltins) || KotlinBuiltIns.isBuiltIn(isFromJavaOrBuiltins);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NameAndSignature method(@NotNull String str, String str2, String str3, String str4) {
        Name identifier = Name.identifier(str2);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        return new NameAndSignature(identifier, signatureBuildingComponents.signature(str, str2 + '(' + str3 + ')' + str4));
    }
}
