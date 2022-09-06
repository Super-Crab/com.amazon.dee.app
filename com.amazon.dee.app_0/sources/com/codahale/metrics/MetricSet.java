package com.codahale.metrics;

import java.util.Map;
/* loaded from: classes9.dex */
public interface MetricSet extends Metric {
    Map<String, Metric> getMetrics();
}
