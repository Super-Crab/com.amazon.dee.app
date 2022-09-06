package org.webrtc;

import org.webrtc.MediaStreamTrack;
/* loaded from: classes5.dex */
public class RtpReceiver {
    private MediaStreamTrack cachedTrack;
    private long nativeObserver;
    final long nativeRtpReceiver;

    /* loaded from: classes5.dex */
    public interface Observer {
        void onFirstPacketReceived(MediaStreamTrack.MediaType mediaType);
    }

    public RtpReceiver(long j) {
        this.nativeRtpReceiver = j;
        this.cachedTrack = new MediaStreamTrack(nativeGetTrack(j));
    }

    private static native RtpParameters nativeGetParameters(long j);

    private static native long nativeGetTrack(long j);

    private static native String nativeId(long j);

    private static native long nativeSetObserver(long j, Observer observer);

    private static native boolean nativeSetParameters(long j, RtpParameters rtpParameters);

    private static native long nativeUnsetObserver(long j, long j2);

    public void SetObserver(Observer observer) {
        long j = this.nativeObserver;
        if (j != 0) {
            nativeUnsetObserver(this.nativeRtpReceiver, j);
        }
        this.nativeObserver = nativeSetObserver(this.nativeRtpReceiver, observer);
    }

    public void dispose() {
        this.cachedTrack.dispose();
        long j = this.nativeObserver;
        if (j != 0) {
            nativeUnsetObserver(this.nativeRtpReceiver, j);
            this.nativeObserver = 0L;
        }
        JniCommon.nativeReleaseRef(this.nativeRtpReceiver);
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpReceiver);
    }

    public String id() {
        return nativeId(this.nativeRtpReceiver);
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        return nativeSetParameters(this.nativeRtpReceiver, rtpParameters);
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
