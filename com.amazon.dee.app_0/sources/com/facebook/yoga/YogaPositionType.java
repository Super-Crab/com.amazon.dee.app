package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaPositionType {
    STATIC(0),
    RELATIVE(1),
    ABSOLUTE(2);
    
    private final int mIntValue;

    YogaPositionType(int i) {
        this.mIntValue = i;
    }

    public static YogaPositionType fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return RELATIVE;
            }
            if (i == 2) {
                return ABSOLUTE;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return STATIC;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
