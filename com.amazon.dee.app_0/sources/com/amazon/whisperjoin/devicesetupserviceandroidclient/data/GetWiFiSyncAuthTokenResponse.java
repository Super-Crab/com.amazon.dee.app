package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
/* loaded from: classes13.dex */
public class GetWiFiSyncAuthTokenResponse {
    private final int mResponseCode;
    private final Long mRetryAfter;
    private final String mWiFiSyncAuthToken;

    public GetWiFiSyncAuthTokenResponse(String str, int i, Long l) {
        this.mWiFiSyncAuthToken = str;
        this.mResponseCode = i;
        this.mRetryAfter = l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && GetWiFiSyncAuthTokenResponse.class == obj.getClass()) {
            return this.mWiFiSyncAuthToken.equals(((GetWiFiSyncAuthTokenResponse) obj).mWiFiSyncAuthToken);
        }
        return false;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public Long getRetryAfter() {
        return this.mRetryAfter;
    }

    public String getWiFiSyncAuthToken() {
        return this.mWiFiSyncAuthToken;
    }

    public int hashCode() {
        return this.mWiFiSyncAuthToken.hashCode();
    }

    public boolean isSuccess() {
        return this.mResponseCode == 200;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mWiFiSyncAuthToken", this.mWiFiSyncAuthToken).add("mResponseCode", this.mResponseCode).add("mRetryAfter", this.mRetryAfter).toString();
    }

    public GetWiFiSyncAuthTokenResponse(String str) {
        this(str, 200, null);
    }
}
