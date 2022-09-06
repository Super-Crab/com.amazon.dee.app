package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class SaveWifiNetworkInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.SaveWifiNetworkInput");
    private String authMaterialIndex;
    private String keyExchangeMethod;
    private WifiNetworkInfo oldWifiNetwork;
    private String productIndex;
    private String provisioningMethod;
    private String sessionToken;
    private String trustMethod;
    private WifiConfiguration wifiConfig;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof SaveWifiNetworkInput)) {
            return false;
        }
        SaveWifiNetworkInput saveWifiNetworkInput = (SaveWifiNetworkInput) obj;
        return super.equals(obj) && Helper.equals(this.keyExchangeMethod, saveWifiNetworkInput.keyExchangeMethod) && Helper.equals(this.trustMethod, saveWifiNetworkInput.trustMethod) && Helper.equals(this.productIndex, saveWifiNetworkInput.productIndex) && Helper.equals(this.provisioningMethod, saveWifiNetworkInput.provisioningMethod) && Helper.equals(this.sessionToken, saveWifiNetworkInput.sessionToken) && Helper.equals(this.authMaterialIndex, saveWifiNetworkInput.authMaterialIndex) && Helper.equals(this.oldWifiNetwork, saveWifiNetworkInput.oldWifiNetwork) && Helper.equals(this.wifiConfig, saveWifiNetworkInput.wifiConfig);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getKeyExchangeMethod() {
        return this.keyExchangeMethod;
    }

    public WifiNetworkInfo getOldWifiNetwork() {
        return this.oldWifiNetwork;
    }

    public String getProductIndex() {
        return this.productIndex;
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

    public WifiConfiguration getWifiConfig() {
        return this.wifiConfig;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.keyExchangeMethod, this.trustMethod, this.productIndex, this.provisioningMethod, this.sessionToken, this.authMaterialIndex, this.oldWifiNetwork, this.wifiConfig);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setKeyExchangeMethod(String str) {
        this.keyExchangeMethod = str;
    }

    public void setOldWifiNetwork(WifiNetworkInfo wifiNetworkInfo) {
        this.oldWifiNetwork = wifiNetworkInfo;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
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

    public void setWifiConfig(WifiConfiguration wifiConfiguration) {
        this.wifiConfig = wifiConfiguration;
    }
}
