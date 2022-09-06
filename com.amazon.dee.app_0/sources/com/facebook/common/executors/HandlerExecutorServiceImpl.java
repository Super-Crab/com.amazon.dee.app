package com.facebook.common.executors;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class HandlerExecutorServiceImpl extends AbstractExecutorService implements HandlerExecutorService {
    private final Handler mHandler;

    public HandlerExecutorServiceImpl(Handler handler) {
        this.mHandler = handler;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        this.mHandler.post(command);
    }

    @Override // com.facebook.common.executors.HandlerExecutorService
    public boolean isHandlerThread() {
        return Thread.currentThread() == this.mHandler.getLooper().getThread();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    /* renamed from: newTaskFor  reason: collision with other method in class */
    protected /* bridge */ /* synthetic */ RunnableFuture mo6858newTaskFor(Runnable runnable, @Nullable Object value) {
        return newTaskFor(runnable, (Runnable) value);
    }

    @Override // com.facebook.common.executors.HandlerExecutorService
    public void quit() {
        this.mHandler.getLooper().quit();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        ScheduledFutureImpl newTaskFor = newTaskFor(command, (Runnable) null);
        this.mHandler.postDelayed(newTaskFor, unit.toMillis(delay));
        return newTaskFor;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public /* bridge */ /* synthetic */ Future submit(Runnable task, @Nullable Object result) {
        return mo6861submit(task, (Runnable) result);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> ScheduledFutureImpl<T> newTaskFor(Runnable runnable, @Nullable T value) {
        return new ScheduledFutureImpl<>(this.mHandler, runnable, value);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        ScheduledFutureImpl mo6859newTaskFor = mo6859newTaskFor((Callable) callable);
        this.mHandler.postDelayed(mo6859newTaskFor, unit.toMillis(delay));
        return mo6859newTaskFor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    /* renamed from: newTaskFor */
    public <T> ScheduledFutureImpl<T> mo6859newTaskFor(Callable<T> callable) {
        return new ScheduledFutureImpl<>(this.mHandler, callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public ScheduledFuture<?> submit(Runnable task) {
        return mo6861submit(task, (Runnable) null);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    /* renamed from: submit  reason: collision with other method in class */
    public <T> ScheduledFuture<T> mo6861submit(Runnable task, @Nullable T result) {
        if (task != null) {
            ScheduledFutureImpl<T> newTaskFor = newTaskFor(task, (Runnable) result);
            execute(newTaskFor);
            return newTaskFor;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ScheduledFuture<T> submit(Callable<T> task) {
        if (task != null) {
            ScheduledFutureImpl<T> mo6859newTaskFor = mo6859newTaskFor((Callable) task);
            execute(mo6859newTaskFor);
            return mo6859newTaskFor;
        }
        throw new NullPointerException();
    }
}
