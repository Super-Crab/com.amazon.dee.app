package com.google.android.cameraview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.CamcorderProfile;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.cameraview.CameraViewImpl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
/* loaded from: classes2.dex */
public class CameraView extends FrameLayout {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int FACING_BACK = 0;
    public static final int FACING_FRONT = 1;
    public static final int FLASH_AUTO = 3;
    public static final int FLASH_OFF = 0;
    public static final int FLASH_ON = 1;
    public static final int FLASH_RED_EYE = 4;
    public static final int FLASH_TORCH = 2;
    private boolean mAdjustViewBounds;
    protected Handler mBgHandler;
    protected HandlerThread mBgThread;
    private final CallbackBridge mCallbacks;
    private Context mContext;
    private final DisplayOrientationDetector mDisplayOrientationDetector;
    CameraViewImpl mImpl;

    /* loaded from: classes2.dex */
    public static abstract class Callback {
        public void onCameraClosed(CameraView cameraView) {
        }

        public void onCameraOpened(CameraView cameraView) {
        }

        public void onFramePreview(CameraView cameraView, byte[] bArr, int i, int i2, int i3) {
        }

        public void onMountError(CameraView cameraView) {
        }

        public void onPictureTaken(CameraView cameraView, byte[] bArr, int i) {
        }

        public void onRecordingEnd(CameraView cameraView) {
        }

        public void onRecordingStart(CameraView cameraView, String str, int i, int i2) {
        }

        public void onVideoRecorded(CameraView cameraView, String str, int i, int i2) {
        }
    }

    /* loaded from: classes2.dex */
    private class CallbackBridge implements CameraViewImpl.Callback {
        private final ArrayList<Callback> mCallbacks = new ArrayList<>();
        private boolean mRequestLayoutOnOpen;

        CallbackBridge() {
        }

