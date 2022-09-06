package com.amazon.alexa.device.setup.echo.bouncycastle.util;
/* loaded from: classes.dex */
public class StoreException extends RuntimeException {
    private final Throwable _e;

    public StoreException(String str, Throwable th) {
        super(str);
        this._e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._e;
    }
}
