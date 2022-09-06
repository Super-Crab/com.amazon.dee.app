package com.amazon.rtcmedia.util;

import android.os.Build;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class Logger {
    private static final String BUILD_TYPE_USER = "user";
    private static final String BUILD_TYPE_USERDEBUG = "userdebug";
    private static final int MAX_TAG_LENGTH = 23;
    private static boolean inTest = false;
    private static boolean userBuild = false;
    private static boolean userDebugBuild = false;
    private final String tag;

    /* loaded from: classes13.dex */
    public enum LogLevel {
        Error(6),
        Warning(5),
        Info(4),
        Debug(3),
        Verbose(2);
        
        int value;

        LogLevel(int i) {
            this.value = i;
        }
    }

    static {
        String str = Build.TYPE;
        if (str != null) {
            userBuild = str.equals(BUILD_TYPE_USER);
            userDebugBuild = Build.TYPE.equals(BUILD_TYPE_USERDEBUG);
            return;
        }
        inTest = true;
    }

    private Logger(String str) {
        this.tag = str;
    }

    static String generateTag(String str, Object obj) {
        String simpleName;
        if (str != null) {
            if (obj != null) {
                if (obj instanceof CharSequence) {
                    simpleName = obj.toString();
                    if (simpleName.equals("")) {
                        throw new IllegalArgumentException("tagObj cannot be an empty string.");
                    }
                } else {
                    Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
                    if (cls.isAnonymousClass()) {
                        simpleName = cls.getEnclosingClass().getSimpleName();
                    } else {
                        simpleName = cls.getSimpleName();
                    }
                }
                String outline72 = GeneratedOutlineSupport1.outline72(str, simpleName);
                return outline72.length() > 23 ? outline72.substring(0, 23) : outline72;
            }
            throw new IllegalArgumentException("tagObj must be non-null.");
        }
        throw new IllegalArgumentException("prefix must be non-null.");
    }

    public static Logger getLogger(Object obj) {
        return new Logger(generateTag("", obj));
    }

    public static boolean isUserDebugBuild() {
        return userDebugBuild;
    }

    public void d(String str) {
        if (!inTest) {
            isLoggable(LogLevel.Debug);
        }
    }

    public void ds(String str) {
        d(str);
    }

    public void e(String str) {
        if (inTest || !isLoggable(LogLevel.Error)) {
            return;
        }
        Log.e(this.tag, str);
    }

    public String getTag() {
        return this.tag;
    }

    public void i(String str) {
        if (inTest || !isLoggable(LogLevel.Info)) {
            return;
        }
        Log.i(this.tag, str);
    }

    public boolean isLoggable(LogLevel logLevel) {
        if (isUserDebugBuild()) {
            return true;
        }
        return Log.isLoggable(this.tag, logLevel.value);
    }

    public void v(String str) {
        if (!inTest) {
            isLoggable(LogLevel.Verbose);
        }
    }

    public void w(String str) {
        if (inTest || !isLoggable(LogLevel.Warning)) {
            return;
        }
        Log.w(this.tag, str);
    }

    public void d(String str, Throwable th) {
        if (!inTest) {
            isLoggable(LogLevel.Debug);
        }
    }

    public void v(String str, Throwable th) {
        if (!inTest) {
            isLoggable(LogLevel.Verbose);
        }
    }

    public void e(String str, Throwable th) {
        if (inTest || !isLoggable(LogLevel.Error)) {
            return;
        }
        Log.e(this.tag, str, th);
    }

    public void i(String str, Throwable th) {
        if (inTest || !isLoggable(LogLevel.Info)) {
            return;
        }
        Log.i(this.tag, str, th);
    }

    public void w(String str, Throwable th) {
        if (inTest || !isLoggable(LogLevel.Warning)) {
            return;
        }
        Log.w(this.tag, str, th);
    }
}
