package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.protocol.Firmware;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.util.-$$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js implements Comparator {
    public static final /* synthetic */ $$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js INSTANCE = new $$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js();

    private /* synthetic */ $$Lambda$MultiDeviceUtils$eRCy09H6wYZO8Vrqp2hTZ2HG8Js() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MultiDeviceUtils.lambda$extractLocaleFromFirmwareInformation$0((Firmware.FirmwareInformation) obj, (Firmware.FirmwareInformation) obj2);
    }
}
