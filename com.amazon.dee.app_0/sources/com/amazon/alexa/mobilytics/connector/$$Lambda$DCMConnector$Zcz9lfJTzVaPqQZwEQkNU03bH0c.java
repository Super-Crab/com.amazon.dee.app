package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.event.metadata.DCMAttributeName;
import java.lang.reflect.Method;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c implements Func1 {
    public static final /* synthetic */ $$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c INSTANCE = new $$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c();

    private /* synthetic */ $$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Boolean valueOf;
        valueOf = Boolean.valueOf(((Method) obj).isAnnotationPresent(DCMAttributeName.class));
        return valueOf;
    }
}
