package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ValidateWiFiSyncAuthTokenRequest {
    private final String mAuthMaterialIndex;
    private final String mProductIndex;
    private final String mSessionToken;
    private final String mWifiSyncAuthToken;

    public ValidateWiFiSyncAuthTokenRequest(String str, String str2, String str3, String str4) {
        this.mWifiSyncAuthToken = str;
        this.mAuthMaterialIndex = str2;
        this.mProductIndex = str3;
        this.mSessionToken = str4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ValidateWiFiSyncAuthTokenRequest.class != obj.getClass()) {
            return false;
        }
        ValidateWiFiSyncAuthTokenRequest validateWiFiSyncAuthTokenRequest = (ValidateWiFiSyncAuthTokenRequest) obj;
        return this.mWifiSyncAuthToken.equals(validateWiFiSyncAuthTokenRequest.mWifiSyncAuthToken) && this.mAuthMaterialIndex.equals(validateWiFiSyncAuthTokenRequest.mAuthMaterialIndex) && this.mProductIndex.equals(validateWiFiSyncAuthTokenRequest.mProductIndex) && this.mSessionToken.equals(validateWiFiSyncAuthTokenRequest.mSessionToken);
    }

    public String getAuthMaterialIndex() {
        return this.mAuthMaterialIndex;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public String getWifiSyncAuthToken() {
        return this.mWifiSyncAuthToken;
    }

    public int hashCode() {
        return Objects.hashCode(this.mWifiSyncAuthToken, this.mAuthMaterialIndex, this.mProductIndex, this.mSessionToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mWifiSyncAuthToken", this.mWifiSyncAuthToken).add("mAuthMaterialIndex", this.mAuthMaterialIndex).add("mProductIndex", this.mProductIndex).add("mSessionToken", this.mSessionToken).toString();
    }
}
