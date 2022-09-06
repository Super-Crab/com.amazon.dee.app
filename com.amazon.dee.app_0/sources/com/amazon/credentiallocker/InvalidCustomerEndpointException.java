package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class InvalidCustomerEndpointException extends CoralException {
    private static final long serialVersionUID = -1;

    public InvalidCustomerEndpointException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public InvalidCustomerEndpointException(Throwable th) {
        initCause(th);
    }

    public InvalidCustomerEndpointException(String str) {
        super(str);
    }

    public InvalidCustomerEndpointException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
