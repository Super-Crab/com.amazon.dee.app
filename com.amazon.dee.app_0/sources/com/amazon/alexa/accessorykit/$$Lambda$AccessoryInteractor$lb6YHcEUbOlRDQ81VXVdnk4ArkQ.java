package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ INSTANCE = new $$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ();

    private /* synthetic */ $$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String deviceType;
        deviceType = ((Device.DeviceInformation) Collections.max((Set) obj, $$Lambda$AccessoryInteractor$OifmqFkEu0Nk0uW1oL1sNntJKg.INSTANCE)).getDeviceType();
        return deviceType;
    }
}
