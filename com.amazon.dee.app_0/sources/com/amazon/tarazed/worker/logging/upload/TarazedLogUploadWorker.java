package com.amazon.tarazed.worker.logging.upload;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.api.LogUploadRequest;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.type.Account;
import com.amazon.tarazed.core.types.Environment;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.amazon.tarazed.type.SharedPrefsConstants;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedLogUploadWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010 \u001a\u00020!H\u0016R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/tarazed/worker/logging/upload/TarazedLogUploadWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "getDeviceInfoUtility$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "setDeviceInfoUtility$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "getLogger$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/logging/TarazedLogger;", "setLogger$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/logging/TarazedLogger;)V", "metrics", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "getMetrics$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "setMetrics$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "sharedPreferences", "Ljavax/inject/Provider;", "Landroid/content/SharedPreferences;", "getSharedPreferences$TarazedAndroidLibrary_release", "()Ljavax/inject/Provider;", "setSharedPreferences$TarazedAndroidLibrary_release", "(Ljavax/inject/Provider;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogUploadWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static int MAX_UPLOAD_ATTEMPTS = 4;
    private static final String METRIC_LOG_UPLOAD_WORKER_ATTEMPTED = "LogUploadWorkerAttempted";
    private static final String METRIC_LOG_UPLOAD_WORKER_FAILED = "LogUploadWorkerFailed";
    private static final String METRIC_MAX_ATTEMPTS_EXCEEDED = "LogUploadWorkerMaxAttemptsExceeded";
    private static final String METRIC_UNKNOWN_DEVICE_ENVIRONMENT = "UnknownDeviceEnvironment";
    private static final String TAG = "TarazedLogUploadWorker";
    @Inject
    @NotNull
    public DeviceInfoUtility deviceInfoUtility;
    @Inject
    @NotNull
    public TarazedLogger logger;
    @Inject
    @NotNull
    public TarazedMetricsHelper metrics;
    @Inject
    @NotNull
    public Provider<SharedPreferences> sharedPreferences;

    /* compiled from: TarazedLogUploadWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/worker/logging/upload/TarazedLogUploadWorker$Companion;", "", "()V", "MAX_UPLOAD_ATTEMPTS", "", "MAX_UPLOAD_ATTEMPTS$annotations", "getMAX_UPLOAD_ATTEMPTS$TarazedAndroidLibrary_release", "()I", "setMAX_UPLOAD_ATTEMPTS$TarazedAndroidLibrary_release", "(I)V", "METRIC_LOG_UPLOAD_WORKER_ATTEMPTED", "", "METRIC_LOG_UPLOAD_WORKER_FAILED", "METRIC_MAX_ATTEMPTS_EXCEEDED", "METRIC_UNKNOWN_DEVICE_ENVIRONMENT", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void MAX_UPLOAD_ATTEMPTS$annotations() {
        }

        public final int getMAX_UPLOAD_ATTEMPTS$TarazedAndroidLibrary_release() {
            return TarazedLogUploadWorker.MAX_UPLOAD_ATTEMPTS;
        }

        public final void setMAX_UPLOAD_ATTEMPTS$TarazedAndroidLibrary_release(int i) {
            TarazedLogUploadWorker.MAX_UPLOAD_ATTEMPTS = i;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedLogUploadWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        LibraryInjector.getComponent().inject(this);
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        Account accountMetadata;
        if (getRunAttemptCount() > MAX_UPLOAD_ATTEMPTS) {
            TarazedLogger tarazedLogger = this.logger;
            if (tarazedLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger.w(TAG, "Log upload job has exceeded max attempts, dropping the request");
            TarazedMetricsHelper tarazedMetricsHelper = this.metrics;
            if (tarazedMetricsHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedMetricsHelper.addCount(TAG, METRIC_MAX_ATTEMPTS_EXCEEDED, 1.0d);
            ListenableWorker.Result failure = ListenableWorker.Result.failure();
            Intrinsics.checkExpressionValueIsNotNull(failure, "Result.failure()");
            return failure;
        }
        try {
            TarazedMetricsHelper tarazedMetricsHelper2 = this.metrics;
            if (tarazedMetricsHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedMetricsHelper2.addCount(TAG, METRIC_LOG_UPLOAD_WORKER_ATTEMPTED, 1.0d);
            Environment.Companion companion = Environment.Companion;
            Provider<SharedPreferences> provider = this.sharedPreferences;
            if (provider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            }
            Environment fromName = companion.fromName(provider.mo10268get().getString(SharedPrefsConstants.DEVICE_ENVIRONMENT, Environment.PROD.name()));
            if (fromName == null) {
                TarazedLogger tarazedLogger2 = this.logger;
                if (tarazedLogger2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                tarazedLogger2.w(TAG, "Unknown device environment, dropping request");
                TarazedMetricsHelper tarazedMetricsHelper3 = this.metrics;
                if (tarazedMetricsHelper3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                tarazedMetricsHelper3.addCount(TAG, METRIC_UNKNOWN_DEVICE_ENVIRONMENT, 1.0d);
                ListenableWorker.Result failure2 = ListenableWorker.Result.failure();
                Intrinsics.checkExpressionValueIsNotNull(failure2, "Result.failure()");
                return failure2;
            }
            AccountMetadataProvider accountMetadataProvider = (AccountMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AccountMetadataProvider.class);
            String deviceType = (accountMetadataProvider == null || (accountMetadata = accountMetadataProvider.getAccountMetadata()) == null) ? null : accountMetadata.getDeviceType();
            if (deviceType == null) {
                TarazedLogger tarazedLogger3 = this.logger;
                if (tarazedLogger3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                tarazedLogger3.w(TAG, "DeviceType has not yet been saved, reschedule log upload.");
                ListenableWorker.Result retry = ListenableWorker.Result.retry();
                Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
                return retry;
            }
            DeviceInfoUtility deviceInfoUtility = this.deviceInfoUtility;
            if (deviceInfoUtility == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
            }
            String serialNumber = deviceInfoUtility.getSerialNumber();
            DeviceInfoUtility deviceInfoUtility2 = this.deviceInfoUtility;
            if (deviceInfoUtility2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
            }
            LogUploadRequest logUploadRequest = new LogUploadRequest(fromName, serialNumber, deviceType, deviceInfoUtility2.getSoftwareVersion());
            TarazedLogger tarazedLogger4 = this.logger;
            if (tarazedLogger4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            TarazedMetricsHelper tarazedMetricsHelper4 = this.metrics;
            if (tarazedMetricsHelper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedLogger4.uploadLogs(logUploadRequest, tarazedMetricsHelper4);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
            return success;
        } catch (Exception e) {
            TarazedLogger tarazedLogger5 = this.logger;
            if (tarazedLogger5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger5.e(TAG, "Exception occurred uploading logs, will retry according to backoff policy", e);
            TarazedMetricsHelper tarazedMetricsHelper5 = this.metrics;
            if (tarazedMetricsHelper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedMetricsHelper5.addCount(TAG, METRIC_LOG_UPLOAD_WORKER_FAILED, 1.0d);
            ListenableWorker.Result retry2 = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry2, "Result.retry()");
            return retry2;
        }
    }

    @NotNull
    public final DeviceInfoUtility getDeviceInfoUtility$TarazedAndroidLibrary_release() {
        DeviceInfoUtility deviceInfoUtility = this.deviceInfoUtility;
        if (deviceInfoUtility == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
        }
        return deviceInfoUtility;
    }

    @NotNull
    public final TarazedLogger getLogger$TarazedAndroidLibrary_release() {
        TarazedLogger tarazedLogger = this.logger;
        if (tarazedLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedLogger;
    }

    @NotNull
    public final TarazedMetricsHelper getMetrics$TarazedAndroidLibrary_release() {
        TarazedMetricsHelper tarazedMetricsHelper = this.metrics;
        if (tarazedMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return tarazedMetricsHelper;
    }

    @NotNull
    public final Provider<SharedPreferences> getSharedPreferences$TarazedAndroidLibrary_release() {
        Provider<SharedPreferences> provider = this.sharedPreferences;
        if (provider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return provider;
    }

    public final void setDeviceInfoUtility$TarazedAndroidLibrary_release(@NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "<set-?>");
        this.deviceInfoUtility = deviceInfoUtility;
    }

    public final void setLogger$TarazedAndroidLibrary_release(@NotNull TarazedLogger tarazedLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedLogger, "<set-?>");
        this.logger = tarazedLogger;
    }

    public final void setMetrics$TarazedAndroidLibrary_release(@NotNull TarazedMetricsHelper tarazedMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(tarazedMetricsHelper, "<set-?>");
        this.metrics = tarazedMetricsHelper;
    }

    public final void setSharedPreferences$TarazedAndroidLibrary_release(@NotNull Provider<SharedPreferences> provider) {
        Intrinsics.checkParameterIsNotNull(provider, "<set-?>");
        this.sharedPreferences = provider;
    }
}
