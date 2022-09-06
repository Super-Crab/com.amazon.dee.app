package com.amazon.alexa.smarthomecameras.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
/* loaded from: classes10.dex */
public class ZoomableImageView extends ImageView {
    private static final float SUPER_MAX_MULTIPLIER = 1.25f;
    private static final float SUPER_MIN_MULTIPLIER = 0.75f;
    private static final String TAG = ZoomableImageView.class.getSimpleName();
    private Context context;
    private ZoomVariables delayedZoomVariables;
    private GestureDetector.OnDoubleTapListener doubleTapListener;
    private Fling fling;
    private GestureDetector gestureDetector;
    private boolean imageRenderedAtLeastOnce;
    private float matchViewHeight;
    private float matchViewWidth;
    private Matrix matrix;
    private float[] matrixArray;
    private float maxScale;
    private float minScale;
    private float normalizedScale;
    private boolean onDrawReady;
    private float prevMatchViewHeight;
    private float prevMatchViewWidth;
    private Matrix prevMatrix;
    private int prevViewHeight;
    private int prevViewWidth;
    private ScaleGestureDetector scaleDetector;
    private ImageView.ScaleType scaleType;
    private State state;
    private float superMaxScale;
    private float superMinScale;
    private OnTouchImageViewListener touchImageViewListener;
    private View.OnTouchListener userTouchListener;
    private int viewHeight;
    private int viewWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.smarthomecameras.view.ZoomableImageView$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(9)
    /* loaded from: classes10.dex */
    public class CompatScroller {
        boolean isPreGingerbread;
        OverScroller overScroller;
        Scroller scroller;

        public CompatScroller(Context context) {
            int i = Build.VERSION.SDK_INT;
            this.isPreGingerbread = false;
            this.overScroller = new OverScroller(context);
        }

        public boolean computeScrollOffset() {
            if (this.isPreGingerbread) {
                return this.scroller.computeScrollOffset();
            }
            this.overScroller.computeScrollOffset();
            return this.overScroller.computeScrollOffset();
        }

