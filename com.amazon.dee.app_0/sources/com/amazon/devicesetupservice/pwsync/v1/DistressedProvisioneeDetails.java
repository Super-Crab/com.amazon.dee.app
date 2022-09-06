package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeDetails;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
/* loaded from: classes12.dex */
public class DistressedProvisioneeDetails extends ProvisioneeDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.DistressedProvisioneeDetails");
    private int advertisedSdkVersionIndex;
    private String authMaterialIndex;
    private String connectivityErrorCode;
    private String networkState;
    private String productIndex;
    private ProvisionableInfo provisioneeInfo;
    private String radio;
    private int rssi;

    @Override // com.amazon.devicesetupservice.ProvisioneeDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof DistressedProvisioneeDetails)) {
            return false;
        }
        DistressedProvisioneeDetails distressedProvisioneeDetails = (DistressedProvisioneeDetails) obj;
        return super.equals(obj) && Helper.equals(this.networkState, distressedProvisioneeDetails.networkState) && Helper.equals(this.provisioneeInfo, distressedProvisioneeDetails.provisioneeInfo) && Helper.equals(this.connectivityErrorCode, distressedProvisioneeDetails.connectivityErrorCode) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(distressedProvisioneeDetails.rssi)) && Helper.equals(this.productIndex, distressedProvisioneeDetails.productIndex) && Helper.equals(this.authMaterialIndex, distressedProvisioneeDetails.authMaterialIndex) && Helper.equals(this.radio, distressedProvisioneeDetails.radio) && Helper.equals(Integer.valueOf(this.advertisedSdkVersionIndex), Integer.valueOf(distressedProvisioneeDetails.advertisedSdkVersionIndex));
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.advertisedSdkVersionIndex;
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getConnectivityErrorCode() {
        return this.connectivityErrorCode;
    }

    public String getNetworkState() {
        return this.networkState;
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
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.networkState, this.provisioneeInfo, this.connectivityErrorCode, Integer.valueOf(this.rssi), this.productIndex, this.authMaterialIndex, this.radio, Integer.valueOf(this.advertisedSdkVersionIndex));
    }

    public void setAdvertisedSdkVersionIndex(int i) {
        this.advertisedSdkVersionIndex = i;
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setConnectivityErrorCode(String str) {
        this.connectivityErrorCode = str;
    }

    public void setNetworkState(String str) {
        this.networkState = str;
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
