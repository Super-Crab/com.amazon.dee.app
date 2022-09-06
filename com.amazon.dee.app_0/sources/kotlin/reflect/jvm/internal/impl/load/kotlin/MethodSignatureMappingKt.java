package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: methodSignatureMapping.kt */
/* loaded from: classes3.dex */
public final class MethodSignatureMappingKt {
    private static final void appendErasedType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        sb.append(mapToJvmType(kotlinType));
    }

    @NotNull
    public static final String computeJvmDescriptor(@NotNull FunctionDescriptor computeJvmDescriptor, boolean z, boolean z2) {
        String asString;
        Intrinsics.checkParameterIsNotNull(computeJvmDescriptor, "$this$computeJvmDescriptor");
        StringBuilder sb = new StringBuilder();
        if (z2) {
            if (computeJvmDescriptor instanceof ConstructorDescriptor) {
                asString = "<init>";
            } else {
                asString = computeJvmDescriptor.getName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
            }
            sb.append(asString);
        }
        sb.append("(");
        for (ValueParameterDescriptor parameter : computeJvmDescriptor.getValueParameters()) {
            Intrinsics.checkExpressionValueIsNotNull(parameter, "parameter");
            KotlinType type = parameter.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "parameter.type");
            appendErasedType(sb, type);
        }
        sb.append(")");
        if (z) {
            if (TypeSignatureMappingKt.hasVoidReturnType(computeJvmDescriptor)) {
                sb.append(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            } else {
                KotlinType returnType = computeJvmDescriptor.getReturnType();
                if (returnType == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType!!");
                appendErasedType(sb, returnType);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static /* synthetic */ String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return computeJvmDescriptor(functionDescriptor, z, z2);
    }

    @Nullable
    public static final String computeJvmSignature(@NotNull CallableDescriptor computeJvmSignature) {
        Intrinsics.checkParameterIsNotNull(computeJvmSignature, "$this$computeJvmSignature");
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        if (DescriptorUtils.isLocal(computeJvmSignature)) {
            return null;
        }
        DeclarationDescriptor mo11607getContainingDeclaration = computeJvmSignature.mo11607getContainingDeclaration();
        if (!(mo11607getContainingDeclaration instanceof ClassDescriptor)) {
            mo11607getContainingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo11607getContainingDeclaration;
        if (classDescriptor != null) {
            Name name = classDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "classDescriptor.name");
            if (name.isSpecial()) {
                return null;
            }
            CallableDescriptor mo11613getOriginal = computeJvmSignature.mo11613getOriginal();
            if (!(mo11613getOriginal instanceof SimpleFunctionDescriptor)) {
                mo11613getOriginal = null;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) mo11613getOriginal;
            if (simpleFunctionDescriptor != null) {
                return signatureBuildingComponents.signature(classDescriptor, computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null));
            }
        }
        return null;
    }

    public static final boolean forceSingleValueParameterBoxing(@NotNull CallableDescriptor f) {
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava;
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (!(f instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) f;
        if (functionDescriptor.getValueParameters().size() != 1 || SpecialBuiltinMembers.isFromJavaOrBuiltins((CallableMemberDescriptor) f) || (!Intrinsics.areEqual(functionDescriptor.getName().asString(), BulkOperationType.remove))) {
            return false;
        }
        FunctionDescriptor mo11613getOriginal = functionDescriptor.mo11613getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "f.original");
        List<ValueParameterDescriptor> valueParameters = mo11613getOriginal.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "f.original.valueParameters");
        Object single = CollectionsKt.single((List<? extends Object>) valueParameters);
        Intrinsics.checkExpressionValueIsNotNull(single, "f.original.valueParameters.single()");
        KotlinType type = ((ValueParameterDescriptor) single).getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "f.original.valueParameters.single().type");
        JvmType mapToJvmType = mapToJvmType(type);
        JvmPrimitiveType jvmPrimitiveType = null;
        if (!(mapToJvmType instanceof JvmType.Primitive)) {
            mapToJvmType = null;
        }
        JvmType.Primitive primitive = (JvmType.Primitive) mapToJvmType;
        if (primitive != null) {
            jvmPrimitiveType = primitive.getJvmPrimitiveType();
        }
        if (jvmPrimitiveType != JvmPrimitiveType.INT || (overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(functionDescriptor)) == null) {
            return false;
        }
        FunctionDescriptor mo11613getOriginal2 = overriddenBuiltinFunctionWithErasedValueParametersInJava.mo11613getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal2, "overridden.original");
        List<ValueParameterDescriptor> valueParameters2 = mo11613getOriginal2.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters2, "overridden.original.valueParameters");
        Object single2 = CollectionsKt.single((List<? extends Object>) valueParameters2);
        Intrinsics.checkExpressionValueIsNotNull(single2, "overridden.original.valueParameters.single()");
        KotlinType type2 = ((ValueParameterDescriptor) single2).getType();
        Intrinsics.checkExpressionValueIsNotNull(type2, "overridden.original.valueParameters.single().type");
        JvmType mapToJvmType2 = mapToJvmType(type2);
        DeclarationDescriptor mo11607getContainingDeclaration = overriddenBuiltinFunctionWithErasedValueParametersInJava.mo11607getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "overridden.containingDeclaration");
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameUnsafe(mo11607getContainingDeclaration), KotlinBuiltIns.FQ_NAMES.mutableCollection.toUnsafe()) && (mapToJvmType2 instanceof JvmType.Object) && Intrinsics.areEqual(((JvmType.Object) mapToJvmType2).getInternalName(), "java/lang/Object");
    }

    @NotNull
    public static final String getInternalName(@NotNull ClassDescriptor internalName) {
        Intrinsics.checkParameterIsNotNull(internalName, "$this$internalName");
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = DescriptorUtilsKt.getFqNameSafe(internalName).toUnsafe();
        Intrinsics.checkExpressionValueIsNotNull(unsafe, "fqNameSafe.toUnsafe()");
        ClassId mapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (mapKotlinToJava != null) {
            JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
            Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(it)");
            String internalName2 = byClassId.getInternalName();
            Intrinsics.checkExpressionValueIsNotNull(internalName2, "JvmClassName.byClassId(it).internalName");
            return internalName2;
        }
        return TypeSignatureMappingKt.computeInternalName$default(internalName, null, 2, null);
    }

    @NotNull
    public static final JvmType mapToJvmType(@NotNull KotlinType mapToJvmType) {
        Intrinsics.checkParameterIsNotNull(mapToJvmType, "$this$mapToJvmType");
        return (JvmType) TypeSignatureMappingKt.mapType$default(mapToJvmType, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, 32, null);
    }
}
