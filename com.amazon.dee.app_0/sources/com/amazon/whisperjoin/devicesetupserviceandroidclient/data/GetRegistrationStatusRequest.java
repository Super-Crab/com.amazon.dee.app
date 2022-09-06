package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AuthMaterialIdentifier;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.LegacyIdentifierData;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GetRegistrationStatusRequest {
    private final AuthMaterialIdentifier mAuthMaterialIdentifier;
    private final String mBarcodeIdentifier;
    private final LegacyIdentifierData mLegacyIdentifier;

    /* loaded from: classes13.dex */
    public static class AuthMaterialIdentifier extends com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AuthMaterialIdentifier {
        public AuthMaterialIdentifier(String str, String str2) {
            super(new AuthMaterialIdentifier.Builder().setAuthMaterialIndex(str).setProductIndex(str2));
        }
    }

    /* loaded from: classes13.dex */
    public static class LegacyIdentifierData extends com.amazon.whisperjoin.devicesetupserviceandroidclient.data.LegacyIdentifierData {
        public LegacyIdentifierData(String str, String str2) {
            super(new LegacyIdentifierData.Builder().setDsn(str).setDeviceType(str2));
        }
    }

    public GetRegistrationStatusRequest(LegacyIdentifierData legacyIdentifierData) {
        this.mLegacyIdentifier = legacyIdentifierData;
        this.mAuthMaterialIdentifier = null;
        this.mBarcodeIdentifier = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetRegistrationStatusRequest.class != obj.getClass()) {
            return false;
        }
        GetRegistrationStatusRequest getRegistrationStatusRequest = (GetRegistrationStatusRequest) obj;
        return Objects.equal(this.mLegacyIdentifier, getRegistrationStatusRequest.mLegacyIdentifier) && Objects.equal(this.mAuthMaterialIdentifier, getRegistrationStatusRequest.mAuthMaterialIdentifier) && Objects.equal(this.mBarcodeIdentifier, getRegistrationStatusRequest.mBarcodeIdentifier);
    }

    public AuthMaterialIdentifier getAuthMaterialIdentifier() {
        return this.mAuthMaterialIdentifier;
    }

    public String getBarcodeIdentifier() {
        return this.mBarcodeIdentifier;
    }

    public LegacyIdentifierData getLegacyIdentifier() {
        return this.mLegacyIdentifier;
    }

    public int hashCode() {
        return Objects.hashCode(this.mLegacyIdentifier, this.mAuthMaterialIdentifier, this.mBarcodeIdentifier);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mLegacyIdentifier", this.mLegacyIdentifier).add("mAuthMaterialIdentifier", this.mAuthMaterialIdentifier).add("mBarcodeIdentifier", this.mBarcodeIdentifier).toString();
    }

    public GetRegistrationStatusRequest(AuthMaterialIdentifier authMaterialIdentifier) {
        this.mLegacyIdentifier = null;
        this.mAuthMaterialIdentifier = authMaterialIdentifier;
        this.mBarcodeIdentifier = null;
    }

    public GetRegistrationStatusRequest(String str) {
        this.mLegacyIdentifier = null;
        this.mAuthMaterialIdentifier = null;
        this.mBarcodeIdentifier = str;
    }
}
