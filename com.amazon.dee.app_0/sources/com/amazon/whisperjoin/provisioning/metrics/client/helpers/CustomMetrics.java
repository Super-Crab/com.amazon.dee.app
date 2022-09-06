package com.amazon.whisperjoin.provisioning.metrics.client.helpers;

import com.amazon.whisperjoin.provisioning.metrics.client.MetricHelper;
/* loaded from: classes13.dex */
public class CustomMetrics {
    private final MetricHelper mMetricHelper;

    public CustomMetrics(MetricHelper metricHelper) {
        this.mMetricHelper = metricHelper;
    }

    public void addCounter(String str, double d) {
        this.mMetricHelper.recordCounter(str, d, new Object[0]);
    }

    public void addString(String str, String str2) {
        this.mMetricHelper.recordString(str, str2, new Object[0]);
    }

    public void addTimer(String str, double d) {
        this.mMetricHelper.recordTimer(str, d, new Object[0]);
    }
}
