package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes3.dex */
public interface ObservableEmitter<T> extends Emitter<T> {
    boolean isDisposed();

    @NonNull
    ObservableEmitter<T> serialize();

    void setCancellable(@Nullable Cancellable c);

    void setDisposable(@Nullable Disposable d);

    boolean tryOnError(@NonNull Throwable t);
}
