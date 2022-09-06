package com.amazon.dee.app.services.metrics;

import com.amazon.alexa.location.utils.MetricsUtil;
/* loaded from: classes12.dex */
enum MetricType {
    TIME("time"),
    COUNT("count"),
    FAULT(MetricsUtil.LegacyMetricTypes.FAULT),
    LIMIT(MetricsUtil.LegacyMetricTypes.LIMIT),
    AVAILABILITY(MetricsUtil.LegacyMetricTypes.AVAILABILITY),
    DATA("");
    
    private final String type;

    MetricType(String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }
}
