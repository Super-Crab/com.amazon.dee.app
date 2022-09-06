package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes3.dex */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(@NonNull Throwable t);

    void onSuccess(@NonNull T t);

    void setCancellable(@Nullable Cancellable c);

    void setDisposable(@Nullable Disposable d);

    boolean tryOnError(@NonNull Throwable t);
}
