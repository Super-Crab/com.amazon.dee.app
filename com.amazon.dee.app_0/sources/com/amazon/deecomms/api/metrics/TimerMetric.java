package com.amazon.deecomms.api.metrics;

import androidx.annotation.NonNull;
import com.amazon.deecomms.api.metrics.CommsMetric;
/* loaded from: classes12.dex */
public class TimerMetric extends CommsMetric {
    private long endTime;
    private long startTime;
    private boolean timerActive;

    public TimerMetric(CommsMetric.MetricType metricType, String str, String str2) {
        super(metricType, str, str2);
    }

    @NonNull
    public static TimerMetric generateClickstream(String str) {
        return new TimerMetric(CommsMetric.MetricType.ClickStream, str);
    }

    @NonNull
    public static TimerMetric generateOperational(String str) {
        return new TimerMetric(CommsMetric.MetricType.Operational, str);
    }

    @Override // com.amazon.deecomms.api.metrics.CommsMetric
    protected void addAttributesForToString(StringBuilder sb) {
        sb.append("timeDelta=");
        sb.append(getTimeDelta());
    }

    @Override // com.amazon.deecomms.api.metrics.CommsMetric
    public String formatMetric() {
        return format(String.valueOf(getTimeDelta()));
    }

    public long getTimeDelta() {
        return this.endTime - this.startTime;
    }

    public final void setTimeDelta(long j) {
        this.startTime = 0L;
        this.endTime = j;
        this.timerActive = false;
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.timerActive = true;
    }

    public void stopTimer() {
        if (this.timerActive) {
            this.endTime = System.currentTimeMillis();
        }
        this.timerActive = false;
    }

    public TimerMetric(CommsMetric.MetricType metricType, String str) {
        super(metricType, "Comms", str);
    }
}
