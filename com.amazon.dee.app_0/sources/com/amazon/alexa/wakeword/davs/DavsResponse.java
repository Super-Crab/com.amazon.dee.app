package com.amazon.alexa.wakeword.davs;

import javax.servlet.http.HttpServletResponse;
/* loaded from: classes11.dex */
public enum DavsResponse {
    SUCCESS(200),
    ILLEGAL_ARGUMENT(400),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED),
    FORBIDDEN(403),
    NO_ARTIFACT_FOUND(404),
    UP_TO_DATE(304),
    OTHER(-999);
    
    private final int code;

    DavsResponse(int i) {
        this.code = i;
    }

    public boolean isError() {
        return (SUCCESS == this || UP_TO_DATE == this) ? false : true;
    }

    public static DavsResponse valueOf(int i) {
        DavsResponse[] values;
        for (DavsResponse davsResponse : values()) {
            if (davsResponse.code == i) {
                return davsResponse;
            }
        }
        return OTHER;
    }
}
