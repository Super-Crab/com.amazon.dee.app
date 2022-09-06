package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    
    private final int mIntValue;

    YogaDirection(int i) {
        this.mIntValue = i;
    }

    public static YogaDirection fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return LTR;
            }
            if (i == 2) {
                return RTL;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return INHERIT;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
