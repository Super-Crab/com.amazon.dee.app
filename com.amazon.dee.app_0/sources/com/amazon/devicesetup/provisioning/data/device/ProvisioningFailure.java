package com.amazon.devicesetup.provisioning.data.device;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class ProvisioningFailure {
    private final int errorCode;
    private final String message;

    public ProvisioningFailure(int i) {
        this(i, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisioningFailure.class != obj.getClass()) {
            return false;
        }
        ProvisioningFailure provisioningFailure = (ProvisioningFailure) obj;
        return this.errorCode == provisioningFailure.errorCode && Objects.equal(this.message, provisioningFailure.message);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.errorCode), this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("errorCode", this.errorCode).add("message", this.message).toString();
    }

    public ProvisioningFailure(int i, String str) {
        this.errorCode = i;
        this.message = str;
    }
}
