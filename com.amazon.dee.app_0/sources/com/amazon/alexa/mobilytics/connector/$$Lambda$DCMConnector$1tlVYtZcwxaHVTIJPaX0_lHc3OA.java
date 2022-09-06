package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.event.metadata.DCMAttributeName;
import java.lang.reflect.Method;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA implements Func1 {
    public static final /* synthetic */ $$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA INSTANCE = new $$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA();

    private /* synthetic */ $$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Boolean valueOf;
        Method method = (Method) obj;
        valueOf = Boolean.valueOf(((DCMAttributeName) r1.getAnnotation(DCMAttributeName.class)).value().length() > 0);
        return valueOf;
    }
}
