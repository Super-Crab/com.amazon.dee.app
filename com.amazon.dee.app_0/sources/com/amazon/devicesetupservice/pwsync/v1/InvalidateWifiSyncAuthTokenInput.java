package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
/* loaded from: classes12.dex */
public class InvalidateWifiSyncAuthTokenInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.InvalidateWifiSyncAuthTokenInput");
    private String authMaterialIndex;
    private String productIndex;
    private String signature;
    private long timestamp;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InvalidateWifiSyncAuthTokenInput)) {
            return false;
        }
        InvalidateWifiSyncAuthTokenInput invalidateWifiSyncAuthTokenInput = (InvalidateWifiSyncAuthTokenInput) obj;
        return super.equals(obj) && Helper.equals(this.productIndex, invalidateWifiSyncAuthTokenInput.productIndex) && Helper.equals(this.authMaterialIndex, invalidateWifiSyncAuthTokenInput.authMaterialIndex) && Helper.equals(Long.valueOf(this.timestamp), Long.valueOf(invalidateWifiSyncAuthTokenInput.timestamp)) && Helper.equals(this.signature, invalidateWifiSyncAuthTokenInput.signature);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getSignature() {
        return this.signature;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.productIndex, this.authMaterialIndex, Long.valueOf(this.timestamp), this.signature);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
