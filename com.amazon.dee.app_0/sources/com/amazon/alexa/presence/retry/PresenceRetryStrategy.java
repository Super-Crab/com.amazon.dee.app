package com.amazon.alexa.presence.retry;

import android.util.Log;
import com.amazon.alexa.presence.Presence;
/* loaded from: classes9.dex */
public class PresenceRetryStrategy implements RetryStrategy {
    private static final long INITIAL_DELAY_INTERVAL_MILLIS = 1000;
    private static final int RETRY_COUNT = 3;
    private static final String TAG = Presence.tag();
    private static final Double EXPONENTIAL_RETRY_FACTOR = Double.valueOf(2.0d);

    private long getBackoffInterval(long j, double d, int i) {
        return (long) (Math.pow(d, i) * j);
    }

    @Override // com.amazon.alexa.presence.retry.RetryStrategy
    public boolean apply(Runnable runnable) {
        for (int i = 0; i < 3; i++) {
            try {
                runnable.run();
                return true;
            } catch (RetriableException unused) {
                try {
                    Thread.sleep(getBackoffInterval(1000L, EXPONENTIAL_RETRY_FACTOR.doubleValue(), i));
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupted exception while waiting to retry", e);
                }
            }
        }
        return false;
    }
}
