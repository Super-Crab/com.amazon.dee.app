package com.amazon.alexa.handsfree.metrics.interprocess;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetricReporter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class InterProcessMetricReportingJobIntentService extends SafeDequeueJobIntentService {
    private static final String INTER_PROCESS_METRIC_JOB_PREFIX = "InterProcessMetricJobPrefix";
    private static final String INVALID_METRIC = "InvalidMetric";
    private static final int JOB_ID = 3205;
    private static final String MISSING_EXTRA = "MissingExtra";
    private static final String SEPARATOR = ":";
    private static final String TAG = "InterProcessMetricJob";
    private final Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    public InterProcessMetricReportingJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, InterProcessMetricReportingJobIntentService.class, (int) JOB_ID, intent);
    }

    @VisibleForTesting
    String createPMETName(@NonNull String str) {
        return GeneratedOutlineSupport1.outline72("InterProcessMetricJobPrefix:", str);
    }

    @VisibleForTesting
    void logInvalidMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(createPMETName(INVALID_METRIC), TAG).emit(this);
    }

    @VisibleForTesting
    void logMissingExtraMetric() {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(createPMETName(MISSING_EXTRA), TAG).emit(this);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        InterProcessMetric interProcessMetric = (InterProcessMetric) intent.getParcelableExtra(InterProcessMetricReporter.PAYLOAD_IDENTIFIER);
        if (interProcessMetric != null) {
            Log.d(TAG, "Inter process metric payload received");
            MetricsBuilder populateMetricsBuilder = interProcessMetric.populateMetricsBuilder(this.mMetricsBuilderProvider.newBuilder());
            if (populateMetricsBuilder != null) {
                populateMetricsBuilder.emit(this);
                return;
            }
            Log.e(TAG, "Unable to log metric as it was invalid");
            logInvalidMetric();
            return;
        }
        Log.w(TAG, "Unable to get Inter process metric payload even though received correct action");
        logMissingExtraMetric();
    }

    public InterProcessMetricReportingJobIntentService(@NonNull Initializer initializer, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mInitializer = initializer;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
