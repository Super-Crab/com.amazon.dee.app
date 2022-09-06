package com.amazon.commsnetworking.metrics;
/* loaded from: classes12.dex */
public class MetricMetadata {
    private boolean includeMetricsPrefix;
    private String metricName;
    private String source;

    public MetricMetadata(String str, String str2) {
        this(str, str2, true);
    }

    public String getMetricName() {
        return this.metricName;
    }

    public String getSource() {
        return this.source;
    }

    public boolean includeCommsNetworkingPrefix() {
        return this.includeMetricsPrefix;
    }

    public MetricMetadata(String str, String str2, boolean z) {
        this.source = str;
        this.metricName = str2;
        this.includeMetricsPrefix = z;
    }
}
