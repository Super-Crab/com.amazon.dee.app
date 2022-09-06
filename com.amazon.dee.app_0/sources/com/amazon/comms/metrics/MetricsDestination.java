package com.amazon.comms.metrics;
/* loaded from: classes11.dex */
public enum MetricsDestination {
    DCM,
    INSIGHTS,
    ALL;

    public static MetricsDestination getDefault() {
        return ALL;
    }
}
