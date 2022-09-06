package com.amazon.communication;

import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class PeriodicTimeoutInstanceRemover<E> {
    private static final long MIN_PERIOD_MS = 1800000;
    private static final DPLogger log = new DPLogger("TComm.PeriodicTimeoutInstanceRemover");
    private int mFailedToRemoveCount;
    private final InstanceTracker<E> mInstanceTracker;
    private long mLastCleanTimestampMs;
    private final long mPeriodMs;
    private int mRemovedInstanceCount;

    public PeriodicTimeoutInstanceRemover(InstanceTracker<E> instanceTracker, long j) {
        if (instanceTracker != null) {
            if (j >= 1800000) {
                this.mInstanceTracker = instanceTracker;
                this.mPeriodMs = j;
                this.mRemovedInstanceCount = 0;
                this.mFailedToRemoveCount = 0;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("period must at least be 1800000. Actual period: ", j));
        }
        throw new IllegalArgumentException("instanceTracker must not be null");
    }

    private boolean isTimeUp() {
        return GlobalTimeSource.INSTANCE.currentTimeMillis() - this.mLastCleanTimestampMs > this.mPeriodMs;
    }

    private void resetTimer() {
        this.mLastCleanTimestampMs = GlobalTimeSource.INSTANCE.currentTimeMillis();
    }

    protected void clean() {
        log.info("clean", "check all tracked instances for timeout", "instance", this);
        for (E e : this.mInstanceTracker.getTrackedInstances()) {
            if (this.mInstanceTracker.isTimedOut(e)) {
                log.info("RemoveTimeoutInstanceRunnable.run", "stop tracking a timeout instance", "instance", e);
                if (this.mInstanceTracker.stopTrackingInstance(e)) {
                    DPLogger dPLogger = log;
                    int i = this.mRemovedInstanceCount;
                    this.mRemovedInstanceCount = i + 1;
                    dPLogger.debug("RemoveTimeoutInstanceRunnable.run", "Successful removed instance", "Removed instance count", Integer.valueOf(i));
                } else {
                    DPLogger dPLogger2 = log;
                    int i2 = this.mFailedToRemoveCount;
                    this.mFailedToRemoveCount = i2 + 1;
                    dPLogger2.debug("RemoveTimeoutInstanceRunnable.run", "Failed to remove instance", "mFailedToRemoveCount", Integer.valueOf(i2));
                }
            }
        }
        log.info("RemoveTimeoutInstanceRunnable.run", "done with tracking instances", "mRemovedInstanceCount", Integer.valueOf(this.mRemovedInstanceCount), "mFailedToRemoveCount", Integer.valueOf(this.mFailedToRemoveCount));
    }

    public synchronized void cleanIfTimesUp() {
        if (isTimeUp()) {
            resetTimer();
            clean();
        }
    }
}
