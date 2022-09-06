package com.amazon.communication.time;

import android.os.SystemClock;
/* loaded from: classes12.dex */
public final class AndroidTimeSource implements TimeSource {
    public static final AndroidTimeSource INSTANCE = new AndroidTimeSource();

    private AndroidTimeSource() {
    }

    @Override // com.amazon.communication.time.TimeSource
    public long currentTimeMillis() {
        return SystemClock.elapsedRealtime();
    }
}
