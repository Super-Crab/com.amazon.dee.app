package com.amazon.whisperjoin.devicesetupserviceandroidclient.error;

import androidx.annotation.Nullable;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DSSServiceError extends Exception {
    public static final int ERROR_403_FORBIDDEN = 403;
    private final int mErrorCode;
    private final String mErrorMessage;
    private final Long mRetryCallAfterTimestampMs;

    public DSSServiceError(int i, @Nullable String str) {
        this(i, str, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DSSServiceError.class != obj.getClass()) {
            return false;
        }
        DSSServiceError dSSServiceError = (DSSServiceError) obj;
        return this.mErrorCode == dSSServiceError.mErrorCode && Objects.equal(this.mErrorMessage, dSSServiceError.mErrorMessage);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public Long getRetryCallAfterTimestampMs() {
        return this.mRetryCallAfterTimestampMs;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mErrorCode), this.mErrorMessage);
    }

    public boolean is403Forbidden() {
        return this.mErrorCode == 403;
    }

    public DSSServiceError(int i, @Nullable String str, @Nullable Long l) {
        super("An Error Occurred Making a called to DSS. HTTP Status = " + i + ", Message = " + str);
        this.mErrorCode = i;
        this.mErrorMessage = str;
        this.mRetryCallAfterTimestampMs = l;
    }
}
