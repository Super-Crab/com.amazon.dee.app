package com.amazon.alexa.accessorykit.utils;

import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsDevice;
/* loaded from: classes6.dex */
public final class RedactionUtil {
    public static final String REDACTED = "[REDACTED]";

    private RedactionUtil() {
    }

    public static AccessoryMobilyticsDevice redact(AccessoryMobilyticsDevice accessoryMobilyticsDevice) {
        if (accessoryMobilyticsDevice == null) {
            return null;
        }
        return new AccessoryMobilyticsDevice(accessoryMobilyticsDevice.deviceType(), "[REDACTED]");
    }
}
