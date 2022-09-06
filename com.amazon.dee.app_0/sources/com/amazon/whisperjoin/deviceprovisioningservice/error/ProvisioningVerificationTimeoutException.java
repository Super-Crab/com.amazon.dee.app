package com.amazon.whisperjoin.deviceprovisioningservice.error;
/* loaded from: classes13.dex */
public class ProvisioningVerificationTimeoutException extends ProvisioningFailureException {
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ProvisioningFailureException);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
