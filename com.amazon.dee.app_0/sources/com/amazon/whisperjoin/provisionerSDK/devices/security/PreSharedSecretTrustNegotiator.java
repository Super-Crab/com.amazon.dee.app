package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whispercloak.SecureChannelImpl;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AesGcmEncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import org.bouncycastle.util.encoders.Base64;
/* loaded from: classes13.dex */
public class PreSharedSecretTrustNegotiator implements TrustNegotiator {
    private final String mPreSharedSecretBase64;
    private final Serializer mSerializer;

    public PreSharedSecretTrustNegotiator(String str, Serializer serializer) {
        if (str != null) {
            if (serializer != null) {
                this.mPreSharedSecretBase64 = str;
                this.mSerializer = serializer;
                return;
            }
            throw new IllegalArgumentException("serializer can not be null");
        }
        throw new IllegalArgumentException("preSharedSecretBase64 can not be null");
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiator
    public EncryptionProvider start(PeripheralDevice peripheralDevice) throws TrustProviderInitializationFailedException {
        try {
            SecureChannelImpl secureChannelImpl = new SecureChannelImpl();
            secureChannelImpl.getCipher();
            return new AesGcmEncryptionProvider(secureChannelImpl.getCipher(), Base64.decode(this.mPreSharedSecretBase64), this.mSerializer);
        } catch (Exception e) {
            throw new TrustProviderInitializationFailedException(e);
        }
    }
}
