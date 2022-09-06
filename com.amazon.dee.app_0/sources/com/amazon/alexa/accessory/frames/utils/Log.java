package com.amazon.alexa.accessory.frames.utils;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.StringUtils;
import java.util.Locale;
/* loaded from: classes.dex */
public final class Log {
    private static final String PKG_TAG = "NotificationPublisher";

    private Log() {
    }

    public static void d(String str, String str2) {
    }

    public static void d(String str, String str2, Object... objArr) {
        d(str, StringUtils.safeFormat(Locale.US, str2, objArr));
    }

    public static void e(String str, String str2) {
        Logger.errorWithTag(PKG_TAG, "%s: %s", str, str2);
    }

    public static void i(String str, String str2) {
        Logger.infoWithTag(PKG_TAG, "%s: %s", str, str2);
    }

    public static void w(String str, String str2) {
        Logger.warnWithTag(PKG_TAG, "%s: %s", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Logger.errorWithTag(PKG_TAG, "%s: %s", th, str, str2);
    }

    public static void i(String str, String str2, Object... objArr) {
        i(str, StringUtils.safeFormat(Locale.US, str2, objArr));
    }

    public static void w(String str, String str2, Throwable th) {
        Logger.warnWithTag(PKG_TAG, "%s: %s - %s", str, str2, th);
    }

    public static void e(String str, String str2, Object... objArr) {
        e(str, StringUtils.safeFormat(Locale.US, str2, objArr));
    }

    public static void w(String str, String str2, Object... objArr) {
        w(str, StringUtils.safeFormat(Locale.US, str2, objArr));
    }

    public static void e(String str, String str2, Throwable th, Object... objArr) {
        e(str, StringUtils.safeFormat(Locale.US, str2, objArr), th);
    }

    public static void w(String str, String str2, Throwable th, Object... objArr) {
        w(str, StringUtils.safeFormat(Locale.US, str2, objArr), th);
    }
}
