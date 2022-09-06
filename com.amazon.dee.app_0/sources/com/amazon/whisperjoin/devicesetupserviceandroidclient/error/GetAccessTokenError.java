package com.amazon.whisperjoin.devicesetupserviceandroidclient.error;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GetAccessTokenError extends Exception {
    private final int mErrorCode;

    public GetAccessTokenError(int i) {
        this.mErrorCode = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && GetAccessTokenError.class == obj.getClass() && this.mErrorCode == ((GetAccessTokenError) obj).mErrorCode;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mErrorCode));
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mErrorCode", this.mErrorCode).toString();
    }
}
