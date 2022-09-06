package org.webrtc;

import org.webrtc.VideoCapturer;
import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
class AndroidVideoTrackSourceObserver implements VideoCapturer.CapturerObserver {
    private final long nativeSource;

    public AndroidVideoTrackSourceObserver(long j) {
        this.nativeSource = j;
    }

    private native void nativeCapturerStarted(long j, boolean z);

    private native void nativeCapturerStopped(long j);

    private native void nativeOnByteBufferFrameCaptured(long j, byte[] bArr, int i, int i2, int i3, int i4, long j2);

    private native void nativeOnFrameCaptured(long j, int i, int i2, int i3, long j2, VideoFrame.Buffer buffer);

    private native void nativeOnTextureFrameCaptured(long j, int i, int i2, int i3, float[] fArr, int i4, long j2);

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    public void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
        nativeOnByteBufferFrameCaptured(this.nativeSource, bArr, bArr.length, i, i2, i3, j);
    }

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    public void onCapturerStarted(boolean z) {
        nativeCapturerStarted(this.nativeSource, z);
    }

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    public void onCapturerStopped() {
        nativeCapturerStopped(this.nativeSource);
    }

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    public void onFrameCaptured(VideoFrame videoFrame) {
        nativeOnFrameCaptured(this.nativeSource, videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation(), videoFrame.getTimestampNs(), videoFrame.getBuffer());
    }

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    public void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
        nativeOnTextureFrameCaptured(this.nativeSource, i, i2, i3, fArr, i4, j);
    }
}
