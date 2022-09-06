package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaDisplay {
    FLEX(0),
    NONE(1);
    
    private final int mIntValue;

    YogaDisplay(int i) {
        this.mIntValue = i;
    }

    public static YogaDisplay fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return NONE;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return FLEX;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
