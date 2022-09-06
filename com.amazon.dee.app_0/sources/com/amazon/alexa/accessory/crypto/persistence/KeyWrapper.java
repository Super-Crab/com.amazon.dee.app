package com.amazon.alexa.accessory.crypto.persistence;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
@RequiresApi(api = 23)
/* loaded from: classes.dex */
class KeyWrapper {
    private static final String ANDROID_KEYGEN_KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String DEFAULT_RSA_KEY_WRAPPING_CIPHER = "RSA/ECB/OAEPPadding";
    private static final Map<String, Supplier<AlgorithmParameterSpec>> RSA_CIPHER_SPECS = ImmutableMap.builder().mo7828put(DEFAULT_RSA_KEY_WRAPPING_CIPHER, $$Lambda$KeyWrapper$2iWR4JOA7a_n4TUrqXuwn0U2f5k.INSTANCE).mo7826build();
    private static final int WRAPPER_KEY_SIZE = 2048;

    /* loaded from: classes.dex */
    static class WrappedKey {
        private final String wrappedKey;
        private final String wrappedKeyAlgorithm;
        private final String wrappingTransformation;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedKey(String str, String str2, String str3) {
            this.wrappedKey = str;
            this.wrappedKeyAlgorithm = str2;
            this.wrappingTransformation = str3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getWrappedKey() {
            return this.wrappedKey;
        }

        String getWrappedKeyAlgorithm() {
            return this.wrappedKeyAlgorithm;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getWrappingTransformation() {
            return this.wrappingTransformation;
        }
    }

    private KeyPair generateKeyPair(KeyStoreAlias keyStoreAlias) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA, ANDROID_KEYGEN_KEYSTORE_PROVIDER);
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(keyStoreAlias.alias, 2).setDigests("SHA-256").setKeySize(2048).setEncryptionPaddings("OAEPPadding").build());
        return keyPairGenerator.generateKeyPair();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AlgorithmParameterSpec lambda$static$0() {
        return new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean canUnwrapKeys(KeyStoreAlias keyStoreAlias, KeyStore keyStore) throws CryptoKeyDataStore.InvalidStoreStateException {
        try {
            return keyStore.entryInstanceOf(keyStoreAlias.alias, KeyStore.PrivateKeyEntry.class);
        } catch (GeneralSecurityException e) {
            throw new CryptoKeyDataStore.InvalidStoreStateException(null, GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Unable to fetch key ["), keyStoreAlias.alias, "] from the Android KeyStore"), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Key unwrapKey(String str, WrappedKey wrappedKey, KeyStoreAlias keyStoreAlias, KeyStore keyStore) throws CryptoKeyDataStore.InvalidStoreStateException {
        try {
            if (keyStore.entryInstanceOf(keyStoreAlias.alias, KeyStore.PrivateKeyEntry.class)) {
                PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyStoreAlias.alias, null);
                byte[] decode = Base64.decode(wrappedKey.getWrappedKey(), 0);
                try {
                    Cipher cipher = Cipher.getInstance(wrappedKey.getWrappingTransformation());
                    if (RSA_CIPHER_SPECS.containsKey(wrappedKey.getWrappingTransformation())) {
                        cipher.init(4, privateKey, RSA_CIPHER_SPECS.get(wrappedKey.getWrappingTransformation()).mo8291get());
                    } else {
                        cipher.init(4, privateKey);
                    }
                    return cipher.unwrap(decode, wrappedKey.getWrappedKeyAlgorithm(), 3);
                } catch (GeneralSecurityException e) {
                    throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Unable to unwrap the provided key using the wrapping key in the Android KeyStore", e);
                }
            }
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Wrapper key not present in Android KeyStore or not of expected type");
        } catch (GeneralSecurityException e2) {
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Unable to fetch the wrapper key from the Android KeyStore", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WrappedKey wrapKey(String str, KeyStoreAlias keyStoreAlias, Key key, KeyStore keyStore) throws CryptoKeyDataStore.InvalidStoreStateException {
        KeyPair generateKeyPair;
        try {
            if (keyStore.entryInstanceOf(keyStoreAlias.alias, KeyStore.PrivateKeyEntry.class)) {
                generateKeyPair = new KeyPair(keyStore.getCertificate(keyStoreAlias.alias).getPublicKey(), (PrivateKey) keyStore.getKey(keyStoreAlias.alias, null));
            } else {
                generateKeyPair = generateKeyPair(keyStoreAlias);
            }
            try {
                Cipher cipher = Cipher.getInstance(DEFAULT_RSA_KEY_WRAPPING_CIPHER);
                if (RSA_CIPHER_SPECS.containsKey(DEFAULT_RSA_KEY_WRAPPING_CIPHER)) {
                    cipher.init(3, generateKeyPair.getPublic(), RSA_CIPHER_SPECS.get(DEFAULT_RSA_KEY_WRAPPING_CIPHER).mo8291get());
                } else {
                    cipher.init(3, generateKeyPair.getPublic());
                }
                return new WrappedKey(Base64.encodeToString(cipher.wrap(key), 0), key.getAlgorithm(), DEFAULT_RSA_KEY_WRAPPING_CIPHER);
            } catch (GeneralSecurityException e) {
                throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Error wrapping the provided key", e);
            }
        } catch (GeneralSecurityException e2) {
            throw new CryptoKeyDataStore.InvalidStoreStateException(str, "Unable to fetch/generate a key-pair for the wrapping key from its alias", e2);
        }
    }
}
