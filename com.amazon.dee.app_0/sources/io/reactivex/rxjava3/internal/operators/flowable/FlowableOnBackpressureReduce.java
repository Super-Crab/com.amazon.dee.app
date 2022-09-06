package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.Objects;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureReduce<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiFunction<T, T, T> reducer;

    /* loaded from: classes3.dex */
    static final class BackpressureReduceSubscriber<T> extends AbstractBackpressureThrottlingSubscriber<T, T> {
        private static final long serialVersionUID = 821363947659780367L;
        final BiFunction<T, T, T> reducer;

        BackpressureReduceSubscriber(@NonNull Subscriber<? super T> downstream, @NonNull BiFunction<T, T, T> reducer) {
            super(downstream);
            this.reducer = reducer;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.current.get() == null) {
                this.current.lazySet(t);
            } else {
                Object andSet = this.current.getAndSet(null);
                if (andSet != null) {
                    try {
                        this.current.lazySet(Objects.requireNonNull(this.reducer.apply(andSet, t), "The reducer returned a null value"));
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        onError(th);
                        cancel();
                        return;
                    }
                } else {
                    this.current.lazySet(t);
                }
            }
            drain();
        }
    }

    public FlowableOnBackpressureReduce(@NonNull Flowable<T> source, @NonNull BiFunction<T, T, T> reducer) {
        super(source);
        this.reducer = reducer;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(@NonNull Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceSubscriber(s, this.reducer));
    }
}
