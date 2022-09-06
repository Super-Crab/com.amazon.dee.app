package com.amazon.alexa.device.setup.echo.bouncycastle.cert;

import java.io.IOException;
/* loaded from: classes.dex */
public class CertIOException extends IOException {
    private Throwable cause;

    public CertIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertIOException(String str) {
        super(str);
    }
}
