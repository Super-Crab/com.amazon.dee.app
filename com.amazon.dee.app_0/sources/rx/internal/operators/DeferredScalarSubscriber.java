package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes5.dex */
public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T> {
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 1;
    static final int NO_REQUEST_HAS_VALUE = 2;
    static final int NO_REQUEST_NO_VALUE = 0;
    protected final Subscriber<? super R> actual;
    protected boolean hasValue;
    final AtomicInteger state = new AtomicInteger();
    protected R value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class InnerProducer implements Producer {
        final DeferredScalarSubscriber<?, ?> parent;

        public InnerProducer(DeferredScalarSubscriber<?, ?> deferredScalarSubscriber) {
            this.parent = deferredScalarSubscriber;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.parent.downstreamRequest(j);
        }
    }

    public DeferredScalarSubscriber(Subscriber<? super R> subscriber) {
        this.actual = subscriber;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void complete() {
        this.actual.onCompleted();
    }

    final void downstreamRequest(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            if (i == 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.actual;
            do {
                int i2 = this.state.get();
                if (i2 == 1 || i2 == 3 || subscriber.isUnsubscribed()) {
                    return;
                }
                if (i2 == 2) {
                    if (!this.state.compareAndSet(2, 3)) {
                        return;
                    }
                    subscriber.onNext((R) this.value);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onCompleted();
                    return;
                }
            } while (!this.state.compareAndSet(0, 1));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= 0 required but it was ", j));
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            complete();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.value = null;
        this.actual.onError(th);
    }

    @Override // rx.Subscriber
    public final void setProducer(Producer producer) {
        producer.request(Long.MAX_VALUE);
    }

    final void setupDownstream() {
        Subscriber<? super R> subscriber = this.actual;
        subscriber.add(this);
        subscriber.setProducer(new InnerProducer(this));
    }

    public final void subscribeTo(Observable<? extends T> observable) {
        setupDownstream();
        observable.unsafeSubscribe(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void complete(R r) {
        Subscriber<? super R> subscriber = this.actual;
        do {
            int i = this.state.get();
            if (i == 2 || i == 3 || subscriber.isUnsubscribed()) {
                return;
            }
            if (i == 1) {
                subscriber.onNext(r);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
                this.state.lazySet(3);
                return;
            }
            this.value = r;
        } while (!this.state.compareAndSet(0, 2));
    }
}
