package com.amazon.identity.auth.device;

import android.util.Base64;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cr {
    private static final String TAG = "com.amazon.identity.auth.device.cr";
    private final SecretKeySpec iB;

    public cr(byte[] bArr) {
        if (bArr != null) {
            this.iB = new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            return;
        }
        throw new IllegalArgumentException("encryption key was null");
    }

    public static String a(ea eaVar) {
        String deviceSerialNumber;
        String f = eaVar.f();
        if (f != null && (deviceSerialNumber = eaVar.getDeviceSerialNumber()) != null) {
            try {
                return Base64.encodeToString(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(f.toCharArray(), deviceSerialNumber.getBytes(), 1000, 128)).getEncoded(), 0);
            } catch (GeneralSecurityException unused) {
                io.e(TAG, "Could not generate a symmetric key with PBKDF2");
            }
        }
        return cp();
    }

    public static String cp() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            keyGenerator.init(128);
            return Base64.encodeToString(keyGenerator.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException unused) {
            io.e(TAG, "Could not generate a symmetric key for algorithm AES");
            return null;
        }
    }

    @Deprecated
    public byte[] b(byte[] bArr) {
        if (bArr != null) {
            try {
                return a(a(1, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, null), bArr, 0, bArr.length);
            } catch (BadPaddingException e) {
                io.e(TAG, "BadPaddingException in encryption, should never happen.", e);
                return null;
            }
        }
        throw new IllegalArgumentException("dataToEncrypt is null");
    }

    @Deprecated
    public byte[] c(byte[] bArr) throws BadPaddingException {
        if (bArr != null) {
            return a(a(2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, null), bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("dataToDecrypt is null");
    }

    public byte[] d(byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = new byte[16];
            new SecureRandom().nextBytes(bArr2);
            try {
                return a(bArr2, a(a(1, "AES/CBC/PKCS7Padding", new IvParameterSpec(bArr2)), bArr, 0, bArr.length));
            } catch (BadPaddingException e) {
                io.e(TAG, "BadPaddingException in encryption, should never happen.", e);
                mq.b("BadPaddingExceptionInEncryption", new String[0]);
                return null;
            }
        }
        throw new IllegalArgumentException("dataToEncrypt is null");
    }

    public byte[] e(byte[] bArr) throws BadPaddingException {
        if (bArr != null) {
            return a(a(2, "AES/CBC/PKCS7Padding", new IvParameterSpec(bArr, 0, 16)), bArr, 16, bArr.length - 16);
        }
        throw new IllegalArgumentException("dataToDecrypt is null");
    }

    private Cipher a(int i, String str, AlgorithmParameterSpec algorithmParameterSpec) {
        return a(i, str, algorithmParameterSpec, true);
    }

    private Cipher a(int i, String str, AlgorithmParameterSpec algorithmParameterSpec, boolean z) {
        Cipher cipher;
        while (true) {
            cipher = null;
            try {
                cipher = Cipher.getInstance(str);
                cipher.init(i, this.iB, algorithmParameterSpec);
                break;
            } catch (AssertionError e) {
                io.e(TAG, "Android throws AssertionError: ", e);
                if (!z || !JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM.equals(str)) {
                    break;
                }
                io.i(TAG, "Retrying creating cipher");
                String str2 = TAG;
                String.format("Retrying use a more specified mode %s, instead of %s", "AES/ECB/PKCS5Padding", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
                io.dm(str2);
                str = "AES/ECB/PKCS5Padding";
                z = false;
            } catch (Exception e2) {
                io.e(TAG, "Aborting cipher creation", e2);
            }
        }
        return cipher;
    }

    public static byte[] a(Cipher cipher, byte[] bArr, int i, int i2) throws BadPaddingException {
        if (cipher == null) {
            return null;
        }
        try {
            return cipher.doFinal(bArr, i, i2);
        } catch (BadPaddingException e) {
            io.e(TAG, "BadPaddingException occurs, MAP will handle it in upper level but won't cause a crash.");
            throw e;
        } catch (Exception e2) {
            io.e(TAG, "Encrypting / Decrypting failed with the given key. Aborting!", e2);
            return null;
        }
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bArr3 = new byte[bArr2.length + 16];
            System.arraycopy(bArr, 0, bArr3, 0, 16);
            System.arraycopy(bArr2, 0, bArr3, 16, bArr2.length);
            return bArr3;
        } catch (Exception e) {
            io.e(TAG, "Exception happended during concatenating the initialization vectors and the cipher text", e);
            return null;
        }
    }
}
