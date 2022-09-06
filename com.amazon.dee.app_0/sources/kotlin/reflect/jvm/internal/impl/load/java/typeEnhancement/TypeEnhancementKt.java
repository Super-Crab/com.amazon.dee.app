package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeEnhancement.kt */
/* loaded from: classes3.dex */
public final class TypeEnhancementKt {
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS;

    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MutabilityQualifier.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            $EnumSwitchMapping$0[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[NullabilityQualifier.values().length];
            $EnumSwitchMapping$1[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            $EnumSwitchMapping$1[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
        }
    }

    static {
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName);
        FqName fqName2 = JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION");
        ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(fqName2);
    }

    private static final Annotations compositeAnnotationsOrSingle(@NotNull List<? extends Annotations> list) {
        List list2;
        int size = list.size();
        if (size != 0) {
            if (size != 1) {
                list2 = CollectionsKt___CollectionsKt.toList(list);
                return new CompositeAnnotations(list2);
            }
            return (Annotations) CollectionsKt.single((List<? extends Object>) list);
        }
        throw new IllegalStateException("At least one Annotations object expected".toString());
    }

    @Nullable
    public static final KotlinType enhance(@NotNull KotlinType enhance, @NotNull Function1<? super Integer, JavaTypeQualifiers> qualifiers) {
        Intrinsics.checkParameterIsNotNull(enhance, "$this$enhance");
        Intrinsics.checkParameterIsNotNull(qualifiers, "qualifiers");
        return enhancePossiblyFlexible(enhance.unwrap(), qualifiers, 0).getTypeIfChanged();
    }

