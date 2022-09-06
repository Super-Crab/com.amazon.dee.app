package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public final class ObservableInternalHelper {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class BufferedReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Observable<T> parent;

        BufferedReplaySupplier(Observable<T> parent, int bufferSize, boolean eagerTruncate) {
            this.parent = parent;
            this.bufferSize = bufferSize;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableObservable<T> mo10365get() {
            return this.parent.replay(this.bufferSize, this.eagerTruncate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class BufferedTimedReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Observable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        BufferedTimedReplaySupplier(Observable<T> parent, int bufferSize, long time, TimeUnit unit, Scheduler scheduler, boolean eagerTruncate) {
            this.parent = parent;
            this.bufferSize = bufferSize;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableObservable<T> mo10365get() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class FlatMapIntoIterable<T, U> implements Function<T, ObservableSource<U>> {
        private final Function<? super T, ? extends Iterable<? extends U>> mapper;

        FlatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
            this.mapper = mapper;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object mo10358apply(Object t) throws Throwable {
            return mo10358apply((FlatMapIntoIterable<T, U>) t);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public ObservableSource<U> mo10358apply(T t) throws Throwable {
            return new ObservableFromIterable((Iterable) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null Iterable"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class FlatMapWithCombinerInner<U, R, T> implements Function<U, R> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final T t;

        FlatMapWithCombinerInner(BiFunction<? super T, ? super U, ? extends R> combiner, T t) {
            this.combiner = combiner;
            this.t = t;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public R mo10358apply(U w) throws Throwable {
            return this.combiner.apply((T) this.t, w);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, ObservableSource<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends ObservableSource<? extends U>> mapper;

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> combiner, Function<? super T, ? extends ObservableSource<? extends U>> mapper) {
            this.combiner = combiner;
            this.mapper = mapper;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object mo10358apply(final Object t) throws Throwable {
            return mo10358apply((FlatMapWithCombinerOuter<T, R, U>) t);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public ObservableSource<R> mo10358apply(final T t) throws Throwable {
            return new ObservableMap((ObservableSource) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null ObservableSource"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ItemDelayFunction<T, U> implements Function<T, ObservableSource<T>> {
        final Function<? super T, ? extends ObservableSource<U>> itemDelay;

        ItemDelayFunction(Function<? super T, ? extends ObservableSource<U>> itemDelay) {
            this.itemDelay = itemDelay;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object mo10358apply(final Object v) throws Throwable {
            return mo10358apply((ItemDelayFunction<T, U>) v);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public ObservableSource<T> mo10358apply(final T v) throws Throwable {
            return new ObservableTake((ObservableSource) Objects.requireNonNull(this.itemDelay.mo10358apply(v), "The itemDelay returned a null ObservableSource"), 1L).map(Functions.justFunction(v)).defaultIfEmpty(v);
        }
    }

    /* loaded from: classes3.dex */
    enum MapToInt implements Function<Object, Object> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public Object mo10358apply(Object t) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ObserverOnComplete<T> implements Action {
        final Observer<T> observer;

        ObserverOnComplete(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.observer.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ObserverOnError<T> implements Consumer<Throwable> {
        final Observer<T> observer;

        ObserverOnError(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable v) {
            this.observer.onError(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ObserverOnNext<T> implements Consumer<T> {
        final Observer<T> observer;

        ObserverOnNext(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T v) {
            this.observer.onNext(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        private final Observable<T> parent;

        ReplaySupplier(Observable<T> parent) {
            this.parent = parent;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableObservable<T> mo10365get() {
            return this.parent.replay();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SimpleBiGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final BiConsumer<S, Emitter<T>> consumer;

        SimpleBiGenerator(BiConsumer<S, Emitter<T>> consumer) {
            this.consumer = consumer;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.BiFunction
        public /* bridge */ /* synthetic */ Object apply(Object t1, Object t2) throws Throwable {
            return apply((SimpleBiGenerator<T, S>) t1, (Emitter) ((Emitter) t2));
        }

        public S apply(S t1, Emitter<T> t2) throws Throwable {
            this.consumer.accept(t1, t2);
            return t1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SimpleGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final Consumer<Emitter<T>> consumer;

        SimpleGenerator(Consumer<Emitter<T>> consumer) {
            this.consumer = consumer;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.BiFunction
        public /* bridge */ /* synthetic */ Object apply(Object t1, Object t2) throws Throwable {
            return apply((SimpleGenerator<T, S>) t1, (Emitter) ((Emitter) t2));
        }

        public S apply(S t1, Emitter<T> t2) throws Throwable {
            this.consumer.accept(t2);
            return t1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class TimedReplayCallable<T> implements Supplier<ConnectableObservable<T>> {
        final boolean eagerTruncate;
        final Observable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        TimedReplayCallable(Observable<T> parent, long time, TimeUnit unit, Scheduler scheduler, boolean eagerTruncate) {
            this.parent = parent;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableObservable<T> mo10365get() {
            return this.parent.replay(this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    private ObservableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Function<T, ObservableSource<U>> flatMapIntoIterable(final Function<? super T, ? extends Iterable<? extends U>> mapper) {
        return new FlatMapIntoIterable(mapper);
    }

    public static <T, U, R> Function<T, ObservableSource<R>> flatMapWithCombiner(final Function<? super T, ? extends ObservableSource<? extends U>> mapper, final BiFunction<? super T, ? super U, ? extends R> combiner) {
        return new FlatMapWithCombinerOuter(combiner, mapper);
    }

    public static <T, U> Function<T, ObservableSource<T>> itemDelay(final Function<? super T, ? extends ObservableSource<U>> itemDelay) {
        return new ItemDelayFunction(itemDelay);
    }

    public static <T> Action observerOnComplete(Observer<T> observer) {
        return new ObserverOnComplete(observer);
    }

    public static <T> Consumer<Throwable> observerOnError(Observer<T> observer) {
        return new ObserverOnError(observer);
    }

    public static <T> Consumer<T> observerOnNext(Observer<T> observer) {
        return new ObserverOnNext(observer);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(final Observable<T> parent) {
        return new ReplaySupplier(parent);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> consumer) {
        return new SimpleBiGenerator(consumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new SimpleGenerator(consumer);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(final Observable<T> parent, final int bufferSize, boolean eagerTruncate) {
        return new BufferedReplaySupplier(parent, bufferSize, eagerTruncate);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(final Observable<T> parent, final int bufferSize, final long time, final TimeUnit unit, final Scheduler scheduler, boolean eagerTruncate) {
        return new BufferedTimedReplaySupplier(parent, bufferSize, time, unit, scheduler, eagerTruncate);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(final Observable<T> parent, final long time, final TimeUnit unit, final Scheduler scheduler, boolean eagerTruncate) {
        return new TimedReplayCallable(parent, time, unit, scheduler, eagerTruncate);
    }
}
