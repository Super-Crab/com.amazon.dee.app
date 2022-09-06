package com.brentvatne.exoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
/* loaded from: classes.dex */
public final class AspectRatioFrameLayout extends FrameLayout {
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    private int resizeMode;
    private float videoAspectRatio;

    public AspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public float getAspectRatio() {
        return this.videoAspectRatio;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public void invalidateAspectRatio() {
        this.videoAspectRatio = 0.0f;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        float f;
        float f2;
        super.onMeasure(i, i2);
        if (this.videoAspectRatio == 0.0f) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f3 = measuredWidth;
        float f4 = measuredHeight;
        float f5 = (this.videoAspectRatio / (f3 / f4)) - 1.0f;
        if (Math.abs(f5) <= 0.01f) {
            return;
        }
        int i3 = this.resizeMode;
        if (i3 == 1) {
            f = this.videoAspectRatio;
        } else {
            if (i3 == 2) {
                f2 = this.videoAspectRatio;
            } else {
                if (i3 != 3) {
                    if (i3 == 4) {
                        int i4 = (int) (this.videoAspectRatio * f4);
                        if (i4 < measuredWidth) {
                            float f6 = i4;
                            float f7 = f3 / f6;
                            measuredWidth = (int) (f6 * f7);
                            measuredHeight = (int) (f4 * f7);
                        } else {
                            measuredWidth = i4;
                        }
                    } else if (f5 > 0.0f) {
                        f = this.videoAspectRatio;
                    } else {
                        f2 = this.videoAspectRatio;
                    }
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
            measuredWidth = (int) (f4 * f2);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
        measuredHeight = (int) (f3 / f);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
    }

    public void setAspectRatio(float f) {
        if (this.videoAspectRatio != f) {
            this.videoAspectRatio = f;
            requestLayout();
        }
    }

    public void setResizeMode(int i) {
        if (this.resizeMode != i) {
            this.resizeMode = i;
            requestLayout();
        }
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.resizeMode = 0;
    }
}
