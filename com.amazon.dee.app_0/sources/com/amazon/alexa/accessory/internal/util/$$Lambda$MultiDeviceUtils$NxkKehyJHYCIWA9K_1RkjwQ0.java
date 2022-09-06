package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.util.-$$Lambda$MultiDeviceUtils$NxkKehyJ-HYCIWA9K_1R--kjwQ0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MultiDeviceUtils$NxkKehyJHYCIWA9K_1RkjwQ0 implements Comparator {
    public static final /* synthetic */ $$Lambda$MultiDeviceUtils$NxkKehyJHYCIWA9K_1RkjwQ0 INSTANCE = new $$Lambda$MultiDeviceUtils$NxkKehyJHYCIWA9K_1RkjwQ0();

    private /* synthetic */ $$Lambda$MultiDeviceUtils$NxkKehyJHYCIWA9K_1RkjwQ0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MultiDeviceUtils.lambda$getDeviceInformationWithHighestDeviceId$2((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
