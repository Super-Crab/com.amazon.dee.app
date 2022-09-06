package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class AggregatingTimer {
    private static final TimeUnit TIMEUNIT = TimeUnit.MILLISECONDS;
    private final boolean mAllowRunningTimers;
    private int mRunningCount = 0;
    private int mTotalSamplesCount = 0;
    private long mLastUpdatedTime = 0;
    private double mTotalElapsedTime = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;

    public AggregatingTimer(boolean z) {
        this.mAllowRunningTimers = z;
    }

    public void addTime(double d, int i) {
        DoubleValidator.validateDouble(d);
        this.mTotalElapsedTime += d;
        this.mTotalSamplesCount += i;
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public double getElapsedTime() {
        int i = this.mRunningCount;
        if (i > 0) {
            if (this.mAllowRunningTimers) {
                return this.mTotalElapsedTime + ((getCurrentTimeMillis() - this.mLastUpdatedTime) * i);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Cannot get elapsed time - "), this.mRunningCount, " start calls not matched with stop."));
        }
        return this.mTotalElapsedTime;
    }

    public int getSamples() {
        if (!this.mAllowRunningTimers) {
            return this.mTotalSamplesCount;
        }
        return this.mTotalSamplesCount + this.mRunningCount;
    }

    public TimeUnit getTimeUnit() {
        return TIMEUNIT;
    }

    public int numRunningTimers() {
        return this.mRunningCount;
    }

    public void startTimer() {
        int i;
        long currentTimeMillis = getCurrentTimeMillis();
        if (this.mRunningCount > 0) {
            this.mTotalElapsedTime += (currentTimeMillis - this.mLastUpdatedTime) * i;
        }
        this.mLastUpdatedTime = currentTimeMillis;
        this.mRunningCount++;
    }

    public void stopTimer() {
        long currentTimeMillis = getCurrentTimeMillis();
        int i = this.mRunningCount;
        if (i > 0) {
            this.mTotalElapsedTime += (currentTimeMillis - this.mLastUpdatedTime) * i;
            this.mRunningCount = i - 1;
            this.mLastUpdatedTime = currentTimeMillis;
            this.mTotalSamplesCount++;
        }
    }
}
