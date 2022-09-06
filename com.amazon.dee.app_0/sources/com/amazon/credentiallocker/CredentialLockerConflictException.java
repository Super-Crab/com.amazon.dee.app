package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class CredentialLockerConflictException extends CoralException {
    private static final long serialVersionUID = -1;

    public CredentialLockerConflictException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public CredentialLockerConflictException(Throwable th) {
        initCause(th);
    }

    public CredentialLockerConflictException(String str) {
        super(str);
    }

    public CredentialLockerConflictException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
