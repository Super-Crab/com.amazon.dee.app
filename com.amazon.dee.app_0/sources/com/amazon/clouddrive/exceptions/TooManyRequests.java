package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class TooManyRequests extends NoRetryException {
    private static final int TOO_MANY_REQUEST_CODE = 429;
    private static final long serialVersionUID = -1;

    public TooManyRequests() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 429;
    }

    public TooManyRequests(Throwable th) {
        super(th);
    }

    public TooManyRequests(String str) {
        super(str);
    }

    public TooManyRequests(String str, Throwable th) {
        super(str, th);
    }

    public TooManyRequests(String str, String str2, String str3) {
        super(429, str, str2, str3);
    }
}
