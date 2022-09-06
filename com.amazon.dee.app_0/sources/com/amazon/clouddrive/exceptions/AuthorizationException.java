package com.amazon.clouddrive.exceptions;
@Deprecated
/* loaded from: classes11.dex */
public class AuthorizationException extends NoRetryException {
    public AuthorizationException() {
    }

    public AuthorizationException(Throwable th) {
        super(th);
    }

    public AuthorizationException(String str) {
        super(str);
    }

    public AuthorizationException(String str, Throwable th) {
        super(str, th);
    }

    public AuthorizationException(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
