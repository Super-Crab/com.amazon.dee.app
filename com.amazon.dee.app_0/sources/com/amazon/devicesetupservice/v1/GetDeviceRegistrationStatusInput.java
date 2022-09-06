package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetDeviceRegistrationStatusInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusInput");
    private String accessToken;
    private AuthMaterialIdentifier authMaterialIdentifier;
    private String barcodeIdentifier;
    private LegacyIdentifier legacyIdentifier;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetDeviceRegistrationStatusInput)) {
            return false;
        }
        GetDeviceRegistrationStatusInput getDeviceRegistrationStatusInput = (GetDeviceRegistrationStatusInput) obj;
        return Helper.equals(this.legacyIdentifier, getDeviceRegistrationStatusInput.legacyIdentifier) && Helper.equals(this.barcodeIdentifier, getDeviceRegistrationStatusInput.barcodeIdentifier) && Helper.equals(this.accessToken, getDeviceRegistrationStatusInput.accessToken) && Helper.equals(this.authMaterialIdentifier, getDeviceRegistrationStatusInput.authMaterialIdentifier);
    }

    public String getAccessToken() {
        return this.accessToken;
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

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.legacyIdentifier, this.barcodeIdentifier, this.accessToken, this.authMaterialIdentifier);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
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
