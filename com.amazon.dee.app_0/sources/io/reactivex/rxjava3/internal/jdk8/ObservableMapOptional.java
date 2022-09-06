package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver;
import java.util.Objects;
import java.util.Optional;
/* loaded from: classes3.dex */
public final class ObservableMapOptional<T, R> extends Observable<R> {
    final Function<? super T, Optional<? extends R>> mapper;
    final Observable<T> source;

    /* loaded from: classes3.dex */
    static final class MapOptionalObserver<T, R> extends BasicFuseableObserver<T, R> {
        final Function<? super T, Optional<? extends R>> mapper;

        MapOptionalObserver(Observer<? super R> downstream, Function<? super T, Optional<? extends R>> mapper) {
            super(downstream);
            this.mapper = mapper;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                Optional optional = (Optional) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null Optional");
                if (!optional.isPresent()) {
                    return;
                }
                this.downstream.onNext((Object) optional.get());
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        /* renamed from: poll */
        public R mo10355poll() throws Throwable {
            Optional optional;
            do {
                T mo10355poll = this.qd.mo10355poll();
                if (mo10355poll == null) {
                    return null;
                }
                optional = (Optional) Objects.requireNonNull(this.mapper.mo10358apply(mo10355poll), "The mapper returned a null Optional");
            } while (!optional.isPresent());
            return (R) optional.get();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }
    }

    public ObservableMapOptional(Observable<T> source, Function<? super T, Optional<? extends R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new MapOptionalObserver(observer, this.mapper));
    }
}
