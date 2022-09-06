package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s implements Comparator {
    public static final /* synthetic */ $$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s INSTANCE = new $$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s();

    private /* synthetic */ $$Lambda$StateChangeAccessoryManager$HHw1gzKuW5s4JRDBoJHEKvak_7s() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return StateChangeAccessoryManager.lambda$getDeviceTypeForReportingMetrics$7((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
