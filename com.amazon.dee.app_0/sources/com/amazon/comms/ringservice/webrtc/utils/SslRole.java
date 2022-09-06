package com.amazon.comms.ringservice.webrtc.utils;

import com.amazon.comms.log.CommsLogger;
import com.google.common.base.Strings;
/* loaded from: classes12.dex */
public enum SslRole {
    CLIENT,
    SERVER,
    UNKNOWN;
    
    private static final CommsLogger log = CommsLogger.getLogger(SslRole.class);

    public static SslRole getLocalSslRole(boolean z, String str, String str2) {
        CommsLogger commsLogger = log;
        commsLogger.i("getLocalSslRole: localDTLSRole " + str + " remoteDTLSRole " + str2);
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(str2)) {
            return UNKNOWN;
        }
        return z ? "active".equals(str2) ? SERVER : CLIENT : "passive".equals(str) ? SERVER : CLIENT;
    }
}
