package org.reactnative.camera.tasks;

import android.os.AsyncTask;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
/* loaded from: classes5.dex */
public class BarCodeScannerAsyncTask extends AsyncTask<Void, Void, Result> {
    private int mCameraViewHeight;
    private int mCameraViewWidth;
    private BarCodeScannerAsyncTaskDelegate mDelegate;
    private int mHeight;
    private byte[] mImageData;
    private boolean mLimitScanArea;
    private final MultiFormatReader mMultiFormatReader;
    private float mRatio;
    private float mScanAreaHeight;
    private float mScanAreaWidth;
    private float mScanAreaX;
    private float mScanAreaY;
    private int mWidth;

    public BarCodeScannerAsyncTask(BarCodeScannerAsyncTaskDelegate barCodeScannerAsyncTaskDelegate, MultiFormatReader multiFormatReader, byte[] bArr, int i, int i2, boolean z, float f, float f2, float f3, float f4, int i3, int i4, float f5) {
        this.mImageData = bArr;
        this.mWidth = i;
        this.mHeight = i2;
        this.mDelegate = barCodeScannerAsyncTaskDelegate;
        this.mMultiFormatReader = multiFormatReader;
        this.mLimitScanArea = z;
        this.mScanAreaX = f;
        this.mScanAreaY = f2;
        this.mScanAreaWidth = f3;
        this.mScanAreaHeight = f4;
        this.mCameraViewWidth = i3;
        this.mCameraViewHeight = i4;
        this.mRatio = f5;
    }

    private BinaryBitmap generateBitmapFromImageData(byte[] bArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        PlanarYUVLuminanceSource planarYUVLuminanceSource;
        if (this.mLimitScanArea) {
            planarYUVLuminanceSource = new PlanarYUVLuminanceSource(bArr, i, i2, i3, i4, i5, i6, false);
        } else {
            planarYUVLuminanceSource = new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, false);
        }
        if (z) {
            return new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource.invert()));
        }
        return new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource));
    }

    private byte[] rotateImage(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... voidArr) {
        if (!isCancelled() && this.mDelegate != null) {
            int i = (int) (this.mCameraViewHeight / this.mRatio);
            int i2 = this.mCameraViewWidth;
            float f = this.mScanAreaY * i2;
            float f2 = i;
            float f3 = this.mScanAreaX;
            int i3 = this.mWidth;
            int i4 = (int) (f3 * i3);
            int i5 = this.mHeight;
            int i6 = (int) (((f + ((i - i2) / 2)) / f2) * i5);
            int i7 = (int) (this.mScanAreaWidth * i3);
            int i8 = (int) (((this.mScanAreaHeight * i2) / f2) * i5);
            try {
                try {
                    try {
                        try {
                            try {
                                return this.mMultiFormatReader.decodeWithState(generateBitmapFromImageData(this.mImageData, i3, i5, false, i4, i6, i7, i8));
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        } catch (NotFoundException unused) {
                        }
                    } catch (NotFoundException unused2) {
                        return this.mMultiFormatReader.decodeWithState(generateBitmapFromImageData(this.mImageData, this.mWidth, this.mHeight, true, (this.mWidth - i7) - i4, (this.mHeight - i8) - i6, i7, i8));
                    }
                } catch (NotFoundException unused3) {
                    return this.mMultiFormatReader.decodeWithState(generateBitmapFromImageData(rotateImage(this.mImageData, this.mWidth, this.mHeight), this.mHeight, this.mWidth, true, i6, (this.mWidth - i7) - i4, i8, i7));
                }
            } catch (NotFoundException unused4) {
                return this.mMultiFormatReader.decodeWithState(generateBitmapFromImageData(rotateImage(this.mImageData, this.mWidth, this.mHeight), this.mHeight, this.mWidth, false, (this.mHeight - i8) - i6, i4, i8, i7));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Result result) {
        super.onPostExecute((BarCodeScannerAsyncTask) result);
        if (result != null) {
            this.mDelegate.onBarCodeRead(result, this.mWidth, this.mHeight, this.mImageData);
        }
        this.mDelegate.onBarCodeScanningTaskCompleted();
    }
}
