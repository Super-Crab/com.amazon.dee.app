package com.amazon.client.metrics.thirdparty;

import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public interface PeriodicMetricReporter {
    MetricEvent createTrackedMetricEvent(String str, String str2);

    MetricEvent createTrackedMetricEvent(String str, String str2, MetricEventType metricEventType);

    MetricEvent getMetricEvent();

    void shutdown();

    void startRecordingPeriodically(long j, TimeUnit timeUnit);

    void stopTrackingMetricEvent(MetricEvent metricEvent);
}
