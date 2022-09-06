package com.amazon.whisperjoin.credentiallocker;

import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes13.dex */
class WifiConfigurationsRequest {
    public final String deviceId;
    public final String deviceType;
    public final List<WifiConfiguration> wifiConfigurations;

    public WifiConfigurationsRequest(String str, String str2, List<WifiConfiguration> list) {
        this.deviceType = str;
        this.deviceId = str2;
        this.wifiConfigurations = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfigurationsRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfigurationsRequest)) {
            return false;
        }
        WifiConfigurationsRequest wifiConfigurationsRequest = (WifiConfigurationsRequest) obj;
        if (!wifiConfigurationsRequest.canEqual(this)) {
            return false;
        }
        String deviceId = getDeviceId();
        String deviceId2 = wifiConfigurationsRequest.getDeviceId();
        if (deviceId != null ? !deviceId.equals(deviceId2) : deviceId2 != null) {
            return false;
        }
        String deviceType = getDeviceType();
        String deviceType2 = wifiConfigurationsRequest.getDeviceType();
        if (deviceType != null ? !deviceType.equals(deviceType2) : deviceType2 != null) {
            return false;
        }
        List<WifiConfiguration> wifiConfigurations = getWifiConfigurations();
        List<WifiConfiguration> wifiConfigurations2 = wifiConfigurationsRequest.getWifiConfigurations();
        return wifiConfigurations != null ? wifiConfigurations.equals(wifiConfigurations2) : wifiConfigurations2 == null;
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

    public int hashCode() {
        String deviceId = getDeviceId();
        int i = 43;
        int hashCode = deviceId == null ? 43 : deviceId.hashCode();
        String deviceType = getDeviceType();
        int hashCode2 = ((hashCode + 59) * 59) + (deviceType == null ? 43 : deviceType.hashCode());
        List<WifiConfiguration> wifiConfigurations = getWifiConfigurations();
        int i2 = hashCode2 * 59;
        if (wifiConfigurations != null) {
            i = wifiConfigurations.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfigurationsRequest(deviceId=");
        outline107.append(getDeviceId());
        outline107.append(", deviceType=");
        outline107.append(getDeviceType());
        outline107.append(", wifiConfigurations=");
        return GeneratedOutlineSupport1.outline95(outline107, getWifiConfigurations(), ")");
    }
}
