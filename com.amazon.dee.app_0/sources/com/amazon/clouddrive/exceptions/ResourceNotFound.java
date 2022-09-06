package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class ResourceNotFound extends NoRetryException {
    private static final long serialVersionUID = -1;

    public ResourceNotFound() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 404;
    }

    public ResourceNotFound(Throwable th) {
        super(th);
    }

    public ResourceNotFound(String str) {
        super(str);
    }

    public ResourceNotFound(String str, Throwable th) {
        super(str, th);
    }

    public ResourceNotFound(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
