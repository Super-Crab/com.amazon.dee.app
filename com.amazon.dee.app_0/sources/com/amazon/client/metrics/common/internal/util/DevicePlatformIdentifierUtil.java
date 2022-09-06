package com.amazon.client.metrics.common.internal.util;

import android.os.Build;
/* loaded from: classes11.dex */
public class DevicePlatformIdentifierUtil {
    private static final String BUILD_MANUFACTURER_VALUE_AMAZON = "Amazon";
    private static DevicePlatformIdentifierUtil sDevicePlatformIdentifierUtil;
    private final boolean mIsDevicePlatformFireOS = "Amazon".equalsIgnoreCase(Build.MANUFACTURER);

    private DevicePlatformIdentifierUtil() {
    }

    public static DevicePlatformIdentifierUtil getInstance() {
        if (sDevicePlatformIdentifierUtil == null) {
            sDevicePlatformIdentifierUtil = new DevicePlatformIdentifierUtil();
        }
        return sDevicePlatformIdentifierUtil;
    }

    public boolean isDevicePlatformAndroid() {
        return !this.mIsDevicePlatformFireOS;
    }

    public boolean isDevicePlatformFireOS() {
        return this.mIsDevicePlatformFireOS;
    }
}
