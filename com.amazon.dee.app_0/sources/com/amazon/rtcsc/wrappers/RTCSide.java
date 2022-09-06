package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCSide {
    private final String swigName;
    private final int swigValue;
    public static final RTCSide RTC_LOCAL = new RTCSide("RTC_LOCAL");
    public static final RTCSide RTC_REMOTE = new RTCSide("RTC_REMOTE");
    public static final RTCSide UNKNOWN_SIDE = new RTCSide("UNKNOWN_SIDE");
    private static RTCSide[] swigValues = {RTC_LOCAL, RTC_REMOTE, UNKNOWN_SIDE};
    private static int swigNext = 0;

    private RTCSide(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCSide swigToEnum(int i) {
        RTCSide[] rTCSideArr = swigValues;
        if (i >= rTCSideArr.length || i < 0 || rTCSideArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCSide[] rTCSideArr2 = swigValues;
                if (i2 < rTCSideArr2.length) {
                    if (rTCSideArr2[i2].swigValue == i) {
                        return rTCSideArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCSide.class, " with value ", i));
                }
            }
        } else {
            return rTCSideArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCSide(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCSide(String str, RTCSide rTCSide) {
        this.swigName = str;
        this.swigValue = rTCSide.swigValue;
        swigNext = this.swigValue + 1;
    }
}
