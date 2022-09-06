package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.repositories.device.v2.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.util.-$$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II implements Comparator {
    public static final /* synthetic */ $$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II INSTANCE = new $$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II();

    private /* synthetic */ $$Lambda$MultiDeviceUtils$PA32Pww8xmdFvyeLajHun8RI5II() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MultiDeviceUtils.lambda$getDeviceTypeOfHighestDeviceId$3((Device) obj, (Device) obj2);
    }
}
