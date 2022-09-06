package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class RetryException extends CloudDriveException {
    private final int mHttpCode;

    public RetryException() {
        this.mHttpCode = -1;
    }

    @Override // com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return this.mHttpCode;
    }

    public RetryException(Throwable th) {
        super(th);
        this.mHttpCode = -1;
    }

    public RetryException(String str) {
        super(str);
        this.mHttpCode = -1;
    }

    public RetryException(String str, Throwable th) {
        super(str, th);
        this.mHttpCode = -1;
    }

    public RetryException(int i, String str, String str2, String str3) {
        super(str, str2, str3);
        this.mHttpCode = i;
    }
}
