package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
/* loaded from: classes3.dex */
public interface SingleObserver<T> {
    void onError(@NonNull Throwable e);

    void onSubscribe(@NonNull Disposable d);

    void onSuccess(@NonNull T t);
}
