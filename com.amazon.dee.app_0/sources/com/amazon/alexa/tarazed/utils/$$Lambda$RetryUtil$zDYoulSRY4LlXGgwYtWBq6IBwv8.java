package com.amazon.alexa.tarazed.utils;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.tarazed.utils.-$$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8 implements Func1 {
    public static final /* synthetic */ $$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8 INSTANCE = new $$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8();

    private /* synthetic */ $$Lambda$RetryUtil$zDYoulSRY4LlXGgwYtWBq6IBwv8() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Observable timer;
        Integer num = (Integer) obj;
        timer = Observable.timer((long) Math.floor((Math.pow(2.0d, num.intValue()) - 1.0d) / 2.0d), TimeUnit.SECONDS);
        return timer;
    }
}
