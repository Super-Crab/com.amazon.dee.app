package com.amazon.alexa.utils.concurrent;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes10.dex */
class a extends ScheduledThreadPoolExecutor {
    private final e a;

    /* renamed from: com.amazon.alexa.utils.concurrent.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    private static class RunnableScheduledFutureC0035a<V> implements RunnableScheduledFuture<V> {
        private final Object a;
        private final RunnableScheduledFuture<V> b;

        private RunnableScheduledFutureC0035a(Object obj, RunnableScheduledFuture<V> runnableScheduledFuture) {
            this.a = obj;
            this.b = runnableScheduledFuture;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(@NonNull Delayed delayed) {
            return this.b.compareTo(delayed);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return this.b.cancel(z);
        }

        @Override // java.util.concurrent.Future
        public V get() throws InterruptedException, ExecutionException {
            return (V) this.b.get();
        }

        @Override // java.util.concurrent.Future
        public V get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) this.b.get(j, timeUnit);
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(@NonNull TimeUnit timeUnit) {
            return this.b.getDelay(timeUnit);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.b.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.b.isDone();
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return this.b.isPeriodic();
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            this.b.run();
        }

        public String toString() {
            return this.a.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i, d dVar) {
        this(i, dVar, new c(), new e());
    }

    a(int i, d dVar, RejectedExecutionHandler rejectedExecutionHandler, e eVar) {
        super(i, dVar, rejectedExecutionHandler);
        this.a = eVar;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, @Nullable Throwable th) {
        super.afterExecute(runnable, th);
        this.a.a(runnable, th);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> runnableScheduledFuture) {
        return new RunnableScheduledFutureC0035a(runnable, runnableScheduledFuture);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor
    protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable, RunnableScheduledFuture<V> runnableScheduledFuture) {
        return new RunnableScheduledFutureC0035a(callable, runnableScheduledFuture);
    }
}
