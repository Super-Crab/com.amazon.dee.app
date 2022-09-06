package com.amazon.tarazed.core.webrtc.android;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WebRTCLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/WebRTCLogger;", "Ljava/util/logging/Handler;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "publish", "record", "Ljava/util/logging/LogRecord;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WebRTCLogger extends Handler {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WebRTCLogger";
    private final TarazedSessionLogger logger;

    /* compiled from: WebRTCLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/WebRTCLogger$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WebRTCLogger(@NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
    }

    @Override // java.util.logging.Handler
    public void close() {
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    @Override // java.util.logging.Handler
    public void publish(@Nullable LogRecord logRecord) {
        if (logRecord == null) {
            this.logger.w(TAG, "WebRTC passed a null log record");
            return;
        }
        Level level = logRecord.getLevel();
        if (Intrinsics.areEqual(level, Level.SEVERE)) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            String loggerName = logRecord.getLoggerName();
            Intrinsics.checkExpressionValueIsNotNull(loggerName, "record.loggerName");
            String message = logRecord.getMessage();
            Intrinsics.checkExpressionValueIsNotNull(message, "record.message");
            tarazedSessionLogger.e(loggerName, message);
        } else if (Intrinsics.areEqual(level, Level.WARNING)) {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            String loggerName2 = logRecord.getLoggerName();
            Intrinsics.checkExpressionValueIsNotNull(loggerName2, "record.loggerName");
            String message2 = logRecord.getMessage();
            Intrinsics.checkExpressionValueIsNotNull(message2, "record.message");
            tarazedSessionLogger2.w(loggerName2, message2);
        } else if (Intrinsics.areEqual(level, Level.INFO)) {
            TarazedSessionLogger tarazedSessionLogger3 = this.logger;
            String loggerName3 = logRecord.getLoggerName();
            Intrinsics.checkExpressionValueIsNotNull(loggerName3, "record.loggerName");
            String message3 = logRecord.getMessage();
            Intrinsics.checkExpressionValueIsNotNull(message3, "record.message");
            tarazedSessionLogger3.i(loggerName3, message3);
        } else {
            TarazedSessionLogger tarazedSessionLogger4 = this.logger;
            String loggerName4 = logRecord.getLoggerName();
            Intrinsics.checkExpressionValueIsNotNull(loggerName4, "record.loggerName");
            String message4 = logRecord.getMessage();
            Intrinsics.checkExpressionValueIsNotNull(message4, "record.message");
            tarazedSessionLogger4.v(loggerName4, message4);
        }
    }
}
