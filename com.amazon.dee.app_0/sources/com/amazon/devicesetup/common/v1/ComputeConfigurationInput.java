package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.Map;
/* loaded from: classes12.dex */
public class ComputeConfigurationInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.ComputeConfigurationInput");
    private Map<String, String> configuration;
    private DeviceDetails deviceDetails;
    private String nonce;
    private String sessionId;

    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeConfigurationInput)) {
            return false;
        }
        ComputeConfigurationInput computeConfigurationInput = (ComputeConfigurationInput) obj;
        return Helper.equals(this.sessionId, computeConfigurationInput.sessionId) && Helper.equals(this.deviceDetails, computeConfigurationInput.deviceDetails) && Helper.equals(this.configuration, computeConfigurationInput.configuration) && Helper.equals(this.nonce, computeConfigurationInput.nonce);
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.sessionId, this.deviceDetails, this.configuration, this.nonce);
    }

    public void setConfiguration(Map<String, String> map) {
        this.configuration = map;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}
