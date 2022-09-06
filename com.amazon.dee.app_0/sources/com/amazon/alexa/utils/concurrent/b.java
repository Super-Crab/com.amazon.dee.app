package com.amazon.alexa.utils.concurrent;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
class b extends ThreadPoolExecutor {
    private final e a;

    /* loaded from: classes10.dex */
    private static class a<V> extends FutureTask<V> {
        private final Object a;

        private a(@NonNull Runnable runnable, V v) {
            super(runnable, v);
            this.a = runnable;
        }

        private a(@NonNull Callable<V> callable) {
            super(callable);
            this.a = callable;
        }

        public String toString() {
            return this.a.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, d dVar) {
        this(i, i2, j, timeUnit, blockingQueue, dVar, new c(), new e());
    }

    b(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, d dVar, c cVar, e eVar) {
        super(i, i2, j, timeUnit, blockingQueue, dVar, cVar);
        this.a = eVar;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, @Nullable Throwable th) {
        super.afterExecute(runnable, th);
        this.a.a(runnable, th);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new a(runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new a(callable);
    }
}
