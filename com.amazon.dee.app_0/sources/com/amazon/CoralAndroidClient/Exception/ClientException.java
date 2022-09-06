package com.amazon.CoralAndroidClient.Exception;
/* loaded from: classes.dex */
public class ClientException extends Exception {
    private static final long serialVersionUID = 1;

    public ClientException() {
    }

    public ClientException(String str, Throwable th) {
        super(str, th);
    }

    public ClientException(String str) {
        super(str);
    }

    public ClientException(Throwable th) {
        super(th);
    }
}
