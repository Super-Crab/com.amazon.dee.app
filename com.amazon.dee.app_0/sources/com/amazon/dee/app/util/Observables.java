package com.amazon.dee.app.util;

import rx.Observable;
import rx.functions.Func1;
/* loaded from: classes12.dex */
public final class Observables {
    private Observables() {
    }

    public static Func1<Observable<? extends Throwable>, Observable<?>> exponentialBackoff(final int i) {
        return new Func1() { // from class: com.amazon.dee.app.util.-$$Lambda$Observables$VBtYoerCwwrpWEPt_x_oLATFArI
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                Observable flatMap;
                flatMap = ((Observable) obj).zipWith(Observable.range(1, i), $$Lambda$Observables$uPIabFgH9eAfYrJNsDR2aod4T4.INSTANCE).flatMap($$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM.INSTANCE);
                return flatMap;
            }
        };
    }

    static /* synthetic */ Integer lambda$null$0(Throwable th, Integer num) {
        return num;
    }
}
