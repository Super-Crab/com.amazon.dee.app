package com.amazon.deecomms.calling.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public enum HttpStatusCodeFamily {
    UNKNOWN(0, 0, 0, 1),
    INFORMATIONAL(0, 0, 0, 1),
    SUCCESS(1, 0, 0, 0),
    REDIRECTION(0, 0, 0, 1),
    CLIENT_ERROR(0, 1, 0, 0),
    SERVER_ERROR(0, 0, 1, 0);
    
    private final int failureCount;
    private final int faultCount;
    private final int successCount;
    private final int unknownCount;

    HttpStatusCodeFamily(int i, int i2, int i3, int i4) {
        this.successCount = i;
        this.failureCount = i2;
        this.faultCount = i3;
        this.unknownCount = i4;
    }

    @NonNull
    public static HttpStatusCodeFamily familyFromStatusCode(@Nullable Integer num) {
        if (num == null) {
            return UNKNOWN;
        }
        int intValue = num.intValue() / 100;
        if (intValue == 1) {
            return INFORMATIONAL;
        }
        if (intValue == 2) {
            return SUCCESS;
        }
        if (intValue == 3) {
            return REDIRECTION;
        }
        if (intValue == 4) {
            return CLIENT_ERROR;
        }
        if (intValue != 5) {
            return UNKNOWN;
        }
        return SERVER_ERROR;
    }

    public int getFailureCount() {
        return this.failureCount;
    }

    public int getFaultCount() {
        return this.faultCount;
    }

    public int getSuccessCount() {
        return this.successCount;
    }

    public int getUnknownCount() {
        return this.unknownCount;
    }
}
