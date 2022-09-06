package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes5.dex */
public final class OnSubscribeFromArray<T> implements Observable.OnSubscribe<T> {
    final T[] array;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class FromArrayProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;

        public FromArrayProducer(Subscriber<? super T> subscriber, T[] tArr) {
            this.child = subscriber;
            this.array = tArr;
        }

        void fastPath() {
            Subscriber<? super T> subscriber = this.child;
            for (T t : this.array) {
                Object obj = (Object) t;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(obj);
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (j == Long.MAX_VALUE) {
                    if (BackpressureUtils.getAndAddRequest(this, j) != 0) {
                        return;
                    }
                    fastPath();
                    return;
                } else if (i == 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
                    return;
                } else {
                    slowPath(j);
                    return;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= 0 required but it was ", j));
        }

        void slowPath(long j) {
            Subscriber<? super T> subscriber = this.child;
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            do {
                long j2 = 0;
                while (true) {
                    if (j != 0 && i != length) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext((Object) tArr[i]);
                        i++;
                        if (i == length) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                        j--;
                        j2--;
                    } else {
                        j = get() + j2;
                        if (j == 0) {
                            this.index = i;
                            j = addAndGet(j2);
                        }
                    }
                }
            } while (j != 0);
        }
    }

    public OnSubscribeFromArray(T[] tArr) {
        this.array = tArr;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.setProducer(new FromArrayProducer(subscriber, this.array));
    }
}
