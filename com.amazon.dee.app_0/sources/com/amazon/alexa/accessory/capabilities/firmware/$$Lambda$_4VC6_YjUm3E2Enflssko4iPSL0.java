package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0 implements Function {
    public static final /* synthetic */ $$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0 INSTANCE = new $$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0();

    private /* synthetic */ $$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Integer.valueOf(((Device.DeviceInformation) obj).getDeviceId());
    }
}
