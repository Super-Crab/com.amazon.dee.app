package com.amazon.whisperjoin.deviceprovisioningservice.util;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
/* loaded from: classes13.dex */
public final class DeviceSerialProvider {
    private static final String TAG = "DeviceSerialProvider";
    private static final String UNKNOWN_ANDROID_DSN = "unknown";

    private DeviceSerialProvider() {
    }

    public static String getDeviceSerial() {
        WJLog.d(TAG, "Using unknown in place of device serial number");
        return "unknown";
    }
}
