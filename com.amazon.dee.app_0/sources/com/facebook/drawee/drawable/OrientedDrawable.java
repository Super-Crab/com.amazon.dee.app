package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes2.dex */
public class OrientedDrawable extends ForwardingDrawable {
    private int mExifOrientation;
    private int mRotationAngle;
    @VisibleForTesting
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;

    public OrientedDrawable(Drawable drawable, int rotationAngle) {
        this(drawable, rotationAngle, 0);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        if (this.mRotationAngle <= 0 && ((i = this.mExifOrientation) == 0 || i == 1)) {
            super.draw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int i = this.mExifOrientation;
        if (i != 5 && i != 7 && this.mRotationAngle % 180 == 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int i = this.mExifOrientation;
        if (i != 5 && i != 7 && this.mRotationAngle % 180 == 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, com.facebook.drawee.drawable.TransformCallback
    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        if (!this.mRotationMatrix.isIdentity()) {
            transform.preConcat(this.mRotationMatrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        int i;
        Drawable current = getCurrent();
        if (this.mRotationAngle <= 0 && ((i = this.mExifOrientation) == 0 || i == 1)) {
            current.setBounds(bounds);
            return;
        }
        int i2 = this.mExifOrientation;
        if (i2 == 2) {
            this.mRotationMatrix.setScale(-1.0f, 1.0f);
        } else if (i2 == 7) {
            this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
            this.mRotationMatrix.postScale(-1.0f, 1.0f);
        } else if (i2 == 4) {
            this.mRotationMatrix.setScale(1.0f, -1.0f);
        } else if (i2 != 5) {
            this.mRotationMatrix.setRotate(this.mRotationAngle, bounds.centerX(), bounds.centerY());
        } else {
            this.mRotationMatrix.setRotate(270.0f, bounds.centerX(), bounds.centerY());
            this.mRotationMatrix.postScale(1.0f, -1.0f);
        }
        this.mTempMatrix.reset();
        this.mRotationMatrix.invert(this.mTempMatrix);
        this.mTempRectF.set(bounds);
        this.mTempMatrix.mapRect(this.mTempRectF);
        RectF rectF = this.mTempRectF;
        current.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public OrientedDrawable(Drawable drawable, int rotationAngle, int exifOrientation) {
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = rotationAngle - (rotationAngle % 90);
        this.mExifOrientation = (exifOrientation < 0 || exifOrientation > 8) ? 0 : exifOrientation;
    }
}
