package com.amazon.comms.metrics;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
/* loaded from: classes11.dex */
public interface TachyonMetricsFactory extends MetricsFactory {
    public static final int SHUTDOWN_DELAY_MILLISECS = 15000;

    void record(MetricEvent metricEvent, MetricsDestination metricsDestination);

    void record(MetricEvent metricEvent, MetricsDestination metricsDestination, Priority priority);

    void recordTrace(String str, String str2, String str3, String str4, String str5);
}
