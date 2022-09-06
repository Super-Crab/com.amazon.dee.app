package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.-$$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis implements Function {
    public static final /* synthetic */ $$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis INSTANCE = new $$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis();

    private /* synthetic */ $$Lambda$AccessorySpeechProviderManager$cTfjK8sR0LPkYcRxFUZL5X8Atis() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String deviceType;
        deviceType = ((Device.DeviceInformation) ((Set) obj).iterator().next()).getDeviceType();
        return deviceType;
    }
}
