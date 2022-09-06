package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class GetWifiConfigurationsResponse implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.GetWifiConfigurationsResponse");
    private List<WifiConfiguration> wifiConfigurations;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiConfigurationsResponse)) {
            return false;
        }
        return Helper.equals(this.wifiConfigurations, ((GetWifiConfigurationsResponse) obj).wifiConfigurations);
    }

    public List<WifiConfiguration> getWifiConfigurations() {
        return this.wifiConfigurations;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.wifiConfigurations);
    }

    public void setWifiConfigurations(List<WifiConfiguration> list) {
        this.wifiConfigurations = list;
    }
}
