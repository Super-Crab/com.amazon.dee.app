package com.dp.framework;
/* loaded from: classes2.dex */
public class CodecException extends Exception {
    private static final long serialVersionUID = 1;

    public CodecException(Exception exc) {
        super(exc);
    }

    public CodecException(String str, Exception exc) {
        super(str, exc);
    }

    public CodecException(String str) {
        super(str);
    }
}
