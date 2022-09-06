package com.amazon.whisperjoin.provisioning.metrics.client;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class MetricHelper {
    static final String PROVISIONING_METRICS_PROGRAM_NAME = "DeviceProvisioning";
    protected MetricEvent mMetricEvent;
    protected MetricsFactory mMetricsFactory;
    private final String mSourceName;

    public MetricHelper(MetricsFactory metricsFactory, String str, String str2) {
        this.mMetricsFactory = metricsFactory;
        this.mMetricEvent = metricsFactory.createMetricEvent(PROVISIONING_METRICS_PROGRAM_NAME, str);
        this.mSourceName = str;
        this.mMetricEvent.setAnonymous(false);
        this.mMetricEvent.setNonAnonymousCustomerId(str2);
    }

    private String getMetricNameWithDimensions(String str, Object... objArr) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        for (Object obj : objArr) {
            if (obj != null) {
                outline107.append("_");
                outline107.append(obj.toString());
            }
        }
        return outline107.toString();
    }

    public void close() {
        MetricsFactory metricsFactory = this.mMetricsFactory;
        if (metricsFactory != null) {
            metricsFactory.record(this.mMetricEvent, Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS);
        }
        this.mMetricsFactory = null;
        this.mMetricEvent = null;
    }

    public void recordCounter(String str, double d, Object... objArr) {
        this.mMetricEvent.addCounter(getMetricNameWithDimensions(str, objArr), d);
    }

    public void recordString(String str, Object obj, Object... objArr) {
        this.mMetricEvent.addString(getMetricNameWithDimensions(str, objArr), obj == null ? null : obj.toString());
    }

    public void recordTimer(String str, double d, Object... objArr) {
        this.mMetricEvent.addTimer(getMetricNameWithDimensions(str, objArr), d);
    }
}
