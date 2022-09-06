package com.amazon.opus;

import androidx.annotation.IntRange;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes13.dex */
public class OpusEncoder {
    public static final int OPUS_APPLICATION_AUDIO = 2049;
    public static final int OPUS_APPLICATION_RESTRICTED_LOWDELAY = 2051;
    public static final int OPUS_APPLICATION_VOIP = 2048;
    public static final int OPUS_AUTO = -1;
    public static final int OPUS_BITRATE_MAX = -1;
    public static final int OPUS_COMPLEXITY_MAX = 10;
    private long address;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes13.dex */
    public @interface ApplicationType {
    }

    static {
        System.loadLibrary("senz");
    }

    private native int nativeEncodeBytes(byte[] bArr, int i, byte[] bArr2);

    private native int nativeEncodeShorts(short[] sArr, int i, byte[] bArr);

    private native int nativeInitEncoder(int i, int i2, int i3);

    private native boolean nativeReleaseEncoder();

    private native int nativeSetBitrate(int i);

    private native int nativeSetComplexity(@IntRange(from = 0, to = 10) int i);

    private native int nativeUseVbr(int i);

    public void close() {
        nativeReleaseEncoder();
    }

    public int encode(short[] sArr, int i, byte[] bArr) {
        return OpusError.throwIfError(nativeEncodeShorts(sArr, i, bArr));
    }

    public void init(int i, int i2, int i3) {
        OpusError.throwIfError(nativeInitEncoder(i, i2, i3));
    }

    public void setBitrate(int i) {
        OpusError.throwIfError(nativeSetBitrate(i));
    }

    public void setComplexity(int i) {
        OpusError.throwIfError(nativeSetComplexity(i));
    }

    public void useVbr(int i) {
        OpusError.throwIfError(nativeUseVbr(i));
    }

    public int encode(byte[] bArr, int i, byte[] bArr2) {
        return OpusError.throwIfError(nativeEncodeBytes(bArr, i, bArr2));
    }
}
