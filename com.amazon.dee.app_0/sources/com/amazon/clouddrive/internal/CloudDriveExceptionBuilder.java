package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.BadToken;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.ConflictError;
import com.amazon.clouddrive.exceptions.Forbidden;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.exceptions.NoRetryException;
import com.amazon.clouddrive.exceptions.PreConditionFailure;
import com.amazon.clouddrive.exceptions.ResourceNotFound;
import com.amazon.clouddrive.exceptions.RetryException;
import com.amazon.clouddrive.exceptions.SystemFault;
import com.amazon.clouddrive.exceptions.SystemUnavailable;
import com.amazon.clouddrive.exceptions.TooManyRequests;
/* loaded from: classes11.dex */
public class CloudDriveExceptionBuilder {
    public static final int TOO_MANY_REQUEST_CODE = 429;
    private String mLogRef;
    private String mMessage;
    private String mNodeId;
    private final int mResponseCode;
    private String mServerErrorCode;
    private String mTimestamp;

    public CloudDriveExceptionBuilder(int i) {
        this.mResponseCode = i;
    }

    public CloudDriveException build() {
        int i = this.mResponseCode;
        if (i != 400) {
            if (i == 401) {
                return new BadToken(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 403) {
                return new Forbidden(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 404) {
                return new ResourceNotFound(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 409) {
                return new ConflictError(this.mMessage, this.mServerErrorCode, this.mLogRef, this.mNodeId);
            }
            if (i == 412) {
                return new PreConditionFailure(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 429) {
                return new TooManyRequests(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 500) {
                return new SystemFault(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            if (i == 503) {
                return new SystemUnavailable(this.mMessage, this.mServerErrorCode, this.mLogRef);
            }
            return (i < 500 || i >= 600) ? new NoRetryException(this.mResponseCode, this.mMessage, this.mServerErrorCode, this.mLogRef) : new RetryException(i, this.mMessage, this.mServerErrorCode, this.mLogRef);
        }
        return new InvalidParameter(this.mMessage, this.mServerErrorCode, this.mLogRef);
    }

    public CloudDriveExceptionBuilder withConflictNodeId(String str) {
        this.mNodeId = str;
        return this;
    }

    public CloudDriveExceptionBuilder withLogRef(String str) {
        this.mLogRef = str;
        return this;
    }

    public CloudDriveExceptionBuilder withMessage(String str) {
        this.mMessage = str;
        return this;
    }

    public CloudDriveExceptionBuilder withServerErrorCode(String str) {
        this.mServerErrorCode = str;
        return this;
    }

    public CloudDriveExceptionBuilder withTimestamp(String str) {
        this.mTimestamp = str;
        return this;
    }
}
