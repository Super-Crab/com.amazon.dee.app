package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartEcdheAuthenticationSessionOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.StartEcdheAuthenticationSessionOutput");
    private String continuationToken;
    private String ecdhePublicKey;
    private String ecdsaSignature;
    private String endpointToUse;

    public boolean equals(Object obj) {
        if (!(obj instanceof StartEcdheAuthenticationSessionOutput)) {
            return false;
        }
        StartEcdheAuthenticationSessionOutput startEcdheAuthenticationSessionOutput = (StartEcdheAuthenticationSessionOutput) obj;
        return Helper.equals(this.continuationToken, startEcdheAuthenticationSessionOutput.continuationToken) && Helper.equals(this.ecdsaSignature, startEcdheAuthenticationSessionOutput.ecdsaSignature) && Helper.equals(this.ecdhePublicKey, startEcdheAuthenticationSessionOutput.ecdhePublicKey) && Helper.equals(this.endpointToUse, startEcdheAuthenticationSessionOutput.endpointToUse);
    }

    public String getContinuationToken() {
        return this.continuationToken;
    }

    public String getEcdhePublicKey() {
        return this.ecdhePublicKey;
    }

    public String getEcdsaSignature() {
        return this.ecdsaSignature;
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.continuationToken, this.ecdsaSignature, this.ecdhePublicKey, this.endpointToUse);
    }

    public void setContinuationToken(String str) {
        this.continuationToken = str;
    }

    public void setEcdhePublicKey(String str) {
        this.ecdhePublicKey = str;
    }

    public void setEcdsaSignature(String str) {
        this.ecdsaSignature = str;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }
}
