package com.amazon.alexa.utils;

import android.os.SystemClock;
import java.util.Date;
/* loaded from: classes10.dex */
public class TimeProvider {
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealTime() {
        return SystemClock.elapsedRealtime();
    }

    public Date getCurrentTimestamp() {
        return new Date();
    }

    public long toEpochTime(long j) {
        return (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + j;
    }
}
