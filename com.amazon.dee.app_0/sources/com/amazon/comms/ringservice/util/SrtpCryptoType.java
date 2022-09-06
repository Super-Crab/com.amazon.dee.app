package com.amazon.comms.ringservice.util;

import com.amazon.comms.log.CommsLogger;
/* loaded from: classes12.dex */
public enum SrtpCryptoType {
    DTLS,
    SDES,
    DTLS_SDES;
    
    private static final CommsLogger log = CommsLogger.getLogger(SrtpCryptoType.class);

    public static SrtpCryptoType getSrtpCryptoType(String str) {
        try {
            return valueOf(str);
        } catch (Exception unused) {
            log.w("Unknown SRTP keying, will fall back to SDES");
            return SDES;
        }
    }
}
