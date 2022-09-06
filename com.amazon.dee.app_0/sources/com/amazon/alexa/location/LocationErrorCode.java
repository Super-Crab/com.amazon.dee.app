package com.amazon.alexa.location;

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
    ALS_AUTH_TOKEN_ERROR(8),
    ALS_400(400),
    ALS_401(HttpServletResponse.SC_UNAUTHORIZED),
    ALS_403(403),
    ALS_404(404),
    ALS_500(500),
    ALS_503(503);
    
    int errorCode;

    LocationErrorCode(int i) {
        this.errorCode = i;
    }

    public int getValue() {
        return this.errorCode;
    }
}
