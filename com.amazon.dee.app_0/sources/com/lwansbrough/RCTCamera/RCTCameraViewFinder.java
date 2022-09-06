package com.lwansbrough.RCTCamera;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class RCTCameraViewFinder extends TextureView implements TextureView.SurfaceTextureListener, Camera.PreviewCallback {
    public static volatile boolean barcodeScannerTaskLock = false;
    private Camera _camera;
    private int _cameraType;
    private int _captureMode;
    private boolean _clearWindowBackground;
    private boolean _isStarting;
    private boolean _isStopping;
    private final MultiFormatReader _multiFormatReader;
    private SurfaceTexture _surfaceTexture;
    private int _surfaceTextureHeight;
    private int _surfaceTextureWidth;
    private float mFingerSpacing;

    /* loaded from: classes3.dex */
    private class ReaderAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Camera camera;
        private byte[] imageData;

        ReaderAsyncTask(Camera camera, byte[] bArr) {
            this.camera = camera;
            this.imageData = bArr;
        }

        private Result getBarcode(int i, int i2, boolean z) {
            BinaryBitmap binaryBitmap;
            try {
                PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(this.imageData, i, i2, 0, 0, i, i2, false);
                if (z) {
                    binaryBitmap = new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource.invert()));
                } else {
                    binaryBitmap = new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource));
                }
                Result decodeWithState = RCTCameraViewFinder.this._multiFormatReader.decodeWithState(binaryBitmap);
                RCTCameraViewFinder.this._multiFormatReader.reset();
                return decodeWithState;
            } catch (Throwable unused) {
                RCTCameraViewFinder.this._multiFormatReader.reset();
                return null;
            }
        }

        private Result getBarcodeAnyOrientation() {
            Camera.Size previewSize = this.camera.getParameters().getPreviewSize();
            int i = previewSize.width;
            int i2 = previewSize.height;
            Result barcode = getBarcode(i, i2, false);
            if (barcode != null) {
                return barcode;
            }
            Result barcode2 = getBarcode(i, i2, true);
            if (barcode2 != null) {
                return barcode2;
            }
            rotateImage(i, i2);
            int i3 = previewSize.height;
            int i4 = previewSize.width;
            Result barcode3 = getBarcode(i3, i4, false);
            return barcode3 != null ? barcode3 : getBarcode(i3, i4, true);
        }

        private void rotateImage(int i, int i2) {
            byte[] bArr = new byte[this.imageData.length];
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = (i3 * i2) + i4;
                    int i6 = (((i4 * i) + i) - i3) - 1;
                    if (i5 >= 0) {
                        byte[] bArr2 = this.imageData;
                        if (i5 < bArr2.length && i6 >= 0 && i6 < bArr2.length) {
                            bArr[i6] = bArr2[i5];
                        }
                    }
                }
            }
            this.imageData = bArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            Result barcodeAnyOrientation;
            if (isCancelled()) {
                return null;
            }
            try {
                barcodeAnyOrientation = getBarcodeAnyOrientation();
            } catch (Throwable unused) {
            }
            if (barcodeAnyOrientation != null) {
                ReactApplicationContext reactContextSingleton = RCTCameraModule.getReactContextSingleton();
                WritableMap createMap = Arguments.createMap();
                WritableArray createArray = Arguments.createArray();
                ResultPoint[] resultPoints = barcodeAnyOrientation.getResultPoints();
                if (resultPoints != null) {
                    for (ResultPoint resultPoint : resultPoints) {
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putString(ReactProperties.HereMapMarker.X, String.valueOf(resultPoint.getX()));
                        createMap2.putString(ReactProperties.HereMapMarker.Y, String.valueOf(resultPoint.getY()));
                        createArray.pushMap(createMap2);
                    }
                }
                createMap.putArray("bounds", createArray);
                createMap.putString("data", barcodeAnyOrientation.getText());
                createMap.putString("type", barcodeAnyOrientation.getBarcodeFormat().toString());
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContextSingleton.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("CameraBarCodeReadAndroid", createMap);
                RCTCameraViewFinder.this._multiFormatReader.reset();
                RCTCameraViewFinder.barcodeScannerTaskLock = false;
                return null;
            }
            throw new Exception();
        }
    }

    public RCTCameraViewFinder(Context context, int i) {
        super(context);
        this._clearWindowBackground = false;
        this._multiFormatReader = new MultiFormatReader();
        setSurfaceTextureListener(this);
        this._cameraType = i;
        initBarcodeReader(RCTCamera.getInstance().getBarCodeTypes());
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private float getFingerSpacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((y * y) + (x * x));
    }

    private void handleZoom(MotionEvent motionEvent, Camera.Parameters parameters) {
        int maxZoom = parameters.getMaxZoom();
        int zoom = parameters.getZoom();
        float fingerSpacing = getFingerSpacing(motionEvent);
        float f = this.mFingerSpacing;
        if (fingerSpacing > f) {
            if (zoom < maxZoom) {
                zoom++;
            }
        } else if (fingerSpacing < f && zoom > 0) {
            zoom--;
        }
        this.mFingerSpacing = fingerSpacing;
        parameters.setZoom(zoom);
        try {
            this._camera.setParameters(parameters);
        } catch (RuntimeException e) {
            Log.e("RCTCameraViewFinder", "setParameters failed", e);
        }
    }

    private void initBarcodeReader(List<String> list) {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet noneOf = EnumSet.noneOf(BarcodeFormat.class);
        if (list != null) {
            for (String str : list) {
                BarcodeFormat parseBarCodeString = parseBarCodeString(str);
                if (parseBarCodeString != null) {
                    noneOf.add(parseBarCodeString);
                }
            }
        }
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) noneOf);
        this._multiFormatReader.setHints(enumMap);
    }

    private BarcodeFormat parseBarCodeString(String str) {
        if ("aztec".equals(str)) {
            return BarcodeFormat.AZTEC;
        }
        if ("ean13".equals(str)) {
            return BarcodeFormat.EAN_13;
        }
        if ("ean8".equals(str)) {
            return BarcodeFormat.EAN_8;
        }
        if ("qr".equals(str)) {
            return BarcodeFormat.QR_CODE;
        }
        if ("pdf417".equals(str)) {
            return BarcodeFormat.PDF_417;
        }
        if ("upce".equals(str)) {
            return BarcodeFormat.UPC_E;
        }
        if ("datamatrix".equals(str)) {
            return BarcodeFormat.DATA_MATRIX;
        }
        if ("code39".equals(str)) {
            return BarcodeFormat.CODE_39;
        }
        if ("code93".equals(str)) {
            return BarcodeFormat.CODE_93;
        }
        if ("interleaved2of5".equals(str)) {
            return BarcodeFormat.ITF;
        }
        if ("codabar".equals(str)) {
            return BarcodeFormat.CODABAR;
        }
        if ("code128".equals(str)) {
            return BarcodeFormat.CODE_128;
        }
        if ("maxicode".equals(str)) {
            return BarcodeFormat.MAXICODE;
        }
        if ("rss14".equals(str)) {
            return BarcodeFormat.RSS_14;
        }
        if ("rssexpanded".equals(str)) {
            return BarcodeFormat.RSS_EXPANDED;
        }
        if ("upca".equals(str)) {
            return BarcodeFormat.UPC_A;
        }
        if ("upceanextension".equals(str)) {
            return BarcodeFormat.UPC_EAN_EXTENSION;
        }
        String str2 = "Unsupported code.. [" + str + "]";
        return null;
    }

    private synchronized void startCamera() {
        List<Camera.Size> supportedVideoSizes;
        Activity activity;
        if (!this._isStarting) {
            boolean z = true;
            this._isStarting = true;
            try {
                try {
                    this._camera = RCTCamera.getInstance().acquireCameraInstance(this._cameraType);
                    Camera.Parameters parameters = this._camera.getParameters();
                    boolean z2 = this._captureMode == 0;
                    if (this._captureMode != 1) {
                        z = false;
                    }
                    if (!z2 && !z) {
                        throw new RuntimeException("Unsupported capture mode:" + this._captureMode);
                    }
                    List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                    if (z2 && supportedFocusModes.contains("continuous-picture")) {
                        parameters.setFocusMode("continuous-picture");
                    } else if (z && supportedFocusModes.contains("continuous-video")) {
                        parameters.setFocusMode("continuous-video");
                    } else if (supportedFocusModes.contains("auto")) {
                        parameters.setFocusMode("auto");
                    }
                    if (z2) {
                        supportedVideoSizes = parameters.getSupportedPictureSizes();
                    } else if (z) {
                        supportedVideoSizes = RCTCamera.getInstance().getSupportedVideoSizes(this._camera);
                    } else {
                        throw new RuntimeException("Unsupported capture mode:" + this._captureMode);
                    }
                    Camera.Size bestSize = RCTCamera.getInstance().getBestSize(supportedVideoSizes, Integer.MAX_VALUE, Integer.MAX_VALUE);
                    parameters.setPictureSize(bestSize.width, bestSize.height);
                    try {
                        this._camera.setParameters(parameters);
                    } catch (RuntimeException e) {
                        Log.e("RCTCameraViewFinder", "setParameters failed", e);
                    }
                    this._camera.setPreviewTexture(this._surfaceTexture);
                    this._camera.startPreview();
                    if (this._clearWindowBackground && (activity = getActivity()) != null) {
                        activity.getWindow().setBackgroundDrawable(null);
                    }
                    this._camera.setPreviewCallback(this);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    stopCamera();
                }
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
            this._isStarting = false;
        }
    }

    private synchronized void stopCamera() {
        if (!this._isStopping) {
            this._isStopping = true;
            try {
                if (this._camera != null) {
                    this._camera.stopPreview();
                    this._camera.setPreviewCallback(null);
                    RCTCamera.getInstance().releaseCameraInstance(this._cameraType);
                    this._camera = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this._isStopping = false;
        }
    }

    public int getCameraType() {
        return this._cameraType;
    }

    public double getRatio() {
        return RCTCamera.getInstance().getPreviewWidth(this._cameraType) / RCTCamera.getInstance().getPreviewHeight(this._cameraType);
    }

    public void handleFocus(MotionEvent motionEvent, Camera.Parameters parameters) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null || !supportedFocusModes.contains("auto") || parameters.getMaxNumFocusAreas() == 0) {
            return;
        }
        this._camera.cancelAutoFocus();
        try {
            Camera.Area computeFocusAreaFromMotionEvent = RCTCameraUtils.computeFocusAreaFromMotionEvent(motionEvent, this._surfaceTextureWidth, this._surfaceTextureHeight);
            parameters.setFocusMode("auto");
            ArrayList arrayList = new ArrayList();
            arrayList.add(computeFocusAreaFromMotionEvent);
            parameters.setFocusAreas(arrayList);
            if (parameters.getMaxNumMeteringAreas() > 0) {
                parameters.setMeteringAreas(arrayList);
            }
            try {
                this._camera.setParameters(parameters);
            } catch (RuntimeException e) {
                Log.e("RCTCameraViewFinder", "setParameters failed", e);
            }
            try {
                this._camera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.lwansbrough.RCTCamera.RCTCameraViewFinder.2
                    @Override // android.hardware.Camera.AutoFocusCallback
                    public void onAutoFocus(boolean z, Camera camera) {
                        if (z) {
                            camera.cancelAutoFocus();
                        }
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (RuntimeException e3) {
            e3.printStackTrace();
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (!RCTCamera.getInstance().isBarcodeScannerEnabled() || barcodeScannerTaskLock) {
            return;
        }
        barcodeScannerTaskLock = true;
        new ReaderAsyncTask(camera, bArr).execute(new Void[0]);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this._surfaceTexture = surfaceTexture;
        this._surfaceTextureWidth = i;
        this._surfaceTextureHeight = i2;
        startCamera();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this._surfaceTexture = null;
        this._surfaceTextureWidth = 0;
        this._surfaceTextureHeight = 0;
        stopCamera();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this._surfaceTextureWidth = i;
        this._surfaceTextureHeight = i2;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Camera camera = this._camera;
        if (camera == null) {
            return false;
        }
        Camera.Parameters parameters = camera.getParameters();
        int action = motionEvent.getAction();
        if (motionEvent.getPointerCount() > 1) {
            if (action == 5) {
                this.mFingerSpacing = getFingerSpacing(motionEvent);
            } else if (action == 2 && parameters.isZoomSupported()) {
                this._camera.cancelAutoFocus();
                handleZoom(motionEvent, parameters);
            }
        } else if (action == 1) {
            handleFocus(motionEvent, parameters);
        }
        return true;
    }

    public void setCameraType(final int i) {
        if (this._cameraType == i) {
            return;
        }
        new Thread(new Runnable() { // from class: com.lwansbrough.RCTCamera.RCTCameraViewFinder.1
            @Override // java.lang.Runnable
            public void run() {
                RCTCameraViewFinder.this.stopPreview();
                RCTCameraViewFinder.this._cameraType = i;
                RCTCameraViewFinder.this.startPreview();
            }
        }).start();
    }

    public void setCaptureMode(int i) {
        RCTCamera.getInstance().setCaptureMode(this._cameraType, i);
        this._captureMode = i;
    }

    public void setCaptureQuality(String str) {
        RCTCamera.getInstance().setCaptureQuality(this._cameraType, str);
    }

    public void setClearWindowBackground(boolean z) {
        this._clearWindowBackground = z;
    }

    public void setFlashMode(int i) {
        RCTCamera.getInstance().setFlashMode(this._cameraType, i);
    }

    public void setTorchMode(int i) {
        RCTCamera.getInstance().setTorchMode(this._cameraType, i);
    }

    public void setZoom(int i) {
        RCTCamera.getInstance().setZoom(this._cameraType, i);
    }

    public void startPreview() {
        if (this._surfaceTexture != null) {
            startCamera();
        }
    }

    public void stopPreview() {
        if (this._camera != null) {
            stopCamera();
        }
    }
}
