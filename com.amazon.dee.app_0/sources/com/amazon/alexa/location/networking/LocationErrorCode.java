package com.amazon.alexa.location.networking;

import javax.servlet.http.HttpServletResponse;
/* loaded from: classes9.dex */
public enum LocationErrorCode {
    GENERIC_ERROR(1),
    PERMISSION_ERROR(2),
    BEYOND_20_LIMIT(3),
    FEATURE_NOT_AVAILABLE(4),
    ALS_TIMEOUT(5),
    ALS_NOT_RECOGNIZE(6),
    ALS_PAYLOAD_ERROR(7),
    ALS_AUTH_TOKEN_ERROR(9),
    ALPS_TIMEOUT(10),
    ALPS_NOT_RECOGNIZE(11),
    ALPS_PAYLOAD_ERROR(12),
    ALPS_AUTH_TOKEN_ERROR(13),
    ALPS_DEVICE_INFO_ERROR(14),
    ALPS_IO_EXCEPTION(15),
    ALS_400(400),
    ALS_401(HttpServletResponse.SC_UNAUTHORIZED),
    ALS_403(403),
    ALS_404(404),
    ALS_500(500),
    ALS_503(503),
    ALPS_400(400),
    ALPS_401(HttpServletResponse.SC_UNAUTHORIZED),
    ALPS_403(403),
    ALPS_404(404),
    ALPS_500(500),
    ALPS_503(503);
    
    int errorCode;

    LocationErrorCode(int i) {
        this.errorCode = i;
    }

    public int getValue() {
        return this.errorCode;
    }
}
