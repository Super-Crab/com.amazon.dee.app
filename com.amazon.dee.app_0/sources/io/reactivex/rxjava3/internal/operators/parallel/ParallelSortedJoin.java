package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.innerError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public /* bridge */ /* synthetic */ void onNext(Object t) {
            onNext((List) ((List) t));
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this, s, Long.MAX_VALUE);
        }

        public void onNext(List<T> t) {
            this.parent.innerNext(t, this.index);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final int[] indexes;
        final List<T>[] lists;
        final SortedJoinInnerSubscriber<T>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        SortedJoinSubscription(Subscriber<? super T> actual, int n, Comparator<? super T> comparator) {
            this.downstream = actual;
            this.comparator = comparator;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[n];
            for (int i = 0; i < n; i++) {
                sortedJoinInnerSubscriberArr[i] = new SortedJoinInnerSubscriber<>(this, i);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[n];
            this.indexes = new int[n];
            this.remaining.lazySet(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() != 0) {
                    return;
                }
                Arrays.fill(this.lists, (Object) null);
            }
        }

        void cancelAll() {
            for (SortedJoinInnerSubscriber<T> sortedJoinInnerSubscriber : this.subscribers) {
                sortedJoinInnerSubscriber.cancel();
            }
        }

        void drain() {
            boolean z;
            T t;
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.downstream;
            List<T>[] listArr = this.lists;
            int[] iArr = this.indexes;
            int length = iArr.length;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        Arrays.fill(listArr, (Object) null);
                        return;
                    }
                    Throwable th = this.error.get();
                    if (th != null) {
                        cancelAll();
                        Arrays.fill(listArr, (Object) null);
                        subscriber.onError(th);
                        return;
                    }
                    int i2 = -1;
                    int i3 = 0;
                    Object obj = (Object) null;
                    while (i3 < length) {
                        List<T> list = listArr[i3];
                        int i4 = iArr[i3];
                        if (list.size() != i4) {
                            if (obj == null) {
                                t = list.get(i4);
                            } else {
                                t = list.get(i4);
                                try {
                                    if (!(this.comparator.compare(obj, t) > 0)) {
                                    }
                                } catch (Throwable th2) {
                                    Exceptions.throwIfFatal(th2);
                                    cancelAll();
                                    Arrays.fill(listArr, (Object) null);
                                    if (!this.error.compareAndSet(null, th2)) {
                                        RxJavaPlugins.onError(th2);
                                    }
                                    subscriber.onError(this.error.get());
                                    return;
                                }
                            }
                            obj = (Object) t;
                            i2 = i3;
                        }
                        i3++;
                        obj = obj;
                    }
                    if (obj == null) {
                        Arrays.fill(listArr, (Object) null);
                        subscriber.onComplete();
                        return;
                    }
                    subscriber.onNext(obj);
                    iArr[i2] = iArr[i2] + 1;
                    j2++;
                }
                if (this.cancelled) {
                    Arrays.fill(listArr, (Object) null);
                    return;
                }
                Throwable th3 = this.error.get();
                if (th3 != null) {
                    cancelAll();
                    Arrays.fill(listArr, (Object) null);
                    subscriber.onError(th3);
                    return;
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        z = true;
                        break;
                    } else if (iArr[i5] != listArr[i5].size()) {
                        z = false;
                        break;
                    } else {
                        i5++;
                    }
                }
                if (z) {
                    Arrays.fill(listArr, (Object) null);
                    subscriber.onComplete();
                    return;
                }
                if (j2 != 0) {
                    BackpressureHelper.produced(this.requested, j2);
                }
                i = addAndGet(-i);
            } while (i != 0);
        }

        void innerError(Throwable e) {
            if (this.error.compareAndSet(null, e)) {
                drain();
            } else if (e == this.error.get()) {
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        void innerNext(List<T> value, int index) {
            this.lists[index] = value;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                if (this.remaining.get() != 0) {
                    return;
                }
                drain();
            }
        }
    }

    public ParallelSortedJoin(ParallelFlowable<List<T>> source, Comparator<? super T> comparator) {
        this.source = source;
        this.comparator = comparator;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(s, this.source.parallelism(), this.comparator);
        s.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }
}
