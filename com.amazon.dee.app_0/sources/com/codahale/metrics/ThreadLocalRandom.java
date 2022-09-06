package com.codahale.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.Random;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ThreadLocalRandom extends Random {
    private static final long addend = 11;
    private static final ThreadLocal<ThreadLocalRandom> localRandom = new ThreadLocal<ThreadLocalRandom>() { // from class: com.codahale.metrics.ThreadLocalRandom.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue */
        public ThreadLocalRandom mo6798initialValue() {
            return new ThreadLocalRandom();
        }
    };
    private static final long mask = 281474976710655L;
    private static final long multiplier = 25214903917L;
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized = true;
    private long pad0;
    private long pad1;
    private long pad2;
    private long pad3;
    private long pad4;
    private long pad5;
    private long pad6;
    private long pad7;
    private long rnd;

    ThreadLocalRandom() {
    }

    public static ThreadLocalRandom current() {
        return localRandom.get();
    }

    @Override // java.util.Random
    protected int next(int i) {
        this.rnd = ((this.rnd * multiplier) + addend) & mask;
        return (int) (this.rnd >>> (48 - i));
    }

    public double nextDouble(double d) {
        if (d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return nextDouble() * d;
        }
        throw new IllegalArgumentException("n must be positive");
    }

    public double nextDouble(double d, double d2) {
        if (d < d2) {
            return ((d2 - d) * nextDouble()) + d;
        }
        throw new IllegalArgumentException();
    }

    public int nextInt(int i, int i2) {
        if (i < i2) {
            return nextInt(i2 - i) + i;
        }
        throw new IllegalArgumentException();
    }

    public long nextLong(long j) {
        long j2 = 0;
        if (j > 0) {
            while (j >= 2147483647L) {
                int next = next(2);
                long j3 = j >>> 1;
                if ((next & 2) != 0) {
                    j3 = j - j3;
                }
                if ((next & 1) == 0) {
                    j2 = (j - j3) + j2;
                }
                j = j3;
            }
            return j2 + nextInt((int) j);
        }
        throw new IllegalArgumentException("n must be positive");
    }

    public long nextLong(long j, long j2) {
        if (j < j2) {
            return nextLong(j2 - j) + j;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (!this.initialized) {
            this.rnd = (j ^ multiplier) & mask;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
