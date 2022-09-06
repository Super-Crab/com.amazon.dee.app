package com.amazon.whisperjoin.common.sharedtypes.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.ArrayUtils;
/* loaded from: classes13.dex */
public class AuthMaterialIndexGenerator {
    private static final String TAG = "AuthMaterialIndexGenerator";

    private AuthMaterialIndexGenerator() {
    }

    public static byte[] generate(byte[] bArr) {
        if (bArr != null) {
            try {
                byte[] digest = MessageDigest.getInstance("SHA-256").digest(bArr);
                return ArrayUtils.subarray(digest, digest.length - 9, digest.length);
            } catch (NoSuchAlgorithmException e) {
                WJLog.e(TAG, "SHA256 algo not found", e);
                throw new RuntimeException("SHA256 algo not found", e);
            }
        }
        throw new IllegalArgumentException("derPubKeyByteArray can not be null");
    }
}
