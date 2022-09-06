package com.google.android.cameraview;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import androidx.collection.SparseArrayCompat;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.cameraview.CameraViewImpl;
import com.google.android.cameraview.PreviewImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactnative.camera.utils.ObjectUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Camera1 extends CameraViewImpl implements MediaRecorder.OnInfoListener, MediaRecorder.OnErrorListener, Camera.PreviewCallback {
    private static final int DELAY_MILLIS_BEFORE_RESETTING_FOCUS = 3000;
    private static final SparseArrayCompat<String> FLASH_MODES = new SparseArrayCompat<>();
    private static final int FOCUS_AREA_SIZE_DEFAULT = 300;
    private static final int FOCUS_METERING_AREA_WEIGHT_DEFAULT = 1000;
    private static final int INVALID_CAMERA_ID = -1;
    private static final SparseArrayCompat<String> WB_MODES;
    private String _mCameraId;
    private final AtomicBoolean isPictureCaptureInProgress;
    private AspectRatio mAspectRatio;
    private boolean mAutoFocus;
    Camera mCamera;
    private int mCameraId;
    private final Camera.CameraInfo mCameraInfo;
    private Camera.Parameters mCameraParameters;
    private int mDeviceOrientation;
    private int mDisplayOrientation;
    private float mExposure;
    private int mFacing;
    private int mFlash;
    private Handler mHandler;
    private boolean mIsPreviewActive;
    private final AtomicBoolean mIsRecording;
    private boolean mIsScanning;
    private MediaRecorder mMediaRecorder;
    private int mOrientation;
    private Size mPictureSize;
    private final SizeMap mPictureSizes;
    private Boolean mPlaySoundOnCapture;
    private Boolean mPlaySoundOnRecord;
    private final SizeMap mPreviewSizes;
    private SurfaceTexture mPreviewTexture;
    private boolean mShowingPreview;
    private String mVideoPath;
    private int mWhiteBalance;
    private float mZoom;
    private boolean mustUpdateSurface;
    MediaActionSound sound;
    private boolean surfaceWasDestroyed;

    static {
        FLASH_MODES.put(0, "off");
        FLASH_MODES.put(1, "on");
        FLASH_MODES.put(2, "torch");
        FLASH_MODES.put(3, "auto");
        FLASH_MODES.put(4, "red-eye");
        WB_MODES = new SparseArrayCompat<>();
        WB_MODES.put(0, "auto");
        WB_MODES.put(1, "cloudy-daylight");
        WB_MODES.put(2, "daylight");
        WB_MODES.put(3, "shade");
        WB_MODES.put(4, "fluorescent");
        WB_MODES.put(5, "incandescent");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera1(CameraViewImpl.Callback callback, PreviewImpl previewImpl, Handler handler) {
        super(callback, previewImpl, handler);
        this.mHandler = new Handler();
        this.isPictureCaptureInProgress = new AtomicBoolean(false);
        this.sound = new MediaActionSound();
        this.mCameraInfo = new Camera.CameraInfo();
        this.mIsRecording = new AtomicBoolean(false);
        this.mPreviewSizes = new SizeMap();
        this.mIsPreviewActive = false;
        this.mShowingPreview = true;
        this.mPictureSizes = new SizeMap();
        this.mOrientation = 0;
        this.mPlaySoundOnCapture = false;
        this.mPlaySoundOnRecord = false;
        previewImpl.setCallback(new PreviewImpl.Callback() { // from class: com.google.android.cameraview.Camera1.1
            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceChanged() {
                synchronized (Camera1.this) {
                    if (!Camera1.this.surfaceWasDestroyed) {
                        Camera1.this.updateSurface();
                    } else {
                        Camera1.this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Camera1.this.start();
                            }
                        });
                    }
                }
            }

            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceDestroyed() {
                synchronized (Camera1.this) {
                    if (Camera1.this.mCamera != null) {
                        Camera1.this.surfaceWasDestroyed = true;
                        try {
                            Camera1.this.mCamera.setPreviewCallback(null);
                            Camera1.this.mCamera.setPreviewDisplay(null);
                        } catch (Exception e) {
                            Log.e("CAMERA_1::", "onSurfaceDestroyed preview cleanup failed", e);
                        }
                    }
                }
                Camera1.this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Camera1.this.stop();
                    }
                });
            }
        });
    }

    private int calcCameraRotation(int i) {
        Camera.CameraInfo cameraInfo = this.mCameraInfo;
        if (cameraInfo.facing == 0) {
            return (cameraInfo.orientation + i) % 360;
        }
        return ((this.mCameraInfo.orientation + i) + (isLandscape(i) ? 180 : 0)) % 360;
    }

    private int calcDisplayOrientation(int i) {
        Camera.CameraInfo cameraInfo = this.mCameraInfo;
        if (cameraInfo.facing == 1) {
            return (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i) + 360) % 360;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect calculateFocusArea(float f, float f2) {
        int i = (int) (f * 2000.0f);
        int i2 = (int) (f2 * 2000.0f);
        int i3 = i - 150;
        int i4 = i2 - 150;
        int i5 = i + 150;
        int i6 = i2 + 150;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i5 > 2000) {
            i5 = 2000;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (i6 > 2000) {
            i6 = 2000;
        }
        return new Rect(i3 - 1000, i4 - 1000, i5 - 1000, i6 - 1000);
    }

    private AspectRatio chooseAspectRatio() {
        Iterator<AspectRatio> it2 = this.mPreviewSizes.ratios().iterator();
        AspectRatio aspectRatio = null;
        while (it2.hasNext()) {
            aspectRatio = it2.next();
            if (aspectRatio.equals(Constants.DEFAULT_ASPECT_RATIO)) {
                break;
            }
        }
        return aspectRatio;
    }

    private void chooseCamera() {
        String str = this._mCameraId;
        if (str != null && !str.isEmpty()) {
            try {
                this.mCameraId = Integer.parseInt(this._mCameraId);
                Camera.getCameraInfo(this.mCameraId, this.mCameraInfo);
                return;
            } catch (Exception unused) {
                this.mCameraId = -1;
                return;
            }
        }
        try {
            int numberOfCameras = Camera.getNumberOfCameras();
            if (numberOfCameras == 0) {
                this.mCameraId = -1;
                Log.w("CAMERA_1::", "getNumberOfCameras returned 0. No camera available.");
                return;
            }
            for (int i = 0; i < numberOfCameras; i++) {
                Camera.getCameraInfo(i, this.mCameraInfo);
                if (this.mCameraInfo.facing == this.mFacing) {
                    this.mCameraId = i;
                    return;
                }
            }
            this.mCameraId = 0;
            Camera.getCameraInfo(this.mCameraId, this.mCameraInfo);
        } catch (Exception e) {
            Log.e("CAMERA_1::", "chooseCamera failed.", e);
            this.mCameraId = -1;
        }
    }

    private Size chooseOptimalSize(SortedSet<Size> sortedSet) {
        if (!this.mPreview.isReady()) {
            return sortedSet.first();
        }
        int width = this.mPreview.getWidth();
        int height = this.mPreview.getHeight();
        if (isLandscape(this.mDisplayOrientation)) {
            height = width;
            width = height;
        }
        Size size = null;
        Iterator<Size> it2 = sortedSet.iterator();
        while (it2.hasNext()) {
            size = it2.next();
            if (width <= size.getWidth() && height <= size.getHeight()) {
                break;
            }
        }
        return size;
    }

    private Size getBestSizeMatch(int i, int i2, SortedSet<Size> sortedSet) {
        if (sortedSet == null || sortedSet.isEmpty()) {
            return null;
        }
        Size last = sortedSet.last();
        if (i == 0 || i2 == 0) {
            return last;
        }
        for (Size size : sortedSet) {
            if (i <= size.getWidth() && i2 <= size.getHeight()) {
                return size;
            }
        }
        return last;
    }

    private boolean isCompatibleWithDevice(int i) {
        boolean z;
        int i2 = i * 1000;
        Iterator<int[]> it2 = getSupportedPreviewFpsRange().iterator();
        do {
            z = false;
            if (it2.hasNext()) {
                int[] next = it2.next();
                boolean z2 = i2 >= next[0] && i2 <= next[1];
                boolean z3 = i2 > 0;
                if (z2 && z3) {
                    z = true;
                    continue;
                }
            } else {
                Log.w("CAMERA_1::", "fps (framePerSecond) received an unsupported value and will be ignored.");
                return false;
            }
        } while (!z);
        return true;
    }

    private boolean isLandscape(int i) {
        return i == 90 || i == 270;
    }

    private boolean openCamera() {
        if (this.mCamera != null) {
            releaseCamera();
        }
        int i = this.mCameraId;
        if (i == -1) {
            return false;
        }
        try {
            try {
                this.mCamera = Camera.open(i);
                this.mCameraParameters = this.mCamera.getParameters();
                this.mPreviewSizes.clear();
                for (Camera.Size size : this.mCameraParameters.getSupportedPreviewSizes()) {
                    this.mPreviewSizes.add(new Size(size.width, size.height));
                }
                this.mPictureSizes.clear();
                for (Camera.Size size2 : this.mCameraParameters.getSupportedPictureSizes()) {
                    this.mPictureSizes.add(new Size(size2.width, size2.height));
                }
                for (AspectRatio aspectRatio : this.mPreviewSizes.ratios()) {
                    if (this.mPictureSizes.sizes(aspectRatio) == null) {
                        this.mPreviewSizes.remove(aspectRatio);
                    }
                }
                if (this.mAspectRatio == null) {
                    this.mAspectRatio = Constants.DEFAULT_ASPECT_RATIO;
                }
                adjustCameraParameters();
                this.mCamera.setDisplayOrientation(calcDisplayOrientation(this.mDisplayOrientation));
                this.mCallback.onCameraOpened();
                return true;
            } catch (RuntimeException unused) {
                this.mCamera.release();
                this.mCamera = null;
                return false;
            }
        } catch (RuntimeException unused2) {
            return false;
        }
    }

    private void pauseMediaRecorder() {
        int i = Build.VERSION.SDK_INT;
        this.mMediaRecorder.pause();
    }

    private void releaseCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.release();
            this.mCamera = null;
            this.mCallback.onCameraClosed();
            this.isPictureCaptureInProgress.set(false);
            this.mIsRecording.set(false);
        }
    }

    private void resetFocus(boolean z, Camera camera) {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.google.android.cameraview.Camera1.11
            @Override // java.lang.Runnable
            public void run() {
                Camera camera2 = Camera1.this.mCamera;
                if (camera2 != null) {
                    camera2.cancelAutoFocus();
                    Camera.Parameters parameters = Camera1.this.mCameraParameters;
                    if (parameters == null) {
                        return;
                    }
                    if (parameters.getFocusMode() != "continuous-picture") {
                        parameters.setFocusMode("continuous-picture");
                        parameters.setFocusAreas(null);
                        parameters.setMeteringAreas(null);
                        try {
                            Camera1.this.mCamera.setParameters(parameters);
                        } catch (RuntimeException e) {
                            Log.e("CAMERA_1::", "setParameters failed", e);
                        }
                    }
                    Camera1.this.mCamera.cancelAutoFocus();
                }
            }
        }, DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS);
    }

    private void resumeMediaRecorder() {
        int i = Build.VERSION.SDK_INT;
        this.mMediaRecorder.resume();
    }

    private boolean setAutoFocusInternal(boolean z) {
        this.mAutoFocus = z;
        if (isCameraOpened()) {
            List<String> supportedFocusModes = this.mCameraParameters.getSupportedFocusModes();
            if (z && supportedFocusModes.contains("continuous-picture")) {
                this.mCameraParameters.setFocusMode("continuous-picture");
                return true;
            } else if (this.mIsScanning && supportedFocusModes.contains("macro")) {
                this.mCameraParameters.setFocusMode("macro");
                return true;
            } else if (supportedFocusModes.contains("fixed")) {
                this.mCameraParameters.setFocusMode("fixed");
                return true;
            } else if (supportedFocusModes.contains("infinity")) {
                this.mCameraParameters.setFocusMode("infinity");
                return true;
            } else {
                this.mCameraParameters.setFocusMode(supportedFocusModes.get(0));
                return true;
            }
        }
        return false;
    }

    private void setCamcorderProfile(CamcorderProfile camcorderProfile, boolean z, int i) {
        if (!isCompatibleWithDevice(i)) {
            i = camcorderProfile.videoFrameRate;
        }
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        this.mMediaRecorder.setVideoFrameRate(i);
        this.mMediaRecorder.setVideoSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
        this.mMediaRecorder.setVideoEncodingBitRate(camcorderProfile.videoBitRate);
        this.mMediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
        if (z) {
            this.mMediaRecorder.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
            this.mMediaRecorder.setAudioChannels(camcorderProfile.audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(camcorderProfile.audioCodec);
        }
    }

    private boolean setExposureInternal(float f) {
        int minExposureCompensation;
        int maxExposureCompensation;
        this.mExposure = f;
        int i = 0;
        if (!isCameraOpened() || (minExposureCompensation = this.mCameraParameters.getMinExposureCompensation()) == (maxExposureCompensation = this.mCameraParameters.getMaxExposureCompensation())) {
            return false;
        }
        float f2 = this.mExposure;
        if (f2 >= 0.0f && f2 <= 1.0f) {
            i = ((int) (f2 * (maxExposureCompensation - minExposureCompensation))) + minExposureCompensation;
        }
        this.mCameraParameters.setExposureCompensation(i);
        return true;
    }

    private boolean setFlashInternal(int i) {
        if (isCameraOpened()) {
            List<String> supportedFlashModes = this.mCameraParameters.getSupportedFlashModes();
            String str = FLASH_MODES.get(i);
            if (supportedFlashModes == null) {
                return false;
            }
            if (supportedFlashModes.contains(str)) {
                this.mCameraParameters.setFlashMode(str);
                this.mFlash = i;
                return true;
            } else if (supportedFlashModes.contains(FLASH_MODES.get(this.mFlash))) {
                return false;
            } else {
                this.mCameraParameters.setFlashMode("off");
                return true;
            }
        }
        this.mFlash = i;
        return false;
    }

    private void setPlaySoundInternal(boolean z) {
        this.mPlaySoundOnCapture = Boolean.valueOf(z);
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                if (camera.enableShutterSound(false)) {
                    return;
                }
                this.mPlaySoundOnCapture = false;
            } catch (Exception e) {
                Log.e("CAMERA_1::", "setPlaySoundInternal failed", e);
                this.mPlaySoundOnCapture = false;
            }
        }
    }

    private void setScanningInternal(boolean z) {
        this.mIsScanning = z;
        if (isCameraOpened()) {
            if (this.mIsScanning) {
                this.mCamera.setPreviewCallback(this);
            } else {
                this.mCamera.setPreviewCallback(null);
            }
        }
    }

    private void setUpMediaRecorder(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile, int i3) {
        CamcorderProfile camcorderProfile2;
        this.mMediaRecorder = new MediaRecorder();
        this.mCamera.unlock();
        this.mMediaRecorder.setCamera(this.mCamera);
        this.mMediaRecorder.setVideoSource(1);
        if (z) {
            this.mMediaRecorder.setAudioSource(5);
        }
        this.mMediaRecorder.setOutputFile(str);
        this.mVideoPath = str;
        if (CamcorderProfile.hasProfile(this.mCameraId, camcorderProfile.quality)) {
            camcorderProfile2 = CamcorderProfile.get(this.mCameraId, camcorderProfile.quality);
        } else {
            camcorderProfile2 = CamcorderProfile.get(this.mCameraId, 1);
        }
        camcorderProfile2.videoBitRate = camcorderProfile.videoBitRate;
        setCamcorderProfile(camcorderProfile2, z, i3);
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        int i4 = this.mOrientation;
        mediaRecorder.setOrientationHint(calcCameraRotation(i4 != 0 ? orientationEnumToRotation(i4) : this.mDeviceOrientation));
        if (i != -1) {
            this.mMediaRecorder.setMaxDuration(i);
        }
        if (i2 != -1) {
            this.mMediaRecorder.setMaxFileSize(i2);
        }
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
    }

    private boolean setWhiteBalanceInternal(int i) {
        this.mWhiteBalance = i;
        if (isCameraOpened()) {
            List<String> supportedWhiteBalance = this.mCameraParameters.getSupportedWhiteBalance();
            String str = WB_MODES.get(i);
            if (supportedWhiteBalance != null && supportedWhiteBalance.contains(str)) {
                this.mCameraParameters.setWhiteBalance(str);
                return true;
            }
            String str2 = WB_MODES.get(this.mWhiteBalance);
            if (supportedWhiteBalance != null && supportedWhiteBalance.contains(str2)) {
                return false;
            }
            this.mCameraParameters.setWhiteBalance("auto");
            return true;
        }
        return false;
    }

    private boolean setZoomInternal(float f) {
        if (isCameraOpened() && this.mCameraParameters.isZoomSupported()) {
            this.mCameraParameters.setZoom((int) (this.mCameraParameters.getMaxZoom() * f));
            this.mZoom = f;
            return true;
        }
        this.mZoom = f;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCameraPreview() {
        Camera camera;
        if (this.mIsPreviewActive || (camera = this.mCamera) == null) {
            return;
        }
        try {
            this.mIsPreviewActive = true;
            camera.startPreview();
            if (!this.mIsScanning) {
                return;
            }
            this.mCamera.setPreviewCallback(this);
        } catch (Exception e) {
            this.mIsPreviewActive = false;
            Log.e("CAMERA_1::", "startCameraPreview failed", e);
        }
    }

    private void stopMediaRecorder() {
        synchronized (this) {
            if (this.mMediaRecorder != null) {
                try {
                    this.mMediaRecorder.stop();
                } catch (RuntimeException e) {
                    Log.e("CAMERA_1::", "stopMediaRecorder stop failed", e);
                }
                try {
                    this.mMediaRecorder.reset();
                    this.mMediaRecorder.release();
                } catch (RuntimeException e2) {
                    Log.e("CAMERA_1::", "stopMediaRecorder reset failed", e2);
                }
                this.mMediaRecorder = null;
            }
            this.mCallback.onRecordingEnd();
            if (this.mPlaySoundOnRecord.booleanValue()) {
                this.sound.play(3);
            }
            int displayOrientationToOrientationEnum = displayOrientationToOrientationEnum(this.mDeviceOrientation);
            if (this.mVideoPath != null && new File(this.mVideoPath).exists()) {
                this.mCallback.onVideoRecorded(this.mVideoPath, this.mOrientation != 0 ? this.mOrientation : displayOrientationToOrientationEnum, displayOrientationToOrientationEnum);
                this.mVideoPath = null;
                return;
            }
            this.mCallback.onVideoRecorded(null, this.mOrientation != 0 ? this.mOrientation : displayOrientationToOrientationEnum, displayOrientationToOrientationEnum);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSurface() {
        if (this.mCamera != null) {
            if (!this.isPictureCaptureInProgress.get() && !this.mIsRecording.get()) {
                this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (Camera1.this) {
                            if (Camera1.this.mCamera != null) {
                                Camera1.this.mustUpdateSurface = false;
                                Camera1.this.setUpPreview();
                                Camera1.this.adjustCameraParameters();
                                if (Camera1.this.mShowingPreview) {
                                    Camera1.this.startCameraPreview();
                                }
                            }
                        }
                    }
                });
            } else {
                this.mustUpdateSurface = true;
            }
        }
    }

    void adjustCameraParameters() {
        Size bestSizeMatch;
        SortedSet<Size> sizes = this.mPreviewSizes.sizes(this.mAspectRatio);
        if (sizes == null) {
            Log.w("CAMERA_1::", "adjustCameraParameters received an unsupported aspect ratio value and will be ignored.");
            this.mAspectRatio = chooseAspectRatio();
            sizes = this.mPreviewSizes.sizes(this.mAspectRatio);
        }
        Size chooseOptimalSize = chooseOptimalSize(sizes);
        Size size = this.mPictureSize;
        if (size != null) {
            bestSizeMatch = getBestSizeMatch(size.getWidth(), this.mPictureSize.getHeight(), this.mPictureSizes.sizes(this.mAspectRatio));
        } else {
            bestSizeMatch = getBestSizeMatch(0, 0, this.mPictureSizes.sizes(this.mAspectRatio));
        }
        boolean z = this.mIsPreviewActive;
        if (z) {
            this.mCamera.stopPreview();
            this.mIsPreviewActive = false;
        }
        this.mCameraParameters.setPreviewSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
        this.mCameraParameters.setPictureSize(bestSizeMatch.getWidth(), bestSizeMatch.getHeight());
        this.mCameraParameters.setJpegThumbnailSize(0, 0);
        int i = this.mOrientation;
        if (i != 0) {
            this.mCameraParameters.setRotation(calcCameraRotation(orientationEnumToRotation(i)));
        } else {
            this.mCameraParameters.setRotation(calcCameraRotation(this.mDeviceOrientation));
        }
        setAutoFocusInternal(this.mAutoFocus);
        setFlashInternal(this.mFlash);
        setExposureInternal(this.mExposure);
        setAspectRatio(this.mAspectRatio);
        setZoomInternal(this.mZoom);
        setWhiteBalanceInternal(this.mWhiteBalance);
        setScanningInternal(this.mIsScanning);
        setPlaySoundInternal(this.mPlaySoundOnCapture.booleanValue());
        try {
            this.mCamera.setParameters(this.mCameraParameters);
        } catch (RuntimeException e) {
            Log.e("CAMERA_1::", "setParameters failed", e);
        }
        if (z) {
            startCameraPreview();
        }
    }

    int displayOrientationToOrientationEnum(int i) {
        if (i != 0) {
            if (i == 90) {
                return 4;
            }
            if (i == 180) {
                return 2;
            }
            return i != 270 ? 1 : 3;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public AspectRatio getAspectRatio() {
        return this.mAspectRatio;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getAutoFocus() {
        if (!isCameraOpened()) {
            return this.mAutoFocus;
        }
        String focusMode = this.mCameraParameters.getFocusMode();
        return focusMode != null && focusMode.contains("continuous");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public SortedSet<Size> getAvailablePictureSizes(AspectRatio aspectRatio) {
        return this.mPictureSizes.sizes(aspectRatio);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public String getCameraId() {
        return this._mCameraId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public List<Properties> getCameraIds() {
        ArrayList arrayList = new ArrayList();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Properties properties = new Properties();
            Camera.getCameraInfo(i, cameraInfo);
            properties.put("id", String.valueOf(i));
            properties.put("type", String.valueOf(cameraInfo.facing));
            arrayList.add(properties);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getCameraOrientation() {
        return this.mCameraInfo.orientation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public float getExposureCompensation() {
        return this.mExposure;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getFacing() {
        return this.mFacing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getFlash() {
        return this.mFlash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public float getFocusDepth() {
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public Size getPictureSize() {
        return this.mPictureSize;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getPlaySoundOnCapture() {
        return this.mPlaySoundOnCapture.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getPlaySoundOnRecord() {
        return this.mPlaySoundOnRecord.booleanValue();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public Size getPreviewSize() {
        Camera.Size previewSize = this.mCameraParameters.getPreviewSize();
        return new Size(previewSize.width, previewSize.height);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getScanning() {
        return this.mIsScanning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public Set<AspectRatio> getSupportedAspectRatios() {
        SizeMap sizeMap = this.mPreviewSizes;
        for (AspectRatio aspectRatio : sizeMap.ratios()) {
            if (this.mPictureSizes.sizes(aspectRatio) == null) {
                sizeMap.remove(aspectRatio);
            }
        }
        return sizeMap.ratios();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public ArrayList<int[]> getSupportedPreviewFpsRange() {
        return (ArrayList) this.mCameraParameters.getSupportedPreviewFpsRange();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public int getWhiteBalance() {
        return this.mWhiteBalance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public float getZoom() {
        return this.mZoom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean isCameraOpened() {
        return this.mCamera != null;
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        stopRecording();
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if (i == 800 || i == 801) {
            stopRecording();
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Camera.Size previewSize = this.mCameraParameters.getPreviewSize();
        this.mCallback.onFramePreview(bArr, previewSize.width, previewSize.height, this.mDeviceOrientation);
    }

    int orientationEnumToRotation(int i) {
        if (i != 1) {
            if (i == 2) {
                return 180;
            }
            if (i == 3) {
                return 270;
            }
            return i != 4 ? 1 : 90;
        }
        return 0;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void pausePreview() {
        synchronized (this) {
            this.mIsPreviewActive = false;
            this.mShowingPreview = false;
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void pauseRecording() {
        pauseMediaRecorder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean record(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile, int i3, int i4) {
        if (!this.isPictureCaptureInProgress.get() && this.mIsRecording.compareAndSet(false, true)) {
            if (i3 != 0) {
                this.mOrientation = i3;
            }
            try {
                setUpMediaRecorder(str, i, i2, z, camcorderProfile, i4);
                this.mMediaRecorder.prepare();
                this.mMediaRecorder.start();
                try {
                    this.mCamera.setParameters(this.mCameraParameters);
                } catch (Exception e) {
                    Log.e("CAMERA_1::", "Record setParameters failed", e);
                }
                int displayOrientationToOrientationEnum = displayOrientationToOrientationEnum(this.mDeviceOrientation);
                this.mCallback.onRecordingStart(str, this.mOrientation != 0 ? this.mOrientation : displayOrientationToOrientationEnum, displayOrientationToOrientationEnum);
                if (this.mPlaySoundOnRecord.booleanValue()) {
                    this.sound.play(2);
                }
                return true;
            } catch (Exception e2) {
                this.mIsRecording.set(false);
                Log.e("CAMERA_1::", "Record start failed", e2);
            }
        }
        return false;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void resumePreview() {
        this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    Camera1.this.mShowingPreview = true;
                    Camera1.this.startCameraPreview();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void resumeRecording() {
        resumeMediaRecorder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean setAspectRatio(AspectRatio aspectRatio) {
        if (this.mAspectRatio != null && isCameraOpened()) {
            if (this.mAspectRatio.equals(aspectRatio)) {
                return false;
            }
            if (this.mPreviewSizes.sizes(aspectRatio) == null) {
                Log.w("CAMERA_1::", "setAspectRatio received an unsupported value and will be ignored.");
                return false;
            }
            this.mAspectRatio = aspectRatio;
            this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.7
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (Camera1.this) {
                        if (Camera1.this.mCamera != null) {
                            Camera1.this.adjustCameraParameters();
                        }
                    }
                }
            });
            return true;
        }
        this.mAspectRatio = aspectRatio;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setAutoFocus(boolean z) {
        if (this.mAutoFocus == z) {
            return;
        }
        synchronized (this) {
            if (setAutoFocusInternal(z)) {
                try {
                    if (this.mCamera != null) {
                        this.mCamera.setParameters(this.mCameraParameters);
                    }
                } catch (RuntimeException e) {
                    Log.e("CAMERA_1::", "setParameters failed", e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setCameraId(String str) {
        if (!ObjectUtils.equals(this._mCameraId, str)) {
            this._mCameraId = str;
            if (ObjectUtils.equals(this._mCameraId, String.valueOf(this.mCameraId))) {
                return;
            }
            this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.5
                @Override // java.lang.Runnable
                public void run() {
                    if (Camera1.this.isCameraOpened()) {
                        Camera1.this.stop();
                        Camera1.this.start();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setDeviceOrientation(int i) {
        synchronized (this) {
            if (this.mDeviceOrientation == i) {
                return;
            }
            this.mDeviceOrientation = i;
            if (isCameraOpened() && this.mOrientation == 0 && !this.mIsRecording.get() && !this.isPictureCaptureInProgress.get()) {
                try {
                    this.mCameraParameters.setRotation(calcCameraRotation(i));
                    this.mCamera.setParameters(this.mCameraParameters);
                } catch (RuntimeException e) {
                    Log.e("CAMERA_1::", "setParameters failed", e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setDisplayOrientation(int i) {
        synchronized (this) {
            if (this.mDisplayOrientation == i) {
                return;
            }
            this.mDisplayOrientation = i;
            if (isCameraOpened()) {
                if (this.mIsPreviewActive) {
                    int i2 = Build.VERSION.SDK_INT;
                }
                if (0 != 0) {
                    this.mCamera.stopPreview();
                    this.mIsPreviewActive = false;
                }
                try {
                    this.mCamera.setDisplayOrientation(calcDisplayOrientation(i));
                } catch (RuntimeException e) {
                    Log.e("CAMERA_1::", "setDisplayOrientation failed", e);
                }
                if (0 != 0) {
                    startCameraPreview();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setExposureCompensation(float f) {
        if (f != this.mExposure && setExposureInternal(f)) {
            try {
                if (this.mCamera == null) {
                    return;
                }
                this.mCamera.setParameters(this.mCameraParameters);
            } catch (RuntimeException e) {
                Log.e("CAMERA_1::", "setParameters failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFacing(int i) {
        if (this.mFacing == i) {
            return;
        }
        this.mFacing = i;
        this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.4
            @Override // java.lang.Runnable
            public void run() {
                if (Camera1.this.isCameraOpened()) {
                    Camera1.this.stop();
                    Camera1.this.start();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFlash(int i) {
        if (i != this.mFlash && setFlashInternal(i)) {
            try {
                if (this.mCamera == null) {
                    return;
                }
                this.mCamera.setParameters(this.mCameraParameters);
            } catch (RuntimeException e) {
                Log.e("CAMERA_1::", "setParameters failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFocusArea(final float f, final float f2) {
        this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.10
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Camera1.this) {
                    if (Camera1.this.mCamera != null) {
                        Camera.Parameters parameters = Camera1.this.mCameraParameters;
                        if (parameters == null) {
                            return;
                        }
                        String focusMode = parameters.getFocusMode();
                        Rect calculateFocusArea = Camera1.this.calculateFocusArea(f, f2);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new Camera.Area(calculateFocusArea, 1000));
                        if (parameters.getMaxNumFocusAreas() != 0 && focusMode != null && (focusMode.equals("auto") || focusMode.equals("macro") || focusMode.equals("continuous-picture") || focusMode.equals("continuous-video"))) {
                            parameters.setFocusMode("auto");
                            parameters.setFocusAreas(arrayList);
                            if (parameters.getMaxNumMeteringAreas() > 0) {
                                parameters.setMeteringAreas(arrayList);
                            }
                            if (!parameters.getSupportedFocusModes().contains("auto")) {
                                return;
                            }
                            try {
                                Camera1.this.mCamera.setParameters(parameters);
                            } catch (RuntimeException e) {
                                Log.e("CAMERA_1::", "setParameters failed", e);
                            }
                            try {
                                Camera1.this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.google.android.cameraview.Camera1.10.1
                                    @Override // android.hardware.Camera.AutoFocusCallback
                                    public void onAutoFocus(boolean z, Camera camera) {
                                    }
                                });
                            } catch (RuntimeException e2) {
                                Log.e("CAMERA_1::", "autoFocus failed", e2);
                            }
                        } else if (parameters.getMaxNumMeteringAreas() > 0) {
                            if (!parameters.getSupportedFocusModes().contains("auto")) {
                                return;
                            }
                            parameters.setFocusMode("auto");
                            parameters.setFocusAreas(arrayList);
                            parameters.setMeteringAreas(arrayList);
                            try {
                                Camera1.this.mCamera.setParameters(parameters);
                            } catch (RuntimeException e3) {
                                Log.e("CAMERA_1::", "setParameters failed", e3);
                            }
                            try {
                                Camera1.this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.google.android.cameraview.Camera1.10.2
                                    @Override // android.hardware.Camera.AutoFocusCallback
                                    public void onAutoFocus(boolean z, Camera camera) {
                                    }
                                });
                            } catch (RuntimeException e4) {
                                Log.e("CAMERA_1::", "autoFocus failed", e4);
                            }
                        } else {
                            try {
                                Camera1.this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.google.android.cameraview.Camera1.10.3
                                    @Override // android.hardware.Camera.AutoFocusCallback
                                    public void onAutoFocus(boolean z, Camera camera) {
                                    }
                                });
                            } catch (RuntimeException e5) {
                                Log.e("CAMERA_1::", "autoFocus failed", e5);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFocusDepth(float f) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPictureSize(Size size) {
        if (size == null && this.mPictureSize == null) {
            return;
        }
        if (size != null && size.equals(this.mPictureSize)) {
            return;
        }
        this.mPictureSize = size;
        if (!isCameraOpened()) {
            return;
        }
        this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.6
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Camera1.this) {
                    if (Camera1.this.mCamera != null) {
                        Camera1.this.adjustCameraParameters();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPlaySoundOnCapture(boolean z) {
        if (z == this.mPlaySoundOnCapture.booleanValue()) {
            return;
        }
        setPlaySoundInternal(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPlaySoundOnRecord(boolean z) {
        this.mPlaySoundOnRecord = Boolean.valueOf(z);
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPreviewTexture(final SurfaceTexture surfaceTexture) {
        this.mBgHandler.post(new Runnable() { // from class: com.google.android.cameraview.Camera1.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (Camera1.this.mCamera == null) {
                        Camera1.this.mPreviewTexture = surfaceTexture;
                        return;
                    }
                    Camera1.this.mCamera.stopPreview();
                    Camera1.this.mIsPreviewActive = false;
                    if (surfaceTexture == null) {
                        Camera1.this.mCamera.setPreviewTexture((SurfaceTexture) Camera1.this.mPreview.mo7263getSurfaceTexture());
                    } else {
                        Camera1.this.mCamera.setPreviewTexture(surfaceTexture);
                    }
                    Camera1.this.mPreviewTexture = surfaceTexture;
                    Camera1.this.startCameraPreview();
                } catch (IOException e) {
                    Log.e("CAMERA_1::", "setPreviewTexture failed", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setScanning(boolean z) {
        if (z == this.mIsScanning) {
            return;
        }
        setScanningInternal(z);
    }

    @SuppressLint({"NewApi"})
    void setUpPreview() {
        try {
            this.surfaceWasDestroyed = false;
            if (this.mCamera == null) {
                return;
            }
            if (this.mPreviewTexture != null) {
                this.mCamera.setPreviewTexture(this.mPreviewTexture);
            } else if (this.mPreview.getOutputClass() == SurfaceHolder.class) {
                if (this.mIsPreviewActive) {
                    int i = Build.VERSION.SDK_INT;
                }
                if (0 != 0) {
                    this.mCamera.stopPreview();
                    this.mIsPreviewActive = false;
                }
                this.mCamera.setPreviewDisplay(this.mPreview.getSurfaceHolder());
                if (0 == 0) {
                    return;
                }
                startCameraPreview();
            } else {
                this.mCamera.setPreviewTexture((SurfaceTexture) this.mPreview.mo7263getSurfaceTexture());
            }
        } catch (Exception e) {
            Log.e("CAMERA_1::", "setUpPreview failed", e);
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setWhiteBalance(int i) {
        if (i != this.mWhiteBalance && setWhiteBalanceInternal(i)) {
            try {
                if (this.mCamera == null) {
                    return;
                }
                this.mCamera.setParameters(this.mCameraParameters);
            } catch (RuntimeException e) {
                Log.e("CAMERA_1::", "setParameters failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setZoom(float f) {
        if (f != this.mZoom && setZoomInternal(f)) {
            try {
                if (this.mCamera == null) {
                    return;
                }
                this.mCamera.setParameters(this.mCameraParameters);
            } catch (RuntimeException e) {
                Log.e("CAMERA_1::", "setParameters failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean start() {
        synchronized (this) {
            chooseCamera();
            if (!openCamera()) {
                this.mCallback.onMountError();
                return true;
            }
            if (this.mPreview.isReady()) {
                setUpPreview();
                if (this.mShowingPreview) {
                    startCameraPreview();
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stop() {
        synchronized (this) {
            if (this.mMediaRecorder != null) {
                try {
                    this.mMediaRecorder.stop();
                } catch (RuntimeException e) {
                    Log.e("CAMERA_1::", "mMediaRecorder.stop() failed", e);
                }
                try {
                    this.mMediaRecorder.reset();
                    this.mMediaRecorder.release();
                } catch (RuntimeException e2) {
                    Log.e("CAMERA_1::", "mMediaRecorder.release() failed", e2);
                }
                this.mMediaRecorder = null;
                if (this.mIsRecording.get()) {
                    this.mCallback.onRecordingEnd();
                    int displayOrientationToOrientationEnum = displayOrientationToOrientationEnum(this.mDeviceOrientation);
                    this.mCallback.onVideoRecorded(this.mVideoPath, this.mOrientation != 0 ? this.mOrientation : displayOrientationToOrientationEnum, displayOrientationToOrientationEnum);
                }
            }
            if (this.mCamera != null) {
                this.mIsPreviewActive = false;
                try {
                    this.mCamera.stopPreview();
                    this.mCamera.setPreviewCallback(null);
                } catch (Exception e3) {
                    Log.e("CAMERA_1::", "stop preview cleanup failed", e3);
                }
            }
            releaseCamera();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stopRecording() {
        if (this.mIsRecording.compareAndSet(true, false)) {
            stopMediaRecorder();
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.lock();
            }
            if (!this.mustUpdateSurface) {
                return;
            }
            updateSurface();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void takePicture(ReadableMap readableMap) {
        if (isCameraOpened()) {
            if (this.mIsPreviewActive) {
                takePictureInternal(readableMap);
                return;
            }
            throw new IllegalStateException("Preview is paused - resume it before taking a picture.");
        }
        throw new IllegalStateException("Camera is not ready. Call start() before takePicture().");
    }

    void takePictureInternal(final ReadableMap readableMap) {
        if (!this.mIsRecording.get() && this.isPictureCaptureInProgress.compareAndSet(false, true)) {
            try {
                if (readableMap.hasKey("orientation") && readableMap.getInt("orientation") != 0) {
                    this.mOrientation = readableMap.getInt("orientation");
                    this.mCameraParameters.setRotation(calcCameraRotation(orientationEnumToRotation(this.mOrientation)));
                    try {
                        this.mCamera.setParameters(this.mCameraParameters);
                    } catch (RuntimeException e) {
                        Log.e("CAMERA_1::", "setParameters rotation failed", e);
                    }
                }
                if (readableMap.hasKey("quality")) {
                    this.mCameraParameters.setJpegQuality((int) (readableMap.getDouble("quality") * 100.0d));
                    try {
                        this.mCamera.setParameters(this.mCameraParameters);
                    } catch (RuntimeException e2) {
                        Log.e("CAMERA_1::", "setParameters quality failed", e2);
                    }
                }
                this.mCamera.takePicture(null, null, null, new Camera.PictureCallback() { // from class: com.google.android.cameraview.Camera1.8
                    @Override // android.hardware.Camera.PictureCallback
                    public void onPictureTaken(byte[] bArr, Camera camera) {
                        if (Camera1.this.mPlaySoundOnCapture.booleanValue()) {
                            Camera1.this.sound.play(0);
                        }
                        synchronized (Camera1.this) {
                            if (Camera1.this.mCamera != null) {
                                if (readableMap.hasKey("pauseAfterCapture") && !readableMap.getBoolean("pauseAfterCapture")) {
                                    try {
                                        Camera1.this.mCamera.startPreview();
                                        Camera1.this.mIsPreviewActive = true;
                                        if (Camera1.this.mIsScanning) {
                                            Camera1.this.mCamera.setPreviewCallback(Camera1.this);
                                        }
                                    } catch (Exception e3) {
                                        Camera1.this.mIsPreviewActive = false;
                                        Camera1.this.mCamera.setPreviewCallback(null);
                                        Log.e("CAMERA_1::", "camera startPreview failed", e3);
                                    }
                                } else {
                                    try {
                                        Camera1.this.mCamera.stopPreview();
                                    } catch (Exception e4) {
                                        Log.e("CAMERA_1::", "camera stopPreview failed", e4);
                                    }
                                    Camera1.this.mIsPreviewActive = false;
                                    Camera1.this.mCamera.setPreviewCallback(null);
                                }
                            }
                        }
                        Camera1.this.isPictureCaptureInProgress.set(false);
                        Camera1.this.mOrientation = 0;
                        Camera1 camera1 = Camera1.this;
                        camera1.mCallback.onPictureTaken(bArr, camera1.displayOrientationToOrientationEnum(camera1.mDeviceOrientation));
                        if (Camera1.this.mustUpdateSurface) {
                            Camera1.this.updateSurface();
                        }
                    }
                });
                return;
            } catch (Exception e3) {
                this.isPictureCaptureInProgress.set(false);
                throw e3;
            }
        }
        throw new IllegalStateException("Camera capture failed. Camera is already capturing.");
    }
}
