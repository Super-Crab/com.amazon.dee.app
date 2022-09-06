package com.codahale.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
public class Meter implements Metered {
    private static final long TICK_INTERVAL = TimeUnit.SECONDS.toNanos(5);
    private final Clock clock;
    private final LongAdder count;
    private final AtomicLong lastTick;
    private final EWMA m15Rate;
    private final EWMA m1Rate;
    private final EWMA m5Rate;
    private final long startTime;

    public Meter() {
        this(Clock.defaultClock());
    }

    public Meter(Clock clock) {
        this.m1Rate = EWMA.oneMinuteEWMA();
        this.m5Rate = EWMA.fiveMinuteEWMA();
        this.m15Rate = EWMA.fifteenMinuteEWMA();
        this.count = new LongAdder();
        this.clock = clock;
        this.startTime = this.clock.getTick();
        this.lastTick = new AtomicLong(this.startTime);
    }

    private void tickIfNecessary() {
        long j = this.lastTick.get();
        long tick = this.clock.getTick();
        long j2 = tick - j;
        long j3 = TICK_INTERVAL;
        if (j2 > j3) {
            if (!this.lastTick.compareAndSet(j, tick - (j2 % j3))) {
                return;
            }
            long j4 = j2 / TICK_INTERVAL;
            for (long j5 = 0; j5 < j4; j5++) {
                this.m1Rate.tick();
                this.m5Rate.tick();
                this.m15Rate.tick();
            }
        }
    }

    @Override // com.codahale.metrics.Metered, com.codahale.metrics.Counting
    public long getCount() {
        return this.count.sum();
    }

    @Override // com.codahale.metrics.Metered
    public double getFifteenMinuteRate() {
        tickIfNecessary();
        return this.m15Rate.getRate(TimeUnit.SECONDS);
    }

    @Override // com.codahale.metrics.Metered
    public double getFiveMinuteRate() {
        tickIfNecessary();
        return this.m5Rate.getRate(TimeUnit.SECONDS);
    }

    @Override // com.codahale.metrics.Metered
    public double getMeanRate() {
        if (getCount() == 0) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        return (getCount() / (this.clock.getTick() - this.startTime)) * TimeUnit.SECONDS.toNanos(1L);
    }

    @Override // com.codahale.metrics.Metered
    public double getOneMinuteRate() {
        tickIfNecessary();
        return this.m1Rate.getRate(TimeUnit.SECONDS);
    }

    public void mark() {
        mark(1L);
    }

    public void mark(long j) {
        tickIfNecessary();
        this.count.add(j);
        this.m1Rate.update(j);
        this.m5Rate.update(j);
        this.m15Rate.update(j);
    }
}
