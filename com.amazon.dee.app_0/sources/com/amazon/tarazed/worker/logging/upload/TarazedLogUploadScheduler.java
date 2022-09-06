package com.amazon.tarazed.worker.logging.upload;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.amazon.tarazed.core.logging.TarazedLogger;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedLogUploadScheduler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/worker/logging/upload/TarazedLogUploadScheduler;", "", "workManager", "Landroidx/work/WorkManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "(Landroidx/work/WorkManager;Lcom/amazon/tarazed/core/logging/TarazedLogger;)V", "schedulePeriodicLogUpload", "", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogUploadScheduler {
    private static final int BACKOFF_DELAY_MIN = 60;
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final int PERIODIC_INTERVAL_SEC = 86400;
    private static final String PERIODIC_JOB_NAME = "tarazedPeriodicLogUploadJob";
    private static final String TAG = "LogUploadScheduler";
    private final TarazedLogger logger;
    private final WorkManager workManager;

    /* compiled from: TarazedLogUploadScheduler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/worker/logging/upload/TarazedLogUploadScheduler$Companion;", "", "()V", "BACKOFF_DELAY_MIN", "", "PERIODIC_INTERVAL_SEC", "PERIODIC_JOB_NAME", "", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedLogUploadScheduler(@NotNull WorkManager workManager, @NotNull TarazedLogger logger) {
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.workManager = workManager;
        this.logger = logger;
    }

    public final void schedulePeriodicLogUpload() {
        this.logger.i(TAG, "Scheduling a periodic log upload");
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(true).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Constraints.Builder()\n  …rue)\n            .build()");
        PeriodicWorkRequest build2 = new PeriodicWorkRequest.Builder(TarazedLogUploadWorker.class, 86400, TimeUnit.SECONDS).setBackoffCriteria(BackoffPolicy.LINEAR, 60, TimeUnit.MINUTES).setConstraints(build).build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "PeriodicWorkRequest\n    …nts)\n            .build()");
        this.workManager.enqueueUniquePeriodicWork(PERIODIC_JOB_NAME, ExistingPeriodicWorkPolicy.KEEP, build2);
        this.logger.i(TAG, "Scheduled a periodic log upload unless one has already been scheduled");
    }
}
