package com.facebook.widget.text.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public class BetterImageSpan extends ReplacementSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    private final int mAlignment;
    private Rect mBounds;
    private final Drawable mDrawable;
    private final Paint.FontMetricsInt mFontMetricsInt;
    private int mHeight;
    private int mWidth;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BetterImageSpanAlignment {
    }

    public BetterImageSpan(Drawable drawable) {
        this(drawable, 1);
    }

    private int getOffsetAboveBaseline(Paint.FontMetricsInt fm) {
        int i = this.mAlignment;
        if (i != 0) {
            if (i != 2) {
                return -this.mHeight;
            }
            int i2 = fm.descent;
            int i3 = fm.ascent;
            return (((i2 - i3) - this.mHeight) / 2) + i3;
        }
        return fm.descent - this.mHeight;
    }

    public static final int normalizeAlignment(int alignment) {
        if (alignment != 0) {
            return alignment != 2 ? 1 : 2;
        }
        return 0;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.getFontMetricsInt(this.mFontMetricsInt);
        int offsetAboveBaseline = y + getOffsetAboveBaseline(this.mFontMetricsInt);
        canvas.translate(x, offsetAboveBaseline);
        this.mDrawable.draw(canvas);
        canvas.translate(-x, -offsetAboveBaseline);
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetrics) {
        updateBounds();
        if (fontMetrics == null) {
            return this.mWidth;
        }
        int offsetAboveBaseline = getOffsetAboveBaseline(fontMetrics);
        int i = this.mHeight + offsetAboveBaseline;
        if (offsetAboveBaseline < fontMetrics.ascent) {
            fontMetrics.ascent = offsetAboveBaseline;
        }
        if (offsetAboveBaseline < fontMetrics.top) {
            fontMetrics.top = offsetAboveBaseline;
        }
        if (i > fontMetrics.descent) {
            fontMetrics.descent = i;
        }
        if (i > fontMetrics.bottom) {
            fontMetrics.bottom = i;
        }
        return this.mWidth;
    }

    public void updateBounds() {
        this.mBounds = this.mDrawable.getBounds();
        this.mWidth = this.mBounds.width();
        this.mHeight = this.mBounds.height();
    }

    public BetterImageSpan(Drawable drawable, int verticalAlignment) {
        this.mFontMetricsInt = new Paint.FontMetricsInt();
        this.mDrawable = drawable;
        this.mAlignment = verticalAlignment;
        updateBounds();
    }
}
