package com.amazon.alexa.voice.ui.onedesign.util.image;
/* loaded from: classes11.dex */
abstract class CancellableRunnable implements Runnable {
    private volatile boolean cancelled = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            onCancelled();
        }
    }

    abstract void onCancelled();

    @Override // java.lang.Runnable
    public abstract void run();
}
