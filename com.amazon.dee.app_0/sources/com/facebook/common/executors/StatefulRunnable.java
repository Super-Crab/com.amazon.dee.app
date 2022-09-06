package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class StatefulRunnable<T> implements Runnable {
    protected static final int STATE_CANCELLED = 2;
    protected static final int STATE_CREATED = 0;
    protected static final int STATE_FAILED = 4;
    protected static final int STATE_FINISHED = 3;
    protected static final int STATE_STARTED = 1;
    protected final AtomicInteger mState = new AtomicInteger(0);

    public void cancel() {
        if (this.mState.compareAndSet(0, 2)) {
            onCancellation();
        }
    }

    protected void disposeResult(@Nullable T result) {
    }

    @Nullable
    /* renamed from: getResult */
    protected abstract T mo6924getResult() throws Exception;

    protected void onCancellation() {
    }

    protected void onFailure(Exception e) {
    }

    protected void onSuccess(@Nullable T result) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.mState.compareAndSet(0, 1)) {
            return;
        }
        try {
            T mo6924getResult = mo6924getResult();
            this.mState.set(3);
            try {
                onSuccess(mo6924getResult);
            } finally {
                disposeResult(mo6924getResult);
            }
        } catch (Exception e) {
            this.mState.set(4);
            onFailure(e);
        }
    }
}
