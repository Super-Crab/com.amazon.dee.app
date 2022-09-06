package rx.internal.operators;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;
/* loaded from: classes5.dex */
public final class OperatorSequenceEqual {
    static final Object LOCAL_ONCOMPLETED = new Object();

    private OperatorSequenceEqual() {
        throw new IllegalStateException("No instances!");
    }

    static <T> Observable<Object> materializeLite(Observable<T> observable) {
        return Observable.concat(observable.map((Func1<T, Object>) new Func1<T, Object>() { // from class: rx.internal.operators.OperatorSequenceEqual.1
            @Override // rx.functions.Func1
            /* renamed from: call */
            public Object mo13102call(T t) {
                return t;
            }
        }), Observable.just(LOCAL_ONCOMPLETED));
    }

    public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> observable, Observable<? extends T> observable2, final Func2<? super T, ? super T, Boolean> func2) {
        return Observable.zip(materializeLite(observable), materializeLite(observable2), new Func2<Object, Object, Boolean>() { // from class: rx.internal.operators.OperatorSequenceEqual.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // rx.functions.Func2
            /* renamed from: call */
            public Boolean mo13094call(Object obj, Object obj2) {
                boolean z = obj == OperatorSequenceEqual.LOCAL_ONCOMPLETED;
                boolean z2 = obj2 == OperatorSequenceEqual.LOCAL_ONCOMPLETED;
                if (!z || !z2) {
                    if (!z && !z2) {
                        return (Boolean) Func2.this.mo13094call(obj, obj2);
                    }
                    return false;
                }
                return true;
            }
        }).all(UtilityFunctions.identity());
    }
}
