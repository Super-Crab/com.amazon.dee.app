package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.protocol.Firmware;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.util.-$$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8 implements Comparator {
    public static final /* synthetic */ $$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8 INSTANCE = new $$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8();

    private /* synthetic */ $$Lambda$MultiDeviceUtils$TmOBtkPcAkoDneqk4u8EVNWYbK8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MultiDeviceUtils.lambda$getFirmwareInformationWithLowestVersion$1((Firmware.FirmwareInformation) obj, (Firmware.FirmwareInformation) obj2);
    }
}
