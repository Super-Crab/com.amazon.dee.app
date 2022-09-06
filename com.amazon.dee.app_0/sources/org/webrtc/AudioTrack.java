package org.webrtc;
/* loaded from: classes5.dex */
public class AudioTrack extends MediaStreamTrack {
    public AudioTrack(long j) {
        super(j);
    }

    private static native void nativeSetVolume(long j, double d);

    public void setVolume(double d) {
        nativeSetVolume(this.nativeTrack, d);
    }
}
