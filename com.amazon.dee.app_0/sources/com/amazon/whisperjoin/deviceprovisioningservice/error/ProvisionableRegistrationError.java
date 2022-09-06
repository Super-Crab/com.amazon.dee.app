package com.amazon.whisperjoin.deviceprovisioningservice.error;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ProvisionableRegistrationError extends ProvisioningFailureException implements Serializable {
    private final Integer mHttpCode;
    private final String mMessage;
    private final CBLRegistrationDetails.State mState;

    public ProvisionableRegistrationError(CBLRegistrationDetails cBLRegistrationDetails) {
        this.mMessage = cBLRegistrationDetails.getMessage();
        this.mHttpCode = cBLRegistrationDetails.getHttpCode();
        this.mState = cBLRegistrationDetails.getRegistrationState();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableRegistrationError.class != obj.getClass()) {
            return false;
        }
        ProvisionableRegistrationError provisionableRegistrationError = (ProvisionableRegistrationError) obj;
        return Objects.equal(this.mMessage, provisionableRegistrationError.mMessage) && Objects.equal(this.mHttpCode, provisionableRegistrationError.mHttpCode) && this.mState == provisionableRegistrationError.mState;
    }

    public Integer getHttpCode() {
        return this.mHttpCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.mMessage;
    }

    public CBLRegistrationDetails.State getState() {
        return this.mState;
    }

    public int hashCode() {
        return Objects.hashCode(this.mMessage, this.mHttpCode, this.mState);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mMessage", this.mMessage).add("mHttpCode", this.mHttpCode).add("mState", this.mState).toString();
    }
}
