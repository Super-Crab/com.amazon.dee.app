package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableRangeLong extends Flowable<Long> {
    final long end;
    final long start;

    /* loaded from: classes3.dex */
    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        BaseRangeSubscription(long index, long end) {
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
        public final Long mo10355poll() {
            long j = this.index;
            if (j == this.end) {
                return null;
            }
            this.index = 1 + j;
            return Long.valueOf(j);
        }
    }

    /* loaded from: classes3.dex */
    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Long> downstream;

        RangeConditionalSubscription(ConditionalSubscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long j = this.end;
            ConditionalSubscriber<? super Long> conditionalSubscriber = this.downstream;
            for (long j2 = this.index; j2 != j; j2++) {
                if (this.cancelled) {
                    return;
                }
                conditionalSubscriber.tryOnNext(Long.valueOf(j2));
            }
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void slowPath(long r) {
            long j = this.end;
            long j2 = this.index;
            ConditionalSubscriber<? super Long> conditionalSubscriber = this.downstream;
            long j3 = j2;
            long j4 = r;
            do {
                long j5 = 0;
                while (true) {
                    if (j5 == j4 || j3 == j) {
                        if (j3 == j) {
                            if (this.cancelled) {
                                return;
                            }
                            conditionalSubscriber.onComplete();
                            return;
                        }
                        j4 = get();
                        if (j5 == j4) {
                            this.index = j3;
                            j4 = addAndGet(-j5);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        if (conditionalSubscriber.tryOnNext(Long.valueOf(j3))) {
                            j5++;
                        }
                        j3++;
                    }
                }
            } while (j4 != 0);
        }
    }

    /* loaded from: classes3.dex */
    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Long> downstream;

        RangeSubscription(Subscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.downstream = actual;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long j = this.end;
            Subscriber<? super Long> subscriber = this.downstream;
            for (long j2 = this.index; j2 != j; j2++) {
                if (this.cancelled) {
                    return;
                }
                subscriber.onNext(Long.valueOf(j2));
            }
            if (this.cancelled) {
                return;
            }
            subscriber.onComplete();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void slowPath(long r) {
            long j = this.end;
            long j2 = this.index;
            Subscriber<? super Long> subscriber = this.downstream;
            long j3 = j2;
            long j4 = r;
            do {
                long j5 = 0;
                while (true) {
                    if (j5 == j4 || j3 == j) {
                        if (j3 == j) {
                            if (this.cancelled) {
                                return;
                            }
                            subscriber.onComplete();
                            return;
                        }
                        j4 = get();
                        if (j5 == j4) {
                            this.index = j3;
                            j4 = addAndGet(-j5);
                        }
                    } else if (this.cancelled) {
                        return;
                    } else {
                        subscriber.onNext(Long.valueOf(j3));
                        j5++;
                        j3++;
                    }
                }
            } while (j4 != 0);
        }
    }

    public FlowableRangeLong(long start, long count) {
        this.start = start;
        this.end = start + count;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Long> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) s, this.start, this.end));
        } else {
            s.onSubscribe(new RangeSubscription(s, this.start, this.end));
        }
    }
}
