package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetProvisionableStatusInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetProvisionableStatusInput");
    private AuthMaterialIdentifier authMaterialIdentifier;
    private String barcodeIdentifier;
    private LegacyIdentifier legacyIdentifier;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetProvisionableStatusInput)) {
            return false;
        }
        GetProvisionableStatusInput getProvisionableStatusInput = (GetProvisionableStatusInput) obj;
        return super.equals(obj) && Helper.equals(this.barcodeIdentifier, getProvisionableStatusInput.barcodeIdentifier) && Helper.equals(this.legacyIdentifier, getProvisionableStatusInput.legacyIdentifier) && Helper.equals(this.authMaterialIdentifier, getProvisionableStatusInput.authMaterialIdentifier);
    }

    public AuthMaterialIdentifier getAuthMaterialIdentifier() {
        return this.authMaterialIdentifier;
    }

    public String getBarcodeIdentifier() {
        return this.barcodeIdentifier;
    }

    public LegacyIdentifier getLegacyIdentifier() {
        return this.legacyIdentifier;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.barcodeIdentifier, this.legacyIdentifier, this.authMaterialIdentifier);
    }

    public void setAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
        this.authMaterialIdentifier = authMaterialIdentifier;
    }

    public void setBarcodeIdentifier(String str) {
        this.barcodeIdentifier = str;
    }

    public void setLegacyIdentifier(LegacyIdentifier legacyIdentifier) {
        this.legacyIdentifier = legacyIdentifier;
    }
}