        public void add(Callback callback) {
            this.mCallbacks.add(callback);
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onCameraClosed() {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onCameraClosed(CameraView.this);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onCameraOpened() {
            if (this.mRequestLayoutOnOpen) {
                this.mRequestLayoutOnOpen = false;
                CameraView.this.requestLayout();
            }
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onCameraOpened(CameraView.this);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onFramePreview(byte[] bArr, int i, int i2, int i3) {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onFramePreview(CameraView.this, bArr, i, i2, i3);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onMountError() {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onMountError(CameraView.this);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onPictureTaken(byte[] bArr, int i) {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onPictureTaken(CameraView.this, bArr, i);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onRecordingEnd() {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onRecordingEnd(CameraView.this);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onRecordingStart(String str, int i, int i2) {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onRecordingStart(CameraView.this, str, i, i2);
            }
        }

        @Override // com.google.android.cameraview.CameraViewImpl.Callback
        public void onVideoRecorded(String str, int i, int i2) {
            Iterator<Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onVideoRecorded(CameraView.this, str, i, i2);
            }
        }

        public void remove(Callback callback) {
            this.mCallbacks.remove(callback);
        }

        public void reserveRequestLayoutOnOpen() {
            this.mRequestLayoutOnOpen = true;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Facing {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flash {
    }

    public CameraView(Context context, boolean z) {
        this(context, null, z);
    }

    @NonNull
    private PreviewImpl createPreviewImpl(Context context) {
        int i = Build.VERSION.SDK_INT;
        return new TextureViewPreview(context, this);
    }

    public void addCallback(@NonNull Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void cleanup() {
        HandlerThread handlerThread = this.mBgThread;
        if (handlerThread != null) {
            int i = Build.VERSION.SDK_INT;
            handlerThread.quitSafely();
            this.mBgThread = null;
        }
    }

    public boolean getAdjustViewBounds() {
        return this.mAdjustViewBounds;
    }

    @Nullable
    public AspectRatio getAspectRatio() {
        return this.mImpl.getAspectRatio();
    }

    public boolean getAutoFocus() {
        return this.mImpl.getAutoFocus();
    }

    public SortedSet<Size> getAvailablePictureSizes(@NonNull AspectRatio aspectRatio) {
        return this.mImpl.getAvailablePictureSizes(aspectRatio);
    }

    public String getCameraId() {
        return this.mImpl.getCameraId();
    }

    public List<Properties> getCameraIds() {
        return this.mImpl.getCameraIds();
    }

    public int getCameraOrientation() {
        return this.mImpl.getCameraOrientation();
    }

    public float getExposureCompensation() {
        return this.mImpl.getExposureCompensation();
    }

    public int getFacing() {
        return this.mImpl.getFacing();
    }

    public int getFlash() {
        return this.mImpl.getFlash();
    }

    public float getFocusDepth() {
        return this.mImpl.getFocusDepth();
    }

    public Size getPictureSize() {
        return this.mImpl.getPictureSize();
    }

    public boolean getPlaySoundOnCapture() {
        return this.mImpl.getPlaySoundOnCapture();
    }

    public boolean getPlaySoundOnRecord() {
        return this.mImpl.getPlaySoundOnRecord();
    }

    public Size getPreviewSize() {
        return this.mImpl.getPreviewSize();
    }

    public boolean getScanning() {
        return this.mImpl.getScanning();
    }

    public Set<AspectRatio> getSupportedAspectRatios() {
        return this.mImpl.getSupportedAspectRatios();
    }

    public ArrayList<int[]> getSupportedPreviewFpsRange() {
        return this.mImpl.getSupportedPreviewFpsRange();
    }

    public View getView() {
        CameraViewImpl cameraViewImpl = this.mImpl;
        if (cameraViewImpl != null) {
            return cameraViewImpl.getView();
        }
        return null;
    }

    public int getWhiteBalance() {
        return this.mImpl.getWhiteBalance();
    }

    public float getZoom() {
        return this.mImpl.getZoom();
    }

    public boolean isCameraOpened() {
        return this.mImpl.isCameraOpened();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.mDisplayOrientationDetector.enable(ViewCompat.getDisplay(this));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.mDisplayOrientationDetector.disable();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (isInEditMode()) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.mAdjustViewBounds) {
            if (!isCameraOpened()) {
                this.mCallbacks.reserveRequestLayoutOnOpen();
                super.onMeasure(i, i2);
                return;
            }
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode == 1073741824 && mode2 != 1073741824) {
                int i3 = (int) (getAspectRatio().toFloat() * View.MeasureSpec.getSize(i));
                if (mode2 == Integer.MIN_VALUE) {
                    i3 = Math.min(i3, View.MeasureSpec.getSize(i2));
                }
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            } else if (mode != 1073741824 && mode2 == 1073741824) {
                int i4 = (int) (getAspectRatio().toFloat() * View.MeasureSpec.getSize(i2));
                if (mode == Integer.MIN_VALUE) {
                    i4 = Math.min(i4, View.MeasureSpec.getSize(i));
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), i2);
            } else {
                super.onMeasure(i, i2);
            }
        } else {
            super.onMeasure(i, i2);
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        AspectRatio aspectRatio = getAspectRatio();
        if (this.mDisplayOrientationDetector.getLastKnownDisplayOrientation() % 180 == 0) {
            aspectRatio = aspectRatio.inverse();
        }
        if (measuredHeight < (aspectRatio.getY() * measuredWidth) / aspectRatio.getX()) {
            this.mImpl.getView().measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec((aspectRatio.getY() * measuredWidth) / aspectRatio.getX(), 1073741824));
        } else {
            this.mImpl.getView().measure(View.MeasureSpec.makeMeasureSpec((aspectRatio.getX() * measuredHeight) / aspectRatio.getY(), 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setFacing(savedState.facing);
        setCameraId(savedState.cameraId);
        setAspectRatio(savedState.ratio);
        setAutoFocus(savedState.autoFocus);
        setFlash(savedState.flash);
        setExposureCompensation(savedState.exposure);
        setFocusDepth(savedState.focusDepth);
        setZoom(savedState.zoom);
        setWhiteBalance(savedState.whiteBalance);
        setPlaySoundOnCapture(savedState.playSoundOnCapture);
        setPlaySoundOnRecord(savedState.playSoundOnRecord);
        setScanning(savedState.scanning);
        setPictureSize(savedState.pictureSize);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.facing = getFacing();
        savedState.cameraId = getCameraId();
        savedState.ratio = getAspectRatio();
        savedState.autoFocus = getAutoFocus();
        savedState.flash = getFlash();
        savedState.exposure = getExposureCompensation();
        savedState.focusDepth = getFocusDepth();
        savedState.zoom = getZoom();
        savedState.whiteBalance = getWhiteBalance();
        savedState.playSoundOnCapture = getPlaySoundOnCapture();
        savedState.playSoundOnRecord = getPlaySoundOnRecord();
        savedState.scanning = getScanning();
        savedState.pictureSize = getPictureSize();
        return savedState;
    }

    public void pausePreview() {
        this.mImpl.pausePreview();
    }

    public void pauseRecording() {
        this.mImpl.pauseRecording();
    }

    public boolean record(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile, int i3, int i4) {
        return this.mImpl.record(str, i, i2, z, camcorderProfile, i3, i4);
    }

    public void removeCallback(@NonNull Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void resumePreview() {
        this.mImpl.resumePreview();
    }

    public void resumeRecording() {
        this.mImpl.resumeRecording();
    }

    public void setAdjustViewBounds(boolean z) {
        if (this.mAdjustViewBounds != z) {
            this.mAdjustViewBounds = z;
            requestLayout();
        }
    }

    public void setAspectRatio(@NonNull AspectRatio aspectRatio) {
        if (this.mImpl.setAspectRatio(aspectRatio)) {
            requestLayout();
        }
    }

    public void setAutoFocus(boolean z) {
        this.mImpl.setAutoFocus(z);
    }

    public void setAutoFocusPointOfInterest(float f, float f2) {
        this.mImpl.setFocusArea(f, f2);
    }

    public void setCameraId(String str) {
        this.mImpl.setCameraId(str);
    }

    public void setExposureCompensation(float f) {
        this.mImpl.setExposureCompensation(f);
    }

    public void setFacing(int i) {
        this.mImpl.setFacing(i);
    }

    public void setFlash(int i) {
        this.mImpl.setFlash(i);
    }

    public void setFocusDepth(float f) {
        this.mImpl.setFocusDepth(f);
    }

    public void setPictureSize(@NonNull Size size) {
        this.mImpl.setPictureSize(size);
    }

    public void setPlaySoundOnCapture(boolean z) {
        this.mImpl.setPlaySoundOnCapture(z);
    }

    public void setPlaySoundOnRecord(boolean z) {
        this.mImpl.setPlaySoundOnRecord(z);
    }

    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        this.mImpl.setPreviewTexture(surfaceTexture);
    }

    public void setScanning(boolean z) {
        this.mImpl.setScanning(z);
    }

    public void setUsingCamera2Api(boolean z) {
        int i = Build.VERSION.SDK_INT;
        boolean isCameraOpened = isCameraOpened();
        Parcelable onSaveInstanceState = onSaveInstanceState();
        if (z && !Camera2.isLegacy(this.mContext)) {
            if (isCameraOpened) {
                stop();
            }
            int i2 = Build.VERSION.SDK_INT;
            this.mImpl = new Camera2Api23(this.mCallbacks, this.mImpl.mPreview, this.mContext, this.mBgHandler);
            onRestoreInstanceState(onSaveInstanceState);
        } else if (this.mImpl instanceof Camera1) {
            return;
        } else {
            if (isCameraOpened) {
                stop();
            }
            this.mImpl = new Camera1(this.mCallbacks, this.mImpl.mPreview, this.mBgHandler);
        }
        if (isCameraOpened) {
            start();
        }
    }

    public void setWhiteBalance(int i) {
        this.mImpl.setWhiteBalance(i);
    }

    public void setZoom(float f) {
        this.mImpl.setZoom(f);
    }

    public void start() {
        this.mImpl.start();
    }

    public void stop() {
        this.mImpl.stop();
    }

    public void stopRecording() {
        this.mImpl.stopRecording();
    }

    public void takePicture(ReadableMap readableMap) {
        this.mImpl.takePicture(readableMap);
    }

    public CameraView(Context context, AttributeSet attributeSet, boolean z) {
        this(context, attributeSet, 0, z);
    }

    public CameraView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i);
        this.mBgThread = new HandlerThread("RNCamera-Handler-Thread");
        this.mBgThread.start();
        this.mBgHandler = new Handler(this.mBgThread.getLooper());
        if (isInEditMode()) {
            this.mCallbacks = null;
            this.mDisplayOrientationDetector = null;
            return;
        }
        this.mAdjustViewBounds = true;
        this.mContext = context;
        PreviewImpl createPreviewImpl = createPreviewImpl(context);
        this.mCallbacks = new CallbackBridge();
        if (!z) {
            int i2 = Build.VERSION.SDK_INT;
            if (!Camera2.isLegacy(context)) {
                int i3 = Build.VERSION.SDK_INT;
                this.mImpl = new Camera2Api23(this.mCallbacks, createPreviewImpl, context, this.mBgHandler);
                this.mDisplayOrientationDetector = new DisplayOrientationDetector(context) { // from class: com.google.android.cameraview.CameraView.1
                    @Override // com.google.android.cameraview.DisplayOrientationDetector
                    public void onDisplayOrientationChanged(int i4, int i5) {
                        CameraView.this.mImpl.setDisplayOrientation(i4);
                        CameraView.this.mImpl.setDeviceOrientation(i5);
                    }
                };
            }
        }
        this.mImpl = new Camera1(this.mCallbacks, createPreviewImpl, this.mBgHandler);
        this.mDisplayOrientationDetector = new DisplayOrientationDetector(context) { // from class: com.google.android.cameraview.CameraView.1
            @Override // com.google.android.cameraview.DisplayOrientationDetector
            public void onDisplayOrientationChanged(int i4, int i5) {
                CameraView.this.mImpl.setDisplayOrientation(i4);
                CameraView.this.mImpl.setDeviceOrientation(i5);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() { // from class: com.google.android.cameraview.CameraView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            /* renamed from: createFromParcel */
            public SavedState mo7259createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            /* renamed from: newArray */
            public SavedState[] mo7260newArray(int i) {
                return new SavedState[i];
            }
        });
        boolean autoFocus;
        String cameraId;
        float exposure;
        int facing;
        int flash;
        float focusDepth;
        Size pictureSize;
        boolean playSoundOnCapture;
        boolean playSoundOnRecord;
        AspectRatio ratio;
        boolean scanning;
        int whiteBalance;
        float zoom;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            this.facing = parcel.readInt();
            this.cameraId = parcel.readString();
            this.ratio = (AspectRatio) parcel.readParcelable(classLoader);
            boolean z = true;
            this.autoFocus = parcel.readByte() != 0;
            this.flash = parcel.readInt();
            this.exposure = parcel.readFloat();
            this.focusDepth = parcel.readFloat();
            this.zoom = parcel.readFloat();
            this.whiteBalance = parcel.readInt();
            this.playSoundOnCapture = parcel.readByte() != 0;
            this.playSoundOnRecord = parcel.readByte() != 0;
            this.scanning = parcel.readByte() == 0 ? false : z;
            this.pictureSize = (Size) parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.facing);
            parcel.writeString(this.cameraId);
            parcel.writeParcelable(this.ratio, 0);
            parcel.writeByte(this.autoFocus ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.flash);
            parcel.writeFloat(this.exposure);
            parcel.writeFloat(this.focusDepth);
            parcel.writeFloat(this.zoom);
            parcel.writeInt(this.whiteBalance);
            parcel.writeByte(this.playSoundOnCapture ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.playSoundOnRecord ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.scanning ? (byte) 1 : (byte) 0);
            parcel.writeParcelable(this.pictureSize, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
