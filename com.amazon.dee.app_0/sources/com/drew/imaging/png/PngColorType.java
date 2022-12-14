package com.drew.imaging.png;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
/* loaded from: classes2.dex */
public enum PngColorType {
    Greyscale(0, "Greyscale", 1, 2, 4, 8, 16),
    TrueColor(2, "True Color", 8, 16),
    IndexedColor(3, "Indexed Color", 1, 2, 4, 8),
    GreyscaleWithAlpha(4, "Greyscale with Alpha", 8, 16),
    TrueColorWithAlpha(6, "True Color with Alpha", 8, 16);
    
    @NotNull
    private final int[] _allowedBitDepths;
    @NotNull
    private final String _description;
    private final int _numericValue;

    PngColorType(int i, @NotNull String str, @NotNull int... iArr) {
        this._numericValue = i;
        this._description = str;
        this._allowedBitDepths = iArr;
    }

    @Nullable
    public static PngColorType fromNumericValue(int i) {
        PngColorType[] pngColorTypeArr;
        for (PngColorType pngColorType : (PngColorType[]) PngColorType.class.getEnumConstants()) {
            if (pngColorType.getNumericValue() == i) {
                return pngColorType;
            }
        }
        return null;
    }

    @NotNull
    public int[] getAllowedBitDepths() {
        return this._allowedBitDepths;
    }

    @NotNull
    public String getDescription() {
        return this._description;
    }

    public int getNumericValue() {
        return this._numericValue;
    }
}
