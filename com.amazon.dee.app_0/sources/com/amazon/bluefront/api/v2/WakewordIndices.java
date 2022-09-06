package com.amazon.bluefront.api.v2;
/* loaded from: classes11.dex */
public class WakewordIndices {
    private long mEndSample;
    private long mStartSample;

    WakewordIndices() {
    }

    public long getEndSample() {
        return this.mEndSample;
    }

    public long getStartSample() {
        return this.mStartSample;
    }

    void setEndSample(long j) {
        this.mEndSample = j;
    }

    void setStartSample(long j) {
        this.mStartSample = j;
    }

    public WakewordIndices(long j, long j2) {
        this.mStartSample = j;
        this.mEndSample = j2;
    }
}
