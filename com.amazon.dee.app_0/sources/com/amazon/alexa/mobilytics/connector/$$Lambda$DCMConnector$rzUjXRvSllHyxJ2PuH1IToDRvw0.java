package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.event.metadata.DCMAttributeMap;
import java.lang.reflect.Method;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0 implements Func1 {
    public static final /* synthetic */ $$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0 INSTANCE = new $$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0();

    private /* synthetic */ $$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Boolean valueOf;
        valueOf = Boolean.valueOf(((Method) obj).isAnnotationPresent(DCMAttributeMap.class));
        return valueOf;
    }
}
