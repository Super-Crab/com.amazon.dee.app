package com.dee.app.metrics;
@Deprecated
/* loaded from: classes2.dex */
public interface MetricsServiceV2 {
    void recordAvailable(MetricDescriptor metricDescriptor);

    void recordCount(MetricDescriptor metricDescriptor, double d);

    void recordData(MetricDescriptor metricDescriptor, Object obj);

    void recordFailure(MetricDescriptor metricDescriptor, String str);

    void recordLimit(MetricDescriptor metricDescriptor, boolean z);

    void recordSuccess(MetricDescriptor metricDescriptor);

    void recordTime(MetricDescriptor metricDescriptor, long j);

    void recordUnavailable(MetricDescriptor metricDescriptor);
}
