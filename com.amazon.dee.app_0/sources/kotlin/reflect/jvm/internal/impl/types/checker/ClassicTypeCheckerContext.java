package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClassicTypeCheckerContext.kt */
/* loaded from: classes4.dex */
public class ClassicTypeCheckerContext extends AbstractTypeCheckerContext implements ClassicTypeSystemContext {
    public static final Companion Companion = new Companion(null);
    private final boolean allowedTypeVariable;
    private final boolean errorTypeEqualsToAnything;
    @NotNull
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final boolean stubTypeEqualsToAnything;

    /* compiled from: ClassicTypeCheckerContext.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform classicSubstitutionSupertypePolicy(@NotNull final ClassicTypeSystemContext classicSubstitutionSupertypePolicy, @NotNull SimpleTypeMarker type) {
            Intrinsics.checkParameterIsNotNull(classicSubstitutionSupertypePolicy, "$this$classicSubstitutionSupertypePolicy");
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type instanceof SimpleType) {
                final TypeSubstitutor buildSubstitutor = TypeConstructorSubstitution.Companion.create((KotlinType) type).buildSubstitutor();
                return new AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerContext$Companion$classicSubstitutionSupertypePolicy$2
                    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext.SupertypesPolicy
                    @NotNull
                    /* renamed from: transformType */
                    public SimpleTypeMarker mo12079transformType(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker type2) {
                        Intrinsics.checkParameterIsNotNull(context, "context");
                        Intrinsics.checkParameterIsNotNull(type2, "type");
                        ClassicTypeSystemContext classicTypeSystemContext = ClassicTypeSystemContext.this;
                        TypeSubstitutor typeSubstitutor = buildSubstitutor;
                        SimpleTypeMarker lowerBoundIfFlexible = classicTypeSystemContext.lowerBoundIfFlexible(type2);
                        if (lowerBoundIfFlexible != null) {
                            KotlinType safeSubstitute = typeSubstitutor.safeSubstitute((KotlinType) lowerBoundIfFlexible, Variance.INVARIANT);
                            Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "substitutor.safeSubstitu…ANT\n                    )");
                            SimpleTypeMarker asSimpleType = classicTypeSystemContext.asSimpleType(safeSubstitute);
                            if (asSimpleType == null) {
                                Intrinsics.throwNpe();
                            }
                            return asSimpleType;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.KotlinType");
                    }
                };
            }
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.access$errorMessage(type).toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ClassicTypeCheckerContext(boolean z, boolean z2, boolean z3, KotlinTypeRefiner kotlinTypeRefiner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? KotlinTypeRefiner.Default.INSTANCE : kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean areEqualTypeConstructors(@NotNull TypeConstructorMarker a, @NotNull TypeConstructorMarker b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (a instanceof TypeConstructor) {
            if (b instanceof TypeConstructor) {
                return areEqualTypeConstructors((TypeConstructor) a, (TypeConstructor) b);
            }
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.access$errorMessage(b).toString());
        }
        throw new IllegalArgumentException(ClassicTypeCheckerContextKt.access$errorMessage(a).toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int argumentsCount(@NotNull KotlinTypeMarker argumentsCount) {
        Intrinsics.checkParameterIsNotNull(argumentsCount, "$this$argumentsCount");
        return ClassicTypeSystemContext.DefaultImpls.argumentsCount(this, argumentsCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentListMarker asArgumentList(@NotNull SimpleTypeMarker asArgumentList) {
        Intrinsics.checkParameterIsNotNull(asArgumentList, "$this$asArgumentList");
        return ClassicTypeSystemContext.DefaultImpls.asArgumentList(this, asArgumentList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public CapturedTypeMarker asCapturedType(@NotNull SimpleTypeMarker asCapturedType) {
        Intrinsics.checkParameterIsNotNull(asCapturedType, "$this$asCapturedType");
        return ClassicTypeSystemContext.DefaultImpls.asCapturedType(this, asCapturedType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(@NotNull SimpleTypeMarker asDefinitelyNotNullType) {
        Intrinsics.checkParameterIsNotNull(asDefinitelyNotNullType, "$this$asDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.asDefinitelyNotNullType(this, asDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public DynamicTypeMarker asDynamicType(@NotNull FlexibleTypeMarker asDynamicType) {
        Intrinsics.checkParameterIsNotNull(asDynamicType, "$this$asDynamicType");
        return ClassicTypeSystemContext.DefaultImpls.asDynamicType(this, asDynamicType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public FlexibleTypeMarker asFlexibleType(@NotNull KotlinTypeMarker asFlexibleType) {
        Intrinsics.checkParameterIsNotNull(asFlexibleType, "$this$asFlexibleType");
        return ClassicTypeSystemContext.DefaultImpls.asFlexibleType(this, asFlexibleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    @Nullable
    public SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker asSimpleType) {
        Intrinsics.checkParameterIsNotNull(asSimpleType, "$this$asSimpleType");
        return ClassicTypeSystemContext.DefaultImpls.asSimpleType(this, asSimpleType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker asTypeArgument(@NotNull KotlinTypeMarker asTypeArgument) {
        Intrinsics.checkParameterIsNotNull(asTypeArgument, "$this$asTypeArgument");
        return ClassicTypeSystemContext.DefaultImpls.asTypeArgument(this, asTypeArgument);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public SimpleTypeMarker captureFromArguments(@NotNull SimpleTypeMarker type, @NotNull CaptureStatus status) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        return ClassicTypeSystemContext.DefaultImpls.captureFromArguments(this, type, status);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @Nullable
    public List<SimpleTypeMarker> fastCorrespondingSupertypes(@NotNull SimpleTypeMarker fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
        Intrinsics.checkParameterIsNotNull(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        return ClassicTypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, fastCorrespondingSupertypes, constructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker get(@NotNull TypeArgumentListMarker get, int i) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        return ClassicTypeSystemContext.DefaultImpls.get(this, get, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeArgumentMarker getArgument(@NotNull KotlinTypeMarker getArgument, int i) {
        Intrinsics.checkParameterIsNotNull(getArgument, "$this$getArgument");
        return ClassicTypeSystemContext.DefaultImpls.getArgument(this, getArgument, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @Nullable
    public TypeArgumentMarker getArgumentOrNull(@NotNull SimpleTypeMarker getArgumentOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
        return ClassicTypeSystemContext.DefaultImpls.getArgumentOrNull(this, getArgumentOrNull, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public FqNameUnsafe getClassFqNameUnsafe(@NotNull TypeConstructorMarker getClassFqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(getClassFqNameUnsafe, "$this$getClassFqNameUnsafe");
        return ClassicTypeSystemContext.DefaultImpls.getClassFqNameUnsafe(this, getClassFqNameUnsafe);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeParameterMarker getParameter(@NotNull TypeConstructorMarker getParameter, int i) {
        Intrinsics.checkParameterIsNotNull(getParameter, "$this$getParameter");
        return ClassicTypeSystemContext.DefaultImpls.getParameter(this, getParameter, i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public PrimitiveType getPrimitiveArrayType(@NotNull TypeConstructorMarker getPrimitiveArrayType) {
        Intrinsics.checkParameterIsNotNull(getPrimitiveArrayType, "$this$getPrimitiveArrayType");
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveArrayType(this, getPrimitiveArrayType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public PrimitiveType getPrimitiveType(@NotNull TypeConstructorMarker getPrimitiveType) {
        Intrinsics.checkParameterIsNotNull(getPrimitiveType, "$this$getPrimitiveType");
        return ClassicTypeSystemContext.DefaultImpls.getPrimitiveType(this, getPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public KotlinTypeMarker getRepresentativeUpperBound(@NotNull TypeParameterMarker getRepresentativeUpperBound) {
        Intrinsics.checkParameterIsNotNull(getRepresentativeUpperBound, "$this$getRepresentativeUpperBound");
        return ClassicTypeSystemContext.DefaultImpls.getRepresentativeUpperBound(this, getRepresentativeUpperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public KotlinTypeMarker getSubstitutedUnderlyingType(@NotNull KotlinTypeMarker getSubstitutedUnderlyingType) {
        Intrinsics.checkParameterIsNotNull(getSubstitutedUnderlyingType, "$this$getSubstitutedUnderlyingType");
        return ClassicTypeSystemContext.DefaultImpls.getSubstitutedUnderlyingType(this, getSubstitutedUnderlyingType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker getType(@NotNull TypeArgumentMarker getType) {
        Intrinsics.checkParameterIsNotNull(getType, "$this$getType");
        return ClassicTypeSystemContext.DefaultImpls.getType(this, getType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @Nullable
    public TypeParameterMarker getTypeParameterClassifier(@NotNull TypeConstructorMarker getTypeParameterClassifier) {
        Intrinsics.checkParameterIsNotNull(getTypeParameterClassifier, "$this$getTypeParameterClassifier");
        return ClassicTypeSystemContext.DefaultImpls.getTypeParameterClassifier(this, getTypeParameterClassifier);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeVariance getVariance(@NotNull TypeArgumentMarker getVariance) {
        Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean hasAnnotation(@NotNull KotlinTypeMarker hasAnnotation, @NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(hasAnnotation, "$this$hasAnnotation");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return ClassicTypeSystemContext.DefaultImpls.hasAnnotation(this, hasAnnotation, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean hasFlexibleNullability(@NotNull KotlinTypeMarker hasFlexibleNullability) {
        Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
        return ClassicTypeSystemContext.DefaultImpls.hasFlexibleNullability(this, hasFlexibleNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(@NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return ClassicTypeSystemContext.DefaultImpls.identicalArguments(this, a, b);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public KotlinTypeMarker intersectTypes(@NotNull List<? extends KotlinTypeMarker> types) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        return ClassicTypeSystemContext.DefaultImpls.intersectTypes(this, types);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isAllowedTypeVariable(@NotNull KotlinTypeMarker isAllowedTypeVariable) {
        Intrinsics.checkParameterIsNotNull(isAllowedTypeVariable, "$this$isAllowedTypeVariable");
        return (isAllowedTypeVariable instanceof UnwrappedType) && this.allowedTypeVariable && (((UnwrappedType) isAllowedTypeVariable).mo12131getConstructor() instanceof NewTypeVariableConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(@NotNull TypeConstructorMarker isAnyConstructor) {
        Intrinsics.checkParameterIsNotNull(isAnyConstructor, "$this$isAnyConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isAnyConstructor(this, isAnyConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isClassType(@NotNull SimpleTypeMarker isClassType) {
        Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
        return ClassicTypeSystemContext.DefaultImpls.isClassType(this, isClassType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassTypeConstructor(@NotNull TypeConstructorMarker isClassTypeConstructor) {
        Intrinsics.checkParameterIsNotNull(isClassTypeConstructor, "$this$isClassTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isClassTypeConstructor(this, isClassTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCommonFinalClassConstructor(@NotNull TypeConstructorMarker isCommonFinalClassConstructor) {
        Intrinsics.checkParameterIsNotNull(isCommonFinalClassConstructor, "$this$isCommonFinalClassConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isCommonFinalClassConstructor(this, isCommonFinalClassConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isDefinitelyNotNullType(@NotNull KotlinTypeMarker isDefinitelyNotNullType) {
        Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, isDefinitelyNotNullType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(@NotNull TypeConstructorMarker isDenotable) {
        Intrinsics.checkParameterIsNotNull(isDenotable, "$this$isDenotable");
        return ClassicTypeSystemContext.DefaultImpls.isDenotable(this, isDenotable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isDynamic(@NotNull KotlinTypeMarker isDynamic) {
        Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
        return ClassicTypeSystemContext.DefaultImpls.isDynamic(this, isDynamic);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isEqualTypeConstructors(@NotNull TypeConstructorMarker c1, @NotNull TypeConstructorMarker c2) {
        Intrinsics.checkParameterIsNotNull(c1, "c1");
        Intrinsics.checkParameterIsNotNull(c2, "c2");
        return ClassicTypeSystemContext.DefaultImpls.isEqualTypeConstructors(this, c1, c2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isError(@NotNull KotlinTypeMarker isError) {
        Intrinsics.checkParameterIsNotNull(isError, "$this$isError");
        return ClassicTypeSystemContext.DefaultImpls.isError(this, isError);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isInlineClass(@NotNull TypeConstructorMarker isInlineClass) {
        Intrinsics.checkParameterIsNotNull(isInlineClass, "$this$isInlineClass");
        return ClassicTypeSystemContext.DefaultImpls.isInlineClass(this, isInlineClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isIntegerLiteralType(@NotNull SimpleTypeMarker isIntegerLiteralType) {
        Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralType(this, isIntegerLiteralType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralTypeConstructor(@NotNull TypeConstructorMarker isIntegerLiteralTypeConstructor) {
        Intrinsics.checkParameterIsNotNull(isIntegerLiteralTypeConstructor, "$this$isIntegerLiteralTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralTypeConstructor(this, isIntegerLiteralTypeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntersection(@NotNull TypeConstructorMarker isIntersection) {
        Intrinsics.checkParameterIsNotNull(isIntersection, "$this$isIntersection");
        return ClassicTypeSystemContext.DefaultImpls.isIntersection(this, isIntersection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isMarkedNullable(@NotNull KotlinTypeMarker isMarkedNullable) {
        Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable(this, isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isNothing(@NotNull KotlinTypeMarker isNothing) {
        Intrinsics.checkParameterIsNotNull(isNothing, "$this$isNothing");
        return ClassicTypeSystemContext.DefaultImpls.isNothing(this, isNothing);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothingConstructor(@NotNull TypeConstructorMarker isNothingConstructor) {
        Intrinsics.checkParameterIsNotNull(isNothingConstructor, "$this$isNothingConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isNothingConstructor(this, isNothingConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNullableType(@NotNull KotlinTypeMarker isNullableType) {
        Intrinsics.checkParameterIsNotNull(isNullableType, "$this$isNullableType");
        return ClassicTypeSystemContext.DefaultImpls.isNullableType(this, isNullableType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isPrimitiveType(@NotNull SimpleTypeMarker isPrimitiveType) {
        Intrinsics.checkParameterIsNotNull(isPrimitiveType, "$this$isPrimitiveType");
        return ClassicTypeSystemContext.DefaultImpls.isPrimitiveType(this, isPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isSingleClassifierType(@NotNull SimpleTypeMarker isSingleClassifierType) {
        Intrinsics.checkParameterIsNotNull(isSingleClassifierType, "$this$isSingleClassifierType");
        return ClassicTypeSystemContext.DefaultImpls.isSingleClassifierType(this, isSingleClassifierType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStarProjection(@NotNull TypeArgumentMarker isStarProjection) {
        Intrinsics.checkParameterIsNotNull(isStarProjection, "$this$isStarProjection");
        return ClassicTypeSystemContext.DefaultImpls.isStarProjection(this, isStarProjection);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubType(@NotNull SimpleTypeMarker isStubType) {
        Intrinsics.checkParameterIsNotNull(isStubType, "$this$isStubType");
        return ClassicTypeSystemContext.DefaultImpls.isStubType(this, isStubType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    public boolean isStubTypeEqualsToAnything() {
        return this.stubTypeEqualsToAnything;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isUnderKotlinPackage(@NotNull TypeConstructorMarker isUnderKotlinPackage) {
        Intrinsics.checkParameterIsNotNull(isUnderKotlinPackage, "$this$isUnderKotlinPackage");
        return ClassicTypeSystemContext.DefaultImpls.isUnderKotlinPackage(this, isUnderKotlinPackage);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker lowerBound(@NotNull FlexibleTypeMarker lowerBound) {
        Intrinsics.checkParameterIsNotNull(lowerBound, "$this$lowerBound");
        return ClassicTypeSystemContext.DefaultImpls.lowerBound(this, lowerBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker lowerBoundIfFlexible(@NotNull KotlinTypeMarker lowerBoundIfFlexible) {
        Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, lowerBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @Nullable
    public KotlinTypeMarker lowerType(@NotNull CapturedTypeMarker lowerType) {
        Intrinsics.checkParameterIsNotNull(lowerType, "$this$lowerType");
        return ClassicTypeSystemContext.DefaultImpls.lowerType(this, lowerType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    @NotNull
    public KotlinTypeMarker makeNullable(@NotNull KotlinTypeMarker makeNullable) {
        Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
        return ClassicTypeSystemContext.DefaultImpls.makeNullable(this, makeNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int parametersCount(@NotNull TypeConstructorMarker parametersCount) {
        Intrinsics.checkParameterIsNotNull(parametersCount, "$this$parametersCount");
        return ClassicTypeSystemContext.DefaultImpls.parametersCount(this, parametersCount);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public Collection<KotlinTypeMarker> possibleIntegerTypes(@NotNull SimpleTypeMarker possibleIntegerTypes) {
        Intrinsics.checkParameterIsNotNull(possibleIntegerTypes, "$this$possibleIntegerTypes");
        return ClassicTypeSystemContext.DefaultImpls.possibleIntegerTypes(this, possibleIntegerTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public KotlinTypeMarker prepareType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof KotlinType) {
            return NewKotlinTypeChecker.Companion.getDefault().transformToNewType(((KotlinType) type).unwrap());
        }
        throw new IllegalArgumentException(ClassicTypeCheckerContextKt.access$errorMessage(type).toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    public KotlinTypeMarker refineType(@NotNull KotlinTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof KotlinType) {
            return this.kotlinTypeRefiner.refineType((KotlinType) type);
        }
        throw new IllegalArgumentException(ClassicTypeCheckerContextKt.access$errorMessage(type).toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(@NotNull TypeArgumentListMarker size) {
        Intrinsics.checkParameterIsNotNull(size, "$this$size");
        return ClassicTypeSystemContext.DefaultImpls.size(this, size);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public Collection<KotlinTypeMarker> supertypes(@NotNull TypeConstructorMarker supertypes) {
        Intrinsics.checkParameterIsNotNull(supertypes, "$this$supertypes");
        return ClassicTypeSystemContext.DefaultImpls.supertypes(this, supertypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull KotlinTypeMarker typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor(this, typeConstructor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker upperBound(@NotNull FlexibleTypeMarker upperBound) {
        Intrinsics.checkParameterIsNotNull(upperBound, "$this$upperBound");
        return ClassicTypeSystemContext.DefaultImpls.upperBound(this, upperBound);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext, kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker upperBoundIfFlexible(@NotNull KotlinTypeMarker upperBoundIfFlexible) {
        Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, upperBoundIfFlexible);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public SimpleTypeMarker withNullability(@NotNull SimpleTypeMarker withNullability, boolean z) {
        Intrinsics.checkParameterIsNotNull(withNullability, "$this$withNullability");
        return ClassicTypeSystemContext.DefaultImpls.withNullability(this, withNullability, z);
    }

    public ClassicTypeCheckerContext(boolean z, boolean z2, boolean z3, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        this.errorTypeEqualsToAnything = z;
        this.stubTypeEqualsToAnything = z2;
        this.allowedTypeVariable = z3;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    @NotNull
    public TypeVariance getVariance(@NotNull TypeParameterMarker getVariance) {
        Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance(this, getVariance);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(@NotNull SimpleTypeMarker isMarkedNullable) {
        Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable((ClassicTypeSystemContext) this, isMarkedNullable);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext
    @NotNull
    /* renamed from: substitutionSupertypePolicy */
    public AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform mo12129substitutionSupertypePolicy(@NotNull SimpleTypeMarker type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return Companion.classicSubstitutionSupertypePolicy(this, type);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, typeConstructor);
    }

    public boolean areEqualTypeConstructors(@NotNull TypeConstructor a, @NotNull TypeConstructor b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return a instanceof IntegerLiteralTypeConstructor ? ((IntegerLiteralTypeConstructor) a).checkConstructor(b) : b instanceof IntegerLiteralTypeConstructor ? ((IntegerLiteralTypeConstructor) b).checkConstructor(a) : Intrinsics.areEqual(a, b);
    }
}
