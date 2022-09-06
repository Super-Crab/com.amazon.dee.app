package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class SaveWifiConfigurationsRequest extends CredentialLockerBaseRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.SaveWifiConfigurationsRequest");
    private String deviceId;
    private String deviceType;
    private List<WifiConfiguration> wifiConfigurations;

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof SaveWifiConfigurationsRequest)) {
            return false;
        }
        SaveWifiConfigurationsRequest saveWifiConfigurationsRequest = (SaveWifiConfigurationsRequest) obj;
        return super.equals(obj) && Helper.equals(this.deviceId, saveWifiConfigurationsRequest.deviceId) && Helper.equals(this.wifiConfigurations, saveWifiConfigurationsRequest.wifiConfigurations) && Helper.equals(this.deviceType, saveWifiConfigurationsRequest.deviceType);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public List<WifiConfiguration> getWifiConfigurations() {
        return this.wifiConfigurations;
    }

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.deviceId, this.wifiConfigurations, this.deviceType);
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setWifiConfigurations(List<WifiConfiguration> list) {
        this.wifiConfigurations = list;
    }
}
