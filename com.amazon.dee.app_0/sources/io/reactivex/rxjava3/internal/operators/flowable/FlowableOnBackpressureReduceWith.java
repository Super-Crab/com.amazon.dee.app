package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Objects;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureReduceWith<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<R, ? super T, R> reducer;
    final Supplier<R> supplier;

    /* loaded from: classes3.dex */
    static final class BackpressureReduceWithSubscriber<T, R> extends AbstractBackpressureThrottlingSubscriber<T, R> {
        private static final long serialVersionUID = 8255923705960622424L;
        final BiFunction<R, ? super T, R> reducer;
        final Supplier<R> supplier;

        BackpressureReduceWithSubscriber(@NonNull Subscriber<? super R> downstream, @NonNull Supplier<R> supplier, @NonNull BiFunction<R, ? super T, R> reducer) {
            super(downstream);
            this.reducer = reducer;
            this.supplier = supplier;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            try {
                if (this.current.get() == null) {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(Objects.requireNonNull(this.supplier.mo10365get(), "The supplier returned a null value"), t), "The reducer returned a null value"));
                } else {
                    R andSet = this.current.getAndSet(null);
                    if (andSet != null) {
                        this.current.lazySet(Objects.requireNonNull(this.reducer.apply(andSet, t), "The reducer returned a null value"));
                    } else {
                        this.current.lazySet(Objects.requireNonNull(this.reducer.apply(Objects.requireNonNull(this.supplier.mo10365get(), "The supplier returned a null value"), t), "The reducer returned a null value"));
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
                cancel();
            }
            drain();
        }
    }

    public FlowableOnBackpressureReduceWith(@NonNull Flowable<T> source, @NonNull Supplier<R> supplier, @NonNull BiFunction<R, ? super T, R> reducer) {
        super(source);
        this.reducer = reducer;
        this.supplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(@NonNull Subscriber<? super R> s) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceWithSubscriber(s, this.supplier, this.reducer));
    }
}
