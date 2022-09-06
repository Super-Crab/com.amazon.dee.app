package com.amazon.communication;

import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.DpThreadFactory;
import com.dp.utils.FailFast;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ProtocolSocketAffinitizedWorkExecutor implements WorkExecutor, Executor {
    private static final long SHUTDOWN_TIMEOUT_MS = 2500;
    protected boolean mIsRunning;
    protected DpThreadFactory mThreadFactory;
    protected final WakeLockHoldingScheduledThreadPoolExecutor[] mThreadPools;
    protected final PowerManagerWrapper.WakeLock mWakeLock;
    private static final DPLogger log = new DPLogger("TComm.ProtocolSocketAffinitizedWorkExecutor");
    private static final long WAKE_LOCK_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(1);

    public ProtocolSocketAffinitizedWorkExecutor(int i, PowerManagerWrapper.WakeLock wakeLock) {
        if (i > 0) {
            this.mThreadPools = new WakeLockHoldingScheduledThreadPoolExecutor[i];
            this.mWakeLock = wakeLock;
            this.mWakeLock.setReferenceCounted(true);
            this.mThreadFactory = newThreadFactory(ThreadName.HANDLER);
            this.mIsRunning = true;
            return;
        }
        throw new IllegalArgumentException("Argument: nThreads must be greater than zero");
    }

    private synchronized WakeLockHoldingScheduledThreadPoolExecutor getThreadPool(int i) {
        FailFast.expectTrue(i >= 0);
        FailFast.expectTrue(i < this.mThreadPools.length);
        if (this.mThreadPools[i] == null) {
            this.mThreadPools[i] = new WakeLockHoldingScheduledThreadPoolExecutor(1, this.mThreadFactory, (Thread.UncaughtExceptionHandler) null, this.mWakeLock, WAKE_LOCK_TIMEOUT_MS);
            log.verbose("getThreadPool", "created thread pool", "index", Integer.valueOf(i));
        }
        return this.mThreadPools[i];
    }

    private WakeLockHoldingScheduledThreadPoolExecutor mapProtocolSocketToThreadPool(ProtocolSocket protocolSocket) {
        int abs = Math.abs(protocolSocket.hashCode()) % this.mThreadPools.length;
        log.verbose("mapProtocolSocketToThreadPool", "mapped socket to index", "index", Integer.valueOf(abs));
        return getThreadPool(abs);
    }

    private void verifyParameters(ProtocolSocket protocolSocket, Callable<Void> callable, long j) throws IllegalArgumentException {
        if (protocolSocket != null) {
            if (callable == null) {
                throw new IllegalArgumentException("Argument: work cannot be null");
            }
            if (j < 0) {
                throw new IllegalArgumentException("Argument: delayMs cannot be less than zero");
            }
            return;
        }
        throw new IllegalArgumentException("Argument: socket cannot be null");
    }

    @Override // com.amazon.communication.WorkExecutor
    public synchronized void doBackgroundWork(Callable<Void> callable) {
        verifyParameters(callable, 0L);
        if (this.mIsRunning) {
            getThreadPool(0).submit(callable);
        } else {
            log.warn("doBackgroundWork", "work submitted after shutdown() was invoked", new Object[0]);
        }
    }

    @Override // com.amazon.communication.WorkExecutor
    public synchronized void doBackgroundWorkAfter(Callable<Void> callable, long j) {
        verifyParameters(callable, j);
        if (this.mIsRunning) {
            getThreadPool(0).schedule(callable, j, TimeUnit.MILLISECONDS);
        } else {
            log.warn("doBackgroundWorkAfter", "work submitted after shutdown() was invoked", new Object[0]);
        }
    }

    @Override // com.amazon.communication.WorkExecutor
    public synchronized void enqueueWork(ProtocolSocket protocolSocket, Callable<Void> callable) {
        verifyParameters(protocolSocket, callable, 0L);
        if (this.mIsRunning) {
            mapProtocolSocketToThreadPool(protocolSocket).submit(callable);
        } else {
            log.warn("enqueueWork", "work submitted after shutdown() was invoked", new Object[0]);
        }
    }

    @Override // com.amazon.communication.WorkExecutor
    public synchronized void enqueueWorkAfter(ProtocolSocket protocolSocket, Callable<Void> callable, long j) {
        verifyParameters(protocolSocket, callable, j);
        if (this.mIsRunning) {
            mapProtocolSocketToThreadPool(protocolSocket).schedule(callable, j, TimeUnit.MILLISECONDS);
        } else {
            log.warn("enqueueWorkAfter", "work submitted after shutdown() was invoked", new Object[0]);
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.ProtocolSocketAffinitizedWorkExecutor.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                runnable.run();
                return null;
            }
        });
    }

    protected DpThreadFactory newThreadFactory(String str) {
        return new DpThreadFactory(str);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void shutdown() {
        WakeLockHoldingScheduledThreadPoolExecutor[] wakeLockHoldingScheduledThreadPoolExecutorArr;
        log.verbose("shutdown", "shutdown was invoked", new Object[0]);
        synchronized (this) {
            if (!this.mIsRunning) {
                log.debug("shutdown", "shutdown was invoked more than once; ignoring", new Object[0]);
                return;
            }
            this.mIsRunning = false;
            for (WakeLockHoldingScheduledThreadPoolExecutor wakeLockHoldingScheduledThreadPoolExecutor : this.mThreadPools) {
                if (wakeLockHoldingScheduledThreadPoolExecutor != null) {
                    wakeLockHoldingScheduledThreadPoolExecutor.properShutdown(SHUTDOWN_TIMEOUT_MS, SHUTDOWN_TIMEOUT_MS, SHUTDOWN_TIMEOUT_MS);
                }
            }
        }
    }

    private void verifyParameters(Callable<Void> callable, long j) throws IllegalArgumentException {
        if (callable != null) {
            if (j < 0) {
                throw new IllegalArgumentException("Argument: delayMs cannot be less than zero");
            }
            return;
        }
        throw new IllegalArgumentException("Argument: work cannot be null");
    }
}
