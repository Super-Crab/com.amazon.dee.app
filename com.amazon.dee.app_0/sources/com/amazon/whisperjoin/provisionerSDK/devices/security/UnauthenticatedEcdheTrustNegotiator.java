package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whispercloak.SecureChannelImpl;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
/* loaded from: classes13.dex */
public class UnauthenticatedEcdheTrustNegotiator extends BaseEcdheTrustNegotiator implements TrustNegotiator {
    public UnauthenticatedEcdheTrustNegotiator(DSSClient dSSClient, Serializer serializer) {
        super(dSSClient, serializer);
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiator
    public EncryptionProvider start(PeripheralDevice peripheralDevice) throws TrustProviderInitializationFailedException {
        return start(peripheralDevice, new SecureChannelImpl(), Command.EXCHANGE_ECDHE_KEY);
    }
}
