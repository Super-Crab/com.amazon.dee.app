package com.facebook.common.logging;

import com.facebook.infer.annotation.NullsafeStrict;
@NullsafeStrict
/* loaded from: classes2.dex */
public interface LoggingDelegate {
    void d(String tag, String msg);

    void d(String tag, String msg, Throwable tr);

    void e(String tag, String msg);

    void e(String tag, String msg, Throwable tr);

    int getMinimumLoggingLevel();

    void i(String tag, String msg);

    void i(String tag, String msg, Throwable tr);

    boolean isLoggable(int level);

    void log(int priority, String tag, String msg);

    void setMinimumLoggingLevel(int level);

    void v(String tag, String msg);

    void v(String tag, String msg, Throwable tr);

    void w(String tag, String msg);

    void w(String tag, String msg, Throwable tr);

    void wtf(String tag, String msg);

    void wtf(String tag, String msg, Throwable tr);
}
