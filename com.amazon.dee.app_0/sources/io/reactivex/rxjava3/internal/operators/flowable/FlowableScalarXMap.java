package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.ScalarSubscription;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableScalarXMap {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ScalarXMapFlowable<T, R> extends Flowable<R> {
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final T value;

        ScalarXMapFlowable(T value, Function<? super T, ? extends Publisher<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }

        @Override // io.reactivex.rxjava3.core.Flowable
        public void subscribeActual(Subscriber<? super R> s) {
            try {
                Publisher publisher = (Publisher) Objects.requireNonNull(this.mapper.mo10358apply((T) this.value), "The mapper returned a null Publisher");
                if (publisher instanceof Supplier) {
                    try {
                        Object mo10365get = ((Supplier) publisher).mo10365get();
                        if (mo10365get == null) {
                            EmptySubscription.complete(s);
                            return;
                        } else {
                            s.onSubscribe(new ScalarSubscription(s, mo10365get));
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        EmptySubscription.error(th, s);
                        return;
                    }
                }
                publisher.subscribe(s);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptySubscription.error(th2, s);
            }
        }
    }

    private FlowableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Flowable<U> scalarXMap(final T value, final Function<? super T, ? extends Publisher<? extends U>> mapper) {
        return RxJavaPlugins.onAssembly(new ScalarXMapFlowable(value, mapper));
    }

    public static <T, R> boolean tryScalarXMapSubscribe(Publisher<T> source, Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> mapper) {
        if (source instanceof Supplier) {
            try {
                Object obj = (Object) ((Supplier) source).mo10365get();
                if (obj == 0) {
                    EmptySubscription.complete(subscriber);
                    return true;
                }
                try {
                    Publisher publisher = (Publisher) Objects.requireNonNull(mapper.mo10358apply(obj), "The mapper returned a null Publisher");
                    if (publisher instanceof Supplier) {
                        try {
                            Object mo10365get = ((Supplier) publisher).mo10365get();
                            if (mo10365get == null) {
                                EmptySubscription.complete(subscriber);
                                return true;
                            }
                            subscriber.onSubscribe(new ScalarSubscription(subscriber, mo10365get));
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            EmptySubscription.error(th, subscriber);
                            return true;
                        }
                    } else {
                        publisher.subscribe(subscriber);
                    }
                    return true;
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    EmptySubscription.error(th2, subscriber);
                    return true;
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                EmptySubscription.error(th3, subscriber);
                return true;
            }
        }
        return false;
    }
}
