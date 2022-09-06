package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.protocol.Accessories;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE implements Predicate {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE INSTANCE = new $$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE();

    private /* synthetic */ $$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        boolean updateRequired;
        updateRequired = ((Accessories.Response) obj).getArtifactUpdatePreference().getUpdateRequired();
        return updateRequired;
    }
}
