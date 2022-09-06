package com.facebook.drawee.generic;

import androidx.annotation.ColorInt;
import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class RoundingParams {
    private RoundingMethod mRoundingMethod = RoundingMethod.BITMAP_ONLY;
    private boolean mRoundAsCircle = false;
    @Nullable
    private float[] mCornersRadii = null;
    private int mOverlayColor = 0;
    private float mBorderWidth = 0.0f;
    private int mBorderColor = 0;
    private float mPadding = 0.0f;
    private boolean mScaleDownInsideBorders = false;
    private boolean mPaintFilterBitmap = false;

    /* loaded from: classes2.dex */
    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public static RoundingParams asCircle() {
        return new RoundingParams().setRoundAsCircle(true);
    }

    public static RoundingParams fromCornersRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        return new RoundingParams().setCornersRadii(topLeft, topRight, bottomRight, bottomLeft);
    }

    public static RoundingParams fromCornersRadius(float radius) {
        return new RoundingParams().setCornersRadius(radius);
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || RoundingParams.class != o.getClass()) {
            return false;
        }
        RoundingParams roundingParams = (RoundingParams) o;
        if (this.mRoundAsCircle != roundingParams.mRoundAsCircle || this.mOverlayColor != roundingParams.mOverlayColor || Float.compare(roundingParams.mBorderWidth, this.mBorderWidth) != 0 || this.mBorderColor != roundingParams.mBorderColor || Float.compare(roundingParams.mPadding, this.mPadding) != 0 || this.mRoundingMethod != roundingParams.mRoundingMethod || this.mScaleDownInsideBorders != roundingParams.mScaleDownInsideBorders || this.mPaintFilterBitmap != roundingParams.mPaintFilterBitmap) {
            return false;
        }
        return Arrays.equals(this.mCornersRadii, roundingParams.mCornersRadii);
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Nullable
    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    public float getPadding() {
        return this.mPadding;
    }

    public boolean getPaintFilterBitmap() {
        return this.mPaintFilterBitmap;
    }

    public boolean getRoundAsCircle() {
        return this.mRoundAsCircle;
    }

    public RoundingMethod getRoundingMethod() {
        return this.mRoundingMethod;
    }

    public boolean getScaleDownInsideBorders() {
        return this.mScaleDownInsideBorders;
    }

    public int hashCode() {
        RoundingMethod roundingMethod = this.mRoundingMethod;
        int i = 0;
        int hashCode = (((roundingMethod != null ? roundingMethod.hashCode() : 0) * 31) + (this.mRoundAsCircle ? 1 : 0)) * 31;
        float[] fArr = this.mCornersRadii;
        int hashCode2 = (((hashCode + (fArr != null ? Arrays.hashCode(fArr) : 0)) * 31) + this.mOverlayColor) * 31;
        float f = this.mBorderWidth;
        int floatToIntBits = (((hashCode2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.mBorderColor) * 31;
        float f2 = this.mPadding;
        if (f2 != 0.0f) {
            i = Float.floatToIntBits(f2);
        }
        return ((((floatToIntBits + i) * 31) + (this.mScaleDownInsideBorders ? 1 : 0)) * 31) + (this.mPaintFilterBitmap ? 1 : 0);
    }

    public RoundingParams setBorder(@ColorInt int color, float width) {
        Preconditions.checkArgument(width >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = width;
        this.mBorderColor = color;
        return this;
    }

    public RoundingParams setBorderColor(@ColorInt int color) {
        this.mBorderColor = color;
        return this;
    }

    public RoundingParams setBorderWidth(float width) {
        Preconditions.checkArgument(width >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = width;
        return this;
    }

    public RoundingParams setCornersRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        float[] orCreateRoundedCornersRadii = getOrCreateRoundedCornersRadii();
        orCreateRoundedCornersRadii[1] = topLeft;
        orCreateRoundedCornersRadii[0] = topLeft;
        orCreateRoundedCornersRadii[3] = topRight;
        orCreateRoundedCornersRadii[2] = topRight;
        orCreateRoundedCornersRadii[5] = bottomRight;
        orCreateRoundedCornersRadii[4] = bottomRight;
        orCreateRoundedCornersRadii[7] = bottomLeft;
        orCreateRoundedCornersRadii[6] = bottomLeft;
        return this;
    }

    public RoundingParams setCornersRadius(float radius) {
        Arrays.fill(getOrCreateRoundedCornersRadii(), radius);
        return this;
    }

    public RoundingParams setOverlayColor(@ColorInt int overlayColor) {
        this.mOverlayColor = overlayColor;
        this.mRoundingMethod = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    public RoundingParams setPadding(float padding) {
        Preconditions.checkArgument(padding >= 0.0f, "the padding cannot be < 0");
        this.mPadding = padding;
        return this;
    }

    public RoundingParams setPaintFilterBitmap(boolean paintFilterBitmap) {
        this.mPaintFilterBitmap = paintFilterBitmap;
        return this;
    }

    public RoundingParams setRoundAsCircle(boolean roundAsCircle) {
        this.mRoundAsCircle = roundAsCircle;
        return this;
    }

    public RoundingParams setRoundingMethod(RoundingMethod roundingMethod) {
        this.mRoundingMethod = roundingMethod;
        return this;
    }

    public RoundingParams setScaleDownInsideBorders(boolean scaleDownInsideBorders) {
        this.mScaleDownInsideBorders = scaleDownInsideBorders;
        return this;
    }

    public static RoundingParams fromCornersRadii(float[] radii) {
        return new RoundingParams().setCornersRadii(radii);
    }

    public RoundingParams setCornersRadii(float[] radii) {
        Preconditions.checkNotNull(radii);
        Preconditions.checkArgument(radii.length == 8, "radii should have exactly 8 values");
        System.arraycopy(radii, 0, getOrCreateRoundedCornersRadii(), 0, 8);
        return this;
    }
}
