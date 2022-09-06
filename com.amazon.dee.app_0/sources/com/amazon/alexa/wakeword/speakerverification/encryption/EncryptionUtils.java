package com.amazon.alexa.wakeword.speakerverification.encryption;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.speakerverification.errors.EncryptionFailure;
import com.amazon.crypto.symmetric.SymmetricKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class EncryptionUtils {
    private static final String TAG = "EncryptionUtils";
    private final CryptoFactory mCryptoFactory;
    private final EncryptionMetricsReporter mEncryptionMetricsReporter;
    private final SymmetricKeyProvider mSymmetricKeyProvider;

    public EncryptionUtils(@NonNull Context context) {
        if (isSdkSupported()) {
            this.mCryptoFactory = new CryptoFactory();
            this.mSymmetricKeyProvider = new SymmetricKeyProvider(context);
            this.mEncryptionMetricsReporter = new EncryptionMetricsReporter(context);
            return;
        }
        this.mCryptoFactory = null;
        this.mSymmetricKeyProvider = null;
        this.mEncryptionMetricsReporter = null;
    }

    public byte[] decrypt(@NonNull byte[] bArr) {
        if (!isSdkSupported()) {
            return null;
        }
        SymmetricKey symmetricKey = this.mSymmetricKeyProvider.getSymmetricKey();
        if (symmetricKey == null) {
            Log.e(TAG, "SymmetricKey is null");
            return null;
        }
        try {
            return this.mCryptoFactory.getSymmetricDecryptor(symmetricKey).decrypt(bArr);
        } catch (Exception e) {
            Log.e(TAG, "Decryption failed", e, new Object[0]);
            return null;
        }
    }

    public byte[] encrypt(@NonNull byte[] bArr) {
        if (!isSdkSupported()) {
            return null;
        }
        if (bArr == null) {
            Log.e(TAG, "Input is null.");
            this.mEncryptionMetricsReporter.onEncryptionFailure(EncryptionFailure.INPUT_NULL);
            return null;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Input size: ");
        outline107.append(bArr.length);
        Log.i(str, outline107.toString());
        SymmetricKey symmetricKey = this.mSymmetricKeyProvider.getSymmetricKey();
        if (symmetricKey == null) {
            Log.e(TAG, "SymmetricKey is null");
            this.mEncryptionMetricsReporter.onEncryptionFailure(EncryptionFailure.SYMMETRIC_KEY_NULL);
            return null;
        }
        try {
            byte[] encrypt = this.mCryptoFactory.getSymmetricEncryptor(symmetricKey).encrypt(bArr);
            this.mEncryptionMetricsReporter.onEncryptionSuccess();
            return encrypt;
        } catch (IllegalArgumentException e) {
            this.mEncryptionMetricsReporter.onEncryptionFailure(EncryptionFailure.ILLEGAL_ARGUMENT_EXCEPTION);
            Log.e(TAG, GeneratedOutlineSupport1.outline39(IllegalArgumentException.class, GeneratedOutlineSupport1.outline107("Encryption failed due to ")), e, new Object[0]);
            return null;
        } catch (SecurityException e2) {
            this.mEncryptionMetricsReporter.onEncryptionFailure(EncryptionFailure.SECURITY_EXCEPTION);
            Log.e(TAG, GeneratedOutlineSupport1.outline39(SecurityException.class, GeneratedOutlineSupport1.outline107("Encryption failed due to ")), e2, new Object[0]);
            return null;
        } catch (Exception e3) {
            this.mEncryptionMetricsReporter.onNonFatalError(e3, "ENCRYPT");
            this.mEncryptionMetricsReporter.onEncryptionFailure(EncryptionFailure.OTHER_EXCEPTION);
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Other exception thrown from calling encrypt method: ");
            outline1072.append(e3.getClass().getSimpleName());
            Log.e(str2, outline1072.toString());
            return null;
        }
    }

    @VisibleForTesting
    boolean isSdkSupported() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @VisibleForTesting
    EncryptionUtils(@NonNull SymmetricKeyProvider symmetricKeyProvider, @NonNull CryptoFactory cryptoFactory, @NonNull EncryptionMetricsReporter encryptionMetricsReporter) {
        this.mCryptoFactory = cryptoFactory;
        this.mSymmetricKeyProvider = symmetricKeyProvider;
        this.mEncryptionMetricsReporter = encryptionMetricsReporter;
    }
}
