package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustCommandNotHandledException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
/* loaded from: classes13.dex */
public interface TrustProvider<I, O> {

    /* loaded from: classes13.dex */
    public enum TrustState {
        UNTRUSTED,
        UNTRUSTED_ENCRYPTED,
        TRUSTED_ENCRYPTED,
        UNTRUSTED_PIN
    }

    EncryptionProvider getEncryptionProvider();

    O handleRequest(I i) throws TrustCommandNotHandledException;

    void initialize() throws TrustProviderInitializationFailedException;

    boolean isSessionNegotiationComplete();
}
