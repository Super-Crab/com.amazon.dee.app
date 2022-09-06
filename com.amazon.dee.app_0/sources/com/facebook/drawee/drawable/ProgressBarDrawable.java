package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
/* loaded from: classes2.dex */
public class ProgressBarDrawable extends Drawable implements CloneableDrawable {
    private final Paint mPaint = new Paint(1);
    private final Path mPath = new Path();
    private final RectF mRect = new RectF();
    private int mBackgroundColor = Integer.MIN_VALUE;
    private int mColor = -2147450625;
    private int mPadding = 10;
    private int mBarWidth = 20;
    private int mLevel = 0;
    private int mRadius = 0;
    private boolean mHideWhenZero = false;
    private boolean mIsVertical = false;

    private void drawBar(Canvas canvas, int color) {
        this.mPaint.setColor(color);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPath.reset();
        this.mPath.setFillType(Path.FillType.EVEN_ODD);
        this.mPath.addRoundRect(this.mRect, Math.min(this.mRadius, this.mBarWidth / 2), Math.min(this.mRadius, this.mBarWidth / 2), Path.Direction.CW);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    private void drawHorizontalBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int i = this.mPadding;
        int i2 = bounds.left + i;
        int i3 = bounds.bottom - i;
        int i4 = this.mBarWidth;
        int i5 = i3 - i4;
        this.mRect.set(i2, i5, i2 + (((width - (i * 2)) * level) / 10000), i5 + i4);
        drawBar(canvas, color);
    }

    private void drawVerticalBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        int height = bounds.height();
        int i = this.mPadding;
        int i2 = bounds.left + i;
        int i3 = bounds.top + i;
        this.mRect.set(i2, i3, i2 + this.mBarWidth, i3 + (((height - (i * 2)) * level) / 10000));
        drawBar(canvas, color);
    }

    @Override // com.facebook.drawee.drawable.CloneableDrawable
    /* renamed from: cloneDrawable */
    public Drawable mo6883cloneDrawable() {
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.mBackgroundColor = this.mBackgroundColor;
        progressBarDrawable.mColor = this.mColor;
        progressBarDrawable.mPadding = this.mPadding;
        progressBarDrawable.mBarWidth = this.mBarWidth;
        progressBarDrawable.mLevel = this.mLevel;
        progressBarDrawable.mRadius = this.mRadius;
        progressBarDrawable.mHideWhenZero = this.mHideWhenZero;
        progressBarDrawable.mIsVertical = this.mIsVertical;
        return progressBarDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.mHideWhenZero || this.mLevel != 0) {
            if (this.mIsVertical) {
                drawVerticalBar(canvas, 10000, this.mBackgroundColor);
                drawVerticalBar(canvas, this.mLevel, this.mColor);
                return;
            }
            drawHorizontalBar(canvas, 10000, this.mBackgroundColor);
            drawHorizontalBar(canvas, this.mLevel, this.mColor);
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public int getColor() {
        return this.mColor;
    }

    public boolean getHideWhenZero() {
        return this.mHideWhenZero;
    }

    public boolean getIsVertical() {
        return this.mIsVertical;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mPaint.getColor());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect padding) {
        int i = this.mPadding;
        padding.set(i, i, i, i);
        return this.mPadding != 0;
    }

    public int getRadius() {
        return this.mRadius;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int level) {
        this.mLevel = level;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setBackgroundColor(int backgroundColor) {
        if (this.mBackgroundColor != backgroundColor) {
            this.mBackgroundColor = backgroundColor;
            invalidateSelf();
        }
    }

    public void setBarWidth(int barWidth) {
        if (this.mBarWidth != barWidth) {
            this.mBarWidth = barWidth;
            invalidateSelf();
        }
    }

    public void setColor(int color) {
        if (this.mColor != color) {
            this.mColor = color;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    public void setHideWhenZero(boolean hideWhenZero) {
        this.mHideWhenZero = hideWhenZero;
    }

    public void setIsVertical(boolean isVertical) {
        if (this.mIsVertical != isVertical) {
            this.mIsVertical = isVertical;
            invalidateSelf();
        }
    }

    public void setPadding(int padding) {
        if (this.mPadding != padding) {
            this.mPadding = padding;
            invalidateSelf();
        }
    }

    public void setRadius(int radius) {
        if (this.mRadius != radius) {
            this.mRadius = radius;
            invalidateSelf();
        }
    }
}
