package com.amazon.identity.auth.device.storage;

import android.annotation.TargetApi;
import android.security.keystore.KeyProtection;
import com.amazon.identity.auth.device.api.MAPError;
import com.google.android.gms.stats.CodePackage;
import java.security.KeyStore;
import java.security.KeyStoreException;
import javax.crypto.SecretKey;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class KeystoreProvider {
    private static final String TAG = "com.amazon.identity.auth.device.storage.KeystoreProvider";
    private final KeyStore os = fo();
    private final String ot;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class KeystoreProviderException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;

        public KeystoreProviderException(MAPError mAPError, String str, Throwable th) {
            super(th.getMessage(), th);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }
    }

    public KeystoreProvider(String str) throws KeystoreProviderException {
        this.ot = str;
    }

    private KeyStore fo() throws KeystoreProviderException {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (Exception e) {
            throw new KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
        }
    }

    @TargetApi(23)
    public void a(SecretKey secretKey) throws KeyStoreException {
        this.os.setEntry(this.ot, new KeyStore.SecretKeyEntry(secretKey), new KeyProtection.Builder(3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build());
    }

    public SecretKey fm() throws KeystoreProviderException {
        try {
            return (SecretKey) this.os.getKey(this.ot, null);
        } catch (Exception e) {
            throw new KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
        }
    }

    public void fn() throws KeystoreProviderException {
        try {
            this.os.deleteEntry(this.ot);
        } catch (Exception e) {
            throw new KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
        }
    }
}
