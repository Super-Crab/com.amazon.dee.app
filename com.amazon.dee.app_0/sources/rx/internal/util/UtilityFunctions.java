package rx.internal.util;

import rx.functions.Func1;
/* loaded from: classes5.dex */
public final class UtilityFunctions {

    /* loaded from: classes5.dex */
    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        /* renamed from: call */
        public Boolean mo13102call(Object obj) {
            return false;
        }
    }

    /* loaded from: classes5.dex */
    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        /* renamed from: call */
        public Boolean mo13102call(Object obj) {
            return true;
        }
    }

    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return new Func1<T, T>() { // from class: rx.internal.util.UtilityFunctions.1
            @Override // rx.functions.Func1
            /* renamed from: call */
            public T mo13102call(T t) {
                return t;
            }
        };
    }
}
