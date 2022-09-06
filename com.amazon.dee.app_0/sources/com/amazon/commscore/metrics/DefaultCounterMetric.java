package com.amazon.commscore.metrics;

import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.commscore.api.metrics.CounterMetric;
/* loaded from: classes12.dex */
public class DefaultCounterMetric extends CounterMetric {
    private final MobilyticsMetricsCounter mCounter;

    public DefaultCounterMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
        this.mCounter = new DefaultMobilyticsMetricsCounter(str, str2, str3, str4);
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public long getCount() {
        return this.mCounter.getCount();
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public void incrementCounter() {
        this.mCounter.incrementCounter();
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public void incrementCounterBy(long j) {
        this.mCounter.incrementCounterByValue(j);
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public void resetCounter() {
        this.mCounter.resetCounter();
    }
}
