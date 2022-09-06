package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
/* loaded from: classes5.dex */
public final class OperatorTakeWhile<T> implements Observable.Operator<T, T> {
    final Func2<? super T, ? super Integer, Boolean> predicate;

    public OperatorTakeWhile(final Func1<? super T, Boolean> func1) {
        this(new Func2<T, Integer, Boolean>() { // from class: rx.internal.operators.OperatorTakeWhile.1
            @Override // rx.functions.Func2
            /* renamed from: call */
            public /* bridge */ /* synthetic */ Boolean mo13094call(Object obj, Integer num) {
                return call2((AnonymousClass1) obj, num);
            }

            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Boolean call2(T t, Integer num) {
                return (Boolean) Func1.this.mo13102call(t);
            }
        });
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeWhile(Func2<? super T, ? super Integer, Boolean> func2) {
        this.predicate = func2;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(subscriber, false) { // from class: rx.internal.operators.OperatorTakeWhile.2
            private int counter;
            private boolean done;

            @Override // rx.Observer
            public void onCompleted() {
                if (!this.done) {
                    subscriber.onCompleted();
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                if (!this.done) {
                    subscriber.onError(th);
                }
            }

            @Override // rx.Observer
            public void onNext(T t) {
                try {
                    Func2<? super T, ? super Integer, Boolean> func2 = OperatorTakeWhile.this.predicate;
                    int i = this.counter;
                    this.counter = i + 1;
                    if (func2.mo13094call(t, Integer.valueOf(i)).booleanValue()) {
                        subscriber.onNext(t);
                        return;
                    }
                    this.done = true;
                    subscriber.onCompleted();
                    unsubscribe();
                } catch (Throwable th) {
                    this.done = true;
                    Exceptions.throwOrReport(th, subscriber, t);
                    unsubscribe();
                }
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
}
