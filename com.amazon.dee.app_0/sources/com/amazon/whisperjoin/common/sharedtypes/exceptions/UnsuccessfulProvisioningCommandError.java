package com.amazon.whisperjoin.common.sharedtypes.exceptions;

import com.google.common.base.Objects;
import java.util.Locale;
/* loaded from: classes13.dex */
public class UnsuccessfulProvisioningCommandError extends Exception {
    private final int mStatusCode;
    private final String mUUID;

    public UnsuccessfulProvisioningCommandError(String str, int i) {
        super(String.format(Locale.ENGLISH, "Command: %s Failed with Status Code: %d", str, Integer.valueOf(i)));
        this.mUUID = str;
        this.mStatusCode = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UnsuccessfulProvisioningCommandError.class != obj.getClass()) {
            return false;
        }
        UnsuccessfulProvisioningCommandError unsuccessfulProvisioningCommandError = (UnsuccessfulProvisioningCommandError) obj;
        return this.mStatusCode == unsuccessfulProvisioningCommandError.mStatusCode && Objects.equal(this.mUUID, unsuccessfulProvisioningCommandError.mUUID);
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getUUID() {
        return this.mUUID;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mStatusCode), this.mUUID);
    }
}
