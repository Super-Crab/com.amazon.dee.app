package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class InvalidTokenException extends CoralException {
    private static final long serialVersionUID = -1;

    public InvalidTokenException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public InvalidTokenException(Throwable th) {
        initCause(th);
    }

    public InvalidTokenException(String str) {
        super(str);
    }

    public InvalidTokenException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
