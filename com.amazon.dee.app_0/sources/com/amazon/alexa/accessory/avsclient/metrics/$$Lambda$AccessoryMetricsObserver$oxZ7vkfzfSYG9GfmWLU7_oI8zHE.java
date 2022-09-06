package com.amazon.alexa.accessory.avsclient.metrics;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE INSTANCE = new $$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE();

    private /* synthetic */ $$Lambda$AccessoryMetricsObserver$oxZ7vkfzfSYG9GfmWLU7_oI8zHE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return AccessoryMetricsObserver.lambda$onMetricsAvailable$2((Device.DeviceInformation) obj);
    }
}
