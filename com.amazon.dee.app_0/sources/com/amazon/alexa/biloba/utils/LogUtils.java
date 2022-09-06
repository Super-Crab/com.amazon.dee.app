package com.amazon.alexa.biloba.utils;

import android.util.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
/* loaded from: classes6.dex */
public final class LogUtils {
    private static final String LOG_PREFIX = "CareHub";

    private LogUtils() {
    }

    public static void d(String str, String str2) {
        String str3 = str + RealTimeTextConstants.COLON_SPACE + str2;
    }

    public static void e(String str, String str2) {
        Log.e(LOG_PREFIX, str + RealTimeTextConstants.COLON_SPACE + str2);
    }

    public static void i(String str, String str2) {
        Log.i(LOG_PREFIX, str + RealTimeTextConstants.COLON_SPACE + str2);
    }

    public static void v(String str, String str2) {
        String str3 = str + RealTimeTextConstants.COLON_SPACE + str2;
    }

    public static void w(String str, String str2) {
        Log.w(LOG_PREFIX, str + RealTimeTextConstants.COLON_SPACE + str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(LOG_PREFIX, str + RealTimeTextConstants.COLON_SPACE + str2, th);
    }
}
