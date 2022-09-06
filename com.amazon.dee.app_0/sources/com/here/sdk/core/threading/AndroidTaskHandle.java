package com.here.sdk.core.threading;

import java.util.concurrent.Future;
/* loaded from: classes3.dex */
class AndroidTaskHandle implements TaskHandle {
    private final Future<?> mFuture;

    AndroidTaskHandle(Future<?> future) {
        this.mFuture = future;
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean cancel() {
        return this.mFuture.cancel(false);
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean isCancelled() {
        return this.mFuture.isCancelled();
    }

    @Override // com.here.sdk.core.threading.TaskHandle
    public boolean isFinished() {
        return this.mFuture.isDone();
    }
}
