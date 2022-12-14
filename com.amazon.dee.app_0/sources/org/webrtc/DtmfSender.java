package org.webrtc;
/* loaded from: classes5.dex */
public class DtmfSender {
    final long nativeDtmfSender;

    public DtmfSender(long j) {
        this.nativeDtmfSender = j;
    }

    private static native boolean nativeCanInsertDtmf(long j);

    private static native int nativeDuration(long j);

    private static native boolean nativeInsertDtmf(long j, String str, int i, int i2);

    private static native int nativeInterToneGap(long j);

    private static native String nativeTones(long j);

    public boolean canInsertDtmf() {
        return nativeCanInsertDtmf(this.nativeDtmfSender);
    }

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeDtmfSender);
    }

    public int duration() {
        return nativeDuration(this.nativeDtmfSender);
    }

    public boolean insertDtmf(String str, int i, int i2) {
        return nativeInsertDtmf(this.nativeDtmfSender, str, i, i2);
    }

    public int interToneGap() {
        return nativeInterToneGap(this.nativeDtmfSender);
    }

    public String tones() {
        return nativeTones(this.nativeDtmfSender);
    }
}
