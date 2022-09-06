package com.amazon.whisperjoin.common.sharedtypes.exceptions;
/* loaded from: classes13.dex */
public class ScanException extends Exception {
    public ScanException(String str) {
        this(str, null);
    }

    public ScanException(String str, Throwable th) {
        super(str, th);
    }
}
