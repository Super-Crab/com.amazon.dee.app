package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
/* loaded from: classes13.dex */
public class ValidateWiFiSyncAuthTokenResponse {
    private final boolean mIsValidToken;

    public ValidateWiFiSyncAuthTokenResponse(boolean z) {
        this.mIsValidToken = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ValidateWiFiSyncAuthTokenResponse.class == obj.getClass() && this.mIsValidToken == ((ValidateWiFiSyncAuthTokenResponse) obj).mIsValidToken;
    }

    public boolean isValidToken() {
        return this.mIsValidToken;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mIsValidToken", this.mIsValidToken).toString();
    }
}
