package com.dp.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public abstract class CancelableCallable implements Callable<Void> {
    private final AtomicBoolean mIsCallableAlive = new AtomicBoolean(true);
    private final String mThreadGuardCheckedName;

    public CancelableCallable(String str) {
        this.mThreadGuardCheckedName = str;
    }

    public void cancel() {
        this.mIsCallableAlive.set(false);
    }

    protected abstract void work() throws Exception;

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        ThreadGuard.ensureThreadPrefix(this.mThreadGuardCheckedName);
        if (this.mIsCallableAlive.get()) {
            work();
            return null;
        }
        return null;
    }
}
