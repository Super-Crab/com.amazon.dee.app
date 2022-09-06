package com.amazon.tarazed.arcus;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class ArcusPeriodicSchedulerWorker extends Worker {
    private static final int MAX_SYNC_ATTEMPTS = 10;
    private static final String METRIC_MAX_ATTEMPTS_EXCEEDED = "ArcusSchedulePeriodicSyncMaxAttemptsExceeded";
    private static final String METRIC_UNKNOWN_EXCEPTION = "ArcusSchedulePeriodicSyncUnknownException";
    private static final String TAG = "ArcusPerSchedWorker";
    @Inject
    ArcusHelper arcusHelper;
    @Inject
    TarazedLogger logger;
    @Inject
    TarazedMetricsHelper metrics;

    public ArcusPeriodicSchedulerWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
        LibraryInjector.getComponent().inject(this);
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        this.logger.i(TAG, "Executing Arcus periodic sync scheduler.");
        if (getRunAttemptCount() > 10) {
            this.logger.w(TAG, "Arcus sync job has exceeded max attempts, dropping the request.");
            this.metrics.addCount(TAG, METRIC_MAX_ATTEMPTS_EXCEEDED, 1.0d);
            return ListenableWorker.Result.failure();
        }
        try {
            this.arcusHelper.schedulePeriodicArcusSync(false);
            return ListenableWorker.Result.success();
        } catch (Exception e) {
            this.logger.w(TAG, "Exception scheduling periodic Arcus sync, will retry according to backoff criteria.", e);
            this.metrics.addCount(TAG, METRIC_UNKNOWN_EXCEPTION, 1.0d);
            return ListenableWorker.Result.retry();
        }
    }
}
