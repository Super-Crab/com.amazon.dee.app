package com.amazon.dee.app.services.metrics.kinesis.session;
/* loaded from: classes12.dex */
public class AppSessionStoreException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public AppSessionStoreException(String str) {
        super(str);
    }

    public AppSessionStoreException(Throwable th) {
        super(th);
    }

    public AppSessionStoreException(String str, Throwable th) {
        super(str, th);
    }
}
