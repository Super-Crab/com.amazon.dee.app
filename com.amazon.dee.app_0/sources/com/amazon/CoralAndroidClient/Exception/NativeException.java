package com.amazon.CoralAndroidClient.Exception;
/* loaded from: classes.dex */
public class NativeException extends ClientException {
    private static final long serialVersionUID = 1;

    public NativeException(String str, Throwable th) {
        super(str, th);
    }

    public NativeException(String str) {
        super(str);
    }

    public NativeException(Throwable th) {
        super(th);
    }
}
