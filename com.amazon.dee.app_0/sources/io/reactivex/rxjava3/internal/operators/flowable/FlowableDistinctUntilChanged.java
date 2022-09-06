package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableDistinctUntilChanged<T, K> extends AbstractFlowableWithUpstream<T, T> {
    final BiPredicate<? super K, ? super K> comparer;
    final Function<? super T, K> keySelector;

    /* loaded from: classes3.dex */
    static final class DistinctUntilChangedConditionalSubscriber<T, K> extends BasicFuseableConditionalSubscriber<T, T> {
        final BiPredicate<? super K, ? super K> comparer;
        boolean hasValue;
        final Function<? super T, K> keySelector;
        K last;

        DistinctUntilChangedConditionalSubscriber(ConditionalSubscriber<? super T> actual, Function<? super T, K> keySelector, BiPredicate<? super K, ? super K> comparer) {
            super(actual);
            this.keySelector = keySelector;
            this.comparer = comparer;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.request(1L);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public T mo10355poll() throws Throwable {
            while (true) {
                T mo10355poll = this.qs.mo10355poll();
                if (mo10355poll == null) {
                    return null;
                }
                K mo10358apply = this.keySelector.mo10358apply(mo10355poll);
                if (!this.hasValue) {
                    this.hasValue = true;
                    this.last = mo10358apply;
                    return mo10355poll;
                } else if (!this.comparer.test((K) this.last, mo10358apply)) {
                    this.last = mo10358apply;
                    return mo10355poll;
                } else {
                    this.last = mo10358apply;
                    if (this.sourceMode != 1) {
                        this.upstream.request(1L);
                    }
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            if (this.sourceMode != 0) {
                return this.downstream.tryOnNext(t);
            }
            try {
                K mo10358apply = this.keySelector.mo10358apply(t);
                if (this.hasValue) {
                    boolean test = this.comparer.test((K) this.last, mo10358apply);
                    this.last = mo10358apply;
                    if (test) {
                        return false;
                    }
                } else {
                    this.hasValue = true;
                    this.last = mo10358apply;
                }
                this.downstream.onNext(t);
                return true;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }
    }

    /* loaded from: classes3.dex */
    static final class DistinctUntilChangedSubscriber<T, K> extends BasicFuseableSubscriber<T, T> implements ConditionalSubscriber<T> {
        final BiPredicate<? super K, ? super K> comparer;
        boolean hasValue;
        final Function<? super T, K> keySelector;
        K last;

        DistinctUntilChangedSubscriber(Subscriber<? super T> actual, Function<? super T, K> keySelector, BiPredicate<? super K, ? super K> comparer) {
            super(actual);
            this.keySelector = keySelector;
            this.comparer = comparer;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.request(1L);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public T mo10355poll() throws Throwable {
            while (true) {
                T mo10355poll = this.qs.mo10355poll();
                if (mo10355poll == null) {
                    return null;
                }
                K mo10358apply = this.keySelector.mo10358apply(mo10355poll);
                if (!this.hasValue) {
                    this.hasValue = true;
                    this.last = mo10358apply;
                    return mo10355poll;
                } else if (!this.comparer.test((K) this.last, mo10358apply)) {
                    this.last = mo10358apply;
                    return mo10355poll;
                } else {
                    this.last = mo10358apply;
                    if (this.sourceMode != 1) {
                        this.upstream.request(1L);
                    }
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(t);
                return true;
            }
            try {
                K mo10358apply = this.keySelector.mo10358apply(t);
                if (this.hasValue) {
                    boolean test = this.comparer.test((K) this.last, mo10358apply);
                    this.last = mo10358apply;
                    if (test) {
                        return false;
                    }
                } else {
                    this.hasValue = true;
                    this.last = mo10358apply;
                }
                this.downstream.onNext(t);
                return true;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }
    }

    public FlowableDistinctUntilChanged(Flowable<T> source, Function<? super T, K> keySelector, BiPredicate<? super K, ? super K> comparer) {
        super(source);
        this.keySelector = keySelector;
        this.comparer = comparer;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        if (s instanceof ConditionalSubscriber) {
            this.source.subscribe((FlowableSubscriber) new DistinctUntilChangedConditionalSubscriber((ConditionalSubscriber) s, this.keySelector, this.comparer));
        } else {
            this.source.subscribe((FlowableSubscriber) new DistinctUntilChangedSubscriber(s, this.keySelector, this.comparer));
        }
    }
}
