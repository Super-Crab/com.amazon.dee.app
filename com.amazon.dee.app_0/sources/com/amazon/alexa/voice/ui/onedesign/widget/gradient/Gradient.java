package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import androidx.annotation.ColorInt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class Gradient implements GradientModel {
    private final int bottomColor;
    private final int height;
    private final int topColor;
    private final int width;

    /* loaded from: classes11.dex */
    public static final class Builder {
        int bottomColor;
        int height;
        int topColor;
        int width;

        public Builder bottomColor(int i) {
            this.bottomColor = i;
            return this;
        }

        public Gradient build() {
            return new Gradient(this);
        }

        public Builder height(int i) {
            this.height = i;
            return this;
        }

        public Builder topColor(int i) {
            this.topColor = i;
            return this;
        }

        public Builder width(int i) {
            this.width = i;
            return this;
        }
    }

    Gradient(Builder builder) {
        this.topColor = builder.topColor;
        this.bottomColor = builder.bottomColor;
        this.width = builder.width;
        this.height = builder.height;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Gradient.class != obj.getClass()) {
            return false;
        }
        Gradient gradient = (Gradient) obj;
        return this.topColor == gradient.topColor && this.bottomColor == gradient.bottomColor && this.width == gradient.width && this.height == gradient.height;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientModel
    @ColorInt
    public int getBottomColor() {
        return this.bottomColor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientModel
    public int getHeight() {
        return this.height;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientModel
    @ColorInt
    public int getTopColor() {
        return this.topColor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientModel
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return ((((((JfifUtil.MARKER_EOI + this.topColor) * 31) + this.bottomColor) * 31) + this.width) * 31) + this.height;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Gradient{topColor=");
        outline107.append(this.topColor);
        outline107.append(", bottomColor=");
        outline107.append(this.bottomColor);
        outline107.append(", width=");
        outline107.append(this.width);
        outline107.append(", height=");
        return GeneratedOutlineSupport1.outline85(outline107, this.height, JsonReaderKt.END_OBJ);
    }
}
