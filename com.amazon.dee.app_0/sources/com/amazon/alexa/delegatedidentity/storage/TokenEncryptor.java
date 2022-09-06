package com.amazon.alexa.delegatedidentity.storage;

import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.crypto.Crypto;
import com.amazon.crypto.symmetric.SymmetricKey;
/* loaded from: classes6.dex */
public class TokenEncryptor {
    static final String ENCRYPTION_KEY_IN_SHARED_PREF = "com.amazon.alexa.delegatedidentity.DelegatedTokenStorage.encrypt.key";
    private static final String TAG = "com.amazon.alexa.delegatedidentity.storage.TokenEncryptor";
    private final LocalAndroidKeyValueStore localAndroidKeyValueStore;

    public TokenEncryptor(LocalAndroidKeyValueStore localAndroidKeyValueStore) {
        this.localAndroidKeyValueStore = localAndroidKeyValueStore;
        initialize();
    }

    private void initialize() {
        try {
            this.localAndroidKeyValueStore.setValue(ENCRYPTION_KEY_IN_SHARED_PREF, encodeToString(Crypto.generateSymmetricKey().toBytes()));
        } catch (Exception e) {
            Log.e(TAG, "Exception caught while setting up encryption key. Not storing encryption key in local KV store.", e);
        }
    }

    @VisibleForTesting
    byte[] decodeToBytes(String str) {
        return Base64.decode(str, 0);
    }

    public String decrypt(@NonNull String str) {
        SymmetricKey retrieveSymmetricKeyFromStore = retrieveSymmetricKeyFromStore();
        if (retrieveSymmetricKeyFromStore == null) {
            Log.e(TAG, "Cannot obtain symmetric key for encryption. Unable to get actor token.`");
            return null;
        }
        return new String(Crypto.decrypt(decodeToBytes(str), retrieveSymmetricKeyFromStore));
    }

    @VisibleForTesting
    String encodeToString(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public String encrypt(@NonNull String str) {
        SymmetricKey retrieveSymmetricKeyFromStore = retrieveSymmetricKeyFromStore();
        if (retrieveSymmetricKeyFromStore == null) {
            Log.e(TAG, "Cannot obtain symmetric key for encryption. Unable to store actor token.`");
            return null;
        }
        return encodeToString(Crypto.encrypt(str.getBytes(), retrieveSymmetricKeyFromStore));
    }

    @VisibleForTesting
    SymmetricKey retrieveSymmetricKeyFromStore() {
        String stringValue = this.localAndroidKeyValueStore.getStringValue(ENCRYPTION_KEY_IN_SHARED_PREF, null);
        if (stringValue != null && !stringValue.isEmpty()) {
            return SymmetricKey.from(decodeToBytes(stringValue));
        }
        Log.e(TAG, "No symmetric key found. Trying to initialize again.");
        initialize();
        return null;
    }
}
