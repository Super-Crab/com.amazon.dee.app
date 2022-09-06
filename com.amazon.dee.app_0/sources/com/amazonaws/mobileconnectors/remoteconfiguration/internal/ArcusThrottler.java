package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.os.SystemClock;
/* loaded from: classes13.dex */
public class ArcusThrottler {
    public static final int CAUSE_FAILED_SYNC = 20;
    public static final int CAUSE_NONE = 0;
    public static final int CAUSE_SUCCESSFUL_SYNC = 10;
    public static final int CAUSE_THROTTLED_SYNC = 30;
    private static final long DEFAULT_SYNC_JITTER_IN_MS = 5000;
    private static final long DEFAULT_TIME_BETWEEN_SYNCS_IN_MS = 900000;
    private static final long MAX_EXP_BACKOFF_WINDOW_SIZE_IN_MS = 900000;
    private static final long ONE_SECOND_MS = 1000;
    private int mSyncAttempts;
    private long mNextSyncAttemptTime = 0;
    private int mCause = 0;

    private long getCurrentWindowSizeInMS() {
        long j = 1000 << (this.mSyncAttempts + 1);
        if (j <= 0 || j > 900000) {
            return 900000L;
        }
        return j;
    }

    private long getJitterInMS() {
        return (long) (Math.random() * 5000.0d);
    }

    private long getRandomTimeInsideCurrentWindowInMS() {
        return (long) (Math.random() * getCurrentWindowSizeInMS());
    }

    public int getCause() {
        return this.mCause;
    }

    public long getTimeToNextSyncInMS() {
        return Math.max(0L, this.mNextSyncAttemptTime - SystemClock.elapsedRealtime());
    }

    public boolean isSyncAllowedRightNow() {
        return getTimeToNextSyncInMS() == 0;
    }

    public void updateSyncTimeAfterFailure() {
        if (getCurrentWindowSizeInMS() < 900000) {
            this.mSyncAttempts++;
        }
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + getRandomTimeInsideCurrentWindowInMS();
        this.mCause = 20;
    }

    public void updateSyncTimeAfterSuccess() {
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + 900000;
        this.mCause = 10;
    }

    public void updateSyncTimeAfterThrottle(long j) {
        if (j <= 0) {
            j = 900000;
        }
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = Math.min(j, 900000L) + SystemClock.elapsedRealtime() + getJitterInMS();
        this.mCause = 30;
    }
}