    private static final SimpleResult enhanceInflexible(@NotNull SimpleType simpleType, Function1<? super Integer, JavaTypeQualifiers> function1, int i, TypeComponentPosition typeComponentPosition) {
        int collectionSizeOrDefault;
        List listOfNotNull;
        TypeProjection createProjection;
        boolean z = false;
        if (shouldEnhance(typeComponentPosition) || !simpleType.getArguments().isEmpty()) {
            ClassifierDescriptor mo12085getDeclarationDescriptor = simpleType.mo12131getConstructor().mo12085getDeclarationDescriptor();
            if (mo12085getDeclarationDescriptor != null) {
                Intrinsics.checkExpressionValueIsNotNull(mo12085getDeclarationDescriptor, "constructor.declarationD…pleResult(this, 1, false)");
                JavaTypeQualifiers mo12165invoke = function1.mo12165invoke(Integer.valueOf(i));
                EnhancementResult<ClassifierDescriptor> enhanceMutability = enhanceMutability(mo12085getDeclarationDescriptor, mo12165invoke, typeComponentPosition);
                ClassifierDescriptor component1 = enhanceMutability.component1();
                Annotations component2 = enhanceMutability.component2();
                TypeConstructor mo11528getTypeConstructor = component1.mo11528getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "enhancedClassifier.typeConstructor");
                int i2 = i + 1;
                boolean z2 = component2 != null;
                List<TypeProjection> arguments = simpleType.getArguments();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                int i3 = i2;
                int i4 = 0;
                for (Object obj : arguments) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    TypeProjection typeProjection = (TypeProjection) obj;
                    if (typeProjection.isStarProjection()) {
                        i3++;
                        TypeConstructor mo11528getTypeConstructor2 = component1.mo11528getTypeConstructor();
                        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor2, "enhancedClassifier.typeConstructor");
                        createProjection = TypeUtils.makeStarProjection(mo11528getTypeConstructor2.getParameters().get(i4));
                    } else {
                        Result enhancePossiblyFlexible = enhancePossiblyFlexible(typeProjection.getType().unwrap(), function1, i3);
                        z2 = (z2 || enhancePossiblyFlexible.getWereChanges()) ? true : z;
                        i3 += enhancePossiblyFlexible.getSubtreeSize();
                        KotlinType mo11705getType = enhancePossiblyFlexible.mo11705getType();
                        Variance projectionKind = typeProjection.getProjectionKind();
                        Intrinsics.checkExpressionValueIsNotNull(projectionKind, "arg.projectionKind");
                        createProjection = TypeUtilsKt.createProjection(mo11705getType, projectionKind, mo11528getTypeConstructor.getParameters().get(i4));
                    }
                    arrayList.add(createProjection);
                    i4 = i5;
                    z = false;
                }
                EnhancementResult<Boolean> enhancedNullability = getEnhancedNullability(simpleType, mo12165invoke, typeComponentPosition);
                boolean booleanValue = enhancedNullability.component1().booleanValue();
                Annotations component22 = enhancedNullability.component2();
                int i6 = i3 - i;
                if (!(z2 || component22 != null)) {
                    return new SimpleResult(simpleType, i6, false);
                }
                listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new Annotations[]{simpleType.mo12070getAnnotations(), component2, component22});
                SimpleType simpleType$default = KotlinTypeFactory.simpleType$default(compositeAnnotationsOrSingle(listOfNotNull), mo11528getTypeConstructor, arrayList, booleanValue, null, 16, null);
                UnwrappedType unwrappedType = simpleType$default;
                if (mo12165invoke.isNotNullTypeParameter()) {
                    unwrappedType = new NotNullTypeParameter(simpleType$default);
                }
                if (component22 != null && mo12165invoke.isNullabilityQualifierForWarning()) {
                    unwrappedType = TypeWithEnhancementKt.wrapEnhancement(simpleType, unwrappedType);
                }
                if (unwrappedType == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
                }
                return new SimpleResult((SimpleType) unwrappedType, i6, true);
            }
            return new SimpleResult(simpleType, 1, false);
        }
        return new SimpleResult(simpleType, 1, false);
    }

    private static final EnhancementResult<ClassifierDescriptor> enhanceMutability(@NotNull ClassifierDescriptor classifierDescriptor, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (shouldEnhance(typeComponentPosition) && (classifierDescriptor instanceof ClassDescriptor)) {
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
            if (mutability != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[mutability.ordinal()];
                if (i != 1) {
                    if (i == 2 && typeComponentPosition == TypeComponentPosition.FLEXIBLE_UPPER) {
                        ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
                        if (javaToKotlinClassMap.isReadOnly(classDescriptor)) {
                            return enhancedMutability(javaToKotlinClassMap.convertReadOnlyToMutable(classDescriptor));
                        }
                    }
                } else if (typeComponentPosition == TypeComponentPosition.FLEXIBLE_LOWER) {
                    ClassDescriptor classDescriptor2 = (ClassDescriptor) classifierDescriptor;
                    if (javaToKotlinClassMap.isMutable(classDescriptor2)) {
                        return enhancedMutability(javaToKotlinClassMap.convertMutableToReadOnly(classDescriptor2));
                    }
                }
            }
            return noChange(classifierDescriptor);
        }
        return noChange(classifierDescriptor);
    }

    private static final Result enhancePossiblyFlexible(@NotNull UnwrappedType unwrappedType, Function1<? super Integer, JavaTypeQualifiers> function1, int i) {
        boolean z = false;
        if (KotlinTypeKt.isError(unwrappedType)) {
            return new Result(unwrappedType, 1, false);
        }
        if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult enhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), function1, i, TypeComponentPosition.FLEXIBLE_LOWER);
            SimpleResult enhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), function1, i, TypeComponentPosition.FLEXIBLE_UPPER);
            boolean z2 = enhanceInflexible.getSubtreeSize() == enhanceInflexible2.getSubtreeSize();
            if (_Assertions.ENABLED && !z2) {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113("Different tree sizes of bounds: ", "lower = (");
                outline113.append(flexibleType.getLowerBound());
                outline113.append(", ");
                outline113.append(enhanceInflexible.getSubtreeSize());
                outline113.append("), ");
                outline113.append("upper = (");
                outline113.append(flexibleType.getUpperBound());
                outline113.append(", ");
                throw new AssertionError(GeneratedOutlineSupport1.outline85(outline113, enhanceInflexible2.getSubtreeSize(), ')'));
            }
            if (enhanceInflexible.getWereChanges() || enhanceInflexible2.getWereChanges()) {
                z = true;
            }
            KotlinType enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible.mo11705getType());
            if (enhancement == null) {
                enhancement = TypeWithEnhancementKt.getEnhancement(enhanceInflexible2.mo11705getType());
            }
            if (z) {
                unwrappedType = TypeWithEnhancementKt.wrapEnhancement(unwrappedType instanceof RawTypeImpl ? new RawTypeImpl(enhanceInflexible.mo11705getType(), enhanceInflexible2.mo11705getType()) : KotlinTypeFactory.flexibleType(enhanceInflexible.mo11705getType(), enhanceInflexible2.mo11705getType()), enhancement);
            }
            return new Result(unwrappedType, enhanceInflexible.getSubtreeSize(), z);
        } else if (!(unwrappedType instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        } else {
            return enhanceInflexible((SimpleType) unwrappedType, function1, i, TypeComponentPosition.INFLEXIBLE);
        }
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult<>(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult<>(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final EnhancementResult<Boolean> getEnhancedNullability(@NotNull KotlinType kotlinType, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!shouldEnhance(typeComponentPosition)) {
            return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
        }
        NullabilityQualifier nullability = javaTypeQualifiers.getNullability();
        if (nullability != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[nullability.ordinal()];
            if (i == 1) {
                return enhancedNullability(true);
            }
            if (i == 2) {
                return enhancedNullability(false);
            }
        }
        return noChange(Boolean.valueOf(kotlinType.isMarkedNullable()));
    }

    public static final boolean hasEnhancedNullability(@NotNull KotlinType hasEnhancedNullability) {
        Intrinsics.checkParameterIsNotNull(hasEnhancedNullability, "$this$hasEnhancedNullability");
        return hasEnhancedNullability(SimpleClassicTypeSystemContext.INSTANCE, hasEnhancedNullability);
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult<>(t, null);
    }

    public static final boolean shouldEnhance(@NotNull TypeComponentPosition shouldEnhance) {
        Intrinsics.checkParameterIsNotNull(shouldEnhance, "$this$shouldEnhance");
        return shouldEnhance != TypeComponentPosition.INFLEXIBLE;
    }

    public static final boolean hasEnhancedNullability(@NotNull TypeSystemCommonBackendContext hasEnhancedNullability, @NotNull KotlinTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(hasEnhancedNullability, "$this$hasEnhancedNullability");
        Intrinsics.checkParameterIsNotNull(type, "type");
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION");
        return hasEnhancedNullability.hasAnnotation(type, fqName);
    }
}
