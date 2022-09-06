package com.amazon.devicesetup.provisioning.data.crypto;

import java.util.Arrays;
/* loaded from: classes12.dex */
public class UnauthenticatedEcdheKeyExchangeResponse {
    private final byte[] derPublicKey;

    public UnauthenticatedEcdheKeyExchangeResponse(byte[] bArr) {
        this.derPublicKey = Arrays.copyOf(bArr, bArr.length);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UnauthenticatedEcdheKeyExchangeResponse)) {
            return false;
        }
        return Arrays.equals(this.derPublicKey, ((UnauthenticatedEcdheKeyExchangeResponse) obj).getDerPublicKey());
    }

    public byte[] getDerPublicKey() {
        byte[] bArr = this.derPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int hashCode() {
        return Arrays.hashCode(this.derPublicKey);
    }
}
