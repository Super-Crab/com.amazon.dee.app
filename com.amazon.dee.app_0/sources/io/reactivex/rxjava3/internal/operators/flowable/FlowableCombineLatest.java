package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableMap;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableCombineLatest<T, R> extends Flowable<R> {
    @Nullable
    final Publisher<? extends T>[] array;
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayErrors;
    @Nullable
    final Iterable<? extends Publisher<? extends T>> iterable;

    /* loaded from: classes3.dex */
    static final class CombineLatestCoordinator<T, R> extends BasicIntQueueSubscription<R> {
        private static final long serialVersionUID = -5082275438355852221L;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int completedSources;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final AtomicThrowable error;
        final Object[] latest;
        int nonEmptySources;
        boolean outputFused;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombineLatestInnerSubscriber<T>[] subscribers;

        CombineLatestCoordinator(Subscriber<? super R> actual, Function<? super Object[], ? extends R> combiner, int n, int bufferSize, boolean delayErrors) {
            this.downstream = actual;
            this.combiner = combiner;
            CombineLatestInnerSubscriber<T>[] combineLatestInnerSubscriberArr = new CombineLatestInnerSubscriber[n];
            for (int i = 0; i < n; i++) {
                combineLatestInnerSubscriberArr[i] = new CombineLatestInnerSubscriber<>(this, i, bufferSize);
            }
            this.subscribers = combineLatestInnerSubscriberArr;
            this.latest = new Object[n];
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
            this.requested = new AtomicLong();
            this.error = new AtomicThrowable();
            this.delayErrors = delayErrors;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            cancelAll();
            drain();
        }

        void cancelAll() {
            for (CombineLatestInnerSubscriber<T> combineLatestInnerSubscriber : this.subscribers) {
                combineLatestInnerSubscriber.cancel();
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, SpscLinkedArrayQueue<?> q) {
            if (this.cancelled) {
                cancelAll();
                q.clear();
                this.error.tryTerminateAndReport();
                return true;
            } else if (!d) {
                return false;
            } else {
                if (this.delayErrors) {
                    if (!empty) {
                        return false;
                    }
                    cancelAll();
                    this.error.tryTerminateConsumer(a);
                    return true;
                }
                Throwable terminate = ExceptionHelper.terminate(this.error);
                if (terminate != null && terminate != ExceptionHelper.TERMINATED) {
                    cancelAll();
                    q.clear();
                    a.onError(terminate);
                    return true;
                } else if (!empty) {
                    return false;
                } else {
                    cancelAll();
                    a.onComplete();
                    return true;
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.queue.clear();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            if (this.outputFused) {
                drainOutput();
            } else {
                drainAsync();
            }
        }

        void drainAsync() {
            int i;
            Subscriber<? super R> subscriber = this.downstream;
            SpscLinkedArrayQueue<?> spscLinkedArrayQueue = this.queue;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.done;
                    Object mo10355poll = spscLinkedArrayQueue.mo10355poll();
                    boolean z2 = mo10355poll == null;
                    if (checkTerminated(z, z2, subscriber, spscLinkedArrayQueue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    try {
                        subscriber.onNext((Object) Objects.requireNonNull(this.combiner.mo10358apply((Object[]) spscLinkedArrayQueue.mo10355poll()), "The combiner returned a null value"));
                        ((CombineLatestInnerSubscriber) mo10355poll).requestOne();
                        j2++;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        cancelAll();
                        ExceptionHelper.addThrowable(this.error, th);
                        subscriber.onError(ExceptionHelper.terminate(this.error));
                        return;
                    }
                }
                if (i == 0 && checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    this.requested.addAndGet(-j2);
                }
                i2 = addAndGet(-i2);
            } while (i2 != 0);
        }

        void drainOutput() {
            Subscriber<? super R> subscriber = this.downstream;
            SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = this.error.get();
                if (th != null) {
                    spscLinkedArrayQueue.clear();
                    subscriber.onError(th);
                    return;
                }
                boolean z = this.done;
                boolean isEmpty = spscLinkedArrayQueue.isEmpty();
                if (!isEmpty) {
                    subscriber.onNext(null);
                }
                if (z && isEmpty) {
                    subscriber.onComplete();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            spscLinkedArrayQueue.clear();
        }

        void innerComplete(int index) {
            synchronized (this) {
                Object[] objArr = this.latest;
                if (objArr[index] != null) {
                    int i = this.completedSources + 1;
                    if (i == objArr.length) {
                        this.done = true;
                    } else {
                        this.completedSources = i;
                        return;
                    }
                } else {
                    this.done = true;
                }
                drain();
            }
        }

        void innerError(int index, Throwable e) {
            if (ExceptionHelper.addThrowable(this.error, e)) {
                if (!this.delayErrors) {
                    cancelAll();
                    this.done = true;
                    drain();
                    return;
                }
                innerComplete(index);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        void innerValue(int index, T value) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                int i = this.nonEmptySources;
                if (objArr[index] == null) {
                    i++;
                    this.nonEmptySources = i;
                }
                objArr[index] = value;
                if (objArr.length == i) {
                    this.queue.offer(this.subscribers[index], objArr.clone());
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                this.subscribers[index].requestOne();
            } else {
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public R mo10355poll() throws Throwable {
            Object mo10355poll = this.queue.mo10355poll();
            if (mo10355poll == null) {
                return null;
            }
            R r = (R) Objects.requireNonNull(this.combiner.mo10358apply((Object[]) this.queue.mo10355poll()), "The combiner returned a null value");
            ((CombineLatestInnerSubscriber) mo10355poll).requestOne();
            return r;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int requestedMode) {
            boolean z = false;
            if ((requestedMode & 4) != 0) {
                return 0;
            }
            int i = requestedMode & 2;
            if (i != 0) {
                z = true;
            }
            this.outputFused = z;
            return i;
        }

        void subscribe(Publisher<? extends T>[] sources, int n) {
            CombineLatestInnerSubscriber<T>[] combineLatestInnerSubscriberArr = this.subscribers;
            for (int i = 0; i < n && !this.done && !this.cancelled; i++) {
                sources[i].subscribe(combineLatestInnerSubscriberArr[i]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class CombineLatestInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -8730235182291002949L;
        final int index;
        final int limit;
        final CombineLatestCoordinator<T, ?> parent;
        final int prefetch;
        int produced;

        CombineLatestInnerSubscriber(CombineLatestCoordinator<T, ?> parent, int index, int prefetch) {
            this.parent = parent;
            this.index = index;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.innerError(this.index, t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.parent.innerValue(this.index, t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this, s, this.prefetch);
        }

        public void requestOne() {
            int i = this.produced + 1;
            if (i == this.limit) {
                this.produced = 0;
                get().request(i);
                return;
            }
            this.produced = i;
        }
    }

    /* loaded from: classes3.dex */
    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public R mo10358apply(T t) throws Throwable {
            return FlowableCombineLatest.this.combiner.mo10358apply(new Object[]{t});
        }
    }

    public FlowableCombineLatest(@NonNull Publisher<? extends T>[] array, @NonNull Function<? super Object[], ? extends R> combiner, int bufferSize, boolean delayErrors) {
        this.array = array;
        this.iterable = null;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayErrors = delayErrors;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> s) {
        int length;
        Publisher<? extends T>[] publisherArr = this.array;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            try {
                length = 0;
                for (Publisher<? extends T> publisher : this.iterable) {
                    if (length == publisherArr.length) {
                        Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                        System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                        publisherArr = publisherArr2;
                    }
                    int i = length + 1;
                    publisherArr[length] = (Publisher) Objects.requireNonNull(publisher, "The Iterator returned a null Publisher");
                    length = i;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptySubscription.error(th, s);
                return;
            }
        } else {
            length = publisherArr.length;
        }
        if (length == 0) {
            EmptySubscription.complete(s);
        } else if (length == 1) {
            publisherArr[0].subscribe(new FlowableMap.MapSubscriber(s, new SingletonArrayFunc()));
        } else {
            CombineLatestCoordinator combineLatestCoordinator = new CombineLatestCoordinator(s, this.combiner, length, this.bufferSize, this.delayErrors);
            s.onSubscribe(combineLatestCoordinator);
            combineLatestCoordinator.subscribe(publisherArr, length);
        }
    }

    public FlowableCombineLatest(@NonNull Iterable<? extends Publisher<? extends T>> iterable, @NonNull Function<? super Object[], ? extends R> combiner, int bufferSize, boolean delayErrors) {
        this.array = null;
        this.iterable = iterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayErrors = delayErrors;
    }
}
