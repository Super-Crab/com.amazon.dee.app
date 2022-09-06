package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.parallel.ParallelFailureHandling;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class ParallelFilterTry<T> extends ParallelFlowable<T> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Predicate<? super T> predicate;
    final ParallelFlowable<T> source;

    /* renamed from: io.reactivex.rxjava3.internal.operators.parallel.ParallelFilterTry$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling = new int[ParallelFailureHandling.values().length];

        static {
            try {
                $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[ParallelFailureHandling.RETRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[ParallelFailureHandling.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[ParallelFailureHandling.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class BaseFilterSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Predicate<? super T> predicate;
        Subscription upstream;

        BaseFilterSubscriber(Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            this.predicate = predicate;
            this.errorHandler = errorHandler;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.upstream.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onNext(T t) {
            if (tryOnNext(t) || this.done) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            this.upstream.request(n);
        }
    }

    /* loaded from: classes3.dex */
    static final class ParallelFilterConditionalSubscriber<T> extends BaseFilterSubscriber<T> {
        final ConditionalSubscriber<? super T> downstream;

        ParallelFilterConditionalSubscriber(ConditionalSubscriber<? super T> actual, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            super(predicate, errorHandler);
            this.downstream = actual;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.downstream.onError(t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (!this.done) {
                long j = 0;
                while (true) {
                    try {
                        return this.predicate.test(t) && this.downstream.tryOnNext(t);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        try {
                            j++;
                            int ordinal = ((ParallelFailureHandling) Objects.requireNonNull(this.errorHandler.apply(Long.valueOf(j), th), "The errorHandler returned a null ParallelFailureHandling")).ordinal();
                            if (ordinal == 0) {
                                cancel();
                                onComplete();
                                return false;
                            }
                            if (ordinal != 2) {
                                if (ordinal != 3) {
                                    cancel();
                                    onError(th);
                                    break;
                                }
                            } else {
                                break;
                            }
                            return false;
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            cancel();
                            onError(new CompositeException(th, th2));
                        }
                    }
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes3.dex */
    static final class ParallelFilterSubscriber<T> extends BaseFilterSubscriber<T> {
        final Subscriber<? super T> downstream;

        ParallelFilterSubscriber(Subscriber<? super T> actual, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            super(predicate, errorHandler);
            this.downstream = actual;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.downstream.onError(t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (!this.done) {
                long j = 0;
                while (true) {
                    try {
                        if (!this.predicate.test(t)) {
                            return false;
                        }
                        this.downstream.onNext(t);
                        return true;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        try {
                            j++;
                            int ordinal = ((ParallelFailureHandling) Objects.requireNonNull(this.errorHandler.apply(Long.valueOf(j), th), "The errorHandler returned a null ParallelFailureHandling")).ordinal();
                            if (ordinal == 0) {
                                cancel();
                                onComplete();
                                return false;
                            }
                            if (ordinal != 2) {
                                if (ordinal != 3) {
                                    cancel();
                                    onError(th);
                                    break;
                                }
                            } else {
                                break;
                            }
                            return false;
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            cancel();
                            onError(new CompositeException(th, th2));
                        }
                    }
                }
                return false;
            }
            return false;
        }
    }

    public ParallelFilterTry(ParallelFlowable<T> source, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
        this.source = source;
        this.predicate = predicate;
        this.errorHandler = errorHandler;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.source.parallelism();
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscribers) {
        if (!validate(subscribers)) {
            return;
        }
        int length = subscribers.length;
        Subscriber<? super T>[] subscriberArr = new Subscriber[length];
        for (int i = 0; i < length; i++) {
            Subscriber<? super T> subscriber = subscribers[i];
            if (subscriber instanceof ConditionalSubscriber) {
                subscriberArr[i] = new ParallelFilterConditionalSubscriber((ConditionalSubscriber) subscriber, this.predicate, this.errorHandler);
            } else {
                subscriberArr[i] = new ParallelFilterSubscriber(subscriber, this.predicate, this.errorHandler);
            }
        }
        this.source.subscribe(subscriberArr);
    }
}
