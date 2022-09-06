package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSystemContext.kt */
/* loaded from: classes4.dex */
public interface TypeSystemInferenceExtensionContext extends TypeSystemCommonSuperTypesContext, TypeSystemContext {

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Nullable
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull SimpleTypeMarker fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
            Intrinsics.checkParameterIsNotNull(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            return TypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(typeSystemInferenceExtensionContext, fastCorrespondingSupertypes, constructor);
        }

        @NotNull
        public static TypeArgumentMarker get(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull TypeArgumentListMarker get, int i) {
            Intrinsics.checkParameterIsNotNull(get, "$this$get");
            return TypeSystemContext.DefaultImpls.get(typeSystemInferenceExtensionContext, get, i);
        }

        @Nullable
        public static TypeArgumentMarker getArgumentOrNull(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
            return TypeSystemContext.DefaultImpls.getArgumentOrNull(typeSystemInferenceExtensionContext, getArgumentOrNull, i);
        }

        public static boolean hasFlexibleNullability(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return TypeSystemContext.DefaultImpls.hasFlexibleNullability(typeSystemInferenceExtensionContext, hasFlexibleNullability);
        }

        public static boolean isClassType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull SimpleTypeMarker isClassType) {
            Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
            return TypeSystemContext.DefaultImpls.isClassType(typeSystemInferenceExtensionContext, isClassType);
        }

        public static boolean isDefinitelyNotNullType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            return TypeSystemContext.DefaultImpls.isDefinitelyNotNullType(typeSystemInferenceExtensionContext, isDefinitelyNotNullType);
        }

        public static boolean isDynamic(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker isDynamic) {
            Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
            return TypeSystemContext.DefaultImpls.isDynamic(typeSystemInferenceExtensionContext, isDynamic);
        }

        public static boolean isIntegerLiteralType(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return TypeSystemContext.DefaultImpls.isIntegerLiteralType(typeSystemInferenceExtensionContext, isIntegerLiteralType);
        }

        public static boolean isNothing(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker isNothing) {
            Intrinsics.checkParameterIsNotNull(isNothing, "$this$isNothing");
            return TypeSystemContext.DefaultImpls.isNothing(typeSystemInferenceExtensionContext, isNothing);
        }

        @NotNull
        public static SimpleTypeMarker lowerBoundIfFlexible(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker lowerBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            return TypeSystemContext.DefaultImpls.lowerBoundIfFlexible(typeSystemInferenceExtensionContext, lowerBoundIfFlexible);
        }

        public static int size(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull TypeArgumentListMarker size) {
            Intrinsics.checkParameterIsNotNull(size, "$this$size");
            return TypeSystemContext.DefaultImpls.size(typeSystemInferenceExtensionContext, size);
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            return TypeSystemContext.DefaultImpls.typeConstructor(typeSystemInferenceExtensionContext, typeConstructor);
        }

        @NotNull
        public static SimpleTypeMarker upperBoundIfFlexible(TypeSystemInferenceExtensionContext typeSystemInferenceExtensionContext, @NotNull KotlinTypeMarker upperBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            return TypeSystemContext.DefaultImpls.upperBoundIfFlexible(typeSystemInferenceExtensionContext, upperBoundIfFlexible);
        }
    }
}
