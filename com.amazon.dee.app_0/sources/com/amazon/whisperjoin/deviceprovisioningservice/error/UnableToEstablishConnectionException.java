package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class UnableToEstablishConnectionException extends ConnectionFailureException {
    private final Throwable mFailureCause;

    public UnableToEstablishConnectionException(Throwable th) {
        this.mFailureCause = th;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && UnableToEstablishConnectionException.class == obj.getClass()) {
            return Objects.equal(this.mFailureCause, ((UnableToEstablishConnectionException) obj).mFailureCause);
        }
        return false;
    }

    public Throwable getFailureCause() {
        return this.mFailureCause;
    }

    public int hashCode() {
        return Objects.hashCode(this.mFailureCause);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mFailureCause", this.mFailureCause).toString();
    }
}
