package org.webrtc;
/* loaded from: classes5.dex */
public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    final long nativeRtpSender;
    private boolean ownsTrack = true;

    public RtpSender(long j) {
        this.nativeRtpSender = j;
        long nativeGetTrack = nativeGetTrack(j);
        DtmfSender dtmfSender = null;
        this.cachedTrack = nativeGetTrack != 0 ? new MediaStreamTrack(nativeGetTrack) : null;
        long nativeGetDtmfSender = nativeGetDtmfSender(j);
        this.dtmfSender = nativeGetDtmfSender != 0 ? new DtmfSender(nativeGetDtmfSender) : dtmfSender;
    }

    private static native long nativeGetDtmfSender(long j);

    private static native RtpParameters nativeGetParameters(long j);

    private static native long nativeGetTrack(long j);

    private static native String nativeId(long j);

    private static native boolean nativeSetParameters(long j, RtpParameters rtpParameters);

    private static native boolean nativeSetTrack(long j, long j2);

    public void dispose() {
        DtmfSender dtmfSender = this.dtmfSender;
        if (dtmfSender != null) {
            dtmfSender.dispose();
        }
        MediaStreamTrack mediaStreamTrack = this.cachedTrack;
        if (mediaStreamTrack != null && this.ownsTrack) {
            mediaStreamTrack.dispose();
        }
        JniCommon.nativeReleaseRef(this.nativeRtpSender);
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpSender);
    }

    public String id() {
        return nativeId(this.nativeRtpSender);
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        return nativeSetParameters(this.nativeRtpSender, rtpParameters);
    }

    public boolean setTrack(MediaStreamTrack mediaStreamTrack, boolean z) {
        if (!nativeSetTrack(this.nativeRtpSender, mediaStreamTrack == null ? 0L : mediaStreamTrack.nativeTrack)) {
            return false;
        }
        MediaStreamTrack mediaStreamTrack2 = this.cachedTrack;
        if (mediaStreamTrack2 != null && this.ownsTrack) {
            mediaStreamTrack2.dispose();
        }
        this.cachedTrack = mediaStreamTrack;
        this.ownsTrack = z;
        return true;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
