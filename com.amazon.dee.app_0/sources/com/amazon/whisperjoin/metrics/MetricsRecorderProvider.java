package com.amazon.whisperjoin.metrics;

import com.amazon.client.metrics.common.MetricsFactory;
/* loaded from: classes13.dex */
public class MetricsRecorderProvider {
    private String mClientName;
    private String mDirectedId;
    private final MetricsFactory mMetricsFactory;
    private final MetricsProgramName mMetricsProgramName;

    public MetricsRecorderProvider(MetricsFactory metricsFactory, MetricsProgramName metricsProgramName, String str, String str2) {
        this.mMetricsFactory = metricsFactory;
        this.mMetricsProgramName = metricsProgramName;
        this.mDirectedId = str;
        this.mClientName = str2;
    }

    public MetricsRecorder getMetricsRecorder(WhisperJoinMetricSourceName whisperJoinMetricSourceName) {
        return new MetricsRecorder(this.mMetricsFactory, whisperJoinMetricSourceName, this.mMetricsProgramName, this.mDirectedId, this.mClientName, new MetricTimerWrapper());
    }

    public MetricsRecorder getMetricsRecorderForProvisionable(WhisperJoinMetricSourceName whisperJoinMetricSourceName, String str) {
        return new MetricsRecorder(this.mMetricsFactory, whisperJoinMetricSourceName, this.mMetricsProgramName, str, new MetricTimerWrapper());
    }

    public MetricsRecorderProvider(MetricsFactory metricsFactory, MetricsProgramName metricsProgramName) {
        this.mMetricsFactory = metricsFactory;
        this.mMetricsProgramName = metricsProgramName;
    }
}
