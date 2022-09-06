package com.codahale.metrics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
public abstract class CachedGauge<T> implements Gauge<T> {
    private final Clock clock;
    private final AtomicLong reloadAt;
    private final long timeoutNS;
    private volatile T value;

    protected CachedGauge(long j, TimeUnit timeUnit) {
        this(Clock.defaultClock(), j, timeUnit);
    }

    protected CachedGauge(Clock clock, long j, TimeUnit timeUnit) {
        this.clock = clock;
        this.reloadAt = new AtomicLong(0L);
        this.timeoutNS = timeUnit.toNanos(j);
    }

    private boolean shouldLoad() {
        long tick;
        long j;
        do {
            tick = this.clock.getTick();
            j = this.reloadAt.get();
            if (j > tick) {
                return false;
            }
        } while (!this.reloadAt.compareAndSet(j, tick + this.timeoutNS));
        return true;
    }

    @Override // com.codahale.metrics.Gauge
    /* renamed from: getValue */
    public T mo6796getValue() {
        if (shouldLoad()) {
            this.value = loadValue();
        }
        return this.value;
    }

    protected abstract T loadValue();
}
