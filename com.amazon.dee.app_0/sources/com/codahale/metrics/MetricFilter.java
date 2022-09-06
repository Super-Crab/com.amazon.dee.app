package com.codahale.metrics;
/* loaded from: classes9.dex */
public interface MetricFilter {
    public static final MetricFilter ALL = new MetricFilter() { // from class: com.codahale.metrics.MetricFilter.1
        @Override // com.codahale.metrics.MetricFilter
        public boolean matches(String str, Metric metric) {
            return true;
        }
    };

    boolean matches(String str, Metric metric);
}
