package com.amazon.whisperjoin.metrics;

import java.util.HashMap;
/* loaded from: classes13.dex */
public class MetricTimerWrapper {
    private final HashMap<String, MetricTimer> mProfilerMap = new HashMap<>();

    public MetricTimer getMetricTimer() {
        return new MetricTimer();
    }

    public MetricTimer getMetricTimerFromProfilerMap(String str) {
        return this.mProfilerMap.get(str);
    }

    public void storeMetricTimerInProfilerMap(String str, MetricTimer metricTimer) {
        this.mProfilerMap.put(str, metricTimer);
    }
}
