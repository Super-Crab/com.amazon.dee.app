package io.reactivex.rxjava3.internal.operators.observable;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.observers.BasicQueueDisposable;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ObservableFromArray<T> extends Observable<T> {
    final T[] array;

    /* loaded from: classes3.dex */
    static final class FromArrayDisposable<T> extends BasicQueueDisposable<T> {
        final T[] array;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        boolean fusionMode;
        int index;

        FromArrayDisposable(Observer<? super T> actual, T[] array) {
            this.downstream = actual;
            this.array = array;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.index = this.array.length;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public T mo10355poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i != tArr.length) {
                this.index = i + 1;
                return (T) Objects.requireNonNull(tArr[i], "The array element is null");
            }
            return null;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 1) != 0) {
                this.fusionMode = true;
                return 1;
            }
            return 0;
        }

        void run() {
            T[] tArr = this.array;
            int length = tArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                T t = tArr[i];
                if (t == null) {
                    this.downstream.onError(new NullPointerException(GeneratedOutlineSupport1.outline52("The element at index ", i, " is null")));
                    return;
                }
                this.downstream.onNext(t);
            }
            if (!isDisposed()) {
                this.downstream.onComplete();
            }
        }
    }

    public ObservableFromArray(T[] array) {
        this.array = array;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        FromArrayDisposable fromArrayDisposable = new FromArrayDisposable(observer, this.array);
        observer.onSubscribe(fromArrayDisposable);
        if (fromArrayDisposable.fusionMode) {
            return;
        }
        fromArrayDisposable.run();
    }
}
