package amazon.speech.simclient;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TtsPrewarmTracker {
    private static final String TAG = GeneratedOutlineSupport1.outline39(TtsPrewarmTracker.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private final Future mPrewarmComplete;
    private final long mPrewarmExpiryTimeMs;

    public TtsPrewarmTracker(Future future, long j) {
        if (future != null) {
            if (j > 0) {
                this.mPrewarmComplete = future;
                this.mPrewarmExpiryTimeMs = System.currentTimeMillis() + j;
                return;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public void await() {
        long remainingWaitTimeMs = getRemainingWaitTimeMs();
        if (remainingWaitTimeMs <= 0) {
            Log.i(TAG, String.format("Prewarm already expired %d ms ago & will be ignored.", Long.valueOf(-remainingWaitTimeMs)));
            return;
        }
        try {
            this.mPrewarmComplete.get(remainingWaitTimeMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e(TAG, "TtsPrewarm interrupted", e);
        } catch (ExecutionException e2) {
            e = e2;
            Log.wtf(TAG, "Unexpected exception while waiting for prewarm future", e);
        } catch (TimeoutException e3) {
            e = e3;
            Log.wtf(TAG, "Unexpected exception while waiting for prewarm future", e);
        }
    }

    public long getRemainingWaitTimeMs() {
        return this.mPrewarmExpiryTimeMs - System.currentTimeMillis();
    }
}
