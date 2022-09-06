package com.amazon.comms.instrumentation;

import android.os.SystemClock;
/* loaded from: classes11.dex */
public class ClocksImpl implements Clocks {
    @Override // com.amazon.comms.instrumentation.Clocks
    public long calculateRelativeTimestamp(long j) {
        return j - (getCurrentTimeMillis() - getElapsedRealtime());
    }

    @Override // com.amazon.comms.instrumentation.Clocks
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.comms.instrumentation.Clocks
    public long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
