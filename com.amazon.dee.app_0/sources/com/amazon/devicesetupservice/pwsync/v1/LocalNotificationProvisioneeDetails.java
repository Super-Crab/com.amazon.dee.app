package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeDetails;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
/* loaded from: classes12.dex */
public class LocalNotificationProvisioneeDetails extends ProvisioneeDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.LocalNotificationProvisioneeDetails");
    private int advertisedSdkVersionIndex;
    private int advertisementVersion;
    private String authMaterialIndex;
    private int authenticationMode;
    private String connectivityErrorCode;
    private String nonce;
    private String productIndex;
    private ProvisionableInfo provisioneeInfo;
    private String radio;
    private int rssi;

    @Override // com.amazon.devicesetupservice.ProvisioneeDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof LocalNotificationProvisioneeDetails)) {
            return false;
        }
        LocalNotificationProvisioneeDetails localNotificationProvisioneeDetails = (LocalNotificationProvisioneeDetails) obj;
        return super.equals(obj) && Helper.equals(this.authMaterialIndex, localNotificationProvisioneeDetails.authMaterialIndex) && Helper.equals(this.productIndex, localNotificationProvisioneeDetails.productIndex) && Helper.equals(this.radio, localNotificationProvisioneeDetails.radio) && Helper.equals(Integer.valueOf(this.advertisementVersion), Integer.valueOf(localNotificationProvisioneeDetails.advertisementVersion)) && Helper.equals(Integer.valueOf(this.advertisedSdkVersionIndex), Integer.valueOf(localNotificationProvisioneeDetails.advertisedSdkVersionIndex)) && Helper.equals(this.nonce, localNotificationProvisioneeDetails.nonce) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(localNotificationProvisioneeDetails.rssi)) && Helper.equals(Integer.valueOf(this.authenticationMode), Integer.valueOf(localNotificationProvisioneeDetails.authenticationMode)) && Helper.equals(this.connectivityErrorCode, localNotificationProvisioneeDetails.connectivityErrorCode) && Helper.equals(this.provisioneeInfo, localNotificationProvisioneeDetails.provisioneeInfo);
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.advertisedSdkVersionIndex;
    }

    public int getAdvertisementVersion() {
        return this.advertisementVersion;
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public int getAuthenticationMode() {
        return this.authenticationMode;
    }

    public String getConnectivityErrorCode() {
        return this.connectivityErrorCode;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public ProvisionableInfo getProvisioneeInfo() {
        return this.provisioneeInfo;
    }

    public String getRadio() {
        return this.radio;
    }

    public int getRssi() {
        return this.rssi;
    }

    @Override // com.amazon.devicesetupservice.ProvisioneeDetails
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.authMaterialIndex, this.productIndex, this.radio, Integer.valueOf(this.advertisementVersion), Integer.valueOf(this.advertisedSdkVersionIndex), this.nonce, Integer.valueOf(this.rssi), Integer.valueOf(this.authenticationMode), this.connectivityErrorCode, this.provisioneeInfo);
    }

    public void setAdvertisedSdkVersionIndex(int i) {
        this.advertisedSdkVersionIndex = i;
    }

    public void setAdvertisementVersion(int i) {
        this.advertisementVersion = i;
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setAuthenticationMode(int i) {
        this.authenticationMode = i;
    }

    public void setConnectivityErrorCode(String str) {
        this.connectivityErrorCode = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setProvisioneeInfo(ProvisionableInfo provisionableInfo) {
        this.provisioneeInfo = provisionableInfo;
    }

    public void setRadio(String str) {
        this.radio = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }
}
