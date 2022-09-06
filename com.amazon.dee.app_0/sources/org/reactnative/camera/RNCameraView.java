package org.reactnative.camera;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.CamcorderProfile;
import android.os.AsyncTask;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.amazon.alexa.routing.api.RouteParameter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.cameraview.CameraView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.tasks.BarCodeScannerAsyncTask;
import org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate;
import org.reactnative.camera.tasks.BarcodeDetectorAsyncTask;
import org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.FaceDetectorAsyncTask;
import org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.PictureSavedDelegate;
import org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import org.reactnative.camera.tasks.TextRecognizerAsyncTask;
import org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate;
import org.reactnative.camera.utils.RNFileUtils;
import org.reactnative.facedetector.RNFaceDetector;
/* loaded from: classes5.dex */
public class RNCameraView extends CameraView implements LifecycleEventListener, BarCodeScannerAsyncTaskDelegate, FaceDetectorAsyncTaskDelegate, BarcodeDetectorAsyncTaskDelegate, TextRecognizerAsyncTaskDelegate, PictureSavedDelegate {
    public volatile boolean barCodeScannerTaskLock;
    public volatile boolean faceDetectorTaskLock;
    public volatile boolean googleBarcodeDetectorTaskLock;
    private boolean invertImageData;
    private List<String> mBarCodeTypes;
    private int mCameraViewHeight;
    private int mCameraViewWidth;
    private boolean mDetectedImageInEvent;
    private int mFaceDetectionClassifications;
    private int mFaceDetectionLandmarks;
    private RNFaceDetector mFaceDetector;
    private int mFaceDetectorMode;
    private GestureDetector mGestureDetector;
    private RNBarcodeDetector mGoogleBarcodeDetector;
    private int mGoogleVisionBarCodeMode;
    private int mGoogleVisionBarCodeType;
    private boolean mIsNew;
    private boolean mIsPaused;
    private Boolean mIsRecording;
    private Boolean mIsRecordingInterrupted;
    private boolean mLimitScanArea;
    private MultiFormatReader mMultiFormatReader;
    private int mPaddingX;
    private int mPaddingY;
    private Map<Promise, File> mPictureTakenDirectories;
    private Map<Promise, ReadableMap> mPictureTakenOptions;
    private Queue<Promise> mPictureTakenPromises;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScanAreaHeight;
    private float mScanAreaWidth;
    private float mScanAreaX;
    private float mScanAreaY;
    private boolean mShouldDetectFaces;
    private boolean mShouldDetectTouches;
    private boolean mShouldGoogleDetectBarcodes;
    private boolean mShouldRecognizeText;
    private boolean mShouldScanBarCodes;
    private ThemedReactContext mThemedReactContext;
    private boolean mTrackingEnabled;
    private boolean mUseNativeZoom;
    private Promise mVideoRecordedPromise;
    private GestureDetector.SimpleOnGestureListener onGestureListener;
    private ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener;
    public volatile boolean textRecognizerTaskLock;

