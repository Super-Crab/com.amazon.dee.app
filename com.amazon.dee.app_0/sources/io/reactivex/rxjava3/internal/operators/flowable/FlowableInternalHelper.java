package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class FlowableInternalHelper {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class BufferedReplaySupplier<T> implements Supplier<ConnectableFlowable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Flowable<T> parent;

        BufferedReplaySupplier(Flowable<T> parent, int bufferSize, boolean eagerTruncate) {
            this.parent = parent;
            this.bufferSize = bufferSize;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableFlowable<T> mo10365get() {
            return this.parent.replay(this.bufferSize, this.eagerTruncate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class BufferedTimedReplay<T> implements Supplier<ConnectableFlowable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Flowable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        BufferedTimedReplay(Flowable<T> parent, int bufferSize, long time, TimeUnit unit, Scheduler scheduler, boolean eagerTruncate) {
            this.parent = parent;
            this.bufferSize = bufferSize;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableFlowable<T> mo10365get() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class FlatMapIntoIterable<T, U> implements Function<T, Publisher<U>> {
        private final Function<? super T, ? extends Iterable<? extends U>> mapper;

        FlatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
            this.mapper = mapper;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public /* bridge */ /* synthetic */ Object mo10358apply(Object t) throws Throwable {
            return mo10358apply((FlatMapIntoIterable<T, U>) t);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public Publisher<U> mo10358apply(T t) throws Throwable {
            return new FlowableFromIterable((Iterable) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null Iterable"));
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
    public static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, Publisher<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends Publisher<? extends U>> mapper;

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> combiner, Function<? super T, ? extends Publisher<? extends U>> mapper) {
            this.combiner = combiner;
            this.mapper = mapper;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public /* bridge */ /* synthetic */ Object mo10358apply(final Object t) throws Throwable {
            return mo10358apply((FlatMapWithCombinerOuter<T, R, U>) t);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public Publisher<R> mo10358apply(final T t) throws Throwable {
            return new FlowableMapPublisher((Publisher) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null Publisher"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ItemDelayFunction<T, U> implements Function<T, Publisher<T>> {
        final Function<? super T, ? extends Publisher<U>> itemDelay;

        ItemDelayFunction(Function<? super T, ? extends Publisher<U>> itemDelay) {
            this.itemDelay = itemDelay;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply */
        public /* bridge */ /* synthetic */ Object mo10358apply(final Object v) throws Throwable {
            return mo10358apply((ItemDelayFunction<T, U>) v);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: collision with other method in class */
        public Publisher<T> mo10358apply(final T v) throws Throwable {
            return new FlowableTakePublisher((Publisher) Objects.requireNonNull(this.itemDelay.mo10358apply(v), "The itemDelay returned a null Publisher"), 1L).map(Functions.justFunction(v)).defaultIfEmpty(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ReplaySupplier<T> implements Supplier<ConnectableFlowable<T>> {
        final Flowable<T> parent;

        ReplaySupplier(Flowable<T> parent) {
            this.parent = parent;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableFlowable<T> mo10365get() {
            return this.parent.replay();
        }
    }

    /* loaded from: classes3.dex */
    public enum RequestMax implements Consumer<Subscription> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Subscription t) {
            t.request(Long.MAX_VALUE);
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
    public static final class SubscriberOnComplete<T> implements Action {
        final Subscriber<T> subscriber;

        SubscriberOnComplete(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.subscriber.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SubscriberOnError<T> implements Consumer<Throwable> {
        final Subscriber<T> subscriber;

        SubscriberOnError(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable v) {
            this.subscriber.onError(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SubscriberOnNext<T> implements Consumer<T> {
        final Subscriber<T> subscriber;

        SubscriberOnNext(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T v) {
            this.subscriber.onNext(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class TimedReplay<T> implements Supplier<ConnectableFlowable<T>> {
        final boolean eagerTruncate;
        private final Flowable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        TimedReplay(Flowable<T> parent, long time, TimeUnit unit, Scheduler scheduler, boolean eagerTruncate) {
            this.parent = parent;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.eagerTruncate = eagerTruncate;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: get */
        public ConnectableFlowable<T> mo10365get() {
            return this.parent.replay(this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    private FlowableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Function<T, Publisher<U>> flatMapIntoIterable(final Function<? super T, ? extends Iterable<? extends U>> mapper) {
        return new FlatMapIntoIterable(mapper);
    }

    public static <T, U, R> Function<T, Publisher<R>> flatMapWithCombiner(final Function<? super T, ? extends Publisher<? extends U>> mapper, final BiFunction<? super T, ? super U, ? extends R> combiner) {
        return new FlatMapWithCombinerOuter(combiner, mapper);
    }

    public static <T, U> Function<T, Publisher<T>> itemDelay(final Function<? super T, ? extends Publisher<U>> itemDelay) {
        return new ItemDelayFunction(itemDelay);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(final Flowable<T> parent) {
        return new ReplaySupplier(parent);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> consumer) {
        return new SimpleBiGenerator(consumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new SimpleGenerator(consumer);
    }

    public static <T> Action subscriberOnComplete(Subscriber<T> subscriber) {
        return new SubscriberOnComplete(subscriber);
    }

    public static <T> Consumer<Throwable> subscriberOnError(Subscriber<T> subscriber) {
        return new SubscriberOnError(subscriber);
    }

    public static <T> Consumer<T> subscriberOnNext(Subscriber<T> subscriber) {
        return new SubscriberOnNext(subscriber);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(final Flowable<T> parent, final int bufferSize, boolean eagerTruncate) {
        return new BufferedReplaySupplier(parent, bufferSize, eagerTruncate);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(final Flowable<T> parent, final int bufferSize, final long time, final TimeUnit unit, final Scheduler scheduler, boolean eagerTruncate) {
        return new BufferedTimedReplay(parent, bufferSize, time, unit, scheduler, eagerTruncate);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(final Flowable<T> parent, final long time, final TimeUnit unit, final Scheduler scheduler, boolean eagerTruncate) {
        return new TimedReplay(parent, time, unit, scheduler, eagerTruncate);
    }
}
