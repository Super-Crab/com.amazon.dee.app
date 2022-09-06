package com.amazon.alexa.voice.handsfree.decider.stepcommand;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.notificationmetadata.NotificationMetadata;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
/* loaded from: classes11.dex */
public abstract class StepCommand {
    @VisibleForTesting
    static final String METRIC_SOURCE = "StepCommand";
    private final Context mContext;
    private final MetricType mMetricType;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final NotificationMetadata mNotificationMetadata;
    private final StepType mStepType;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public StepCommand(@NonNull Context context, @NonNull StepType stepType, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull MetricType metricType) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mNotificationMetadata = notificationMetadata;
        this.mMetricType = metricType;
        this.mStepType = stepType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    public NotificationMetadata getNotificationMetadata() {
        reportNotificationMetadataMetric(METRIC_SOURCE, this.mMetricType.getValue());
        return this.mNotificationMetadata;
    }

    @Nullable
    public abstract Intent getStepIntent();

    @NonNull
    public StepType getStepType() {
        return this.mStepType;
    }

    @VisibleForTesting
    void reportNotificationMetadataMetric(@NonNull String str, @NonNull String str2) {
        this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(str, str2).emit(this.mContext);
    }
}
