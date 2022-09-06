package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiScanData;
import java.util.List;
/* loaded from: classes12.dex */
public class GetWiFiNetworksInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetWiFiNetworksInput");
    private String accessToken;
    private String authMaterialIndex;
    private WifiScanData currentWifiNetwork;
    private String keyExchangeMethod;
    private String productIndex;
    private WifiScanData provisioneeLastConnectedNetwork;
    private String provisioningMethod;
    private String sessionToken;
    private String trustMethod;
    private List<WifiScanData> wifiScanDataList;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWiFiNetworksInput)) {
            return false;
        }
        GetWiFiNetworksInput getWiFiNetworksInput = (GetWiFiNetworksInput) obj;
        return Helper.equals(this.productIndex, getWiFiNetworksInput.productIndex) && Helper.equals(this.sessionToken, getWiFiNetworksInput.sessionToken) && Helper.equals(this.authMaterialIndex, getWiFiNetworksInput.authMaterialIndex) && Helper.equals(this.provisioneeLastConnectedNetwork, getWiFiNetworksInput.provisioneeLastConnectedNetwork) && Helper.equals(this.currentWifiNetwork, getWiFiNetworksInput.currentWifiNetwork) && Helper.equals(this.provisioningMethod, getWiFiNetworksInput.provisioningMethod) && Helper.equals(this.trustMethod, getWiFiNetworksInput.trustMethod) && Helper.equals(this.accessToken, getWiFiNetworksInput.accessToken) && Helper.equals(this.keyExchangeMethod, getWiFiNetworksInput.keyExchangeMethod) && Helper.equals(this.wifiScanDataList, getWiFiNetworksInput.wifiScanDataList);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public WifiScanData getCurrentWifiNetwork() {
        return this.currentWifiNetwork;
    }

    public String getKeyExchangeMethod() {
        return this.keyExchangeMethod;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public WifiScanData getProvisioneeLastConnectedNetwork() {
        return this.provisioneeLastConnectedNetwork;
    }

    public String getProvisioningMethod() {
        return this.provisioningMethod;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public String getTrustMethod() {
        return this.trustMethod;
    }

    public List<WifiScanData> getWifiScanDataList() {
        return this.wifiScanDataList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.productIndex, this.sessionToken, this.authMaterialIndex, this.provisioneeLastConnectedNetwork, this.currentWifiNetwork, this.provisioningMethod, this.trustMethod, this.accessToken, this.keyExchangeMethod, this.wifiScanDataList);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setCurrentWifiNetwork(WifiScanData wifiScanData) {
        this.currentWifiNetwork = wifiScanData;
    }

    public void setKeyExchangeMethod(String str) {
        this.keyExchangeMethod = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setProvisioneeLastConnectedNetwork(WifiScanData wifiScanData) {
        this.provisioneeLastConnectedNetwork = wifiScanData;
    }

    public void setProvisioningMethod(String str) {
        this.provisioningMethod = str;
    }

    public void setSessionToken(String str) {
        this.sessionToken = str;
    }

    public void setTrustMethod(String str) {
        this.trustMethod = str;
    }

    public void setWifiScanDataList(List<WifiScanData> list) {
        this.wifiScanDataList = list;
    }
}
