package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes3.dex */
public final class SingleDoOnLifecycle<T> extends Single<T> {
    final Action onDispose;
    final Consumer<? super Disposable> onSubscribe;
    final Single<T> source;

    /* loaded from: classes3.dex */
    static final class SingleLifecycleObserver<T> implements SingleObserver<T>, Disposable {
        final SingleObserver<? super T> downstream;
        final Action onDispose;
        final Consumer<? super Disposable> onSubscribe;
        Disposable upstream;

        SingleLifecycleObserver(SingleObserver<? super T> downstream, Consumer<? super Disposable> onSubscribe, Action onDispose) {
            this.downstream = downstream;
            this.onSubscribe = onSubscribe;
            this.onDispose = onDispose;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            try {
                this.onDispose.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(@NonNull Throwable e) {
            Disposable disposable = this.upstream;
            DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
            if (disposable != disposableHelper) {
                this.upstream = disposableHelper;
                this.downstream.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(@NonNull Disposable d) {
            try {
                this.onSubscribe.accept(d);
                if (!DisposableHelper.validate(this.upstream, d)) {
                    return;
                }
                this.upstream = d;
                this.downstream.onSubscribe(this);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                d.dispose();
                this.upstream = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.downstream);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(@NonNull T t) {
            Disposable disposable = this.upstream;
            DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
            if (disposable != disposableHelper) {
                this.upstream = disposableHelper;
                this.downstream.onSuccess(t);
            }
        }
    }

    public SingleDoOnLifecycle(Single<T> upstream, Consumer<? super Disposable> onSubscribe, Action onDispose) {
        this.source = upstream;
        this.onSubscribe = onSubscribe;
        this.onDispose = onDispose;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver<? super T> observer) {
        this.source.subscribe(new SingleLifecycleObserver(observer, this.onSubscribe, this.onDispose));
    }
}
