package org.webrtc;

import java.nio.ByteBuffer;
import org.webrtc.VideoEncoder;
/* loaded from: classes5.dex */
class VideoEncoderWrapperCallback implements VideoEncoder.Callback {
    private final long nativeEncoder;

    public VideoEncoderWrapperCallback(long j) {
        this.nativeEncoder = j;
    }

    private static native void nativeOnEncodedFrame(long j, ByteBuffer byteBuffer, int i, int i2, long j2, int i3, int i4, boolean z, Integer num);

    @Override // org.webrtc.VideoEncoder.Callback
    public void onEncodedFrame(EncodedImage encodedImage, VideoEncoder.CodecSpecificInfo codecSpecificInfo) {
        nativeOnEncodedFrame(this.nativeEncoder, encodedImage.buffer, encodedImage.encodedWidth, encodedImage.encodedHeight, encodedImage.captureTimeNs, encodedImage.frameType.getNative(), encodedImage.rotation, encodedImage.completeFrame, encodedImage.qp);
    }
}
