package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class StartEcdheAuthenticationSessionResponse {
    private final String mContinuationToken;
    private final String mEcdsaSignatureBase64Encoded;
    private final String mProvisionerEcdhePublicKeyPemEncoded;

    public StartEcdheAuthenticationSessionResponse(String str, String str2, String str3) {
        this.mContinuationToken = str;
        this.mProvisionerEcdhePublicKeyPemEncoded = str2;
        this.mEcdsaSignatureBase64Encoded = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StartEcdheAuthenticationSessionResponse.class != obj.getClass()) {
            return false;
        }
        StartEcdheAuthenticationSessionResponse startEcdheAuthenticationSessionResponse = (StartEcdheAuthenticationSessionResponse) obj;
        return Objects.equal(this.mContinuationToken, startEcdheAuthenticationSessionResponse.mContinuationToken) && Objects.equal(this.mProvisionerEcdhePublicKeyPemEncoded, startEcdheAuthenticationSessionResponse.mProvisionerEcdhePublicKeyPemEncoded) && Objects.equal(this.mEcdsaSignatureBase64Encoded, startEcdheAuthenticationSessionResponse.mEcdsaSignatureBase64Encoded);
    }

    public String getContinuationToken() {
        return this.mContinuationToken;
    }

    public String getEcdsaSignatureBase64Encoded() {
        return this.mEcdsaSignatureBase64Encoded;
    }

    public String getProvisionerEcdhePublicKeyPemEncoded() {
        return this.mProvisionerEcdhePublicKeyPemEncoded;
    }

    public int hashCode() {
        return Objects.hashCode(this.mContinuationToken, this.mProvisionerEcdhePublicKeyPemEncoded, this.mEcdsaSignatureBase64Encoded);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mContinuationToken", this.mContinuationToken).add("mProvisionerEcdhePublicKeyPemEncoded", this.mProvisionerEcdhePublicKeyPemEncoded).add("mEcdsaSignatureBase64Encoded", this.mEcdsaSignatureBase64Encoded).toString();
    }
}
