package com.fasterxml.jackson.core;

import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class JacksonException extends IOException {
    private static final long serialVersionUID = 123;

    /* JADX INFO: Access modifiers changed from: protected */
    public JacksonException(String str) {
        super(str);
    }

    public abstract JsonLocation getLocation();

    public abstract String getOriginalMessage();

    /* renamed from: getProcessor */
    public abstract Object mo7003getProcessor();

    protected JacksonException(Throwable th) {
        super(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JacksonException(String str, Throwable th) {
        super(str, th);
    }
}
