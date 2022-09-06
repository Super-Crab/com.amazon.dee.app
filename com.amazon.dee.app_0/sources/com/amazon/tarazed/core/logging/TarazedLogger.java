package com.amazon.tarazed.core.logging;

import android.content.Context;
import com.amazon.tarazed.core.logging.api.LogUploadRequest;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogger;", "Lcom/amazon/tarazed/core/logging/TarazedBaseLogger;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "getAppender$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "uploader", "Lcom/amazon/tarazed/core/logging/TarazedDETLogUploader;", "uploadLogs", "", "request", "Lcom/amazon/tarazed/core/logging/api/LogUploadRequest;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogger extends TarazedBaseLogger {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String LOG_FILE_NAME = "tarazed.log";
    private static final String METRIC_LOG_UPLOAD_FAILED = "LogUploadFailed";
    private static final String TAG = "TarazedLogger";
    @NotNull
    private final TarazedLogFileAppender appender;
    private final TarazedDETLogUploader uploader;

    /* compiled from: TarazedLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogger$Companion;", "", "()V", "LOG_FILE_NAME", "", "METRIC_LOG_UPLOAD_FAILED", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedLogger(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.appender = new TarazedLogFileAppender(context, LOG_FILE_NAME);
        this.uploader = new TarazedDETLogUploader(getAppender$TarazedMobileCore_release());
    }

    @Override // com.amazon.tarazed.core.logging.TarazedBaseLogger
    @NotNull
    public TarazedLogFileAppender getAppender$TarazedMobileCore_release() {
        return this.appender;
    }

    public final void uploadLogs(@NotNull LogUploadRequest request, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        i(TAG, "Uploading logs...");
        try {
            this.uploader.uploadLogs(request);
            i(TAG, "Log upload successful");
        } catch (Exception e) {
            e(TAG, "Log upload failed", e);
            metricsHelper.addCountHighPriority(TAG, METRIC_LOG_UPLOAD_FAILED, 1.0d);
            throw e;
        }
    }
}
