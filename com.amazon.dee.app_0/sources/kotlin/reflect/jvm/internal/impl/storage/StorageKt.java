package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: storage.kt */
/* loaded from: classes4.dex */
public final class StorageKt {
    @NotNull
    public static final <T> T getValue(@NotNull NotNullLazyValue<? extends T> getValue, @Nullable Object obj, @NotNull KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(getValue, "$this$getValue");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return getValue.mo12560invoke();
    }

    @Nullable
    public static final <T> T getValue(@NotNull NullableLazyValue<? extends T> getValue, @Nullable Object obj, @NotNull KProperty<?> p) {
        Intrinsics.checkParameterIsNotNull(getValue, "$this$getValue");
        Intrinsics.checkParameterIsNotNull(p, "p");
        return getValue.mo12560invoke();
    }
}
