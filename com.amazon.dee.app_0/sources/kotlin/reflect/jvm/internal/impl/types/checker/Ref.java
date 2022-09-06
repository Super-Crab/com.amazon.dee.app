package kotlin.reflect.jvm.internal.impl.types.checker;

import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinTypeRefiner.kt */
/* loaded from: classes4.dex */
public final class Ref<T> {
    @Nullable
    private T value;

    public Ref(@Nullable T t) {
        this.value = t;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }
}
