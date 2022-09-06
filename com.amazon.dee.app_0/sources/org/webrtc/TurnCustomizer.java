package org.webrtc;
/* loaded from: classes5.dex */
public class TurnCustomizer {
    final long nativeTurnCustomizer;

    public TurnCustomizer(long j) {
        this.nativeTurnCustomizer = j;
    }

    private static native void nativeFreeTurnCustomizer(long j);

    public void dispose() {
        nativeFreeTurnCustomizer(this.nativeTurnCustomizer);
    }
}
