package com.amazon.tarazed.core.logging;

import android.content.Context;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.LoggingCredentials;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedSessionLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "Lcom/amazon/tarazed/core/logging/TarazedBaseLogger;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "getAppender$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "uploader", "Lcom/amazon/tarazed/core/logging/TarazedCloudWatchLogUploader;", "uploadLogs", "", "loggingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "uploadLogs$TarazedMobileCore_release", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionLogger extends TarazedBaseLogger {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String LOG_FILE_NAME = "tarazed-session.log";
    private static final String METRIC_LOG_UPLOAD_ATTEMPTS = "SessionLogUploadAttempt";
    private static final String METRIC_LOG_UPLOAD_FAILED = "SessionLogUploadFailed";
    private static final String TAG = "TarazedSessionLogger";
    @NotNull
    private final TarazedLogFileAppender appender;
    private final TarazedCloudWatchLogUploader uploader;

    /* compiled from: TarazedSessionLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedSessionLogger$Companion;", "", "()V", "LOG_FILE_NAME", "", "METRIC_LOG_UPLOAD_ATTEMPTS", "METRIC_LOG_UPLOAD_FAILED", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedSessionLogger(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.appender = new TarazedLogFileAppender(context, LOG_FILE_NAME);
        this.uploader = new TarazedCloudWatchLogUploader(getAppender$TarazedMobileCore_release());
    }

    @Override // com.amazon.tarazed.core.logging.TarazedBaseLogger
    @NotNull
    public TarazedLogFileAppender getAppender$TarazedMobileCore_release() {
        return this.appender;
    }

    public final void uploadLogs$TarazedMobileCore_release(@NotNull LoggingCredentials loggingCredentials, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(loggingCredentials, "loggingCredentials");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        i(TAG, "Uploading logs...");
        try {
            metricsHelper.addCountHighPriority(TAG, METRIC_LOG_UPLOAD_ATTEMPTS, 1.0d);
            this.uploader.uploadLogs(loggingCredentials);
            i(TAG, "Log upload successful");
        } catch (Exception e) {
            e(TAG, "Log upload failed", e);
            metricsHelper.addCountHighPriority(TAG, METRIC_LOG_UPLOAD_FAILED, 1.0d);
        }
    }
}