        public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.isPreGingerbread) {
                this.scroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.overScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void forceFinished(boolean z) {
            if (this.isPreGingerbread) {
                this.scroller.forceFinished(z);
            } else {
                this.overScroller.forceFinished(z);
            }
        }

        public int getCurrX() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrX();
            }
            return this.overScroller.getCurrX();
        }

        public int getCurrY() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrY();
            }
            return this.overScroller.getCurrY();
        }

        public boolean isFinished() {
            if (this.isPreGingerbread) {
                return this.scroller.isFinished();
            }
            return this.overScroller.isFinished();
        }
    }

    /* loaded from: classes10.dex */
    private class DoubleTapZoom implements Runnable {
        private static final float ZOOM_TIME = 500.0f;
        private float bitmapX;
        private float bitmapY;
        private PointF endTouch;
        private AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        private long startTime;
        private PointF startTouch;
        private float startZoom;
        private boolean stretchImageToSuper;
        private float targetZoom;

        DoubleTapZoom(float f, float f2, float f3, boolean z) {
            ZoomableImageView.this.setState(State.ANIMATE_ZOOM);
            this.startTime = System.currentTimeMillis();
            this.startZoom = ZoomableImageView.this.normalizedScale;
            this.targetZoom = f;
            this.stretchImageToSuper = z;
            PointF transformCoordTouchToBitmap = ZoomableImageView.this.transformCoordTouchToBitmap(f2, f3, false);
            this.bitmapX = transformCoordTouchToBitmap.x;
            this.bitmapY = transformCoordTouchToBitmap.y;
            this.startTouch = ZoomableImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            this.endTouch = new PointF(ZoomableImageView.this.viewWidth / 2, ZoomableImageView.this.viewHeight / 2);
        }

        private double calculateDeltaScale(float f) {
            float f2 = this.startZoom;
            return GeneratedOutlineSupport1.outline0(this.targetZoom, f2, f, f2) / ZoomableImageView.this.normalizedScale;
        }

        private float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startTime)) / 500.0f));
        }

        private void translateImageToCenterTouchPosition(float f) {
            PointF pointF = this.startTouch;
            float f2 = pointF.x;
            PointF pointF2 = this.endTouch;
            float outline0 = GeneratedOutlineSupport1.outline0(pointF2.x, f2, f, f2);
            float f3 = pointF.y;
            float outline02 = GeneratedOutlineSupport1.outline0(pointF2.y, f3, f, f3);
            PointF transformCoordBitmapToTouch = ZoomableImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            ZoomableImageView.this.matrix.postTranslate(outline0 - transformCoordBitmapToTouch.x, outline02 - transformCoordBitmapToTouch.y);
        }

        @Override // java.lang.Runnable
        public void run() {
            float interpolate = interpolate();
            ZoomableImageView.this.scaleImage(calculateDeltaScale(interpolate), this.bitmapX, this.bitmapY, this.stretchImageToSuper);
            translateImageToCenterTouchPosition(interpolate);
            ZoomableImageView.this.fixScaleTrans();
            ZoomableImageView zoomableImageView = ZoomableImageView.this;
            zoomableImageView.setImageMatrix(zoomableImageView.matrix);
            if (ZoomableImageView.this.touchImageViewListener != null) {
                ZoomableImageView.this.touchImageViewListener.onMove();
            }
            if (interpolate < 1.0f) {
                ZoomableImageView.this.compatPostOnAnimation(this);
            } else {
                ZoomableImageView.this.setState(State.DEFAULT);
            }
        }
    }

    /* loaded from: classes10.dex */
    private class Fling implements Runnable {
        int currX;
        int currY;
        CompatScroller scroller;

        Fling(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            ZoomableImageView.this.setState(State.FLING);
            this.scroller = new CompatScroller(ZoomableImageView.this.context);
            ZoomableImageView.this.matrix.getValues(ZoomableImageView.this.matrixArray);
            int i7 = (int) ZoomableImageView.this.matrixArray[2];
            int i8 = (int) ZoomableImageView.this.matrixArray[5];
            if (ZoomableImageView.this.getImageWidth() > ZoomableImageView.this.viewWidth) {
                i3 = ZoomableImageView.this.viewWidth - ((int) ZoomableImageView.this.getImageWidth());
                i4 = 0;
            } else {
                i3 = i7;
                i4 = i3;
            }
            if (ZoomableImageView.this.getImageHeight() > ZoomableImageView.this.viewHeight) {
                i5 = ZoomableImageView.this.viewHeight - ((int) ZoomableImageView.this.getImageHeight());
                i6 = 0;
            } else {
                i5 = i8;
                i6 = i5;
            }
            this.scroller.fling(i7, i8, i, i2, i3, i4, i5, i6);
            this.currX = i7;
            this.currY = i8;
        }

        public void cancelFling() {
            if (this.scroller != null) {
                ZoomableImageView.this.setState(State.DEFAULT);
                this.scroller.forceFinished(true);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ZoomableImageView.this.touchImageViewListener != null) {
                ZoomableImageView.this.touchImageViewListener.onMove();
            }
            if (this.scroller.isFinished()) {
                this.scroller = null;
            } else if (!this.scroller.computeScrollOffset()) {
            } else {
                int currX = this.scroller.getCurrX();
                int currY = this.scroller.getCurrY();
                int i = currX - this.currX;
                int i2 = currY - this.currY;
                this.currX = currX;
                this.currY = currY;
                ZoomableImageView.this.matrix.postTranslate(i, i2);
                ZoomableImageView.this.fixTrans();
                ZoomableImageView zoomableImageView = ZoomableImageView.this;
                zoomableImageView.setImageMatrix(zoomableImageView.matrix);
                ZoomableImageView.this.compatPostOnAnimation(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = ZoomableImageView.this.doubleTapListener != null ? ZoomableImageView.this.doubleTapListener.onDoubleTap(motionEvent) : false;
            if (ZoomableImageView.this.state == State.DEFAULT) {
                ZoomableImageView.this.compatPostOnAnimation(new DoubleTapZoom(ZoomableImageView.this.normalizedScale == ZoomableImageView.this.minScale ? ZoomableImageView.this.maxScale : ZoomableImageView.this.minScale, motionEvent.getX(), motionEvent.getY(), false));
                return true;
            }
            return onDoubleTap;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (ZoomableImageView.this.doubleTapListener != null) {
                return ZoomableImageView.this.doubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ZoomableImageView.this.fling != null) {
                ZoomableImageView.this.fling.cancelFling();
            }
            ZoomableImageView zoomableImageView = ZoomableImageView.this;
            zoomableImageView.fling = new Fling((int) f, (int) f2);
            ZoomableImageView zoomableImageView2 = ZoomableImageView.this;
            zoomableImageView2.compatPostOnAnimation(zoomableImageView2.fling);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            ZoomableImageView.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (ZoomableImageView.this.doubleTapListener != null) {
                return ZoomableImageView.this.doubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return ZoomableImageView.this.performClick();
        }

        /* synthetic */ GestureListener(ZoomableImageView zoomableImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public interface OnTouchImageViewListener {
        void onMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomableImageView.this.scaleImage(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (ZoomableImageView.this.touchImageViewListener != null) {
                ZoomableImageView.this.touchImageViewListener.onMove();
                return true;
            }
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ZoomableImageView.this.setState(State.ZOOM);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            ZoomableImageView.this.setState(State.DEFAULT);
            float f = ZoomableImageView.this.normalizedScale;
            boolean z = true;
            if (ZoomableImageView.this.normalizedScale > ZoomableImageView.this.maxScale) {
                f = ZoomableImageView.this.maxScale;
            } else if (ZoomableImageView.this.normalizedScale < ZoomableImageView.this.minScale) {
                f = ZoomableImageView.this.minScale;
            } else {
                z = false;
            }
            float f2 = f;
            if (z) {
                ZoomableImageView zoomableImageView = ZoomableImageView.this;
                ZoomableImageView.this.compatPostOnAnimation(new DoubleTapZoom(f2, zoomableImageView.viewWidth / 2, ZoomableImageView.this.viewHeight / 2, true));
            }
        }

        /* synthetic */ ScaleListener(ZoomableImageView zoomableImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum State {
        DEFAULT,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class ZoomVariables {
        public float focusX;
        public float focusY;
        public float scale;
        public ImageView.ScaleType scaleType;

        public ZoomVariables(float f, float f2, float f3, ImageView.ScaleType scaleType) {
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType;
        }
    }

    public ZoomableImageView(Context context) {
        super(context);
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        prepareView(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public void compatPostOnAnimation(Runnable runnable) {
        int i = Build.VERSION.SDK_INT;
        postOnAnimation(runnable);
    }

    private void fitImageToView() {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.matrix == null || this.prevMatrix == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f = intrinsicWidth;
        float f2 = this.viewWidth / f;
        float f3 = intrinsicHeight;
        float f4 = this.viewHeight / f3;
        int i = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.scaleType.ordinal()];
        if (i == 1) {
            f2 = 1.0f;
        } else if (i != 2) {
            if (i == 3) {
                f2 = Math.min(1.0f, Math.min(f2, f4));
                f4 = f2;
            } else if (i != 4) {
                if (i != 5) {
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
                }
                int i2 = this.viewWidth;
                float f5 = i2 - (f2 * f);
                int i3 = this.viewHeight;
                float f6 = i3 - (f4 * f3);
                this.matchViewWidth = i2 - f5;
                this.matchViewHeight = i3 - f6;
                if (isZoomed() && !this.imageRenderedAtLeastOnce) {
                    this.matrix.setScale(f2, f4);
                    this.matrix.postTranslate(f5 / 2.0f, f6 / 2.0f);
                    this.normalizedScale = 1.0f;
                } else {
                    if (this.prevMatchViewWidth != 0.0f || this.prevMatchViewHeight == 0.0f) {
                        savePreviousImageValues();
                    }
                    this.prevMatrix.getValues(this.matrixArray);
                    float[] fArr = this.matrixArray;
                    float f7 = this.matchViewWidth / f;
                    float f8 = this.normalizedScale;
                    fArr[0] = f7 * f8;
                    fArr[4] = (this.matchViewHeight / f3) * f8;
                    float f9 = fArr[2];
                    float f10 = fArr[5];
                    translateMatrixAfterRotate(2, f9, this.prevMatchViewWidth * f8, getImageWidth(), this.prevViewWidth, this.viewWidth, intrinsicWidth);
                    translateMatrixAfterRotate(5, f10, this.prevMatchViewHeight * this.normalizedScale, getImageHeight(), this.prevViewHeight, this.viewHeight, intrinsicHeight);
                    this.matrix.setValues(this.matrixArray);
                }
                fixTrans();
                setImageMatrix(this.matrix);
            }
            f2 = Math.min(f2, f4);
        } else {
            f2 = Math.max(f2, f4);
        }
        f4 = f2;
        int i22 = this.viewWidth;
        float f52 = i22 - (f2 * f);
        int i32 = this.viewHeight;
        float f62 = i32 - (f4 * f3);
        this.matchViewWidth = i22 - f52;
        this.matchViewHeight = i32 - f62;
        if (isZoomed()) {
        }
        if (this.prevMatchViewWidth != 0.0f) {
        }
        savePreviousImageValues();
        this.prevMatrix.getValues(this.matrixArray);
        float[] fArr2 = this.matrixArray;
        float f72 = this.matchViewWidth / f;
        float f82 = this.normalizedScale;
        fArr2[0] = f72 * f82;
        fArr2[4] = (this.matchViewHeight / f3) * f82;
        float f92 = fArr2[2];
        float f102 = fArr2[5];
        translateMatrixAfterRotate(2, f92, this.prevMatchViewWidth * f82, getImageWidth(), this.prevViewWidth, this.viewWidth, intrinsicWidth);
        translateMatrixAfterRotate(5, f102, this.prevMatchViewHeight * this.normalizedScale, getImageHeight(), this.prevViewHeight, this.viewHeight, intrinsicHeight);
        this.matrix.setValues(this.matrixArray);
        fixTrans();
        setImageMatrix(this.matrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.matrixArray);
        float imageWidth = getImageWidth();
        int i = this.viewWidth;
        if (imageWidth < i) {
            this.matrixArray[2] = (i - getImageWidth()) / 2.0f;
        }
        float imageHeight = getImageHeight();
        int i2 = this.viewHeight;
        if (imageHeight < i2) {
            this.matrixArray[5] = (i2 - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.matrixArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixTrans() {
        this.matrix.getValues(this.matrixArray);
        float[] fArr = this.matrixArray;
        float f = fArr[2];
        float f2 = fArr[5];
        float fixTrans = getFixTrans(f, this.viewWidth, getImageWidth());
        float fixTrans2 = getFixTrans(f2, this.viewHeight, getImageHeight());
        if (fixTrans == 0.0f && fixTrans2 == 0.0f) {
            return;
        }
        this.matrix.postTranslate(fixTrans, fixTrans2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f5 = f2 - f3;
            f4 = 0.0f;
        } else {
            f4 = f2 - f3;
            f5 = 0.0f;
        }
        if (f < f4) {
            return (-f) + f4;
        }
        if (f <= f5) {
            return 0.0f;
        }
        return (-f) + f5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    public static int[] getRealScreenSize(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int i2 = point.x;
        int i3 = point.y;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Screen size ");
        outline107.append(String.format("%d, %d", Integer.valueOf(i2), Integer.valueOf(i3)));
        Log.i(str, outline107.toString());
        return new int[]{i2, i3};
    }

    private void printMatrixInfo() {
        float[] fArr;
        this.matrix.getValues(new float[9]);
        String str = "Scale: " + fArr[0] + " TransX: " + fArr[2] + " TransY: " + fArr[5];
    }

    private void savePreviousImageValues() {
        Matrix matrix = this.matrix;
        if (matrix == null || this.viewHeight == 0 || this.viewWidth == 0) {
            return;
        }
        matrix.getValues(this.matrixArray);
        this.prevMatrix.setValues(this.matrixArray);
        this.prevMatchViewHeight = this.matchViewHeight;
        this.prevMatchViewWidth = this.matchViewWidth;
        this.prevViewHeight = this.viewHeight;
        this.prevViewWidth = this.viewWidth;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.superMinScale;
            f4 = this.superMaxScale;
        } else {
            f3 = this.minScale;
            f4 = this.maxScale;
        }
        float f5 = this.normalizedScale;
        this.normalizedScale = (float) (f5 * d);
        float f6 = this.normalizedScale;
        if (f6 > f4) {
            this.normalizedScale = f4;
            d = f4 / f5;
        } else if (f6 < f3) {
            this.normalizedScale = f3;
            d = f3 / f5;
        }
        float f7 = (float) d;
        this.matrix.postScale(f7, f7, f, f2);
        fixScaleTrans();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(State state) {
        this.state = state;
    }

    private int setViewSize(int i, int i2, int i3) {
        if (i != Integer.MIN_VALUE) {
            return i != 0 ? i2 : i3;
        }
        return Math.min(i3, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordBitmapToTouch(float f, float f2) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new PointF();
        }
        this.matrix.getValues(this.matrixArray);
        float intrinsicWidth = f / drawable.getIntrinsicWidth();
        float intrinsicHeight = f2 / drawable.getIntrinsicHeight();
        return new PointF((getImageWidth() * intrinsicWidth) + this.matrixArray[2], (getImageHeight() * intrinsicHeight) + this.matrixArray[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return new PointF();
        }
        this.matrix.getValues(this.matrixArray);
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float[] fArr = this.matrixArray;
        float f3 = fArr[2];
        float f4 = fArr[5];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f2 - f4) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    private void translateMatrixAfterRotate(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        float f4 = i3;
        if (f3 < f4) {
            float[] fArr = this.matrixArray;
            fArr[i] = (f4 - (i4 * fArr[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.matrixArray[i] = -((f3 - f4) * 0.5f);
        } else if (f2 == 0.0f) {
            this.matrixArray[i] = 0.0f;
        } else {
            this.matrixArray[i] = -(((((i2 * 0.5f) + Math.abs(f)) / f2) * f3) - (f4 * 0.5f));
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        this.matrix.getValues(this.matrixArray);
        float f = this.matrixArray[2];
        if (getImageWidth() < this.viewWidth) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        return (Math.abs(f) + ((float) this.viewWidth)) + 1.0f < getImageWidth() || i <= 0;
    }

    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    public float getMaxZoom() {
        return this.maxScale;
    }

    public float getMinZoom() {
        return this.minScale;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(this.viewWidth / 2, this.viewHeight / 2, true);
        transformCoordTouchToBitmap.x /= intrinsicWidth;
        transformCoordTouchToBitmap.y /= intrinsicHeight;
        return transformCoordTouchToBitmap;
    }

    public RectF getZoomedRect() {
        if (this.scaleType != ImageView.ScaleType.FIT_XY) {
            PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(0.0f, 0.0f, true);
            PointF transformCoordTouchToBitmap2 = transformCoordTouchToBitmap(this.viewWidth, this.viewHeight, true);
            float intrinsicWidth = getDrawable().getIntrinsicWidth();
            float intrinsicHeight = getDrawable().getIntrinsicHeight();
            return new RectF(transformCoordTouchToBitmap.x / intrinsicWidth, transformCoordTouchToBitmap.y / intrinsicHeight, transformCoordTouchToBitmap2.x / intrinsicWidth, transformCoordTouchToBitmap2.y / intrinsicHeight);
        }
        throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.onDrawReady = false;
    }

    public boolean isCurrentlyZoomed() {
        return getCurrentZoom() != 1.0f;
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        ZoomVariables zoomVariables = this.delayedZoomVariables;
        if (zoomVariables != null) {
            setZoom(zoomVariables.scale, zoomVariables.focusX, zoomVariables.focusY, zoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.viewWidth = setViewSize(mode, size, intrinsicWidth);
            this.viewHeight = setViewSize(mode2, size2, intrinsicHeight);
            setMeasuredDimension(this.viewWidth, this.viewHeight);
            fitImageToView();
            return;
        }
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.normalizedScale = bundle.getFloat("saveScale");
            this.matrixArray = bundle.getFloatArray("matrix");
            this.prevMatrix.setValues(this.matrixArray);
            this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.prevViewHeight = bundle.getInt("viewHeight");
            this.prevViewWidth = bundle.getInt("viewWidth");
            this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.matrixArray);
        bundle.putFloatArray("matrix", this.matrixArray);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        return bundle;
    }

    protected void prepareView(Context context) {
        super.setClickable(true);
        this.context = context;
        this.scaleDetector = new ScaleGestureDetector(context, new ScaleListener(this, null));
        this.gestureDetector = new GestureDetector(context, new GestureListener(this, null));
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.matrixArray = new float[9];
        this.normalizedScale = 1.0f;
        if (this.scaleType == null) {
            this.scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.superMinScale = this.minScale * 0.75f;
        this.superMaxScale = this.maxScale * SUPER_MAX_MULTIPLIER;
        setImageMatrix(this.matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.DEFAULT);
        this.onDrawReady = false;
        super.setOnTouchListener(new PrivateOnTouchListener(this, null));
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageResourceURL(String str) {
        GeneratedOutlineSupport1.outline163("Center cropping image at path ", str, TAG);
        getRealScreenSize((Activity) this.context);
        Glide.with(this.context.getApplicationContext()).mo6762load(str).into(this);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setMaxZoom(float f) {
        this.maxScale = f;
        this.superMaxScale = this.maxScale * SUPER_MAX_MULTIPLIER;
    }

    public void setMinZoom(float f) {
        this.minScale = f;
        this.superMinScale = this.minScale * 0.75f;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.doubleTapListener = onDoubleTapListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.touchImageViewListener = onTouchImageViewListener;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.FIT_START && scaleType != ImageView.ScaleType.FIT_END) {
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                super.setScaleType(scaleType2);
                return;
            }
            this.scaleType = scaleType;
            if (!this.onDrawReady) {
                return;
            }
            setZoom(this);
            return;
        }
        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.normalizedScale, f, f2);
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class PrivateOnTouchListener implements View.OnTouchListener {
        private PointF last;

        private PrivateOnTouchListener() {
            this.last = new PointF();
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
            if (r1 != 6) goto L7;
         */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
            /*
                r7 = this;
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.view.ScaleGestureDetector r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1000(r0)
                r0.onTouchEvent(r9)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.view.GestureDetector r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1100(r0)
                r0.onTouchEvent(r9)
                android.graphics.PointF r0 = new android.graphics.PointF
                float r1 = r9.getX()
                float r2 = r9.getY()
                r0.<init>(r1, r2)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$600(r1)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.DEFAULT
                r3 = 1
                if (r1 == r2) goto L3e
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$600(r1)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.DRAG
                if (r1 == r2) goto L3e
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$600(r1)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.FLING
                if (r1 != r2) goto Lc2
            L3e:
                int r1 = r9.getAction()
                if (r1 == 0) goto La5
                if (r1 == r3) goto L9d
                r2 = 2
                if (r1 == r2) goto L4d
                r0 = 6
                if (r1 == r0) goto L9d
                goto Lc2
            L4d:
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$600(r1)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.DRAG
                if (r1 != r2) goto Lc2
                float r1 = r0.x
                android.graphics.PointF r2 = r7.last
                float r4 = r2.x
                float r1 = r1 - r4
                float r4 = r0.y
                float r2 = r2.y
                float r4 = r4 - r2
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                int r5 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1300(r2)
                float r5 = (float) r5
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r6 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                float r6 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1400(r6)
                float r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1500(r2, r1, r5, r6)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                int r5 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1600(r2)
                float r5 = (float) r5
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r6 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                float r6 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1700(r6)
                float r2 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1500(r2, r4, r5, r6)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r4 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.graphics.Matrix r4 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1800(r4)
                r4.postTranslate(r1, r2)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1900(r1)
                android.graphics.PointF r1 = r7.last
                float r2 = r0.x
                float r0 = r0.y
                r1.set(r2, r0)
                goto Lc2
            L9d:
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.DEFAULT
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1200(r0, r1)
                goto Lc2
            La5:
                android.graphics.PointF r1 = r7.last
                r1.set(r0)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$Fling r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$400(r0)
                if (r0 == 0) goto Lbb
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$Fling r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$400(r0)
                r0.cancelFling()
            Lbb:
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$State r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.State.DRAG
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1200(r0, r1)
            Lc2:
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.graphics.Matrix r1 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$1800(r0)
                r0.setImageMatrix(r1)
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.view.View$OnTouchListener r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$2000(r0)
                if (r0 == 0) goto Ldc
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                android.view.View$OnTouchListener r0 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$2000(r0)
                r0.onTouch(r8, r9)
            Ldc:
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r8 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$OnTouchImageViewListener r8 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$2100(r8)
                if (r8 == 0) goto Led
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView r8 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.this
                com.amazon.alexa.smarthomecameras.view.ZoomableImageView$OnTouchImageViewListener r8 = com.amazon.alexa.smarthomecameras.view.ZoomableImageView.access$2100(r8)
                r8.onMove()
            Led:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.smarthomecameras.view.ZoomableImageView.PrivateOnTouchListener.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }

        /* synthetic */ PrivateOnTouchListener(ZoomableImageView zoomableImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.scaleType);
    }

    public void setZoom(float f, float f2, float f3, ImageView.ScaleType scaleType) {
        if (!this.onDrawReady) {
            this.delayedZoomVariables = new ZoomVariables(f, f2, f3, scaleType);
            return;
        }
        if (scaleType != this.scaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage(f, this.viewWidth / 2, this.viewHeight / 2, true);
        this.matrix.getValues(this.matrixArray);
        this.matrixArray[2] = -((f2 * getImageWidth()) - (this.viewWidth * 0.5f));
        this.matrixArray[5] = -((f3 * getImageHeight()) - (this.viewHeight * 0.5f));
        this.matrix.setValues(this.matrixArray);
        fixTrans();
        setImageMatrix(this.matrix);
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        prepareView(context);
    }

    public void setZoom(ZoomableImageView zoomableImageView) {
        PointF scrollPosition;
        if (zoomableImageView == null || (scrollPosition = zoomableImageView.getScrollPosition()) == null) {
            return;
        }
        setZoom(zoomableImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, zoomableImageView.getScaleType());
    }
}
