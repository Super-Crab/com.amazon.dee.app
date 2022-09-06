package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes5.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {
    final Iterable<? extends T> is;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;

        /* renamed from: it  reason: collision with root package name */
        private final Iterator<? extends T> f23it;
        private final Subscriber<? super T> o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it2) {
            this.o = subscriber;
            this.f23it = it2;
        }

        void fastpath() {
            Subscriber<? super T> subscriber = this.o;
            Iterator<? extends T> it2 = this.f23it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext((T) it2.next());
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        if (!it2.hasNext()) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                    return;
                }
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            if (get() == Long.MAX_VALUE) {
                return;
            }
            if (j == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                fastpath();
            } else if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
            } else {
                slowpath(j);
            }
        }

        void slowpath(long j) {
            Subscriber<? super T> subscriber = this.o;
            Iterator<? extends T> it2 = this.f23it;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 != j2) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        try {
                            subscriber.onNext((T) it2.next());
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            try {
                                if (!it2.hasNext()) {
                                    if (subscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    subscriber.onCompleted();
                                    return;
                                }
                                j3++;
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber);
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwOrReport(th2, subscriber);
                            return;
                        }
                    } else {
                        j2 = get();
                        if (j3 == j2) {
                            j2 = BackpressureUtils.produced(this, j3);
                        }
                    }
                }
            } while (j2 != 0);
        }
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable != null) {
            this.is = iterable;
            return;
        }
        throw new NullPointerException("iterable must not be null");
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            Iterator<? extends T> it2 = this.is.iterator();
            boolean hasNext = it2.hasNext();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            if (!hasNext) {
                subscriber.onCompleted();
            } else {
                subscriber.setProducer(new IterableProducer(subscriber, it2));
            }
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
