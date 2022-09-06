package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.android.AndroidPeriodicMetricReporterImpl;
import com.amazon.client.metrics.common.internal.fireos.FireOSPeriodicMetricReporterImpl;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class PeriodicMetricReporterImpl implements PeriodicMetricReporter {
    private final PeriodicMetricReporter mDelegatePeriodicMetricReporter;

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegatePeriodicMetricReporter = new FireOSPeriodicMetricReporterImpl(metricsFactory, str, str2, metricEventType, priority, channel);
        } else {
            this.mDelegatePeriodicMetricReporter = new AndroidPeriodicMetricReporterImpl(metricsFactory, str, str2, metricEventType, priority, channel);
        }
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2) {
        return this.mDelegatePeriodicMetricReporter.createTrackedMetricEvent(str, str2);
    }

    PeriodicMetricReporter getDelegatePeriodicMetricReporter() {
        return this.mDelegatePeriodicMetricReporter;
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent getMetricEvent() {
        return this.mDelegatePeriodicMetricReporter.getMetricEvent();
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void shutdown() {
        this.mDelegatePeriodicMetricReporter.shutdown();
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void startRecordingPeriodically(long j, TimeUnit timeUnit) {
        this.mDelegatePeriodicMetricReporter.startRecordingPeriodically(j, timeUnit);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public void stopTrackingMetricEvent(MetricEvent metricEvent) {
        this.mDelegatePeriodicMetricReporter.stopTrackingMetricEvent(metricEvent);
    }

    @Override // com.amazon.client.metrics.common.PeriodicMetricReporter
    public MetricEvent createTrackedMetricEvent(String str, String str2, MetricEventType metricEventType) {
        return this.mDelegatePeriodicMetricReporter.createTrackedMetricEvent(str, str2, metricEventType);
    }

    @Deprecated
    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority) {
        this(metricsFactory, str, str2, metricEventType, priority, Channel.ANONYMOUS);
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType) {
        this(metricsFactory, str, str2, metricEventType, Priority.NORMAL, Channel.ANONYMOUS);
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2) {
        this(metricsFactory, str, str2, MetricEventType.getDefault());
    }

    PeriodicMetricReporterImpl(com.amazon.client.metrics.PeriodicMetricReporterImpl periodicMetricReporterImpl) {
        if (periodicMetricReporterImpl != null) {
            this.mDelegatePeriodicMetricReporter = new FireOSPeriodicMetricReporterImpl(periodicMetricReporterImpl);
            return;
        }
        throw new NullPointerException("FirstParty PeriodicMetricReporterImpl may not be null");
    }

    PeriodicMetricReporterImpl(com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl periodicMetricReporterImpl) {
        if (periodicMetricReporterImpl != null) {
            this.mDelegatePeriodicMetricReporter = new AndroidPeriodicMetricReporterImpl(periodicMetricReporterImpl);
            return;
        }
        throw new NullPointerException("ThirdParty PeriodicMetricReporterImpl may not be null");
    }
}
