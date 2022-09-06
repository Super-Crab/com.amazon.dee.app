package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class FinalizeEcdheAuthenticationSessionRequest {
    private final byte[] mAuthEcdheKeyExchangeResponse;
    private final String mContinuationToken;

    public FinalizeEcdheAuthenticationSessionRequest(String str, byte[] bArr) {
        if (str != null) {
            if (bArr != null) {
                this.mContinuationToken = str;
                this.mAuthEcdheKeyExchangeResponse = Arrays.copyOf(bArr, bArr.length);
                return;
            }
            throw new IllegalArgumentException("Key exchange response can not be null");
        }
        throw new IllegalArgumentException("Continuation Token can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FinalizeEcdheAuthenticationSessionRequest.class != obj.getClass()) {
            return false;
        }
        FinalizeEcdheAuthenticationSessionRequest finalizeEcdheAuthenticationSessionRequest = (FinalizeEcdheAuthenticationSessionRequest) obj;
        return Objects.equal(this.mContinuationToken, finalizeEcdheAuthenticationSessionRequest.mContinuationToken) && Objects.equal(this.mAuthEcdheKeyExchangeResponse, finalizeEcdheAuthenticationSessionRequest.mAuthEcdheKeyExchangeResponse);
    }

    public byte[] getAuthEcdheKeyExchangeResponse() {
        byte[] bArr = this.mAuthEcdheKeyExchangeResponse;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getContinuationToken() {
        return this.mContinuationToken;
    }

    public int hashCode() {
        return Objects.hashCode(this.mContinuationToken, this.mAuthEcdheKeyExchangeResponse);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mContinuationToken", this.mContinuationToken).add("mAuthEcdheKeyExchangeResponse", this.mAuthEcdheKeyExchangeResponse).toString();
    }
}
