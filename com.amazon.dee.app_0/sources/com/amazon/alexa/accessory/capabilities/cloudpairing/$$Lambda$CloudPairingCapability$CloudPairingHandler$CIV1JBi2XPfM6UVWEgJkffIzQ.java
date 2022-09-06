package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.capabilities.cloudpairing.CloudPairingCapability;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cloudpairing.-$$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6--UVWEgJkffIzQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6UVWEgJkffIzQ implements MappedResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6UVWEgJkffIzQ INSTANCE = new $$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6UVWEgJkffIzQ();

    private /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6UVWEgJkffIzQ() {
    }

    @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return CloudPairingCapability.CloudPairingHandler.lambda$handleRemoveCloudPairingKeys$2(response);
    }
}
