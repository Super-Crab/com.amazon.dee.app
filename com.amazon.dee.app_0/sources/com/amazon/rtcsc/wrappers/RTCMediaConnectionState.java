package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCMediaConnectionState {
    private final String swigName;
    private final int swigValue;
    public static final RTCMediaConnectionState MEDIA_CONNECTING = new RTCMediaConnectionState("MEDIA_CONNECTING");
    public static final RTCMediaConnectionState MEDIA_CONNECTED = new RTCMediaConnectionState("MEDIA_CONNECTED");
    public static final RTCMediaConnectionState MEDIA_DISCONNECTED = new RTCMediaConnectionState("MEDIA_DISCONNECTED");
    public static final RTCMediaConnectionState UNKNOWN_MEDIA_CONNECTION_STATE = new RTCMediaConnectionState("UNKNOWN_MEDIA_CONNECTION_STATE");
    private static RTCMediaConnectionState[] swigValues = {MEDIA_CONNECTING, MEDIA_CONNECTED, MEDIA_DISCONNECTED, UNKNOWN_MEDIA_CONNECTION_STATE};
    private static int swigNext = 0;

    private RTCMediaConnectionState(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCMediaConnectionState swigToEnum(int i) {
        RTCMediaConnectionState[] rTCMediaConnectionStateArr = swigValues;
        if (i >= rTCMediaConnectionStateArr.length || i < 0 || rTCMediaConnectionStateArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCMediaConnectionState[] rTCMediaConnectionStateArr2 = swigValues;
                if (i2 < rTCMediaConnectionStateArr2.length) {
                    if (rTCMediaConnectionStateArr2[i2].swigValue == i) {
                        return rTCMediaConnectionStateArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCMediaConnectionState.class, " with value ", i));
                }
            }
        } else {
            return rTCMediaConnectionStateArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCMediaConnectionState(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCMediaConnectionState(String str, RTCMediaConnectionState rTCMediaConnectionState) {
        this.swigName = str;
        this.swigValue = rTCMediaConnectionState.swigValue;
        swigNext = this.swigValue + 1;
    }
}
