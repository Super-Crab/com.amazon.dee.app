package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaConstructor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaField;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: RuntimeTypeMapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lkotlin/reflect/jvm/internal/RuntimeTypeMapper;", "", "()V", "JAVA_LANG_VOID", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "primitiveType", "Lkotlin/reflect/jvm/internal/impl/builtins/PrimitiveType;", "Ljava/lang/Class;", "getPrimitiveType", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "isKnownBuiltInFunction", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "mapJvmClassToKotlinClassId", "klass", "mapJvmFunctionSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "mapName", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "mapPropertySignature", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "possiblyOverriddenProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "mapSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "possiblySubstitutedFunction", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RuntimeTypeMapper {
    public static final RuntimeTypeMapper INSTANCE = new RuntimeTypeMapper();
    private static final ClassId JAVA_LANG_VOID;

    static {
        ClassId classId = ClassId.topLevel(new FqName("java.lang.Void"));
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(\"java.lang.Void\"))");
        JAVA_LANG_VOID = classId;
    }

    private RuntimeTypeMapper() {
    }

    private final PrimitiveType getPrimitiveType(@NotNull Class<?> cls) {
        if (cls.isPrimitive()) {
            JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(cls.getSimpleName());
            Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(simpleName)");
            return jvmPrimitiveType.getPrimitiveType();
        }
        return null;
    }

    private final boolean isKnownBuiltInFunction(FunctionDescriptor functionDescriptor) {
        if (DescriptorFactory.isEnumValueOfMethod(functionDescriptor) || DescriptorFactory.isEnumValuesMethod(functionDescriptor)) {
            return true;
        }
        return Intrinsics.areEqual(functionDescriptor.getName(), CloneableClassScope.Companion.getCLONE_NAME()) && functionDescriptor.getValueParameters().isEmpty();
    }

    private final JvmFunctionSignature.KotlinFunction mapJvmFunctionSignature(FunctionDescriptor functionDescriptor) {
        return new JvmFunctionSignature.KotlinFunction(new JvmMemberSignature.Method(mapName(functionDescriptor), MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 1, null)));
    }

    private final String mapName(CallableMemberDescriptor callableMemberDescriptor) {
        String asString;
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(callableMemberDescriptor);
        if (jvmMethodNameIfSpecial == null) {
            if (callableMemberDescriptor instanceof PropertyGetterDescriptor) {
                asString = JvmAbi.getterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString());
            } else {
                asString = callableMemberDescriptor instanceof PropertySetterDescriptor ? JvmAbi.setterName(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName().asString()) : callableMemberDescriptor.getName().asString();
            }
            jvmMethodNameIfSpecial = asString;
            Intrinsics.checkExpressionValueIsNotNull(jvmMethodNameIfSpecial, "when (descriptor) {\n    …name.asString()\n        }");
        }
        return jvmMethodNameIfSpecial;
    }

    @NotNull
    public final ClassId mapJvmClassToKotlinClassId(@NotNull Class<?> klass) {
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        if (klass.isArray()) {
            Class<?> componentType = klass.getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "klass.componentType");
            PrimitiveType primitiveType = getPrimitiveType(componentType);
            if (primitiveType != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, primitiveType.getArrayTypeName());
            }
            ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.array.toSafe());
            Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB….FQ_NAMES.array.toSafe())");
            return classId;
        } else if (Intrinsics.areEqual(klass, Void.TYPE)) {
            return JAVA_LANG_VOID;
        } else {
            PrimitiveType primitiveType2 = getPrimitiveType(klass);
            if (primitiveType2 != null) {
                return new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, primitiveType2.getTypeName());
            }
            ClassId classId2 = ReflectClassUtilKt.getClassId(klass);
            if (!classId2.isLocal()) {
                JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
                FqName asSingleFqName = classId2.asSingleFqName();
                Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "classId.asSingleFqName()");
                ClassId mapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(asSingleFqName);
                if (mapJavaToKotlin != null) {
                    return mapJavaToKotlin;
                }
            }
            return classId2;
        }
    }

    @NotNull
    public final JvmPropertySignature mapPropertySignature(@NotNull PropertyDescriptor possiblyOverriddenProperty) {
        Intrinsics.checkParameterIsNotNull(possiblyOverriddenProperty, "possiblyOverriddenProperty");
        CallableMemberDescriptor unwrapFakeOverride = DescriptorUtils.unwrapFakeOverride(possiblyOverriddenProperty);
        Intrinsics.checkExpressionValueIsNotNull(unwrapFakeOverride, "DescriptorUtils.unwrapFa…ssiblyOverriddenProperty)");
        PropertyDescriptor mo11613getOriginal = ((PropertyDescriptor) unwrapFakeOverride).mo11613getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "DescriptorUtils.unwrapFa…rriddenProperty).original");
        Method method = null;
        JvmFunctionSignature.KotlinFunction kotlinFunction = null;
        if (mo11613getOriginal instanceof DeserializedPropertyDescriptor) {
            DeserializedPropertyDescriptor deserializedPropertyDescriptor = (DeserializedPropertyDescriptor) mo11613getOriginal;
            ProtoBuf.Property mo12068getProto = deserializedPropertyDescriptor.mo12068getProto();
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
            Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.propertySignature");
            JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(mo12068getProto, generatedExtension);
            if (jvmPropertySignature != null) {
                return new JvmPropertySignature.KotlinProperty(mo11613getOriginal, mo12068getProto, jvmPropertySignature, deserializedPropertyDescriptor.getNameResolver(), deserializedPropertyDescriptor.getTypeTable());
            }
        } else if (mo11613getOriginal instanceof JavaPropertyDescriptor) {
            SourceElement source = ((JavaPropertyDescriptor) mo11613getOriginal).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            JavaElement mo11614getJavaElement = javaSourceElement != null ? javaSourceElement.mo11614getJavaElement() : null;
            if (mo11614getJavaElement instanceof ReflectJavaField) {
                return new JvmPropertySignature.JavaField(((ReflectJavaField) mo11614getJavaElement).mo11631getMember());
            }
            if (mo11614getJavaElement instanceof ReflectJavaMethod) {
                Method mo11631getMember = ((ReflectJavaMethod) mo11614getJavaElement).mo11631getMember();
                PropertySetterDescriptor setter = mo11613getOriginal.getSetter();
                SourceElement source2 = setter != null ? setter.getSource() : null;
                if (!(source2 instanceof JavaSourceElement)) {
                    source2 = null;
                }
                JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
                JavaElement mo11614getJavaElement2 = javaSourceElement2 != null ? javaSourceElement2.mo11614getJavaElement() : null;
                if (!(mo11614getJavaElement2 instanceof ReflectJavaMethod)) {
                    mo11614getJavaElement2 = null;
                }
                ReflectJavaMethod reflectJavaMethod = (ReflectJavaMethod) mo11614getJavaElement2;
                if (reflectJavaMethod != null) {
                    method = reflectJavaMethod.mo11631getMember();
                }
                return new JvmPropertySignature.JavaMethodProperty(mo11631getMember, method);
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java field " + mo11613getOriginal + " (source = " + mo11614getJavaElement + ')');
        }
        PropertyGetterDescriptor mo11581getGetter = mo11613getOriginal.mo11581getGetter();
        if (mo11581getGetter == null) {
            Intrinsics.throwNpe();
        }
        JvmFunctionSignature.KotlinFunction mapJvmFunctionSignature = mapJvmFunctionSignature(mo11581getGetter);
        PropertySetterDescriptor setter2 = mo11613getOriginal.getSetter();
        if (setter2 != null) {
            kotlinFunction = mapJvmFunctionSignature(setter2);
        }
        return new JvmPropertySignature.MappedKotlinProperty(mapJvmFunctionSignature, kotlinFunction);
    }

    @NotNull
    public final JvmFunctionSignature mapSignature(@NotNull FunctionDescriptor possiblySubstitutedFunction) {
        Method mo11631getMember;
        JvmMemberSignature.Method jvmConstructorSignature;
        JvmMemberSignature.Method jvmMethodSignature;
        Intrinsics.checkParameterIsNotNull(possiblySubstitutedFunction, "possiblySubstitutedFunction");
        CallableMemberDescriptor unwrapFakeOverride = DescriptorUtils.unwrapFakeOverride(possiblySubstitutedFunction);
        Intrinsics.checkExpressionValueIsNotNull(unwrapFakeOverride, "DescriptorUtils.unwrapFa…siblySubstitutedFunction)");
        FunctionDescriptor mo11613getOriginal = ((FunctionDescriptor) unwrapFakeOverride).mo11613getOriginal();
        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "DescriptorUtils.unwrapFa…titutedFunction).original");
        if (mo11613getOriginal instanceof DeserializedCallableMemberDescriptor) {
            DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor = (DeserializedCallableMemberDescriptor) mo11613getOriginal;
            MessageLite mo12068getProto = deserializedCallableMemberDescriptor.mo12068getProto();
            if ((mo12068getProto instanceof ProtoBuf.Function) && (jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) mo12068getProto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable())) != null) {
                return new JvmFunctionSignature.KotlinFunction(jvmMethodSignature);
            }
            if ((mo12068getProto instanceof ProtoBuf.Constructor) && (jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) mo12068getProto, deserializedCallableMemberDescriptor.getNameResolver(), deserializedCallableMemberDescriptor.getTypeTable())) != null) {
                DeclarationDescriptor mo11607getContainingDeclaration = possiblySubstitutedFunction.mo11607getContainingDeclaration();
                Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "possiblySubstitutedFunction.containingDeclaration");
                if (InlineClassesUtilsKt.isInlineClass(mo11607getContainingDeclaration)) {
                    return new JvmFunctionSignature.KotlinFunction(jvmConstructorSignature);
                }
                return new JvmFunctionSignature.KotlinConstructor(jvmConstructorSignature);
            }
            return mapJvmFunctionSignature(mo11613getOriginal);
        }
        JavaElement javaElement = null;
        if (mo11613getOriginal instanceof JavaMethodDescriptor) {
            SourceElement source = ((JavaMethodDescriptor) mo11613getOriginal).getSource();
            if (!(source instanceof JavaSourceElement)) {
                source = null;
            }
            JavaSourceElement javaSourceElement = (JavaSourceElement) source;
            JavaElement mo11614getJavaElement = javaSourceElement != null ? javaSourceElement.mo11614getJavaElement() : null;
            if (!(mo11614getJavaElement instanceof ReflectJavaMethod)) {
                mo11614getJavaElement = null;
            }
            ReflectJavaMethod reflectJavaMethod = (ReflectJavaMethod) mo11614getJavaElement;
            if (reflectJavaMethod != null && (mo11631getMember = reflectJavaMethod.mo11631getMember()) != null) {
                return new JvmFunctionSignature.JavaMethod(mo11631getMember);
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java method " + mo11613getOriginal);
        } else if (mo11613getOriginal instanceof JavaClassConstructorDescriptor) {
            SourceElement source2 = ((JavaClassConstructorDescriptor) mo11613getOriginal).getSource();
            if (!(source2 instanceof JavaSourceElement)) {
                source2 = null;
            }
            JavaSourceElement javaSourceElement2 = (JavaSourceElement) source2;
            if (javaSourceElement2 != null) {
                javaElement = javaSourceElement2.mo11614getJavaElement();
            }
            if (javaElement instanceof ReflectJavaConstructor) {
                return new JvmFunctionSignature.JavaConstructor(((ReflectJavaConstructor) javaElement).mo11631getMember());
            }
            if (javaElement instanceof ReflectJavaClass) {
                ReflectJavaClass reflectJavaClass = (ReflectJavaClass) javaElement;
                if (reflectJavaClass.isAnnotationType()) {
                    return new JvmFunctionSignature.FakeJavaAnnotationConstructor(reflectJavaClass.mo11620getElement());
                }
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java constructor " + mo11613getOriginal + " (" + javaElement + ')');
        } else if (isKnownBuiltInFunction(mo11613getOriginal)) {
            return mapJvmFunctionSignature(mo11613getOriginal);
        } else {
            throw new KotlinReflectionInternalError("Unknown origin of " + mo11613getOriginal + " (" + mo11613getOriginal.getClass() + ')');
        }
    }
}
