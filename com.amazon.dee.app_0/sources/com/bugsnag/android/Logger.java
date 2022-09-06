package com.bugsnag.android;

import android.util.Log;
/* loaded from: classes.dex */
final class Logger {
    private static final String LOG_TAG = "Bugsnag";
    private static volatile boolean enabled = true;

    private Logger() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getEnabled() {
        return enabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void info(String str) {
        if (enabled) {
            Log.i(LOG_TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setEnabled(boolean z) {
        enabled = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void warn(String str) {
        if (enabled) {
            Log.w(LOG_TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void warn(String str, Throwable th) {
        if (enabled) {
            Log.w(LOG_TAG, str, th);
        }
    }
}
