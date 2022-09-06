package org.webrtc;

import android.os.Handler;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public interface CameraSession {
    public static final String ERROR_SET_ATTRIBUTES_FAILED = "setAttributes failed";

    /* loaded from: classes5.dex */
    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    /* loaded from: classes5.dex */
    public interface Events {
        void onByteBufferFrameCaptured(CameraSession cameraSession, byte[] bArr, int i, int i2, int i3, long j);

        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpened(CameraSession cameraSession, CameraInformation cameraInformation);

        void onCameraOpening();

        void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame);

        void onTextureFrameCaptured(CameraSession cameraSession, int i, int i2, int i3, float[] fArr, int i4, long j);
    }

    /* loaded from: classes5.dex */
    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void getCameraMetadata(CameraMetadataObserver cameraMetadataObserver, Handler handler);

    void setAttributes(CameraAttributes cameraAttributes);

    void setVideoEffectStatusListener(String str, VideoEffectStatusListener videoEffectStatusListener);

    void stop();
}
