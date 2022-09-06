package com.amazon.identity.auth.device;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ku {
    private static final String TAG = "com.amazon.identity.auth.device.ku";

    private ku() {
    }

    public static String dO(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] dF = jg.dF(str);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(dF);
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder(digest.length * 2);
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", Integer.valueOf(digest[i] & 255)));
            }
            return sb.toString().substring(23, 31);
        } catch (NoSuchAlgorithmException e) {
            io.e(TAG, "SHA-256 algorithm does not exist.  PANICK!", e);
            return null;
        }
    }
}
