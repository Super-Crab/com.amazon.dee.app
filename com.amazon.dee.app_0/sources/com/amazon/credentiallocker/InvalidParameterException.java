package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class InvalidParameterException extends CoralException {
    private static final long serialVersionUID = -1;

    public InvalidParameterException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public InvalidParameterException(Throwable th) {
        initCause(th);
    }

    public InvalidParameterException(String str) {
        super(str);
    }

    public InvalidParameterException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
