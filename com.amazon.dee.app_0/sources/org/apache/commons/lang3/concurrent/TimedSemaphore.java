package org.apache.commons.lang3.concurrent;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
/* loaded from: classes4.dex */
public class TimedSemaphore {
    public static final int NO_LIMIT = 0;
    private static final int THREAD_POOL_SIZE = 1;
    private int acquireCount;
    private final ScheduledExecutorService executorService;
    private int lastCallsPerPeriod;
    private int limit;
    private final boolean ownExecutor;
    private final long period;
    private long periodCount;
    private boolean shutdown;
    private ScheduledFuture<?> task;
    private long totalAcquireCount;
    private final TimeUnit unit;

    public TimedSemaphore(long j, TimeUnit timeUnit, int i) {
        this(null, j, timeUnit, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0026 A[Catch: all -> 0x003b, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:8:0x0011, B:10:0x0018, B:16:0x0026, B:17:0x002a, B:21:0x0033, B:22:0x003a), top: B:26:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002a A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:8:0x0011, B:10:0x0018, B:16:0x0026, B:17:0x002a, B:21:0x0033, B:22:0x003a), top: B:26:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void acquire() throws java.lang.InterruptedException {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.isShutdown()     // Catch: java.lang.Throwable -> L3b
            if (r0 != 0) goto L33
            java.util.concurrent.ScheduledFuture<?> r0 = r3.task     // Catch: java.lang.Throwable -> L3b
            if (r0 != 0) goto L11
            java.util.concurrent.ScheduledFuture r0 = r3.startTimer()     // Catch: java.lang.Throwable -> L3b
            r3.task = r0     // Catch: java.lang.Throwable -> L3b
        L11:
            int r0 = r3.getLimit()     // Catch: java.lang.Throwable -> L3b
            r1 = 1
            if (r0 <= 0) goto L23
            int r0 = r3.acquireCount     // Catch: java.lang.Throwable -> L3b
            int r2 = r3.getLimit()     // Catch: java.lang.Throwable -> L3b
            if (r0 >= r2) goto L21
            goto L23
        L21:
            r0 = 0
            goto L24
        L23:
            r0 = r1
        L24:
            if (r0 != 0) goto L2a
            r3.wait()     // Catch: java.lang.Throwable -> L3b
            goto L2f
        L2a:
            int r2 = r3.acquireCount     // Catch: java.lang.Throwable -> L3b
            int r2 = r2 + r1
            r3.acquireCount = r2     // Catch: java.lang.Throwable -> L3b
        L2f:
            if (r0 == 0) goto L11
            monitor-exit(r3)
            return
        L33:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L3b
            java.lang.String r1 = "TimedSemaphore is shut down!"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3b
            throw r0     // Catch: java.lang.Throwable -> L3b
        L3b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.TimedSemaphore.acquire():void");
    }

    synchronized void endOfPeriod() {
        this.lastCallsPerPeriod = this.acquireCount;
        this.totalAcquireCount += this.acquireCount;
        this.periodCount++;
        this.acquireCount = 0;
        notifyAll();
    }

    public synchronized int getAcquireCount() {
        return this.acquireCount;
    }

    public synchronized int getAvailablePermits() {
        return getLimit() - getAcquireCount();
    }

    public synchronized double getAverageCallsPerPeriod() {
        double d;
        if (this.periodCount == 0) {
            d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        } else {
            d = this.totalAcquireCount / this.periodCount;
        }
        return d;
    }

    protected ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    public synchronized int getLastAcquiresPerPeriod() {
        return this.lastCallsPerPeriod;
    }

    public final synchronized int getLimit() {
        return this.limit;
    }

    public long getPeriod() {
        return this.period;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public final synchronized void setLimit(int i) {
        this.limit = i;
    }

    public synchronized void shutdown() {
        if (!this.shutdown) {
            if (this.ownExecutor) {
                getExecutorService().shutdownNow();
            }
            if (this.task != null) {
                this.task.cancel(false);
            }
            this.shutdown = true;
        }
    }

    protected ScheduledFuture<?> startTimer() {
        return getExecutorService().scheduleAtFixedRate(new Runnable() { // from class: org.apache.commons.lang3.concurrent.TimedSemaphore.1
            @Override // java.lang.Runnable
            public void run() {
                TimedSemaphore.this.endOfPeriod();
            }
        }, getPeriod(), getPeriod(), getUnit());
    }

    public TimedSemaphore(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit, int i) {
        Validate.inclusiveBetween(1L, Long.MAX_VALUE, j, "Time period must be greater than 0!");
        this.period = j;
        this.unit = timeUnit;
        if (scheduledExecutorService != null) {
            this.executorService = scheduledExecutorService;
            this.ownExecutor = false;
        } else {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            this.executorService = scheduledThreadPoolExecutor;
            this.ownExecutor = true;
        }
        setLimit(i);
    }
}
