package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class NotFoundException extends CoralException {
    private static final long serialVersionUID = -1;

    public NotFoundException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public NotFoundException(Throwable th) {
        initCause(th);
    }

    public NotFoundException(String str) {
        super(str);
    }

    public NotFoundException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
