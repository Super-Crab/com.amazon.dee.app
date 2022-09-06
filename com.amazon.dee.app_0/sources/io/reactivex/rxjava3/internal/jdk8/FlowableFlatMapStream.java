package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableFlatMapStream<T, R> extends Flowable<R> {
    final Function<? super T, ? extends Stream<? extends R>> mapper;
    final int prefetch;
    final Flowable<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class FlatMapStreamSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5127032662980523968L;
        volatile boolean cancelled;
        int consumed;
        AutoCloseable currentCloseable;
        Iterator<? extends R> currentIterator;
        final Subscriber<? super R> downstream;
        long emitted;
        final Function<? super T, ? extends Stream<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        int sourceMode;
        Subscription upstream;
        volatile boolean upstreamDone;
        final AtomicLong requested = new AtomicLong();
        final AtomicThrowable error = new AtomicThrowable();

        FlatMapStreamSubscriber(Subscriber<? super R> downstream, Function<? super T, ? extends Stream<? extends R>> mapper, int prefetch) {
            this.downstream = downstream;
            this.mapper = mapper;
            this.prefetch = prefetch;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            drain();
        }

        void clearCurrentRethrowCloseError() throws Throwable {
            this.currentIterator = null;
            AutoCloseable autoCloseable = this.currentCloseable;
            this.currentCloseable = null;
            if (autoCloseable != null) {
                autoCloseable.close();
            }
        }

        void clearCurrentSuppressCloseError() {
            try {
                clearCurrentRethrowCloseError();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        void drain() {
            int i;
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.downstream;
            SimpleQueue<T> simpleQueue = this.queue;
            AtomicThrowable atomicThrowable = this.error;
            Iterator<? extends R> it2 = this.currentIterator;
            long j = this.requested.get();
            long j2 = this.emitted;
            int i2 = this.prefetch;
            int i3 = i2 - (i2 >> 2);
            int i4 = 0;
            boolean z = true;
            Object[] objArr = this.sourceMode != 1 ? 1 : null;
            long j3 = j;
            int i5 = 1;
            Iterator<? extends R> it3 = it2;
            while (true) {
                if (this.cancelled) {
                    simpleQueue.clear();
                    clearCurrentSuppressCloseError();
                } else {
                    boolean z2 = this.upstreamDone;
                    if (atomicThrowable.get() != null) {
                        subscriber.onError(atomicThrowable.get());
                        this.cancelled = z;
                    } else {
                        if (it3 == null) {
                            try {
                                T mo10355poll = simpleQueue.mo10355poll();
                                if (mo10355poll == null) {
                                    int i6 = z ? 1 : 0;
                                    Object[] objArr2 = z ? 1 : 0;
                                    i = i6;
                                } else {
                                    i = i4;
                                }
                                if (z2 && i != 0) {
                                    subscriber.onComplete();
                                    this.cancelled = z;
                                } else if (i == 0) {
                                    if (objArr != null) {
                                        int i7 = this.consumed;
                                        int i8 = z ? 1 : 0;
                                        int i9 = z ? 1 : 0;
                                        int i10 = i7 + i8;
                                        this.consumed = i10;
                                        if (i10 == i3) {
                                            this.consumed = i4;
                                            this.upstream.request(i3);
                                        }
                                    }
                                    try {
                                        Stream stream = (Stream) Objects.requireNonNull(this.mapper.mo10358apply(mo10355poll), "The mapper returned a null Stream");
                                        it3 = stream.iterator();
                                        if (it3.hasNext()) {
                                            this.currentIterator = it3;
                                            this.currentCloseable = stream;
                                        }
                                        it3 = null;
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        trySignalError(subscriber, th);
                                    }
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                trySignalError(subscriber, th2);
                            }
                        }
                        if (it3 != null && j2 != j3) {
                            try {
                                Object obj = (Object) Objects.requireNonNull(it3.next(), "The Stream.Iterator returned a null value");
                                if (!this.cancelled) {
                                    subscriber.onNext(obj);
                                    j2++;
                                    if (!this.cancelled) {
                                        try {
                                            if (!it3.hasNext()) {
                                                try {
                                                    clearCurrentRethrowCloseError();
                                                    it3 = null;
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    it3 = null;
                                                    Exceptions.throwIfFatal(th);
                                                    trySignalError(subscriber, th);
                                                    i4 = 0;
                                                    z = true;
                                                }
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                    }
                                }
                            } catch (Throwable th5) {
                                Exceptions.throwIfFatal(th5);
                                trySignalError(subscriber, th5);
                            }
                        }
                    }
                    i4 = 0;
                    z = true;
                }
                this.emitted = j2;
                i5 = addAndGet(-i5);
                if (i5 == 0) {
                    return;
                }
                j3 = this.requested.get();
                i4 = 0;
                z = true;
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.upstreamDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.error.compareAndSet(null, t)) {
                this.upstreamDone = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2 && !this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            }
            drain();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(@NonNull Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) s;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.upstreamDone = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.downstream.onSubscribe(this);
                        s.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                s.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        void trySignalError(Subscriber<?> downstream, Throwable ex) {
            if (this.error.compareAndSet(null, ex)) {
                this.upstream.cancel();
                this.cancelled = true;
                downstream.onError(ex);
                return;
            }
            RxJavaPlugins.onError(ex);
        }
    }

    public FlowableFlatMapStream(Flowable<T> source, Function<? super T, ? extends Stream<? extends R>> mapper, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
    }

    public static <T, R> Subscriber<T> subscribe(Subscriber<? super R> downstream, Function<? super T, ? extends Stream<? extends R>> mapper, int prefetch) {
        return new FlatMapStreamSubscriber(downstream, mapper, prefetch);
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        Flowable<T> flowable = this.source;
        if (flowable instanceof Supplier) {
            Stream stream = null;
            try {
                Object mo10365get = ((Supplier) flowable).mo10365get();
                if (mo10365get != null) {
                    stream = (Stream) Objects.requireNonNull(this.mapper.mo10358apply(mo10365get), "The mapper returned a null Stream");
                }
                if (stream != null) {
                    FlowableFromStream.subscribeStream(s, stream);
                    return;
                } else {
                    EmptySubscription.complete(s);
                    return;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptySubscription.error(th, s);
                return;
            }
        }
        flowable.subscribe(subscribe(s, this.mapper, this.prefetch));
    }
}
