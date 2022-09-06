package com.amazon.tarazed.core.logging;

import android.util.Log;
import com.amazon.tarazed.core.TarazedBuildConfig;
import com.facebook.common.callercontext.ContextChain;
import java.util.logging.Level;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedBaseLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eR\u0012\u0010\u0003\u001a\u00020\u0004X \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedBaseLogger;", "", "()V", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "getAppender$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "d", "", "tag", "", "msg", "e", "tr", "", ContextChain.TAG_INFRA, "v", "w", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class TarazedBaseLogger {
    public final void d(@NotNull String tag, @NotNull String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (TarazedBuildConfig.INSTANCE.isDebugBuild()) {
            TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
            Level level = Level.FINE;
            Intrinsics.checkExpressionValueIsNotNull(level, "Level.FINE");
            TarazedLogFileAppender.appendLog$default(appender$TarazedMobileCore_release, level, tag, msg, null, 8, null);
        }
    }

    public final void e(@NotNull String tag, @NotNull String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Log.e(tag, msg);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.SEVERE;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.SEVERE");
        TarazedLogFileAppender.appendLog$default(appender$TarazedMobileCore_release, level, tag, msg, null, 8, null);
    }

    @NotNull
    public abstract TarazedLogFileAppender getAppender$TarazedMobileCore_release();

    public final void i(@NotNull String tag, @NotNull String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Log.i(tag, msg);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.INFO;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.INFO");
        TarazedLogFileAppender.appendLog$default(appender$TarazedMobileCore_release, level, tag, msg, null, 8, null);
    }

    public final void v(@NotNull String tag, @NotNull String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (TarazedBuildConfig.INSTANCE.isDebugBuild()) {
            TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
            Level level = Level.FINER;
            Intrinsics.checkExpressionValueIsNotNull(level, "Level.FINER");
            TarazedLogFileAppender.appendLog$default(appender$TarazedMobileCore_release, level, tag, msg, null, 8, null);
        }
    }

    public final void w(@NotNull String tag, @NotNull String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Log.w(tag, msg);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.WARNING;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.WARNING");
        TarazedLogFileAppender.appendLog$default(appender$TarazedMobileCore_release, level, tag, msg, null, 8, null);
    }

    public final void e(@NotNull String tag, @NotNull String msg, @NotNull Throwable tr) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(tr, "tr");
        Log.e(tag, msg, tr);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.SEVERE;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.SEVERE");
        appender$TarazedMobileCore_release.appendLog(level, tag, msg, tr);
    }

    public final void i(@NotNull String tag, @NotNull String msg, @NotNull Throwable tr) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(tr, "tr");
        Log.i(tag, msg, tr);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.INFO;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.INFO");
        appender$TarazedMobileCore_release.appendLog(level, tag, msg, tr);
    }

    public final void w(@NotNull String tag, @NotNull String msg, @NotNull Throwable tr) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(tr, "tr");
        Log.w(tag, msg, tr);
        TarazedLogFileAppender appender$TarazedMobileCore_release = getAppender$TarazedMobileCore_release();
        Level level = Level.WARNING;
        Intrinsics.checkExpressionValueIsNotNull(level, "Level.WARNING");
        appender$TarazedMobileCore_release.appendLog(level, tag, msg, tr);
    }
}
