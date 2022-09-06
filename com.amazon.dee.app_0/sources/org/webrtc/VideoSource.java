package org.webrtc;
/* loaded from: classes5.dex */
public class VideoSource extends MediaSource {
    public VideoSource(long j) {
        super(j);
    }

    private static native void nativeAdaptOutputFormat(long j, int i, int i2, int i3);

    public void adaptOutputFormat(int i, int i2, int i3) {
        nativeAdaptOutputFormat(this.nativeSource, i, i2, i3);
    }
}
