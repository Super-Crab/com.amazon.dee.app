package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class GradientBackground implements GradientBackgroundModel {
    private final int color;
    private final Drawable drawable;

    /* loaded from: classes11.dex */
    public static final class Builder {
        int color;
        Drawable drawable;

        public GradientBackground build() {
            return new GradientBackground(this);
        }

        public Builder color(int i) {
            this.color = i;
            return this;
        }

        public Builder drawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }
    }

    GradientBackground(Builder builder) {
        this.drawable = builder.drawable;
        this.color = builder.color;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GradientBackground.class != obj.getClass()) {
            return false;
        }
        GradientBackground gradientBackground = (GradientBackground) obj;
        Drawable drawable = this.drawable;
        if (drawable == null ? gradientBackground.drawable != null : !drawable.equals(gradientBackground.drawable)) {
            return false;
        }
        return this.color == gradientBackground.color;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientBackgroundModel
    @ColorInt
    public int getColor() {
        return this.color;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientBackgroundModel
    public Drawable getDrawable() {
        return this.drawable;
    }

    public int hashCode() {
        Drawable drawable = this.drawable;
        return ((JfifUtil.MARKER_EOI + (drawable != null ? drawable.hashCode() : 0)) * 31) + this.color;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GradientBackground{drawable=");
        outline107.append(this.drawable);
        outline107.append(", color=");
        return GeneratedOutlineSupport1.outline85(outline107, this.color, JsonReaderKt.END_OBJ);
    }
}
