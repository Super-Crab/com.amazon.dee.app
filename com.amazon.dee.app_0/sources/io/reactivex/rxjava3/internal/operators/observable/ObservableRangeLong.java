package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
/* loaded from: classes3.dex */
public final class ObservableRangeLong extends Observable<Long> {
    private final long count;
    private final long start;

    /* loaded from: classes3.dex */
    static final class RangeDisposable extends BasicIntQueueDisposable<Long> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Long> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Long> actual, long start, long end) {
            this.downstream = actual;
            this.index = start;
            this.end = end;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            set(1);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() != 0;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 1) != 0) {
                this.fused = true;
                return 1;
            }
            return 0;
        }

        void run() {
            if (this.fused) {
                return;
            }
            Observer<? super Long> observer = this.downstream;
            long j = this.end;
            for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                observer.onNext(Long.valueOf(j2));
            }
            if (get() != 0) {
                return;
            }
            lazySet(1);
            observer.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public Long mo10355poll() {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Long.valueOf(j);
            }
            lazySet(1);
            return null;
        }
    }

    public ObservableRangeLong(long start, long count) {
        this.start = start;
        this.count = count;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super Long> o) {
        long j = this.start;
        RangeDisposable rangeDisposable = new RangeDisposable(o, j, j + this.count);
        o.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }
}
