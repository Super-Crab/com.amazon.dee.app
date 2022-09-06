package com.dp.utils;

import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.Thread;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class DpThreadPoolUtil {
    private static final long SHUTDOWN_NOW_TIMEOUT_MS = 2000;
    private static final long SHUTDOWN_TIMEOUT_MS = 2000;
    private static final long THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final DPLogger log = new DPLogger();

    private DpThreadPoolUtil() {
    }

    public static void afterExecute(Runnable runnable, Throwable th, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler != null) {
            Thread.currentThread().setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        if (th == null) {
            if (runnable instanceof Future) {
                Future future = (Future) runnable;
                if (future.isDone()) {
                    try {
                        future.get();
                    } catch (InterruptedException unused) {
                        log.verbose("afterExecute", "interrupted", new Object[0]);
                        Thread.currentThread().interrupt();
                    } catch (CancellationException unused2) {
                        log.debug("afterExecute", "hit CancellationException on this thread, this  is expected if you canceled a task", "thread", Thread.currentThread().getName());
                    } catch (ExecutionException e) {
                        th = e.getCause();
                    }
                }
            }
            th = null;
        }
        if (th != null) {
            log.error("afterExecute", "unexpected exception", "thread", Thread.currentThread().getName(), th);
            if (uncaughtExceptionHandler == null) {
                return;
            }
            uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
    }

    public static ThreadGroup buildThreadGroup(String str) {
        return new ThreadGroup(GeneratedOutlineSupport1.outline72(str, "-Group"));
    }

    public static void properShutdown(ExecutorService executorService, ThreadGroup threadGroup) {
        properShutdown(executorService, threadGroup, 2000L, 2000L, 2000L);
    }

    private static void shutdownThreadpool(ExecutorService executorService, long j, long j2) throws InterruptedException {
        executorService.shutdown();
        if (!executorService.awaitTermination(j, TimeUnit.MILLISECONDS)) {
            executorService.shutdownNow();
            if (executorService.awaitTermination(j2, TimeUnit.MILLISECONDS)) {
                return;
            }
            log.error("shutdownThreadpool", "failed to shut down the thread pool", new Object[0]);
        }
    }

    private static void waitForThreadsToTerminate(ThreadGroup threadGroup, long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + j;
        Thread[] threadArr = new Thread[1];
        while (threadGroup.enumerate(threadArr) > 0) {
            Thread thread = threadArr[0];
            long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
            if (currentTimeMillis2 <= 0) {
                log.error("waitForThreadsToTerminate", "timed out waiting for threads to join", new Object[0]);
                return;
            } else if (!thread.isDaemon()) {
                thread.join(currentTimeMillis2);
                if (thread.getState() != Thread.State.TERMINATED) {
                    log.error("waitForThreadsToTerminate", "thread never reached TERMINATED state", new Object[0]);
                    return;
                }
            }
        }
    }

    public static void properShutdown(ExecutorService executorService, ThreadGroup threadGroup, long j, long j2, long j3) {
        if (executorService != null) {
            if (j <= 0) {
                throw new IllegalArgumentException("shutdownTimeoutMs must be greater than zero");
            }
            if (j2 <= 0) {
                throw new IllegalArgumentException("shutdownNowTimeoutMs must be greater than zero");
            }
            if (j3 > 0) {
                try {
                    shutdownThreadpool(executorService, j, j2);
                    if (threadGroup == null) {
                        return;
                    }
                    waitForThreadsToTerminate(threadGroup, j3);
                    return;
                } catch (InterruptedException e) {
                    log.error("properShutdown", "interrupted while trying to shut down the thread pool", e);
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            throw new IllegalArgumentException("threadJoinTimeoutMs must be greater than zero");
        }
        throw new IllegalArgumentException("threadPool must not be null.");
    }
}
