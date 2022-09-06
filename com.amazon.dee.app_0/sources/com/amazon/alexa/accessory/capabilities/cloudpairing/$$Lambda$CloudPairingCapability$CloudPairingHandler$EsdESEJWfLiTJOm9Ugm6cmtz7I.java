package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.capabilities.cloudpairing.CloudPairingCapability;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cloudpairing.-$$Lambda$CloudPairingCapability$CloudPairingHandler$E-sdESEJWfLiTJOm9Ugm6cmtz7I  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CloudPairingCapability$CloudPairingHandler$EsdESEJWfLiTJOm9Ugm6cmtz7I implements MappedResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$EsdESEJWfLiTJOm9Ugm6cmtz7I INSTANCE = new $$Lambda$CloudPairingCapability$CloudPairingHandler$EsdESEJWfLiTJOm9Ugm6cmtz7I();

    private /* synthetic */ $$Lambda$CloudPairingCapability$CloudPairingHandler$EsdESEJWfLiTJOm9Ugm6cmtz7I() {
    }

    @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return CloudPairingCapability.CloudPairingHandler.lambda$handleReplaceCloudPairingKeys$1(response);
    }
}
