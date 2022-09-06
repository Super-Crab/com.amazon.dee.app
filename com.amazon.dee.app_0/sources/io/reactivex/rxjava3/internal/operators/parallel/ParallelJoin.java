package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class ParallelJoin<T> extends Flowable<T> {
    final boolean delayErrors;
    final int prefetch;
    final ParallelFlowable<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class JoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final JoinSubscriptionBase<T> parent;
        final int prefetch;
        long produced;
        volatile SimplePlainQueue<T> queue;

        JoinInnerSubscriber(JoinSubscriptionBase<T> parent, int prefetch) {
            this.parent = parent;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        SimplePlainQueue<T> getQueue() {
            SimplePlainQueue<T> simplePlainQueue = this.queue;
            if (simplePlainQueue == null) {
                SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.prefetch);
                this.queue = spscArrayQueue;
                return spscArrayQueue;
            }
            return simplePlainQueue;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.parent.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this, s, this.prefetch);
        }

        public void request(long n) {
            long j = this.produced + n;
            if (j >= this.limit) {
                this.produced = 0L;
                get().request(j);
                return;
            }
            this.produced = j;
        }

        public void requestOne() {
            long j = this.produced + 1;
            if (j == this.limit) {
                this.produced = 0L;
                get().request(j);
                return;
            }
            this.produced = j;
        }
    }

    /* loaded from: classes3.dex */
    static final class JoinSubscription<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        JoinSubscription(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0060, code lost:
            if (r12 == false) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0062, code lost:
            if (r11 == false) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
            r3.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0068, code lost:
            if (r11 == false) goto L77;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
            r10 = r14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drainLoop() {
            /*
                Method dump skipped, instructions count: 198
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscription.drainLoop():void");
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        public void onError(Throwable e) {
            if (this.errors.compareAndSet(null, e)) {
                cancelAll();
                drain();
            } else if (e == this.errors.get()) {
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        public void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(value);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1L);
                } else if (!inner.getQueue().offer(value)) {
                    cancelAll();
                    MissingBackpressureException missingBackpressureException = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet(null, missingBackpressureException)) {
                        this.downstream.onError(missingBackpressureException);
                        return;
                    } else {
                        RxJavaPlugins.onError(missingBackpressureException);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!inner.getQueue().offer(value)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class JoinSubscriptionBase<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3100232009247827843L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        final JoinInnerSubscriber<T>[] subscribers;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger done = new AtomicInteger();

        JoinSubscriptionBase(Subscriber<? super T> actual, int n, int prefetch) {
            this.downstream = actual;
            JoinInnerSubscriber<T>[] joinInnerSubscriberArr = new JoinInnerSubscriber[n];
            for (int i = 0; i < n; i++) {
                joinInnerSubscriberArr[i] = new JoinInnerSubscriber<>(this, prefetch);
            }
            this.subscribers = joinInnerSubscriberArr;
            this.done.lazySet(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() != 0) {
                    return;
                }
                cleanup();
            }
        }

        void cancelAll() {
            for (JoinInnerSubscriber<T> joinInnerSubscriber : this.subscribers) {
                joinInnerSubscriber.cancel();
            }
        }

        void cleanup() {
            for (JoinInnerSubscriber<T> joinInnerSubscriber : this.subscribers) {
                joinInnerSubscriber.queue = null;
            }
        }

        abstract void drain();

        abstract void onComplete();

        abstract void onError(Throwable e);

        abstract void onNext(JoinInnerSubscriber<T> inner, T value);

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }
    }

    /* loaded from: classes3.dex */
    static final class JoinSubscriptionDelayError<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        JoinSubscriptionDelayError(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x004e, code lost:
            if (r12 == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
            if (r11 == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0052, code lost:
            r19.errors.tryTerminateConsumer(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0057, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0058, code lost:
            if (r11 == false) goto L69;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
            r10 = r14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drainLoop() {
            /*
                r19 = this;
                r0 = r19
                io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin$JoinInnerSubscriber<T>[] r1 = r0.subscribers
                int r2 = r1.length
                org.reactivestreams.Subscriber<? super T> r3 = r0.downstream
                r5 = 1
            L8:
                java.util.concurrent.atomic.AtomicLong r6 = r0.requested
                long r6 = r6.get()
                r8 = 0
                r10 = r8
            L11:
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 == 0) goto L5e
                boolean r12 = r0.cancelled
                if (r12 == 0) goto L1d
                r19.cleanup()
                return
            L1d:
                java.util.concurrent.atomic.AtomicInteger r12 = r0.done
                int r12 = r12.get()
                if (r12 != 0) goto L27
                r12 = 1
                goto L28
            L27:
                r12 = 0
            L28:
                r14 = r10
                r10 = 0
                r11 = 1
            L2b:
                if (r10 >= r2) goto L4e
                r4 = r1[r10]
                io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue<T> r13 = r4.queue
                if (r13 == 0) goto L4b
                java.lang.Object r13 = r13.mo10355poll()
                if (r13 == 0) goto L4b
                r3.onNext(r13)
                r4.requestOne()
                r17 = 1
                long r13 = r14 + r17
                int r4 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r4 != 0) goto L49
                r10 = r13
                goto L5e
            L49:
                r14 = r13
                r11 = 0
            L4b:
                int r10 = r10 + 1
                goto L2b
            L4e:
                if (r12 == 0) goto L58
                if (r11 == 0) goto L58
                io.reactivex.rxjava3.internal.util.AtomicThrowable r1 = r0.errors
                r1.tryTerminateConsumer(r3)
                return
            L58:
                if (r11 == 0) goto L5c
                r10 = r14
                goto L5e
            L5c:
                r10 = r14
                goto L11
            L5e:
                int r4 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r4 != 0) goto L96
                boolean r4 = r0.cancelled
                if (r4 == 0) goto L6a
                r19.cleanup()
                return
            L6a:
                java.util.concurrent.atomic.AtomicInteger r4 = r0.done
                int r4 = r4.get()
                if (r4 != 0) goto L74
                r4 = 1
                goto L75
            L74:
                r4 = 0
            L75:
                r6 = 0
            L76:
                if (r6 >= r2) goto L8a
                r7 = r1[r6]
                io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue<T> r7 = r7.queue
                if (r7 == 0) goto L87
                boolean r7 = r7.isEmpty()
                if (r7 != 0) goto L87
                r16 = 0
                goto L8c
            L87:
                int r6 = r6 + 1
                goto L76
            L8a:
                r16 = 1
            L8c:
                if (r4 == 0) goto L96
                if (r16 == 0) goto L96
                io.reactivex.rxjava3.internal.util.AtomicThrowable r1 = r0.errors
                r1.tryTerminateConsumer(r3)
                return
            L96:
                int r4 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r4 == 0) goto L9f
                java.util.concurrent.atomic.AtomicLong r4 = r0.requested
                io.reactivex.rxjava3.internal.util.BackpressureHelper.produced(r4, r10)
            L9f:
                int r4 = -r5
                int r5 = r0.addAndGet(r4)
                if (r5 != 0) goto L8
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionDelayError.drainLoop():void");
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        void onError(Throwable e) {
            if (this.errors.tryAddThrowableOrReport(e)) {
                this.done.decrementAndGet();
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.parallel.ParallelJoin.JoinSubscriptionBase
        void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(value);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1L);
                } else if (!inner.getQueue().offer(value)) {
                    inner.cancel();
                    this.errors.tryAddThrowableOrReport(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                if (!inner.getQueue().offer(value)) {
                    inner.cancel();
                    this.errors.tryAddThrowableOrReport(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }
    }

    public ParallelJoin(ParallelFlowable<? extends T> source, int prefetch, boolean delayErrors) {
        this.source = source;
        this.prefetch = prefetch;
        this.delayErrors = delayErrors;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        JoinSubscriptionBase joinSubscription;
        if (this.delayErrors) {
            joinSubscription = new JoinSubscriptionDelayError(s, this.source.parallelism(), this.prefetch);
        } else {
            joinSubscription = new JoinSubscription(s, this.source.parallelism(), this.prefetch);
        }
        s.onSubscribe(joinSubscription);
        this.source.subscribe(joinSubscription.subscribers);
    }
}
