package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk implements Function {
    public static final /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk INSTANCE = new $$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk();

    private /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$VFIx5xnsR4pEaryGNF07xNgnhkk() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Device.DeviceInformation deviceInformationWithHighestId;
        deviceInformationWithHighestId = InterprocessPrivacyModeEmitter.deviceInformationWithHighestId((Set) obj);
        return deviceInformationWithHighestId;
    }
}
