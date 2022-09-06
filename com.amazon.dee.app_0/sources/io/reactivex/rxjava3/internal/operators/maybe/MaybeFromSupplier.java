package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes3.dex */
public final class MaybeFromSupplier<T> extends Maybe<T> implements Supplier<T> {
    final Supplier<? extends T> supplier;

    public MaybeFromSupplier(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override // io.reactivex.rxjava3.functions.Supplier
    /* renamed from: get */
    public T mo10365get() throws Throwable {
        return this.supplier.mo10365get();
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    protected void subscribeActual(MaybeObserver<? super T> observer) {
        Disposable empty = Disposable.empty();
        observer.onSubscribe(empty);
        if (!empty.isDisposed()) {
            try {
                Object obj = (T) this.supplier.mo10365get();
                if (empty.isDisposed()) {
                    return;
                }
                if (obj == null) {
                    observer.onComplete();
                } else {
                    observer.onSuccess(obj);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (!empty.isDisposed()) {
                    observer.onError(th);
                } else {
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
