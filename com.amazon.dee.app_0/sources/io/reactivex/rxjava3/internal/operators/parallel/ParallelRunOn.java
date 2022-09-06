package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class ParallelRunOn<T> extends ParallelFlowable<T> {
    final int prefetch;
    final Scheduler scheduler;
    final ParallelFlowable<? extends T> source;

    /* loaded from: classes3.dex */
    static abstract class BaseRunOnSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = 9222303586456402150L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SpscArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription upstream;
        final Scheduler.Worker worker;

        BaseRunOnSubscriber(int prefetch, SpscArrayQueue<T> queue, Scheduler.Worker worker) {
            this.prefetch = prefetch;
            this.queue = queue;
            this.limit = prefetch - (prefetch >> 2);
            this.worker = worker;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                this.worker.dispose();
                if (getAndIncrement() != 0) {
                    return;
                }
                this.queue.clear();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public final void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            schedule();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            schedule();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onNext(T t) {
            if (this.done) {
                return;
            }
            if (!this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("Queue is full?!"));
                return;
            }
            schedule();
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                schedule();
            }
        }

        final void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.schedule(this);
            }
        }
    }

    /* loaded from: classes3.dex */
    final class MultiWorkerCallback implements SchedulerMultiWorkerSupport.WorkerCallback {
        final Subscriber<T>[] parents;
        final Subscriber<? super T>[] subscribers;

        MultiWorkerCallback(Subscriber<? super T>[] subscribers, Subscriber<T>[] parents) {
            this.subscribers = subscribers;
            this.parents = parents;
        }

        @Override // io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport.WorkerCallback
        public void onWorker(int i, Scheduler.Worker w) {
            ParallelRunOn.this.createSubscriber(i, this.subscribers, this.parents, w);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class RunOnConditionalSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final ConditionalSubscriber<? super T> downstream;

        RunOnConditionalSubscriber(ConditionalSubscriber<? super T> actual, int prefetch, SpscArrayQueue<T> queue, Scheduler.Worker worker) {
            super(prefetch, queue, worker);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(this.prefetch);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
            if (r13 != 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
            if (r18.cancelled == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006e, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            if (r18.done == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
            r7 = r18.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0078, code lost:
            if (r7 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007a, code lost:
            r2.clear();
            r3.onError(r7);
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0085, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x008a, code lost:
            if (r2.isEmpty() == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x008c, code lost:
            r3.onComplete();
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0094, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0097, code lost:
            if (r11 == 0) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0099, code lost:
            io.reactivex.rxjava3.internal.util.BackpressureHelper.produced(r18.requested, r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x009e, code lost:
            r18.consumed = r1;
            r6 = addAndGet(-r6);
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r18 = this;
                r0 = r18
                int r1 = r0.consumed
                io.reactivex.rxjava3.internal.queue.SpscArrayQueue<T> r2 = r0.queue
                io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber<? super T> r3 = r0.downstream
                int r4 = r0.limit
                r5 = 1
                r6 = r5
            Lc:
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r7 = r7.get()
                r9 = 0
                r11 = r9
            L15:
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 == 0) goto L68
                boolean r14 = r0.cancelled
                if (r14 == 0) goto L21
                r2.clear()
                return
            L21:
                boolean r14 = r0.done
                if (r14 == 0) goto L35
                java.lang.Throwable r15 = r0.error
                if (r15 == 0) goto L35
                r2.clear()
                r3.onError(r15)
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L35:
                java.lang.Object r15 = r2.mo10355poll()
                r16 = 0
                if (r15 != 0) goto L40
                r17 = r5
                goto L42
            L40:
                r17 = r16
            L42:
                if (r14 == 0) goto L4f
                if (r17 == 0) goto L4f
                r3.onComplete()
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L4f:
                if (r17 == 0) goto L52
                goto L68
            L52:
                boolean r13 = r3.tryOnNext(r15)
                if (r13 == 0) goto L5b
                r13 = 1
                long r11 = r11 + r13
            L5b:
                int r1 = r1 + 1
                if (r1 != r4) goto L15
                org.reactivestreams.Subscription r13 = r0.upstream
                long r14 = (long) r1
                r13.request(r14)
                r1 = r16
                goto L15
            L68:
                if (r13 != 0) goto L95
                boolean r7 = r0.cancelled
                if (r7 == 0) goto L72
                r2.clear()
                return
            L72:
                boolean r7 = r0.done
                if (r7 == 0) goto L95
                java.lang.Throwable r7 = r0.error
                if (r7 == 0) goto L86
                r2.clear()
                r3.onError(r7)
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L86:
                boolean r7 = r2.isEmpty()
                if (r7 == 0) goto L95
                r3.onComplete()
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L95:
                int r7 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
                if (r7 == 0) goto L9e
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                io.reactivex.rxjava3.internal.util.BackpressureHelper.produced(r7, r11)
            L9e:
                r0.consumed = r1
                int r6 = -r6
                int r6 = r0.addAndGet(r6)
                if (r6 != 0) goto Lc
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelRunOn.RunOnConditionalSubscriber.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class RunOnSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final Subscriber<? super T> downstream;

        RunOnSubscriber(Subscriber<? super T> actual, int prefetch, SpscArrayQueue<T> queue, Scheduler.Worker worker) {
            super(prefetch, queue, worker);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(this.prefetch);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
            if (r13 != 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
            if (r18.cancelled == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x006e, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0071, code lost:
            if (r18.done == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0073, code lost:
            r13 = r18.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
            if (r13 == null) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
            r2.clear();
            r3.onError(r13);
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0082, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0087, code lost:
            if (r2.isEmpty() == false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0089, code lost:
            r3.onComplete();
            r18.worker.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0091, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0094, code lost:
            if (r11 == 0) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x009d, code lost:
            if (r7 == Long.MAX_VALUE) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x009f, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a5, code lost:
            r7 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a9, code lost:
            if (r7 != r6) goto L57;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00ab, code lost:
            r18.consumed = r1;
            r6 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00b2, code lost:
            if (r6 != 0) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00b4, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00b5, code lost:
            r6 = r7;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r18 = this;
                r0 = r18
                int r1 = r0.consumed
                io.reactivex.rxjava3.internal.queue.SpscArrayQueue<T> r2 = r0.queue
                org.reactivestreams.Subscriber<? super T> r3 = r0.downstream
                int r4 = r0.limit
                r5 = 1
                r6 = r5
            Lc:
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r7 = r7.get()
                r9 = 0
                r11 = r9
            L15:
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 == 0) goto L65
                boolean r14 = r0.cancelled
                if (r14 == 0) goto L21
                r2.clear()
                return
            L21:
                boolean r14 = r0.done
                if (r14 == 0) goto L35
                java.lang.Throwable r15 = r0.error
                if (r15 == 0) goto L35
                r2.clear()
                r3.onError(r15)
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L35:
                java.lang.Object r15 = r2.mo10355poll()
                r16 = 0
                if (r15 != 0) goto L40
                r17 = r5
                goto L42
            L40:
                r17 = r16
            L42:
                if (r14 == 0) goto L4f
                if (r17 == 0) goto L4f
                r3.onComplete()
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L4f:
                if (r17 == 0) goto L52
                goto L65
            L52:
                r3.onNext(r15)
                r13 = 1
                long r11 = r11 + r13
                int r1 = r1 + 1
                if (r1 != r4) goto L15
                org.reactivestreams.Subscription r13 = r0.upstream
                long r14 = (long) r1
                r13.request(r14)
                r1 = r16
                goto L15
            L65:
                if (r13 != 0) goto L92
                boolean r13 = r0.cancelled
                if (r13 == 0) goto L6f
                r2.clear()
                return
            L6f:
                boolean r13 = r0.done
                if (r13 == 0) goto L92
                java.lang.Throwable r13 = r0.error
                if (r13 == 0) goto L83
                r2.clear()
                r3.onError(r13)
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L83:
                boolean r13 = r2.isEmpty()
                if (r13 == 0) goto L92
                r3.onComplete()
                io.reactivex.rxjava3.core.Scheduler$Worker r1 = r0.worker
                r1.dispose()
                return
            L92:
                int r9 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
                if (r9 == 0) goto La5
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r7 == 0) goto La5
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                long r8 = -r11
                r7.addAndGet(r8)
            La5:
                int r7 = r18.get()
                if (r7 != r6) goto Lb5
                r0.consumed = r1
                int r6 = -r6
                int r6 = r0.addAndGet(r6)
                if (r6 != 0) goto Lc
                return
            Lb5:
                r6 = r7
                goto Lc
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelRunOn.RunOnSubscriber.run():void");
        }
    }

    public ParallelRunOn(ParallelFlowable<? extends T> parent, Scheduler scheduler, int prefetch) {
        this.source = parent;
        this.scheduler = scheduler;
        this.prefetch = prefetch;
    }

    void createSubscriber(int i, Subscriber<? super T>[] subscribers, Subscriber<T>[] parents, Scheduler.Worker worker) {
        Subscriber<? super T> subscriber = subscribers[i];
        SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.prefetch);
        if (subscriber instanceof ConditionalSubscriber) {
            parents[i] = new RunOnConditionalSubscriber((ConditionalSubscriber) subscriber, this.prefetch, spscArrayQueue, worker);
        } else {
            parents[i] = new RunOnSubscriber(subscriber, this.prefetch, spscArrayQueue, worker);
        }
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.source.parallelism();
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(final Subscriber<? super T>[] subscribers) {
        if (!validate(subscribers)) {
            return;
        }
        int length = subscribers.length;
        Subscriber<T>[] subscriberArr = new Subscriber[length];
        Scheduler scheduler = this.scheduler;
        if (scheduler instanceof SchedulerMultiWorkerSupport) {
            ((SchedulerMultiWorkerSupport) scheduler).createWorkers(length, new MultiWorkerCallback(subscribers, subscriberArr));
        } else {
            for (int i = 0; i < length; i++) {
                createSubscriber(i, subscribers, subscriberArr, this.scheduler.createWorker());
            }
        }
        this.source.subscribe(subscriberArr);
    }
}
