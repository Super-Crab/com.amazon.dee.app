package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: flexibleTypes.kt */
/* loaded from: classes4.dex */
public final class FlexibleTypesKt {
    @NotNull
    public static final FlexibleType asFlexibleType(@NotNull KotlinType asFlexibleType) {
        Intrinsics.checkParameterIsNotNull(asFlexibleType, "$this$asFlexibleType");
        UnwrappedType unwrap = asFlexibleType.unwrap();
        if (unwrap != null) {
            return (FlexibleType) unwrap;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
    }

    public static final boolean isFlexible(@NotNull KotlinType isFlexible) {
        Intrinsics.checkParameterIsNotNull(isFlexible, "$this$isFlexible");
        return isFlexible.unwrap() instanceof FlexibleType;
    }

    @NotNull
    public static final SimpleType lowerIfFlexible(@NotNull KotlinType lowerIfFlexible) {
        Intrinsics.checkParameterIsNotNull(lowerIfFlexible, "$this$lowerIfFlexible");
        UnwrappedType unwrap = lowerIfFlexible.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getLowerBound();
        }
        if (!(unwrap instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return (SimpleType) unwrap;
    }

    @NotNull
    public static final SimpleType upperIfFlexible(@NotNull KotlinType upperIfFlexible) {
        Intrinsics.checkParameterIsNotNull(upperIfFlexible, "$this$upperIfFlexible");
        UnwrappedType unwrap = upperIfFlexible.unwrap();
        if (unwrap instanceof FlexibleType) {
            return ((FlexibleType) unwrap).getUpperBound();
        }
        if (!(unwrap instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return (SimpleType) unwrap;
    }
}
