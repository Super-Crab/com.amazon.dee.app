package com.amazon.whisperjoin.credentiallocker;

import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes13.dex */
class WifiConfigurationsResponse {
    public final List<WifiConfiguration> wifiConfigurations;

    public WifiConfigurationsResponse(List<WifiConfiguration> list) {
        this.wifiConfigurations = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfigurationsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfigurationsResponse)) {
            return false;
        }
        WifiConfigurationsResponse wifiConfigurationsResponse = (WifiConfigurationsResponse) obj;
        if (!wifiConfigurationsResponse.canEqual(this)) {
            return false;
        }
        List<WifiConfiguration> wifiConfigurations = getWifiConfigurations();
        List<WifiConfiguration> wifiConfigurations2 = wifiConfigurationsResponse.getWifiConfigurations();
        return wifiConfigurations != null ? wifiConfigurations.equals(wifiConfigurations2) : wifiConfigurations2 == null;
    }

    public List<WifiConfiguration> getWifiConfigurations() {
        return this.wifiConfigurations;
    }

    public int hashCode() {
        List<WifiConfiguration> wifiConfigurations = getWifiConfigurations();
        return 59 + (wifiConfigurations == null ? 43 : wifiConfigurations.hashCode());
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("WifiConfigurationsResponse(wifiConfigurations="), getWifiConfigurations(), ")");
    }
}
