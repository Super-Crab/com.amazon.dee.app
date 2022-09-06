package com.amazon.devicesetup.provisioning.data.crypto;

import com.amazon.devicesetup.utility.SecureMessage;
import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes12.dex */
public class AuthenticatedEcdheKeyExchangeResponse {
    private final byte[] provisionableDerPublicKey;
    private final SecureMessage secureMessage;

    public AuthenticatedEcdheKeyExchangeResponse(byte[] bArr, SecureMessage secureMessage) {
        this.provisionableDerPublicKey = Arrays.copyOf(bArr, bArr.length);
        this.secureMessage = secureMessage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthenticatedEcdheKeyExchangeResponse.class != obj.getClass()) {
            return false;
        }
        AuthenticatedEcdheKeyExchangeResponse authenticatedEcdheKeyExchangeResponse = (AuthenticatedEcdheKeyExchangeResponse) obj;
        return new EqualsBuilder().append(this.provisionableDerPublicKey, authenticatedEcdheKeyExchangeResponse.provisionableDerPublicKey).append(this.secureMessage, authenticatedEcdheKeyExchangeResponse.secureMessage).isEquals();
    }

    public byte[] getProvisionableDerPublicKey() {
        byte[] bArr = this.provisionableDerPublicKey;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public SecureMessage getSecureMessage() {
        return this.secureMessage;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.provisionableDerPublicKey).append(this.secureMessage).toHashCode();
    }
}
