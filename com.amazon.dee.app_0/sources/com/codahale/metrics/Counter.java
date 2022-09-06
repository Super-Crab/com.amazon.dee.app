package com.codahale.metrics;
/* loaded from: classes9.dex */
public class Counter implements Metric, Counting {
    private final LongAdder count = new LongAdder();

    public void dec() {
        dec(1L);
    }

    public void dec(long j) {
        this.count.add(-j);
    }

    @Override // com.codahale.metrics.Counting
    public long getCount() {
        return this.count.sum();
    }

    public void inc() {
        inc(1L);
    }

    public void inc(long j) {
        this.count.add(j);
    }
}
