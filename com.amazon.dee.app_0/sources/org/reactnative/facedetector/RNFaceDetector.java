package org.reactnative.facedetector;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.frame.RNFrame;
/* loaded from: classes5.dex */
public class RNFaceDetector {
    public static int ACCURATE_MODE = 1;
    public static int ALL_CLASSIFICATIONS = 1;
    public static int ALL_LANDMARKS = 1;
    public static int FAST_MODE;
    public static int NO_CLASSIFICATIONS;
    public static int NO_LANDMARKS;
    private FaceDetector.Builder mBuilder;
    private ImageDimensions mPreviousDimensions;
    private FaceDetector mFaceDetector = null;
    private int mClassificationType = NO_CLASSIFICATIONS;
    private int mLandmarkType = NO_LANDMARKS;
    private float mMinFaceSize = 0.15f;
    private int mMode = FAST_MODE;

    public RNFaceDetector(Context context) {
        this.mBuilder = null;
        this.mBuilder = new FaceDetector.Builder(context);
        this.mBuilder.setMinFaceSize(this.mMinFaceSize);
        this.mBuilder.setMode(this.mMode);
        this.mBuilder.setLandmarkType(this.mLandmarkType);
        this.mBuilder.setClassificationType(this.mClassificationType);
    }

    private void createFaceDetector() {
        this.mFaceDetector = this.mBuilder.build();
    }

    private void releaseFaceDetector() {
        FaceDetector faceDetector = this.mFaceDetector;
        if (faceDetector != null) {
            faceDetector.release();
            this.mFaceDetector = null;
        }
    }

    public SparseArray<Face> detect(RNFrame rNFrame) {
        if (!rNFrame.getDimensions().equals(this.mPreviousDimensions)) {
            releaseFaceDetector();
        }
        if (this.mFaceDetector == null) {
            createFaceDetector();
            this.mPreviousDimensions = rNFrame.getDimensions();
        }
        return this.mFaceDetector.detect(rNFrame.getFrame());
    }

    public boolean isOperational() {
        if (this.mFaceDetector == null) {
            createFaceDetector();
        }
        return this.mFaceDetector.isOperational();
    }

    public void release() {
        releaseFaceDetector();
        this.mPreviousDimensions = null;
    }

    public void setClassificationType(int i) {
        if (i != this.mClassificationType) {
            release();
            this.mBuilder.setClassificationType(i);
            this.mClassificationType = i;
        }
    }

    public void setLandmarkType(int i) {
        if (i != this.mLandmarkType) {
            release();
            this.mBuilder.setLandmarkType(i);
            this.mLandmarkType = i;
        }
    }

    public void setMode(int i) {
        if (i != this.mMode) {
            release();
            this.mBuilder.setMode(i);
            this.mMode = i;
        }
    }

    public void setTracking(boolean z) {
        release();
        this.mBuilder.setTrackingEnabled(z);
    }
}
