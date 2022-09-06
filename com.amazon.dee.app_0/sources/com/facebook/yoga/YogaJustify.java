package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaJustify {
    FLEX_START(0),
    CENTER(1),
    FLEX_END(2),
    SPACE_BETWEEN(3),
    SPACE_AROUND(4),
    SPACE_EVENLY(5);
    
    private final int mIntValue;

    YogaJustify(int i) {
        this.mIntValue = i;
    }

    public static YogaJustify fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return CENTER;
            }
            if (i == 2) {
                return FLEX_END;
            }
            if (i == 3) {
                return SPACE_BETWEEN;
            }
            if (i == 4) {
                return SPACE_AROUND;
            }
            if (i == 5) {
                return SPACE_EVENLY;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return FLEX_START;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
