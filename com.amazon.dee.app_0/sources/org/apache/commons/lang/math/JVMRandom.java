package org.apache.commons.lang.math;

import java.util.Random;
/* loaded from: classes4.dex */
public final class JVMRandom extends Random {
    private static final Random SHARED_RANDOM = new Random();
    private static final long serialVersionUID = 1;
    private boolean constructed;

    public JVMRandom() {
        this.constructed = false;
        this.constructed = true;
    }

    private static int bitsRequired(long j) {
        int i = 0;
        long j2 = j;
        while (j >= 0) {
            if (j2 == 0) {
                return i;
            }
            i++;
            j <<= 1;
            j2 >>= 1;
        }
        return 64 - i;
    }

    private static long next63bits() {
        return SHARED_RANDOM.nextLong() & Long.MAX_VALUE;
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return SHARED_RANDOM.nextBoolean();
    }

    @Override // java.util.Random
    public void nextBytes(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Random
    public double nextDouble() {
        return SHARED_RANDOM.nextDouble();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return SHARED_RANDOM.nextFloat();
    }

    @Override // java.util.Random
    public synchronized double nextGaussian() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Random
    public int nextInt() {
        return nextInt(Integer.MAX_VALUE);
    }

    @Override // java.util.Random
    public long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    @Override // java.util.Random
    public synchronized void setSeed(long j) {
        if (this.constructed) {
            throw new UnsupportedOperationException();
        }
    }

    public static long nextLong(long j) {
        long next63bits;
        long j2;
        if (j > 0) {
            if (((-j) & j) == j) {
                return next63bits() >> (63 - bitsRequired(j - 1));
            }
            do {
                next63bits = next63bits();
                j2 = next63bits % j;
            } while ((j - 1) + (next63bits - j2) < 0);
            return j2;
        }
        throw new IllegalArgumentException("Upper bound for nextInt must be positive");
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        return SHARED_RANDOM.nextInt(i);
    }
}
