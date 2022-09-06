package com.amazon.whisperjoin.provisioning.security;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class KeyExchangeResponse implements Validatable {
    private final String publicKey;

    public KeyExchangeResponse(String str) {
        this.publicKey = str;
        validate();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof KeyExchangeResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KeyExchangeResponse)) {
            return false;
        }
        KeyExchangeResponse keyExchangeResponse = (KeyExchangeResponse) obj;
        if (!keyExchangeResponse.canEqual(this)) {
            return false;
        }
        String publicKey = getPublicKey();
        String publicKey2 = keyExchangeResponse.getPublicKey();
        return publicKey != null ? publicKey.equals(publicKey2) : publicKey2 == null;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        String publicKey = getPublicKey();
        return 59 + (publicKey == null ? 43 : publicKey.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("KeyExchangeResponse(publicKey=");
        outline107.append(getPublicKey());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (InputValidator.isValidKeyExchangeResponse(this.publicKey)) {
            return;
        }
        throw new IllegalArgumentException("ECDH Public key cannot be empty. Please use InputValidator methods to validate parameters.");
    }
}
