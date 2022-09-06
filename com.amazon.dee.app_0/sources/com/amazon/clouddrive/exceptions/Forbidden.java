package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class Forbidden extends NoRetryException {
    private static final long serialVersionUID = -1;

    public Forbidden() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 403;
    }

    public Forbidden(Throwable th) {
        super(th);
    }

    public Forbidden(String str) {
        super(str);
    }

    public Forbidden(String str, Throwable th) {
        super(str, th);
    }

    public Forbidden(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
