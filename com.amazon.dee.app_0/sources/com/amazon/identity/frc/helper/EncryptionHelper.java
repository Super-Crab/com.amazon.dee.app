package com.amazon.identity.frc.helper;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes12.dex */
public final class EncryptionHelper {
    private static final String TAG = "com.amazon.identity.frc.helper.EncryptionHelper";

    private EncryptionHelper() {
    }

    @SuppressLint({"TrulyRandom"})
    public static String encrypt(String str, String str2) {
        try {
            if (str == null) {
                Log.e(TAG, "The input string should not be null!");
                return null;
            } else if (str2 == null) {
                Log.e(TAG, "Seed is null, cannot encrypt the input string! Just return the original string");
                return str;
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(str.getBytes("UTF-8"));
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
                cipher.init(1, generateKey(str2, "AES/CBC/PKCS7Padding"));
                byte[] doFinal = cipher.doFinal(byteArray);
                byte[] iv = cipher.getIV();
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(generateKey(str2, "HmacSHA256"));
                mac.update(iv);
                mac.update(doFinal);
                byte[] doFinal2 = mac.doFinal();
                byte[] bArr = new byte[doFinal.length + 25];
                bArr[0] = 0;
                System.arraycopy(doFinal2, 0, bArr, 1, 8);
                System.arraycopy(iv, 0, bArr, 9, 16);
                System.arraycopy(doFinal, 0, bArr, 25, doFinal.length);
                return Base64.encodeToString(bArr, 2);
            }
        } catch (Exception e) {
            Log.e(TAG, String.format(Locale.getDefault(), "Exception happened when encrypting the string:%s", e.getClass().getName()), e);
            return null;
        }
    }

    private static Key generateKey(String str, String str2) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), str2.getBytes("UTF-8"), 1000, 128)).getEncoded(), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
    }
}
