package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class InstantPeriodicTask implements Callable<Void>, Disposable {
    static final FutureTask<Void> CANCELLED = new FutureTask<>(Functions.EMPTY_RUNNABLE, null);
    final ExecutorService executor;
    final AtomicReference<Future<?>> first = new AtomicReference<>();
    final AtomicReference<Future<?>> rest = new AtomicReference<>();
    Thread runner;
    final Runnable task;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InstantPeriodicTask(Runnable task, ExecutorService executor) {
        this.task = task;
        this.executor = executor;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Future<?> andSet = this.first.getAndSet(CANCELLED);
        boolean z = true;
        if (andSet != null && andSet != CANCELLED) {
            andSet.cancel(this.runner != Thread.currentThread());
        }
        Future<?> andSet2 = this.rest.getAndSet(CANCELLED);
        if (andSet2 == null || andSet2 == CANCELLED) {
            return;
        }
        if (this.runner == Thread.currentThread()) {
            z = false;
        }
        andSet2.cancel(z);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.first.get() == CANCELLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFirst(Future<?> f) {
        Future<?> future;
        do {
            future = this.first.get();
            if (future == CANCELLED) {
                f.cancel(this.runner != Thread.currentThread());
                return;
            }
        } while (!this.first.compareAndSet(future, f));
    }

    void setRest(Future<?> f) {
        Future<?> future;
        do {
            future = this.rest.get();
            if (future == CANCELLED) {
                f.cancel(this.runner != Thread.currentThread());
                return;
            }
        } while (!this.rest.compareAndSet(future, f));
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.runner = Thread.currentThread();
        try {
            this.task.run();
            this.runner = null;
            setRest(this.executor.submit(this));
            return null;
        } catch (Throwable th) {
            this.runner = null;
            RxJavaPlugins.onError(th);
            throw th;
        }
    }
}
