package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ProvisionableProvisioningDoneFailureException extends ProvisioningFailureException {
    private final ProvisioningFailure mProvisioningFailure;

    public ProvisionableProvisioningDoneFailureException(ProvisioningFailure provisioningFailure) {
        this.mProvisioningFailure = provisioningFailure;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ProvisionableProvisioningDoneFailureException.class == obj.getClass()) {
            return Objects.equal(this.mProvisioningFailure, ((ProvisionableProvisioningDoneFailureException) obj).mProvisioningFailure);
        }
        return false;
    }

    public ProvisioningFailure getProvisioningFailure() {
        return this.mProvisioningFailure;
    }

    public int hashCode() {
        return Objects.hashCode(this.mProvisioningFailure);
    }
}
