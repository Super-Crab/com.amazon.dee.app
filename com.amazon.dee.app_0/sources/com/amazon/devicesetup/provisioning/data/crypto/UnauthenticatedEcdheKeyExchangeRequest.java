package com.amazon.devicesetup.provisioning.data.crypto;

import java.util.Arrays;
/* loaded from: classes12.dex */
public class UnauthenticatedEcdheKeyExchangeRequest {
    private final byte[] mDerPublicKey;

    public UnauthenticatedEcdheKeyExchangeRequest(byte[] bArr) {
        this.mDerPublicKey = Arrays.copyOf(bArr, bArr.length);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UnauthenticatedEcdheKeyExchangeRequest)) {
            return false;
        }
        return Arrays.equals(this.mDerPublicKey, ((UnauthenticatedEcdheKeyExchangeRequest) obj).getDerPublicKey());
    }

    public byte[] getDerPublicKey() {
        byte[] bArr = this.mDerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mDerPublicKey);
    }
}
