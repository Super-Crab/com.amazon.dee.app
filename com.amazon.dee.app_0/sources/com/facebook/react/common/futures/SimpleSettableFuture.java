package com.facebook.react.common.futures;

import androidx.annotation.Nullable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
public class SimpleSettableFuture<T> implements Future<T> {
    @Nullable
    private Exception mException;
    private final CountDownLatch mReadyLatch = new CountDownLatch(1);
    @Nullable
    private T mResult;

    private void checkNotSet() {
        if (this.mReadyLatch.getCount() != 0) {
            return;
        }
        throw new RuntimeException("Result has already been set!");
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public T get() throws InterruptedException, ExecutionException {
        this.mReadyLatch.await();
        Exception exc = this.mException;
        if (exc == null) {
            return this.mResult;
        }
        throw new ExecutionException(exc);
    }

    @Nullable
    public T getOrThrow() {
        try {
            return get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mReadyLatch.getCount() == 0;
    }

    public void set(@Nullable T t) {
        checkNotSet();
        this.mResult = t;
        this.mReadyLatch.countDown();
    }

    public void setException(Exception exc) {
        checkNotSet();
        this.mException = exc;
        this.mReadyLatch.countDown();
    }

    @Nullable
    public T getOrThrow(long j, TimeUnit timeUnit) {
        try {
            return get(j, timeUnit);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.mReadyLatch.await(j, timeUnit)) {
            Exception exc = this.mException;
            if (exc == null) {
                return this.mResult;
            }
            throw new ExecutionException(exc);
        }
        throw new TimeoutException("Timed out waiting for result");
    }
}
