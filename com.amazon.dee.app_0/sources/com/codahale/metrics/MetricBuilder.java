package com.codahale.metrics;

import com.codahale.metrics.Metric;
/* loaded from: classes9.dex */
public interface MetricBuilder<T extends Metric> {
    boolean isInstance(Metric metric);

    /* renamed from: newMetric */
    T mo6799newMetric();
}
