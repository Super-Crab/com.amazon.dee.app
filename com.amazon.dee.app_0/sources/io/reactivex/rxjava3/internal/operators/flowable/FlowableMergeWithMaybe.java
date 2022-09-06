package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableMergeWithMaybe<T> extends AbstractFlowableWithUpstream<T, T> {
    final MaybeSource<? extends T> other;

    /* loaded from: classes3.dex */
    static final class MergeWithObserver<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean cancelled;
        int consumed;
        final Subscriber<? super T> downstream;
        long emitted;
        final int limit;
        volatile boolean mainDone;
        volatile int otherState;
        volatile SimplePlainQueue<T> queue;
        T singleItem;
        final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
        final OtherObserver<T> otherObserver = new OtherObserver<>(this);
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final int prefetch = Flowable.bufferSize();

        /* loaded from: classes3.dex */
        static final class OtherObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> parent) {
                this.parent = parent;
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.otherComplete();
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable e) {
                this.parent.otherError(e);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }
        }

        MergeWithObserver(Subscriber<? super T> downstream) {
            this.downstream = downstream;
            int i = this.prefetch;
            this.limit = i - (i >> 2);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
            this.errors.tryTerminateAndReport();
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0080, code lost:
            if (r3 != 0) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0084, code lost:
            if (r19.cancelled == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0086, code lost:
            r19.singleItem = null;
            r19.queue = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x008a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0091, code lost:
            if (r19.errors.get() == null) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
            r19.singleItem = null;
            r19.queue = null;
            r19.errors.tryTerminateConsumer(r19.downstream);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x009e, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x009f, code lost:
            r3 = r19.mainDone;
            r6 = r19.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00a3, code lost:
            if (r6 == null) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00a9, code lost:
            if (r6.isEmpty() == false) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ac, code lost:
            r17 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00af, code lost:
            r17 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b1, code lost:
            if (r3 == false) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
            if (r17 == false) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b7, code lost:
            if (r19.otherState != 2) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00b9, code lost:
            r19.queue = null;
            r1.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00be, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00bf, code lost:
            r19.emitted = r7;
            r19.consumed = r4;
            r2 = addAndGet(-r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00c8, code lost:
            if (r2 != 0) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00ca, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 206
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableMergeWithMaybe.MergeWithObserver.drainLoop():void");
        }

        SimplePlainQueue<T> getOrCreateQueue() {
            SimplePlainQueue<T> simplePlainQueue = this.queue;
            if (simplePlainQueue == null) {
                SpscArrayQueue spscArrayQueue = new SpscArrayQueue(Flowable.bufferSize());
                this.queue = spscArrayQueue;
                return spscArrayQueue;
            }
            return simplePlainQueue;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable ex) {
            if (this.errors.tryAddThrowableOrReport(ex)) {
                DisposableHelper.dispose(this.otherObserver);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    SimplePlainQueue<T> simplePlainQueue = this.queue;
                    if (simplePlainQueue != null && !simplePlainQueue.isEmpty()) {
                        simplePlainQueue.offer(t);
                    } else {
                        this.emitted = j + 1;
                        this.downstream.onNext(t);
                        int i = this.consumed + 1;
                        if (i == this.limit) {
                            this.consumed = 0;
                            this.mainSubscription.get().request(i);
                        } else {
                            this.consumed = i;
                        }
                    }
                } else {
                    getOrCreateQueue().offer(t);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                getOrCreateQueue().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this.mainSubscription, s, this.prefetch);
        }

        void otherComplete() {
            this.otherState = 2;
            drain();
        }

        void otherError(Throwable ex) {
            if (this.errors.tryAddThrowableOrReport(ex)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
            }
        }

        void otherSuccess(T value) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    this.emitted = j + 1;
                    this.downstream.onNext(value);
                    this.otherState = 2;
                } else {
                    this.singleItem = value;
                    this.otherState = 1;
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            } else {
                this.singleItem = value;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            BackpressureHelper.add(this.requested, n);
            drain();
        }
    }

    public FlowableMergeWithMaybe(Flowable<T> source, MaybeSource<? extends T> other) {
        super(source);
        this.other = other;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(subscriber);
        subscriber.onSubscribe(mergeWithObserver);
        this.source.subscribe((FlowableSubscriber) mergeWithObserver);
        this.other.subscribe(mergeWithObserver.otherObserver);
    }
}
