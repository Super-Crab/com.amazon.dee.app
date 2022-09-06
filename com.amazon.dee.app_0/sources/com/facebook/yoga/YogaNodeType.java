package com.facebook.yoga;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public enum YogaNodeType {
    DEFAULT(0),
    TEXT(1);
    
    private final int mIntValue;

    YogaNodeType(int i) {
        this.mIntValue = i;
    }

    public static YogaNodeType fromInt(int i) {
        if (i != 0) {
            if (i == 1) {
                return TEXT;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown enum value: ", i));
        }
        return DEFAULT;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
