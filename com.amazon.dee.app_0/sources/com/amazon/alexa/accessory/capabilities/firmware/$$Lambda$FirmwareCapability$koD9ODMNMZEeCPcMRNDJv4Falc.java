package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$koD9-ODMNMZEeCPcMRNDJv4Falc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$koD9ODMNMZEeCPcMRNDJv4Falc implements Function {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$koD9ODMNMZEeCPcMRNDJv4Falc INSTANCE = new $$Lambda$FirmwareCapability$koD9ODMNMZEeCPcMRNDJv4Falc();

    private /* synthetic */ $$Lambda$FirmwareCapability$koD9ODMNMZEeCPcMRNDJv4Falc() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("Error scheduling job to download kota package on wifi", (Throwable) obj);
    }
}
