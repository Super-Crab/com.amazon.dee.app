package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public final class Stopwatch {
    private final CurrentTimeSupplier currentTimeSupplier;
    private volatile boolean isRunning;
    private volatile long lastStartedTime;
    private volatile long previousLapTimes;

    /* loaded from: classes.dex */
    public interface CurrentTimeSupplier {
        long getCurrentTimeMillis();
    }

    public Stopwatch(CurrentTimeSupplier currentTimeSupplier) {
        Preconditions.notNull(currentTimeSupplier, "currentTimeSupplier");
        this.currentTimeSupplier = currentTimeSupplier;
    }

    private synchronized long getTimeSinceLastStart() {
        if (!this.isRunning) {
            return 0L;
        }
        return this.currentTimeSupplier.getCurrentTimeMillis() - this.lastStartedTime;
    }

    public synchronized long getElapsedTimeMillis() {
        return this.previousLapTimes + getTimeSinceLastStart();
    }

    public synchronized void startOrResume() {
        if (this.isRunning) {
            return;
        }
        this.lastStartedTime = this.currentTimeSupplier.getCurrentTimeMillis();
        this.isRunning = true;
    }

    public synchronized void stop() {
        if (!this.isRunning) {
            return;
        }
        this.previousLapTimes += getTimeSinceLastStart();
        this.isRunning = false;
    }

    public synchronized void stopAndReset() {
        if (this.isRunning) {
            this.isRunning = false;
        }
        this.previousLapTimes = 0L;
    }
}
