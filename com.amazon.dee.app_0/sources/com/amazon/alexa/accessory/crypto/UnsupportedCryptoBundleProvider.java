package com.amazon.alexa.accessory.crypto;
/* loaded from: classes.dex */
public class UnsupportedCryptoBundleProvider implements CryptoBundleProvider {
    @Override // com.amazon.alexa.accessory.crypto.CryptoBundleProvider
    public String accessoryId() {
        throw new UnsupportedOperationException("accessoryId() is not supported yet.");
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoBundleProvider
    public CryptoBundle fetchCryptoBundle() {
        throw new UnsupportedOperationException("fetchCrytoBundle() is not supported yet.");
    }
}
