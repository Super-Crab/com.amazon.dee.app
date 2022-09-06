package com.amazon.alexa.wakeword.davs;

import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes11.dex */
public class CheckSumUtils {
    public static final String DEFAULT_ARTIFACT_MD5 = "";
    private static final String MD_5 = "MD5";

    private CheckSumUtils() {
        throw new IllegalStateException("cannot create instance");
    }

    public static String getMD5(@Nullable byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Integer.valueOf(digest[i] & 255)));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static boolean verifyChecksum(byte[] bArr, String str) {
        return str.equalsIgnoreCase(getMD5(bArr));
    }
}
