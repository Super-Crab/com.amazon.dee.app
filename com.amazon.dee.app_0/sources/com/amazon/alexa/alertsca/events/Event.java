package com.amazon.alexa.alertsca.events;

import android.os.SystemClock;
/* loaded from: classes6.dex */
public abstract class Event {
    private final long createdTime = SystemClock.elapsedRealtime();

    public long getCreatedTimeMilliseconds() {
        return this.createdTime;
    }
}
