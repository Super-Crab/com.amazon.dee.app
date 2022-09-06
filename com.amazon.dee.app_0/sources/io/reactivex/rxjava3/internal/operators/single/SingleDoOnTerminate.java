package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
/* loaded from: classes3.dex */
public final class SingleDoOnTerminate<T> extends Single<T> {
    final Action onTerminate;
    final SingleSource<T> source;

    /* loaded from: classes3.dex */
    final class DoOnTerminate implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;

        DoOnTerminate(SingleObserver<? super T> observer) {
            this.downstream = observer;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable e) {
            try {
                SingleDoOnTerminate.this.onTerminate.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                e = new CompositeException(e, th);
            }
            this.downstream.onError(e);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable d) {
            this.downstream.onSubscribe(d);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T value) {
            try {
                SingleDoOnTerminate.this.onTerminate.run();
                this.downstream.onSuccess(value);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }

    public SingleDoOnTerminate(SingleSource<T> source, Action onTerminate) {
        this.source = source;
        this.onTerminate = onTerminate;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(final SingleObserver<? super T> observer) {
        this.source.subscribe(new DoOnTerminate(observer));
    }
}
