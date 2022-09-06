package com.amazon.alexa.accessory.crypto;
/* loaded from: classes.dex */
public interface CryptoBundleProvider {
    String accessoryId();

    CryptoBundle fetchCryptoBundle();
}
