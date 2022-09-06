package org.reactnative.facedetector.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.vision.face.Face;
import java.io.File;
import java.io.IOException;
import org.reactnative.facedetector.FaceDetectorUtils;
import org.reactnative.facedetector.RNFaceDetector;
import org.reactnative.frame.RNFrameFactory;
/* loaded from: classes5.dex */
public class FileFaceDetectionAsyncTask extends AsyncTask<Void, Void, SparseArray<Face>> {
    private static final String DETECT_LANDMARKS_OPTION_KEY = "detectLandmarks";
    private static final String ERROR_TAG = "E_FACE_DETECTION_FAILED";
    private static final String MODE_OPTION_KEY = "mode";
    private static final String RUN_CLASSIFICATIONS_OPTION_KEY = "runClassifications";
    private Context mContext;
    private ReadableMap mOptions;
    private String mPath;
    private Promise mPromise;
    private RNFaceDetector mRNFaceDetector;
    private String mUri;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mOrientation = 0;

    public FileFaceDetectionAsyncTask(Context context, ReadableMap readableMap, Promise promise) {
        this.mUri = readableMap.getString("uri");
        this.mPromise = promise;
        this.mOptions = readableMap;
        this.mContext = context;
    }

    private static RNFaceDetector detectorForOptions(ReadableMap readableMap, Context context) {
        RNFaceDetector rNFaceDetector = new RNFaceDetector(context);
        rNFaceDetector.setTracking(false);
        if (readableMap.hasKey(MODE_OPTION_KEY)) {
            rNFaceDetector.setMode(readableMap.getInt(MODE_OPTION_KEY));
        }
        if (readableMap.hasKey(RUN_CLASSIFICATIONS_OPTION_KEY)) {
            rNFaceDetector.setClassificationType(readableMap.getInt(RUN_CLASSIFICATIONS_OPTION_KEY));
        }
        if (readableMap.hasKey(DETECT_LANDMARKS_OPTION_KEY)) {
            rNFaceDetector.setLandmarkType(readableMap.getInt(DETECT_LANDMARKS_OPTION_KEY));
        }
        return rNFaceDetector;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        String str = this.mUri;
        if (str == null) {
            this.mPromise.reject(ERROR_TAG, "You have to provide an URI of an image.");
            cancel(true);
            return;
        }
        this.mPath = Uri.parse(str).getPath();
        String str2 = this.mPath;
        if (str2 == null) {
            Promise promise = this.mPromise;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid URI provided: `");
            outline107.append(this.mUri);
            outline107.append("`.");
            promise.reject(ERROR_TAG, outline107.toString());
            cancel(true);
            return;
        }
        if (!(str2.startsWith(this.mContext.getCacheDir().getPath()) || this.mPath.startsWith(this.mContext.getFilesDir().getPath()))) {
            this.mPromise.reject(ERROR_TAG, "The image has to be in the local app's directories.");
            cancel(true);
        } else if (new File(this.mPath).exists()) {
        } else {
            Promise promise2 = this.mPromise;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("The file does not exist. Given path: `");
            outline1072.append(this.mPath);
            outline1072.append("`.");
            promise2.reject(ERROR_TAG, outline1072.toString());
            cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public SparseArray<Face> doInBackground(Void... voidArr) {
        if (isCancelled()) {
            return null;
        }
        this.mRNFaceDetector = detectorForOptions(this.mOptions, this.mContext);
        Bitmap decodeFile = BitmapFactory.decodeFile(this.mPath);
        this.mWidth = decodeFile.getWidth();
        this.mHeight = decodeFile.getHeight();
        try {
            this.mOrientation = new ExifInterface(this.mPath).getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reading orientation from file `");
            outline107.append(this.mPath);
            outline107.append("` failed.");
            Log.e(ERROR_TAG, outline107.toString(), e);
        }
        return this.mRNFaceDetector.detect(RNFrameFactory.buildFrame(decodeFile));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(SparseArray<Face> sparseArray) {
        super.onPostExecute((FileFaceDetectionAsyncTask) sparseArray);
        WritableMap createMap = Arguments.createMap();
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < sparseArray.size(); i++) {
            WritableMap serializeFace = FaceDetectorUtils.serializeFace(sparseArray.valueAt(i));
            serializeFace.putDouble("yawAngle", ((-serializeFace.getDouble("yawAngle")) + 360.0d) % 360.0d);
            serializeFace.putDouble("rollAngle", ((-serializeFace.getDouble("rollAngle")) + 360.0d) % 360.0d);
            createArray.pushMap(serializeFace);
        }
        createMap.putArray("faces", createArray);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("width", this.mWidth);
        createMap2.putInt("height", this.mHeight);
        createMap2.putInt("orientation", this.mOrientation);
        createMap2.putString("uri", this.mUri);
        createMap.putMap("image", createMap2);
        this.mRNFaceDetector.release();
        this.mPromise.resolve(createMap);
    }
}
