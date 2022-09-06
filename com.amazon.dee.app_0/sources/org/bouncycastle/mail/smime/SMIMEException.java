package org.bouncycastle.mail.smime;
/* loaded from: classes4.dex */
public class SMIMEException extends Exception {
    Exception e;

    public SMIMEException(String str) {
        super(str);
    }

    public SMIMEException(String str, Exception exc) {
        super(str);
        this.e = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.e;
    }

    public Exception getUnderlyingException() {
        return this.e;
    }
}
