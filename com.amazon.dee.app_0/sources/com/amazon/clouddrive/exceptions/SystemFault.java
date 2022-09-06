package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class SystemFault extends RetryException {
    private static final long serialVersionUID = -1;

    public SystemFault() {
    }

    @Override // com.amazon.clouddrive.exceptions.RetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 500;
    }

    public SystemFault(Throwable th) {
        super(th);
    }

    public SystemFault(String str) {
        super(str);
    }

    public SystemFault(String str, Throwable th) {
        super(str, th);
    }

    public SystemFault(String str, String str2, String str3) {
        super(500, str, str2, str3);
    }
}
