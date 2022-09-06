package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
/* loaded from: classes12.dex */
public class ValidateWifiSyncAuthTokenInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenInput");
    private String authMaterialIndex;
    private String productIndex;
    private String sessionToken;
    private String wifiSyncAuthToken;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof ValidateWifiSyncAuthTokenInput)) {
            return false;
        }
        ValidateWifiSyncAuthTokenInput validateWifiSyncAuthTokenInput = (ValidateWifiSyncAuthTokenInput) obj;
        return super.equals(obj) && Helper.equals(this.wifiSyncAuthToken, validateWifiSyncAuthTokenInput.wifiSyncAuthToken) && Helper.equals(this.authMaterialIndex, validateWifiSyncAuthTokenInput.authMaterialIndex) && Helper.equals(this.productIndex, validateWifiSyncAuthTokenInput.productIndex) && Helper.equals(this.sessionToken, validateWifiSyncAuthTokenInput.sessionToken);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public String getWifiSyncAuthToken() {
        return this.wifiSyncAuthToken;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.wifiSyncAuthToken, this.authMaterialIndex, this.productIndex, this.sessionToken);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public void setWifiSyncAuthToken(String str) {
        this.wifiSyncAuthToken = str;
    }
}
