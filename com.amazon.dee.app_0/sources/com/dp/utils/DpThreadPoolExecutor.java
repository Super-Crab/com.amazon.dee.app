package com.dp.utils;

import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class DpThreadPoolExecutor extends ThreadPoolExecutor implements ProperShutdown {
    protected final Thread.UncaughtExceptionHandler mExceptionHandler;
    protected final ThreadGroup mThreadGroup;

    public DpThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this(i, i2, j, timeUnit, blockingQueue, new DpThreadFactory(str), uncaughtExceptionHandler);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        DpThreadPoolUtil.afterExecute(runnable, th, this.mExceptionHandler);
    }

    @Override // com.dp.utils.ProperShutdown
    public void properShutdown() {
        DpThreadPoolUtil.properShutdown(this, this.mThreadGroup);
    }

    public DpThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        super(i, i2, j, timeUnit, blockingQueue);
        if (dpThreadFactory != null) {
            this.mThreadGroup = dpThreadFactory.getThreadGroup();
            this.mExceptionHandler = uncaughtExceptionHandler;
            setThreadFactory(dpThreadFactory);
            return;
        }
        throw new IllegalArgumentException("ThreadFactory must not be null");
    }

    @Override // com.dp.utils.ProperShutdown
    public void properShutdown(long j, long j2, long j3) {
        DpThreadPoolUtil.properShutdown(this, this.mThreadGroup, j, j2, j3);
    }
}
