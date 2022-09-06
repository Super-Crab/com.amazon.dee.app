package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
/* loaded from: classes5.dex */
public final class OperatorMapNotification<T, R> implements Observable.Operator<R, T> {
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class MapNotificationSubscriber<T, R> extends Subscriber<T> {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        R value;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong missedRequested = new AtomicLong();
        final AtomicReference<Producer> producer = new AtomicReference<>();

        public MapNotificationSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
            this.actual = subscriber;
            this.onNext = func1;
            this.onError = func12;
            this.onCompleted = func0;
        }

        void accountProduced() {
            long j = this.produced;
            if (j == 0 || this.producer.get() == null) {
                return;
            }
            BackpressureUtils.produced(this.requested, j);
        }

        @Override // rx.Observer
        public void onCompleted() {
            accountProduced();
            try {
                this.value = this.onCompleted.mo13098call();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual);
            }
            tryEmit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            accountProduced();
            try {
                this.value = this.onError.mo13102call(th);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.actual, th);
            }
            tryEmit();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.produced++;
                this.actual.onNext((R) this.onNext.mo13102call(t));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual, t);
            }
        }

        void requestInner(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i == 0) {
                    return;
                }
                while (true) {
                    long j2 = this.requested.get();
                    if ((j2 & Long.MIN_VALUE) != 0) {
                        long j3 = Long.MAX_VALUE & j2;
                        if (this.requested.compareAndSet(j2, Long.MIN_VALUE | BackpressureUtils.addCap(j3, j))) {
                            if (j3 != 0) {
                                return;
                            }
                            if (!this.actual.isUnsubscribed()) {
                                this.actual.onNext((R) this.value);
                            }
                            if (this.actual.isUnsubscribed()) {
                                return;
                            }
                            this.actual.onCompleted();
                            return;
                        }
                    } else {
                        if (this.requested.compareAndSet(j2, BackpressureUtils.addCap(j2, j))) {
                            AtomicReference<Producer> atomicReference = this.producer;
                            Producer producer = atomicReference.get();
                            if (producer != null) {
                                producer.request(j);
                                return;
                            }
                            BackpressureUtils.getAndAddRequest(this.missedRequested, j);
                            Producer producer2 = atomicReference.get();
                            if (producer2 == null) {
                                return;
                            }
                            long andSet = this.missedRequested.getAndSet(0L);
                            if (andSet == 0) {
                                return;
                            }
                            producer2.request(andSet);
                            return;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= 0 required but it was ", j));
            }
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            if (this.producer.compareAndSet(null, producer)) {
                long andSet = this.missedRequested.getAndSet(0L);
                if (andSet == 0) {
                    return;
                }
                producer.request(andSet);
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void tryEmit() {
            long j;
            do {
                j = this.requested.get();
                if ((j & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.requested.compareAndSet(j, Long.MIN_VALUE | j));
            if (j != 0 || this.producer.get() == null) {
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onNext((R) this.value);
                }
                if (this.actual.isUnsubscribed()) {
                    return;
                }
                this.actual.onCompleted();
            }
        }
    }

    public OperatorMapNotification(Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
        this.onNext = func1;
        this.onError = func12;
        this.onCompleted = func0;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final MapNotificationSubscriber mapNotificationSubscriber = new MapNotificationSubscriber(subscriber, this.onNext, this.onError, this.onCompleted);
        subscriber.add(mapNotificationSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMapNotification.1
            @Override // rx.Producer
            public void request(long j) {
                mapNotificationSubscriber.requestInner(j);
            }
        });
        return mapNotificationSubscriber;
    }
}
