package com.dp.utils;

import java.lang.Thread;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/* loaded from: classes2.dex */
public class DpScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor implements ProperShutdown {
    protected final Thread.UncaughtExceptionHandler mExceptionHandler;
    protected final ThreadGroup mThreadGroup;

    public DpScheduledThreadPoolExecutor(int i, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this(i, new DpThreadFactory(str), uncaughtExceptionHandler);
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

    public DpScheduledThreadPoolExecutor(int i, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        super(i);
        if (dpThreadFactory != null) {
            this.mThreadGroup = dpThreadFactory.getThreadGroup();
            this.mExceptionHandler = uncaughtExceptionHandler;
            setThreadFactory(dpThreadFactory);
            return;
        }
        throw new IllegalArgumentException("threadFactory must not be null");
    }

    @Override // com.dp.utils.ProperShutdown
    public void properShutdown(long j, long j2, long j3) {
        DpThreadPoolUtil.properShutdown(this, this.mThreadGroup, j, j2, j3);
    }
}
