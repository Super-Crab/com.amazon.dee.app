package com.amazon.alexa.handsfree.protocols.metrics;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes8.dex */
public interface MetricRecorder {
    void recordMetric(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, @NonNull Metric metric);

    void recordMetricGroup(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, @NonNull List<Metric> list);

    void recordMetricGroup(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, Metric... metricArr);
}
