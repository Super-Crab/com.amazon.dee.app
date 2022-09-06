package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface PerformanceMetricFactory {
    Metric buildLatencyMetric(@NonNull String str, @NonNull String str2, long j);

    Metric buildPercentileMetricFailure(@NonNull String str, @NonNull String str2);

    Metric buildPercentileMetricSuccess(@NonNull String str, @NonNull String str2);

    Metric buildPerformanceMetric(@NonNull String str, @NonNull String str2);

    Metric buildPerformanceMetricFailure(@NonNull String str, @NonNull String str2, int i);
}
