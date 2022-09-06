package com.amazon.alexa.accessory.capabilities.bulkdata.exceptions;

import com.amazon.alexa.accessory.protocol.Common;
/* loaded from: classes.dex */
public class BulkDataException extends RuntimeException {
    private final Throwable cause;
    private final Common.ErrorCode errorCode;
    private final String message;

    public BulkDataException(String str) {
        super(str);
        this.message = str;
        this.cause = null;
        this.errorCode = Common.ErrorCode.UNKNOWN;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public Common.ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public int getErrorCodeValue() {
        return this.errorCode.getNumber();
    }

    public String getErrorMessage() {
        return this.message;
    }

    public BulkDataException(String str, Common.ErrorCode errorCode) {
        super(str);
        this.message = str;
        this.cause = null;
        this.errorCode = errorCode;
    }

    public BulkDataException(String str, Throwable th) {
        super(str, th);
        this.message = str;
        this.cause = th;
        this.errorCode = Common.ErrorCode.UNKNOWN;
    }
}
