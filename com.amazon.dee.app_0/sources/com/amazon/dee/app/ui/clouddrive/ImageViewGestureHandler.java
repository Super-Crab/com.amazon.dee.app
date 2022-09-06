package com.amazon.dee.app.ui.clouddrive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.clouddrive.gestures.IGestureListener;
import com.amazon.dee.app.ui.clouddrive.gestures.ViewBoxGestureDetector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class ImageViewGestureHandler implements IViewBoxImageView, View.OnTouchListener, IGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final int EDGE_BOTH = 2;
    private static final int EDGE_LEFT = 0;
    private static final int EDGE_NONE = -1;
    private static final int EDGE_RIGHT = 1;
    private static final String TAG = Log.tag(ImageViewGestureHandler.class);
    private static final Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
    private RectF cropWindowRect;
    private float defaultScale;
    private IGetImageBounds mBoundsListener;
    private FlingRunnable mCurrentFlingRunnable;
    private GestureDetector mGestureDetector;
    private WeakReference<ImageView> mImageView;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private OnMatrixChangedListener mMatrixChangeListener;
    private OnPhotoTapListener mPhotoTapListener;
    private ViewBoxGestureDetector mScaleDragDetector;
    private OnViewTapListener mViewTapListener;
    private int mZoomDuration;
    private final Matrix mBaseMatrix = new Matrix();
    private final Matrix mDrawMatrix = new Matrix();
    private final Matrix mSuppMatrix = new Matrix();
    private final RectF mDisplayRect = new RectF();
    private final float[] mMatrixValues = new float[9];
    private float mMinScale = 0.75f;
    private float mMidScale = 1.5f;
    private float mMaxScale = 3.0f;
    private boolean mAllowParentInterceptOnEdge = true;
    private int mScrollEdge = 2;
    private boolean mZoomEnabled = true;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.CENTER;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.ui.clouddrive.ImageViewGestureHandler$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final float mZoomEnd;
        private final float mZoomStart;

        AnimatedZoomRunnable(float f, float f2, float f3, float f4) {
            this.mFocalX = f3;
            this.mFocalY = f4;
            this.mZoomStart = f;
            this.mZoomEnd = f2;
        }

        private float interpolate() {
            return ImageViewGestureHandler.sInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ImageViewGestureHandler.this.mZoomDuration));
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView = ImageViewGestureHandler.this.getImageView();
            if (imageView == null) {
                return;
            }
            float interpolate = interpolate();
            float f = this.mZoomStart;
            float outline0 = GeneratedOutlineSupport1.outline0(this.mZoomEnd, f, interpolate, f) / ImageViewGestureHandler.this.getScale();
            ImageViewGestureHandler.this.mSuppMatrix.postScale(outline0, outline0, this.mFocalX, this.mFocalY);
            ImageViewGestureHandler.this.checkAndDisplayMatrix();
            if (interpolate >= 1.0f) {
                return;
            }
            imageView.postOnAnimation(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final OverScroller mScroller;

        FlingRunnable(Context context) {
            this.mScroller = new OverScroller(context);
        }

        public void cancelFling() {
            String unused = ImageViewGestureHandler.TAG;
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = ImageViewGestureHandler.this.getDisplayRect();
            if (displayRect == null) {
                return;
            }
            int round = Math.round(-displayRect.left);
            float f = i;
            if (f < displayRect.width()) {
                i6 = Math.round(displayRect.width() - f);
                i5 = 0;
            } else {
                i5 = round;
                i6 = i5;
            }
            int round2 = Math.round(-displayRect.top);
            float f2 = i2;
            if (f2 < displayRect.height()) {
                i8 = Math.round(displayRect.height() - f2);
                i7 = 0;
            } else {
                i7 = round2;
                i8 = i7;
            }
            this.mCurrentX = round;
            this.mCurrentY = round2;
            String unused = ImageViewGestureHandler.TAG;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("fling. StartX:", round, " StartY:", round2, " MaxX:");
            outline110.append(i6);
            outline110.append(" MaxY:");
            outline110.append(i8);
            outline110.toString();
            if (round == i6 && round2 == i8) {
                return;
            }
            this.mScroller.fling(round, round2, i3, i4, i5, i6, i7, i8, 0, 0);
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView;
            if (!this.mScroller.isFinished() && (imageView = ImageViewGestureHandler.this.getImageView()) != null && this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                String unused = ImageViewGestureHandler.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("fling run(). CurrentX:");
                outline107.append(this.mCurrentX);
                outline107.append(" CurrentY:");
                GeneratedOutlineSupport1.outline175(outline107, this.mCurrentY, " NewX:", currX, " NewY:");
                outline107.append(currY);
                outline107.toString();
                ImageViewGestureHandler.this.mSuppMatrix.postTranslate(this.mCurrentX - currX, this.mCurrentY - currY);
                ImageViewGestureHandler imageViewGestureHandler = ImageViewGestureHandler.this;
                imageViewGestureHandler.setImageViewMatrix(imageViewGestureHandler.getDrawMatrix());
                this.mCurrentX = currX;
                this.mCurrentY = currY;
                imageView.postOnAnimation(this);
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    /* loaded from: classes12.dex */
    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    /* loaded from: classes12.dex */
    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImageViewGestureHandler(ImageView imageView) {
        this.mImageView = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        this.mZoomDuration = imageView.getContext().getResources().getInteger(R.integer.image_zoom_duration_ms);
        setImageViewScaleTypeMatrix(imageView);
        if (imageView.isInEditMode()) {
            return;
        }
        this.mScaleDragDetector = new ViewBoxGestureDetector(imageView.getContext(), this);
        this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener());
        this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        update();
        this.defaultScale = getScale();
    }

    private void cancelFling() {
        FlingRunnable flingRunnable = this.mCurrentFlingRunnable;
        if (flingRunnable != null) {
            flingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView == null || (imageView instanceof IViewBoxImageView) || ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            return;
        }
        throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a ImageViewGestureHandler");
    }

    private boolean checkMatrixBounds() {
        RectF displayRect;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        if (getImageView() == null || (displayRect = getDisplayRect(getDrawMatrix())) == null) {
            return false;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        Rect imageBounds = getImageBounds();
        float height2 = imageBounds.height();
        float f8 = 0.0f;
        if (height <= height2) {
            int i = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i == 2) {
                f3 = displayRect.top;
                f4 = -f3;
            } else if (i != 3) {
                if (i == 4 && this.mScaleDragDetector.isDragging()) {
                    f4 = this.cropWindowRect.top + (((height2 - height) / 2.0f) - displayRect.top);
                }
                f4 = 0.0f;
            } else {
                f2 = height2 - height;
                f = displayRect.top;
                f4 = f2 - f;
            }
        } else {
            float f9 = displayRect.top;
            int i2 = imageBounds.top;
            if (f9 > i2) {
                f3 = f9 - i2;
                f4 = -f3;
            } else {
                f = displayRect.bottom;
                int i3 = imageBounds.bottom;
                if (f < i3) {
                    f2 = i3;
                    f4 = f2 - f;
                }
                f4 = 0.0f;
            }
        }
        float width2 = imageBounds.width();
        if (width <= width2) {
            int i4 = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i4 != 2) {
                if (i4 == 3) {
                    f5 = width2 - width;
                    f6 = displayRect.left;
                } else if (i4 != 4) {
                    f5 = (width2 - width) / 2.0f;
                    f6 = displayRect.left;
                } else if (this.mScaleDragDetector.isDragging()) {
                    f7 = (((width2 - width) / 2.0f) - displayRect.left) + this.cropWindowRect.left;
                    f8 = f7;
                }
                f7 = f5 - f6;
                f8 = f7;
            } else {
                f8 = -displayRect.left;
            }
            this.mScrollEdge = 2;
        } else {
            float f10 = displayRect.left;
            int i5 = imageBounds.left;
            if (f10 > i5) {
                this.mScrollEdge = 0;
                f8 = -(f10 - i5);
            } else {
                float f11 = displayRect.right;
                int i6 = imageBounds.right;
                if (f11 < i6) {
                    f8 = i6 - f11;
                    this.mScrollEdge = 1;
                } else {
                    this.mScrollEdge = -1;
                }
            }
        }
        this.mSuppMatrix.postTranslate(f8, f4);
        return true;
    }

    private int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private Bitmap getOriginalBitmap() {
        BitmapDrawable bitmapDrawable;
        if (!(getImageView().getDrawable() instanceof BitmapDrawable) || (bitmapDrawable = (BitmapDrawable) getImageView().getDrawable()) == null) {
            return null;
        }
        return bitmapDrawable.getBitmap();
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalArgumentException(scaleType.name() + " is not supported in ViewBoxImageView");
    }

    private void resetMatrix() {
        this.mSuppMatrix.reset();
        setImageViewMatrix(getDrawMatrix());
        checkMatrixBounds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            if (this.mMatrixChangeListener == null || (displayRect = getDisplayRect(matrix)) == null) {
                return;
            }
            this.mMatrixChangeListener.onMatrixChanged(displayRect);
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView == null || (imageView instanceof IViewBoxImageView) || ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            return;
        }
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
    }

    private void updateBaseMatrix(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView == null || drawable == null) {
            return;
        }
        float imageViewWidth = getImageViewWidth(imageView);
        float imageViewHeight = getImageViewHeight(imageView);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mBaseMatrix.reset();
        float f = intrinsicWidth;
        float f2 = imageViewWidth / f;
        float f3 = intrinsicHeight;
        float f4 = imageViewHeight / f3;
        ImageView.ScaleType scaleType = this.mScaleType;
        if (scaleType == ImageView.ScaleType.CENTER) {
            this.mBaseMatrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
        } else if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            float max = Math.max(f2, f4);
            this.mBaseMatrix.postScale(max, max);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * max)) / 2.0f, (imageViewHeight - (f3 * max)) / 2.0f);
        } else if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            float min = Math.min(1.0f, Math.min(f2, f4));
            this.mBaseMatrix.postScale(min, min);
            this.mBaseMatrix.postTranslate((imageViewWidth - (f * min)) / 2.0f, (imageViewHeight - (f3 * min)) / 2.0f);
        } else {
            RectF rectF = new RectF(0.0f, 0.0f, f, f3);
            RectF rectF2 = new RectF(0.0f, 0.0f, imageViewWidth, imageViewHeight);
            int i = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i == 2) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
            } else if (i == 3) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
            } else if (i == 5) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            } else if (i == 6) {
                this.mBaseMatrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            }
        }
        resetMatrix();
    }

    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        WeakReference<ImageView> weakReference = this.mImageView;
        if (weakReference == null) {
            return;
        }
        ImageView imageView = weakReference.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            imageView.setOnTouchListener(null);
            cancelFling();
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.setOnDoubleTapListener(null);
        }
        this.mMatrixChangeListener = null;
        this.mPhotoTapListener = null;
        this.mViewTapListener = null;
        this.mImageView = null;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Bitmap getCroppedImage(String str) {
        float abs;
        float abs2;
        Bitmap originalBitmap = getOriginalBitmap();
        float height = originalBitmap.getHeight();
        float width = originalBitmap.getWidth();
        float[] fArr = new float[9];
        getImageView().getImageMatrix().getValues(fArr);
        int i = 0;
        float f = fArr[0];
        float f2 = fArr[4];
        float f3 = width / (f * width);
        float position = CropRect.TOP.getPosition();
        float f4 = height / (f2 * height);
        float width2 = CropRect.getWidth();
        float height2 = CropRect.getHeight();
        RectF displayRect = getDisplayRect();
        float round = Math.round(displayRect.left);
        float position2 = CropRect.LEFT.getPosition();
        if (position2 > 0.0f) {
            abs = round >= 0.0f ? position2 - round : position2 + Math.abs(round);
        } else {
            abs = Math.abs(round);
        }
        float round2 = Math.round(displayRect.top);
        if (position > 0.0f) {
            abs2 = round2 >= 0.0f ? position - round2 : position + Math.abs(round2);
        } else {
            abs2 = Math.abs(round2);
        }
        int floor = (int) Math.floor(abs * f3);
        int floor2 = (int) Math.floor(abs2 * f4);
        int floor3 = (int) Math.floor(width2 * f3);
        int floor4 = (int) Math.floor(height2 * f4);
        if (floor < 0) {
            floor = 0;
        }
        if (floor2 >= 0) {
            i = floor2;
        }
        if (floor + floor3 > width) {
            floor3 = ((int) width) - floor;
        }
        if (i + floor4 > height) {
            floor4 = ((int) height) - i;
        }
        Bitmap createBitmap = Bitmap.createBitmap(originalBitmap, floor, i, floor3, floor4);
        DeviceScreenConfiguration byType = DeviceScreenConfiguration.getByType(str);
        double screenScale = byType.getScreenScale(createBitmap);
        return screenScale < 1.0d ? Bitmap.createScaledBitmap(createBitmap, byType.width, (int) (createBitmap.getHeight() * screenScale), true) : createBitmap;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public RectF getDisplayRect() {
        checkMatrixBounds();
        return getDisplayRect(getDrawMatrix());
    }

    public Matrix getDrawMatrix() {
        this.mDrawMatrix.set(this.mBaseMatrix);
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    public Rect getImageBounds() {
        if (getImageView() == null) {
            return new Rect();
        }
        IGetImageBounds iGetImageBounds = this.mBoundsListener;
        if (iGetImageBounds != null) {
            return iGetImageBounds.getImageBounds();
        }
        return new Rect(getImageViewWidth(getImageView()), 0, 0, getImageViewHeight(getImageView()));
    }

    public ImageView getImageView() {
        WeakReference<ImageView> weakReference = this.mImageView;
        ImageView imageView = weakReference != null ? weakReference.get() : null;
        if (imageView == null) {
            cleanup();
            Log.i(TAG, "ImageView no longer exists. You should not use this ImageViewGestureHandler any more.");
        }
        return imageView;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMaximumScale() {
        return this.mMaxScale;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMediumScale() {
        return this.mMidScale;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMinimumScale() {
        return this.mMinScale;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getScale() {
        return (float) Math.sqrt(((float) Math.pow(getValue(this.mSuppMatrix, 0), 2.0d)) + ((float) Math.pow(getValue(this.mSuppMatrix, 3), 2.0d)));
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Bitmap getVisibleRectBitmap() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return null;
        }
        Bitmap drawingCache = imageView.getDrawingCache();
        if (drawingCache != null) {
            return drawingCache;
        }
        Bitmap createBitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.RGB_565);
        imageView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.gestures.IGestureListener
    public void onDrag(float f, float f2) {
        if (this.mScaleDragDetector.isScaling()) {
            return;
        }
        String.format("onDrag: dx: %.2f. dy: %.2f", Float.valueOf(f), Float.valueOf(f2));
        ImageView imageView = getImageView();
        this.mSuppMatrix.postTranslate(f, f2);
        checkAndDisplayMatrix();
        ViewParent parent = imageView.getParent();
        if (!this.mAllowParentInterceptOnEdge || this.mScaleDragDetector.isScaling()) {
            if (parent == null) {
                return;
            }
            parent.requestDisallowInterceptTouchEvent(true);
            return;
        }
        int i = this.mScrollEdge;
        if ((i != 2 && ((i != 0 || f < 1.0f) && (this.mScrollEdge != 1 || f > -1.0f))) || parent == null) {
            return;
        }
        parent.requestDisallowInterceptTouchEvent(false);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.gestures.IGestureListener
    public void onFling(float f, float f2, float f3, float f4) {
        String str = "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4;
        ImageView imageView = getImageView();
        this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
        this.mCurrentFlingRunnable.fling(getImageViewWidth(imageView), getImageViewHeight(imageView), (int) f3, (int) f4);
        imageView.post(this.mCurrentFlingRunnable);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (this.mZoomEnabled) {
                int top = imageView.getTop();
                int right = imageView.getRight();
                int bottom = imageView.getBottom();
                int left = imageView.getLeft();
                if (top == this.mIvTop && bottom == this.mIvBottom && left == this.mIvLeft && right == this.mIvRight) {
                    return;
                }
                updateBaseMatrix(imageView.getDrawable());
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
                return;
            }
            updateBaseMatrix(imageView.getDrawable());
        }
    }

    @Override // com.amazon.dee.app.ui.clouddrive.gestures.IGestureListener
    public void onScale(float f, float f2, float f3) {
        String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
        if (getScale() < this.mMaxScale || f < 1.0f) {
            this.mSuppMatrix.postScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        RectF displayRect;
        boolean z = false;
        if (ImageView.class.isAssignableFrom(view.getClass()) && this.mZoomEnabled && hasDrawable((ImageView) view)) {
            ViewParent parent = view.getParent();
            int action = motionEvent.getAction();
            if (action != 0) {
                if ((action == 1 || action == 3) && getScale() < this.mMinScale && (displayRect = getDisplayRect()) != null) {
                    view.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, displayRect.centerX(), displayRect.centerY()));
                    z = true;
                }
            } else {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                } else {
                    Log.i(TAG, "onTouch getParent() returned null");
                }
                cancelFling();
            }
            ViewBoxGestureDetector viewBoxGestureDetector = this.mScaleDragDetector;
            if (viewBoxGestureDetector != null && viewBoxGestureDetector.onTouchEvent(motionEvent)) {
                z = true;
            }
            GestureDetector gestureDetector = this.mGestureDetector;
            if (gestureDetector != null && gestureDetector.onTouchEvent(motionEvent)) {
                return true;
            }
            return z;
        }
        return false;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void reset() {
        update();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setCropWindowRect(RectF rectF) {
        this.cropWindowRect = rectF;
        CropRect.LEFT.setPosition(rectF.left);
        CropRect.TOP.setPosition(rectF.top);
        CropRect.RIGHT.setPosition(rectF.right);
        CropRect.BOTTOM.setPosition(rectF.bottom);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public boolean setDisplayMatrix(Matrix matrix) {
        if (matrix != null) {
            ImageView imageView = getImageView();
            if (imageView == null || imageView.getDrawable() == null) {
                return false;
            }
            this.mSuppMatrix.set(matrix);
            setImageViewMatrix(getDrawMatrix());
            checkMatrixBounds();
            return true;
        }
        throw new IllegalArgumentException("Matrix cannot be null");
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setImageBoundsListener(IGetImageBounds iGetImageBounds) {
        this.mBoundsListener = iGetImageBounds;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMaximumScale(float f) {
        this.mMaxScale = f;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMediumScale(float f) {
        this.mMidScale = f;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMinimumScale(float f) {
        this.mMinScale = f;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float setMinimumScaleToFit(Drawable drawable, float f, float f2) {
        float max = Math.max(f2 / drawable.getIntrinsicHeight(), f / drawable.getIntrinsicWidth());
        setMinimumScale(max);
        return max;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnDoubleTapListener(@Nullable GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnMatrixChangeListener(@NonNull OnMatrixChangedListener onMatrixChangedListener) {
        this.mMatrixChangeListener = onMatrixChangedListener;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnPhotoTapListener(@NonNull OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mViewTapListener = onViewTapListener;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f) {
        setScale(f, false);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        if (!isSupportedScaleType(scaleType) || scaleType == this.mScaleType) {
            return;
        }
        this.mScaleType = scaleType;
        update();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setZoomTransitionDuration(int i) {
        if (i >= 0) {
            this.mZoomDuration = i;
        }
    }

    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        update();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void update() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (this.mZoomEnabled) {
                setImageViewScaleTypeMatrix(imageView);
                updateBaseMatrix(imageView.getDrawable());
            } else {
                resetMatrix();
            }
            setScale(getMinimumScale());
        }
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f, imageView.getRight() / 2, imageView.getBottom() / 2, z);
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f, float f2, float f3, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (f < this.mMinScale || f > this.mMaxScale) {
                Log.i(TAG, "Scale must be within the range of minScale and maxScale");
            } else if (z) {
                imageView.post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
            } else {
                this.mSuppMatrix.setScale(f, f, f2, f3);
                checkAndDisplayMatrix();
            }
        }
    }
}
