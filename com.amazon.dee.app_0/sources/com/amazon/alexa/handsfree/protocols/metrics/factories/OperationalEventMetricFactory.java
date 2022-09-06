package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface OperationalEventMetricFactory {
    Metric buildDspApkUpdateMetric(@NonNull String str);

    Metric buildFeatureGateDownMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);

    Metric buildFirstStartupMetric(@NonNull String str);

    Metric buildLatencyMetric(long j, long j2, @NonNull String str);

    Metric buildSdkUpdateMetric(@NonNull String str);
}
