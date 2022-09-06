package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class DeleteWifiConfigurationsRequest extends CredentialLockerBaseRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.DeleteWifiConfigurationsRequest");
    private String deviceId;
    private String deviceType;

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof DeleteWifiConfigurationsRequest)) {
            return false;
        }
        DeleteWifiConfigurationsRequest deleteWifiConfigurationsRequest = (DeleteWifiConfigurationsRequest) obj;
        return super.equals(obj) && Helper.equals(this.deviceId, deleteWifiConfigurationsRequest.deviceId) && Helper.equals(this.deviceType, deleteWifiConfigurationsRequest.deviceType);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.deviceId, this.deviceType);
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
