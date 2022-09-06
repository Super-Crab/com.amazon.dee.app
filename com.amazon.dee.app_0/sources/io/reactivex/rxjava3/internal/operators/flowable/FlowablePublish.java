package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T> {
    final int bufferSize;
    final AtomicReference<PublishConnection<T>> current = new AtomicReference<>();
    final Publisher<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class InnerSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 2845000326761540265L;
        final Subscriber<? super T> downstream;
        long emitted;
        final PublishConnection<T> parent;

        InnerSubscription(Subscriber<? super T> downstream, PublishConnection<T> parent) {
            this.downstream = downstream;
            this.parent = parent;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                this.parent.drain();
            }
        }
    }

    /* loaded from: classes3.dex */
    static final class PublishConnection<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = -1672047311619175801L;
        final int bufferSize;
        int consumed;
        final AtomicReference<PublishConnection<T>> current;
        volatile boolean done;
        Throwable error;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final AtomicBoolean connect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        PublishConnection(AtomicReference<PublishConnection<T>> current, int bufferSize) {
            this.current = current;
            this.bufferSize = bufferSize;
        }

        boolean add(InnerSubscription<T> inner) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription<T>[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = this.subscribers.get();
                if (innerSubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = innerSubscriptionArr.length;
                innerSubscriptionArr2 = new InnerSubscription[length + 1];
                System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr2, 0, length);
                innerSubscriptionArr2[length] = inner;
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
            return true;
        }

        boolean checkTerminated(boolean isDone, boolean isEmpty) {
            InnerSubscription<T>[] andSet;
            if (!isDone || !isEmpty) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                signalError(th);
                return true;
            }
            for (InnerSubscription<T> innerSubscription : this.subscribers.getAndSet(TERMINATED)) {
                if (!innerSubscription.isCancelled()) {
                    innerSubscription.downstream.onComplete();
                }
            }
            return true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.subscribers.getAndSet(TERMINATED);
            this.current.compareAndSet(this, null);
            SubscriptionHelper.cancel(this.upstream);
        }

        void drain() {
            boolean z;
            int i;
            T t;
            if (getAndIncrement() != 0) {
                return;
            }
            SimpleQueue<T> simpleQueue = this.queue;
            int i2 = this.consumed;
            int i3 = this.bufferSize;
            int i4 = i3 - (i3 >> 2);
            boolean z2 = true;
            boolean z3 = this.sourceMode != 1;
            int i5 = i2;
            SimpleQueue<T> simpleQueue2 = simpleQueue;
            int i6 = 1;
            while (true) {
                if (simpleQueue2 != null) {
                    InnerSubscription<T>[] innerSubscriptionArr = this.subscribers.get();
                    long j = Long.MAX_VALUE;
                    boolean z4 = false;
                    for (InnerSubscription<T> innerSubscription : innerSubscriptionArr) {
                        long j2 = innerSubscription.get();
                        if (j2 != Long.MIN_VALUE) {
                            j = Math.min(j2 - innerSubscription.emitted, j);
                            z4 = true;
                        }
                    }
                    if (!z4) {
                        j = 0;
                    }
                    for (long j3 = 0; j != j3; j3 = 0) {
                        boolean z5 = this.done;
                        try {
                            T mo10355poll = simpleQueue2.mo10355poll();
                            boolean z6 = mo10355poll == null;
                            if (checkTerminated(z5, z6)) {
                                return;
                            }
                            if (z6) {
                                break;
                            }
                            int length = innerSubscriptionArr.length;
                            int i7 = 0;
                            while (i7 < length) {
                                InnerSubscription<T> innerSubscription2 = innerSubscriptionArr[i7];
                                if (!innerSubscription2.isCancelled()) {
                                    innerSubscription2.downstream.onNext(mo10355poll);
                                    i = length;
                                    t = mo10355poll;
                                    innerSubscription2.emitted++;
                                } else {
                                    i = length;
                                    t = mo10355poll;
                                }
                                i7++;
                                mo10355poll = t;
                                length = i;
                            }
                            if (z3 && (i5 = i5 + 1) == i4) {
                                this.upstream.get().request(i4);
                                i5 = 0;
                            }
                            j--;
                            if (innerSubscriptionArr != this.subscribers.get()) {
                                z2 = true;
                                break;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.upstream.get().cancel();
                            simpleQueue2.clear();
                            this.done = true;
                            signalError(th);
                            return;
                        }
                    }
                    z = true;
                    if (checkTerminated(this.done, simpleQueue2.isEmpty())) {
                        return;
                    }
                } else {
                    z = z2;
                }
                this.consumed = i5;
                i6 = addAndGet(-i6);
                if (i6 == 0) {
                    return;
                }
                if (simpleQueue2 == null) {
                    simpleQueue2 = this.queue;
                }
                z2 = z;
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            } else {
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.upstream, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) s;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        s.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request(this.bufferSize);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void remove(InnerSubscription<T> inner) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = this.subscribers.get();
                int length = innerSubscriptionArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerSubscriptionArr[i2] == inner) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    innerSubscriptionArr2 = EMPTY;
                } else {
                    InnerSubscription[] innerSubscriptionArr3 = new InnerSubscription[length - 1];
                    System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr3, 0, i);
                    System.arraycopy(innerSubscriptionArr, i + 1, innerSubscriptionArr3, i, (length - i) - 1);
                    innerSubscriptionArr2 = innerSubscriptionArr3;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
        }

        void signalError(Throwable ex) {
            InnerSubscription<T>[] andSet;
            for (InnerSubscription<T> innerSubscription : this.subscribers.getAndSet(TERMINATED)) {
                if (!innerSubscription.isCancelled()) {
                    innerSubscription.downstream.onError(ex);
                }
            }
        }
    }

    public FlowablePublish(Publisher<T> source, int bufferSize) {
        this.source = source;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.rxjava3.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> connection) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.current.get();
            if (publishConnection != null && !publishConnection.isDisposed()) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.current, this.bufferSize);
            if (this.current.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        boolean z = true;
        if (publishConnection.connect.get() || !publishConnection.connect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            connection.accept(publishConnection);
            if (!z) {
                return;
            }
            this.source.subscribe(publishConnection);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.rxjava3.flowables.ConnectableFlowable
    public void reset() {
        PublishConnection<T> publishConnection = this.current.get();
        if (publishConnection == null || !publishConnection.isDisposed()) {
            return;
        }
        this.current.compareAndSet(publishConnection, null);
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.source;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.current.get();
            if (publishConnection != null) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.current, this.bufferSize);
            if (this.current.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        InnerSubscription<T> innerSubscription = new InnerSubscription<>(s, publishConnection);
        s.onSubscribe(innerSubscription);
        if (publishConnection.add(innerSubscription)) {
            if (innerSubscription.isCancelled()) {
                publishConnection.remove(innerSubscription);
                return;
            } else {
                publishConnection.drain();
                return;
            }
        }
        Throwable th = publishConnection.error;
        if (th != null) {
            innerSubscription.downstream.onError(th);
        } else {
            innerSubscription.downstream.onComplete();
        }
    }
}
