package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@CanIgnoreReturnValue
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ForwardingExecutorService extends ForwardingObject implements ExecutorService {
    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return mo8280delegate().awaitTermination(j, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract ExecutorService mo8280delegate();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        mo8280delegate().execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return mo8280delegate().invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return (T) mo8280delegate().invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return mo8280delegate().isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return mo8280delegate().isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        mo8280delegate().shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return mo8280delegate().shutdownNow();
    }

    /* renamed from: submit */
    public <T> Future<T> mo8278submit(Callable<T> callable) {
        return mo8280delegate().submit(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return mo8280delegate().invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) mo8280delegate().invokeAny(collection, j, timeUnit);
    }

    /* renamed from: submit */
    public Future<?> mo8276submit(Runnable runnable) {
        return mo8280delegate().submit(runnable);
    }

    /* renamed from: submit */
    public <T> Future<T> mo8277submit(Runnable runnable, T t) {
        return mo8280delegate().submit(runnable, t);
    }
}
