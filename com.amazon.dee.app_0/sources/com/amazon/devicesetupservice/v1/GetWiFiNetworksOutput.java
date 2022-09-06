package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiScanData;
import java.util.List;
/* loaded from: classes12.dex */
public class GetWiFiNetworksOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetWiFiNetworksOutput");
    private String endpointToUse;
    private WifiNetworkDetail preferredWifiConfig;
    private List<WifiScanData> unknownWifiNetworkList;
    private List<WifiNetworkDetail> wifiConfigList;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWiFiNetworksOutput)) {
            return false;
        }
        GetWiFiNetworksOutput getWiFiNetworksOutput = (GetWiFiNetworksOutput) obj;
        return Helper.equals(this.unknownWifiNetworkList, getWiFiNetworksOutput.unknownWifiNetworkList) && Helper.equals(this.endpointToUse, getWiFiNetworksOutput.endpointToUse) && Helper.equals(this.wifiConfigList, getWiFiNetworksOutput.wifiConfigList) && Helper.equals(this.preferredWifiConfig, getWiFiNetworksOutput.preferredWifiConfig);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public WifiNetworkDetail getPreferredWifiConfig() {
        return this.preferredWifiConfig;
    }

    public List<WifiScanData> getUnknownWifiNetworkList() {
        return this.unknownWifiNetworkList;
    }

    public List<WifiNetworkDetail> getWifiConfigList() {
        return this.wifiConfigList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.unknownWifiNetworkList, this.endpointToUse, this.wifiConfigList, this.preferredWifiConfig);
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setPreferredWifiConfig(WifiNetworkDetail wifiNetworkDetail) {
        this.preferredWifiConfig = wifiNetworkDetail;
    }

    public void setUnknownWifiNetworkList(List<WifiScanData> list) {
        this.unknownWifiNetworkList = list;
    }

    public void setWifiConfigList(List<WifiNetworkDetail> list) {
        this.wifiConfigList = list;
    }
}
