package org.webrtc;

import android.content.Context;
/* loaded from: classes5.dex */
public interface VideoCapturer {

    /* loaded from: classes5.dex */
    public interface CapturerObserver {
        void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j);

        void onCapturerStarted(boolean z);

        void onCapturerStopped();

        void onFrameCaptured(VideoFrame videoFrame);

        void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j);
    }

    void changeCaptureFormat(int i, int i2, int i3);

    void dispose();

    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    boolean isScreencast();

    void startCapture(int i, int i2, int i3);

    void stopCapture() throws InterruptedException;
}
