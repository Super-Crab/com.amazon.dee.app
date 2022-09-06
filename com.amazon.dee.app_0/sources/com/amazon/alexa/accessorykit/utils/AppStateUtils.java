package com.amazon.alexa.accessorykit.utils;

import android.app.ActivityManager;
/* loaded from: classes6.dex */
public final class AppStateUtils {
    private AppStateUtils() {
    }

    public static boolean isAppInForeground() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        int i = runningAppProcessInfo.importance;
        return i == 100 || i == 200;
    }
}
