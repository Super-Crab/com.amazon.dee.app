package com.amazon.alexa.client.metrics.core;

import android.os.SystemClock;
import java.util.Map;
/* loaded from: classes6.dex */
public class DefaultMetricsTimer extends DefaultAlexaMetricsEvent implements MetricsTimer {
    private long elapsedTime;
    private long finishedTime;
    private boolean isRunning;
    private final Object isRunningMutex;
    private long lastStartTime;
    private long startingTime;

    public DefaultMetricsTimer(String str, String str2, Map<String, Object> map) {
        super(str, str2, map);
        this.isRunningMutex = new Object();
        init(true);
    }

    private void finishTimerInternal(Long l) {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = l.longValue();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
        }
    }

    private void init(boolean z) {
        this.isRunning = z;
        this.lastStartTime = SystemClock.elapsedRealtime();
        this.startingTime = this.lastStartTime;
        this.elapsedTime = 0L;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsTimer
    public void finishTimer() {
        finishTimerInternal(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsTimer
    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public long getStartingTime() {
        return this.startingTime;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsTimer
    public void pauseTimer() {
        finishTimerInternal(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsTimer
    public void resumeTimer() {
        synchronized (this.isRunningMutex) {
            if (!this.isRunning) {
                this.isRunning = true;
                this.lastStartTime = SystemClock.elapsedRealtime();
            }
        }
    }

    public void setStartingTime(long j) {
        this.startingTime = j;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsTimer
    public void finishTimer(Long l) {
        finishTimerInternal(l);
    }

    public DefaultMetricsTimer(String str, String str2, Map<String, Object> map, long j, boolean z) {
        this(str, str2, null, map, j, z);
    }

    public DefaultMetricsTimer(String str, String str2, String str3, Map<String, Object> map, long j, boolean z) {
        super(str, str2, str3, map);
        this.isRunningMutex = new Object();
        init(z);
        this.elapsedTime = j;
    }
}
