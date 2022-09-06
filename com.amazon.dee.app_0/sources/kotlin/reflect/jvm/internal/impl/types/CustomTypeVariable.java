package kotlin.reflect.jvm.internal.impl.types;

import org.jetbrains.annotations.NotNull;
/* compiled from: TypeCapabilities.kt */
/* loaded from: classes4.dex */
public interface CustomTypeVariable {
    boolean isTypeVariable();

    @NotNull
    KotlinType substitutionResult(@NotNull KotlinType kotlinType);
}
