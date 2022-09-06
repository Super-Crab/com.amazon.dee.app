package com.amazon.alexa.presence.logging;

import android.util.Log;
import com.amazon.alexa.presence.BuildConfig;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.slf4j.helpers.MarkerIgnoringBase;
/* loaded from: classes9.dex */
public class PresenceSlf4jAndroidLogger extends MarkerIgnoringBase {
    private static final int DEBUG = 0;
    private static final int ERROR = 3;
    private static final int INFO = 1;
    private static final int VERBOSE = -1;
    private static final int WARN = 2;
    private final String name;

    public PresenceSlf4jAndroidLogger(String str) {
        this.name = str;
    }

    private String combine(String str, Object... objArr) {
        return String.format(ParameterizedMessage.format(str, objArr), objArr);
    }

    private void doLog(int i, String str) {
        if (!this.name.contains(BuildConfig.APPLICATION_ID) || i == -1 || i == 0) {
            return;
        }
        if (i == 1) {
            Log.i(this.name, str);
        } else if (i == 2) {
            Log.w(this.name, str);
        } else if (i != 3) {
        } else {
            Log.e(this.name, str);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        doLog(0, str);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        doLog(3, str);
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, org.slf4j.helpers.NamedLoggerBase, org.slf4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        doLog(1, str);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return false;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        doLog(-1, str);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        doLog(2, str);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        doLog(0, combine(str, obj));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        doLog(3, combine(str, obj));
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        doLog(1, combine(str, obj));
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        doLog(-1, combine(str, obj));
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        doLog(2, combine(str, obj));
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        doLog(0, combine(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        doLog(3, combine(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        doLog(1, combine(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        doLog(-1, combine(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        doLog(2, combine(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        doLog(0, combine(str, objArr));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        doLog(3, combine(str, objArr));
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        doLog(1, combine(str, objArr));
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        doLog(-1, combine(str, objArr));
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        doLog(2, combine(str, objArr));
    }

    private void doLog(int i, String str, Throwable th) {
        if (i == -1 || i == 0) {
            return;
        }
        if (i == 1) {
            Log.i(this.name, str, th);
        } else if (i == 2) {
            Log.w(this.name, str, th);
        } else if (i != 3) {
        } else {
            Log.e(this.name, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        doLog(0, str, th);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        doLog(3, str, th);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        doLog(1, str, th);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        doLog(-1, str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        doLog(2, str, th);
    }
}
