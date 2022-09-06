package org.apache.commons.codec.digest;

import com.amazonaws.services.s3.internal.Constants;
/* loaded from: classes4.dex */
public enum HmacAlgorithms {
    HMAC_MD5("HmacMD5"),
    HMAC_SHA_1(Constants.HMAC_SHA1_ALGORITHM),
    HMAC_SHA_256("HmacSHA256"),
    HMAC_SHA_384("HmacSHA384"),
    HMAC_SHA_512("HmacSHA512");
    
    private final String algorithm;

    HmacAlgorithms(String str) {
        this.algorithm = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.algorithm;
    }
}
