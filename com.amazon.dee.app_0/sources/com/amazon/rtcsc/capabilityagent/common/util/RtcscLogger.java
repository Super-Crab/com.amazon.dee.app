package com.amazon.rtcsc.capabilityagent.common.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RtcscLogger {
    public static final int ASSERT = 7;
    private static final String BUILD_TYPE_USER = "user";
    private static final String BUILD_TYPE_USERDEBUG = "userdebug";
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final String TAG = "[RTCSC-CA]";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static boolean userBuild = false;
    private static boolean userDebugBuild = false;

    static {
        String str = Build.TYPE;
        if (str != null) {
            userBuild = str.equals(BUILD_TYPE_USER);
            userDebugBuild = Build.TYPE.equals(BUILD_TYPE_USERDEBUG);
        }
    }

    private RtcscLogger() {
    }

    @SuppressLint({"LogTagMismatch"})
    public static void d(String str, String str2, Object... objArr) {
        if (isLoggable(3)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            outline113.toString();
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void e(String str, String str2, Object... objArr) {
        if (isLoggable(6)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.e(TAG, outline113.toString());
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void i(String str, String str2, Object... objArr) {
        if (isLoggable(4)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.i(TAG, outline113.toString());
        }
    }

    private static boolean isDebugBuild() {
        return !userBuild;
    }

    public static boolean isLoggable(int i) {
        if (isUserDebugBuild()) {
            return true;
        }
        return Log.isLoggable(TAG, i);
    }

    private static boolean isUserDebugBuild() {
        return userDebugBuild;
    }

    @SuppressLint({"LogTagMismatch"})
    public static void v(String str, String str2, Object... objArr) {
        if (isLoggable(2)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            outline113.toString();
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void w(String str, String str2, Object... objArr) {
        if (isLoggable(5)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.w(TAG, outline113.toString());
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void d(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(3)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            outline113.toString();
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void e(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(6)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.e(TAG, outline113.toString(), th);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void i(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(4)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.i(TAG, outline113.toString(), th);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void v(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(2)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            outline113.toString();
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void w(String str, Throwable th) {
        if (isLoggable(5)) {
            Log.w(TAG, str, th);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void w(String str, Throwable th, String str2, Object... objArr) {
        if (isLoggable(5)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(String.format(str2, objArr));
            Log.w(TAG, outline113.toString(), th);
        }
    }
}
