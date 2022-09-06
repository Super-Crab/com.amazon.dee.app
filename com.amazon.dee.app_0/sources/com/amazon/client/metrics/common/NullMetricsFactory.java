package com.amazon.client.metrics.common;
/* loaded from: classes11.dex */
public class NullMetricsFactory implements MetricsFactory {
    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2) {
        return new NullClickStreamMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return new NullMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return new NullMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public String getSessionID() {
        return "";
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent) {
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority) {
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority, Channel channel) {
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return new NullClickStreamMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    /* renamed from: createClickStreamWeblabTrigger */
    public ClickStreamMetricsEvent mo2878createClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        return new NullClickStreamMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return new NullClickStreamMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        return new NullMetricEvent(str, str2, metricEventType);
    }
}
