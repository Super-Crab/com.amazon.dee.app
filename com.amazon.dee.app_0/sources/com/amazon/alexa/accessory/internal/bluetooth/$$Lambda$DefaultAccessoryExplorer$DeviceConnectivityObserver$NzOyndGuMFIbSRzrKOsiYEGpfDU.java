package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.AccessorySession;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU implements Function {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU INSTANCE = new $$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU();

    private /* synthetic */ $$Lambda$DefaultAccessoryExplorer$DeviceConnectivityObserver$NzOyndGuMFIbSRzrKOsiYEGpfDU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource observable;
        observable = ((AccessorySession) obj).getTransportRepository().shouldUpgrade().toObservable();
        return observable;
    }
}
