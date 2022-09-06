package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class AuthenticatedEcdheKeyExchangeRequest {
    private final byte[] mDerEncodedProvisionerPublicKey;
    private final byte[] mEcdsaSignature;

    public AuthenticatedEcdheKeyExchangeRequest(byte[] bArr, byte[] bArr2) {
        this.mDerEncodedProvisionerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.mEcdsaSignature = Arrays.copyOf(bArr2, bArr2.length);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthenticatedEcdheKeyExchangeRequest.class != obj.getClass()) {
            return false;
        }
        AuthenticatedEcdheKeyExchangeRequest authenticatedEcdheKeyExchangeRequest = (AuthenticatedEcdheKeyExchangeRequest) obj;
        return new EqualsBuilder().append(this.mDerEncodedProvisionerPublicKey, authenticatedEcdheKeyExchangeRequest.mDerEncodedProvisionerPublicKey).append(this.mEcdsaSignature, authenticatedEcdheKeyExchangeRequest.mEcdsaSignature).isEquals();
    }

    public byte[] getDerEncodedProvisionerPublicKey() {
        byte[] bArr = this.mDerEncodedProvisionerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getEcdsaSignature() {
        byte[] bArr = this.mEcdsaSignature;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mDerEncodedProvisionerPublicKey).append(this.mEcdsaSignature).toHashCode();
    }
}
