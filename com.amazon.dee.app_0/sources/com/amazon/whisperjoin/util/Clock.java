package com.amazon.whisperjoin.util;

import android.os.SystemClock;
/* loaded from: classes13.dex */
public class Clock {
    public long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long uptimeMillis() {
        return SystemClock.uptimeMillis();
    }
}
