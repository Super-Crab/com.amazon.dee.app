package com.amazon.whisperjoin.provisioning.security;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class KeyExchangeRequest implements Validatable {
    private final String publicKey;
    private final int scheme;

    public KeyExchangeRequest(String str, int i) {
        this.publicKey = str;
        this.scheme = i;
        validate();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof KeyExchangeRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KeyExchangeRequest)) {
            return false;
        }
        KeyExchangeRequest keyExchangeRequest = (KeyExchangeRequest) obj;
        if (!keyExchangeRequest.canEqual(this)) {
            return false;
        }
        String publicKey = getPublicKey();
        String publicKey2 = keyExchangeRequest.getPublicKey();
        if (publicKey != null ? !publicKey.equals(publicKey2) : publicKey2 != null) {
            return false;
        }
        return getScheme() == keyExchangeRequest.getScheme();
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public int getScheme() {
        return this.scheme;
    }

    public int hashCode() {
        String publicKey = getPublicKey();
        return getScheme() + (((publicKey == null ? 43 : publicKey.hashCode()) + 59) * 59);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("KeyExchangeRequest(publicKey=");
        outline107.append(getPublicKey());
        outline107.append(", scheme=");
        outline107.append(getScheme());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (InputValidator.isValidKeyExchangeRequest(this.publicKey)) {
            return;
        }
        throw new IllegalArgumentException("ECDH Public key cannot be empty. Please use InputValidator methods to validate parameters.");
    }
}
