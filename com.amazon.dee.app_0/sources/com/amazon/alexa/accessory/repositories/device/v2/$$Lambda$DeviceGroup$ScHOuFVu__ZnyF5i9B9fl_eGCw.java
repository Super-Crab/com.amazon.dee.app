package com.amazon.alexa.accessory.repositories.device.v2;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.repositories.device.v2.-$$Lambda$DeviceGroup$S-cHOuFVu__ZnyF5i9B9fl_eGCw  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DeviceGroup$ScHOuFVu__ZnyF5i9B9fl_eGCw implements Comparator {
    public static final /* synthetic */ $$Lambda$DeviceGroup$ScHOuFVu__ZnyF5i9B9fl_eGCw INSTANCE = new $$Lambda$DeviceGroup$ScHOuFVu__ZnyF5i9B9fl_eGCw();

    private /* synthetic */ $$Lambda$DeviceGroup$ScHOuFVu__ZnyF5i9B9fl_eGCw() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DeviceGroup.lambda$getDeviceWithHighestDeviceId$0((Device) obj, (Device) obj2);
    }
}
