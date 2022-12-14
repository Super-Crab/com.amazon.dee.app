package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public final class TestScheduler extends Scheduler {
    long counter;
    final Queue<TimedRunnable> queue = new PriorityBlockingQueue(11);
    volatile long time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class TimedRunnable implements Comparable<TimedRunnable> {
        final long count;
        final Runnable run;
        final TestWorker scheduler;
        final long time;

        TimedRunnable(TestWorker scheduler, long time, Runnable run, long count) {
            this.time = time;
            this.run = run;
            this.scheduler = scheduler;
            this.count = count;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", Long.valueOf(this.time), this.run.toString());
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable o) {
            long j = this.time;
            long j2 = o.time;
            if (j == j2) {
                return Long.compare(this.count, o.count);
            }
            return Long.compare(j, j2);
        }
    }

    public TestScheduler() {
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(unit.toNanos(delayTime) + this.time, TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new TestWorker();
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public long now(@NonNull TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanoseconds) {
        while (true) {
            TimedRunnable peek = this.queue.peek();
            if (peek == null) {
                break;
            }
            long j = peek.time;
            if (j > targetTimeInNanoseconds) {
                break;
            }
            if (j == 0) {
                j = this.time;
            }
            this.time = j;
            this.queue.remove(peek);
            if (!peek.scheduler.disposed) {
                peek.run.run();
            }
        }
        this.time = targetTimeInNanoseconds;
    }

    public TestScheduler(long delayTime, TimeUnit unit) {
        this.time = unit.toNanos(delayTime);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class TestWorker extends Scheduler.Worker {
        volatile boolean disposed;

        /* loaded from: classes3.dex */
        final class QueueRemove implements Runnable {
            final TimedRunnable timedAction;

            QueueRemove(TimedRunnable timedAction) {
                this.timedAction = timedAction;
            }

            @Override // java.lang.Runnable
            public void run() {
                TestScheduler.this.queue.remove(this.timedAction);
            }
        }

        TestWorker() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public long now(@NonNull TimeUnit unit) {
            return TestScheduler.this.now(unit);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable run, long delayTime, @NonNull TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            long nanos = unit.toNanos(delayTime) + TestScheduler.this.time;
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, nanos, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return Disposable.fromRunnable(new QueueRemove(timedRunnable));
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable run) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, 0L, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return Disposable.fromRunnable(new QueueRemove(timedRunnable));
        }
    }
}
