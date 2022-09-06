package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: utils.kt */
/* loaded from: classes3.dex */
public final class Constant extends JavaDefaultValue {
    @NotNull
    private final Object value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Constant(@NotNull Object value) {
        super(null);
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }
}
