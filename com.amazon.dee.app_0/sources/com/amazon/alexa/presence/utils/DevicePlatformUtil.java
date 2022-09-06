package com.amazon.alexa.presence.utils;

import android.os.Build;
/* loaded from: classes9.dex */
public final class DevicePlatformUtil {
    private DevicePlatformUtil() {
    }

    public static boolean isAndroidMOrLater() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isAndroidOreoOrLater() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
