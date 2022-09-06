package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableRange extends Flowable<Integer> {
    final int end;
    final int start;

    /* loaded from: classes3.dex */
    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Integer> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final int end;
        int index;

        BaseRangeSubscription(int index, int end) {
            this.index = index;
            this.end = end;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.end;
        }

        abstract void fastPath();

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.end;
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

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public final Integer mo10355poll() {
            int i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Integer.valueOf(i);
        }
    }

    /* loaded from: classes3.dex */
    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Integer> downstream;

        RangeConditionalSubscription(ConditionalSubscriber<? super Integer> actual, int index, int end) {
            super(index, end);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        void fastPath() {
            int i = this.end;
            ConditionalSubscriber<? super Integer> conditionalSubscriber = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (this.cancelled) {
                    return;
                }
                conditionalSubscriber.tryOnNext(Integer.valueOf(i2));
            }
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        void slowPath(long r) {
            int i = this.end;
            int i2 = this.index;
            ConditionalSubscriber<? super Integer> conditionalSubscriber = this.downstream;
            long j = r;
            do {
                long j2 = 0;
                while (true) {
                    if (j2 == j || i2 == i) {
                        if (i2 == i) {
                            if (this.cancelled) {
                                return;
                            }
                            conditionalSubscriber.onComplete();
                            return;
                        }
                        j = get();
                        if (j2 == j) {
                            this.index = i2;
                            j = addAndGet(-j2);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        if (conditionalSubscriber.tryOnNext(Integer.valueOf(i2))) {
                            j2++;
                        }
                        i2++;
                    }
                }
            } while (j != 0);
        }
    }

    /* loaded from: classes3.dex */
    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Integer> downstream;

        RangeSubscription(Subscriber<? super Integer> actual, int index, int end) {
            super(index, end);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        void fastPath() {
            int i = this.end;
            Subscriber<? super Integer> subscriber = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (this.cancelled) {
                    return;
                }
                subscriber.onNext(Integer.valueOf(i2));
            }
            if (this.cancelled) {
                return;
            }
            subscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        void slowPath(long r) {
            int i = this.end;
            int i2 = this.index;
            Subscriber<? super Integer> subscriber = this.downstream;
            long j = r;
            do {
                long j2 = 0;
                while (true) {
                    if (j2 == j || i2 == i) {
                        if (i2 == i) {
                            if (this.cancelled) {
                                return;
                            }
                            subscriber.onComplete();
                            return;
                        }
                        j = get();
                        if (j2 == j) {
                            this.index = i2;
                            j = addAndGet(-j2);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        subscriber.onNext(Integer.valueOf(i2));
                        j2++;
                        i2++;
                    }
                }
            } while (j != 0);
        }
    }

    public FlowableRange(int start, int count) {
        this.start = start;
        this.end = start + count;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Integer> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) s, this.start, this.end));
        } else {
            s.onSubscribe(new RangeSubscription(s, this.start, this.end));
        }
    }
}
