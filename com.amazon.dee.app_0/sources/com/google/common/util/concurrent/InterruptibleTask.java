package com.google.common.util.concurrent;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();
    private static final Runnable PARKED = new DoNothingRunnable();

    /* loaded from: classes3.dex */
    private static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable runnable = get();
        if (!(runnable instanceof Thread) || !compareAndSet(runnable, INTERRUPTING)) {
            return;
        }
        try {
            ((Thread) runnable).interrupt();
        } finally {
            if (getAndSet(DONE) == PARKED) {
                LockSupport.unpark((Thread) runnable);
            }
        }
    }

    abstract boolean isDone();

    @Override // java.lang.Runnable
    public final void run() {
        T mo8292runInterruptibly;
        Thread currentThread = Thread.currentThread();
        if (!compareAndSet(null, currentThread)) {
            return;
        }
        boolean z = !isDone();
        if (z) {
            try {
                mo8292runInterruptibly = mo8292runInterruptibly();
            } catch (Throwable th) {
                if (!compareAndSet(currentThread, DONE)) {
                    Runnable runnable = get();
                    boolean z2 = false;
                    int i = 0;
                    while (true) {
                        if (runnable != INTERRUPTING && runnable != PARKED) {
                            break;
                        }
                        i++;
                        if (i > 1000) {
                            Runnable runnable2 = PARKED;
                            if (runnable == runnable2 || compareAndSet(INTERRUPTING, runnable2)) {
                                boolean z3 = Thread.interrupted() || z2;
                                LockSupport.park(this);
                                z2 = z3;
                            }
                        } else {
                            Thread.yield();
                        }
                        runnable = get();
                    }
                    if (z2) {
                        currentThread.interrupt();
                    }
                }
                if (!z) {
                    return;
                }
                afterRanInterruptibly(null, th);
                return;
            }
        } else {
            mo8292runInterruptibly = null;
        }
        if (!compareAndSet(currentThread, DONE)) {
            Runnable runnable3 = get();
            boolean z4 = false;
            int i2 = 0;
            while (true) {
                if (runnable3 != INTERRUPTING && runnable3 != PARKED) {
                    break;
                }
                i2++;
                if (i2 > 1000) {
                    Runnable runnable4 = PARKED;
                    if (runnable3 == runnable4 || compareAndSet(INTERRUPTING, runnable4)) {
                        boolean z5 = Thread.interrupted() || z4;
                        LockSupport.park(this);
                        z4 = z5;
                    }
                } else {
                    Thread.yield();
                }
                runnable3 = get();
            }
            if (z4) {
                currentThread.interrupt();
            }
        }
        if (!z) {
            return;
        }
        afterRanInterruptibly(mo8292runInterruptibly, null);
    }

    /* renamed from: runInterruptibly */
    abstract T mo8292runInterruptibly() throws Exception;

    abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            str = GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(name, 21), "running=[RUNNING ON ", name, "]");
        } else {
            str = "running=[NOT STARTED YET]";
        }
        String pendingString = toPendingString();
        return GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(pendingString, GeneratedOutlineSupport1.outline6(str, 2)), str, ", ", pendingString);
    }
}
