package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryOutputParameters;
/* loaded from: classes12.dex */
public class SmartHomeDiscoveryOutputParameters extends DiscoveryOutputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.SmartHomeDiscoveryOutputParameters");
    private SmartHomeDeviceCredentials credentials;
    private String version;

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeDiscoveryOutputParameters)) {
            return false;
        }
        SmartHomeDiscoveryOutputParameters smartHomeDiscoveryOutputParameters = (SmartHomeDiscoveryOutputParameters) obj;
        return super.equals(obj) && Helper.equals(this.version, smartHomeDiscoveryOutputParameters.version) && Helper.equals(this.credentials, smartHomeDiscoveryOutputParameters.credentials);
    }

    public SmartHomeDeviceCredentials getCredentials() {
        return this.credentials;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.devicesetupservice.DiscoveryOutputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.version, this.credentials);
    }

    public void setCredentials(SmartHomeDeviceCredentials smartHomeDeviceCredentials) {
        this.credentials = smartHomeDeviceCredentials;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
