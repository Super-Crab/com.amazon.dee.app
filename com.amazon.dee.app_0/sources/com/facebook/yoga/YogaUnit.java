package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaUnit {
    UNDEFINED(0),
    POINT(1),
    PERCENT(2),
    AUTO(3);
    
    private final int mIntValue;

    YogaUnit(int i) {
        this.mIntValue = i;
    }

    public static YogaUnit fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return POINT;
            }
            if (i == 2) {
                return PERCENT;
            }
            if (i == 3) {
                return AUTO;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return UNDEFINED;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
