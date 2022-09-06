package com.amazon.tarazed.core.logging;

import android.content.Context;
import com.amazon.alexa.accessorykit.ModelTransformer;
import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt__FileReadWriteKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedLogFileAppender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u000fH\u0002J\u001a\u0010\u001d\u001a\u00020\u001b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001b0\u001fJ\u0006\u0010 \u001a\u00020\u001bJ\b\u0010!\u001a\u00020\u001bH\u0002J\u0006\u0010\"\u001a\u00020\u001bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "", "context", "Landroid/content/Context;", "logFileName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "appenderChannel", "Lkotlinx/coroutines/channels/Channel;", "Ljava/util/logging/LogRecord;", "appenderJob", "Lkotlinx/coroutines/Job;", "logFile", "Ljava/io/File;", "logHandler", "Ljava/util/logging/Handler;", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "appendLog", ModelTransformer.KEY_BATTERY_LEVEL, "Ljava/util/logging/Level;", "tag", "msg", "thrown", "", "clearLogs", "", "createHandler", "forEachLine", "action", "Lkotlin/Function1;", "start", "startAppenderJob", "stop", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogFileAppender {
    public static final Companion Companion = new Companion(null);
    private static final int LOG_FILE_COUNT = 1;
    private static final int LOG_FILE_LIMIT_BYTES = 786432;
    private final Channel<LogRecord> appenderChannel;
    private Job appenderJob;
    private final File logFile;
    private Handler logHandler;
    private final Logger logger;

    /* compiled from: TarazedLogFileAppender.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender$Companion;", "", "()V", "LOG_FILE_COUNT", "", "LOG_FILE_LIMIT_BYTES", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedLogFileAppender(@NotNull Context context, @NotNull String logFileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logFileName, "logFileName");
        this.logger = Logger.getAnonymousLogger();
        this.logFile = new File(context.getCacheDir(), logFileName);
        this.appenderChannel = ChannelKt.Channel$default(0, 1, null);
        Logger logger = this.logger;
        Intrinsics.checkExpressionValueIsNotNull(logger, "logger");
        logger.setUseParentHandlers(false);
        Logger logger2 = this.logger;
        Intrinsics.checkExpressionValueIsNotNull(logger2, "logger");
        logger2.setLevel(Level.ALL);
        start();
    }

    public static /* synthetic */ Job appendLog$default(TarazedLogFileAppender tarazedLogFileAppender, Level level, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 8) != 0) {
            th = null;
        }
        return tarazedLogFileAppender.appendLog(level, str, str2, th);
    }

    private final Handler createHandler() {
        FileHandler fileHandler = new FileHandler(this.logFile.getAbsolutePath(), LOG_FILE_LIMIT_BYTES, 1, true);
        fileHandler.setFormatter(TarazedLogFormatter.INSTANCE);
        return fileHandler;
    }

    private final void startAppenderJob() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedLogFileAppender$startAppenderJob$1(this, null), 3, null);
        this.appenderJob = launch$default;
    }

    @NotNull
    public final Job appendLog(@NotNull Level level, @NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedLogFileAppender$appendLog$1(this, level, msg, tag, th, null), 3, null);
        return launch$default;
    }

    public final void clearLogs() {
        this.logFile.delete();
    }

    public final void forEachLine(@NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        File file = this.logFile;
        Charset defaultCharset = Charset.defaultCharset();
        Intrinsics.checkExpressionValueIsNotNull(defaultCharset, "Charset.defaultCharset()");
        FilesKt__FileReadWriteKt.forEachLine(file, defaultCharset, action);
    }

    public final void start() {
        startAppenderJob();
        this.logHandler = createHandler();
        Logger logger = this.logger;
        Handler handler = this.logHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logHandler");
        }
        logger.addHandler(handler);
    }

    public final void stop() {
        Logger logger = this.logger;
        Handler handler = this.logHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logHandler");
        }
        logger.removeHandler(handler);
        Handler handler2 = this.logHandler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logHandler");
        }
        handler2.close();
        Job job = this.appenderJob;
        if (job == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appenderJob");
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }
}
