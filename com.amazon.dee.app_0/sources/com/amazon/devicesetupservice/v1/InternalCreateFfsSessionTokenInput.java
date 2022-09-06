package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalCreateFfsSessionTokenInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalCreateFfsSessionTokenInput");
    private String accessToken;
    private String customerId;
    private String deviceSerial;
    private String productIndex;
    private String publicKey;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalCreateFfsSessionTokenInput)) {
            return false;
        }
        InternalCreateFfsSessionTokenInput internalCreateFfsSessionTokenInput = (InternalCreateFfsSessionTokenInput) obj;
        return Helper.equals(this.accessToken, internalCreateFfsSessionTokenInput.accessToken) && Helper.equals(this.publicKey, internalCreateFfsSessionTokenInput.publicKey) && Helper.equals(this.customerId, internalCreateFfsSessionTokenInput.customerId) && Helper.equals(this.deviceSerial, internalCreateFfsSessionTokenInput.deviceSerial) && Helper.equals(this.productIndex, internalCreateFfsSessionTokenInput.productIndex);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getCustomerId() {
        return this.customerId;
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
        return Helper.hash(Integer.valueOf(classNameHashCode), this.accessToken, this.publicKey, this.customerId, this.deviceSerial, this.productIndex);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
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
