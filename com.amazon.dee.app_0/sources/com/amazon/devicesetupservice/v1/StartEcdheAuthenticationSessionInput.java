package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartEcdheAuthenticationSessionInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.StartEcdheAuthenticationSessionInput");
    private String authMaterialIndex;
    private String nonce;
    private String productIndex;
    private String softwareVersionIndex;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof StartEcdheAuthenticationSessionInput)) {
            return false;
        }
        StartEcdheAuthenticationSessionInput startEcdheAuthenticationSessionInput = (StartEcdheAuthenticationSessionInput) obj;
        return super.equals(obj) && Helper.equals(this.softwareVersionIndex, startEcdheAuthenticationSessionInput.softwareVersionIndex) && Helper.equals(this.authMaterialIndex, startEcdheAuthenticationSessionInput.authMaterialIndex) && Helper.equals(this.nonce, startEcdheAuthenticationSessionInput.nonce) && Helper.equals(this.productIndex, startEcdheAuthenticationSessionInput.productIndex);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getSoftwareVersionIndex() {
        return this.softwareVersionIndex;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.softwareVersionIndex, this.authMaterialIndex, this.nonce, this.productIndex);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setSoftwareVersionIndex(String str) {
        this.softwareVersionIndex = str;
    }
}
