package com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear;

import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes13.dex */
public final class LocalConfigInstanceIdGenerationHelper {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String TAG = "LocalConfigInstanceIdGenerationHelper";

    private LocalConfigInstanceIdGenerationHelper() {
    }

    private static String convertBytesToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static String generateLocalConfigInstanceId(String str) {
        Checks.checkArgument(str != null && !str.isEmpty(), "Invalid string for hashing");
        try {
            return convertBytesToHexString(MessageDigest.getInstance("SHA-256").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            Log.e(TAG, "Unable to generate hash from string identifier");
            return "";
        }
    }
}
