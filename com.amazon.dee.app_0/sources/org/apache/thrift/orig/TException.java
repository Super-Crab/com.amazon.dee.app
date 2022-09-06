package org.apache.thrift.orig;
/* loaded from: classes4.dex */
public class TException extends Exception {
    private static final long serialVersionUID = 1;

    public TException() {
    }

    public TException(String str) {
        super(str);
    }

    public TException(Throwable th) {
        super(th);
    }

    public TException(String str, Throwable th) {
        super(str, th);
    }
}
