package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableZip<T, R> extends Flowable<R> {
    final int bufferSize;
    final boolean delayError;
    final Publisher<? extends T>[] sources;
    final Iterable<? extends Publisher<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    /* loaded from: classes3.dex */
    static final class ZipCoordinator<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(Subscriber<? super R> actual, Function<? super Object[], ? extends R> zipper, int n, int prefetch, boolean delayErrors) {
            this.downstream = actual;
            this.zipper = zipper;
            this.delayErrors = delayErrors;
            ZipSubscriber<T, R>[] zipSubscriberArr = new ZipSubscriber[n];
            for (int i = 0; i < n; i++) {
                zipSubscriberArr[i] = new ZipSubscriber<>(this, prefetch);
            }
            this.current = new Object[n];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
            }
        }

        void cancelAll() {
            for (ZipSubscriber<T, R> zipSubscriber : this.subscribers) {
                zipSubscriber.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b9, code lost:
            if (r14 != 0) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00bd, code lost:
            if (r19.cancelled == false) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00bf, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00c2, code lost:
            if (r19.delayErrors != false) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00ca, code lost:
            if (r19.errors.get() == null) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00cc, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00d4, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00d5, code lost:
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00d7, code lost:
            if (r6 >= r4) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00d9, code lost:
            r0 = r3[r6];
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00dd, code lost:
            if (r5[r6] != null) goto L100;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00df, code lost:
            r10 = r0.done;
            r0 = r0.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00e3, code lost:
            if (r0 == null) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00e5, code lost:
            r0 = r0.mo10355poll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00ea, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00eb, code lost:
            io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r0);
            r19.errors.tryAddThrowableOrReport(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x00f6, code lost:
            if (r19.delayErrors == false) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00f8, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0100, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0101, code lost:
            r0 = null;
            r10 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0104, code lost:
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0105, code lost:
            if (r0 != null) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0107, code lost:
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0109, code lost:
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x010b, code lost:
            if (r10 == false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x010d, code lost:
            if (r11 == false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x010f, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0117, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0118, code lost:
            if (r11 != false) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x011a, code lost:
            r5[r6] = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x011c, code lost:
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0123, code lost:
            if (r12 == 0) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0125, code lost:
            r0 = r3.length;
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0128, code lost:
            if (r6 >= r0) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x012a, code lost:
            r3[r6].request(r12);
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0139, code lost:
            if (r8 == Long.MAX_VALUE) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x013b, code lost:
            r19.requested.addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0141, code lost:
            r7 = addAndGet(-r7);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 329
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableZip.ZipCoordinator.drain():void");
        }

        void error(ZipSubscriber<T, R> inner, Throwable e) {
            if (this.errors.tryAddThrowableOrReport(e)) {
                inner.done = true;
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        void subscribe(Publisher<? extends T>[] sources, int n) {
            ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
            for (int i = 0; i < n && !this.cancelled; i++) {
                if (!this.delayErrors && this.errors.get() != null) {
                    return;
                }
                sources[i].subscribe(zipSubscriberArr[i]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ZipSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> parent, int prefetch) {
            this.parent = parent;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.error(this, t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) s;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        s.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                s.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (this.sourceMode != 1) {
                long j = this.produced + n;
                if (j >= this.limit) {
                    this.produced = 0L;
                    get().request(j);
                    return;
                }
                this.produced = j;
            }
        }
    }

    public FlowableZip(Publisher<? extends T>[] sources, Iterable<? extends Publisher<? extends T>> sourcesIterable, Function<? super Object[], ? extends R> zipper, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.zipper = zipper;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> s) {
        int length;
        Publisher<? extends T>[] publisherArr = this.sources;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            length = 0;
            for (Publisher<? extends T> publisher : this.sourcesIterable) {
                if (length == publisherArr.length) {
                    Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                    System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                    publisherArr = publisherArr2;
                }
                publisherArr[length] = publisher;
                length++;
            }
        } else {
            length = publisherArr.length;
        }
        if (length == 0) {
            EmptySubscription.complete(s);
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(s, this.zipper, length, this.bufferSize, this.delayError);
        s.onSubscribe(zipCoordinator);
        zipCoordinator.subscribe(publisherArr, length);
    }
}
