package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeWithEnhancement.kt */
/* loaded from: classes4.dex */
public final class TypeWithEnhancementKt {
    @Nullable
    public static final KotlinType getEnhancement(@NotNull KotlinType getEnhancement) {
        Intrinsics.checkParameterIsNotNull(getEnhancement, "$this$getEnhancement");
        if (getEnhancement instanceof TypeWithEnhancement) {
            return ((TypeWithEnhancement) getEnhancement).getEnhancement();
        }
        return null;
    }

    @NotNull
    public static final UnwrappedType inheritEnhancement(@NotNull UnwrappedType inheritEnhancement, @NotNull KotlinType origin) {
        Intrinsics.checkParameterIsNotNull(inheritEnhancement, "$this$inheritEnhancement");
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        return wrapEnhancement(inheritEnhancement, getEnhancement(origin));
    }

    @NotNull
    public static final KotlinType unwrapEnhancement(@NotNull KotlinType unwrapEnhancement) {
        Intrinsics.checkParameterIsNotNull(unwrapEnhancement, "$this$unwrapEnhancement");
        KotlinType enhancement = getEnhancement(unwrapEnhancement);
        return enhancement != null ? enhancement : unwrapEnhancement;
    }

    @NotNull
    public static final UnwrappedType wrapEnhancement(@NotNull UnwrappedType wrapEnhancement, @Nullable KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(wrapEnhancement, "$this$wrapEnhancement");
        if (kotlinType == null) {
            return wrapEnhancement;
        }
        if (wrapEnhancement instanceof SimpleType) {
            return new SimpleTypeWithEnhancement((SimpleType) wrapEnhancement, kotlinType);
        }
        if (!(wrapEnhancement instanceof FlexibleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return new FlexibleTypeWithEnhancement((FlexibleType) wrapEnhancement, kotlinType);
    }
}
