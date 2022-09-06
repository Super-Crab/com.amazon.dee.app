package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class InvalidPermissionException extends CoralException {
    private static final long serialVersionUID = -1;

    public InvalidPermissionException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public InvalidPermissionException(Throwable th) {
        initCause(th);
    }

    public InvalidPermissionException(String str) {
        super(str);
    }

    public InvalidPermissionException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
