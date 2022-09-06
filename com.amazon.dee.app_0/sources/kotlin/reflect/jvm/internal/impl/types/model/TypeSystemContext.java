package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSystemContext.kt */
/* loaded from: classes4.dex */
public interface TypeSystemContext extends TypeSystemOptimizationContext {

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Nullable
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
            Intrinsics.checkParameterIsNotNull(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            return null;
        }

        @NotNull
        public static TypeArgumentMarker get(TypeSystemContext typeSystemContext, @NotNull TypeArgumentListMarker get, int i) {
            Intrinsics.checkParameterIsNotNull(get, "$this$get");
            if (get instanceof SimpleTypeMarker) {
                return typeSystemContext.getArgument((KotlinTypeMarker) get, i);
            }
            if (get instanceof ArgumentList) {
                TypeArgumentMarker typeArgumentMarker = ((ArgumentList) get).get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeArgumentMarker, "get(index)");
                return typeArgumentMarker;
            }
            throw new IllegalStateException(("unknown type argument list type: " + get + ", " + Reflection.getOrCreateKotlinClass(get.getClass())).toString());
        }

        @Nullable
        public static TypeArgumentMarker getArgumentOrNull(TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
            int argumentsCount = typeSystemContext.argumentsCount(getArgumentOrNull);
            if (i >= 0 && argumentsCount > i) {
                return typeSystemContext.getArgument(getArgumentOrNull, i);
            }
            return null;
        }

        public static boolean hasFlexibleNullability(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(hasFlexibleNullability)) != typeSystemContext.isMarkedNullable(typeSystemContext.upperBoundIfFlexible(hasFlexibleNullability));
        }

        public static boolean identicalArguments(TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
            Intrinsics.checkParameterIsNotNull(a, "a");
            Intrinsics.checkParameterIsNotNull(b, "b");
            return TypeSystemOptimizationContext.DefaultImpls.identicalArguments(typeSystemContext, a, b);
        }

        public static boolean isClassType(TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker isClassType) {
            Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
            return typeSystemContext.isClassTypeConstructor(typeSystemContext.typeConstructor(isClassType));
        }

        public static boolean isDefinitelyNotNullType(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(isDefinitelyNotNullType);
            return (asSimpleType != null ? typeSystemContext.asDefinitelyNotNullType(asSimpleType) : null) != null;
        }

        public static boolean isDynamic(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker isDynamic) {
            Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(isDynamic);
            return (asFlexibleType != null ? typeSystemContext.asDynamicType(asFlexibleType) : null) != null;
        }

        public static boolean isIntegerLiteralType(TypeSystemContext typeSystemContext, @NotNull SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return typeSystemContext.isIntegerLiteralTypeConstructor(typeSystemContext.typeConstructor(isIntegerLiteralType));
        }

        public static boolean isNothing(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker isNothing) {
            Intrinsics.checkParameterIsNotNull(isNothing, "$this$isNothing");
            return typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(isNothing)) && !typeSystemContext.isNullableType(isNothing);
        }

        @NotNull
        public static SimpleTypeMarker lowerBoundIfFlexible(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker lowerBoundIfFlexible) {
            SimpleTypeMarker asSimpleType;
            Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(lowerBoundIfFlexible);
            if ((asFlexibleType == null || (asSimpleType = typeSystemContext.lowerBound(asFlexibleType)) == null) && (asSimpleType = typeSystemContext.asSimpleType(lowerBoundIfFlexible)) == null) {
                Intrinsics.throwNpe();
            }
            return asSimpleType;
        }

        public static int size(TypeSystemContext typeSystemContext, @NotNull TypeArgumentListMarker size) {
            Intrinsics.checkParameterIsNotNull(size, "$this$size");
            if (size instanceof SimpleTypeMarker) {
                return typeSystemContext.argumentsCount((KotlinTypeMarker) size);
            }
            if (size instanceof ArgumentList) {
                return ((ArgumentList) size).size();
            }
            throw new IllegalStateException(("unknown type argument list type: " + size + ", " + Reflection.getOrCreateKotlinClass(size.getClass())).toString());
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            SimpleTypeMarker asSimpleType = typeSystemContext.asSimpleType(typeConstructor);
            if (asSimpleType == null) {
                asSimpleType = typeSystemContext.lowerBoundIfFlexible(typeConstructor);
            }
            return typeSystemContext.typeConstructor(asSimpleType);
        }

        @NotNull
        public static SimpleTypeMarker upperBoundIfFlexible(TypeSystemContext typeSystemContext, @NotNull KotlinTypeMarker upperBoundIfFlexible) {
            SimpleTypeMarker asSimpleType;
            Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            FlexibleTypeMarker asFlexibleType = typeSystemContext.asFlexibleType(upperBoundIfFlexible);
            if ((asFlexibleType == null || (asSimpleType = typeSystemContext.upperBound(asFlexibleType)) == null) && (asSimpleType = typeSystemContext.asSimpleType(upperBoundIfFlexible)) == null) {
                Intrinsics.throwNpe();
            }
            return asSimpleType;
        }
    }

    int argumentsCount(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    TypeArgumentListMarker asArgumentList(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Nullable
    CapturedTypeMarker asCapturedType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Nullable
    DefinitelyNotNullTypeMarker asDefinitelyNotNullType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @Nullable
    DynamicTypeMarker asDynamicType(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @Nullable
    FlexibleTypeMarker asFlexibleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    TypeArgumentMarker asTypeArgument(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    SimpleTypeMarker captureFromArguments(@NotNull SimpleTypeMarker simpleTypeMarker, @NotNull CaptureStatus captureStatus);

    @NotNull
    TypeArgumentMarker get(@NotNull TypeArgumentListMarker typeArgumentListMarker, int i);

    @NotNull
    TypeArgumentMarker getArgument(@NotNull KotlinTypeMarker kotlinTypeMarker, int i);

    @NotNull
    TypeParameterMarker getParameter(@NotNull TypeConstructorMarker typeConstructorMarker, int i);

    @NotNull
    KotlinTypeMarker getType(@NotNull TypeArgumentMarker typeArgumentMarker);

    @NotNull
    TypeVariance getVariance(@NotNull TypeArgumentMarker typeArgumentMarker);

    @NotNull
    TypeVariance getVariance(@NotNull TypeParameterMarker typeParameterMarker);

    @NotNull
    KotlinTypeMarker intersectTypes(@NotNull List<? extends KotlinTypeMarker> list);

    boolean isAnyConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isClassTypeConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isCommonFinalClassConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isDenotable(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isEqualTypeConstructors(@NotNull TypeConstructorMarker typeConstructorMarker, @NotNull TypeConstructorMarker typeConstructorMarker2);

    boolean isError(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isIntegerLiteralTypeConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isIntersection(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isMarkedNullable(@NotNull SimpleTypeMarker simpleTypeMarker);

    boolean isNothingConstructor(@NotNull TypeConstructorMarker typeConstructorMarker);

    boolean isNullableType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    boolean isPrimitiveType(@NotNull SimpleTypeMarker simpleTypeMarker);

    boolean isSingleClassifierType(@NotNull SimpleTypeMarker simpleTypeMarker);

    boolean isStarProjection(@NotNull TypeArgumentMarker typeArgumentMarker);

    boolean isStubType(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    SimpleTypeMarker lowerBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @NotNull
    SimpleTypeMarker lowerBoundIfFlexible(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Nullable
    KotlinTypeMarker lowerType(@NotNull CapturedTypeMarker capturedTypeMarker);

    int parametersCount(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    Collection<KotlinTypeMarker> possibleIntegerTypes(@NotNull SimpleTypeMarker simpleTypeMarker);

    int size(@NotNull TypeArgumentListMarker typeArgumentListMarker);

    @NotNull
    Collection<KotlinTypeMarker> supertypes(@NotNull TypeConstructorMarker typeConstructorMarker);

    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker simpleTypeMarker);

    @NotNull
    SimpleTypeMarker upperBound(@NotNull FlexibleTypeMarker flexibleTypeMarker);

    @NotNull
    SimpleTypeMarker upperBoundIfFlexible(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @NotNull
    SimpleTypeMarker withNullability(@NotNull SimpleTypeMarker simpleTypeMarker, boolean z);
}
