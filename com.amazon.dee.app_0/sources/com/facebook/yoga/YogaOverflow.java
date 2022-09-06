package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaOverflow {
    VISIBLE(0),
    HIDDEN(1),
    SCROLL(2);
    
    private final int mIntValue;

    YogaOverflow(int i) {
        this.mIntValue = i;
    }

    public static YogaOverflow fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return HIDDEN;
            }
            if (i == 2) {
                return SCROLL;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return VISIBLE;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
