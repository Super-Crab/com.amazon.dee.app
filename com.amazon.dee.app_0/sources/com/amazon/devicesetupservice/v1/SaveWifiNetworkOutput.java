package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class SaveWifiNetworkOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.SaveWifiNetworkOutput");
    private String endpointToUse;

    public boolean equals(Object obj) {
        if (!(obj instanceof SaveWifiNetworkOutput)) {
            return false;
        }
        return Helper.equals(this.endpointToUse, ((SaveWifiNetworkOutput) obj).endpointToUse);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.endpointToUse);
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }
}
