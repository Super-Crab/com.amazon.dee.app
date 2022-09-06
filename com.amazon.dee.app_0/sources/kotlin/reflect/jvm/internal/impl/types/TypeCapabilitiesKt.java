package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeCapabilities.kt */
/* loaded from: classes4.dex */
public final class TypeCapabilitiesKt {
    @Nullable
    public static final CustomTypeVariable getCustomTypeVariable(@NotNull KotlinType getCustomTypeVariable) {
        Intrinsics.checkParameterIsNotNull(getCustomTypeVariable, "$this$getCustomTypeVariable");
        UnwrappedType unwrap = getCustomTypeVariable.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable == null || !customTypeVariable.isTypeVariable()) {
            return null;
        }
        return customTypeVariable;
    }

    @NotNull
    public static final KotlinType getSubtypeRepresentative(@NotNull KotlinType getSubtypeRepresentative) {
        KotlinType subTypeRepresentative;
        Intrinsics.checkParameterIsNotNull(getSubtypeRepresentative, "$this$getSubtypeRepresentative");
        UnwrappedType unwrap = getSubtypeRepresentative.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        return (subtypingRepresentatives == null || (subTypeRepresentative = subtypingRepresentatives.getSubTypeRepresentative()) == null) ? getSubtypeRepresentative : subTypeRepresentative;
    }

    @NotNull
    public static final KotlinType getSupertypeRepresentative(@NotNull KotlinType getSupertypeRepresentative) {
        KotlinType superTypeRepresentative;
        Intrinsics.checkParameterIsNotNull(getSupertypeRepresentative, "$this$getSupertypeRepresentative");
        UnwrappedType unwrap = getSupertypeRepresentative.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        return (subtypingRepresentatives == null || (superTypeRepresentative = subtypingRepresentatives.getSuperTypeRepresentative()) == null) ? getSupertypeRepresentative : superTypeRepresentative;
    }

    public static final boolean isCustomTypeVariable(@NotNull KotlinType isCustomTypeVariable) {
        Intrinsics.checkParameterIsNotNull(isCustomTypeVariable, "$this$isCustomTypeVariable");
        UnwrappedType unwrap = isCustomTypeVariable.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable != null) {
            return customTypeVariable.isTypeVariable();
        }
        return false;
    }

    public static final boolean sameTypeConstructors(@NotNull KotlinType first, @NotNull KotlinType second) {
        Intrinsics.checkParameterIsNotNull(first, "first");
        Intrinsics.checkParameterIsNotNull(second, "second");
        UnwrappedType unwrap = first.unwrap();
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives = (SubtypingRepresentatives) unwrap;
        if (!(subtypingRepresentatives != null ? subtypingRepresentatives.sameTypeConstructor(second) : false)) {
            UnwrappedType unwrap2 = second.unwrap();
            if (!(unwrap2 instanceof SubtypingRepresentatives)) {
                unwrap2 = null;
            }
            SubtypingRepresentatives subtypingRepresentatives2 = (SubtypingRepresentatives) unwrap2;
            if (!(subtypingRepresentatives2 != null ? subtypingRepresentatives2.sameTypeConstructor(first) : false)) {
                return false;
            }
        }
        return true;
    }
}
