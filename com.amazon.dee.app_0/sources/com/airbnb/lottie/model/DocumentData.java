package com.airbnb.lottie.model;

import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;
import com.android.tools.r8.GeneratedOutlineSupport1;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class DocumentData {
    public final double baselineShift;
    @ColorInt
    public final int color;
    public final String fontName;
    public final Justification justification;
    public final double lineHeight;
    public final double size;
    @ColorInt
    public final int strokeColor;
    public final boolean strokeOverFill;
    public final double strokeWidth;
    public final String text;
    public final int tracking;

    /* loaded from: classes.dex */
    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, double d, Justification justification, int i, double d2, double d3, @ColorInt int i2, @ColorInt int i3, double d4, boolean z) {
        this.text = str;
        this.fontName = str2;
        this.size = d;
        this.justification = justification;
        this.tracking = i;
        this.lineHeight = d2;
        this.baselineShift = d3;
        this.color = i2;
        this.strokeColor = i3;
        this.strokeWidth = d4;
        this.strokeOverFill = z;
    }

    public int hashCode() {
        String str = this.fontName;
        int ordinal = this.justification.ordinal();
        long doubleToLongBits = Double.doubleToLongBits(this.lineHeight);
        return ((((((ordinal + (((int) (GeneratedOutlineSupport1.outline7(str, this.text.hashCode() * 31, 31) + this.size)) * 31)) * 31) + this.tracking) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.color;
    }
}
