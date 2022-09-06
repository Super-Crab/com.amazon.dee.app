package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryInputParameters;
/* loaded from: classes12.dex */
public class SmartHomeDiscoveryInputParameters extends DiscoveryInputParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.SmartHomeDiscoveryInputParameters");
    private String protocolType;

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeDiscoveryInputParameters)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.protocolType, ((SmartHomeDiscoveryInputParameters) obj).protocolType);
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    @Override // com.amazon.devicesetupservice.DiscoveryInputParameters
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.protocolType);
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }
}
