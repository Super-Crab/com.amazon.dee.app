package org.reactnative.camera;

import android.content.pm.PackageInfo;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.android.cameraview.AspectRatio;
import com.google.android.cameraview.Size;
import com.google.zxing.BarcodeFormat;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.reactnative.barcodedetector.BarcodeFormatUtils;
import org.reactnative.camera.utils.ScopedContext;
import org.reactnative.facedetector.RNFaceDetector;
/* loaded from: classes5.dex */
public class CameraModule extends ReactContextBaseJavaModule {
    static final int GOOGLE_VISION_BARCODE_MODE_ALTERNATE = 1;
    static final int GOOGLE_VISION_BARCODE_MODE_INVERTED = 2;
    static final int GOOGLE_VISION_BARCODE_MODE_NORMAL = 0;
    private static final String TAG = "CameraModule";
    public static final Map<String, Object> VALID_BARCODE_TYPES = Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.1
        {
            put("aztec", BarcodeFormat.AZTEC.toString());
            put("ean13", BarcodeFormat.EAN_13.toString());
            put("ean8", BarcodeFormat.EAN_8.toString());
            put("qr", BarcodeFormat.QR_CODE.toString());
            put("pdf417", BarcodeFormat.PDF_417.toString());
            put("upc_e", BarcodeFormat.UPC_E.toString());
            put("datamatrix", BarcodeFormat.DATA_MATRIX.toString());
            put("code39", BarcodeFormat.CODE_39.toString());
            put("code93", BarcodeFormat.CODE_93.toString());
            put("interleaved2of5", BarcodeFormat.ITF.toString());
            put("codabar", BarcodeFormat.CODABAR.toString());
            put("code128", BarcodeFormat.CODE_128.toString());
            put("maxicode", BarcodeFormat.MAXICODE.toString());
            put("rss14", BarcodeFormat.RSS_14.toString());
            put("rssexpanded", BarcodeFormat.RSS_EXPANDED.toString());
            put("upc_a", BarcodeFormat.UPC_A.toString());
            put("upc_ean", BarcodeFormat.UPC_EAN_EXTENSION.toString());
        }
    });
    static final int VIDEO_1080P = 1;
    static final int VIDEO_2160P = 0;
    static final int VIDEO_480P = 3;
    static final int VIDEO_4x3 = 4;
    static final int VIDEO_720P = 2;
    private ScopedContext mScopedContext;

    public CameraModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mScopedContext = new ScopedContext(reactApplicationContext);
    }

    @ReactMethod
    public void checkIfRecordAudioPermissionsAreDefined(Promise promise) {
        try {
            PackageInfo packageInfo = getCurrentActivity().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String str : packageInfo.requestedPermissions) {
                    if (str.equals("android.permission.RECORD_AUDIO")) {
                        promise.resolve(true);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        promise.resolve(false);
    }

    @ReactMethod
    public void checkIfVideoIsValid(final String str, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: org.reactnative.camera.CameraModule.14
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                boolean z = true;
                try {
                    try {
                        mediaMetadataRetriever.setDataSource(str);
                        String extractMetadata = mediaMetadataRetriever.extractMetadata(17);
                        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(12);
                        Promise promise2 = promise;
                        if (extractMetadata == null || (!"yes".equals(extractMetadata) && !"true".equals(extractMetadata) && (extractMetadata2 == null || !extractMetadata2.contains("video")))) {
                            z = false;
                        }
                        promise2.resolve(Boolean.valueOf(z));
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Throwable unused) {
                        }
                    } catch (Throwable th) {
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Throwable unused2) {
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    promise.resolve(true);
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Throwable unused3) {
                    }
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getAvailablePictureSizes(final String str, final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.12
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    WritableArray createArray = Arguments.createArray();
                    if (rNCameraView.isCameraOpened()) {
                        for (Size size : rNCameraView.getAvailablePictureSizes(AspectRatio.parse(str))) {
                            createArray.pushString(size.toString());
                        }
                        promise.resolve(createArray);
                        return;
                    }
                    promise.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                } catch (Exception unused) {
                    promise.reject("E_CAMERA_BAD_VIEWTAG", "getAvailablePictureSizesAsync: Expected a Camera component");
                }
            }
        });
    }

    @ReactMethod
    public void getCameraIds(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.11
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    WritableArray createArray = Arguments.createArray();
                    for (Properties properties : ((RNCameraView) nativeViewHierarchyManager.resolveView(i)).getCameraIds()) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString("id", properties.getProperty("id"));
                        writableNativeMap.putInt("type", Integer.valueOf(properties.getProperty("type")).intValue());
                        createArray.pushMap(writableNativeMap);
                    }
                    promise.resolve(createArray);
                } catch (Exception e) {
                    e.printStackTrace();
                    promise.reject("E_CAMERA_FAILED", e.getMessage());
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2
            {
                put("Type", getTypeConstants());
                put("FlashMode", getFlashModeConstants());
                put("AutoFocus", getAutoFocusConstants());
                put(ExifInterface.TAG_WHITE_BALANCE, getWhiteBalanceConstants());
                put("VideoQuality", getVideoQualityConstants());
                put("BarCodeType", getBarCodeConstants());
                put("FaceDetection", Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.1
                    {
                        put(DriveModeMetrics.SubComponentType.MODE, getFaceDetectionModeConstants());
                        put("Landmarks", getFaceDetectionLandmarksConstants());
                        put("Classifications", getFaceDetectionClassificationsConstants());
                    }

                    private Map<String, Object> getFaceDetectionClassificationsConstants() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.1.2
                            {
                                put("all", Integer.valueOf(RNFaceDetector.ALL_CLASSIFICATIONS));
                                put("none", Integer.valueOf(RNFaceDetector.NO_CLASSIFICATIONS));
                            }
                        });
                    }

                    private Map<String, Object> getFaceDetectionLandmarksConstants() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.1.3
                            {
                                put("all", Integer.valueOf(RNFaceDetector.ALL_LANDMARKS));
                                put("none", Integer.valueOf(RNFaceDetector.NO_LANDMARKS));
                            }
                        });
                    }

                    private Map<String, Object> getFaceDetectionModeConstants() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.1.1
                            {
                                put("fast", Integer.valueOf(RNFaceDetector.FAST_MODE));
                                put("accurate", Integer.valueOf(RNFaceDetector.ACCURATE_MODE));
                            }
                        });
                    }
                }));
                put("GoogleVisionBarcodeDetection", Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.2
                    {
                        put("BarcodeType", BarcodeFormatUtils.REVERSE_FORMATS);
                        put("BarcodeMode", getGoogleVisionBarcodeModeConstants());
                    }
                }));
                put(ExifInterface.TAG_ORIENTATION, Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.3
                    {
                        put("auto", 0);
                        put("portrait", 1);
                        put("portraitUpsideDown", 2);
                        put("landscapeLeft", 3);
                        put("landscapeRight", 4);
                    }
                }));
            }

            private Map<String, Object> getAutoFocusConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.6
                    {
                        put("on", true);
                        put("off", false);
                    }
                });
            }

            private Map<String, Object> getBarCodeConstants() {
                return CameraModule.VALID_BARCODE_TYPES;
            }

            private Map<String, Object> getFlashModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.5
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 3);
                        put("torch", 2);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Map<String, Object> getGoogleVisionBarcodeModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.9
                    {
                        put(DriveModeVoxUiConstants.NORMAL, 0);
                        put("ALTERNATE", 1);
                        put("INVERTED", 2);
                    }
                });
            }

            private Map<String, Object> getTypeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.4
                    {
                        put("front", 1);
                        put("back", 0);
                    }
                });
            }

            private Map<String, Object> getVideoQualityConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.8
                    {
                        put("2160p", 0);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_1080P, 1);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_720P, 2);
                        put(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_480P, 3);
                        put("4:3", 4);
                    }
                });
            }

            private Map<String, Object> getWhiteBalanceConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() { // from class: org.reactnative.camera.CameraModule.2.7
                    {
                        put("auto", 0);
                        put("cloudy", 1);
                        put("sunny", 2);
                        put("shadow", 3);
                        put("fluorescent", 4);
                        put("incandescent", 5);
                    }
                });
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCameraModule";
    }

    public ScopedContext getScopedContext() {
        return this.mScopedContext;
    }

    @ReactMethod
    public void getSupportedPreviewFpsRange(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.13
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    WritableArray createArray = Arguments.createArray();
                    Iterator<int[]> it2 = ((RNCameraView) nativeViewHierarchyManager.resolveView(i)).getSupportedPreviewFpsRange().iterator();
                    while (it2.hasNext()) {
                        int[] next = it2.next();
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putInt("MINIMUM_FPS", next[0]);
                        writableNativeMap.putInt("MAXIMUM_FPS", next[1]);
                        createArray.pushMap(writableNativeMap);
                    }
                    promise.resolve(createArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void getSupportedRatios(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.10
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    WritableArray createArray = Arguments.createArray();
                    if (rNCameraView.isCameraOpened()) {
                        for (AspectRatio aspectRatio : rNCameraView.getSupportedAspectRatios()) {
                            createArray.pushString(aspectRatio.toString());
                        }
                        promise.resolve(createArray);
                        return;
                    }
                    promise.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void pausePreview(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.3
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (!rNCameraView.isCameraOpened()) {
                        return;
                    }
                    rNCameraView.pausePreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void pauseRecording(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.8
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (!rNCameraView.isCameraOpened()) {
                        return;
                    }
                    rNCameraView.pauseRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void record(final ReadableMap readableMap, final int i, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final File cacheDirectory = this.mScopedContext.getCacheDirectory();
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.6
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (rNCameraView.isCameraOpened()) {
                        rNCameraView.record(readableMap, promise, cacheDirectory);
                    } else {
                        promise.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                    }
                } catch (Exception e) {
                    promise.reject("E_CAPTURE_FAILED", e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void resumePreview(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.4
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (!rNCameraView.isCameraOpened()) {
                        return;
                    }
                    rNCameraView.resumePreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void resumeRecording(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.9
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (!rNCameraView.isCameraOpened()) {
                        return;
                    }
                    rNCameraView.resumeRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void stopRecording(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.7
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (!rNCameraView.isCameraOpened()) {
                        return;
                    }
                    rNCameraView.stopRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void takePicture(final ReadableMap readableMap, final int i, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final File cacheDirectory = this.mScopedContext.getCacheDirectory();
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: org.reactnative.camera.CameraModule.5
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                try {
                    if (rNCameraView.isCameraOpened()) {
                        rNCameraView.takePicture(readableMap, promise, cacheDirectory);
                    } else {
                        promise.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                    }
                } catch (Exception e) {
                    promise.reject("E_TAKE_PICTURE_FAILED", e.getMessage());
                }
            }
        });
    }
}
