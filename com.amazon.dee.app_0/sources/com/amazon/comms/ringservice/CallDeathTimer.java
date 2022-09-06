package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes12.dex */
public class CallDeathTimer {
    private static final long CALL_ACKNOWLEDGE_DEATH_INTERVAL_IN_MS = 2000;
    private static final int CALL_ACKNOWLEDGE_DEATH_INTERVAL_IN_SECONDS = 2;
    private static final boolean DEAMON = true;
    private static final String UNKNOWN_CALL_ID = "???";
    private static final CommsLogger log = CommsLogger.getLogger(CallDeathTimer.class);
    private final Call call;
    private final MetricsSession metrics;
    private final boolean shouldDieIfCallNotAcknowledged;
    private boolean deathTimerCancelled = false;
    private final DeathTask deathTask = new DeathTask();
    private final Timer deathTimer = new Timer("CallDeathTimer", true);

    /* loaded from: classes12.dex */
    private class DeathTask extends TimerTask {
        private DeathTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CallDeathTimer.log.i("DeathTask.run()");
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Dropping the call because the UI didn't acknowledged: ");
            outline107.append(CallDeathTimer.log.sensitiveCallId(CallDeathTimer.this.call.getCallId()));
            RuntimeException runtimeException = new RuntimeException(outline107.toString());
            CallDeathTimer.this.metrics.recordCount("RingService", "CallDroppedNotAcknowledged", 1.0d, CallDeathTimer.this.call.getCallId());
            CallDeathTimer.log.e(runtimeException.getMessage(), runtimeException);
            throw runtimeException;
        }
    }

    public CallDeathTimer(MetricsSession metricsSession, Call call, boolean z) {
        this.metrics = metricsSession;
        this.call = call;
        this.shouldDieIfCallNotAcknowledged = z;
    }

    public void cancel() {
        log.i("CallDeathTimer.cancel()");
        this.deathTimer.cancel();
        this.deathTimerCancelled = true;
    }

    public void schedule() {
        log.i("CallDeathTimer.schedule()");
        if (!this.shouldDieIfCallNotAcknowledged || this.deathTimerCancelled) {
            return;
        }
        log.i(String.format("Scheduling call auto drop in %s ms", 2000L));
        this.deathTimer.schedule(this.deathTask, 2000L);
    }
}
