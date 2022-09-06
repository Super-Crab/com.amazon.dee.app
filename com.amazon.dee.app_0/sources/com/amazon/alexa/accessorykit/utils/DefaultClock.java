package com.amazon.alexa.accessorykit.utils;

import android.os.SystemClock;
/* loaded from: classes6.dex */
public class DefaultClock implements Clock {
    @Override // com.amazon.alexa.accessorykit.utils.Clock
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
