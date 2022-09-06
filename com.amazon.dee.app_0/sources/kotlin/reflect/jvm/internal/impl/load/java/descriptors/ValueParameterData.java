package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: util.kt */
/* loaded from: classes3.dex */
public final class ValueParameterData {
    private final boolean hasDefaultValue;
    @NotNull
    private final KotlinType type;

    public ValueParameterData(@NotNull KotlinType type, boolean z) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.hasDefaultValue = z;
    }

    public final boolean getHasDefaultValue() {
        return this.hasDefaultValue;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }
}