    public RNCameraView(ThemedReactContext themedReactContext) {
        super(themedReactContext, true);
        this.mPictureTakenPromises = new ConcurrentLinkedQueue();
        this.mPictureTakenOptions = new ConcurrentHashMap();
        this.mPictureTakenDirectories = new ConcurrentHashMap();
        this.mBarCodeTypes = null;
        this.mDetectedImageInEvent = false;
        this.mIsPaused = false;
        this.mIsNew = true;
        this.invertImageData = false;
        this.mIsRecording = false;
        this.mIsRecordingInterrupted = false;
        this.mUseNativeZoom = false;
        this.barCodeScannerTaskLock = false;
        this.faceDetectorTaskLock = false;
        this.googleBarcodeDetectorTaskLock = false;
        this.textRecognizerTaskLock = false;
        this.mShouldDetectFaces = false;
        this.mShouldGoogleDetectBarcodes = false;
        this.mShouldScanBarCodes = false;
        this.mShouldRecognizeText = false;
        this.mShouldDetectTouches = false;
        this.mFaceDetectorMode = RNFaceDetector.FAST_MODE;
        this.mFaceDetectionLandmarks = RNFaceDetector.NO_LANDMARKS;
        this.mFaceDetectionClassifications = RNFaceDetector.NO_CLASSIFICATIONS;
        this.mGoogleVisionBarCodeType = RNBarcodeDetector.ALL_FORMATS;
        this.mGoogleVisionBarCodeMode = RNBarcodeDetector.NORMAL_MODE;
        this.mTrackingEnabled = true;
        this.mLimitScanArea = false;
        this.mScanAreaX = 0.0f;
        this.mScanAreaY = 0.0f;
        this.mScanAreaWidth = 0.0f;
        this.mScanAreaHeight = 0.0f;
        this.mCameraViewWidth = 0;
        this.mCameraViewHeight = 0;
        this.onGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: org.reactnative.camera.RNCameraView.6
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                RNCameraView rNCameraView = RNCameraView.this;
                RNCameraViewHelper.emitTouchEvent(rNCameraView, true, rNCameraView.scalePosition(motionEvent.getX()), RNCameraView.this.scalePosition(motionEvent.getY()));
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                RNCameraView rNCameraView = RNCameraView.this;
                RNCameraViewHelper.emitTouchEvent(rNCameraView, false, rNCameraView.scalePosition(motionEvent.getX()), RNCameraView.this.scalePosition(motionEvent.getY()));
                return true;
            }
        };
        this.onScaleGestureListener = new ScaleGestureDetector.OnScaleGestureListener() { // from class: org.reactnative.camera.RNCameraView.7
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                RNCameraView.this.onZoom(scaleGestureDetector.getScaleFactor());
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                RNCameraView.this.onZoom(scaleGestureDetector.getScaleFactor());
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        };
        this.mThemedReactContext = themedReactContext;
        themedReactContext.addLifecycleEventListener(this);
        addCallback(new CameraView.Callback() { // from class: org.reactnative.camera.RNCameraView.1
            @Override // com.google.android.cameraview.CameraView.Callback
            public void onCameraOpened(CameraView cameraView) {
                RNCameraViewHelper.emitCameraReadyEvent(cameraView);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r15v1 */
            /* JADX WARN: Type inference failed for: r15v4 */
            @Override // com.google.android.cameraview.CameraView.Callback
            public void onFramePreview(CameraView cameraView, byte[] bArr, int i, int i2, int i3) {
                int i4;
                boolean z;
                int i5;
                AnonymousClass1 anonymousClass1;
                int correctCameraRotation = RNCameraViewHelper.getCorrectCameraRotation(i3, RNCameraView.this.getFacing(), RNCameraView.this.getCameraOrientation());
                boolean z2 = RNCameraView.this.mShouldScanBarCodes && !RNCameraView.this.barCodeScannerTaskLock && (cameraView instanceof BarCodeScannerAsyncTaskDelegate);
                boolean z3 = RNCameraView.this.mShouldDetectFaces && !RNCameraView.this.faceDetectorTaskLock && (cameraView instanceof FaceDetectorAsyncTaskDelegate);
                boolean z4 = RNCameraView.this.mShouldGoogleDetectBarcodes && !RNCameraView.this.googleBarcodeDetectorTaskLock && (cameraView instanceof BarcodeDetectorAsyncTaskDelegate);
                boolean z5 = RNCameraView.this.mShouldRecognizeText && !RNCameraView.this.textRecognizerTaskLock && (cameraView instanceof TextRecognizerAsyncTaskDelegate);
                if ((z2 || z3 || z4 || z5) && bArr.length >= i * 1.5d * i2) {
                    if (z2) {
                        RNCameraView rNCameraView = RNCameraView.this;
                        rNCameraView.barCodeScannerTaskLock = true;
                        i4 = 0;
                        new BarCodeScannerAsyncTask((BarCodeScannerAsyncTaskDelegate) cameraView, rNCameraView.mMultiFormatReader, bArr, i, i2, RNCameraView.this.mLimitScanArea, RNCameraView.this.mScanAreaX, RNCameraView.this.mScanAreaY, RNCameraView.this.mScanAreaWidth, RNCameraView.this.mScanAreaHeight, RNCameraView.this.mCameraViewWidth, RNCameraView.this.mCameraViewHeight, RNCameraView.this.getAspectRatio().toFloat()).execute(new Void[0]);
                    } else {
                        i4 = 0;
                    }
                    if (z3) {
                        int i6 = i4;
                        anonymousClass1 = this;
                        RNCameraView rNCameraView2 = RNCameraView.this;
                        rNCameraView2.faceDetectorTaskLock = true;
                        z = true;
                        new FaceDetectorAsyncTask((FaceDetectorAsyncTaskDelegate) cameraView, rNCameraView2.mFaceDetector, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY).execute(new Void[i6]);
                        i5 = i6;
                    } else {
                        z = true;
                        i5 = i4;
                        anonymousClass1 = this;
                    }
                    if (z4) {
                        RNCameraView rNCameraView3 = RNCameraView.this;
                        rNCameraView3.googleBarcodeDetectorTaskLock = z;
                        if (rNCameraView3.mGoogleVisionBarCodeMode == RNBarcodeDetector.NORMAL_MODE) {
                            RNCameraView.this.invertImageData = i5;
                        } else if (RNCameraView.this.mGoogleVisionBarCodeMode == RNBarcodeDetector.ALTERNATE_MODE) {
                            RNCameraView.this.invertImageData ^= z;
                        } else if (RNCameraView.this.mGoogleVisionBarCodeMode == RNBarcodeDetector.INVERTED_MODE) {
                            RNCameraView.this.invertImageData = z;
                        }
                        if (RNCameraView.this.invertImageData) {
                            for (int i7 = i5; i7 < bArr.length; i7++) {
                                bArr[i7] = (byte) (~bArr[i7]);
                            }
                        }
                        new BarcodeDetectorAsyncTask((BarcodeDetectorAsyncTaskDelegate) cameraView, RNCameraView.this.mGoogleBarcodeDetector, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY).execute(new Void[i5]);
                    }
                    if (!z5) {
                        return;
                    }
                    RNCameraView rNCameraView4 = RNCameraView.this;
                    rNCameraView4.textRecognizerTaskLock = true;
                    new TextRecognizerAsyncTask((TextRecognizerAsyncTaskDelegate) cameraView, rNCameraView4.mThemedReactContext, bArr, i, i2, correctCameraRotation, RNCameraView.this.getResources().getDisplayMetrics().density, RNCameraView.this.getFacing(), RNCameraView.this.getWidth(), RNCameraView.this.getHeight(), RNCameraView.this.mPaddingX, RNCameraView.this.mPaddingY).execute(new Void[i5]);
                }
            }

            @Override // com.google.android.cameraview.CameraView.Callback
            public void onMountError(CameraView cameraView) {
                RNCameraViewHelper.emitMountErrorEvent(cameraView, "Camera view threw an error - component could not be rendered.");
            }

            @Override // com.google.android.cameraview.CameraView.Callback
            public void onPictureTaken(CameraView cameraView, byte[] bArr, int i) {
                Promise promise = (Promise) RNCameraView.this.mPictureTakenPromises.poll();
                ReadableMap readableMap = (ReadableMap) RNCameraView.this.mPictureTakenOptions.remove(promise);
                if (readableMap.hasKey("fastMode") && readableMap.getBoolean("fastMode")) {
                    promise.resolve(null);
                }
                File file = (File) RNCameraView.this.mPictureTakenDirectories.remove(promise);
                int i2 = Build.VERSION.SDK_INT;
                new ResolveTakenPictureAsyncTask(bArr, promise, readableMap, file, i, RNCameraView.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                RNCameraViewHelper.emitPictureTakenEvent(cameraView);
            }

            @Override // com.google.android.cameraview.CameraView.Callback
            public void onRecordingEnd(CameraView cameraView) {
                RNCameraViewHelper.emitRecordingEndEvent(cameraView);
            }

            @Override // com.google.android.cameraview.CameraView.Callback
            public void onRecordingStart(CameraView cameraView, String str, int i, int i2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("videoOrientation", i);
                createMap.putInt("deviceOrientation", i2);
                createMap.putString("uri", RNFileUtils.uriFromFile(new File(str)).toString());
                RNCameraViewHelper.emitRecordingStartEvent(cameraView, createMap);
            }

            @Override // com.google.android.cameraview.CameraView.Callback
            public void onVideoRecorded(CameraView cameraView, String str, int i, int i2) {
                if (RNCameraView.this.mVideoRecordedPromise != null) {
                    if (str == null) {
                        RNCameraView.this.mVideoRecordedPromise.reject("E_RECORDING", "Couldn't stop recording - there is none in progress");
                    } else {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("isRecordingInterrupted", RNCameraView.this.mIsRecordingInterrupted.booleanValue());
                        createMap.putInt("videoOrientation", i);
                        createMap.putInt("deviceOrientation", i2);
                        createMap.putString("uri", RNFileUtils.uriFromFile(new File(str)).toString());
                        RNCameraView.this.mVideoRecordedPromise.resolve(createMap);
                    }
                    RNCameraView.this.mIsRecording = false;
                    RNCameraView.this.mIsRecordingInterrupted = false;
                    RNCameraView.this.mVideoRecordedPromise = null;
                }
            }
        });
    }

    private boolean hasCameraPermissions() {
        int i = Build.VERSION.SDK_INT;
        return ContextCompat.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0;
    }

    private void initBarcodeReader() {
        this.mMultiFormatReader = new MultiFormatReader();
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet noneOf = EnumSet.noneOf(BarcodeFormat.class);
        List<String> list = this.mBarCodeTypes;
        if (list != null) {
            for (String str : list) {
                String str2 = (String) CameraModule.VALID_BARCODE_TYPES.get(str);
                if (str2 != null) {
                    noneOf.add(BarcodeFormat.valueOf(str2));
                }
            }
        }
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) noneOf);
        this.mMultiFormatReader.setHints(enumMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onZoom(float f) {
        float zoom = getZoom();
        float f2 = (f - 1.0f) + zoom;
        if (f2 > zoom) {
            setZoom(Math.min(f2, 1.0f));
        } else {
            setZoom(Math.max(f2, 0.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int scalePosition(float f) {
        Resources resources = getResources();
        resources.getConfiguration();
        return (int) (f / resources.getDisplayMetrics().density);
    }

    private void setupBarcodeDetector() {
        this.mGoogleBarcodeDetector = new RNBarcodeDetector(this.mThemedReactContext);
        this.mGoogleBarcodeDetector.setBarcodeType(this.mGoogleVisionBarCodeType);
    }

    private void setupFaceDetector() {
        this.mFaceDetector = new RNFaceDetector(this.mThemedReactContext);
        this.mFaceDetector.setMode(this.mFaceDetectorMode);
        this.mFaceDetector.setLandmarkType(this.mFaceDetectionLandmarks);
        this.mFaceDetector.setClassificationType(this.mFaceDetectionClassifications);
        this.mFaceDetector.setTracking(this.mTrackingEnabled);
    }

    @Override // org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate
    public void onBarCodeRead(Result result, int i, int i2, byte[] bArr) {
        byte[] bArr2;
        String str = result.getBarcodeFormat().toString();
        if (!this.mShouldScanBarCodes || !this.mBarCodeTypes.contains(str)) {
            return;
        }
        if (this.mDetectedImageInEvent) {
            try {
                YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                bArr2 = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(String.format("Error decoding imageData from NV21 format (%d bytes)", Integer.valueOf(bArr.length)), e);
            }
        } else {
            bArr2 = null;
        }
        RNCameraViewHelper.emitBarCodeReadEvent(this, result, i, i2, bArr2);
    }

    @Override // org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate
    public void onBarCodeScanningTaskCompleted() {
        this.barCodeScannerTaskLock = false;
        MultiFormatReader multiFormatReader = this.mMultiFormatReader;
        if (multiFormatReader != null) {
            multiFormatReader.reset();
        }
    }

    @Override // org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate
    public void onBarcodeDetectingTaskCompleted() {
        this.googleBarcodeDetectorTaskLock = false;
    }

    @Override // org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate
    public void onBarcodeDetectionError(RNBarcodeDetector rNBarcodeDetector) {
        if (!this.mShouldGoogleDetectBarcodes) {
            return;
        }
        RNCameraViewHelper.emitBarcodeDetectionErrorEvent(this, rNBarcodeDetector);
    }

    @Override // org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate
    public void onBarcodesDetected(WritableArray writableArray, int i, int i2, byte[] bArr) {
        byte[] bArr2;
        if (!this.mShouldGoogleDetectBarcodes) {
            return;
        }
        if (this.mDetectedImageInEvent) {
            try {
                YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                bArr2 = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(String.format("Error decoding imageData from NV21 format (%d bytes)", Integer.valueOf(bArr.length)), e);
            }
        } else {
            bArr2 = null;
        }
        RNCameraViewHelper.emitBarcodesDetectedEvent(this, writableArray, bArr2);
    }

    @Override // org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate
    public void onFaceDetectingTaskCompleted() {
        this.faceDetectorTaskLock = false;
    }

    @Override // org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate
    public void onFaceDetectionError(RNFaceDetector rNFaceDetector) {
        if (!this.mShouldDetectFaces) {
            return;
        }
        RNCameraViewHelper.emitFaceDetectionErrorEvent(this, rNFaceDetector);
    }

    @Override // org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate
    public void onFacesDetected(WritableArray writableArray) {
        if (!this.mShouldDetectFaces) {
            return;
        }
        RNCameraViewHelper.emitFacesDetectedEvent(this, writableArray);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.release();
        }
        RNBarcodeDetector rNBarcodeDetector = this.mGoogleBarcodeDetector;
        if (rNBarcodeDetector != null) {
            rNBarcodeDetector.release();
        }
        this.mMultiFormatReader = null;
        this.mThemedReactContext.removeLifecycleEventListener(this);
        this.mBgHandler.post(new Runnable() { // from class: org.reactnative.camera.RNCameraView.5
            @Override // java.lang.Runnable
            public void run() {
                RNCameraView.this.stop();
                RNCameraView.this.cleanup();
            }
        });
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        if (this.mIsRecording.booleanValue()) {
            this.mIsRecordingInterrupted = true;
        }
        if (this.mIsPaused || !isCameraOpened()) {
            return;
        }
        this.mIsPaused = true;
        stop();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        if (hasCameraPermissions()) {
            this.mBgHandler.post(new Runnable() { // from class: org.reactnative.camera.RNCameraView.4
                @Override // java.lang.Runnable
                public void run() {
                    if ((!RNCameraView.this.mIsPaused || RNCameraView.this.isCameraOpened()) && !RNCameraView.this.mIsNew) {
                        return;
                    }
                    RNCameraView.this.mIsPaused = false;
                    RNCameraView.this.mIsNew = false;
                    RNCameraView.this.start();
                }
            });
        } else {
            RNCameraViewHelper.emitMountErrorEvent(this, "Camera permissions not granted - component could not be rendered.");
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        View view = getView();
        if (view == null) {
            return;
        }
        float f = i3 - i;
        float f2 = i4 - i2;
        float f3 = getAspectRatio().toFloat();
        int i7 = getResources().getConfiguration().orientation;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (i7 == 2) {
            float f4 = f3 * f2;
            if (f4 < f) {
                i6 = (int) (f / f3);
                i5 = (int) f;
            } else {
                i5 = (int) f4;
                i6 = (int) f2;
            }
        } else {
            float f5 = f3 * f;
            if (f5 > f2) {
                i6 = (int) f5;
                i5 = (int) f;
            } else {
                i5 = (int) (f2 / f3);
                i6 = (int) f2;
            }
        }
        int i8 = (int) ((f - i5) / 2.0f);
        int i9 = (int) ((f2 - i6) / 2.0f);
        this.mPaddingX = i8;
        this.mPaddingY = i9;
        view.layout(i8, i9, i5 + i8, i6 + i9);
    }

    @Override // org.reactnative.camera.tasks.PictureSavedDelegate
    public void onPictureSaved(WritableMap writableMap) {
        RNCameraViewHelper.emitPictureSavedEvent(this, writableMap);
    }

    @Override // org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate
    public void onTextRecognized(WritableArray writableArray) {
        if (!this.mShouldRecognizeText) {
            return;
        }
        RNCameraViewHelper.emitTextRecognizedEvent(this, writableArray);
    }

    @Override // org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate
    public void onTextRecognizerTaskCompleted() {
        this.textRecognizerTaskLock = false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mUseNativeZoom) {
            this.mScaleGestureDetector.onTouchEvent(motionEvent);
        }
        if (this.mShouldDetectTouches) {
            this.mGestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return true;
    }

    public void record(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() { // from class: org.reactnative.camera.RNCameraView.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = readableMap.hasKey(RouteParameter.PATH) ? readableMap.getString(RouteParameter.PATH) : RNFileUtils.getOutputFilePath(file, ".mp4");
                    int i = readableMap.hasKey("maxDuration") ? readableMap.getInt("maxDuration") : -1;
                    int i2 = readableMap.hasKey("maxFileSize") ? readableMap.getInt("maxFileSize") : -1;
                    int i3 = readableMap.hasKey("fps") ? readableMap.getInt("fps") : -1;
                    CamcorderProfile camcorderProfile = readableMap.hasKey("quality") ? RNCameraViewHelper.getCamcorderProfile(readableMap.getInt("quality")) : CamcorderProfile.get(1);
                    if (readableMap.hasKey("videoBitrate")) {
                        camcorderProfile.videoBitRate = readableMap.getInt("videoBitrate");
                    }
                    if (RNCameraView.super.record(string, i * 1000, i2, readableMap.hasKey("mute") ? !readableMap.getBoolean("mute") : true, camcorderProfile, readableMap.hasKey("orientation") ? readableMap.getInt("orientation") : 0, i3)) {
                        RNCameraView.this.mIsRecording = true;
                        RNCameraView.this.mVideoRecordedPromise = promise;
                        return;
                    }
                    promise.reject("E_RECORDING_FAILED", "Starting video recording failed. Another recording might be in progress.");
                } catch (IOException unused) {
                    promise.reject("E_RECORDING_FAILED", "Starting video recording failed - could not create video file.");
                }
            }
        });
    }

    @Override // android.view.View, android.view.ViewParent
    @SuppressLint({"all"})
    public void requestLayout() {
    }

    public void setBarCodeTypes(List<String> list) {
        this.mBarCodeTypes = list;
        initBarcodeReader();
    }

    public void setCameraViewDimensions(int i, int i2) {
        this.mCameraViewWidth = i;
        this.mCameraViewHeight = i2;
    }

    public void setDetectedImageInEvent(boolean z) {
        this.mDetectedImageInEvent = z;
    }

    public void setFaceDetectionClassifications(int i) {
        this.mFaceDetectionClassifications = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setClassificationType(i);
        }
    }

    public void setFaceDetectionLandmarks(int i) {
        this.mFaceDetectionLandmarks = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setLandmarkType(i);
        }
    }

    public void setFaceDetectionMode(int i) {
        this.mFaceDetectorMode = i;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setMode(i);
        }
    }

    public void setGoogleVisionBarcodeMode(int i) {
        this.mGoogleVisionBarCodeMode = i;
    }

    public void setGoogleVisionBarcodeType(int i) {
        this.mGoogleVisionBarCodeType = i;
        RNBarcodeDetector rNBarcodeDetector = this.mGoogleBarcodeDetector;
        if (rNBarcodeDetector != null) {
            rNBarcodeDetector.setBarcodeType(i);
        }
    }

    public void setRectOfInterest(float f, float f2, float f3, float f4) {
        this.mLimitScanArea = true;
        this.mScanAreaX = f;
        this.mScanAreaY = f2;
        this.mScanAreaWidth = f3;
        this.mScanAreaHeight = f4;
    }

    public void setShouldDetectFaces(boolean z) {
        if (z && this.mFaceDetector == null) {
            setupFaceDetector();
        }
        this.mShouldDetectFaces = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void setShouldDetectTouches(boolean z) {
        if (!this.mShouldDetectTouches && z) {
            this.mGestureDetector = new GestureDetector(this.mThemedReactContext, this.onGestureListener);
        } else {
            this.mGestureDetector = null;
        }
        this.mShouldDetectTouches = z;
    }

    public void setShouldGoogleDetectBarcodes(boolean z) {
        if (z && this.mGoogleBarcodeDetector == null) {
            setupBarcodeDetector();
        }
        this.mShouldGoogleDetectBarcodes = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void setShouldRecognizeText(boolean z) {
        this.mShouldRecognizeText = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void setShouldScanBarCodes(boolean z) {
        if (z && this.mMultiFormatReader == null) {
            initBarcodeReader();
        }
        this.mShouldScanBarCodes = z;
        setScanning(this.mShouldDetectFaces || this.mShouldGoogleDetectBarcodes || this.mShouldScanBarCodes || this.mShouldRecognizeText);
    }

    public void setTracking(boolean z) {
        this.mTrackingEnabled = z;
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        if (rNFaceDetector != null) {
            rNFaceDetector.setTracking(z);
        }
    }

    public void setUseNativeZoom(boolean z) {
        if (!this.mUseNativeZoom && z) {
            this.mScaleGestureDetector = new ScaleGestureDetector(this.mThemedReactContext, this.onScaleGestureListener);
        } else {
            this.mScaleGestureDetector = null;
        }
        this.mUseNativeZoom = z;
    }

    public void takePicture(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() { // from class: org.reactnative.camera.RNCameraView.2
            @Override // java.lang.Runnable
            public void run() {
                RNCameraView.this.mPictureTakenPromises.add(promise);
                RNCameraView.this.mPictureTakenOptions.put(promise, readableMap);
                RNCameraView.this.mPictureTakenDirectories.put(promise, file);
                try {
                    RNCameraView.super.takePicture(readableMap);
                } catch (Exception e) {
                    RNCameraView.this.mPictureTakenPromises.remove(promise);
                    RNCameraView.this.mPictureTakenOptions.remove(promise);
                    RNCameraView.this.mPictureTakenDirectories.remove(promise);
                    promise.reject("E_TAKE_PICTURE_FAILED", e.getMessage());
                }
            }
        });
    }
}
