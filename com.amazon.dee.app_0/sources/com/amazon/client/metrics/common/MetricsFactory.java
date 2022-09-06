package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.clickstream.GenericClickStreamMetricEvent;
/* loaded from: classes11.dex */
public interface MetricsFactory {
    ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2);

    ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType);

    ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z);

    /* renamed from: createClickStreamWeblabTrigger */
    GenericClickStreamMetricEvent mo2878createClickStreamWeblabTrigger(String str, String str2, String str3, String str4);

    MetricEvent createConcurrentMetricEvent(String str, String str2);

    MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType);

    MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z);

    MetricEvent createMetricEvent(String str, String str2);

    MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType);

    MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z);

    String getSessionID();

    void record(MetricEvent metricEvent);

    @Deprecated
    void record(MetricEvent metricEvent, Priority priority);

    void record(MetricEvent metricEvent, Priority priority, Channel channel);
}
