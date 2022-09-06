package com.amazon.alexa.mobilytics.event.operational;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes9.dex */
public class DefaultMobilyticsMetricsTimer extends DefaultMobilyticsOperationalEvent implements MobilyticsMetricsTimer {
    private transient long elapsedTime;
    private transient long finishedTime;
    protected transient boolean isRunning;
    private transient long lastStartTime;
    private final transient ReentrantReadWriteLock lock;
    private final transient ReentrantReadWriteLock.ReadLock readLock;
    private final transient ReentrantReadWriteLock.WriteLock writeLock;

    public DefaultMobilyticsMetricsTimer(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        super(str, OperationalEventType.TIMER, str2, str3);
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        init(true);
    }

    private void init(boolean z) {
        this.isRunning = z;
        this.lastStartTime = this.eventTimestamp;
        this.elapsedTime = 0L;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public void finishTimer() {
        this.writeLock.lock();
        try {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = System.currentTimeMillis();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
            mo1484withEventNumericValue(Long.valueOf(this.elapsedTime));
        } finally {
            this.writeLock.unlock();
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public long getElapsedTime() {
        this.readLock.lock();
        try {
            long j = this.elapsedTime;
            mo1484withEventNumericValue(Long.valueOf(this.elapsedTime));
            return j;
        } finally {
            this.readLock.unlock();
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public void pauseTimer() {
        this.writeLock.lock();
        try {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = System.currentTimeMillis();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
            mo1484withEventNumericValue(Long.valueOf(this.elapsedTime));
        } finally {
            this.writeLock.unlock();
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public void resumeTimer() {
        this.writeLock.lock();
        try {
            if (!this.isRunning) {
                this.isRunning = true;
                this.lastStartTime = System.currentTimeMillis();
            }
        } finally {
            this.writeLock.unlock();
        }
    }

    public DefaultMobilyticsMetricsTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, String str4) {
        super(str, OperationalEventType.TIMER, str2, str3, str4);
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        init(true);
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public void finishTimer(Long l) {
        this.writeLock.lock();
        try {
            if (this.isRunning) {
                this.isRunning = false;
                this.finishedTime = l.longValue();
                this.elapsedTime = (this.finishedTime - this.lastStartTime) + this.elapsedTime;
            }
            mo1484withEventNumericValue(Long.valueOf(this.elapsedTime));
        } finally {
            this.writeLock.unlock();
        }
    }

    public DefaultMobilyticsMetricsTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z) {
        super(str, OperationalEventType.TIMER, str2, str3);
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        init(z);
        this.elapsedTime = j;
        mo1484withEventNumericValue(Long.valueOf(j));
    }

    public DefaultMobilyticsMetricsTimer(@NonNull String str, @NonNull String str2, @Nullable String str3, long j, boolean z, String str4) {
        super(str, OperationalEventType.TIMER, str2, str3, str4);
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        init(z);
        this.elapsedTime = j;
        mo1484withEventNumericValue(Long.valueOf(j));
    }
}
