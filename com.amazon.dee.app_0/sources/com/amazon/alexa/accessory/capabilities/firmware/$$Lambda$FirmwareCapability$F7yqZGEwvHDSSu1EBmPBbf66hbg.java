package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.protocol.Firmware;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg implements Function {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg INSTANCE = new $$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg();

    private /* synthetic */ $$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Firmware.NextAvailableUpdate mo10084build;
        mo10084build = Firmware.NextAvailableUpdate.newBuilder().setDeviceId(((Integer) obj).intValue()).mo10084build();
        return mo10084build;
    }
}
