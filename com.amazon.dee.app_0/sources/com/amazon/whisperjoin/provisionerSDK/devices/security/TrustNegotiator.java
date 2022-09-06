package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
/* loaded from: classes13.dex */
public interface TrustNegotiator {
    EncryptionProvider start(PeripheralDevice peripheralDevice) throws TrustProviderInitializationFailedException;
}
