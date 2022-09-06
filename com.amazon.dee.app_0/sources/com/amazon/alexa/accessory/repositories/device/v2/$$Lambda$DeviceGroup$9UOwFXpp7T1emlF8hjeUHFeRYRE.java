package com.amazon.alexa.accessory.repositories.device.v2;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.repositories.device.v2.-$$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE implements Comparator {
    public static final /* synthetic */ $$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE INSTANCE = new $$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE();

    private /* synthetic */ $$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DeviceGroup.lambda$getDeviceWithHighestId$1((Device) obj, (Device) obj2);
    }
}
