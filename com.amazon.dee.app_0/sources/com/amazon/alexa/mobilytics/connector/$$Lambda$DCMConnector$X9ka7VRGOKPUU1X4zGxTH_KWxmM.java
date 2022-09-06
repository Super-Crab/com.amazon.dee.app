package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.event.metadata.DCMAttributeMap;
import java.lang.reflect.Method;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM implements Func1 {
    public static final /* synthetic */ $$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM INSTANCE = new $$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM();

    private /* synthetic */ $$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Boolean valueOf;
        Method method = (Method) obj;
        valueOf = Boolean.valueOf(((DCMAttributeMap) r1.getAnnotation(DCMAttributeMap.class)).value().length() > 0);
        return valueOf;
    }
}
