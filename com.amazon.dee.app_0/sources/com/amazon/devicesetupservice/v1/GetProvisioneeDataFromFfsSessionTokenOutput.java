package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetProvisioneeDataFromFfsSessionTokenOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetProvisioneeDataFromFfsSessionTokenOutput");
    private String accessToken;
    private String authMaterialIndex;
    private String customerId;
    private String deviceAssociationMethod;
    private String deviceSerial;
    private String productIndex;
    private String publicKey;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetProvisioneeDataFromFfsSessionTokenOutput)) {
            return false;
        }
        GetProvisioneeDataFromFfsSessionTokenOutput getProvisioneeDataFromFfsSessionTokenOutput = (GetProvisioneeDataFromFfsSessionTokenOutput) obj;
        return Helper.equals(this.authMaterialIndex, getProvisioneeDataFromFfsSessionTokenOutput.authMaterialIndex) && Helper.equals(this.productIndex, getProvisioneeDataFromFfsSessionTokenOutput.productIndex) && Helper.equals(this.publicKey, getProvisioneeDataFromFfsSessionTokenOutput.publicKey) && Helper.equals(this.deviceAssociationMethod, getProvisioneeDataFromFfsSessionTokenOutput.deviceAssociationMethod) && Helper.equals(this.customerId, getProvisioneeDataFromFfsSessionTokenOutput.customerId) && Helper.equals(this.accessToken, getProvisioneeDataFromFfsSessionTokenOutput.accessToken) && Helper.equals(this.deviceSerial, getProvisioneeDataFromFfsSessionTokenOutput.deviceSerial);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceAssociationMethod() {
        return this.deviceAssociationMethod;
    }

    public String getDeviceSerial() {
        return this.deviceSerial;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.authMaterialIndex, this.productIndex, this.publicKey, this.deviceAssociationMethod, this.customerId, this.accessToken, this.deviceSerial);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setDeviceAssociationMethod(String str) {
        this.deviceAssociationMethod = str;
    }

    public void setDeviceSerial(String str) {
        this.deviceSerial = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }
}
