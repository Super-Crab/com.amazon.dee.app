package com.here.sdk.core;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
/* loaded from: classes3.dex */
public final class Color {
    @Deprecated
    public final short alpha;
    @Deprecated
    public final short blue;
    private final float falpha;
    private final float fblue;
    private final float fgreen;
    private final float fred;
    @Deprecated
    public final short green;
    @Deprecated
    public final short red;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum ConstructorType {
        Private
    }

    private Color(ConstructorType constructorType, float f, float f2, float f3, float f4) {
        this.fred = f;
        this.fgreen = f2;
        this.fblue = f3;
        this.falpha = f4;
        this.red = colorComponentFromFloat(f);
        this.green = colorComponentFromFloat(f2);
        this.blue = colorComponentFromFloat(f3);
        this.alpha = colorComponentFromFloat(f4);
    }

    @Deprecated
    public Color(short s, short s2, short s3) {
        this(s, s2, s3, (short) 255);
    }

    @Deprecated
    public Color(short s, short s2, short s3, short s4) {
        this.red = s;
        this.green = s2;
        this.blue = s3;
        this.alpha = s4;
        this.fred = colorComponentToFloat(s);
        this.fgreen = colorComponentToFloat(s2);
        this.fblue = colorComponentToFloat(s3);
        this.falpha = colorComponentToFloat(s4);
    }

    private static short colorComponentFromFloat(float f) {
        return (short) Math.max(0, Math.min(255, Math.round(f * 255.0f)));
    }

    private static float colorComponentToFloat(short s) {
        return Math.max(0.0f, Math.min(1.0f, s / 255.0f));
    }

    @NonNull
    public static Color valueOf(float f, float f2, float f3) {
        return new Color(ConstructorType.Private, f, f2, f3, 1.0f);
    }

    @NonNull
    public static Color valueOf(float f, float f2, float f3, float f4) {
        return new Color(ConstructorType.Private, f, f2, f3, f4);
    }

    @NonNull
    public static Color valueOf(@ColorInt int i) {
        return valueOf(((i >> 16) & 255) / 255.0f, ((i >> 8) & 255) / 255.0f, (i & 255) / 255.0f, ((i >> 24) & 255) / 255.0f);
    }

    public float alpha() {
        return this.falpha;
    }

    public float blue() {
        return this.fblue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return false;
        }
        Color color = (Color) obj;
        return this.red == color.red && this.green == color.green && this.blue == color.blue && this.alpha == color.alpha;
    }

    public float green() {
        return this.fgreen;
    }

    public int hashCode() {
        return ((((((this.red + 217) * 31) + this.green) * 31) + this.blue) * 31) + this.alpha;
    }

    public float red() {
        return this.fred;
    }

    @ColorInt
    public int toArgb() {
        return (((int) ((alpha() * 255.0f) + 0.5f)) << 24) | (((int) ((red() * 255.0f) + 0.5f)) << 16) | (((int) ((green() * 255.0f) + 0.5f)) << 8) | ((int) ((blue() * 255.0f) + 0.5f));
    }

    @NonNull
    public String toString() {
        return "Color(" + red() + ", " + green() + ", " + blue() + ", " + alpha() + ")";
    }
}
