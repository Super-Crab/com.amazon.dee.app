package com.amazon.deecomms.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.GCMParameterSpec;
import javax.security.auth.x500.X500Principal;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class EncryptionUtils {
    private static final int AES_KEY_SIZE_IN_BITS = 128;
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final int GCM_TAG_SIZE_IN_BITS = 128;
    private static final String PREFS_NAME = "KEY_PREFS";
    @GuardedBy("ANDROID_KEY_STORE_LOCK")
    private static KeyStore androidKeyStore;
    private final Context mContext;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EncryptionUtils.class);
    private static final Object ANDROID_KEY_STORE_LOCK = new Object();

    public EncryptionUtils(@NonNull Context context) {
        this.mContext = context;
    }

    @Nullable
    private static String computeSHA256Hash(@NonNull char[] cArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(Utils.toBytes(cArr));
            return Base64.encodeToString(messageDigest.digest(), 0).trim();
        } catch (NoSuchAlgorithmException e) {
            LOG.e("SHA 256 algorithm is not found for computing hash", e);
            return null;
        }
    }

    private Certificate getCertificate(KeyStore keyStore, @NonNull String str) throws KeyStoreException {
        if (keyStore == null || !keyStore.containsAlias(str)) {
            return null;
        }
        return keyStore.getCertificate(str);
    }

    private String getHash(@NonNull PublicKey publicKey) {
        CharBuffer asCharBuffer = ByteBuffer.wrap(Base64.encode(publicKey.getEncoded(), 8)).asCharBuffer();
        char[] cArr = new char[asCharBuffer.length()];
        asCharBuffer.get(cArr);
        return computeSHA256Hash(cArr);
    }

    private KeyStore getKeyStore() {
        synchronized (ANDROID_KEY_STORE_LOCK) {
            if (androidKeyStore == null) {
                try {
                    KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                    keyStore.load(null);
                    androidKeyStore = keyStore;
                } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                    logError("GetKeystoreFailed", e);
                    CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_CRYPTO_KEYSTORE_FAILURE);
                    generateOperational.getMetadata().put("source", e.getClass().getSimpleName());
                    generateOperational.getMetadata().put("EventValue", 29);
                    generateOperational.getMetadata().put("errorSource", e.getMessage());
                }
            }
        }
        return androidKeyStore;
    }

    private synchronized PublicKey getPublicKeyInternal(@NonNull String str, boolean z) {
        PublicKey publicKey;
        KeyPair generateKey;
        publicKey = null;
        try {
            Certificate certificate = getCertificate(getKeyStore(), str);
            if (certificate != null) {
                publicKey = certificate.getPublicKey();
            } else if (z && (generateKey = generateKey(str)) != null) {
                publicKey = generateKey.getPublic();
            }
        } catch (RuntimeException | KeyStoreException e) {
            logError("GetKeyFailed", e);
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_CRYPTO_GETKEY_FAILURE);
            generateOperational.getMetadata().put("source", e.getClass().getSimpleName());
            generateOperational.getMetadata().put("EventValue", 29);
            generateOperational.getMetadata().put("errorSource", e.getMessage());
            MetricsHelper.recordSingleOccurrence(generateOperational);
        }
        return publicKey;
    }

    private void logError(String str, Throwable th) {
        LOG.e(str, th);
    }

    public String decryptString(@NonNull String str, @NonNull String str2) {
        try {
            Key key = getKeyStore().getKey(str, null);
            if (key == null) {
                LOG.e("Cannot find key from keystore to decrypt");
                return null;
            }
            LOG.d(String.format("Bas64 encrypted cipher text = %s ", LOG.sensitive(str2)));
            byte[] decode = Base64.decode(str2, 0);
            Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
            cipher.init(4, key);
            ByteBuffer wrap = ByteBuffer.wrap(decode);
            if (wrap.remaining() >= 2) {
                int i = wrap.getShort() & UShort.MAX_VALUE;
                if (wrap.remaining() >= i + 16) {
                    byte[] bArr = new byte[i];
                    wrap.get(bArr);
                    SecretKey secretKey = (SecretKey) cipher.unwrap(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 3);
                    Cipher cipher2 = Cipher.getInstance("AES/GCM/NoPadding");
                    cipher2.init(2, secretKey, new GCMParameterSpec(128, new byte[12]));
                    ByteBuffer allocate = ByteBuffer.allocate(cipher2.getOutputSize(wrap.remaining()));
                    cipher2.doFinal(wrap, allocate);
                    return new String(allocate.array(), 0, allocate.position());
                }
                throw new IllegalArgumentException("Invalid cipher text");
            }
            throw new IllegalArgumentException("Invalid CipherText");
        } catch (RuntimeException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | ShortBufferException e) {
            logError("DecryptStringFailed", e);
            return null;
        }
    }

    public String encryptString(@NonNull String str, @Nullable String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                LOG.e("Cannot encrypt null string");
                return null;
            }
            PublicKey publicKeyInternal = getPublicKeyInternal(str, true);
            if (publicKeyInternal == null) {
                LOG.e("Cannot find key from keystore to encrypt");
                return null;
            }
            Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
            cipher.init(3, publicKeyInternal);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            keyGenerator.init(128);
            SecretKey generateKey = keyGenerator.generateKey();
            byte[] wrap = cipher.wrap(generateKey);
            Cipher cipher2 = Cipher.getInstance("AES/GCM/NoPadding");
            cipher2.init(1, generateKey, new GCMParameterSpec(128, new byte[12]));
            ByteBuffer allocate = ByteBuffer.allocate(wrap.length + 2 + cipher2.getOutputSize(str2.length()));
            ByteBuffer wrap2 = ByteBuffer.wrap(str2.getBytes());
            allocate.putShort((short) wrap.length);
            allocate.put(wrap);
            cipher2.doFinal(wrap2, allocate);
            String str3 = new String(Base64.encode(allocate.array(), 0, allocate.position(), 0));
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Encrypted Text String:: ");
            sb.append(LOG.sensitive(str3));
            commsLogger.d(sb.toString());
            return str3;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | ShortBufferException e) {
            logError("EncryptStringFailed", e);
            return null;
        }
    }

    public synchronized KeyPair generateKey(String str) {
        LOG.i("generating key");
        Calendar.getInstance();
        Calendar.getInstance().add(1, 1);
        int i = Build.VERSION.SDK_INT;
        KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=Amazon, O=Android Authority")).setCertificateSerialNumber(BigInteger.ONE).setEncryptionPaddings("PKCS1Padding").setKeySize(2048).build();
        try {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA, getKeyStore().getProvider());
                keyPairGenerator.initialize(build);
                KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
                LOG.i("key generated");
                String hash = getHash(generateKeyPair.getPublic());
                CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_CRYPTO_KEYGEN_SUCCESS);
                generateOperational.getMetadata().put("errorSource", hash);
                generateOperational.getMetadata().put("EventValue", 29);
                MetricsHelper.recordSingleOccurrence(generateOperational);
                this.mContext.getSharedPreferences(PREFS_NAME, 0).edit().putString(str, hash).apply();
                return generateKeyPair;
            } catch (NoSuchAlgorithmException e) {
                e = e;
                logError("GenerateKeyFailed", e);
                return null;
            } catch (ProviderException e2) {
                logError("GenerateKeyFailed due to Provider Exception", e2);
                CounterMetric generateOperational2 = CounterMetric.generateOperational(MetricKeys.DEBUG_CRYPTO_KEYGEN_FAILURE);
                generateOperational2.getMetadata().put("source", "ProviderException");
                generateOperational2.getMetadata().put("EventValue", 29);
                generateOperational2.getMetadata().put("errorSource", e2.getMessage());
                MetricsHelper.recordSingleOccurrence(generateOperational2);
                this.mContext.getSharedPreferences("SHARED_PREFS", 0).edit().putBoolean(Constants.HAS_ENCRYPTION_ISSUE, true).apply();
                throw e2;
            }
        } catch (IllegalStateException e3) {
            logError("GenerateKey Failed due to Illegal State Exception", e3);
            CounterMetric generateOperational3 = CounterMetric.generateOperational(MetricKeys.DEBUG_CRYPTO_KEYGEN_FAILURE);
            generateOperational3.getMetadata().put("source", "IllegalStateException");
            generateOperational3.getMetadata().put("errorSource", e3.getMessage());
            generateOperational3.getMetadata().put("EventValue", 29);
            MetricsHelper.recordSingleOccurrence(generateOperational3);
            this.mContext.getSharedPreferences("SHARED_PREFS", 0).edit().putBoolean(Constants.HAS_ENCRYPTION_ISSUE, true).apply();
            return null;
        } catch (InvalidAlgorithmParameterException e4) {
            e = e4;
            logError("GenerateKeyFailed", e);
            return null;
        }
    }

    public synchronized char[] getKey(@NonNull String str) {
        PublicKey publicKeyInternal = getPublicKeyInternal(str, true);
        if (publicKeyInternal == null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Cannot get key for alias ");
            outline1.append(str.hashCode());
            commsLogger.e(outline1.toString());
            return null;
        }
        CharBuffer asCharBuffer = ByteBuffer.wrap(Base64.encode(publicKeyInternal.getEncoded(), 8)).asCharBuffer();
        char[] cArr = new char[asCharBuffer.length()];
        asCharBuffer.get(cArr);
        return cArr;
    }

    public synchronized Date getKeyExpiryDate(String str) {
        try {
            Certificate certificate = getCertificate(getKeyStore(), str);
            if (!(certificate instanceof X509Certificate)) {
                LOG.i("Key not present in Keystore.");
                return null;
            }
            return ((X509Certificate) certificate).getNotAfter();
        } catch (RuntimeException | KeyStoreException e) {
            logError("Expiry date fetch failed", e);
            return null;
        }
    }

    public boolean hasEncryptionIssue() {
        return this.mContext.getSharedPreferences("SHARED_PREFS", 0).getBoolean(Constants.HAS_ENCRYPTION_ISSUE, false);
    }

    public synchronized boolean isKeyExpired(String str) {
        Date keyExpiryDate = getKeyExpiryDate(str);
        if (keyExpiryDate == null) {
            return false;
        }
        return new Date().after(keyExpiryDate);
    }

    public synchronized boolean isKeyValid(@NonNull String str) {
        PublicKey publicKeyInternal = getPublicKeyInternal(str, false);
        if (publicKeyInternal == null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Key is not found for alias:");
            outline1.append(str.hashCode());
            commsLogger.e(outline1.toString());
            return false;
        }
        String hash = getHash(publicKeyInternal);
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PREFS_NAME, 0);
        String string = sharedPreferences.getString(str, null);
        if (TextUtils.isEmpty(string)) {
            string = Utils.getStringPreferenceFromSharedPrefs(this.mContext, str, null);
            if (!TextUtils.isEmpty(string)) {
                sharedPreferences.edit().putString(str, string).apply();
                this.mContext.getSharedPreferences("SHARED_PREFS", 0).edit().remove(str).apply();
            }
        }
        boolean equals = TextUtils.equals(hash, string);
        if (!equals) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Key Integrity is not valid for alias:");
            outline12.append(str.hashCode());
            commsLogger2.e(outline12.toString());
        }
        return equals;
    }
}
