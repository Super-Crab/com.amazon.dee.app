package com.codahale.metrics;
/* loaded from: classes9.dex */
public class Histogram implements Metric, Sampling, Counting {
    private final LongAdder count = new LongAdder();
    private final Reservoir reservoir;

    public Histogram(Reservoir reservoir) {
        this.reservoir = reservoir;
    }

    @Override // com.codahale.metrics.Counting
    public long getCount() {
        return this.count.sum();
    }

    @Override // com.codahale.metrics.Sampling
    public Snapshot getSnapshot() {
        return this.reservoir.getSnapshot();
    }

    public void update(int i) {
        update(i);
    }

    public void update(long j) {
        this.count.increment();
        this.reservoir.update(j);
    }
}
