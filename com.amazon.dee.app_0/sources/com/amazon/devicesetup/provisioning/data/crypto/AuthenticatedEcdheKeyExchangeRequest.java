package com.amazon.devicesetup.provisioning.data.crypto;

import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes12.dex */
public class AuthenticatedEcdheKeyExchangeRequest {
    private final byte[] derEncodedProvisionerPublicKey;
    private final byte[] ecdsaSignature;

    public AuthenticatedEcdheKeyExchangeRequest(byte[] bArr, byte[] bArr2) {
        this.derEncodedProvisionerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.ecdsaSignature = Arrays.copyOf(bArr2, bArr2.length);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthenticatedEcdheKeyExchangeRequest.class != obj.getClass()) {
            return false;
        }
        AuthenticatedEcdheKeyExchangeRequest authenticatedEcdheKeyExchangeRequest = (AuthenticatedEcdheKeyExchangeRequest) obj;
        return new EqualsBuilder().append(this.derEncodedProvisionerPublicKey, authenticatedEcdheKeyExchangeRequest.derEncodedProvisionerPublicKey).append(this.ecdsaSignature, authenticatedEcdheKeyExchangeRequest.ecdsaSignature).isEquals();
    }

    public byte[] getDerEncodedProvisionerPublicKey() {
        byte[] bArr = this.derEncodedProvisionerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getEcdsaSignature() {
        byte[] bArr = this.ecdsaSignature;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.derEncodedProvisionerPublicKey).append(this.ecdsaSignature).toHashCode();
    }
}
