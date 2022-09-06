package org.webrtc;
/* loaded from: classes5.dex */
public class MediaStreamTrack {
    final long nativeTrack;

    /* loaded from: classes5.dex */
    public enum MediaType {
        MEDIA_TYPE_AUDIO,
        MEDIA_TYPE_VIDEO
    }

    /* loaded from: classes5.dex */
    public enum State {
        LIVE,
        ENDED
    }

    public MediaStreamTrack(long j) {
        this.nativeTrack = j;
    }

    private static native boolean nativeEnabled(long j);

    private static native String nativeId(long j);

    private static native String nativeKind(long j);

    private static native boolean nativeSetEnabled(long j, boolean z);

    private static native State nativeState(long j);

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeTrack);
    }

    public boolean enabled() {
        return nativeEnabled(this.nativeTrack);
    }

    public String id() {
        return nativeId(this.nativeTrack);
    }

    public String kind() {
        return nativeKind(this.nativeTrack);
    }

    public boolean setEnabled(boolean z) {
        return nativeSetEnabled(this.nativeTrack, z);
    }

    public State state() {
        return nativeState(this.nativeTrack);
    }
}
