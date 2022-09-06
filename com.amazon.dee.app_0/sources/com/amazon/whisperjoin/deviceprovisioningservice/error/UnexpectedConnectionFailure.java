package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class UnexpectedConnectionFailure extends ConnectionFailureException {
    private final int mGattStatus;

    public UnexpectedConnectionFailure(int i) {
        this.mGattStatus = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && UnexpectedConnectionFailure.class == obj.getClass() && this.mGattStatus == ((UnexpectedConnectionFailure) obj).mGattStatus;
    }

    public int getGattStatus() {
        return this.mGattStatus;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mGattStatus));
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mGattStatus", this.mGattStatus).toString();
    }
}
