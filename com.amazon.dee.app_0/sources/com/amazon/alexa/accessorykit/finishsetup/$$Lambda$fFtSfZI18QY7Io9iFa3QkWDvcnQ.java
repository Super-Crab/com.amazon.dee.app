package com.amazon.alexa.accessorykit.finishsetup;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ implements Function {
    public static final /* synthetic */ $$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ INSTANCE = new $$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ();

    private /* synthetic */ $$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Device.DeviceInformation) obj).getDeviceType();
    }
}
