package com.amazon.comms.log;

import android.os.Build;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
/* loaded from: classes11.dex */
public class CommsLogger {
    private static final String BUILD_TYPE_USER = "user";
    private static final String BUILD_TYPE_USERDEBUG = "userdebug";
    private static final int MAX_TAG_LENGTH = 23;
    private static boolean userBuild = false;
    private static boolean userDebugBuild = false;
    private final String tag;

    /* loaded from: classes11.dex */
    public interface CommsLoggerFactory {
        CommsLogger getLogger(Object obj);
    }

    /* loaded from: classes11.dex */
    public static class CommsLoggerFactoryImpl implements CommsLoggerFactory {
        @Override // com.amazon.comms.log.CommsLogger.CommsLoggerFactory
        public CommsLogger getLogger(Object obj) {
            return new CommsLogger(CommsLogger.generateTag("", obj));
        }
    }

    static {
        String str = Build.TYPE;
        if (str != null) {
            userBuild = str.equals(BUILD_TYPE_USER);
            userDebugBuild = Build.TYPE.equals(BUILD_TYPE_USERDEBUG);
        }
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
                    if (cls.isAnonymousClass() && cls.getEnclosingClass() != null) {
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

    public static CommsLogger getLogger(String str, Object obj) {
        return new CommsLogger(generateTag(str, obj));
    }

    public static boolean isDebugBuild() {
        return !userBuild;
    }

    public static boolean isUserDebugBuild() {
        return userDebugBuild;
    }

    public void d(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Debug)) {
            String.format(str, objArr);
        }
    }

    public void ds(String str) {
        d(str);
    }

    public void e(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, String.format(str, objArr));
        }
    }

    public String getTag() {
        return this.tag;
    }

    public void i(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Info)) {
            Log.i(this.tag, String.format(str, objArr));
        }
    }

    public boolean isLoggable(LogLevel logLevel) {
        if (isUserDebugBuild()) {
            return true;
        }
        return Log.isLoggable(this.tag, logLevel.value);
    }

    public String sensitive(String str) {
        return isLoggable(LogLevel.Debug) ? str : "*****";
    }

    public String sensitiveCallId(String str) {
        return str;
    }

    public void v(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Verbose)) {
            String.format(str, objArr);
        }
    }

    public void w(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Warning)) {
            Log.w(this.tag, String.format(str, objArr));
        }
    }

    private CommsLogger(String str) {
        this.tag = str;
    }

    public static CommsLogger getLogger(Object obj) {
        return new CommsLogger(generateTag("", obj));
    }

    public void d(String str) {
        isLoggable(LogLevel.Debug);
    }

    public void e(String str) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, str);
        }
    }

    public void i(String str) {
        if (isLoggable(LogLevel.Info)) {
            Log.i(this.tag, str);
        }
    }

    public void v(String str) {
        isLoggable(LogLevel.Verbose);
    }

    public void w(String str) {
        if (isLoggable(LogLevel.Warning)) {
            Log.w(this.tag, str);
        }
    }

    public void d(String str, Throwable th) {
        isLoggable(LogLevel.Debug);
    }

    public void v(String str, Throwable th) {
        isLoggable(LogLevel.Verbose);
    }

    public void e(String str, Throwable th) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, str, th);
        }
    }

    public void i(String str, Throwable th) {
        if (isLoggable(LogLevel.Info)) {
            Log.i(this.tag, str, th);
        }
    }

    public void w(String str, Throwable th) {
        if (isLoggable(LogLevel.Warning)) {
            Log.w(this.tag, str, th);
        }
    }
}
