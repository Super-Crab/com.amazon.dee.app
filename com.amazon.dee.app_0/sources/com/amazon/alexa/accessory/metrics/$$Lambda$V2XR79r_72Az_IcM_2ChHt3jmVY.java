package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.MultiDeviceUtils;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.metrics.-$$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY implements Function {
    public static final /* synthetic */ $$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY INSTANCE = new $$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY();

    private /* synthetic */ $$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return MultiDeviceUtils.getDeviceTypeOfHighestDeviceId((DeviceGroup) obj);
    }
}
