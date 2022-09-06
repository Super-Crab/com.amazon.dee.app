package com.dp.utils;

import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class DpExecutors {
    private DpExecutors() {
    }

    public static DpThreadPoolExecutor newCachedThreadPool(DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), dpThreadFactory, uncaughtExceptionHandler);
    }

    public static DpThreadPoolExecutor newFixedThreadPool(int i, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), dpThreadFactory, uncaughtExceptionHandler);
    }

    public static DpScheduledThreadPoolExecutor newScheduledThreadPool(int i, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpScheduledThreadPoolExecutor(i, dpThreadFactory, uncaughtExceptionHandler);
    }

    public static DpScheduledThreadPoolExecutor newSingleThreadScheduledExecutor(DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpScheduledThreadPoolExecutor(1, dpThreadFactory, uncaughtExceptionHandler);
    }

    public static ExecutorService newSynchronousExecutor() {
        return new SynchronousExecutor();
    }

    public static DpThreadPoolExecutor newCachedThreadPool(String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), str, uncaughtExceptionHandler);
    }

    public static DpThreadPoolExecutor newFixedThreadPool(int i, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), str, uncaughtExceptionHandler);
    }

    public static DpScheduledThreadPoolExecutor newScheduledThreadPool(int i, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpScheduledThreadPoolExecutor(i, str, uncaughtExceptionHandler);
    }

    public static DpScheduledThreadPoolExecutor newSingleThreadScheduledExecutor(String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new DpScheduledThreadPoolExecutor(1, str, uncaughtExceptionHandler);
    }

    public static DpThreadPoolExecutor newCachedThreadPool(String str) {
        return new DpThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), str, (Thread.UncaughtExceptionHandler) null);
    }

    public static DpThreadPoolExecutor newFixedThreadPool(int i, String str) {
        return new DpThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), str, (Thread.UncaughtExceptionHandler) null);
    }

    public static DpScheduledThreadPoolExecutor newScheduledThreadPool(int i, String str) {
        return new DpScheduledThreadPoolExecutor(i, str, (Thread.UncaughtExceptionHandler) null);
    }

    public static DpScheduledThreadPoolExecutor newSingleThreadScheduledExecutor(String str) {
        return new DpScheduledThreadPoolExecutor(1, str, (Thread.UncaughtExceptionHandler) null);
    }
}
