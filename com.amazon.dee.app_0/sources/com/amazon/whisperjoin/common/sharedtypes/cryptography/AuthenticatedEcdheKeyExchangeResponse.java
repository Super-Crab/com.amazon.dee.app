package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import com.amazon.whispercloak.SecureMessage;
import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class AuthenticatedEcdheKeyExchangeResponse {
    private final byte[] mProvisionableDerPublicKey;
    private final SecureMessage mSecureMessage;

    public AuthenticatedEcdheKeyExchangeResponse(byte[] bArr, SecureMessage secureMessage) {
        this.mProvisionableDerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.mSecureMessage = secureMessage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthenticatedEcdheKeyExchangeResponse.class != obj.getClass()) {
            return false;
        }
        AuthenticatedEcdheKeyExchangeResponse authenticatedEcdheKeyExchangeResponse = (AuthenticatedEcdheKeyExchangeResponse) obj;
        return new EqualsBuilder().append(this.mProvisionableDerPublicKey, authenticatedEcdheKeyExchangeResponse.mProvisionableDerPublicKey).append(this.mSecureMessage, authenticatedEcdheKeyExchangeResponse.mSecureMessage).isEquals();
    }

    public byte[] getProvisionableDerPublicKey() {
        byte[] bArr = this.mProvisionableDerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public SecureMessage getSecureMessage() {
        return this.mSecureMessage;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mProvisionableDerPublicKey).append(this.mSecureMessage).toHashCode();
    }
}
