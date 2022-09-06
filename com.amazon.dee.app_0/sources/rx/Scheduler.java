package rx;

import java.util.concurrent.TimeUnit;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.SchedulerWhen;
import rx.subscriptions.MultipleAssignmentSubscription;
/* loaded from: classes5.dex */
public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes5.dex */
    public static abstract class Worker implements Subscription {
        public long now() {
            return System.currentTimeMillis();
        }

        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(final Action0 action0, long j, long j2, TimeUnit timeUnit) {
            final long nanos = timeUnit.toNanos(j2);
            final long nanos2 = TimeUnit.MILLISECONDS.toNanos(now());
            final long nanos3 = timeUnit.toNanos(j) + nanos2;
            final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            Action0 action02 = new Action0() { // from class: rx.Scheduler.Worker.1
                long count;
                long lastNowNanos;
                long startInNanos;

                {
                    this.lastNowNanos = nanos2;
                    this.startInNanos = nanos3;
                }

                @Override // rx.functions.Action0
                public void call() {
                    long j3;
                    action0.call();
                    if (!multipleAssignmentSubscription.isUnsubscribed()) {
                        long nanos4 = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                        long j4 = Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS;
                        long j5 = this.lastNowNanos;
                        if (nanos4 + j4 >= j5) {
                            long j6 = nanos;
                            if (nanos4 < j5 + j6 + j4) {
                                long j7 = this.startInNanos;
                                long j8 = this.count + 1;
                                this.count = j8;
                                j3 = (j8 * j6) + j7;
                                this.lastNowNanos = nanos4;
                                multipleAssignmentSubscription.set(Worker.this.schedule(this, j3 - nanos4, TimeUnit.NANOSECONDS));
                            }
                        }
                        long j9 = nanos;
                        j3 = nanos4 + j9;
                        long j10 = this.count + 1;
                        this.count = j10;
                        this.startInNanos = j3 - (j9 * j10);
                        this.lastNowNanos = nanos4;
                        multipleAssignmentSubscription.set(Worker.this.schedule(this, j3 - nanos4, TimeUnit.NANOSECONDS));
                    }
                }
            };
            MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription.set(multipleAssignmentSubscription2);
            multipleAssignmentSubscription2.set(schedule(action02, j, timeUnit));
            return multipleAssignmentSubscription;
        }
    }

    public abstract Worker createWorker();

    public long now() {
        return System.currentTimeMillis();
    }

    @Experimental
    public <S extends Scheduler & Subscription> S when(Func1<Observable<Observable<Completable>>, Completable> func1) {
        return new SchedulerWhen(func1, this);
    }
}
