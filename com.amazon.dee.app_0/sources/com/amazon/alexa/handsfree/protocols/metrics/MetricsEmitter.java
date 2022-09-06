package com.amazon.alexa.handsfree.protocols.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface MetricsEmitter {
    void recordMetrics(@NonNull Context context, @NonNull Object obj, @NonNull Metric... metricArr);

    void recordMetrics(@NonNull MetricsRecordMode metricsRecordMode, @NonNull Context context, @NonNull Object obj, @NonNull Metric... metricArr);
}
