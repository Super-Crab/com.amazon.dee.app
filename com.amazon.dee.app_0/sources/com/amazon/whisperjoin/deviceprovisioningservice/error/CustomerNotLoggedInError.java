package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.google.common.base.MoreObjects;
/* loaded from: classes13.dex */
public class CustomerNotLoggedInError extends PreconditionFailureException {
    public boolean equals(Object obj) {
        return obj != null && CustomerNotLoggedInError.class == obj.getClass();
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}
