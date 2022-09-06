package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetWifiConfigurationsRequest extends CredentialLockerBaseRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.GetWifiConfigurationsRequest");
    private String deviceId;
    private String deviceType;
    private boolean utf8Supported;

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiConfigurationsRequest)) {
            return false;
        }
        GetWifiConfigurationsRequest getWifiConfigurationsRequest = (GetWifiConfigurationsRequest) obj;
        return super.equals(obj) && Helper.equals(this.deviceType, getWifiConfigurationsRequest.deviceType) && Helper.equals(Boolean.valueOf(this.utf8Supported), Boolean.valueOf(getWifiConfigurationsRequest.utf8Supported)) && Helper.equals(this.deviceId, getWifiConfigurationsRequest.deviceId);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.deviceType, Boolean.valueOf(this.utf8Supported), this.deviceId);
    }

    public boolean isUtf8Supported() {
        return this.utf8Supported;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setUtf8Supported(boolean z) {
        this.utf8Supported = z;
    }
}
