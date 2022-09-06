package com.amazon.alexa.accessory.capabilities.crypto;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
class CancellableRunnable implements Runnable {
    private final AtomicBoolean isCancelled = new AtomicBoolean(false);
    private final Object lock;
    private final Runnable runnable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CancellableRunnable(Object obj, Runnable runnable) {
        this.lock = obj;
        this.runnable = runnable;
    }

    public void cancel() {
        this.isCancelled.set(true);
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.lock) {
            if (!this.isCancelled.get()) {
                this.runnable.run();
            }
        }
    }
}
