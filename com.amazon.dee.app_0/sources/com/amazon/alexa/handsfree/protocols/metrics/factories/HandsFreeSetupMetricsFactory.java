package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface HandsFreeSetupMetricsFactory {
    @NonNull
    Metric buildHandsFreeSetupMetric(@NonNull String str, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData);

    @NonNull
    Metric buildHandsFreeSetupMetric(@NonNull String str, @NonNull String str2, @NonNull HandsFreeSetupMetricData handsFreeSetupMetricData);
}
