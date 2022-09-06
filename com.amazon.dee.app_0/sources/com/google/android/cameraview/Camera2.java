package com.google.android.cameraview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaActionSound;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.cameraview.CameraViewImpl;
import com.google.android.cameraview.PreviewImpl;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import org.reactnative.camera.utils.ObjectUtils;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(21)
/* loaded from: classes2.dex */
public class Camera2 extends CameraViewImpl implements MediaRecorder.OnInfoListener, MediaRecorder.OnErrorListener {
    private static final int FOCUS_AREA_SIZE_DEFAULT = 300;
    private static final int FOCUS_METERING_AREA_WEIGHT_DEFAULT = 1000;
    private static final SparseIntArray INTERNAL_FACINGS = new SparseIntArray();
    private static final int MAX_PREVIEW_HEIGHT = 1080;
    private static final int MAX_PREVIEW_WIDTH = 1920;
    private static final String TAG = "Camera2";
    private String _mCameraId;
    private AspectRatio mAspectRatio;
    private boolean mAutoFocus;
    Set<String> mAvailableCameras;
    CameraDevice mCamera;
    private CameraCharacteristics mCameraCharacteristics;
    private final CameraDevice.StateCallback mCameraDeviceCallback;
    private String mCameraId;
    private final CameraManager mCameraManager;
    private int mCameraOrientation;
    PictureCaptureCallback mCaptureCallback;
    CameraCaptureSession mCaptureSession;
    private int mDeviceOrientation;
    private int mDisplayOrientation;
    private float mExposure;
    private int mFacing;
    private int mFlash;
    private float mFocusDepth;
    private int mImageFormat;
    private Rect mInitialCropRegion;
    private AspectRatio mInitialRatio;
    private boolean mIsRecording;
    private boolean mIsScanning;
    private MediaRecorder mMediaRecorder;
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener;
    private Size mPictureSize;
    private final SizeMap mPictureSizes;
    private Boolean mPlaySoundOnCapture;
    private Boolean mPlaySoundOnRecord;
    CaptureRequest.Builder mPreviewRequestBuilder;
    private final SizeMap mPreviewSizes;
    private Surface mPreviewSurface;
    private ImageReader mScanImageReader;
    private final CameraCaptureSession.StateCallback mSessionCallback;
    private ImageReader mStillImageReader;
    private String mVideoPath;
    private int mWhiteBalance;
    private float mZoom;
    MediaActionSound sound;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class PictureCaptureCallback extends CameraCaptureSession.CaptureCallback {
        static final int STATE_CAPTURING = 5;
        static final int STATE_LOCKED = 2;
        static final int STATE_LOCKING = 1;
        static final int STATE_PRECAPTURE = 3;
        static final int STATE_PREVIEW = 0;
        static final int STATE_WAITING = 4;
        private ReadableMap mOptions = null;
        private int mState;

        PictureCaptureCallback() {
        }

        private void process(@NonNull CaptureResult captureResult) {
            int i = this.mState;
            if (i == 1) {
                Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num == null) {
                    return;
                }
                if (num.intValue() != 4 && num.intValue() != 5) {
                    return;
                }
                Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num2 != null && num2.intValue() != 2) {
                    setState(2);
                    onPrecaptureRequired();
                    return;
                }
                setState(5);
                onReady();
            } else if (i == 3) {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num3 != null && num3.intValue() != 5 && num3.intValue() != 4 && num3.intValue() != 2) {
                    return;
                }
                setState(4);
            } else if (i != 4) {
            } else {
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num4 != null && num4.intValue() == 5) {
                    return;
                }
                setState(5);
                onReady();
            }
        }

        ReadableMap getOptions() {
            return this.mOptions;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
            process(totalCaptureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureResult captureResult) {
            process(captureResult);
        }

        public abstract void onPrecaptureRequired();

        public abstract void onReady();

        void setOptions(ReadableMap readableMap) {
            this.mOptions = readableMap;
        }

        void setState(int i) {
            this.mState = i;
        }
    }

    static {
        INTERNAL_FACINGS.put(0, 1);
        INTERNAL_FACINGS.put(1, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera2(CameraViewImpl.Callback callback, PreviewImpl previewImpl, Context context, Handler handler) {
        super(callback, previewImpl, handler);
        this.mCameraDeviceCallback = new CameraDevice.StateCallback() { // from class: com.google.android.cameraview.Camera2.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onClosed(@NonNull CameraDevice cameraDevice) {
                Camera2.this.mCallback.onCameraClosed();
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                Camera2.this.mCamera = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(@NonNull CameraDevice cameraDevice, int i) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError: ");
                outline107.append(cameraDevice.getId());
                outline107.append(" (");
                outline107.append(i);
                outline107.append(")");
                Log.e(Camera2.TAG, outline107.toString());
                Camera2.this.mCamera = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(@NonNull CameraDevice cameraDevice) {
                Camera2 camera2 = Camera2.this;
                camera2.mCamera = cameraDevice;
                camera2.mCallback.onCameraOpened();
                Camera2.this.startCaptureSession();
            }
        };
        this.mSessionCallback = new CameraCaptureSession.StateCallback() { // from class: com.google.android.cameraview.Camera2.2
            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
                CameraCaptureSession cameraCaptureSession2 = Camera2.this.mCaptureSession;
                if (cameraCaptureSession2 == null || !cameraCaptureSession2.equals(cameraCaptureSession)) {
                    return;
                }
                Camera2.this.mCaptureSession = null;
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                Log.e(Camera2.TAG, "Failed to configure capture session.");
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                Camera2 camera2 = Camera2.this;
                if (camera2.mCamera == null) {
                    return;
                }
                camera2.mCaptureSession = cameraCaptureSession;
                camera2.mInitialCropRegion = (Rect) camera2.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION);
                Camera2.this.updateAutoFocus();
                Camera2.this.updateFlash();
                Camera2.this.updateFocusDepth();
                Camera2.this.updateWhiteBalance();
                Camera2.this.updateZoom();
                try {
                    Camera2.this.mCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), Camera2.this.mCaptureCallback, null);
                } catch (CameraAccessException e) {
                    Log.e(Camera2.TAG, "Failed to start camera preview because it couldn't access camera", e);
                } catch (IllegalStateException e2) {
                    Log.e(Camera2.TAG, "Failed to start camera preview.", e2);
                }
            }
        };
        this.mCaptureCallback = new PictureCaptureCallback() { // from class: com.google.android.cameraview.Camera2.3
            @Override // com.google.android.cameraview.Camera2.PictureCaptureCallback
            public void onPrecaptureRequired() {
                Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                setState(3);
                try {
                    Camera2.this.mCaptureSession.capture(Camera2.this.mPreviewRequestBuilder.build(), this, null);
                    Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
                } catch (CameraAccessException e) {
                    Log.e(Camera2.TAG, "Failed to run precapture sequence.", e);
                }
            }

            @Override // com.google.android.cameraview.Camera2.PictureCaptureCallback
            public void onReady() {
                Camera2.this.captureStillPicture();
            }
        };
        this.mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() { // from class: com.google.android.cameraview.Camera2.4
            @Override // android.media.ImageReader.OnImageAvailableListener
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                try {
                    Image.Plane[] planes = acquireNextImage.getPlanes();
                    if (planes.length > 0) {
                        ByteBuffer buffer = planes[0].getBuffer();
                        byte[] bArr = new byte[buffer.remaining()];
                        buffer.get(bArr);
                        if (acquireNextImage.getFormat() != 256) {
                            Camera2.this.mCallback.onFramePreview(bArr, acquireNextImage.getWidth(), acquireNextImage.getHeight(), Camera2.this.mDisplayOrientation);
                        } else {
                            Camera2.this.mCallback.onPictureTaken(bArr, 0);
                        }
                        acquireNextImage.close();
                    }
                    acquireNextImage.close();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (acquireNextImage != null) {
                            try {
                                acquireNextImage.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
        };
        this.sound = new MediaActionSound();
        this.mAvailableCameras = new HashSet();
        this.mPreviewSizes = new SizeMap();
        this.mPictureSizes = new SizeMap();
        this.mAspectRatio = Constants.DEFAULT_ASPECT_RATIO;
        this.mPlaySoundOnCapture = false;
        this.mPlaySoundOnRecord = false;
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
        this.mCameraManager.registerAvailabilityCallback(new CameraManager.AvailabilityCallback() { // from class: com.google.android.cameraview.Camera2.5
            @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
            public void onCameraAvailable(@NonNull String str) {
                super.onCameraAvailable(str);
                Camera2.this.mAvailableCameras.add(str);
            }

            @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
            public void onCameraUnavailable(@NonNull String str) {
                super.onCameraUnavailable(str);
                Camera2.this.mAvailableCameras.remove(str);
            }
        }, (Handler) null);
        this.mImageFormat = this.mIsScanning ? 35 : 256;
        this.mPreview.setCallback(new PreviewImpl.Callback() { // from class: com.google.android.cameraview.Camera2.6
            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceChanged() {
                Camera2.this.startCaptureSession();
            }

            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceDestroyed() {
                Camera2.this.stop();
            }
        });
    }

    private MeteringRectangle calculateFocusArea(float f, float f2) {
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        return new MeteringRectangle(Math.max(((int) (f * rect.width())) - 150, 0), Math.max(((int) (f2 * rect.height())) - 150, 0), 300, 300, 999);
    }

    private boolean chooseCameraIdByFacing() {
        String str = this._mCameraId;
        if (str != null && !str.isEmpty()) {
            try {
                this.mCameraCharacteristics = this.mCameraManager.getCameraCharacteristics(this._mCameraId);
                Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (num == null) {
                    Log.e(TAG, "Unexpected state: LENS_FACING null");
                    return false;
                }
                int size = INTERNAL_FACINGS.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (INTERNAL_FACINGS.valueAt(i) == num.intValue()) {
                        this.mFacing = INTERNAL_FACINGS.keyAt(i);
                        break;
                    } else {
                        i++;
                    }
                }
                this.mCameraId = this._mCameraId;
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Failed to get camera characteristics", e);
                return false;
            }
        }
        try {
            int i2 = INTERNAL_FACINGS.get(this.mFacing);
            String[] cameraIdList = this.mCameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                Log.e(TAG, "No cameras available.");
                return false;
            }
            for (String str2 : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(str2);
                Integer num2 = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (num2 == null) {
                    Log.e(TAG, "Unexpected state: LENS_FACING null");
                } else if (num2.intValue() == i2) {
                    this.mCameraId = str2;
                    this.mCameraCharacteristics = cameraCharacteristics;
                    return true;
                }
            }
            this.mCameraId = cameraIdList[0];
            this.mCameraCharacteristics = this.mCameraManager.getCameraCharacteristics(this.mCameraId);
            Integer num3 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (num3 == null) {
                Log.e(TAG, "Unexpected state: LENS_FACING null");
                return false;
            }
            int size2 = INTERNAL_FACINGS.size();
            for (int i3 = 0; i3 < size2; i3++) {
                if (INTERNAL_FACINGS.valueAt(i3) == num3.intValue()) {
                    this.mFacing = INTERNAL_FACINGS.keyAt(i3);
                    return true;
                }
            }
            this.mFacing = 0;
            return true;
        } catch (CameraAccessException e2) {
            Log.e(TAG, "Failed to get a list of camera devices", e2);
            return false;
        }
    }

    private Size chooseOptimalSize() {
        int width = this.mPreview.getWidth();
        int height = this.mPreview.getHeight();
        if (width < height) {
            height = width;
            width = height;
        }
        SortedSet<Size> sizes = this.mPreviewSizes.sizes(this.mAspectRatio);
        for (Size size : sizes) {
            if (size.getWidth() >= width && size.getHeight() >= height) {
                return size;
            }
        }
        return sizes.last();
    }

    private void collectCameraInfo() {
        android.util.Size[] outputSizes;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            this.mPreviewSizes.clear();
            for (android.util.Size size : streamConfigurationMap.getOutputSizes(this.mPreview.getOutputClass())) {
                int width = size.getWidth();
                int height = size.getHeight();
                if (width <= MAX_PREVIEW_WIDTH && height <= 1080) {
                    this.mPreviewSizes.add(new Size(width, height));
                }
            }
            this.mPictureSizes.clear();
            collectPictureSizes(this.mPictureSizes, streamConfigurationMap);
            if (this.mPictureSize == null) {
                this.mPictureSize = this.mPictureSizes.sizes(this.mAspectRatio).last();
            }
            for (AspectRatio aspectRatio : this.mPreviewSizes.ratios()) {
                if (!this.mPictureSizes.ratios().contains(aspectRatio)) {
                    this.mPreviewSizes.remove(aspectRatio);
                }
            }
            if (!this.mPreviewSizes.ratios().contains(this.mAspectRatio)) {
                this.mAspectRatio = this.mPreviewSizes.ratios().iterator().next();
            }
            this.mCameraOrientation = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to get configuration map: ");
        outline107.append(this.mCameraId);
        throw new IllegalStateException(outline107.toString());
    }

    private int getOutputRotation() {
        int intValue = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        if (this.mFacing == 0) {
            return (intValue + this.mDeviceOrientation) % 360;
        }
        return ((intValue + this.mDeviceOrientation) + (isLandscape(this.mDeviceOrientation) ? 180 : 0)) % 360;
    }

    private boolean isLandscape(int i) {
        return i == 90 || i == 270;
    }

    public static boolean isLegacy(Context context) {
        try {
            CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
            for (String str : cameraManager.getCameraIdList()) {
                Integer num = (Integer) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                if (num != null && num.intValue() != 2) {
                }
                Log.w(TAG, "Camera2 can only run in legacy mode and should not be used.");
                return true;
            }
            return false;
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to check camera legacy status, returning true.", e);
            return true;
        }
    }

    private boolean isMeteringAreaAFSupported() {
        return ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() >= 1;
    }

    private void lockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        try {
            this.mCaptureCallback.setState(1);
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to lock focus.", e);
        }
    }

    private void pauseMediaRecorder() {
        int i = Build.VERSION.SDK_INT;
        this.mMediaRecorder.pause();
    }

    private void prepareScanImageReader() {
        ImageReader imageReader = this.mScanImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        Size last = this.mPreviewSizes.sizes(this.mAspectRatio).last();
        this.mScanImageReader = ImageReader.newInstance(last.getWidth(), last.getHeight(), 35, 1);
        this.mScanImageReader.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
    }

    private void prepareStillImageReader() {
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        this.mStillImageReader = ImageReader.newInstance(this.mPictureSize.getWidth(), this.mPictureSize.getHeight(), 256, 1);
        this.mStillImageReader.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
    }

    private void resumeMediaRecorder() {
        int i = Build.VERSION.SDK_INT;
        this.mMediaRecorder.resume();
    }

    private void setCamcorderProfile(CamcorderProfile camcorderProfile, boolean z) {
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        this.mMediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
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

    private void setUpMediaRecorder(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile) {
        this.mMediaRecorder = new MediaRecorder();
        this.mMediaRecorder.setVideoSource(2);
        if (z) {
            this.mMediaRecorder.setAudioSource(1);
        }
        this.mMediaRecorder.setOutputFile(str);
        this.mVideoPath = str;
        CamcorderProfile camcorderProfile2 = !CamcorderProfile.hasProfile(Integer.parseInt(this.mCameraId), camcorderProfile.quality) ? CamcorderProfile.get(1) : camcorderProfile;
        camcorderProfile2.videoBitRate = camcorderProfile.videoBitRate;
        setCamcorderProfile(camcorderProfile2, z);
        this.mMediaRecorder.setOrientationHint(getOutputRotation());
        if (i != -1) {
            this.mMediaRecorder.setMaxDuration(i);
        }
        if (i2 != -1) {
            this.mMediaRecorder.setMaxFileSize(i2);
        }
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
    }

    private void startOpeningCamera() {
        try {
            this.mCameraManager.openCamera(this.mCameraId, this.mCameraDeviceCallback, (Handler) null);
        } catch (CameraAccessException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to open camera: ");
            outline107.append(this.mCameraId);
            throw new RuntimeException(outline107.toString(), e);
        }
    }

    private void stopMediaRecorder() {
        this.mIsRecording = false;
        try {
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.abortCaptures();
            this.mMediaRecorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mMediaRecorder.reset();
        this.mMediaRecorder.release();
        this.mMediaRecorder = null;
        this.mCallback.onRecordingEnd();
        if (this.mPlaySoundOnRecord.booleanValue()) {
            this.sound.play(3);
        }
        String str = this.mVideoPath;
        if (str != null && new File(str).exists()) {
            this.mCallback.onVideoRecorded(this.mVideoPath, 0, 0);
            this.mVideoPath = null;
            return;
        }
        this.mCallback.onVideoRecorded(null, 0, 0);
    }

    void captureStillPicture() {
        try {
            CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(2);
            if (this.mIsScanning) {
                this.mImageFormat = 256;
                createCaptureRequest.removeTarget(this.mScanImageReader.getSurface());
            }
            createCaptureRequest.addTarget(this.mStillImageReader.getSurface());
            createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_MODE));
            int i = this.mFlash;
            if (i == 0) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                createCaptureRequest.set(CaptureRequest.FLASH_MODE, 0);
            } else if (i == 1) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 3);
            } else if (i == 2) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                createCaptureRequest.set(CaptureRequest.FLASH_MODE, 2);
            } else if (i == 3) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
            } else if (i == 4) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
            }
            createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(getOutputRotation()));
            if (this.mCaptureCallback.getOptions().hasKey("quality")) {
                createCaptureRequest.set(CaptureRequest.JPEG_QUALITY, Byte.valueOf((byte) (this.mCaptureCallback.getOptions().getDouble("quality") * 100.0d)));
            }
            createCaptureRequest.set(CaptureRequest.SCALER_CROP_REGION, this.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION));
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.capture(createCaptureRequest.build(), new CameraCaptureSession.CaptureCallback() { // from class: com.google.android.cameraview.Camera2.9
                @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
                    if (Camera2.this.mCaptureCallback.getOptions().hasKey("pauseAfterCapture") && !Camera2.this.mCaptureCallback.getOptions().getBoolean("pauseAfterCapture")) {
                        Camera2.this.unlockFocus();
                    }
                    if (Camera2.this.mPlaySoundOnCapture.booleanValue()) {
                        Camera2.this.sound.play(0);
                    }
                }
            }, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Cannot capture a still picture.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void collectPictureSizes(SizeMap sizeMap, StreamConfigurationMap streamConfigurationMap) {
        android.util.Size[] outputSizes;
        for (android.util.Size size : streamConfigurationMap.getOutputSizes(this.mImageFormat)) {
            this.mPictureSizes.add(new Size(size.getWidth(), size.getHeight()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public AspectRatio getAspectRatio() {
        return this.mAspectRatio;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getAutoFocus() {
        return this.mAutoFocus;
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
        String[] cameraIdList;
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : this.mCameraManager.getCameraIdList()) {
                Properties properties = new Properties();
                properties.put("id", str);
                properties.put("type", String.valueOf(((Integer) this.mCameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING)).intValue() == 0 ? 1 : 0));
                arrayList.add(properties);
            }
            return arrayList;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to get a list of camera ids", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getCameraOrientation() {
        return this.mCameraOrientation;
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
        return this.mFocusDepth;
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
        return new Size(this.mPreview.getWidth(), this.mPreview.getHeight());
    }

    public Surface getPreviewSurface() {
        Surface surface = this.mPreviewSurface;
        return surface != null ? surface : this.mPreview.getSurface();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getScanning() {
        return this.mIsScanning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public Set<AspectRatio> getSupportedAspectRatios() {
        return this.mPreviewSizes.ratios();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public ArrayList<int[]> getSupportedPreviewFpsRange() {
        Log.e("CAMERA_2:: ", "getSupportedPreviewFpsRange is not currently supported for Camera2");
        return new ArrayList<>();
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

    @Override // com.google.android.cameraview.CameraViewImpl
    public void pausePreview() {
        try {
            this.mCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
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
        if (!this.mIsRecording) {
            setUpMediaRecorder(str, i, i2, z, camcorderProfile);
            try {
                this.mMediaRecorder.prepare();
                if (this.mCaptureSession != null) {
                    this.mCaptureSession.close();
                    this.mCaptureSession = null;
                }
                Size chooseOptimalSize = chooseOptimalSize();
                this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
                Surface previewSurface = getPreviewSurface();
                Surface surface = this.mMediaRecorder.getSurface();
                this.mPreviewRequestBuilder = this.mCamera.createCaptureRequest(3);
                this.mPreviewRequestBuilder.addTarget(previewSurface);
                this.mPreviewRequestBuilder.addTarget(surface);
                this.mCamera.createCaptureSession(Arrays.asList(previewSurface, surface), this.mSessionCallback, null);
                this.mMediaRecorder.start();
                this.mIsRecording = true;
                this.mCallback.onRecordingStart(this.mVideoPath, 0, 0);
                if (this.mPlaySoundOnRecord.booleanValue()) {
                    this.sound.play(2);
                }
                return true;
            } catch (CameraAccessException | IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void resumePreview() {
        unlockFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void resumeRecording() {
        resumeMediaRecorder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean setAspectRatio(AspectRatio aspectRatio) {
        if (aspectRatio != null && this.mPreviewSizes.isEmpty()) {
            this.mInitialRatio = aspectRatio;
            return false;
        } else if (aspectRatio == null || aspectRatio.equals(this.mAspectRatio) || !this.mPreviewSizes.ratios().contains(aspectRatio)) {
            return false;
        } else {
            this.mAspectRatio = aspectRatio;
            prepareStillImageReader();
            prepareScanImageReader();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession == null) {
                return true;
            }
            cameraCaptureSession.close();
            this.mCaptureSession = null;
            startCaptureSession();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setAutoFocus(boolean z) {
        if (this.mAutoFocus == z) {
            return;
        }
        this.mAutoFocus = z;
        if (this.mPreviewRequestBuilder == null) {
            return;
        }
        updateAutoFocus();
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession == null) {
            return;
        }
        try {
            cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException unused) {
            this.mAutoFocus = !this.mAutoFocus;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setCameraId(String str) {
        if (!ObjectUtils.equals(this._mCameraId, str)) {
            this._mCameraId = str;
            if (ObjectUtils.equals(this._mCameraId, this.mCameraId) || !isCameraOpened()) {
                return;
            }
            stop();
            start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setDeviceOrientation(int i) {
        this.mDeviceOrientation = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setDisplayOrientation(int i) {
        this.mDisplayOrientation = i;
        this.mPreview.setDisplayOrientation(this.mDisplayOrientation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setExposureCompensation(float f) {
        Log.e("CAMERA_2:: ", "Adjusting exposure is not currently supported for Camera2");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFacing(int i) {
        if (this.mFacing == i) {
            return;
        }
        this.mFacing = i;
        if (!isCameraOpened()) {
            return;
        }
        stop();
        start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFlash(int i) {
        int i2 = this.mFlash;
        if (i2 == i) {
            return;
        }
        this.mFlash = i;
        if (this.mPreviewRequestBuilder == null) {
            return;
        }
        updateFlash();
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession == null) {
            return;
        }
        try {
            cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException unused) {
            this.mFlash = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFocusArea(float f, float f2) {
        if (this.mCaptureSession == null) {
            return;
        }
        CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() { // from class: com.google.android.cameraview.Camera2.8
            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                super.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
                if (captureRequest.getTag() == "FOCUS_TAG") {
                    Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, null);
                    try {
                        Camera2.this.mCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), null, null);
                    } catch (CameraAccessException e) {
                        Log.e(Camera2.TAG, "Failed to manual focus.", e);
                    }
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
                super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
                Log.e(Camera2.TAG, "Manual AF failure: " + captureFailure);
            }
        };
        try {
            this.mCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to manual focus.", e);
        }
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), captureCallback, null);
        } catch (CameraAccessException e2) {
            Log.e(TAG, "Failed to manual focus.", e2);
        }
        if (isMeteringAreaAFSupported()) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{calculateFocusArea(f, f2)});
        }
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 1);
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        this.mPreviewRequestBuilder.setTag("FOCUS_TAG");
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), captureCallback, null);
        } catch (CameraAccessException e3) {
            Log.e(TAG, "Failed to manual focus.", e3);
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFocusDepth(float f) {
        float f2 = this.mFocusDepth;
        if (f2 == f) {
            return;
        }
        this.mFocusDepth = f;
        if (this.mCaptureSession == null) {
            return;
        }
        updateFocusDepth();
        try {
            this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException unused) {
            this.mFocusDepth = f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPictureSize(Size size) {
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            this.mCaptureSession.close();
            this.mCaptureSession = null;
        }
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        if (size == null) {
            AspectRatio aspectRatio = this.mAspectRatio;
            if (aspectRatio == null || this.mPictureSize == null) {
                return;
            }
            this.mPictureSizes.sizes(aspectRatio).last();
        } else {
            this.mPictureSize = size;
        }
        prepareStillImageReader();
        startCaptureSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPlaySoundOnCapture(boolean z) {
        this.mPlaySoundOnCapture = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPlaySoundOnRecord(boolean z) {
        this.mPlaySoundOnRecord = Boolean.valueOf(z);
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            this.mPreviewSurface = new Surface(surfaceTexture);
        } else {
            this.mPreviewSurface = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.cameraview.Camera2.7
            @Override // java.lang.Runnable
            public void run() {
                CameraCaptureSession cameraCaptureSession = Camera2.this.mCaptureSession;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.close();
                    Camera2.this.mCaptureSession = null;
                }
                Camera2.this.startCaptureSession();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setScanning(boolean z) {
        if (this.mIsScanning == z) {
            return;
        }
        this.mIsScanning = z;
        if (!this.mIsScanning) {
            this.mImageFormat = 256;
        } else {
            this.mImageFormat = 35;
        }
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCaptureSession = null;
        }
        startCaptureSession();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setWhiteBalance(int i) {
        int i2 = this.mWhiteBalance;
        if (i2 == i) {
            return;
        }
        this.mWhiteBalance = i;
        if (this.mCaptureSession == null) {
            return;
        }
        updateWhiteBalance();
        try {
            this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException unused) {
            this.mWhiteBalance = i2;
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setZoom(float f) {
        float f2 = this.mZoom;
        if (f2 == f) {
            return;
        }
        this.mZoom = f;
        if (this.mCaptureSession == null) {
            return;
        }
        updateZoom();
        try {
            this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException unused) {
            this.mZoom = f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean start() {
        if (!chooseCameraIdByFacing()) {
            this.mAspectRatio = this.mInitialRatio;
            this.mCallback.onMountError();
            return false;
        }
        collectCameraInfo();
        setAspectRatio(this.mInitialRatio);
        this.mInitialRatio = null;
        prepareStillImageReader();
        prepareScanImageReader();
        startOpeningCamera();
        return true;
    }

    void startCaptureSession() {
        if (!isCameraOpened() || !this.mPreview.isReady() || this.mStillImageReader == null || this.mScanImageReader == null) {
            return;
        }
        Size chooseOptimalSize = chooseOptimalSize();
        this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
        Surface previewSurface = getPreviewSurface();
        try {
            this.mPreviewRequestBuilder = this.mCamera.createCaptureRequest(1);
            this.mPreviewRequestBuilder.addTarget(previewSurface);
            if (this.mIsScanning) {
                this.mPreviewRequestBuilder.addTarget(this.mScanImageReader.getSurface());
            }
            this.mCamera.createCaptureSession(Arrays.asList(previewSurface, this.mStillImageReader.getSurface(), this.mScanImageReader.getSurface()), this.mSessionCallback, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to start capture session", e);
            this.mCallback.onMountError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stop() {
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCaptureSession = null;
        }
        CameraDevice cameraDevice = this.mCamera;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.mCamera = null;
        }
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
            this.mStillImageReader = null;
        }
        ImageReader imageReader2 = this.mScanImageReader;
        if (imageReader2 != null) {
            imageReader2.close();
            this.mScanImageReader = null;
        }
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            this.mMediaRecorder.reset();
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
            if (!this.mIsRecording) {
                return;
            }
            this.mCallback.onRecordingEnd();
            this.mCallback.onVideoRecorded(this.mVideoPath, 0, 0);
            this.mIsRecording = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stopRecording() {
        if (this.mIsRecording) {
            stopMediaRecorder();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.close();
                this.mCaptureSession = null;
            }
            startCaptureSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void takePicture(ReadableMap readableMap) {
        this.mCaptureCallback.setOptions(readableMap);
        if (this.mAutoFocus) {
            lockFocus();
        } else {
            captureStillPicture();
        }
    }

    void unlockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            updateAutoFocus();
            updateFlash();
            if (this.mIsScanning) {
                this.mImageFormat = 35;
                startCaptureSession();
            } else {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                this.mCaptureCallback.setState(0);
            }
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to restart camera preview.", e);
        }
    }

    void updateAutoFocus() {
        if (this.mAutoFocus) {
            int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (iArr != null && iArr.length != 0 && (iArr.length != 1 || iArr[0] != 0)) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                return;
            }
            this.mAutoFocus = false;
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
            return;
        }
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
    }

    void updateFlash() {
        int i = this.mFlash;
        if (i == 0) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (i == 1) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 3);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (i == 2) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 2);
        } else if (i == 3) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (i != 4) {
        } else {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 4);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        }
    }

    void updateFocusDepth() {
        if (this.mAutoFocus) {
            return;
        }
        Float f = (Float) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        if (f != null) {
            this.mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(f.floatValue() * this.mFocusDepth));
            return;
        }
        throw new NullPointerException("Unexpected state: LENS_INFO_MINIMUM_FOCUS_DISTANCE null");
    }

    void updateWhiteBalance() {
        int i = this.mWhiteBalance;
        if (i == 0) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 1);
        } else if (i == 1) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 6);
        } else if (i == 2) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 5);
        } else if (i == 3) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 8);
        } else if (i == 4) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 3);
        } else if (i != 5) {
        } else {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 2);
        }
    }

    void updateZoom() {
        float outline0 = GeneratedOutlineSupport1.outline0(((Float) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue(), 1.0f, this.mZoom, 1.0f);
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (rect != null) {
            int width = rect.width();
            int height = rect.height();
            int i = (width - ((int) (width / outline0))) / 2;
            int i2 = (height - ((int) (height / outline0))) / 2;
            Rect rect2 = new Rect(rect.left + i, rect.top + i2, rect.right - i, rect.bottom - i2);
            if (outline0 != 1.0f) {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, rect2);
            } else {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.mInitialCropRegion);
            }
        }
    }
}
