package org.apache.commons.net.ftp.parser;
/* loaded from: classes4.dex */
public class ParserInitializationException extends RuntimeException {
    private final Throwable rootCause;

    public ParserInitializationException(String str) {
        super(str);
        this.rootCause = null;
    }

    public Throwable getRootCause() {
        return this.rootCause;
    }

    public ParserInitializationException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
    }
}
