package org.webrtc;

import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
final /* synthetic */ class VideoFrame$$Lambda$0 implements Runnable {
    private final VideoFrame.I420Buffer arg$1;

    private VideoFrame$$Lambda$0(VideoFrame.I420Buffer i420Buffer) {
        this.arg$1 = i420Buffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Runnable get$Lambda(VideoFrame.I420Buffer i420Buffer) {
        return new VideoFrame$$Lambda$0(i420Buffer);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.arg$1.release();
    }
}
