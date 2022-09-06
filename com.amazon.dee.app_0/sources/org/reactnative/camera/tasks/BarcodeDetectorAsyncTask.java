package org.reactnative.camera.tasks;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.barcode.Barcode;
import org.reactnative.barcodedetector.BarcodeFormatUtils;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.frame.RNFrameFactory;
/* loaded from: classes5.dex */
public class BarcodeDetectorAsyncTask extends AsyncTask<Void, Void, SparseArray<Barcode>> {
    private RNBarcodeDetector mBarcodeDetector;
    private BarcodeDetectorAsyncTaskDelegate mDelegate;
    private int mHeight;
    private byte[] mImageData;
    private ImageDimensions mImageDimensions;
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mRotation;
    private double mScaleX;
    private double mScaleY;
    private int mWidth;

    public BarcodeDetectorAsyncTask(BarcodeDetectorAsyncTaskDelegate barcodeDetectorAsyncTaskDelegate, RNBarcodeDetector rNBarcodeDetector, byte[] bArr, int i, int i2, int i3, float f, int i4, int i5, int i6, int i7, int i8) {
        this.mImageData = bArr;
        this.mWidth = i;
        this.mHeight = i2;
        this.mRotation = i3;
        this.mDelegate = barcodeDetectorAsyncTaskDelegate;
        this.mBarcodeDetector = rNBarcodeDetector;
        this.mImageDimensions = new ImageDimensions(i, i2, i3, i4);
        this.mScaleX = i5 / (this.mImageDimensions.getWidth() * f);
        this.mScaleY = i6 / (this.mImageDimensions.getHeight() * f);
        this.mPaddingLeft = i7;
        this.mPaddingTop = i8;
    }

    private WritableMap processBounds(Rect rect) {
        WritableMap createMap = Arguments.createMap();
        int i = rect.left;
        int i2 = rect.top;
        int i3 = this.mWidth;
        if (i < i3 / 2) {
            i += this.mPaddingLeft / 2;
        } else if (i > i3 / 2) {
            i -= this.mPaddingLeft / 2;
        }
        int i4 = rect.top;
        int i5 = this.mHeight;
        if (i4 < i5 / 2) {
            i2 += this.mPaddingTop / 2;
        } else if (i4 > i5 / 2) {
            i2 -= this.mPaddingTop / 2;
        }
        createMap.putDouble(ReactProperties.HereMapMarker.X, i * this.mScaleX);
        createMap.putDouble(ReactProperties.HereMapMarker.Y, i2 * this.mScaleY);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("width", rect.width() * this.mScaleX);
        createMap2.putDouble("height", rect.height() * this.mScaleY);
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putMap("origin", createMap);
        createMap3.putMap("size", createMap2);
        return createMap3;
    }

    private WritableArray serializeEventData(SparseArray<Barcode> sparseArray) {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < sparseArray.size(); i++) {
            Barcode valueAt = sparseArray.valueAt(i);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("data", valueAt.displayValue);
            createMap.putString("rawData", valueAt.rawValue);
            createMap.putString("type", BarcodeFormatUtils.get(valueAt.format));
            createMap.putMap("bounds", processBounds(valueAt.getBoundingBox()));
            createArray.pushMap(createMap);
        }
        return createArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public SparseArray<Barcode> doInBackground(Void... voidArr) {
        RNBarcodeDetector rNBarcodeDetector;
        if (isCancelled() || this.mDelegate == null || (rNBarcodeDetector = this.mBarcodeDetector) == null || !rNBarcodeDetector.isOperational()) {
            return null;
        }
        return this.mBarcodeDetector.detect(RNFrameFactory.buildFrame(this.mImageData, this.mWidth, this.mHeight, this.mRotation));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(SparseArray<Barcode> sparseArray) {
        super.onPostExecute((BarcodeDetectorAsyncTask) sparseArray);
        if (sparseArray == null) {
            this.mDelegate.onBarcodeDetectionError(this.mBarcodeDetector);
            return;
        }
        if (sparseArray.size() > 0) {
            this.mDelegate.onBarcodesDetected(serializeEventData(sparseArray), this.mWidth, this.mHeight, this.mImageData);
        }
        this.mDelegate.onBarcodeDetectingTaskCompleted();
    }
}
