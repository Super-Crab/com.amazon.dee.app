package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8 implements Comparator {
    public static final /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8 INSTANCE = new $$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8();

    private /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$ElX7PhSONBBKWls1kfSYyS7nfv8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return InterprocessPrivacyModeEmitter.lambda$deviceInformationWithHighestId$8((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
