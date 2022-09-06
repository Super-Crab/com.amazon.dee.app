package org.webrtc;
/* loaded from: classes5.dex */
public class MediaSource {
    final long nativeSource;

    /* loaded from: classes5.dex */
    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        MUTED
    }

    public MediaSource(long j) {
        this.nativeSource = j;
    }

    private static native State nativeState(long j);

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeSource);
    }

    public State state() {
        return nativeState(this.nativeSource);
    }
}
