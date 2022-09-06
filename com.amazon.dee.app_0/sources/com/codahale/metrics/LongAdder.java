package com.codahale.metrics;

import com.codahale.metrics.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.busy.set(0);
        this.cells = null;
        this.base.set(objectInputStream.readLong());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    public void add(long j) {
        int length;
        AtomicLong atomicLong;
        AtomicLong[] atomicLongArr = this.cells;
        if (atomicLongArr == null) {
            long j2 = this.base.get();
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        Striped64.HashCode hashCode = Striped64.threadHashCode.get();
        int i = hashCode.code;
        boolean z = true;
        if (atomicLongArr != null && (length = atomicLongArr.length) >= 1 && (atomicLong = atomicLongArr[i & (length - 1)]) != null) {
            long j3 = atomicLong.get();
            z = atomicLong.compareAndSet(j3, j3 + j);
            if (z) {
                return;
            }
        }
        retryUpdate(j, hashCode, z);
    }

    public void decrement() {
        add(-1L);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) sum();
    }

    @Override // com.codahale.metrics.Striped64
    final long fn(long j, long j2) {
        return j + j2;
    }

    public void increment() {
        add(1L);
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    public void reset() {
        internalReset(0L);
    }

    public long sum() {
        long j = this.base.get();
        AtomicLong[] atomicLongArr = this.cells;
        if (atomicLongArr != null) {
            for (AtomicLong atomicLong : atomicLongArr) {
                if (atomicLong != null) {
                    j += atomicLong.get();
                }
            }
        }
        return j;
    }

    public long sumThenReset() {
        long j = this.base.get();
        AtomicLong[] atomicLongArr = this.cells;
        this.base.set(0L);
        if (atomicLongArr != null) {
            for (AtomicLong atomicLong : atomicLongArr) {
                if (atomicLong != null) {
                    j += atomicLong.get();
                    atomicLong.set(0L);
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
