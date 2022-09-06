package com.amazon.identity.auth.device;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class bk implements Runnable {
    private static final String TAG = bk.class.getName();
    private volatile CountDownLatch he = new CountDownLatch(1);
    private AtomicBoolean hf = new AtomicBoolean(false);

    public void asyncOperationComplete() {
        this.he.countDown();
    }

    protected void interruptAsyncOperation() {
    }

    protected void onTimeout() {
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        run(null, null, null);
    }

    protected abstract void startAsyncOperation();

    public synchronized void run(Long l, TimeUnit timeUnit, String str) {
        if (this.hf.getAndSet(true)) {
            io.e(TAG, "Attempted to call run() more than once on the same object.");
            return;
        }
        io.a("Starting the operation %s and waiting for it to finish", str);
        startAsyncOperation();
        do {
            if (l != null) {
                try {
                    if (!this.he.await(l.longValue(), timeUnit)) {
                        onTimeout();
                    }
                } catch (InterruptedException unused) {
                    interruptAsyncOperation();
                }
            } else {
                this.he.await();
            }
        } while (this.he.getCount() > 0);
        io.a("Completed the operation %s", str);
    }
}
