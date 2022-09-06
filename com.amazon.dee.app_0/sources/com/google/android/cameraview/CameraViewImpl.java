package com.google.android.cameraview;

import android.graphics.SurfaceTexture;
import android.media.CamcorderProfile;
import android.os.Handler;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
/* loaded from: classes2.dex */
abstract class CameraViewImpl {
    protected final Handler mBgHandler;
    protected final Callback mCallback;
    protected final PreviewImpl mPreview;

    /* loaded from: classes2.dex */
    interface Callback {
        void onCameraClosed();

        void onCameraOpened();

        void onFramePreview(byte[] bArr, int i, int i2, int i3);

        void onMountError();

        void onPictureTaken(byte[] bArr, int i);

        void onRecordingEnd();

        void onRecordingStart(String str, int i, int i2);

        void onVideoRecorded(String str, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraViewImpl(Callback callback, PreviewImpl previewImpl, Handler handler) {
        this.mCallback = callback;
        this.mPreview = previewImpl;
        this.mBgHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract AspectRatio getAspectRatio();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getAutoFocus();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SortedSet<Size> getAvailablePictureSizes(AspectRatio aspectRatio);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCameraId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List<Properties> getCameraIds();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getCameraOrientation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getExposureCompensation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getFacing();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getFlash();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getFocusDepth();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Size getPictureSize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getPlaySoundOnCapture();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getPlaySoundOnRecord();

    public abstract Size getPreviewSize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getScanning();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Set<AspectRatio> getSupportedAspectRatios();

    public abstract ArrayList<int[]> getSupportedPreviewFpsRange();

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getView() {
        return this.mPreview.getView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getWhiteBalance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getZoom();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isCameraOpened();

    public abstract void pausePreview();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void pauseRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean record(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile, int i3, int i4);

    public abstract void resumePreview();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void resumeRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean setAspectRatio(AspectRatio aspectRatio);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setAutoFocus(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setCameraId(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setDeviceOrientation(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setDisplayOrientation(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setExposureCompensation(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFacing(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFlash(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFocusArea(float f, float f2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFocusDepth(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setPictureSize(Size size);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setPlaySoundOnCapture(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setPlaySoundOnRecord(boolean z);

    public abstract void setPreviewTexture(SurfaceTexture surfaceTexture);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setScanning(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setWhiteBalance(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setZoom(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean start();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stop();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stopRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void takePicture(ReadableMap readableMap);
}
