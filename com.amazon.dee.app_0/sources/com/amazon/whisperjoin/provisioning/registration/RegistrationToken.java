package com.amazon.whisperjoin.provisioning.registration;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
/* loaded from: classes13.dex */
public class RegistrationToken implements Validatable {
    private final long expiry;
    private final String token;

    public RegistrationToken(String str, long j) {
        this.token = str;
        this.expiry = j;
        validate();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RegistrationToken;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrationToken)) {
            return false;
        }
        RegistrationToken registrationToken = (RegistrationToken) obj;
        if (!registrationToken.canEqual(this)) {
            return false;
        }
        String token = getToken();
        String token2 = registrationToken.getToken();
        if (token != null ? !token.equals(token2) : token2 != null) {
            return false;
        }
        return getExpiry() == registrationToken.getExpiry();
    }

    public long getExpiry() {
        return this.expiry;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        String token = getToken();
        int hashCode = token == null ? 43 : token.hashCode();
        long expiry = getExpiry();
        return ((hashCode + 59) * 59) + ((int) ((expiry >>> 32) ^ expiry));
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (InputValidator.isValidRegistrationToken(this.token)) {
            return;
        }
        throw new IllegalArgumentException("Registration token cannot be empty. Please use InputValidator methods to validate parameters.");
    }
}
