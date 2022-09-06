package org.bouncycastle.openpgp;
/* loaded from: classes5.dex */
public class PGPRuntimeOperationException extends RuntimeException {
    private final Throwable cause;

    public PGPRuntimeOperationException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
