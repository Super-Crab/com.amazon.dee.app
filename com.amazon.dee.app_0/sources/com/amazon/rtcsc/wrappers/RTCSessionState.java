package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCSessionState {
    private final String swigName;
    private final int swigValue;
    public static final RTCSessionState SESSION_STATE_UNKNOWN = new RTCSessionState("SESSION_STATE_UNKNOWN");
    public static final RTCSessionState SESSION_PREPARING = new RTCSessionState("SESSION_PREPARING");
    public static final RTCSessionState SESSION_ACTIVE = new RTCSessionState("SESSION_ACTIVE");
    public static final RTCSessionState SESSION_PAUSED = new RTCSessionState("SESSION_PAUSED");
    public static final RTCSessionState SESSION_FINISHING = new RTCSessionState("SESSION_FINISHING");
    private static RTCSessionState[] swigValues = {SESSION_STATE_UNKNOWN, SESSION_PREPARING, SESSION_ACTIVE, SESSION_PAUSED, SESSION_FINISHING};
    private static int swigNext = 0;

    private RTCSessionState(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCSessionState swigToEnum(int i) {
        RTCSessionState[] rTCSessionStateArr = swigValues;
        if (i >= rTCSessionStateArr.length || i < 0 || rTCSessionStateArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCSessionState[] rTCSessionStateArr2 = swigValues;
                if (i2 < rTCSessionStateArr2.length) {
                    if (rTCSessionStateArr2[i2].swigValue == i) {
                        return rTCSessionStateArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCSessionState.class, " with value ", i));
                }
            }
        } else {
            return rTCSessionStateArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCSessionState(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCSessionState(String str, RTCSessionState rTCSessionState) {
        this.swigName = str;
        this.swigValue = rTCSessionState.swigValue;
        swigNext = this.swigValue + 1;
    }
}
