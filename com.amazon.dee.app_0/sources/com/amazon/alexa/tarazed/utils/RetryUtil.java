package com.amazon.alexa.tarazed.utils;

import rx.Observable;
import rx.functions.Func1;
/* loaded from: classes10.dex */
public final class RetryUtil {
    private RetryUtil() {
    }

    public static Func1<Observable<? extends Throwable>, Observable<?>> exponentialBackoff(final int i) {
        return new Func1() { // from class: com.amazon.alexa.tarazed.utils.-$$Lambda$RetryUtil$PY1w0SBFIDjWL9ir9eVm9Js-MO0
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                Observable flatMap;
                flatMap = ((Observable) obj).zipWith(Observable.range(1, i), $$Lambda$RetryUtil$aBLnYPFbLK0ZnOjTc6txVOUGQ.INSTANCE).flatMap($$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8.INSTANCE);
                return flatMap;
            }
        };
    }

    static /* synthetic */ Integer lambda$null$0(Throwable th, Integer num) {
        return num;
    }
}
