package com.amazon.alexa.accessory.persistence.device;

import com.amazon.alexa.accessory.persistence.device.DeviceContract;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.device.-$$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE implements Function {
    public static final /* synthetic */ $$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE INSTANCE = new $$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE();

    private /* synthetic */ $$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return DeviceContract.Device.convertDeviceGroupsToDevices((List) obj);
    }
}
