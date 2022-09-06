package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
/* loaded from: classes12.dex */
public class AuthMaterialIdentifier extends ProvisioneeIdentifier implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.AuthMaterialIdentifier");
    private String authMaterialIndex;
    private String productIndex;

    @Override // com.amazon.devicesetupservice.ProvisioneeIdentifier
    public boolean equals(Object obj) {
        if (!(obj instanceof AuthMaterialIdentifier)) {
            return false;
        }
        AuthMaterialIdentifier authMaterialIdentifier = (AuthMaterialIdentifier) obj;
        return super.equals(obj) && Helper.equals(this.productIndex, authMaterialIdentifier.productIndex) && Helper.equals(this.authMaterialIndex, authMaterialIdentifier.authMaterialIndex);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    @Override // com.amazon.devicesetupservice.ProvisioneeIdentifier
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.productIndex, this.authMaterialIndex);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }
}
