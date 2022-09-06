package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaFlexDirection {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);
    
    private final int mIntValue;

    YogaFlexDirection(int i) {
        this.mIntValue = i;
    }

    public static YogaFlexDirection fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return COLUMN_REVERSE;
            }
            if (i == 2) {
                return ROW;
            }
            if (i == 3) {
                return ROW_REVERSE;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return COLUMN;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
