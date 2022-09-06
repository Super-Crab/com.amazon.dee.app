package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCMediaType {
    private final String swigName;
    private final int swigValue;
    public static final RTCMediaType RTC_AUDIO = new RTCMediaType("RTC_AUDIO");
    public static final RTCMediaType RTC_VIDEO = new RTCMediaType("RTC_VIDEO");
    public static final RTCMediaType RTC_DATA = new RTCMediaType("RTC_DATA");
    public static final RTCMediaType UNKNOWN_MEDIA_TYPE = new RTCMediaType("UNKNOWN_MEDIA_TYPE");
    private static RTCMediaType[] swigValues = {RTC_AUDIO, RTC_VIDEO, RTC_DATA, UNKNOWN_MEDIA_TYPE};
    private static int swigNext = 0;

    private RTCMediaType(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCMediaType swigToEnum(int i) {
        RTCMediaType[] rTCMediaTypeArr = swigValues;
        if (i >= rTCMediaTypeArr.length || i < 0 || rTCMediaTypeArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCMediaType[] rTCMediaTypeArr2 = swigValues;
                if (i2 < rTCMediaTypeArr2.length) {
                    if (rTCMediaTypeArr2[i2].swigValue == i) {
                        return rTCMediaTypeArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCMediaType.class, " with value ", i));
                }
            }
        } else {
            return rTCMediaTypeArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCMediaType(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCMediaType(String str, RTCMediaType rTCMediaType) {
        this.swigName = str;
        this.swigValue = rTCMediaType.swigValue;
        swigNext = this.swigValue + 1;
    }
}
