package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class NoRetryException extends CloudDriveException {
    private final int mHttpCode;

    public NoRetryException() {
        this.mHttpCode = -1;
    }

    @Override // com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return this.mHttpCode;
    }

    public NoRetryException(Throwable th) {
        super(th);
        this.mHttpCode = -1;
    }

    public NoRetryException(String str) {
        super(str);
        this.mHttpCode = -1;
    }

    public NoRetryException(String str, Throwable th) {
        super(str, th);
        this.mHttpCode = -1;
    }

    public NoRetryException(String str, String str2, String str3) {
        super(str, str2, str3);
        this.mHttpCode = -1;
    }

    public NoRetryException(int i, String str, String str2, String str3) {
        super(str, str2, str3);
        this.mHttpCode = i;
    }
}
