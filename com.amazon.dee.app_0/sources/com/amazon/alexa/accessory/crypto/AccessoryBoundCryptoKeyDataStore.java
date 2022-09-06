package com.amazon.alexa.accessory.crypto;

import com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public final class AccessoryBoundCryptoKeyDataStore implements CryptoBundleProvider {
    private final AccessoryIdentifierProvider idProvider;
    private final CryptoKeyDataStore keyStore;

    public AccessoryBoundCryptoKeyDataStore(AccessoryIdentifierProvider accessoryIdentifierProvider, CryptoKeyDataStore cryptoKeyDataStore) {
        Preconditions.notNull(accessoryIdentifierProvider, "idProvider");
        Preconditions.notNull(cryptoKeyDataStore, "keyStore");
        this.idProvider = accessoryIdentifierProvider;
        this.keyStore = cryptoKeyDataStore;
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoBundleProvider
    public String accessoryId() {
        return this.idProvider.getIdentifier();
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoBundleProvider
    public CryptoBundle fetchCryptoBundle() {
        CryptoKeyDataStore.NegotiatedData data = this.keyStore.getData(accessoryId());
        if (data == null) {
            return null;
        }
        return new CryptoBundle(data.cipherSuite, data.cipherTransform, data.encryptionKey, data.authenticationKey);
    }
}
