package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.internal.disposables.CancellableDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableCreate<T> extends Flowable<T> {
    final BackpressureStrategy backpressure;
    final FlowableOnSubscribe<T> source;

    /* renamed from: io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$reactivex$rxjava3$core$BackpressureStrategy = new int[BackpressureStrategy.values().length];

        static {
            try {
                $SwitchMap$io$reactivex$rxjava3$core$BackpressureStrategy[BackpressureStrategy.MISSING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$core$BackpressureStrategy[BackpressureStrategy.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$core$BackpressureStrategy[BackpressureStrategy.DROP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$core$BackpressureStrategy[BackpressureStrategy.LATEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class BaseEmitter<T> extends AtomicLong implements FlowableEmitter<T>, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> downstream;
        final SequentialDisposable serial = new SequentialDisposable();

        BaseEmitter(Subscriber<? super T> downstream) {
            this.downstream = downstream;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.serial.dispose();
            onUnsubscribed();
        }

        protected void completeDownstream() {
            if (isCancelled()) {
                return;
            }
            try {
                this.downstream.onComplete();
            } finally {
                this.serial.dispose();
            }
        }

        protected boolean errorDownstream(Throwable e) {
            if (isCancelled()) {
                return false;
            }
            try {
                this.downstream.onError(e);
                this.serial.dispose();
                return true;
            } catch (Throwable th) {
                this.serial.dispose();
                throw th;
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final boolean isCancelled() {
            return this.serial.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            completeDownstream();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public final void onError(Throwable e) {
            if (e == null) {
                e = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
            }
            if (!signalError(e)) {
                RxJavaPlugins.onError(e);
            }
        }

        void onRequested() {
        }

        void onUnsubscribed() {
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this, n);
                onRequested();
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final long requested() {
            return get();
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final FlowableEmitter<T> serialize() {
            return new SerializedEmitter(this);
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final void setCancellable(Cancellable c) {
            setDisposable(new CancellableDisposable(c));
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final void setDisposable(Disposable d) {
            this.serial.update(d);
        }

        public boolean signalError(Throwable e) {
            return errorDownstream(e);
        }

        @Override // java.util.concurrent.atomic.AtomicLong
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public final boolean tryOnError(Throwable e) {
            if (e == null) {
                e = ExceptionHelper.createNullPointerException("tryOnError called with a null Throwable.");
            }
            return signalError(e);
        }
    }

    /* loaded from: classes3.dex */
    static final class BufferAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicInteger wip;

        BufferAsyncEmitter(Subscriber<? super T> actual, int capacityHint) {
            super(actual);
            this.queue = new SpscLinkedArrayQueue<>(capacityHint);
            this.wip = new AtomicInteger();
        }

        void drain() {
            int i;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.downstream;
            SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
            int i2 = 1;
            do {
                long j = get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (isCancelled()) {
                        spscLinkedArrayQueue.clear();
                        return;
                    } else {
                        boolean z = this.done;
                        Object obj = (T) spscLinkedArrayQueue.mo10355poll();
                        boolean z2 = obj == null;
                        if (z && z2) {
                            Throwable th = this.error;
                            if (th != null) {
                                errorDownstream(th);
                                return;
                            } else {
                                completeDownstream();
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            subscriber.onNext(obj);
                            j2++;
                        }
                    }
                }
                if (i == 0) {
                    if (isCancelled()) {
                        spscLinkedArrayQueue.clear();
                        return;
                    }
                    boolean z3 = this.done;
                    boolean isEmpty = spscLinkedArrayQueue.isEmpty();
                    if (z3 && isEmpty) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            errorDownstream(th2);
                            return;
                        } else {
                            completeDownstream();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    BackpressureHelper.produced(this, j2);
                }
                i2 = this.wip.addAndGet(-i2);
            } while (i2 != 0);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter, io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (this.done || isCancelled()) {
                return;
            }
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
                return;
            }
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        public boolean signalError(Throwable e) {
            if (this.done || isCancelled()) {
                return false;
            }
            this.error = e;
            this.done = true;
            drain();
            return true;
        }
    }

    /* loaded from: classes3.dex */
    static final class DropAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        DropAsyncEmitter(Subscriber<? super T> downstream) {
            super(downstream);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.NoOverflowBaseAsyncEmitter
        void onOverflow() {
        }
    }

    /* loaded from: classes3.dex */
    static final class ErrorAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;

        ErrorAsyncEmitter(Subscriber<? super T> downstream) {
            super(downstream);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.NoOverflowBaseAsyncEmitter
        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    /* loaded from: classes3.dex */
    static final class LatestAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<T> queue;
        final AtomicInteger wip;

        LatestAsyncEmitter(Subscriber<? super T> downstream) {
            super(downstream);
            this.queue = new AtomicReference<>();
            this.wip = new AtomicInteger();
        }

        void drain() {
            int i;
            boolean z;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.downstream;
            AtomicReference<T> atomicReference = this.queue;
            int i2 = 1;
            do {
                long j = get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    z = false;
                    if (i == 0) {
                        break;
                    } else if (isCancelled()) {
                        atomicReference.lazySet(null);
                        return;
                    } else {
                        boolean z2 = this.done;
                        Object obj = (T) atomicReference.getAndSet(null);
                        boolean z3 = obj == null;
                        if (z2 && z3) {
                            Throwable th = this.error;
                            if (th != null) {
                                errorDownstream(th);
                                return;
                            } else {
                                completeDownstream();
                                return;
                            }
                        } else if (z3) {
                            break;
                        } else {
                            subscriber.onNext(obj);
                            j2++;
                        }
                    }
                }
                if (i == 0) {
                    if (isCancelled()) {
                        atomicReference.lazySet(null);
                        return;
                    }
                    boolean z4 = this.done;
                    if (atomicReference.get() == null) {
                        z = true;
                    }
                    if (z4 && z) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            errorDownstream(th2);
                            return;
                        } else {
                            completeDownstream();
                            return;
                        }
                    }
                }
                if (j2 != 0) {
                    BackpressureHelper.produced(this, j2);
                }
                i2 = this.wip.addAndGet(-i2);
            } while (i2 != 0);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter, io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (this.done || isCancelled()) {
                return;
            }
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
                return;
            }
            this.queue.set(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableCreate.BaseEmitter
        public boolean signalError(Throwable e) {
            if (this.done || isCancelled()) {
                return false;
            }
            this.error = e;
            this.done = true;
            drain();
            return true;
        }
    }

    /* loaded from: classes3.dex */
    static final class MissingEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        MissingEmitter(Subscriber<? super T> downstream) {
            super(downstream);
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            long j;
            if (isCancelled()) {
                return;
            }
            if (t != null) {
                this.downstream.onNext(t);
                do {
                    j = get();
                    if (j == 0) {
                        return;
                    }
                } while (!compareAndSet(j, j - 1));
                return;
            }
            onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
        }
    }

    /* loaded from: classes3.dex */
    static abstract class NoOverflowBaseAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        NoOverflowBaseAsyncEmitter(Subscriber<? super T> downstream) {
            super(downstream);
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public final void onNext(T t) {
            if (isCancelled()) {
                return;
            }
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
            } else if (get() != 0) {
                this.downstream.onNext(t);
                BackpressureHelper.produced(this, 1L);
            } else {
                onOverflow();
            }
        }

        abstract void onOverflow();
    }

    /* loaded from: classes3.dex */
    static final class SerializedEmitter<T> extends AtomicInteger implements FlowableEmitter<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final BaseEmitter<T> emitter;
        final AtomicThrowable errors = new AtomicThrowable();
        final SimplePlainQueue<T> queue = new SpscLinkedArrayQueue(16);

        SerializedEmitter(BaseEmitter<T> emitter) {
            this.emitter = emitter;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            BaseEmitter<T> baseEmitter = this.emitter;
            SimplePlainQueue<T> simplePlainQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            int i = 1;
            while (!baseEmitter.isCancelled()) {
                if (atomicThrowable.get() != null) {
                    simplePlainQueue.clear();
                    atomicThrowable.tryTerminateConsumer(baseEmitter);
                    return;
                }
                boolean z = this.done;
                T mo10355poll = simplePlainQueue.mo10355poll();
                boolean z2 = mo10355poll == null;
                if (z && z2) {
                    baseEmitter.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    baseEmitter.onNext(mo10355poll);
                }
            }
            simplePlainQueue.clear();
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public boolean isCancelled() {
            return this.emitter.isCancelled();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            if (this.emitter.isCancelled() || this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onError(Throwable t) {
            if (!tryOnError(t)) {
                RxJavaPlugins.onError(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (this.emitter.isCancelled() || this.done) {
                return;
            }
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
                return;
            }
            if (get() == 0 && compareAndSet(0, 1)) {
                this.emitter.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimplePlainQueue<T> simplePlainQueue = this.queue;
                synchronized (simplePlainQueue) {
                    simplePlainQueue.offer(t);
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public long requested() {
            return this.emitter.requested();
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public FlowableEmitter<T> serialize() {
            return this;
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public void setCancellable(Cancellable c) {
            this.emitter.setCancellable(c);
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public void setDisposable(Disposable d) {
            this.emitter.setDisposable(d);
        }

        @Override // java.util.concurrent.atomic.AtomicInteger
        public String toString() {
            return this.emitter.toString();
        }

        @Override // io.reactivex.rxjava3.core.FlowableEmitter
        public boolean tryOnError(Throwable t) {
            if (!this.emitter.isCancelled() && !this.done) {
                if (t == null) {
                    t = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
                }
                if (this.errors.tryAddThrowable(t)) {
                    this.done = true;
                    drain();
                    return true;
                }
            }
            return false;
        }
    }

    public FlowableCreate(FlowableOnSubscribe<T> source, BackpressureStrategy backpressure) {
        this.source = source;
        this.backpressure = backpressure;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> t) {
        BaseEmitter missingEmitter;
        int ordinal = this.backpressure.ordinal();
        if (ordinal == 0) {
            missingEmitter = new MissingEmitter(t);
        } else if (ordinal == 1) {
            missingEmitter = new ErrorAsyncEmitter(t);
        } else if (ordinal == 3) {
            missingEmitter = new DropAsyncEmitter(t);
        } else if (ordinal != 4) {
            missingEmitter = new BufferAsyncEmitter(t, Flowable.bufferSize());
        } else {
            missingEmitter = new LatestAsyncEmitter(t);
        }
        t.onSubscribe(missingEmitter);
        try {
            this.source.subscribe(missingEmitter);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            missingEmitter.onError(th);
        }
    }
}
