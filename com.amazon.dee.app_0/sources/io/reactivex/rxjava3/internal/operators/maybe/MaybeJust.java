package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.fuseable.ScalarSupplier;
/* loaded from: classes3.dex */
public final class MaybeJust<T> extends Maybe<T> implements ScalarSupplier<T> {
    final T value;

    public MaybeJust(T value) {
        this.value = value;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.ScalarSupplier, io.reactivex.rxjava3.functions.Supplier
    /* renamed from: get */
    public T mo10365get() {
        return this.value;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    protected void subscribeActual(MaybeObserver<? super T> observer) {
        observer.onSubscribe(Disposable.disposed());
        observer.onSuccess((T) this.value);
    }
}
