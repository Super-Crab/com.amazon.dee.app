package com.codahale.metrics;

import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class Timer implements Metered, Sampling {
    private final Clock clock;
    private final Histogram histogram;
    private final Meter meter;

    /* loaded from: classes9.dex */
    public static class Context implements Closeable {
        private final Clock clock;
        private final long startTime;
        private final Timer timer;

        private Context(Timer timer, Clock clock) {
            this.timer = timer;
            this.clock = clock;
            this.startTime = clock.getTick();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            stop();
        }

        public long stop() {
            long tick = this.clock.getTick() - this.startTime;
            this.timer.update(tick, TimeUnit.NANOSECONDS);
            return tick;
        }
    }

    public Timer() {
        this(new ExponentiallyDecayingReservoir());
    }

    public Timer(Reservoir reservoir) {
        this(reservoir, Clock.defaultClock());
    }

    public Timer(Reservoir reservoir, Clock clock) {
        this.meter = new Meter(clock);
        this.clock = clock;
        this.histogram = new Histogram(reservoir);
    }

    private void update(long j) {
        if (j >= 0) {
            this.histogram.update(j);
            this.meter.mark();
        }
    }

    @Override // com.codahale.metrics.Metered, com.codahale.metrics.Counting
    public long getCount() {
        return this.histogram.getCount();
    }

    @Override // com.codahale.metrics.Metered
    public double getFifteenMinuteRate() {
        return this.meter.getFifteenMinuteRate();
    }

    @Override // com.codahale.metrics.Metered
    public double getFiveMinuteRate() {
        return this.meter.getFiveMinuteRate();
    }

    @Override // com.codahale.metrics.Metered
    public double getMeanRate() {
        return this.meter.getMeanRate();
    }

    @Override // com.codahale.metrics.Metered
    public double getOneMinuteRate() {
        return this.meter.getOneMinuteRate();
    }

    @Override // com.codahale.metrics.Sampling
    public Snapshot getSnapshot() {
        return this.histogram.getSnapshot();
    }

    public Context time() {
        return new Context(this.clock);
    }

    public <T> T time(Callable<T> callable) throws Exception {
        long tick = this.clock.getTick();
        try {
            return callable.call();
        } finally {
            update(this.clock.getTick() - tick);
        }
    }

    public void update(long j, TimeUnit timeUnit) {
        update(timeUnit.toNanos(j));
    }
}
