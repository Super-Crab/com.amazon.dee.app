package com.amazon.alexa.photos.util;

import android.os.SystemClock;
/* loaded from: classes9.dex */
public class SystemUtility {
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealTimeMillis() {
        return SystemClock.elapsedRealtime();
    }
}
