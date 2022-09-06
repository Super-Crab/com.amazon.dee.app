package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext;
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
/* compiled from: NewKotlinTypeChecker.kt */
/* loaded from: classes4.dex */
public final class SimpleClassicTypeSystemContext implements ClassicTypeSystemContext {
    public static final SimpleClassicTypeSystemContext INSTANCE = new SimpleClassicTypeSystemContext();

    private SimpleClassicTypeSystemContext() {
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

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(@NotNull TypeConstructorMarker isAnyConstructor) {
        Intrinsics.checkParameterIsNotNull(isAnyConstructor, "$this$isAnyConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isAnyConstructor(this, isAnyConstructor);
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(@NotNull TypeConstructorMarker isDenotable) {
        Intrinsics.checkParameterIsNotNull(isDenotable, "$this$isDenotable");
        return ClassicTypeSystemContext.DefaultImpls.isDenotable(this, isDenotable);
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

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isInlineClass(@NotNull TypeConstructorMarker isInlineClass) {
        Intrinsics.checkParameterIsNotNull(isInlineClass, "$this$isInlineClass");
        return ClassicTypeSystemContext.DefaultImpls.isInlineClass(this, isInlineClass);
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
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

    @NotNull
    public AbstractTypeCheckerContext newBaseTypeCheckerContext(boolean z, boolean z2) {
        return ClassicTypeSystemContext.DefaultImpls.newBaseTypeCheckerContext(this, z, z2);
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
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

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    @NotNull
    public TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, typeConstructor);
    }
}
