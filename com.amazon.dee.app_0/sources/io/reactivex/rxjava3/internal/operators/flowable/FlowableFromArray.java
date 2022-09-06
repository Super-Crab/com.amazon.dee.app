package io.reactivex.rxjava3.internal.operators.flowable;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.Objects;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableFromArray<T> extends Flowable<T> {
    final T[] array;

    /* loaded from: classes3.dex */
    static final class ArrayConditionalSubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super T> downstream;

        ArrayConditionalSubscription(ConditionalSubscriber<? super T> actual, T[] array) {
            super(array);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = tArr[i];
                if (t == null) {
                    conditionalSubscriber.onError(new NullPointerException(GeneratedOutlineSupport1.outline52("The element at index ", i, " is null")));
                    return;
                }
                conditionalSubscriber.tryOnNext(t);
            }
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void slowPath(long r) {
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
            do {
                long j = r;
                long j2 = 0;
                while (true) {
                    if (j2 == j || i == length) {
                        if (i == length) {
                            if (this.cancelled) {
                                return;
                            }
                            conditionalSubscriber.onComplete();
                            return;
                        }
                        j = get();
                        if (j2 == j) {
                            this.index = i;
                            r = addAndGet(-j2);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        T t = tArr[i];
                        if (t == null) {
                            conditionalSubscriber.onError(new NullPointerException(GeneratedOutlineSupport1.outline52("The element at index ", i, " is null")));
                            return;
                        }
                        if (conditionalSubscriber.tryOnNext(t)) {
                            j2++;
                        }
                        i++;
                    }
                }
            } while (r != 0);
        }
    }

    /* loaded from: classes3.dex */
    static final class ArraySubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super T> downstream;

        ArraySubscription(Subscriber<? super T> actual, T[] array) {
            super(array);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void fastPath() {
            T[] tArr = this.array;
            int length = tArr.length;
            Subscriber<? super T> subscriber = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (this.cancelled) {
                    return;
                }
                T t = tArr[i];
                if (t == null) {
                    subscriber.onError(new NullPointerException(GeneratedOutlineSupport1.outline52("The element at index ", i, " is null")));
                    return;
                }
                subscriber.onNext(t);
            }
            if (this.cancelled) {
                return;
            }
            subscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        void slowPath(long r) {
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            Subscriber<? super T> subscriber = this.downstream;
            do {
                long j = r;
                long j2 = 0;
                while (true) {
                    if (j2 == j || i == length) {
                        if (i == length) {
                            if (this.cancelled) {
                                return;
                            }
                            subscriber.onComplete();
                            return;
                        }
                        j = get();
                        if (j2 == j) {
                            this.index = i;
                            r = addAndGet(-j2);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        T t = tArr[i];
                        if (t == null) {
                            subscriber.onError(new NullPointerException(GeneratedOutlineSupport1.outline52("The element at index ", i, " is null")));
                            return;
                        }
                        subscriber.onNext(t);
                        j2++;
                        i++;
                    }
                }
            } while (r != 0);
        }
    }

    /* loaded from: classes3.dex */
    static abstract class BaseArraySubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        BaseArraySubscription(T[] array) {
            this.array = array;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.array.length;
        }

        abstract void fastPath();

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public final T mo10355poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return (T) Objects.requireNonNull(tArr[i], "array element is null");
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            if (!SubscriptionHelper.validate(n) || BackpressureHelper.add(this, n) != 0) {
                return;
            }
            if (n == Long.MAX_VALUE) {
                fastPath();
            } else {
                slowPath(n);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public final int requestFusion(int mode) {
            return mode & 1;
        }

        abstract void slowPath(long r);
    }

    public FlowableFromArray(T[] array) {
        this.array = array;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new ArrayConditionalSubscription((ConditionalSubscriber) s, this.array));
        } else {
            s.onSubscribe(new ArraySubscription(s, this.array));
        }
    }
}
