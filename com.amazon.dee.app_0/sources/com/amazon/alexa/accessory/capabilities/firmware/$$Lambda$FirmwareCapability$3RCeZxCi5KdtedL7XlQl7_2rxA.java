package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$3RCeZxCi-5KdtedL7XlQl7_2rxA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$3RCeZxCi5KdtedL7XlQl7_2rxA implements Function {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$3RCeZxCi5KdtedL7XlQl7_2rxA INSTANCE = new $$Lambda$FirmwareCapability$3RCeZxCi5KdtedL7XlQl7_2rxA();

    private /* synthetic */ $$Lambda$FirmwareCapability$3RCeZxCi5KdtedL7XlQl7_2rxA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Firmware.FirmwareComponent mo10084build;
        mo10084build = Firmware.FirmwareComponent.newBuilder().setName(r1.getName()).setSize(r1.getSize()).setVersion(r1.getVersion()).setSignature(((FirmwareComponentSupplier) obj).getSignature()).mo10084build();
        return mo10084build;
    }
}
