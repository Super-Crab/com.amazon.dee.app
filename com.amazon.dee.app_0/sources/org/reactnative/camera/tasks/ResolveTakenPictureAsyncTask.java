package org.reactnative.camera.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.sharing.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import java.io.File;
import java.io.IOException;
import org.reactnative.camera.utils.RNFileUtils;
/* loaded from: classes5.dex */
public class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, WritableMap> {
    private static final String ERROR_TAG = "E_TAKING_PICTURE_FAILED";
    private Bitmap mBitmap;
    private File mCacheDirectory;
    private int mDeviceOrientation;
    private byte[] mImageData;
    private ReadableMap mOptions;
    private PictureSavedDelegate mPictureSavedDelegate;
    private Promise mPromise;

    /* renamed from: org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ResolveTakenPictureAsyncTask(byte[] bArr, Promise promise, ReadableMap readableMap, File file, int i, PictureSavedDelegate pictureSavedDelegate) {
        this.mPromise = promise;
        this.mOptions = readableMap;
        this.mImageData = bArr;
        this.mCacheDirectory = file;
        this.mDeviceOrientation = i;
        this.mPictureSavedDelegate = pictureSavedDelegate;
    }

    private Bitmap flipHorizontally(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private String getImagePath() throws IOException {
        if (this.mOptions.hasKey(RouteParameter.PATH)) {
            return this.mOptions.getString(RouteParameter.PATH);
        }
        return RNFileUtils.getOutputFilePath(this.mCacheDirectory, Constants.DEFAULT_IMAGE_EXTENSION);
    }

    private int getImageRotation(int i) {
        if (i != 3) {
            if (i == 6) {
                return 90;
            }
            return i != 8 ? 0 : 270;
        }
        return 180;
    }

    private int getQuality() {
        return (int) (this.mOptions.getDouble("quality") * 100.0d);
    }

    private void loadBitmap() throws IOException {
        if (this.mBitmap == null) {
            byte[] bArr = this.mImageData;
            this.mBitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        if (this.mBitmap != null) {
            return;
        }
        throw new IOException("Failed to decode Image Bitmap");
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, i, (int) (bitmap.getHeight() * (i / bitmap.getWidth())), true);
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x002b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0031 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String writeStreamToFile(java.io.ByteArrayOutputStream r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            java.lang.String r1 = r3.getImagePath()     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L1d
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L1b
            r2.<init>(r1)     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L1b
            r4.writeTo(r2)     // Catch: java.io.IOException -> L16 java.lang.Throwable -> L2d
            r2.close()     // Catch: java.io.IOException -> L11
            goto L29
        L11:
            r4 = move-exception
            r4.printStackTrace()
            goto L29
        L16:
            r4 = move-exception
            goto L20
        L18:
            r4 = move-exception
            r2 = r0
            goto L20
        L1b:
            r4 = move-exception
            goto L2f
        L1d:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L20:
            r0 = r4
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r2 == 0) goto L29
            r2.close()     // Catch: java.io.IOException -> L11
        L29:
            if (r0 != 0) goto L2c
            return r1
        L2c:
            throw r0
        L2d:
            r4 = move-exception
            r0 = r2
        L2f:
            if (r0 == 0) goto L39
            r0.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r0 = move-exception
            r0.printStackTrace()
        L39:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.writeStreamToFile(java.io.ByteArrayOutputStream):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(29:9|10|11|(1:106)(2:15|(1:17)(1:105))|18|(1:20)|21|(1:25)|26|(1:104)(1:30)|31|(2:33|(2:35|(1:37)(9:38|39|(7:83|(4:88|89|(2:91|(1:93))|(1:95))|98|(1:100)|89|(0)|(0))(1:42)|43|44|(4:46|(3:62|(1:(1:68))(1:65)|66)|50|(1:54))(4:69|(3:78|(1:81)|82)|73|(1:77))|55|56|57))(17:101|102|39|(0)|83|(10:88|89|(0)|(0)|43|44|(0)(0)|55|56|57)|98|(0)|89|(0)|(0)|43|44|(0)(0)|55|56|57))|103|102|39|(0)|83|(0)|98|(0)|89|(0)|(0)|43|44|(0)(0)|55|56|57) */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0237, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0238, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f3, code lost:
        r2 = new androidx.exifinterface.media.ExifInterface(r9);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ea A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00fe A[Catch: all -> 0x023d, IOException -> 0x0240, NotFoundException -> 0x0243, TryCatch #7 {NotFoundException -> 0x0243, IOException -> 0x0240, all -> 0x023d, blocks: (B:8:0x0038, B:11:0x0044, B:13:0x004c, B:15:0x0057, B:18:0x006c, B:20:0x0074, B:21:0x0085, B:23:0x008d, B:25:0x0095, B:26:0x00a0, B:28:0x00a8, B:32:0x00b3, B:34:0x00bb, B:39:0x00cb, B:67:0x0128, B:70:0x0132, B:72:0x0153, B:81:0x01a7, B:83:0x01af, B:85:0x01b7, B:74:0x015b, B:77:0x017b, B:80:0x019c, B:79:0x018d, B:86:0x01c2, B:88:0x01ec, B:95:0x0217, B:97:0x021f, B:99:0x0227, B:90:0x01f4, B:93:0x01fc, B:94:0x0207, B:49:0x00e6, B:60:0x0101, B:62:0x0105, B:64:0x0119, B:66:0x011e, B:56:0x00f3, B:57:0x00f8, B:59:0x00fe, B:40:0x00d4), top: B:147:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0105 A[Catch: all -> 0x023d, IOException -> 0x0240, NotFoundException -> 0x0243, TryCatch #7 {NotFoundException -> 0x0243, IOException -> 0x0240, all -> 0x023d, blocks: (B:8:0x0038, B:11:0x0044, B:13:0x004c, B:15:0x0057, B:18:0x006c, B:20:0x0074, B:21:0x0085, B:23:0x008d, B:25:0x0095, B:26:0x00a0, B:28:0x00a8, B:32:0x00b3, B:34:0x00bb, B:39:0x00cb, B:67:0x0128, B:70:0x0132, B:72:0x0153, B:81:0x01a7, B:83:0x01af, B:85:0x01b7, B:74:0x015b, B:77:0x017b, B:80:0x019c, B:79:0x018d, B:86:0x01c2, B:88:0x01ec, B:95:0x0217, B:97:0x021f, B:99:0x0227, B:90:0x01f4, B:93:0x01fc, B:94:0x0207, B:49:0x00e6, B:60:0x0101, B:62:0x0105, B:64:0x0119, B:66:0x011e, B:56:0x00f3, B:57:0x00f8, B:59:0x00fe, B:40:0x00d4), top: B:147:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011e A[Catch: all -> 0x023d, IOException -> 0x0240, NotFoundException -> 0x0243, TryCatch #7 {NotFoundException -> 0x0243, IOException -> 0x0240, all -> 0x023d, blocks: (B:8:0x0038, B:11:0x0044, B:13:0x004c, B:15:0x0057, B:18:0x006c, B:20:0x0074, B:21:0x0085, B:23:0x008d, B:25:0x0095, B:26:0x00a0, B:28:0x00a8, B:32:0x00b3, B:34:0x00bb, B:39:0x00cb, B:67:0x0128, B:70:0x0132, B:72:0x0153, B:81:0x01a7, B:83:0x01af, B:85:0x01b7, B:74:0x015b, B:77:0x017b, B:80:0x019c, B:79:0x018d, B:86:0x01c2, B:88:0x01ec, B:95:0x0217, B:97:0x021f, B:99:0x0227, B:90:0x01f4, B:93:0x01fc, B:94:0x0207, B:49:0x00e6, B:60:0x0101, B:62:0x0105, B:64:0x0119, B:66:0x011e, B:56:0x00f3, B:57:0x00f8, B:59:0x00fe, B:40:0x00d4), top: B:147:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0132 A[Catch: all -> 0x023d, IOException -> 0x0240, NotFoundException -> 0x0243, TRY_ENTER, TryCatch #7 {NotFoundException -> 0x0243, IOException -> 0x0240, all -> 0x023d, blocks: (B:8:0x0038, B:11:0x0044, B:13:0x004c, B:15:0x0057, B:18:0x006c, B:20:0x0074, B:21:0x0085, B:23:0x008d, B:25:0x0095, B:26:0x00a0, B:28:0x00a8, B:32:0x00b3, B:34:0x00bb, B:39:0x00cb, B:67:0x0128, B:70:0x0132, B:72:0x0153, B:81:0x01a7, B:83:0x01af, B:85:0x01b7, B:74:0x015b, B:77:0x017b, B:80:0x019c, B:79:0x018d, B:86:0x01c2, B:88:0x01ec, B:95:0x0217, B:97:0x021f, B:99:0x0227, B:90:0x01f4, B:93:0x01fc, B:94:0x0207, B:49:0x00e6, B:60:0x0101, B:62:0x0105, B:64:0x0119, B:66:0x011e, B:56:0x00f3, B:57:0x00f8, B:59:0x00fe, B:40:0x00d4), top: B:147:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c2 A[Catch: all -> 0x023d, IOException -> 0x0240, NotFoundException -> 0x0243, TryCatch #7 {NotFoundException -> 0x0243, IOException -> 0x0240, all -> 0x023d, blocks: (B:8:0x0038, B:11:0x0044, B:13:0x004c, B:15:0x0057, B:18:0x006c, B:20:0x0074, B:21:0x0085, B:23:0x008d, B:25:0x0095, B:26:0x00a0, B:28:0x00a8, B:32:0x00b3, B:34:0x00bb, B:39:0x00cb, B:67:0x0128, B:70:0x0132, B:72:0x0153, B:81:0x01a7, B:83:0x01af, B:85:0x01b7, B:74:0x015b, B:77:0x017b, B:80:0x019c, B:79:0x018d, B:86:0x01c2, B:88:0x01ec, B:95:0x0217, B:97:0x021f, B:99:0x0227, B:90:0x01f4, B:93:0x01fc, B:94:0x0207, B:49:0x00e6, B:60:0x0101, B:62:0x0105, B:64:0x0119, B:66:0x011e, B:56:0x00f3, B:57:0x00f8, B:59:0x00fe, B:40:0x00d4), top: B:147:0x0038 }] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.facebook.react.bridge.WritableMap] */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v4, types: [int] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v9 */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.react.bridge.WritableMap doInBackground(java.lang.Void... r17) {
        /*
            Method dump skipped, instructions count: 644
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.doInBackground(java.lang.Void[]):com.facebook.react.bridge.WritableMap");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(WritableMap writableMap) {
        super.onPostExecute((ResolveTakenPictureAsyncTask) writableMap);
        if (writableMap != null) {
            if (this.mOptions.hasKey("fastMode") && this.mOptions.getBoolean("fastMode")) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", this.mOptions.getInt("id"));
                createMap.putMap("data", writableMap);
                this.mPictureSavedDelegate.onPictureSaved(createMap);
                return;
            }
            this.mPromise.resolve(writableMap);
        }
    }
}
