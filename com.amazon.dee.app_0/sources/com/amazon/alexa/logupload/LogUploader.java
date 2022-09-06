package com.amazon.alexa.logupload;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes9.dex */
public class LogUploader {
    public static final String DTS_SESSION_ID = "dtsSessionId";
    private static final int HTTP_ERROR_STATUS_START = 400;
    public static final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final String TAG = "LogUploader";
    private final Context context;
    @VisibleForTesting
    LogNetworkService logNetworkService;
    @VisibleForTesting
    LogRetriever logRetriever;
    @VisibleForTesting
    LogUploadMetricsService logUploadMetricsService;
    private final String metricPrefix;

    public LogUploader(Context context, @Nullable String str) {
        this.context = context;
        this.metricPrefix = str;
    }

    private LogNetworkService getLogNetworkService() {
        if (this.logNetworkService == null) {
            this.logNetworkService = new LogNetworkService();
        }
        return this.logNetworkService;
    }

    private LogRetriever getLogRetriever() {
        if (this.logRetriever == null) {
            this.logRetriever = new LogRetriever(this.context);
        }
        return this.logRetriever;
    }

    private LogUploadMetricsService getLogUploadMetricsService() {
        if (this.logUploadMetricsService == null) {
            this.logUploadMetricsService = new LogUploadMetricsService(this.metricPrefix);
        }
        return this.logUploadMetricsService;
    }

    private void handleLogUploadException(LogUploadException logUploadException) {
        Log.e(TAG, "Unable to upload logs to DET", logUploadException);
        if (logUploadException.getExceptionCode() == 2 && logUploadException.getExceptionSubCode() >= 400) {
            getLogUploadMetricsService().recordLogUploadHttpError(logUploadException.getExceptionSubCode(), logUploadException.getMessage());
        } else {
            getLogUploadMetricsService().recordLogUploadOtherError(logUploadException.getMessage());
        }
    }

    public void uploadLogsToDET(final String str) {
        SERIAL_EXECUTOR.execute(new Runnable() { // from class: com.amazon.alexa.logupload.-$$Lambda$LogUploader$TQKJy12M7ap0bsVCbkqTz5wWSKs
            @Override // java.lang.Runnable
            public final void run() {
                LogUploader.this.lambda$uploadLogsToDET$0$LogUploader(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: uploadLogsToDETSynchronously */
    public void lambda$uploadLogsToDET$0$LogUploader(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.i(TAG, "DTS Session Id is invalid");
            return;
        }
        getLogUploadMetricsService().recordLogUploadAttempt();
        try {
            try {
                getLogNetworkService().sendLogReport(getLogRetriever().getDETReport(str));
                getLogUploadMetricsService().recordLogUploadSuccess();
            } catch (LogUploadException e) {
                handleLogUploadException(e);
            }
        } catch (LogUploadException e2) {
            Log.e(TAG, "Unable to retrieve logs", e2);
            getLogUploadMetricsService().recordLogUploadRetrievingError(e2.getMessage());
        }
    }
}
