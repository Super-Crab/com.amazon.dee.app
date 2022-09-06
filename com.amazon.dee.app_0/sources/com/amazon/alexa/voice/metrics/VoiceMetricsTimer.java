package com.amazon.alexa.voice.metrics;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.metrics.service.MetricsTimer;
import java.util.Map;
/* loaded from: classes11.dex */
public class VoiceMetricsTimer extends VoiceMetricsEvent implements MetricsTimer {
    private long elapsedTime;
    private long finishedTime;
    private boolean isRunning;
    private final Object isRunningMutex;
    private long lastStartTime;

    public VoiceMetricsTimer(String str, String str2, Map<String, Object> map) {
        super(str, str2, map);
        this.isRunningMutex = new Object();
        init(true);
    }

    private void init(boolean z) {
        this.isRunning = z;
        this.lastStartTime = SystemClock.elapsedRealtime();
        this.elapsedTime = 0L;
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsTimer
    public void finishTimer() {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = SystemClock.elapsedRealtime();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
        }
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsTimer
    public long getElapsedTime() {
        return this.elapsedTime;
    }

    @VisibleForTesting
    public boolean isFinished() {
        boolean z;
        synchronized (this.isRunningMutex) {
            z = !this.isRunning;
        }
        return z;
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsTimer
    public void pauseTimer() {
        synchronized (this.isRunningMutex) {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = SystemClock.elapsedRealtime();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
        }
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsTimer
    public void resumeTimer() {
        synchronized (this.isRunningMutex) {
            if (!this.isRunning) {
                this.isRunning = true;
                this.lastStartTime = SystemClock.elapsedRealtime();
            }
        }
    }

    public VoiceMetricsTimer(String str, String str2, Map<String, Object> map, long j, boolean z) {
        super(str, str2, map);
        this.isRunningMutex = new Object();
        init(z);
        this.elapsedTime = j;
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsTimer
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
