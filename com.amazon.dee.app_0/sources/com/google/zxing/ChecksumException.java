package com.google.zxing;
/* loaded from: classes3.dex */
public final class ChecksumException extends ReaderException {
    private static final ChecksumException INSTANCE = new ChecksumException();

    static {
        INSTANCE.setStackTrace(ReaderException.NO_TRACE);
    }

    private ChecksumException() {
    }

    public static ChecksumException getChecksumInstance() {
        return ReaderException.isStackTrace ? new ChecksumException() : INSTANCE;
    }

    private ChecksumException(Throwable th) {
        super(th);
    }

    public static ChecksumException getChecksumInstance(Throwable th) {
        return ReaderException.isStackTrace ? new ChecksumException(th) : INSTANCE;
    }
}
