package com.amazon.opus;
/* loaded from: classes13.dex */
public class OpusDecoder {
    private long address;

    static {
        System.loadLibrary("senz");
    }

    private native int nativeDecodeBytes(byte[] bArr, byte[] bArr2, int i);

    private native int nativeDecodeShorts(byte[] bArr, short[] sArr, int i);

    private native int nativeInitDecoder(int i, int i2);

    private native boolean nativeReleaseDecoder();

    public void close() {
        nativeReleaseDecoder();
    }

    public int decode(byte[] bArr, short[] sArr, int i) {
        return OpusError.throwIfError(nativeDecodeShorts(bArr, sArr, i));
    }

    public void init(int i, int i2) {
        OpusError.throwIfError(nativeInitDecoder(i, i2));
    }

    public int decode(byte[] bArr, byte[] bArr2, int i) {
        return OpusError.throwIfError(nativeDecodeBytes(bArr, bArr2, i));
    }
}
