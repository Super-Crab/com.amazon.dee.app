package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaDimension {
    WIDTH(0),
    HEIGHT(1);
    
    private final int mIntValue;

    YogaDimension(int i) {
        this.mIntValue = i;
    }

    public static YogaDimension fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return HEIGHT;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return WIDTH;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
