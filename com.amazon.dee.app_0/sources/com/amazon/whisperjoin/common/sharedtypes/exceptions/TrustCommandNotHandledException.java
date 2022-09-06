package com.amazon.whisperjoin.common.sharedtypes.exceptions;
/* loaded from: classes13.dex */
public class TrustCommandNotHandledException extends Exception {
    public TrustCommandNotHandledException(String str, Throwable th) {
        super(str, th);
    }

    public TrustCommandNotHandledException(Throwable th) {
        this("Trust command could not be handled", th);
    }

    public TrustCommandNotHandledException() {
        this(null);
    }
}
