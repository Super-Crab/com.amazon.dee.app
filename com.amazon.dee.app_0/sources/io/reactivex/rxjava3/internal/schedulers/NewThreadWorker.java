package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public class NewThreadWorker extends Scheduler.Worker implements Disposable {
    volatile boolean disposed;
    private final ScheduledExecutorService executor;

    public NewThreadWorker(ThreadFactory threadFactory) {
        this.executor = SchedulerPoolFactory.create(threadFactory);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.executor.shutdownNow();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    @NonNull
    public Disposable schedule(@NonNull final Runnable run) {
        return schedule(run, 0L, null);
    }

    @NonNull
    public ScheduledRunnable scheduleActual(final Runnable run, long delayTime, @NonNull TimeUnit unit, @Nullable DisposableContainer parent) {
        Future<?> schedule;
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(RxJavaPlugins.onSchedule(run), parent);
        if (parent == null || parent.add(scheduledRunnable)) {
            try {
                if (delayTime <= 0) {
                    schedule = this.executor.submit((Callable) scheduledRunnable);
                } else {
                    schedule = this.executor.schedule((Callable) scheduledRunnable, delayTime, unit);
                }
                scheduledRunnable.setFuture(schedule);
            } catch (RejectedExecutionException e) {
                if (parent != null) {
                    parent.remove(scheduledRunnable);
                }
                RxJavaPlugins.onError(e);
            }
            return scheduledRunnable;
        }
        return scheduledRunnable;
    }

    public Disposable scheduleDirect(final Runnable run, long delayTime, TimeUnit unit) {
        Future<?> schedule;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(RxJavaPlugins.onSchedule(run));
        try {
            if (delayTime <= 0) {
                schedule = this.executor.submit(scheduledDirectTask);
            } else {
                schedule = this.executor.schedule(scheduledDirectTask, delayTime, unit);
            }
            scheduledDirectTask.setFuture(schedule);
            return scheduledDirectTask;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.onError(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    public Disposable schedulePeriodicallyDirect(Runnable run, long initialDelay, long period, TimeUnit unit) {
        Future<?> schedule;
        Runnable onSchedule = RxJavaPlugins.onSchedule(run);
        if (period <= 0) {
            InstantPeriodicTask instantPeriodicTask = new InstantPeriodicTask(onSchedule, this.executor);
            try {
                if (initialDelay <= 0) {
                    schedule = this.executor.submit(instantPeriodicTask);
                } else {
                    schedule = this.executor.schedule(instantPeriodicTask, initialDelay, unit);
                }
                instantPeriodicTask.setFirst(schedule);
                return instantPeriodicTask;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.onError(e);
                return EmptyDisposable.INSTANCE;
            }
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(onSchedule);
        try {
            scheduledDirectPeriodicTask.setFuture(this.executor.scheduleAtFixedRate(scheduledDirectPeriodicTask, initialDelay, period, unit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            RxJavaPlugins.onError(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public void shutdown() {
        if (!this.disposed) {
            this.disposed = true;
            this.executor.shutdown();
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    @NonNull
    public Disposable schedule(@NonNull final Runnable action, long delayTime, @NonNull TimeUnit unit) {
        if (this.disposed) {
            return EmptyDisposable.INSTANCE;
        }
        return scheduleActual(action, delayTime, unit, null);
    }
}
