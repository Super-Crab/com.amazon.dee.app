package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class ScaleTypeDrawable extends ForwardingDrawable {
    @Nullable
    @VisibleForTesting
    Matrix mDrawMatrix;
    @Nullable
    @VisibleForTesting
    PointF mFocusPoint;
    @VisibleForTesting
    ScalingUtils.ScaleType mScaleType;
    @Nullable
    @VisibleForTesting
    Object mScaleTypeState;
    private Matrix mTempMatrix;
    @VisibleForTesting
    int mUnderlyingHeight;
    @VisibleForTesting
    int mUnderlyingWidth;

    public ScaleTypeDrawable(Drawable drawable, ScalingUtils.ScaleType scaleType) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mFocusPoint = null;
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mTempMatrix = new Matrix();
        this.mScaleType = scaleType;
    }

    private void configureBoundsIfUnderlyingChanged() {
        boolean z;
        ScalingUtils.ScaleType scaleType = this.mScaleType;
        boolean z2 = true;
        if (scaleType instanceof ScalingUtils.StatefulScaleType) {
            Object state = ((ScalingUtils.StatefulScaleType) scaleType).getState();
            z = state == null || !state.equals(this.mScaleTypeState);
            this.mScaleTypeState = state;
        } else {
            z = false;
        }
        if (this.mUnderlyingWidth == getCurrent().getIntrinsicWidth() && this.mUnderlyingHeight == getCurrent().getIntrinsicHeight()) {
            z2 = false;
        }
        if (z2 || z) {
            configureBounds();
        }
    }

    @VisibleForTesting
    void configureBounds() {
        Drawable current = getCurrent();
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.mUnderlyingWidth = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.mUnderlyingHeight = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (intrinsicWidth == width && intrinsicHeight == height) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (this.mScaleType == ScalingUtils.ScaleType.FIT_XY) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else {
            current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            ScalingUtils.ScaleType scaleType = this.mScaleType;
            Matrix matrix = this.mTempMatrix;
            PointF pointF = this.mFocusPoint;
            float f = 0.5f;
            float f2 = pointF != null ? pointF.x : 0.5f;
            PointF pointF2 = this.mFocusPoint;
            if (pointF2 != null) {
                f = pointF2.y;
            }
            scaleType.getTransform(matrix, bounds, intrinsicWidth, intrinsicHeight, f2, f);
            this.mDrawMatrix = this.mTempMatrix;
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.mDrawMatrix);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    @Nullable
    public PointF getFocusPoint() {
        return this.mFocusPoint;
    }

    public ScalingUtils.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        configureBoundsIfUnderlyingChanged();
        Matrix matrix = this.mDrawMatrix;
        if (matrix != null) {
            transform.preConcat(matrix);
        }
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        configureBounds();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable
    public Drawable setCurrent(@Nullable Drawable newDelegate) {
        Drawable current = super.setCurrent(newDelegate);
        configureBounds();
        return current;
    }

    public void setFocusPoint(@Nullable PointF focusPoint) {
        if (Objects.equal(this.mFocusPoint, focusPoint)) {
            return;
        }
        if (focusPoint == null) {
            this.mFocusPoint = null;
        } else {
            if (this.mFocusPoint == null) {
                this.mFocusPoint = new PointF();
            }
            this.mFocusPoint.set(focusPoint);
        }
        configureBounds();
        invalidateSelf();
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        if (Objects.equal(this.mScaleType, scaleType)) {
            return;
        }
        this.mScaleType = scaleType;
        this.mScaleTypeState = null;
        configureBounds();
        invalidateSelf();
    }

    public ScaleTypeDrawable(Drawable drawable, ScalingUtils.ScaleType scaleType, @Nullable PointF focusPoint) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mFocusPoint = null;
        this.mUnderlyingWidth = 0;
        this.mUnderlyingHeight = 0;
        this.mTempMatrix = new Matrix();
        this.mScaleType = scaleType;
        this.mFocusPoint = focusPoint;
    }
}
