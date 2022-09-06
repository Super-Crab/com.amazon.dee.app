package com.amazon.alexa.accessory.persistence.device;

import com.amazon.alexa.accessory.persistence.device.DeviceContract;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.device.-$$Lambda$PS0SfmfT7V_YwcpMNbpo_-zXOiw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw implements Function {
    public static final /* synthetic */ $$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw INSTANCE = new $$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw();

    private /* synthetic */ $$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return DeviceContract.Device.convertDeviceGroupToDevice((DeviceGroup) obj);
    }
}
