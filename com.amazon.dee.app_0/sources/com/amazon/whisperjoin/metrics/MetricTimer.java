package com.amazon.whisperjoin.metrics;

import android.os.SystemClock;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
/* loaded from: classes13.dex */
public class MetricTimer {
    private static final String TAG = "MetricTimer";
    private long mStartTime = -1;

    public void start() {
        if (this.mStartTime != -1) {
            WJLog.d(TAG, " recorder start called again before terminal event");
        }
        this.mStartTime = SystemClock.elapsedRealtime();
    }

    public long stop() {
        long j = this.mStartTime;
        if (j == -1) {
            return j;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.mStartTime;
        this.mStartTime = -1L;
        return elapsedRealtime;
    }
}
