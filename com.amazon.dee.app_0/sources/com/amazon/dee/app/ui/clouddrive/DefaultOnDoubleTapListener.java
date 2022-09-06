package com.amazon.dee.app.ui.clouddrive;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.amazon.dee.app.ui.clouddrive.ImageViewGestureHandler;
/* loaded from: classes12.dex */
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {
    private ImageViewGestureHandler mGestureHandler;

    public DefaultOnDoubleTapListener(ImageViewGestureHandler imageViewGestureHandler) {
        setGestureHandler(imageViewGestureHandler);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        ImageViewGestureHandler imageViewGestureHandler = this.mGestureHandler;
        if (imageViewGestureHandler == null) {
            return false;
        }
        try {
            float scale = imageViewGestureHandler.getScale();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (scale < this.mGestureHandler.getMediumScale()) {
                this.mGestureHandler.setScale(this.mGestureHandler.getMediumScale(), x, y, true);
            } else if (scale >= this.mGestureHandler.getMediumScale() && scale < this.mGestureHandler.getMaximumScale()) {
                this.mGestureHandler.setScale(this.mGestureHandler.getMaximumScale(), x, y, true);
            } else {
                this.mGestureHandler.setScale(this.mGestureHandler.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        RectF displayRect;
        ImageViewGestureHandler imageViewGestureHandler = this.mGestureHandler;
        if (imageViewGestureHandler == null) {
            return false;
        }
        ImageView imageView = imageViewGestureHandler.getImageView();
        ImageViewGestureHandler.OnPhotoTapListener onPhotoTapListener = this.mGestureHandler.getOnPhotoTapListener();
        if (onPhotoTapListener != null && (displayRect = this.mGestureHandler.getDisplayRect()) != null) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (displayRect.contains(x, y)) {
                onPhotoTapListener.onPhotoTap(imageView, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                return true;
            }
        }
        ImageViewGestureHandler.OnViewTapListener onViewTapListener = this.mGestureHandler.getOnViewTapListener();
        if (onViewTapListener != null) {
            onViewTapListener.onViewTap(imageView, motionEvent.getX(), motionEvent.getY());
        }
        return false;
    }

    public void setGestureHandler(ImageViewGestureHandler imageViewGestureHandler) {
        this.mGestureHandler = imageViewGestureHandler;
    }
}
