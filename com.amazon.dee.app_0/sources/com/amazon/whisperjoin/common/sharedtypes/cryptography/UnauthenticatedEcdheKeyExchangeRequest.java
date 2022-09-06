package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class UnauthenticatedEcdheKeyExchangeRequest {
    private final byte[] mDerPublicKey;
    private final byte[] mEcdsaSignature;

    public UnauthenticatedEcdheKeyExchangeRequest(byte[] bArr) {
        this.mDerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.mEcdsaSignature = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnauthenticatedEcdheKeyExchangeRequest)) {
            return false;
        }
        UnauthenticatedEcdheKeyExchangeRequest unauthenticatedEcdheKeyExchangeRequest = (UnauthenticatedEcdheKeyExchangeRequest) obj;
        return new EqualsBuilder().append(this.mDerPublicKey, unauthenticatedEcdheKeyExchangeRequest.mDerPublicKey).append(this.mEcdsaSignature, unauthenticatedEcdheKeyExchangeRequest.mEcdsaSignature).isEquals();
    }

    public byte[] getDerPublicKey() {
        byte[] bArr = this.mDerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getEcdsaSignature() {
        byte[] bArr = this.mEcdsaSignature;
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return null;
    }

    public boolean hasEcdsaSignature() {
        return this.mEcdsaSignature != null;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mDerPublicKey).append(this.mEcdsaSignature).toHashCode();
    }

    public UnauthenticatedEcdheKeyExchangeRequest(byte[] bArr, byte[] bArr2) {
        this.mDerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.mEcdsaSignature = Arrays.copyOf(bArr2, bArr2.length);
    }
}
