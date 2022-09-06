package com.amazon.rtcsc.wrappers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCSCAppDisconnectCode {
    private final String swigName;
    private final int swigValue;
    public static final RTCSCAppDisconnectCode USER_TERMINATED_SESSION = new RTCSCAppDisconnectCode("USER_TERMINATED_SESSION");
    public static final RTCSCAppDisconnectCode USER_DECLINED_SESSION = new RTCSCAppDisconnectCode("USER_DECLINED_SESSION");
    public static final RTCSCAppDisconnectCode APPCLIENT_NOT_FOUND = new RTCSCAppDisconnectCode("APPCLIENT_NOT_FOUND");
    public static final RTCSCAppDisconnectCode APPCLIENT_START_FAILED = new RTCSCAppDisconnectCode("APPCLIENT_START_FAILED");
    public static final RTCSCAppDisconnectCode PERMISSION_CHECKS_FAILED = new RTCSCAppDisconnectCode("PERMISSION_CHECKS_FAILED");
    public static final RTCSCAppDisconnectCode DEVICE_UNAVAILABLE = new RTCSCAppDisconnectCode("DEVICE_UNAVAILABLE");
    public static final RTCSCAppDisconnectCode CAMERA_UNAVAILABLE = new RTCSCAppDisconnectCode("CAMERA_UNAVAILABLE");
    public static final RTCSCAppDisconnectCode HIGHER_PRIORITY_SESSION_ONGOING = new RTCSCAppDisconnectCode("HIGHER_PRIORITY_SESSION_ONGOING");
    public static final RTCSCAppDisconnectCode HIGHER_PRIORITY_SESSION_INTERRUPTED = new RTCSCAppDisconnectCode("HIGHER_PRIORITY_SESSION_INTERRUPTED");
    public static final RTCSCAppDisconnectCode SESSION_DOMAIN_ERROR = new RTCSCAppDisconnectCode("SESSION_DOMAIN_ERROR");
    private static RTCSCAppDisconnectCode[] swigValues = {USER_TERMINATED_SESSION, USER_DECLINED_SESSION, APPCLIENT_NOT_FOUND, APPCLIENT_START_FAILED, PERMISSION_CHECKS_FAILED, DEVICE_UNAVAILABLE, CAMERA_UNAVAILABLE, HIGHER_PRIORITY_SESSION_ONGOING, HIGHER_PRIORITY_SESSION_INTERRUPTED, SESSION_DOMAIN_ERROR};
    private static int swigNext = 0;

    private RTCSCAppDisconnectCode(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCSCAppDisconnectCode swigToEnum(int i) {
        RTCSCAppDisconnectCode[] rTCSCAppDisconnectCodeArr = swigValues;
        if (i >= rTCSCAppDisconnectCodeArr.length || i < 0 || rTCSCAppDisconnectCodeArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCSCAppDisconnectCode[] rTCSCAppDisconnectCodeArr2 = swigValues;
                if (i2 < rTCSCAppDisconnectCodeArr2.length) {
                    if (rTCSCAppDisconnectCodeArr2[i2].swigValue == i) {
                        return rTCSCAppDisconnectCodeArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCSCAppDisconnectCode.class, " with value ", i));
                }
            }
        } else {
            return rTCSCAppDisconnectCodeArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCSCAppDisconnectCode(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCSCAppDisconnectCode(String str, RTCSCAppDisconnectCode rTCSCAppDisconnectCode) {
        this.swigName = str;
        this.swigValue = rTCSCAppDisconnectCode.swigValue;
        swigNext = this.swigValue + 1;
    }
}
