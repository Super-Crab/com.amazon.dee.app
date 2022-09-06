package com.codahale.metrics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes9.dex */
public class ExponentiallyDecayingReservoir implements Reservoir {
    private static final double DEFAULT_ALPHA = 0.015d;
    private static final int DEFAULT_SIZE = 1028;
    private static final long RESCALE_THRESHOLD = TimeUnit.HOURS.toNanos(1);
    private final double alpha;
    private final Clock clock;
    private final AtomicLong count;
    private final ReentrantReadWriteLock lock;
    private final AtomicLong nextScaleTime;
    private final int size;
    private volatile long startTime;
    private final ConcurrentSkipListMap<Double, Long> values;

    public ExponentiallyDecayingReservoir() {
        this(1028, DEFAULT_ALPHA);
    }

    public ExponentiallyDecayingReservoir(int i, double d) {
        this(i, d, Clock.defaultClock());
    }

    public ExponentiallyDecayingReservoir(int i, double d, Clock clock) {
        this.values = new ConcurrentSkipListMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.alpha = d;
        this.size = i;
        this.clock = clock;
        this.count = new AtomicLong(0L);
        this.startTime = currentTimeInSeconds();
        this.nextScaleTime = new AtomicLong(clock.getTick() + RESCALE_THRESHOLD);
    }

    private long currentTimeInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds(this.clock.getTime());
    }

    private void lockForRegularUsage() {
        this.lock.readLock().lock();
    }

    private void lockForRescale() {
        this.lock.writeLock().lock();
    }

    private void rescale(long j, long j2) {
        if (this.nextScaleTime.compareAndSet(j2, j + RESCALE_THRESHOLD)) {
            lockForRescale();
            try {
                long j3 = this.startTime;
                this.startTime = currentTimeInSeconds();
                Iterator it2 = new ArrayList(this.values.keySet()).iterator();
                while (it2.hasNext()) {
                    Double d = (Double) it2.next();
                    this.values.put(Double.valueOf(d.doubleValue() * Math.exp((-this.alpha) * (this.startTime - j3))), this.values.remove(d));
                }
                this.count.set(this.values.size());
            } finally {
                unlockForRescale();
            }
        }
    }

    private void rescaleIfNeeded() {
        long tick = this.clock.getTick();
        long j = this.nextScaleTime.get();
        if (tick >= j) {
            rescale(tick, j);
        }
    }

    private void unlockForRegularUsage() {
        this.lock.readLock().unlock();
    }

    private void unlockForRescale() {
        this.lock.writeLock().unlock();
    }

    private double weight(long j) {
        return Math.exp(this.alpha * j);
    }

    @Override // com.codahale.metrics.Reservoir
    public Snapshot getSnapshot() {
        lockForRegularUsage();
        try {
            return new Snapshot(this.values.values());
        } finally {
            unlockForRegularUsage();
        }
    }

    @Override // com.codahale.metrics.Reservoir
    public int size() {
        return (int) Math.min(this.size, this.count.get());
    }

    @Override // com.codahale.metrics.Reservoir
    public void update(long j) {
        update(j, currentTimeInSeconds());
    }

    public void update(long j, long j2) {
        rescaleIfNeeded();
        lockForRegularUsage();
        try {
            double weight = weight(j2 - this.startTime) / ThreadLocalRandom.current().nextDouble();
            if (this.count.incrementAndGet() <= this.size) {
                this.values.put(Double.valueOf(weight), Long.valueOf(j));
            } else {
                Double firstKey = this.values.firstKey();
                if (firstKey.doubleValue() < weight && this.values.putIfAbsent(Double.valueOf(weight), Long.valueOf(j)) == null) {
                    while (this.values.remove(firstKey) == null) {
                        firstKey = this.values.firstKey();
                    }
                }
            }
        } finally {
            unlockForRegularUsage();
        }
    }
}
