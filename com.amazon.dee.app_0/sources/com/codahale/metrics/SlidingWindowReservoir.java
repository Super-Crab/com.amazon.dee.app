package com.codahale.metrics;
/* loaded from: classes9.dex */
public class SlidingWindowReservoir implements Reservoir {
    private long count = 0;
    private final long[] measurements;

    public SlidingWindowReservoir(int i) {
        this.measurements = new long[i];
    }

    @Override // com.codahale.metrics.Reservoir
    public Snapshot getSnapshot() {
        long[] jArr = new long[size()];
        for (int i = 0; i < jArr.length; i++) {
            synchronized (this) {
                jArr[i] = this.measurements[i];
            }
        }
        return new Snapshot(jArr);
    }

    @Override // com.codahale.metrics.Reservoir
    public synchronized int size() {
        return (int) Math.min(this.count, this.measurements.length);
    }

    @Override // com.codahale.metrics.Reservoir
    public synchronized void update(long j) {
        long[] jArr = this.measurements;
        long j2 = this.count;
        this.count = 1 + j2;
        jArr[((int) j2) % this.measurements.length] = j;
    }
}
