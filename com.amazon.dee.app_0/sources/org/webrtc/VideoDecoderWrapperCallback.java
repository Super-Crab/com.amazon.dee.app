package org.webrtc;

import org.webrtc.VideoDecoder;
/* loaded from: classes5.dex */
class VideoDecoderWrapperCallback implements VideoDecoder.Callback {
    private final long nativeDecoder;

    public VideoDecoderWrapperCallback(long j) {
        this.nativeDecoder = j;
    }

    private static native void nativeOnDecodedFrame(long j, VideoFrame videoFrame, Integer num, Integer num2);

    @Override // org.webrtc.VideoDecoder.Callback
    public void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2) {
        nativeOnDecodedFrame(this.nativeDecoder, videoFrame, num, num2);
    }
}
