package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.DataMapValue;
import com.amazon.devicesetup.common.v1.DeviceDetails;
import java.util.Map;
/* loaded from: classes12.dex */
public class ComputeConfigurationDataInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ComputeConfigurationDataInput");
    private Map<String, String> configuration;
    private Map<String, DataMapValue> configurationDataMap;
    private DeviceDetails deviceDetails;
    private String nonce;

    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeConfigurationDataInput)) {
            return false;
        }
        ComputeConfigurationDataInput computeConfigurationDataInput = (ComputeConfigurationDataInput) obj;
        return Helper.equals(this.configuration, computeConfigurationDataInput.configuration) && Helper.equals(this.configurationDataMap, computeConfigurationDataInput.configurationDataMap) && Helper.equals(this.nonce, computeConfigurationDataInput.nonce) && Helper.equals(this.deviceDetails, computeConfigurationDataInput.deviceDetails);
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public Map<String, DataMapValue> getConfigurationDataMap() {
        return this.configurationDataMap;
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getNonce() {
        return this.nonce;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.configuration, this.configurationDataMap, this.nonce, this.deviceDetails);
    }

    public void setConfiguration(Map<String, String> map) {
        this.configuration = map;
    }

    public void setConfigurationDataMap(Map<String, DataMapValue> map) {
        this.configurationDataMap = map;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }
}
