package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes3.dex */
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
    private final ExecutionList executionList;

    ListenableFutureTask(Callable<V> callable) {
        super(callable);
        this.executionList = new ExecutionList();
    }

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<>(callable);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.executionList.add(runnable, executor);
    }

    @Override // java.util.concurrent.FutureTask
    protected void done() {
        this.executionList.execute();
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get(long j, TimeUnit timeUnit) throws TimeoutException, InterruptedException, ExecutionException {
        long nanos = timeUnit.toNanos(j);
        if (nanos <= 2147483647999999999L) {
            return (V) super.get(j, timeUnit);
        }
        return (V) super.get(Math.min(nanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, @NullableDecl V v) {
        return new ListenableFutureTask<>(runnable, v);
    }

    ListenableFutureTask(Runnable runnable, @NullableDecl V v) {
        super(runnable, v);
        this.executionList = new ExecutionList();
    }
}
