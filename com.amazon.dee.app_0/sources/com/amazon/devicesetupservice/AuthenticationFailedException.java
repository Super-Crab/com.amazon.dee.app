package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class AuthenticationFailedException extends CoralException {
    private static final long serialVersionUID = -1;

    public AuthenticationFailedException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public AuthenticationFailedException(Throwable th) {
        initCause(th);
    }

    public AuthenticationFailedException(String str) {
        super(str);
    }

    public AuthenticationFailedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
