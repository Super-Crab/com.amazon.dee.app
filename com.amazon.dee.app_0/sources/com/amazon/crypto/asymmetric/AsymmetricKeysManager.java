package com.amazon.crypto.asymmetric;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.crypto.KeyManager;
import com.amazon.crypto.utils.Preconditions;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.security.auth.x500.X500Principal;
@RequiresApi(api = 22)
/* loaded from: classes12.dex */
public final class AsymmetricKeysManager implements KeyManager<AsymmetricKeys> {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private final Context context;
    private final KeyStore keyStore;

    @VisibleForTesting
    AsymmetricKeysManager(Context context, KeyStore keyStore) {
        this.context = context;
        this.keyStore = keyStore;
        int i = Build.VERSION.SDK_INT;
    }

    private static KeyStore androidKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
            return keyStore;
        } catch (IOException e) {
            throw new SecurityException(e);
        } catch (GeneralSecurityException e2) {
            throw new SecurityException(e2);
        }
    }

    @RequiresApi(23)
    private AlgorithmParameterSpec createSpec(String str) {
        return new KeyGenParameterSpec.Builder(str, 3).setKeySize(2048).setCertificateSubject(new X500Principal(GeneratedOutlineSupport1.outline72("CN=", str))).setCertificateSerialNumber(BigInteger.ONE).setDigests("SHA-1").setEncryptionPaddings("OAEPPadding").setRandomizedEncryptionRequired(false).build();
    }

    @NonNull
    public static AsymmetricKeysManager with(@NonNull Context context) {
        Preconditions.notNull(context);
        return new AsymmetricKeysManager(context, androidKeyStore());
    }

    @Override // com.amazon.crypto.KeyManager
    public boolean contains(@Nullable String str) throws SecurityException {
        try {
            Preconditions.notBlank(str);
            try {
                return this.keyStore.containsAlias(str);
            } catch (KeyStoreException e) {
                throw new SecurityException(e);
            }
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // com.amazon.crypto.KeyManager
    public void remove(@NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notBlank(str);
        Preconditions.isTrue(contains(str));
        try {
            this.keyStore.deleteEntry(str);
        } catch (KeyStoreException e) {
            throw new SecurityException(e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.crypto.KeyManager
    @NonNull
    @RequiresApi(23)
    /* renamed from: generate */
    public AsymmetricKeys mo3298generate(@NonNull String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notBlank(str);
        Preconditions.isTrue(true ^ contains(str));
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA, ANDROID_KEY_STORE);
            keyPairGenerator.initialize(createSpec(str));
            keyPairGenerator.generateKeyPair();
            return mo3299retrieve(str);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.crypto.KeyManager
    @NonNull
    /* renamed from: retrieve */
    public AsymmetricKeys mo3299retrieve(@Nullable String str) throws IllegalArgumentException, SecurityException {
        Preconditions.notBlank(str);
        Preconditions.isTrue(contains(str));
        try {
            if (this.keyStore.entryInstanceOf(str, KeyStore.PrivateKeyEntry.class)) {
                try {
                    try {
                        return AsymmetricKeys.from(PublishedKey.from(this.keyStore.getCertificate(str).getPublicKey().getEncoded()), WithheldKey.from((PrivateKey) this.keyStore.getKey(str, null)));
                    } catch (GeneralSecurityException e) {
                        throw new SecurityException(e);
                    }
                } catch (GeneralSecurityException e2) {
                    throw new SecurityException(e2);
                }
            }
            throw new SecurityException(GeneratedOutlineSupport1.outline72("Bad key found for alias ", str));
        } catch (GeneralSecurityException unused) {
            throw new SecurityException(GeneratedOutlineSupport1.outline72("Bad key found for alias ", str));
        }
    }
}
