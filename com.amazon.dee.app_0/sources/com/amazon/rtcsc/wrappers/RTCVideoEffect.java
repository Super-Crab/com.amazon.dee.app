package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCVideoEffect {
    private final String swigName;
    private final int swigValue;
    public static final RTCVideoEffect NO_EFFECT = new RTCVideoEffect("NO_EFFECT");
    public static final RTCVideoEffect FROST_EFFECT = new RTCVideoEffect("FROST_EFFECT");
    public static final RTCVideoEffect VIDEO_EFFECT_UNKNOWN = new RTCVideoEffect("VIDEO_EFFECT_UNKNOWN");
    private static RTCVideoEffect[] swigValues = {NO_EFFECT, FROST_EFFECT, VIDEO_EFFECT_UNKNOWN};
    private static int swigNext = 0;

    private RTCVideoEffect(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCVideoEffect swigToEnum(int i) {
        RTCVideoEffect[] rTCVideoEffectArr = swigValues;
        if (i >= rTCVideoEffectArr.length || i < 0 || rTCVideoEffectArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCVideoEffect[] rTCVideoEffectArr2 = swigValues;
                if (i2 < rTCVideoEffectArr2.length) {
                    if (rTCVideoEffectArr2[i2].swigValue == i) {
                        return rTCVideoEffectArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCVideoEffect.class, " with value ", i));
                }
            }
        } else {
            return rTCVideoEffectArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCVideoEffect(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCVideoEffect(String str, RTCVideoEffect rTCVideoEffect) {
        this.swigName = str;
        this.swigValue = rTCVideoEffect.swigValue;
        swigNext = this.swigValue + 1;
    }
}
