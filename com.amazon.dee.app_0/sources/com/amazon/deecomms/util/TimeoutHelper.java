package com.amazon.deecomms.util;

import android.os.SystemClock;
/* loaded from: classes12.dex */
public class TimeoutHelper {
    private long startedAt;
    private long timeoutDuration;

    public synchronized boolean hasTimedOut() {
        return SystemClock.elapsedRealtime() - this.startedAt > this.timeoutDuration;
    }

    public synchronized void startTimeOut(long j) {
        this.startedAt = SystemClock.elapsedRealtime();
        this.timeoutDuration = j;
    }
}
