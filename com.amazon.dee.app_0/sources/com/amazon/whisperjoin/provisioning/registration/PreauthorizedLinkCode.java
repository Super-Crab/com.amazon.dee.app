package com.amazon.whisperjoin.provisioning.registration;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import java.util.Locale;
/* loaded from: classes13.dex */
public class PreauthorizedLinkCode implements Validatable {
    private final long expiry;
    private final String linkCode;

    public PreauthorizedLinkCode(String str, long j) {
        this.linkCode = str;
        this.expiry = j;
        validate();
    }

    public long getExpiry() {
        return this.expiry;
    }

    public String getLinkCode() {
        return this.linkCode;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "PreauthorizedLinkCode(linkCode=%s, expiry=%d)", this.linkCode, Long.valueOf(this.expiry));
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (InputValidator.isValidPreauthorizedLinkCode(this.linkCode)) {
            return;
        }
        throw new IllegalArgumentException("Registration link code cannot be empty. Please use InputValidator methods to validate parameters.");
    }
}
