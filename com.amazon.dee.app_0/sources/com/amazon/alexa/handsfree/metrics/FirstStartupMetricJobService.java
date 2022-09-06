package com.amazon.alexa.handsfree.metrics;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class FirstStartupMetricJobService extends JobService {
    private static final long FLEX_MILLISECONDS = 86380000;
    private static final int JOB_ID = 30050;
    private static final long PERIOD_MILLISECONDS = 86400000;
    private static final String TAG = FirstStartupMetricJobService.class.getSimpleName();
    private final Helper mHelper;
    private final Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class Helper {
        private JobScheduler getJobScheduler(@NonNull Context context) {
            return (JobScheduler) context.getSystemService("jobscheduler");
        }

        @VisibleForTesting
        void cancelJob(@NonNull Context context) {
            getJobScheduler(context).cancel(FirstStartupMetricJobService.JOB_ID);
        }

        public void scheduleJob(@NonNull Context context) {
            JobInfo.Builder requiredNetworkType = new JobInfo.Builder(FirstStartupMetricJobService.JOB_ID, new ComponentName(context, FirstStartupMetricJobService.class)).setRequiredNetworkType(1);
            int i = Build.VERSION.SDK_INT;
            requiredNetworkType.setPeriodic(86400000L, FirstStartupMetricJobService.FLEX_MILLISECONDS);
            getJobScheduler(context).schedule(requiredNetworkType.build());
        }
    }

    public FirstStartupMetricJobService() {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mHelper = new Helper();
    }

    @VisibleForTesting
    DeviceInformation getDeviceTypeInformation() {
        return DeviceTypeInformationProvider.getInstance(this).getSupportedDeviceInformation(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(@NonNull JobParameters jobParameters) {
        if (getDeviceTypeInformation() == null) {
            Log.d(TAG, "onStartJob - Device is not Hands-Free, canceling job.");
            this.mHelper.cancelJob(this);
            return false;
        }
        Log.d(TAG, "onStartJob - emitting FRO!");
        this.mMetricsBuilderProvider.newBuilder().withFirstStartupMetric(FirstStartupMetricJobService.class.getSimpleName()).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_IGNORE_METRICS_ENABLED, this);
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@NonNull JobParameters jobParameters) {
        return false;
    }

    @VisibleForTesting
    FirstStartupMetricJobService(@NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Initializer initializer, @NonNull Helper helper) {
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mInitializer = initializer;
        this.mHelper = helper;
    }
}
