package com.amazon.device.messaging;

import java.io.IOException;
/* loaded from: classes12.dex */
public class ADMRegistrationException extends IOException {
    private final ErrorCode mErrorCode;
    private final String mErrorDescription;

    /* loaded from: classes12.dex */
    public enum ErrorCode {
        MainThread("MAIN_THREAD"),
        AuthenticationFailed(ADMConstants.ERROR_AUTHENTICATION_FAILED),
        ServiceNotAvailable(ADMConstants.ERROR_SERVICE_NOT_AVAILABLE),
        InvalidSender(ADMConstants.ERROR_INVALID_SENDER),
        InvalidParameters("INVALID_PARAMETERS"),
        InvalidManifest("INVALID_MANIFEST"),
        InternalError("INTERNAL_ERROR");
        
        final String mValue;

        ErrorCode(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public ADMRegistrationException(ErrorCode errorCode) {
        this(errorCode, (Throwable) null);
    }

    public ErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String str = this.mErrorDescription;
        return str == null ? this.mErrorCode.getValue() : String.format("%s - %s", this.mErrorCode, str);
    }

    public ADMRegistrationException(ErrorCode errorCode, Throwable th) {
        this(errorCode, null, th);
    }

    public ADMRegistrationException(ErrorCode errorCode, String str) {
        this(errorCode, str, null);
    }

    public ADMRegistrationException(ErrorCode errorCode, String str, Throwable th) {
        super(th);
        this.mErrorCode = errorCode;
        this.mErrorDescription = str;
    }
}
