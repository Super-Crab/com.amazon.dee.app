package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;
/* compiled from: TypeCapabilities.kt */
/* loaded from: classes4.dex */
public interface SubtypingRepresentatives {
    @NotNull
    KotlinType getSubTypeRepresentative();

    @NotNull
    KotlinType getSuperTypeRepresentative();

    boolean sameTypeConstructor(@NotNull KotlinType kotlinType);
}
