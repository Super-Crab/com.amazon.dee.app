package com.amazon.alexa.mode.util;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;
@SuppressLint({"NewApi"})
/* loaded from: classes9.dex */
public final class ModeUtil {
    private static final String DRIVE_MODE_DEBUG_PREFS = "DriveModeDebugPrefs";
    private static final String MAIN_MODE_ACTIVITY_NAME = "com.amazon.dee.app.ui.main.MainActivity";
    private static final String PACKAGE_NAME = "com.amazon.dee.app";
    private static final ComponentName sMainComponent = new ComponentName("com.amazon.dee.app", "com.amazon.dee.app.ui.main.MainActivity");

    private ModeUtil() {
        throw new IllegalStateException("No instances!");
    }

    public static ComponentName componentNameFromMode() {
        return sMainComponent;
    }

    private static boolean getPrefs(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, false);
    }

    public static boolean isDebugMode(Context context) {
        return getPrefs(context, DRIVE_MODE_DEBUG_PREFS);
    }

    public static boolean isDebuggable(Context context) {
        return (context == null || context.getApplicationContext() == null || (context.getApplicationInfo().flags & 2) == 0) ? false : true;
    }
}
