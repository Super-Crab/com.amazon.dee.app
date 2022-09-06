package com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data;
/* loaded from: classes13.dex */
public class ExceptionData {
    private final byte[] mData;
    private final Exception mException;

    public ExceptionData(byte[] bArr, Exception exc) {
        if (exc != null) {
            this.mData = bArr == null ? null : (byte[]) bArr.clone();
            this.mException = exc;
            return;
        }
        throw new IllegalArgumentException("throwable can not be null");
    }

    public byte[] getData() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public Exception getException() {
        return this.mException;
    }
}
