package com.facebook.common.util;
/* loaded from: classes2.dex */
public class ExceptionWithNoStacktrace extends Exception {
    public ExceptionWithNoStacktrace(String detailMessage) {
        super(detailMessage);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
