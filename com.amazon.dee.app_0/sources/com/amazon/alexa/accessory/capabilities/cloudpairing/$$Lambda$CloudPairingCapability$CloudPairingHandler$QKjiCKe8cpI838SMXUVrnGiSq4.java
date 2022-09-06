package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.capabilities.cloudpairing.CloudPairingCapability;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cloudpairing.-$$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838-SMXUVrnGiSq4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838SMXUVrnGiSq4 implements MappedResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838SMXUVrnGiSq4 INSTANCE = new $$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838SMXUVrnGiSq4();

    private /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838SMXUVrnGiSq4() {
    }

    @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return CloudPairingCapability.CloudPairingHandler.lambda$handleSetCloudPairingKeys$0(response);
    }
}
