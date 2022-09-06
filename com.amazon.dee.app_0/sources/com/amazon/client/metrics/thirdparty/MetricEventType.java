package com.amazon.client.metrics.thirdparty;
/* loaded from: classes11.dex */
public enum MetricEventType {
    AGGREGATING,
    AVERAGING;

    public static MetricEventType getDefault() {
        return AGGREGATING;
    }
}
