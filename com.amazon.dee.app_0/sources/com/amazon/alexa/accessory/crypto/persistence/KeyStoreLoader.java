package com.amazon.alexa.accessory.crypto.persistence;

import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
/* loaded from: classes.dex */
public class KeyStoreLoader {
    private static final String ANDROID_KEY_STORE_PROVIDER = "AndroidKeyStore";
    private KeyStore lazy = null;

    protected KeyStore fabricateAndLoadKeyStore() throws CryptoKeyDataStore.CryptoKeyDataStoreException {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_PROVIDER);
            keyStore.load(null);
            return keyStore;
        } catch (IOException | GeneralSecurityException e) {
            throw new CryptoKeyDataStore.CryptoKeyDataStoreIOException(null, "Error loading the Android KeyStore", e);
        }
    }

    public final synchronized KeyStore getKeyStore() throws CryptoKeyDataStore.CryptoKeyDataStoreIOException {
        if (this.lazy == null) {
            this.lazy = fabricateAndLoadKeyStore();
        }
        return this.lazy;
    }
}
