package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.UnknownHostException;
import org.checkerframework.dataflow.qual.Pure;
/* loaded from: classes2.dex */
public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    @interface LogLevel {
    }

    private Log() {
    }

    @Pure
    private static String appendThrowableString(String str, @Nullable Throwable th) {
        String throwableString = getThrowableString(th);
        if (!TextUtils.isEmpty(throwableString)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "\n  ");
            outline113.append(throwableString.replace("\n", "\n  "));
            outline113.append('\n');
            return outline113.toString();
        }
        return str;
    }

    @Pure
    public static void d(String str, String str2) {
        int i = logLevel;
    }

    @Pure
    public static void e(String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    @Pure
    public static int getLogLevel() {
        return logLevel;
    }

    @Nullable
    @Pure
    public static String getThrowableString(@Nullable Throwable th) {
        if (th == null) {
            return null;
        }
        if (isCausedByUnknownHostException(th)) {
            return "UnknownHostException (no network)";
        }
        if (!logStackTraces) {
            return th.getMessage();
        }
        return android.util.Log.getStackTraceString(th).trim().replace(DeviceDatabaseUtils.DELIMITER, "    ");
    }

    @Pure
    public static void i(String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    @Pure
    private static boolean isCausedByUnknownHostException(@Nullable Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setLogStackTraces(boolean z) {
        logStackTraces = z;
    }

    @Pure
    public static void w(String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    @Pure
    public boolean getLogStackTraces() {
        return logStackTraces;
    }

    @Pure
    public static void d(String str, String str2, @Nullable Throwable th) {
        d(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void e(String str, String str2, @Nullable Throwable th) {
        e(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void i(String str, String str2, @Nullable Throwable th) {
        i(str, appendThrowableString(str2, th));
    }

    @Pure
    public static void w(String str, String str2, @Nullable Throwable th) {
        w(str, appendThrowableString(str2, th));
    }
}
