package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes5.dex */
public final class OperatorElementAt<T> implements Observable.Operator<T, T> {
    final T defaultValue;
    final boolean hasDefault;
    final int index;

    /* loaded from: classes5.dex */
    static class InnerProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 1;
        final Producer actual;

        public InnerProducer(Producer producer) {
            this.actual = producer;
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i <= 0 || !compareAndSet(false, true)) {
                    return;
                }
                this.actual.request(Long.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("n >= 0 required");
        }
    }

    public OperatorElementAt(int i) {
        this(i, null, false);
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorElementAt(int i, T t) {
        this(i, t, true);
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorElementAt.1
            private int currentIndex;

            @Override // rx.Observer
            public void onCompleted() {
                int i = this.currentIndex;
                OperatorElementAt operatorElementAt = OperatorElementAt.this;
                if (i <= operatorElementAt.index) {
                    if (operatorElementAt.hasDefault) {
                        subscriber.onNext(operatorElementAt.defaultValue);
                        subscriber.onCompleted();
                        return;
                    }
                    subscriber.onError(new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline86(new StringBuilder(), OperatorElementAt.this.index, " is out of bounds")));
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                if (i == OperatorElementAt.this.index) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                    unsubscribe();
                }
            }

            @Override // rx.Subscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(new InnerProducer(producer));
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }

    private OperatorElementAt(int i, T t, boolean z) {
        if (i >= 0) {
            this.index = i;
            this.defaultValue = t;
            this.hasDefault = z;
            return;
        }
        throw new IndexOutOfBoundsException(i + " is out of bounds");
    }
}
