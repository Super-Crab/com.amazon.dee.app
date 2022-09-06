package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
/* loaded from: classes3.dex */
public final class SingleToObservable<T> extends Observable<T> {
    final SingleSource<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SingleToObservableObserver<T> extends DeferredScalarDisposable<T> implements SingleObserver<T> {
        private static final long serialVersionUID = 3786543492451018833L;
        Disposable upstream;

        SingleToObservableObserver(Observer<? super T> downstream) {
            super(downstream);
        }

        @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable e) {
            error(e);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T value) {
            complete(value);
        }
    }

    public SingleToObservable(SingleSource<? extends T> source) {
        this.source = source;
    }

    public static <T> SingleObserver<T> create(Observer<? super T> downstream) {
        return new SingleToObservableObserver(downstream);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(final Observer<? super T> observer) {
        this.source.subscribe(create(observer));
    }
}
