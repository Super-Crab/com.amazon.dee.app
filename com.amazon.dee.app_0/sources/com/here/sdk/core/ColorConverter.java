package com.here.sdk.core;
/* loaded from: classes3.dex */
final class ColorConverter {
    ColorConverter() {
    }

    static Color convertFromInternal(ColorInternal colorInternal) {
        return Color.valueOf(colorInternal.r, colorInternal.g, colorInternal.b, colorInternal.a);
    }

    static ColorInternal convertToInternal(Color color) {
        return new ColorInternal(color.red(), color.green(), color.blue(), color.alpha());
    }
}
