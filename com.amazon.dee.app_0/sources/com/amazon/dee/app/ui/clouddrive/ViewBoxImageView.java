package com.amazon.dee.app.ui.clouddrive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import com.amazon.dee.app.ui.clouddrive.ImageViewGestureHandler;
/* loaded from: classes12.dex */
public class ViewBoxImageView extends AppCompatImageView implements IViewBoxImageView {
    private final ImageViewGestureHandler mGestureHandler;
    private ImageView.ScaleType mPendingScaleType;

    public ViewBoxImageView(Context context) {
        this(context, null);
    }

    private void postUpdate() {
        post(new Runnable() { // from class: com.amazon.dee.app.ui.clouddrive.ViewBoxImageView.1
            @Override // java.lang.Runnable
            public void run() {
                if (ViewBoxImageView.this.mGestureHandler != null) {
                    ViewBoxImageView.this.mGestureHandler.update();
                }
            }
        });
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Bitmap getCroppedImage(String str) {
        return this.mGestureHandler.getCroppedImage(str);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Matrix getDisplayMatrix() {
        return this.mGestureHandler.getDrawMatrix();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public RectF getDisplayRect() {
        return this.mGestureHandler.getDisplayRect();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMaximumScale() {
        return this.mGestureHandler.getMaximumScale();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMediumScale() {
        return this.mGestureHandler.getMediumScale();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getMinimumScale() {
        return this.mGestureHandler.getMinimumScale();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public ImageViewGestureHandler.OnPhotoTapListener getOnPhotoTapListener() {
        return this.mGestureHandler.getOnPhotoTapListener();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public ImageViewGestureHandler.OnViewTapListener getOnViewTapListener() {
        return this.mGestureHandler.getOnViewTapListener();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float getScale() {
        return this.mGestureHandler.getScale();
    }

    @Override // android.widget.ImageView, com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public ImageView.ScaleType getScaleType() {
        return this.mGestureHandler.getScaleType();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public Bitmap getVisibleRectBitmap() {
        return this.mGestureHandler.getVisibleRectBitmap();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        this.mGestureHandler.cleanup();
        super.onDetachedFromWindow();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void reset() {
        this.mGestureHandler.reset();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mGestureHandler.setAllowParentInterceptOnEdge(z);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setCropWindowRect(RectF rectF) {
        this.mGestureHandler.setCropWindowRect(rectF);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public boolean setDisplayMatrix(Matrix matrix) {
        return this.mGestureHandler.setDisplayMatrix(matrix);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setImageBoundsListener(IGetImageBounds iGetImageBounds) {
        this.mGestureHandler.setImageBoundsListener(iGetImageBounds);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        postUpdate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        postUpdate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        postUpdate();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMaximumScale(float f) {
        this.mGestureHandler.setMaximumScale(f);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMediumScale(float f) {
        this.mGestureHandler.setMediumScale(f);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setMinimumScale(float f) {
        this.mGestureHandler.setMinimumScale(f);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public float setMinimumScaleToFit(Drawable drawable, float f, float f2) {
        return this.mGestureHandler.setMinimumScaleToFit(drawable, f, f2);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnDoubleTapListener(@NonNull GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mGestureHandler.setOnDoubleTapListener(onDoubleTapListener);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnMatrixChangeListener(@NonNull ImageViewGestureHandler.OnMatrixChangedListener onMatrixChangedListener) {
        this.mGestureHandler.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnPhotoTapListener(@NonNull ImageViewGestureHandler.OnPhotoTapListener onPhotoTapListener) {
        this.mGestureHandler.setOnPhotoTapListener(onPhotoTapListener);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setOnViewTapListener(ImageViewGestureHandler.OnViewTapListener onViewTapListener) {
        this.mGestureHandler.setOnViewTapListener(onViewTapListener);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f) {
        this.mGestureHandler.setScale(f);
    }

    @Override // android.widget.ImageView, com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        ImageViewGestureHandler imageViewGestureHandler = this.mGestureHandler;
        if (imageViewGestureHandler != null) {
            imageViewGestureHandler.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setZoomTransitionDuration(int i) {
        this.mGestureHandler.setZoomTransitionDuration(i);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void update() {
        this.mGestureHandler.update();
    }

    public ViewBoxImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f, boolean z) {
        this.mGestureHandler.setScale(f, z);
    }

    public ViewBoxImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        this.mGestureHandler = new ImageViewGestureHandler(this);
        ImageView.ScaleType scaleType = this.mPendingScaleType;
        if (scaleType != null) {
            setScaleType(scaleType);
            this.mPendingScaleType = null;
        }
    }

    @Override // com.amazon.dee.app.ui.clouddrive.IViewBoxImageView
    public void setScale(float f, float f2, float f3, boolean z) {
        this.mGestureHandler.setScale(f, f2, f3, z);
    }
}
