package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class DiscoveredProvisionableDeviceInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceInput");
    private String accessToken;
    private int advertisedSdkVersionIndex;
    private String authMaterialIndex;
    private String deviceName;
    private String nonce;
    private String productIndex;
    private ProvisionableInfo provisionableInfo;
    private String provisionerApplication;
    private String provisionerApplicationVersion;
    private ProvisionerInfo provisionerInfo;
    private String provisioningMethod;
    private String radio;
    private int rssi;
    private String softwareVersionIndex;
    private String trustMethod;

    public boolean equals(Object obj) {
        if (!(obj instanceof DiscoveredProvisionableDeviceInput)) {
            return false;
        }
        DiscoveredProvisionableDeviceInput discoveredProvisionableDeviceInput = (DiscoveredProvisionableDeviceInput) obj;
        return Helper.equals(this.deviceName, discoveredProvisionableDeviceInput.deviceName) && Helper.equals(this.productIndex, discoveredProvisionableDeviceInput.productIndex) && Helper.equals(this.provisionerInfo, discoveredProvisionableDeviceInput.provisionerInfo) && Helper.equals(this.accessToken, discoveredProvisionableDeviceInput.accessToken) && Helper.equals(this.trustMethod, discoveredProvisionableDeviceInput.trustMethod) && Helper.equals(this.softwareVersionIndex, discoveredProvisionableDeviceInput.softwareVersionIndex) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(discoveredProvisionableDeviceInput.rssi)) && Helper.equals(this.provisioningMethod, discoveredProvisionableDeviceInput.provisioningMethod) && Helper.equals(this.provisionerApplication, discoveredProvisionableDeviceInput.provisionerApplication) && Helper.equals(this.authMaterialIndex, discoveredProvisionableDeviceInput.authMaterialIndex) && Helper.equals(this.nonce, discoveredProvisionableDeviceInput.nonce) && Helper.equals(this.provisionerApplicationVersion, discoveredProvisionableDeviceInput.provisionerApplicationVersion) && Helper.equals(this.provisionableInfo, discoveredProvisionableDeviceInput.provisionableInfo) && Helper.equals(this.radio, discoveredProvisionableDeviceInput.radio) && Helper.equals(Integer.valueOf(this.advertisedSdkVersionIndex), Integer.valueOf(discoveredProvisionableDeviceInput.advertisedSdkVersionIndex));
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.advertisedSdkVersionIndex;
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public ProvisionableInfo getProvisionableInfo() {
        return this.provisionableInfo;
    }

    public String getProvisionerApplication() {
        return this.provisionerApplication;
    }

    public String getProvisionerApplicationVersion() {
        return this.provisionerApplicationVersion;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.provisionerInfo;
    }

    public String getProvisioningMethod() {
        return this.provisioningMethod;
    }

    public String getRadio() {
        return this.radio;
    }

    public int getRssi() {
        return this.rssi;
    }

    public String getSoftwareVersionIndex() {
        return this.softwareVersionIndex;
    }

    public String getTrustMethod() {
        return this.trustMethod;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceName, this.productIndex, this.provisionerInfo, this.accessToken, this.trustMethod, this.softwareVersionIndex, Integer.valueOf(this.rssi), this.provisioningMethod, this.provisionerApplication, this.authMaterialIndex, this.nonce, this.provisionerApplicationVersion, this.provisionableInfo, this.radio, Integer.valueOf(this.advertisedSdkVersionIndex));
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAdvertisedSdkVersionIndex(int i) {
        this.advertisedSdkVersionIndex = i;
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setProvisionableInfo(ProvisionableInfo provisionableInfo) {
        this.provisionableInfo = provisionableInfo;
    }

    public void setProvisionerApplication(String str) {
        this.provisionerApplication = str;
    }

    public void setProvisionerApplicationVersion(String str) {
        this.provisionerApplicationVersion = str;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.provisionerInfo = provisionerInfo;
    }

    public void setProvisioningMethod(String str) {
        this.provisioningMethod = str;
    }

    public void setRadio(String str) {
        this.radio = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setSoftwareVersionIndex(String str) {
        this.softwareVersionIndex = str;
    }

    public void setTrustMethod(String str) {
        this.trustMethod = str;
    }
}
