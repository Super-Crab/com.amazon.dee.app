package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.clickstream.GenericClickStreamMetricEvent;
import com.amazon.client.metrics.thirdparty.internal.BasicClickStreamMetricEvent;
import com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent;
import com.amazon.client.metrics.thirdparty.internal.ClickStreamWeblabTrigger;
/* loaded from: classes11.dex */
public abstract class BaseMetricsFactoryImpl implements MetricsFactory {
    private static final boolean DEFAULT_ALLOW_RUNNING_TIMERS = false;

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2) {
        return createClickStreamMetricEvent(str, str2, MetricEventType.getDefault(), false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    /* renamed from: createClickStreamWeblabTrigger */
    public GenericClickStreamMetricEvent mo2889createClickStreamWeblabTrigger(String str, String str2, String str3, String str4) {
        if (shouldRecordMetrics()) {
            return new ClickStreamWeblabTrigger(str, str2, str3, str4);
        }
        return new NullClickStreamMetricEvent(str, str2);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return createConcurrentMetricEvent(str, str2, MetricEventType.getDefault(), false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return createMetricEvent(str, str2, MetricEventType.getDefault(), false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public void record(MetricEvent metricEvent) {
        record(metricEvent, Priority.NORMAL);
    }

    protected abstract boolean shouldRecordMetrics();

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return createClickStreamMetricEvent(str, str2, metricEventType, false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return createConcurrentMetricEvent(str, str2, metricEventType, false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return createMetricEvent(str, str2, metricEventType, false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority) {
        record(metricEvent, priority, Channel.ANONYMOUS);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public ClickStreamMetricsEvent createClickStreamMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        if (shouldRecordMetrics()) {
            return new BasicClickStreamMetricEvent(str, str2, metricEventType, z);
        }
        return new NullClickStreamMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        if (shouldRecordMetrics()) {
            return new ConcurrentMetricEvent(str, str2, metricEventType, z);
        }
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        if (shouldRecordMetrics()) {
            return new BasicMetricEvent(str, str2, metricEventType, z);
        }
        return new NullMetricEvent(str, str2, metricEventType);
    }
}
