package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public final class Cue {
    public static final int ANCHOR_TYPE_END = 2;
    public static final int ANCHOR_TYPE_MIDDLE = 1;
    public static final int ANCHOR_TYPE_START = 0;
    public static final float DIMEN_UNSET = -3.4028235E38f;
    public static final Cue EMPTY = new Builder().setText("").build();
    public static final int LINE_TYPE_FRACTION = 0;
    public static final int LINE_TYPE_NUMBER = 1;
    public static final int TEXT_SIZE_TYPE_ABSOLUTE = 2;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL = 0;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL_IGNORE_PADDING = 1;
    public static final int TYPE_UNSET = Integer.MIN_VALUE;
    public static final int VERTICAL_TYPE_LR = 2;
    public static final int VERTICAL_TYPE_RL = 1;
    @Nullable
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final int positionAnchor;
    public final float shearDegrees;
    public final float size;
    @Nullable
    public final CharSequence text;
    @Nullable
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final int windowColor;
    public final boolean windowColorSet;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AnchorType {
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable
        private Bitmap bitmap;
        private float bitmapHeight;
        private float line;
        private int lineAnchor;
        private int lineType;
        private float position;
        private int positionAnchor;
        private float shearDegrees;
        private float size;
        @Nullable
        private CharSequence text;
        @Nullable
        private Layout.Alignment textAlignment;
        private float textSize;
        private int textSizeType;
        private int verticalType;
        @ColorInt
        private int windowColor;
        private boolean windowColorSet;

        public Cue build() {
            return new Cue(this.text, this.textAlignment, this.bitmap, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.textSizeType, this.textSize, this.size, this.bitmapHeight, this.windowColorSet, this.windowColor, this.verticalType, this.shearDegrees);
        }

        public Builder clearWindowColor() {
            this.windowColorSet = false;
            return this;
        }

        @Nullable
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public float getBitmapHeight() {
            return this.bitmapHeight;
        }

        public float getLine() {
            return this.line;
        }

        public int getLineAnchor() {
            return this.lineAnchor;
        }

        public int getLineType() {
            return this.lineType;
        }

        public float getPosition() {
            return this.position;
        }

        public int getPositionAnchor() {
            return this.positionAnchor;
        }

        public float getSize() {
            return this.size;
        }

        @Nullable
        public CharSequence getText() {
            return this.text;
        }

        @Nullable
        public Layout.Alignment getTextAlignment() {
            return this.textAlignment;
        }

        public float getTextSize() {
            return this.textSize;
        }

        public int getTextSizeType() {
            return this.textSizeType;
        }

        public int getVerticalType() {
            return this.verticalType;
        }

        @ColorInt
        public int getWindowColor() {
            return this.windowColor;
        }

        public boolean isWindowColorSet() {
            return this.windowColorSet;
        }

        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder setBitmapHeight(float f) {
            this.bitmapHeight = f;
            return this;
        }

        public Builder setLine(float f, int i) {
            this.line = f;
            this.lineType = i;
            return this;
        }

        public Builder setLineAnchor(int i) {
            this.lineAnchor = i;
            return this;
        }

        public Builder setPosition(float f) {
            this.position = f;
            return this;
        }

        public Builder setPositionAnchor(int i) {
            this.positionAnchor = i;
            return this;
        }

        public Builder setShearDegrees(float f) {
            this.shearDegrees = f;
            return this;
        }

        public Builder setSize(float f) {
            this.size = f;
            return this;
        }

        public Builder setText(CharSequence charSequence) {
            this.text = charSequence;
            return this;
        }

        public Builder setTextAlignment(@Nullable Layout.Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        public Builder setTextSize(float f, int i) {
            this.textSize = f;
            this.textSizeType = i;
            return this;
        }

        public Builder setVerticalType(int i) {
            this.verticalType = i;
            return this;
        }

        public Builder setWindowColor(@ColorInt int i) {
            this.windowColor = i;
            this.windowColorSet = true;
            return this;
        }

        public Builder() {
            this.text = null;
            this.bitmap = null;
            this.textAlignment = null;
            this.line = -3.4028235E38f;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.textSizeType = Integer.MIN_VALUE;
            this.textSize = -3.4028235E38f;
            this.size = -3.4028235E38f;
            this.bitmapHeight = -3.4028235E38f;
            this.windowColorSet = false;
            this.windowColor = ViewCompat.MEASURED_STATE_MASK;
            this.verticalType = Integer.MIN_VALUE;
        }

        private Builder(Cue cue) {
            this.text = cue.text;
            this.bitmap = cue.bitmap;
            this.textAlignment = cue.textAlignment;
            this.line = cue.line;
            this.lineType = cue.lineType;
            this.lineAnchor = cue.lineAnchor;
            this.position = cue.position;
            this.positionAnchor = cue.positionAnchor;
            this.textSizeType = cue.textSizeType;
            this.textSize = cue.textSize;
            this.size = cue.size;
            this.bitmapHeight = cue.bitmapHeight;
            this.windowColorSet = cue.windowColorSet;
            this.windowColor = cue.windowColor;
            this.verticalType = cue.verticalType;
            this.shearDegrees = cue.shearDegrees;
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface LineType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TextSizeType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VerticalType {
    }

    public Builder buildUpon() {
        return new Builder();
    }

    @Deprecated
    public Cue(CharSequence charSequence) {
        this(charSequence, null, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this(charSequence, alignment, f, i, i2, f2, i3, f3, false, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, int i4, float f4) {
        this(charSequence, alignment, null, f, i, i2, f2, i3, i4, f4, f3, -3.4028235E38f, false, ViewCompat.MEASURED_STATE_MASK, Integer.MIN_VALUE, 0.0f);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4) {
        this(charSequence, alignment, null, f, i, i2, f2, i3, Integer.MIN_VALUE, -3.4028235E38f, f3, -3.4028235E38f, z, i4, Integer.MIN_VALUE, 0.0f);
    }

    private Cue(@Nullable CharSequence charSequence, @Nullable Layout.Alignment alignment, @Nullable Bitmap bitmap, float f, int i, int i2, float f2, int i3, int i4, float f3, float f4, float f5, boolean z, int i5, int i6, float f6) {
        if (charSequence == null) {
            Assertions.checkNotNull(bitmap);
        } else {
            Assertions.checkArgument(bitmap == null);
        }
        this.text = charSequence;
        this.textAlignment = alignment;
        this.bitmap = bitmap;
        this.line = f;
        this.lineType = i;
        this.lineAnchor = i2;
        this.position = f2;
        this.positionAnchor = i3;
        this.size = f4;
        this.bitmapHeight = f5;
        this.windowColorSet = z;
        this.windowColor = i5;
        this.textSizeType = i4;
        this.textSize = f3;
        this.verticalType = i6;
        this.shearDegrees = f6;
    }
}
