package com.amazon.commscore.api.metrics;
/* loaded from: classes12.dex */
public abstract class TimerMetric extends OperationalMetric {
    public TimerMetric(String str, String str2, String str3) {
        super(str, str2, str3, OperationalMetricType.TIMER);
    }

    public abstract void finishTimer();

    public abstract void finishTimer(long j);

    public abstract long getElapsedTime();

    public abstract void pauseTimer();

    public abstract void resumeTimer();

    public TimerMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4, OperationalMetricType.TIMER);
    }
}
