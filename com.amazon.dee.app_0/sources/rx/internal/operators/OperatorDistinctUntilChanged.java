package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;
/* loaded from: classes5.dex */
public final class OperatorDistinctUntilChanged<T, U> implements Observable.Operator<T, T>, Func2<U, U, Boolean> {
    final Func2<? super U, ? super U, Boolean> comparator;
    final Func1<? super T, ? extends U> keySelector;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class Holder {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged<>(UtilityFunctions.identity());

        Holder() {
        }
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> func1) {
        this.keySelector = func1;
        this.comparator = this;
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // rx.functions.Func2
    /* renamed from: call */
    public Boolean mo13094call(U u, U u2) {
        return Boolean.valueOf(u == u2 || (u != null && u.equals(u2)));
    }

    public OperatorDistinctUntilChanged(Func2<? super U, ? super U, Boolean> func2) {
        this.keySelector = UtilityFunctions.identity();
        this.comparator = func2;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDistinctUntilChanged.1
            boolean hasPrevious;
            U previousKey;

            @Override // rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                Object obj;
                try {
                    U mo13102call = OperatorDistinctUntilChanged.this.keySelector.mo13102call(t);
                    U u = this.previousKey;
                    this.previousKey = mo13102call;
                    if (this.hasPrevious) {
                        try {
                            if (!OperatorDistinctUntilChanged.this.comparator.mo13094call(u, mo13102call).booleanValue()) {
                                subscriber.onNext(t);
                                return;
                            } else {
                                request(1L);
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber, obj);
                            return;
                        }
                    }
                    this.hasPrevious = true;
                    subscriber.onNext(t);
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber, t);
                }
            }
        };
    }
}
