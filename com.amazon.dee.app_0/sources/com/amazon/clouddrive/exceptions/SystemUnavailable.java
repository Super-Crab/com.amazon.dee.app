package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class SystemUnavailable extends RetryException {
    private static final long serialVersionUID = -1;

    public SystemUnavailable() {
    }

    @Override // com.amazon.clouddrive.exceptions.RetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 503;
    }

    public SystemUnavailable(Throwable th) {
        super(th);
    }

    public SystemUnavailable(String str) {
        super(str);
    }

    public SystemUnavailable(String str, Throwable th) {
        super(str, th);
    }

    public SystemUnavailable(String str, String str2, String str3) {
        super(503, str, str2, str3);
    }
}
