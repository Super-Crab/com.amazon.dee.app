package com.amazon.deecomms.common.util;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
/* loaded from: classes12.dex */
public abstract class StatePoller {
    static final long SLEEP_TIME = 50;

    @NonNull
    protected abstract CommsLogger getPollerLogger();

    protected long getPollerSleepTime() {
        return SLEEP_TIME;
    }

    public abstract boolean isInExpectedState();

    boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }

    public final boolean waitForExpectedState(long j) {
        if (isOnMainThread()) {
            getPollerLogger().w("Should not be called on main looper, trying isInExpectedState()");
            return isInExpectedState();
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (!isInExpectedState()) {
            if (System.currentTimeMillis() - currentTimeMillis > j) {
                CommsLogger pollerLogger = getPollerLogger();
                pollerLogger.w("waitForExpectedState: false, exceeded max time (" + j + " ms)");
                return false;
            }
            sleep(getPollerSleepTime());
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        CommsLogger pollerLogger2 = getPollerLogger();
        pollerLogger2.d("waitForExpectedState: true, elapsed=" + currentTimeMillis2 + " ms");
        return true;
    }
}
