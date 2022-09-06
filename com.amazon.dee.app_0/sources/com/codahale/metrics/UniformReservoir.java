package com.codahale.metrics;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
/* loaded from: classes9.dex */
public class UniformReservoir implements Reservoir {
    private static final int BITS_PER_LONG = 63;
    private static final int DEFAULT_SIZE = 1028;
    private final AtomicLong count;
    private final AtomicLongArray values;

    public UniformReservoir() {
        this(1028);
    }

    public UniformReservoir(int i) {
        this.count = new AtomicLong();
        this.values = new AtomicLongArray(i);
        for (int i2 = 0; i2 < this.values.length(); i2++) {
            this.values.set(i2, 0L);
        }
        this.count.set(0L);
    }

    private static long nextLong(long j) {
        long nextLong;
        long j2;
        do {
            nextLong = ThreadLocalRandom.current().nextLong() & Long.MAX_VALUE;
            j2 = nextLong % j;
        } while ((j - 1) + (nextLong - j2) < 0);
        return j2;
    }

    @Override // com.codahale.metrics.Reservoir
    public Snapshot getSnapshot() {
        int size = size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(Long.valueOf(this.values.get(i)));
        }
        return new Snapshot(arrayList);
    }

    @Override // com.codahale.metrics.Reservoir
    public int size() {
        long j = this.count.get();
        return j > ((long) this.values.length()) ? this.values.length() : (int) j;
    }

    @Override // com.codahale.metrics.Reservoir
    public void update(long j) {
        AtomicLongArray atomicLongArray;
        int i;
        long incrementAndGet = this.count.incrementAndGet();
        if (incrementAndGet <= this.values.length()) {
            atomicLongArray = this.values;
            i = ((int) incrementAndGet) - 1;
        } else {
            long nextLong = nextLong(incrementAndGet);
            if (nextLong >= this.values.length()) {
                return;
            }
            atomicLongArray = this.values;
            i = (int) nextLong;
        }
        atomicLongArray.set(i, j);
    }
}
