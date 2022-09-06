package com.amazon.commscore.api.metrics;
/* loaded from: classes12.dex */
public abstract class CounterMetric extends OperationalMetric {
    public CounterMetric(String str, String str2, String str3) {
        super(str, str2, str3, OperationalMetricType.COUNTER);
    }

    public abstract long getCount();

    public abstract void incrementCounter();

    public abstract void incrementCounterBy(long j);

    public abstract void resetCounter();

    public CounterMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4, OperationalMetricType.COUNTER);
    }
}
