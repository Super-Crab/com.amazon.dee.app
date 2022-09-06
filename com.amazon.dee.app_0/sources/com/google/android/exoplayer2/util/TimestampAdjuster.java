package com.google.android.exoplayer2.util;

import androidx.annotation.GuardedBy;
import com.google.android.exoplayer2.C;
/* loaded from: classes2.dex */
public final class TimestampAdjuster {
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;
    @GuardedBy("this")
    private long firstSampleTimestampUs;
    @GuardedBy("this")
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    @GuardedBy("this")
    private long timestampOffsetUs;

    public TimestampAdjuster(long j) {
        reset(j);
    }

    public static long ptsToUs(long j) {
        return (j * 1000000) / 90000;
    }

    public static long usToNonWrappedPts(long j) {
        return (j * 90000) / 1000000;
    }

    public static long usToWrappedPts(long j) {
        return usToNonWrappedPts(j) % MAX_PTS_PLUS_ONE;
    }

    public synchronized long adjustSampleTimestamp(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        if (this.timestampOffsetUs == C.TIME_UNSET) {
            this.timestampOffsetUs = (this.firstSampleTimestampUs == MODE_SHARED ? ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue() : this.firstSampleTimestampUs) - j;
            notifyAll();
        }
        this.lastUnadjustedTimestampUs = j;
        return j + this.timestampOffsetUs;
    }

    public synchronized long adjustTsTimestamp(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        if (this.lastUnadjustedTimestampUs != C.TIME_UNSET) {
            long usToNonWrappedPts = usToNonWrappedPts(this.lastUnadjustedTimestampUs);
            long j2 = (4294967296L + usToNonWrappedPts) / MAX_PTS_PLUS_ONE;
            long j3 = ((j2 - 1) * MAX_PTS_PLUS_ONE) + j;
            long j4 = (j2 * MAX_PTS_PLUS_ONE) + j;
            j = Math.abs(j3 - usToNonWrappedPts) < Math.abs(j4 - usToNonWrappedPts) ? j3 : j4;
        }
        return adjustSampleTimestamp(ptsToUs(j));
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j;
        if (this.firstSampleTimestampUs != Long.MAX_VALUE && this.firstSampleTimestampUs != MODE_SHARED) {
            j = this.firstSampleTimestampUs;
        }
        j = C.TIME_UNSET;
        return j;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        return this.lastUnadjustedTimestampUs != C.TIME_UNSET ? this.lastUnadjustedTimestampUs + this.timestampOffsetUs : getFirstSampleTimestampUs();
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized void reset(long j) {
        this.firstSampleTimestampUs = j;
        this.timestampOffsetUs = j == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = C.TIME_UNSET;
    }

    public synchronized void sharedInitializeOrWait(boolean z, long j) throws InterruptedException {
        Assertions.checkState(this.firstSampleTimestampUs == MODE_SHARED);
        if (this.timestampOffsetUs != C.TIME_UNSET) {
            return;
        }
        if (z) {
            this.nextSampleTimestampUs.set(Long.valueOf(j));
        } else {
            while (this.timestampOffsetUs == C.TIME_UNSET) {
                wait();
            }
        }
    }
}
