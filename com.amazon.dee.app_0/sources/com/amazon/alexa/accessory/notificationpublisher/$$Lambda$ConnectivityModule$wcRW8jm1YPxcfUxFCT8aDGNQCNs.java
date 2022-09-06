package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.protocol.Input;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs implements Function {
    public static final /* synthetic */ $$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs INSTANCE = new $$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs();

    private /* synthetic */ $$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        List singletonList;
        singletonList = Collections.singletonList(((Input.InputBehaviorConfigurationSet) obj).getConfigurationsList());
        return singletonList;
    }
}
