package com.codahale.metrics;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
abstract class Striped64 extends Number {
    transient AtomicLong base = new AtomicLong();
    transient AtomicInteger busy = new AtomicInteger();
    volatile transient AtomicLong[] cells;
    static final ThreadHashCode threadHashCode = new ThreadHashCode();
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class HashCode {
        static final Random rng = new Random();
        int code;

        HashCode() {
            int nextInt = rng.nextInt();
            this.code = nextInt == 0 ? 1 : nextInt;
        }
    }

    /* loaded from: classes9.dex */
    static final class ThreadHashCode extends ThreadLocal<HashCode> {
        ThreadHashCode() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue */
        public HashCode mo6797initialValue() {
            return new HashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean casBase(long j, long j2) {
        return this.base.compareAndSet(j, j2);
    }

    final boolean casBusy() {
        return this.busy.compareAndSet(0, 1);
    }

    abstract long fn(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void internalReset(long j) {
        AtomicLong[] atomicLongArr = this.cells;
        this.base.set(j);
        if (atomicLongArr != null) {
            for (AtomicLong atomicLong : atomicLongArr) {
                if (atomicLong != null) {
                    atomicLong.set(j);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0083, code lost:
        if (r11.cells != r3) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0085, code lost:
        r2 = new java.util.concurrent.atomic.AtomicLong[r5 << 1];
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008a, code lost:
        if (r4 >= r5) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008c, code lost:
        r2[r4] = r3[r4];
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0093, code lost:
        r11.cells = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0004 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f3 A[EDGE_INSN: B:86:0x00f3->B:78:0x00f3 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void retryUpdate(long r12, com.codahale.metrics.Striped64.HashCode r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.codahale.metrics.Striped64.retryUpdate(long, com.codahale.metrics.Striped64$HashCode, boolean):void");
    }
}
