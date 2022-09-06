package com.dp.utils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
class SynchronousExecutor extends AbstractExecutorService {
    private AtomicBoolean mShutdown = new AtomicBoolean(false);
    private CountDownLatch mShutdownSignal = new CountDownLatch(1);

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        try {
            return this.mShutdownSignal.await(j, timeUnit);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (!this.mShutdown.get()) {
            runnable.run();
            return;
        }
        throw new RejectedExecutionException("Executor already shutdown");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.mShutdown.get();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.mShutdown.set(true);
        this.mShutdownSignal.countDown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.emptyList();
    }
}
