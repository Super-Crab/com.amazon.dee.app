package com.amazon.communication;

import com.amazon.communication.PowerManagerWrapper;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.DpScheduledThreadPoolExecutor;
import com.dp.utils.DpThreadFactory;
import java.lang.Thread;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class WakeLockHoldingScheduledThreadPoolExecutor extends DpScheduledThreadPoolExecutor {
    private static final long NO_WAKELOCK_TIMEOUT = 0;
    private static final DPLogger log = new DPLogger("TComm.WakeLockHoldingScheduledThreadPoolExecutor");
    private final PowerManagerWrapper.WakeLock mWakeLock;
    private final long mWakelockTimeout;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class WakeLockHoldingCallable<T> implements Callable<T> {
        private final Callable<T> mDelegate;
        private final PowerManagerWrapper.WakeLock mWakeLock;
        private final AtomicBoolean mWakeLockReleased = new AtomicBoolean(false);

        public WakeLockHoldingCallable(Callable<T> callable, PowerManagerWrapper.WakeLock wakeLock) {
            this.mDelegate = callable;
            this.mWakeLock = wakeLock;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            try {
                return this.mDelegate.call();
            } finally {
                releaseWakeLock();
            }
        }

        public void releaseWakeLock() {
            if (this.mWakeLockReleased.compareAndSet(false, true)) {
                WakeLockHoldingScheduledThreadPoolExecutor.log.verbose("WakeLockHoldingCallable.releaseWakeLock", "releasing wakeLock", "wakeLock", this.mWakeLock);
                this.mWakeLock.release();
                return;
            }
            WakeLockHoldingScheduledThreadPoolExecutor.log.verbose("WakeLockHoldingCallable.releaseWakeLock", "wakeLock already released", "wakeLock", this.mWakeLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class WakeLockHoldingFuture<T> implements ScheduledFuture<T> {
        private WakeLockHoldingCallable<T> mCallable;
        private ScheduledFuture<T> mDelegate;

        public WakeLockHoldingFuture(ScheduledFuture<T> scheduledFuture, WakeLockHoldingCallable<T> wakeLockHoldingCallable) {
            this.mDelegate = scheduledFuture;
            this.mCallable = wakeLockHoldingCallable;
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            WakeLockHoldingScheduledThreadPoolExecutor.log.debug("WakeLockHoldingFuture.cancel", "cancel scheduled task", "mayInterruptIfRunning", Boolean.valueOf(z));
            boolean cancel = this.mDelegate.cancel(z);
            if (cancel) {
                this.mCallable.releaseWakeLock();
            }
            return cancel;
        }

        @Override // java.util.concurrent.Future
        public T get() throws CancellationException, ExecutionException, InterruptedException {
            return this.mDelegate.get();
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return this.mDelegate.getDelay(timeUnit);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.mDelegate.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.mDelegate.isDone();
        }

        @Override // java.lang.Comparable
        public int compareTo(Delayed delayed) {
            return this.mDelegate.compareTo(delayed);
        }

        @Override // java.util.concurrent.Future
        public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
            return this.mDelegate.get(j, timeUnit);
        }
    }

    public WakeLockHoldingScheduledThreadPoolExecutor(int i, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, PowerManagerWrapper.WakeLock wakeLock) {
        super(i, str, uncaughtExceptionHandler);
        this.mWakeLock = wakeLock;
        this.mWakelockTimeout = 0L;
        this.mWakeLock.setReferenceCounted(true);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return schedule(Executors.callable(runnable), j, timeUnit, this.mWakeLock);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return schedule(callable, 0L, TimeUnit.NANOSECONDS, this.mWakeLock);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return schedule(callable, j, timeUnit, this.mWakeLock);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private <T> ScheduledFuture<T> schedule(Callable<T> callable, long j, TimeUnit timeUnit, PowerManagerWrapper.WakeLock wakeLock) {
        log.verbose("schedule", "acquiring wakeLock", "wakeLock", wakeLock, "delay", Long.valueOf(timeUnit.toMillis(j)));
        if (wakeLock != null) {
            long j2 = this.mWakelockTimeout;
            if (j2 == 0) {
                wakeLock.acquire();
            } else {
                wakeLock.acquire(j2);
            }
            try {
                WakeLockHoldingCallable wakeLockHoldingCallable = new WakeLockHoldingCallable(callable, wakeLock);
                return new WakeLockHoldingFuture(super.schedule(wakeLockHoldingCallable, j, timeUnit), wakeLockHoldingCallable);
            } catch (RuntimeException e) {
                log.error("schedule", ADMRegistrationConstants.CALL_EXCEPTION, e);
                wakeLock.release();
                throw e;
            }
        }
        throw new IllegalArgumentException("WakeLock cannot be null");
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return schedule(Executors.callable(runnable), 0L, TimeUnit.NANOSECONDS, this.mWakeLock);
    }

    public WakeLockHoldingScheduledThreadPoolExecutor(int i, String str, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, PowerManagerWrapper.WakeLock wakeLock, long j) {
        super(i, str, uncaughtExceptionHandler);
        this.mWakeLock = wakeLock;
        this.mWakelockTimeout = j;
        this.mWakeLock.setReferenceCounted(true);
    }

    public WakeLockHoldingScheduledThreadPoolExecutor(int i, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, PowerManagerWrapper.WakeLock wakeLock) {
        super(i, dpThreadFactory, uncaughtExceptionHandler);
        this.mWakeLock = wakeLock;
        this.mWakelockTimeout = 0L;
        this.mWakeLock.setReferenceCounted(true);
    }

    public WakeLockHoldingScheduledThreadPoolExecutor(int i, DpThreadFactory dpThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, PowerManagerWrapper.WakeLock wakeLock, long j) {
        super(i, dpThreadFactory, uncaughtExceptionHandler);
        this.mWakeLock = wakeLock;
        this.mWakelockTimeout = j;
        this.mWakeLock.setReferenceCounted(true);
    }
}
