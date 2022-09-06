package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes3.dex */
public final class JavaTypeResolver {
    private final LazyJavaResolverContext c;
    private final TypeParameterResolver typeParameterResolver;

    public JavaTypeResolver(@NotNull LazyJavaResolverContext c, @NotNull TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(typeParameterResolver, "typeParameterResolver");
        this.c = c;
        this.typeParameterResolver = typeParameterResolver;
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(@NotNull JavaClassifierType javaClassifierType, ClassDescriptor classDescriptor) {
        Variance variance;
        if (!JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1.INSTANCE.invoke2((JavaType) CollectionsKt.lastOrNull((List<? extends Object>) javaClassifierType.getTypeArguments()))) {
            return false;
        }
        TypeConstructor mo11528getTypeConstructor = JavaToKotlinClassMap.INSTANCE.convertReadOnlyToMutable(classDescriptor).mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "JavaToKotlinClassMap.con…         .typeConstructor");
        List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "JavaToKotlinClassMap.con…ypeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull((List<? extends Object>) parameters);
        if (typeParameterDescriptor == null || (variance = typeParameterDescriptor.getVariance()) == null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(variance, "JavaToKotlinClassMap.con….variance ?: return false");
        return variance != Variance.OUT_VARIANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0021, code lost:
        if ((!r3.isEmpty()) != false) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.types.TypeProjection> computeArguments(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType r16, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes r17, kotlin.reflect.jvm.internal.impl.types.TypeConstructor r18) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver.computeArguments(kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes, kotlin.reflect.jvm.internal.impl.types.TypeConstructor):java.util.List");
    }

    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, SimpleType simpleType) {
        Annotations lazyJavaAnnotations;
        if (simpleType == null || (lazyJavaAnnotations = simpleType.mo12070getAnnotations()) == null) {
            lazyJavaAnnotations = new LazyJavaAnnotations(this.c, javaClassifierType);
        }
        Annotations annotations = lazyJavaAnnotations;
        TypeConstructor computeTypeConstructor = computeTypeConstructor(javaClassifierType, javaTypeAttributes);
        TypeConstructor typeConstructor = null;
        if (computeTypeConstructor != null) {
            boolean isNullable = isNullable(javaTypeAttributes);
            if (simpleType != null) {
                typeConstructor = simpleType.mo12131getConstructor();
            }
            if (Intrinsics.areEqual(typeConstructor, computeTypeConstructor) && !javaClassifierType.isRaw() && isNullable) {
                return simpleType.mo12132makeNullableAsSpecified(true);
            }
            return KotlinTypeFactory.simpleType$default(annotations, computeTypeConstructor, computeArguments(javaClassifierType, javaTypeAttributes, computeTypeConstructor), isNullable, null, 16, null);
        }
        return null;
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        TypeConstructor mo11528getTypeConstructor;
        JavaClassifier classifier = javaClassifierType.getClassifier();
        if (classifier != null) {
            if (classifier instanceof JavaClass) {
                JavaClass javaClass = (JavaClass) classifier;
                FqName fqName = javaClass.getFqName();
                if (fqName != null) {
                    ClassDescriptor mapKotlinClass = mapKotlinClass(javaClassifierType, javaTypeAttributes, fqName);
                    if (mapKotlinClass == null) {
                        mapKotlinClass = this.c.getComponents().getModuleClassResolver().resolveClass(javaClass);
                    }
                    return (mapKotlinClass == null || (mo11528getTypeConstructor = mapKotlinClass.mo11528getTypeConstructor()) == null) ? createNotFoundClass(javaClassifierType) : mo11528getTypeConstructor;
                }
                throw new AssertionError("Class type should have a FQ name: " + classifier);
            } else if (classifier instanceof JavaTypeParameter) {
                TypeParameterDescriptor resolveTypeParameter = this.typeParameterResolver.resolveTypeParameter((JavaTypeParameter) classifier);
                if (resolveTypeParameter == null) {
                    return null;
                }
                return resolveTypeParameter.mo11528getTypeConstructor();
            } else {
                throw new IllegalStateException("Unknown classifier kind: " + classifier);
            }
        }
        return createNotFoundClass(javaClassifierType);
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType) {
        List<Integer> listOf;
        ClassId classId = ClassId.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName()));
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(FqName(…classifierQualifiedName))");
        NotFoundClasses notFoundClasses = this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses();
        listOf = CollectionsKt__CollectionsJVMKt.listOf(0);
        TypeConstructor mo11528getTypeConstructor = notFoundClasses.getClass(classId, listOf).mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "c.components.deserialize…istOf(0)).typeConstructor");
        return mo11528getTypeConstructor;
    }

    private final boolean isConflictingArgumentFor(@NotNull Variance variance, TypeParameterDescriptor typeParameterDescriptor) {
        return (typeParameterDescriptor.getVariance() == Variance.INVARIANT || variance == typeParameterDescriptor.getVariance()) ? false : true;
    }

    private final boolean isNullable(@NotNull JavaTypeAttributes javaTypeAttributes) {
        return (javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.isForAnnotationParameter() || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) ? false : true;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes, FqName fqName) {
        if (javaTypeAttributes.isForAnnotationParameter() && Intrinsics.areEqual(fqName, JavaTypeResolverKt.access$getJAVA_LANG_CLASS_FQ_NAME$p())) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(javaToKotlinClassMap, fqName, this.c.getModule().getBuiltIns(), null, 4, null);
        if (mapJavaToKotlin$default == null) {
            return null;
        }
        return (!javaToKotlinClassMap.isReadOnly(mapJavaToKotlin$default) || !(javaTypeAttributes.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND || javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, mapJavaToKotlin$default))) ? mapJavaToKotlin$default : javaToKotlinClassMap.convertReadOnlyToMutable(mapJavaToKotlin$default);
    }

    public static /* synthetic */ KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver, JavaArrayType javaArrayType, JavaTypeAttributes javaTypeAttributes, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return javaTypeResolver.transformArrayType(javaArrayType, javaTypeAttributes, z);
    }

    private final KotlinType transformJavaClassifierType(JavaClassifierType javaClassifierType, JavaTypeAttributes javaTypeAttributes) {
        JavaTypeResolver$transformJavaClassifierType$1 javaTypeResolver$transformJavaClassifierType$1 = new JavaTypeResolver$transformJavaClassifierType$1(javaClassifierType);
        boolean z = !javaTypeAttributes.isForAnnotationParameter() && javaTypeAttributes.getHowThisTypeIsUsed() != TypeUsage.SUPERTYPE;
        boolean isRaw = javaClassifierType.isRaw();
        if (!isRaw && !z) {
            SimpleType computeSimpleJavaClassifierType = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes, null);
            return computeSimpleJavaClassifierType != null ? computeSimpleJavaClassifierType : javaTypeResolver$transformJavaClassifierType$1.mo12560invoke();
        }
        SimpleType computeSimpleJavaClassifierType2 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
        if (computeSimpleJavaClassifierType2 != null) {
            SimpleType computeSimpleJavaClassifierType3 = computeSimpleJavaClassifierType(javaClassifierType, javaTypeAttributes.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), computeSimpleJavaClassifierType2);
            if (computeSimpleJavaClassifierType3 == null) {
                return javaTypeResolver$transformJavaClassifierType$1.mo12560invoke();
            }
            if (isRaw) {
                return new RawTypeImpl(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType3);
            }
            return KotlinTypeFactory.flexibleType(computeSimpleJavaClassifierType2, computeSimpleJavaClassifierType3);
        }
        return javaTypeResolver$transformJavaClassifierType$1.mo12560invoke();
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType, JavaTypeAttributes javaTypeAttributes, TypeParameterDescriptor typeParameterDescriptor) {
        if (javaType instanceof JavaWildcardType) {
            JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
            JavaType mo11641getBound = javaWildcardType.mo11641getBound();
            Variance variance = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
            if (mo11641getBound != null && !isConflictingArgumentFor(variance, typeParameterDescriptor)) {
                return TypeUtilsKt.createProjection(transformJavaType(mo11641getBound, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null)), variance, typeParameterDescriptor);
            }
            return JavaTypeResolverKt.makeStarProjection(typeParameterDescriptor, javaTypeAttributes);
        }
        return new TypeProjectionImpl(Variance.INVARIANT, transformJavaType(javaType, javaTypeAttributes));
    }

    @NotNull
    public final KotlinType transformArrayType(@NotNull JavaArrayType arrayType, @NotNull JavaTypeAttributes attr, boolean z) {
        Intrinsics.checkParameterIsNotNull(arrayType, "arrayType");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        JavaType mo11616getComponentType = arrayType.mo11616getComponentType();
        JavaPrimitiveType javaPrimitiveType = (JavaPrimitiveType) (!(mo11616getComponentType instanceof JavaPrimitiveType) ? null : mo11616getComponentType);
        PrimitiveType type = javaPrimitiveType != null ? javaPrimitiveType.getType() : null;
        if (type != null) {
            SimpleType primitiveArrayKotlinType = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(type);
            Intrinsics.checkExpressionValueIsNotNull(primitiveArrayKotlinType, "c.module.builtIns.getPri…KotlinType(primitiveType)");
            return attr.isForAnnotationParameter() ? primitiveArrayKotlinType : KotlinTypeFactory.flexibleType(primitiveArrayKotlinType, primitiveArrayKotlinType.mo12132makeNullableAsSpecified(true));
        }
        KotlinType transformJavaType = transformJavaType(mo11616getComponentType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, attr.isForAnnotationParameter(), null, 2, null));
        if (attr.isForAnnotationParameter()) {
            SimpleType arrayType2 = this.c.getModule().getBuiltIns().getArrayType(z ? Variance.OUT_VARIANCE : Variance.INVARIANT, transformJavaType);
            Intrinsics.checkExpressionValueIsNotNull(arrayType2, "c.module.builtIns.getArr…ctionKind, componentType)");
            return arrayType2;
        }
        SimpleType arrayType3 = this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, transformJavaType);
        Intrinsics.checkExpressionValueIsNotNull(arrayType3, "c.module.builtIns.getArr…INVARIANT, componentType)");
        return KotlinTypeFactory.flexibleType(arrayType3, this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, transformJavaType).mo12132makeNullableAsSpecified(true));
    }

    @NotNull
    public final KotlinType transformJavaType(@Nullable JavaType javaType, @NotNull JavaTypeAttributes attr) {
        KotlinType transformJavaType;
        SimpleType unitType;
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        if (javaType instanceof JavaPrimitiveType) {
            PrimitiveType type = ((JavaPrimitiveType) javaType).getType();
            if (type != null) {
                unitType = this.c.getModule().getBuiltIns().getPrimitiveKotlinType(type);
            } else {
                unitType = this.c.getModule().getBuiltIns().getUnitType();
            }
            Intrinsics.checkExpressionValueIsNotNull(unitType, "if (primitiveType != nul….module.builtIns.unitType");
            return unitType;
        } else if (javaType instanceof JavaClassifierType) {
            return transformJavaClassifierType((JavaClassifierType) javaType, attr);
        } else {
            if (javaType instanceof JavaArrayType) {
                return transformArrayType$default(this, (JavaArrayType) javaType, attr, false, 4, null);
            }
            if (javaType instanceof JavaWildcardType) {
                JavaType mo11641getBound = ((JavaWildcardType) javaType).mo11641getBound();
                if (mo11641getBound != null && (transformJavaType = transformJavaType(mo11641getBound, attr)) != null) {
                    return transformJavaType;
                }
                SimpleType defaultBound = this.c.getModule().getBuiltIns().getDefaultBound();
                Intrinsics.checkExpressionValueIsNotNull(defaultBound, "c.module.builtIns.defaultBound");
                return defaultBound;
            } else if (javaType == null) {
                SimpleType defaultBound2 = this.c.getModule().getBuiltIns().getDefaultBound();
                Intrinsics.checkExpressionValueIsNotNull(defaultBound2, "c.module.builtIns.defaultBound");
                return defaultBound2;
            } else {
                throw new UnsupportedOperationException("Unsupported type: " + javaType);
            }
        }
    }
}
