package com.amazon.communication;
/* loaded from: classes12.dex */
public class ExponentialBackoffWaitCalculator {
    private int mBackoffCoefficient;
    private long mInitialBackoffMs;
    private double mJitterFactor;
    private long mMaxBackoffMs;
    private int mRetries;

    public ExponentialBackoffWaitCalculator(long j, long j2, int i, double d) {
        this.mInitialBackoffMs = j;
        this.mMaxBackoffMs = j2;
        if (this.mMaxBackoffMs >= this.mInitialBackoffMs) {
            this.mBackoffCoefficient = i;
            this.mJitterFactor = d;
            return;
        }
        throw new IllegalArgumentException("initial backoff cannot be greator than max backoff");
    }

    private synchronized void resetRetries() {
        this.mRetries = 0;
    }

    public synchronized int getRetries() {
        return this.mRetries;
    }

    public synchronized long getWaitMs() {
        double random;
        double d = this.mInitialBackoffMs;
        if (this.mRetries > 0) {
            d += this.mBackoffCoefficient * Math.pow(2.0d, this.mRetries);
        }
        random = (Math.random() * d * this.mJitterFactor) + d;
        this.mRetries++;
        if (random > this.mMaxBackoffMs) {
            random = this.mMaxBackoffMs;
        }
        return (long) random;
    }

    public synchronized void reset() {
        resetRetries();
    }

    public synchronized void reset(long j, long j2, int i, double d) {
        resetRetries();
        this.mInitialBackoffMs = j;
        this.mMaxBackoffMs = j2;
        this.mBackoffCoefficient = i;
        this.mJitterFactor = d;
    }
}
