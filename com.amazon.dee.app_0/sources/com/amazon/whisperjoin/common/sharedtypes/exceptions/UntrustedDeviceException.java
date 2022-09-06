package com.amazon.whisperjoin.common.sharedtypes.exceptions;
/* loaded from: classes13.dex */
public class UntrustedDeviceException extends Exception {
    public UntrustedDeviceException(String str, Throwable th) {
        super(str, th);
    }

    public UntrustedDeviceException(String str) {
        this(str, null);
    }
}
