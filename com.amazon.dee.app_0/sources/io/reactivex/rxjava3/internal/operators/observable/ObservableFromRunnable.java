package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.fuseable.CancellableQueueFuseable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes3.dex */
public final class ObservableFromRunnable<T> extends Observable<T> implements Supplier<T> {
    final Runnable run;

    public ObservableFromRunnable(Runnable run) {
        this.run = run;
    }

    @Override // io.reactivex.rxjava3.functions.Supplier
    /* renamed from: get */
    public T mo10365get() throws Throwable {
        this.run.run();
        return null;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        CancellableQueueFuseable cancellableQueueFuseable = new CancellableQueueFuseable();
        observer.onSubscribe(cancellableQueueFuseable);
        if (!cancellableQueueFuseable.isDisposed()) {
            try {
                this.run.run();
                if (cancellableQueueFuseable.isDisposed()) {
                    return;
                }
                observer.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (!cancellableQueueFuseable.isDisposed()) {
                    observer.onError(th);
                } else {
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
