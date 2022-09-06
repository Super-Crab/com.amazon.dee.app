package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes3.dex */
public final class TrampolineScheduler extends Scheduler {
    private static final TrampolineScheduler INSTANCE = new TrampolineScheduler();

    /* loaded from: classes3.dex */
    static final class SleepingRunnable implements Runnable {
        private final long execTime;
        private final Runnable run;
        private final TrampolineWorker worker;

        SleepingRunnable(Runnable run, TrampolineWorker worker, long execTime) {
            this.run = run;
            this.worker = worker;
            this.execTime = execTime;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.worker.disposed) {
                long now = this.worker.now(TimeUnit.MILLISECONDS);
                long j = this.execTime;
                if (j > now) {
                    try {
                        Thread.sleep(j - now);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        RxJavaPlugins.onError(e);
                        return;
                    }
                }
                if (this.worker.disposed) {
                    return;
                }
                this.run.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class TimedRunnable implements Comparable<TimedRunnable> {
        final int count;
        volatile boolean disposed;
        final long execTime;
        final Runnable run;

        TimedRunnable(Runnable run, Long execTime, int count) {
            this.run = run;
            this.execTime = execTime.longValue();
            this.count = count;
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable that) {
            int compare = Long.compare(this.execTime, that.execTime);
            return compare == 0 ? Integer.compare(this.count, that.count) : compare;
        }
    }

    /* loaded from: classes3.dex */
    static final class TrampolineWorker extends Scheduler.Worker implements Disposable {
        volatile boolean disposed;
        final PriorityBlockingQueue<TimedRunnable> queue = new PriorityBlockingQueue<>();
        private final AtomicInteger wip = new AtomicInteger();
        final AtomicInteger counter = new AtomicInteger();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public final class AppendToQueueTask implements Runnable {
            final TimedRunnable timedRunnable;

            AppendToQueueTask(TimedRunnable timedRunnable) {
                this.timedRunnable = timedRunnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                TimedRunnable timedRunnable = this.timedRunnable;
                timedRunnable.disposed = true;
                TrampolineWorker.this.queue.remove(timedRunnable);
            }
        }

        TrampolineWorker() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        Disposable enqueue(Runnable action, long execTime) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TimedRunnable timedRunnable = new TimedRunnable(action, Long.valueOf(execTime), this.counter.incrementAndGet());
            this.queue.add(timedRunnable);
            if (this.wip.getAndIncrement() == 0) {
                int i = 1;
                while (!this.disposed) {
                    TimedRunnable poll = this.queue.poll();
                    if (poll == null) {
                        i = this.wip.addAndGet(-i);
                        if (i == 0) {
                            return EmptyDisposable.INSTANCE;
                        }
                    } else if (!poll.disposed) {
                        poll.run.run();
                    }
                }
                this.queue.clear();
                return EmptyDisposable.INSTANCE;
            }
            return Disposable.fromRunnable(new AppendToQueueTask(timedRunnable));
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable action) {
            return enqueue(action, now(TimeUnit.MILLISECONDS));
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable action, long delayTime, @NonNull TimeUnit unit) {
            long millis = unit.toMillis(delayTime) + now(TimeUnit.MILLISECONDS);
            return enqueue(new SleepingRunnable(action, this, millis), millis);
        }
    }

    TrampolineScheduler() {
    }

    public static TrampolineScheduler instance() {
        return INSTANCE;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new TrampolineWorker();
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run) {
        RxJavaPlugins.onSchedule(run).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run, long delay, TimeUnit unit) {
        try {
            unit.sleep(delay);
            RxJavaPlugins.onSchedule(run).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            RxJavaPlugins.onError(e);
        }
        return EmptyDisposable.INSTANCE;
    }
}
