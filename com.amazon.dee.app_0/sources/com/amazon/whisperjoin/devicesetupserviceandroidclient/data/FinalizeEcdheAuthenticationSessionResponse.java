package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.Objects;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class FinalizeEcdheAuthenticationSessionResponse {
    private final byte[] mSessionKeyDEREncoded;

    public FinalizeEcdheAuthenticationSessionResponse(byte[] bArr) {
        this.mSessionKeyDEREncoded = Arrays.copyOf(bArr, bArr.length);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FinalizeEcdheAuthenticationSessionResponse.class == obj.getClass()) {
            return Arrays.equals(this.mSessionKeyDEREncoded, ((FinalizeEcdheAuthenticationSessionResponse) obj).getSessionKeyDEREncoded());
        }
        return false;
    }

    public byte[] getSessionKeyDEREncoded() {
        return this.mSessionKeyDEREncoded;
    }

    public int hashCode() {
        return Objects.hashCode(this.mSessionKeyDEREncoded);
    }
}
