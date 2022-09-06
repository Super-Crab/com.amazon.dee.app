package com.lwansbrough.RCTCamera;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.sharing.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.lwansbrough.RCTCamera.MutableImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class RCTCameraModule extends ReactContextBaseJavaModule implements MediaRecorder.OnInfoListener, MediaRecorder.OnErrorListener, LifecycleEventListener {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int RCT_CAMERA_ASPECT_FILL = 0;
    public static final int RCT_CAMERA_ASPECT_FIT = 1;
    public static final int RCT_CAMERA_ASPECT_STRETCH = 2;
    public static final int RCT_CAMERA_CAPTURE_MODE_STILL = 0;
    public static final int RCT_CAMERA_CAPTURE_MODE_VIDEO = 1;
    public static final String RCT_CAMERA_CAPTURE_QUALITY_1080P = "1080p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_480P = "480p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_720P = "720p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_HIGH = "high";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_LOW = "low";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_MEDIUM = "medium";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_PREVIEW = "preview";
    public static final int RCT_CAMERA_CAPTURE_TARGET_CAMERA_ROLL = 2;
    public static final int RCT_CAMERA_CAPTURE_TARGET_DISK = 1;
    public static final int RCT_CAMERA_CAPTURE_TARGET_MEMORY = 0;
    public static final int RCT_CAMERA_CAPTURE_TARGET_TEMP = 3;
    public static final int RCT_CAMERA_FLASH_MODE_AUTO = 2;
    public static final int RCT_CAMERA_FLASH_MODE_OFF = 0;
    public static final int RCT_CAMERA_FLASH_MODE_ON = 1;
    public static final int RCT_CAMERA_ORIENTATION_AUTO = Integer.MAX_VALUE;
    public static final int RCT_CAMERA_ORIENTATION_LANDSCAPE_LEFT = 1;
    public static final int RCT_CAMERA_ORIENTATION_LANDSCAPE_RIGHT = 3;
    public static final int RCT_CAMERA_ORIENTATION_PORTRAIT = 0;
    public static final int RCT_CAMERA_ORIENTATION_PORTRAIT_UPSIDE_DOWN = 2;
    public static final int RCT_CAMERA_TORCH_MODE_AUTO = 2;
    public static final int RCT_CAMERA_TORCH_MODE_OFF = 0;
    public static final int RCT_CAMERA_TORCH_MODE_ON = 1;
    public static final int RCT_CAMERA_TYPE_BACK = 2;
    public static final int RCT_CAMERA_TYPE_FRONT = 1;
    private static final String TAG = "RCTCameraModule";
    private static ReactApplicationContext _reactContext;
    private long MRStartTime;
    private RCTSensorOrientationChecker _sensorOrientationChecker;
    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private ReadableMap mRecordingOptions;
    private Promise mRecordingPromise;
    private Boolean mSafeToCapture;
    private File mVideoFile;

    public RCTCameraModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCamera = null;
        this.mRecordingPromise = null;
        this.mSafeToCapture = true;
        _reactContext = reactApplicationContext;
        this._sensorOrientationChecker = new RCTSensorOrientationChecker(_reactContext);
        _reactContext.addLifecycleEventListener(this);
    }

    private void addToMediaStore(String str) {
        MediaScannerConnection.scanFile(_reactContext, new String[]{str}, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void captureWithOrientation(final ReadableMap readableMap, final Promise promise, int i) {
        final Camera acquireCameraInstance = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
        if (acquireCameraInstance == null) {
            promise.reject("No camera found.");
        } else if (readableMap.getInt("mode") == 1) {
            record(readableMap, promise, i);
        } else {
            RCTCamera.getInstance().setCaptureQuality(readableMap.getInt("type"), readableMap.getString("quality"));
            if (readableMap.hasKey("playSoundOnCapture") && readableMap.getBoolean("playSoundOnCapture")) {
                new MediaActionSound().play(0);
            }
            if (readableMap.hasKey("quality")) {
                RCTCamera.getInstance().setCaptureQuality(readableMap.getInt("type"), readableMap.getString("quality"));
            }
            RCTCamera.getInstance().adjustCameraRotationToDeviceOrientation(readableMap.getInt("type"), i);
            acquireCameraInstance.setPreviewCallback(null);
            Camera.PictureCallback pictureCallback = new Camera.PictureCallback() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.3
                @Override // android.hardware.Camera.PictureCallback
                public void onPictureTaken(final byte[] bArr, Camera camera) {
                    camera.stopPreview();
                    camera.startPreview();
                    AsyncTask.execute(new Runnable() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RCTCameraModule rCTCameraModule = RCTCameraModule.this;
                            MutableImage mutableImage = new MutableImage(bArr);
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            rCTCameraModule.processImage(mutableImage, readableMap, promise);
                        }
                    });
                    RCTCameraModule.this.mSafeToCapture = true;
                }
            };
            Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.4
                @Override // android.hardware.Camera.ShutterCallback
                public void onShutter() {
                    try {
                        acquireCameraInstance.setPreviewCallback(null);
                        acquireCameraInstance.setPreviewTexture(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            if (!this.mSafeToCapture.booleanValue()) {
                return;
            }
            try {
                acquireCameraInstance.takePicture(shutterCallback, null, pictureCallback);
                this.mSafeToCapture = false;
            } catch (RuntimeException e) {
                Log.e(TAG, "Couldn't capture photo.", e);
            }
        }
    }

    public static byte[] convertFileToByteArray(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File getOutputCameraRollFile(int i) {
        return getOutputFile(i, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    private File getOutputFile(int i, File file) {
        String format;
        if (!file.exists() && !file.mkdirs()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("failed to create directory:");
            outline107.append(file.getAbsolutePath());
            Log.e(TAG, outline107.toString());
            return null;
        }
        String format2 = String.format("%s", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        if (i == 1) {
            format = String.format("IMG_%s.jpg", format2);
        } else if (i == 2) {
            format = String.format("VID_%s.mp4", format2);
        } else {
            Log.e(TAG, "Unsupported media type:" + i);
            return null;
        }
        return new File(String.format("%s%s%s", file.getPath(), File.separator, format));
    }

    private File getOutputMediaFile(int i) {
        String str;
        if (i == 1) {
            str = Environment.DIRECTORY_PICTURES;
        } else if (i == 2) {
            str = Environment.DIRECTORY_MOVIES;
        } else {
            Log.e(TAG, "Unsupported media type:" + i);
            return null;
        }
        return getOutputFile(i, Environment.getExternalStoragePublicDirectory(str));
    }

    public static ReactApplicationContext getReactContextSingleton() {
        return _reactContext;
    }

    private File getTempMediaFile(int i) {
        try {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File cacheDir = _reactContext.getCacheDir();
            if (i == 1) {
                return File.createTempFile("IMG_" + format, Constants.DEFAULT_IMAGE_EXTENSION, cacheDir);
            } else if (i == 2) {
                return File.createTempFile("VID_" + format, ".mp4", cacheDir);
            } else {
                Log.e(TAG, "Unsupported media type:" + i);
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private Throwable prepareMediaRecorder(ReadableMap readableMap, int i) {
        CamcorderProfile captureVideoQuality = RCTCamera.getInstance().setCaptureVideoQuality(readableMap.getInt("type"), readableMap.getString("quality"));
        if (captureVideoQuality == null) {
            return new RuntimeException("CamcorderProfile not found in prepareMediaRecorder.");
        }
        this.mCamera.unlock();
        this.mMediaRecorder = new MediaRecorder();
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
        this.mMediaRecorder.setCamera(this.mCamera);
        this.mMediaRecorder.setAudioSource(5);
        this.mMediaRecorder.setVideoSource(1);
        if (i == 0) {
            this.mMediaRecorder.setOrientationHint(90);
        } else if (i == 1) {
            this.mMediaRecorder.setOrientationHint(0);
        } else if (i == 2) {
            this.mMediaRecorder.setOrientationHint(270);
        } else if (i == 3) {
            this.mMediaRecorder.setOrientationHint(180);
        }
        captureVideoQuality.fileFormat = 2;
        this.mMediaRecorder.setProfile(captureVideoQuality);
        this.mVideoFile = null;
        int i2 = readableMap.getInt("target");
        if (i2 == 0) {
            this.mVideoFile = getTempMediaFile(2);
        } else if (i2 == 2) {
            this.mVideoFile = getOutputCameraRollFile(2);
        } else if (i2 != 3) {
            this.mVideoFile = getOutputMediaFile(2);
        } else {
            this.mVideoFile = getTempMediaFile(2);
        }
        File file = this.mVideoFile;
        if (file == null) {
            return new RuntimeException("Error while preparing output file in prepareMediaRecorder.");
        }
        this.mMediaRecorder.setOutputFile(file.getPath());
        if (readableMap.hasKey("totalSeconds")) {
            this.mMediaRecorder.setMaxDuration(readableMap.getInt("totalSeconds") * 1000);
        }
        if (readableMap.hasKey("maxFileSize")) {
            this.mMediaRecorder.setMaxFileSize(readableMap.getInt("maxFileSize"));
        }
        try {
            this.mMediaRecorder.prepare();
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Media recorder prepare error.", e);
            releaseMediaRecorder();
            return e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void processImage(MutableImage mutableImage, ReadableMap readableMap, Promise promise) {
        boolean z;
        boolean z2 = false;
        if (readableMap.hasKey("fixOrientation") && readableMap.getBoolean("fixOrientation")) {
            try {
                mutableImage.fixOrientation();
            } catch (MutableImage.ImageMutationFailedException e) {
                promise.reject("Error fixing orientation image", e);
            }
        }
        double width = mutableImage.getWidth() / mutableImage.getHeight();
        try {
            int i = readableMap.getInt("type");
            double previewVisibleWidth = RCTCamera.getInstance().getPreviewVisibleWidth(i) / RCTCamera.getInstance().getPreviewVisibleHeight(i);
            z = ((previewVisibleWidth > 1.0d ? 1 : (previewVisibleWidth == 1.0d ? 0 : -1)) > 0) != ((width > 1.0d ? 1 : (width == 1.0d ? 0 : -1)) > 0);
            width = previewVisibleWidth;
        } catch (IllegalArgumentException unused) {
            z = false;
        }
        if (readableMap.hasKey("cropToPreview") && readableMap.getBoolean("cropToPreview")) {
            if (z) {
                width = 1.0d / width;
            }
            try {
                mutableImage.cropToPreview(width);
            } catch (IllegalArgumentException e2) {
                promise.reject("Error cropping image to preview", e2);
            }
        }
        if (readableMap.hasKey("mirrorImage") && readableMap.getBoolean("mirrorImage")) {
            z2 = true;
        }
        if (z2) {
            try {
                mutableImage.mirrorImage();
            } catch (MutableImage.ImageMutationFailedException e3) {
                promise.reject("Error mirroring image", e3);
            }
        }
        int i2 = 80;
        if (readableMap.hasKey("jpegQuality")) {
            i2 = readableMap.getInt("jpegQuality");
        }
        int height = z ? mutableImage.getHeight() : mutableImage.getWidth();
        int width2 = z ? mutableImage.getWidth() : mutableImage.getHeight();
        int i3 = readableMap.getInt("target");
        if (i3 == 0) {
            String base64 = mutableImage.toBase64(i2);
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("data", base64);
            writableNativeMap.putInt("width", height);
            writableNativeMap.putInt("height", width2);
            promise.resolve(writableNativeMap);
        } else if (i3 == 1) {
            File outputMediaFile = getOutputMediaFile(1);
            if (outputMediaFile == null) {
                promise.reject("Error creating media file.");
                return;
            }
            try {
                mutableImage.writeDataToFile(outputMediaFile, readableMap, i2);
                resolveImage(outputMediaFile, height, width2, promise, false);
            } catch (IOException e4) {
                promise.reject("failed to save image file", e4);
            }
        } else if (i3 == 2) {
            File outputCameraRollFile = getOutputCameraRollFile(1);
            if (outputCameraRollFile == null) {
                promise.reject("Error creating media file.");
                return;
            }
            try {
                mutableImage.writeDataToFile(outputCameraRollFile, readableMap, i2);
                addToMediaStore(outputCameraRollFile.getAbsolutePath());
                resolveImage(outputCameraRollFile, height, width2, promise, true);
            } catch (IOException e5) {
                e = e5;
                promise.reject("failed to save image file", e);
            } catch (NullPointerException e6) {
                e = e6;
                promise.reject("failed to save image file", e);
            }
        } else if (i3 == 3) {
            File tempMediaFile = getTempMediaFile(1);
            if (tempMediaFile == null) {
                promise.reject("Error creating media file.");
                return;
            }
            try {
                mutableImage.writeDataToFile(tempMediaFile, readableMap, i2);
                resolveImage(tempMediaFile, height, width2, promise, false);
            } catch (IOException e7) {
                promise.reject("failed to save image file", e7);
            }
        }
    }

    private void record(ReadableMap readableMap, Promise promise, int i) {
        if (this.mRecordingPromise != null) {
            return;
        }
        this.mCamera = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
        if (this.mCamera == null) {
            promise.reject(new RuntimeException("No camera found."));
            return;
        }
        Throwable prepareMediaRecorder = prepareMediaRecorder(readableMap, i);
        if (prepareMediaRecorder != null) {
            promise.reject(prepareMediaRecorder);
            return;
        }
        try {
            this.mMediaRecorder.start();
            this.MRStartTime = System.currentTimeMillis();
            this.mRecordingOptions = readableMap;
            this.mRecordingPromise = promise;
        } catch (Exception e) {
            Log.e(TAG, "Media recorder start error.", e);
            promise.reject(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0080, code lost:
        if (r4 != 3) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void releaseMediaRecorder() {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lwansbrough.RCTCamera.RCTCameraModule.releaseMediaRecorder():void");
    }

    private void resolveImage(File file, int i, int i2, final Promise promise, boolean z) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(RouteParameter.PATH, Uri.fromFile(file).toString());
        writableNativeMap.putInt("width", i);
        writableNativeMap.putInt("height", i2);
        if (z) {
            MediaScannerConnection.scanFile(_reactContext, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.5
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                    if (uri != null) {
                        writableNativeMap.putString("mediaUri", uri.toString());
                    }
                    promise.resolve(writableNativeMap);
                }
            });
        } else {
            promise.resolve(writableNativeMap);
        }
    }

    @ReactMethod
    public void capture(final ReadableMap readableMap, final Promise promise) {
        if (RCTCamera.getInstance() == null) {
            promise.reject("Camera is not ready yet.");
            return;
        }
        int i = readableMap.hasKey("orientation") ? readableMap.getInt("orientation") : RCTCamera.getInstance().getOrientation();
        if (i == Integer.MAX_VALUE) {
            this._sensorOrientationChecker.onResume();
            this._sensorOrientationChecker.registerOrientationListener(new RCTSensorOrientationListener() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.2
                @Override // com.lwansbrough.RCTCamera.RCTSensorOrientationListener
                public void orientationEvent() {
                    int orientation = RCTCameraModule.this._sensorOrientationChecker.getOrientation();
                    RCTCameraModule.this._sensorOrientationChecker.unregisterOrientationListener();
                    RCTCameraModule.this._sensorOrientationChecker.onPause();
                    RCTCameraModule.this.captureWithOrientation(readableMap, promise, orientation);
                }
            });
            return;
        }
        captureWithOrientation(readableMap, promise, i);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1
            {
                put("Aspect", getAspectConstants());
                put("BarCodeType", getBarCodeConstants());
                put("Type", getTypeConstants());
                put("CaptureQuality", getCaptureQualityConstants());
                put("CaptureMode", getCaptureModeConstants());
                put("CaptureTarget", getCaptureTargetConstants());
                put(ExifInterface.TAG_ORIENTATION, getOrientationConstants());
                put("FlashMode", getFlashModeConstants());
                put("TorchMode", getTorchModeConstants());
            }

            private Map<String, Object> getAspectConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.1
                    {
                        put("stretch", 2);
                        put("fit", 1);
                        put("fill", 0);
                    }
                });
            }

            private Map<String, Object> getBarCodeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.2
                });
            }

            private Map<String, Object> getCaptureModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.5
                    {
                        put("still", 0);
                        put("video", 1);
                    }
                });
            }

            private Map<String, Object> getCaptureQualityConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.4
                    {
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH);
                        put("photo", RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_PREVIEW, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_PREVIEW);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_480P, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_480P);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_720P, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_720P);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_1080P, RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_1080P);
                    }
                });
            }

            private Map<String, Object> getCaptureTargetConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.6
                    {
                        put("memory", 0);
                        put("disk", 1);
                        put("cameraRoll", 2);
                        put("temp", 3);
                    }
                });
            }

            private Map<String, Object> getFlashModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.8
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 2);
                    }
                });
            }

            private Map<String, Object> getOrientationConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.7
                    {
                        put("auto", Integer.MAX_VALUE);
                        put("landscapeLeft", 1);
                        put("landscapeRight", 3);
                        put("portrait", 0);
                        put("portraitUpsideDown", 2);
                    }
                });
            }

            private Map<String, Object> getTorchModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.9
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 2);
                    }
                });
            }

            private Map<String, Object> getTypeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: com.lwansbrough.RCTCamera.RCTCameraModule.1.3
                    {
                        put("front", 1);
                        put("back", 2);
                    }
                });
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void hasFlash(ReadableMap readableMap, Promise promise) {
        Camera acquireCameraInstance = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
        if (acquireCameraInstance == null) {
            promise.reject("No camera found.");
            return;
        }
        List<String> supportedFlashModes = acquireCameraInstance.getParameters().getSupportedFlashModes();
        promise.resolve(Boolean.valueOf(supportedFlashModes != null && !supportedFlashModes.isEmpty()));
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mSafeToCapture = true;
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if ((i == 800 || i == 801) && this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    @ReactMethod
    public void setZoom(ReadableMap readableMap, int i) {
        Camera acquireCameraInstance;
        RCTCamera rCTCamera = RCTCamera.getInstance();
        if (rCTCamera == null || (acquireCameraInstance = rCTCamera.acquireCameraInstance(readableMap.getInt("type"))) == null) {
            return;
        }
        Camera.Parameters parameters = acquireCameraInstance.getParameters();
        int maxZoom = parameters.getMaxZoom();
        if (!parameters.isZoomSupported() || i < 0 || i >= maxZoom) {
            return;
        }
        parameters.setZoom(i);
        try {
            acquireCameraInstance.setParameters(parameters);
        } catch (RuntimeException e) {
            Log.e(TAG, "setParameters failed", e);
        }
    }

    @ReactMethod
    public void stopCapture(Promise promise) {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
            promise.resolve("Finished recording.");
            return;
        }
        promise.resolve("Not recording.");
    }
}
