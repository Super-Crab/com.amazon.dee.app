package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface Metrics {
    void recordCustomMetric(@NonNull String str, @NonNull ClientMetric clientMetric, @NonNull MetricRecordingType... metricRecordingTypeArr);

    void recordSimpleDuration(@NonNull String str, @NonNull MetricName metricName, double d);

    void recordSimpleErrorEvent(@NonNull String str, @NonNull MetricName metricName, @NonNull Exception exc);

    void recordSimpleEvent(@NonNull String str, @NonNull MetricName metricName, @NonNull MetricRecordingType... metricRecordingTypeArr);
}
