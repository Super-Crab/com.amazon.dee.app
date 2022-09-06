package com.amazon.communication;

import com.amazon.communication.socket.ProtocolSocket;
import com.dp.utils.DpExecutors;
import com.dp.utils.DpScheduledThreadPoolExecutor;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class FixedThreadPoolWorkExecutor implements WorkExecutor, Executor {
    private static final String DEFAULT_PREFIX = "FIXED_POOL_HANDLER_THREAD";
    private static final long SHUTDOWN_TIMEOUT_MS = 2500;
    private final DpScheduledThreadPoolExecutor mExecutor;

    public FixedThreadPoolWorkExecutor(int i, String str) {
        this.mExecutor = DpExecutors.newScheduledThreadPool(i, str);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void doBackgroundWork(Callable<Void> callable) {
        this.mExecutor.submit(callable);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void doBackgroundWorkAfter(Callable<Void> callable, long j) {
        this.mExecutor.schedule(callable, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void enqueueWork(ProtocolSocket protocolSocket, Callable<Void> callable) {
        this.mExecutor.submit(callable);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void enqueueWorkAfter(ProtocolSocket protocolSocket, Callable<Void> callable, long j) {
        this.mExecutor.schedule(callable, j, TimeUnit.MILLISECONDS);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }

    @Override // com.amazon.communication.WorkExecutor
    public void shutdown() {
        this.mExecutor.properShutdown(SHUTDOWN_TIMEOUT_MS, SHUTDOWN_TIMEOUT_MS, SHUTDOWN_TIMEOUT_MS);
    }

    public FixedThreadPoolWorkExecutor(int i) {
        this.mExecutor = DpExecutors.newScheduledThreadPool(i, DEFAULT_PREFIX);
    }

    public FixedThreadPoolWorkExecutor(DpScheduledThreadPoolExecutor dpScheduledThreadPoolExecutor) {
        this.mExecutor = dpScheduledThreadPoolExecutor;
    }
}
