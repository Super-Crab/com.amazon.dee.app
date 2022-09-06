package com.amazon.deecomms.services.metrics;

import com.dee.app.metrics.MetricsTimer;
import java.util.Map;
/* loaded from: classes12.dex */
public class DefaultMetricsTimer extends DefaultAlexaMetricsEvent implements MetricsTimer {
    private long elapsedTime;
    private long finishedTime;
    private boolean isRunning;
    private final Object isRunningMutex;
    private long lastStartTime;
    private final long startingTime;

    public DefaultMetricsTimer(String str, String str2, Map<String, Object> map) {
        this(str, str2, map, 0L, true);
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void finishTimer() {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                finishTimer(Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public long getElapsedTime() {
        long currentTimeMillis;
        synchronized (this.isRunningMutex) {
            currentTimeMillis = this.isRunning ? (this.elapsedTime + System.currentTimeMillis()) - this.lastStartTime : this.elapsedTime;
        }
        return currentTimeMillis;
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void pauseTimer() {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = System.currentTimeMillis();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
        }
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void resumeTimer() {
        synchronized (this.isRunningMutex) {
            if (!this.isRunning) {
                this.isRunning = true;
                this.lastStartTime = System.currentTimeMillis();
            }
        }
    }

    public DefaultMetricsTimer(String str, String str2, Map<String, Object> map, long j, boolean z) {
        super(str, str2, map);
        this.isRunningMutex = new Object();
        this.isRunning = z;
        this.lastStartTime = getEventDate();
        this.startingTime = this.lastStartTime;
        this.elapsedTime = j;
    }

    @Override // com.dee.app.metrics.MetricsTimer
    public void finishTimer(Long l) {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = l.longValue();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
        }
    }
}
