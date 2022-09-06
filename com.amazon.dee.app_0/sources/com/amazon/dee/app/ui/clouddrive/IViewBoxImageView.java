package com.amazon.dee.app.ui.clouddrive;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.amazon.dee.app.ui.clouddrive.ImageViewGestureHandler;
/* loaded from: classes12.dex */
public interface IViewBoxImageView {
    Bitmap getCroppedImage(String str);

    Matrix getDisplayMatrix();

    RectF getDisplayRect();

    float getMaximumScale();

    float getMediumScale();

    float getMinimumScale();

    ImageViewGestureHandler.OnPhotoTapListener getOnPhotoTapListener();

    ImageViewGestureHandler.OnViewTapListener getOnViewTapListener();

    float getScale();

    ImageView.ScaleType getScaleType();

    Bitmap getVisibleRectBitmap();

    void reset();

    void setAllowParentInterceptOnEdge(boolean z);

    void setCropWindowRect(RectF rectF);

    boolean setDisplayMatrix(Matrix matrix);

    void setImageBoundsListener(IGetImageBounds iGetImageBounds);

    void setMaximumScale(float f);

    void setMediumScale(float f);

    void setMinimumScale(float f);

    float setMinimumScaleToFit(Drawable drawable, float f, float f2);

    void setOnDoubleTapListener(@NonNull GestureDetector.OnDoubleTapListener onDoubleTapListener);

    void setOnMatrixChangeListener(@NonNull ImageViewGestureHandler.OnMatrixChangedListener onMatrixChangedListener);

    void setOnPhotoTapListener(@NonNull ImageViewGestureHandler.OnPhotoTapListener onPhotoTapListener);

    void setOnViewTapListener(ImageViewGestureHandler.OnViewTapListener onViewTapListener);

    void setScale(float f);

    void setScale(float f, float f2, float f3, boolean z);

    void setScale(float f, boolean z);

    void setScaleType(@NonNull ImageView.ScaleType scaleType);

    void setZoomTransitionDuration(int i);

    void update();
}
