package io.reactivex.rxjava3.internal.fuseable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
/* loaded from: classes3.dex */
public interface SimpleQueue<T> {
    void clear();

    boolean isEmpty();

    boolean offer(@NonNull T value);

    boolean offer(@NonNull T v1, @NonNull T v2);

    @Nullable
    /* renamed from: poll */
    T mo10355poll() throws Throwable;
}
