package com.amazon.whisperjoin.provisionerSDK.radios;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class GenericPromiseFactory<TResult> implements PromiseFactory<TResult> {
    private final Callable<TResult> mAction;
    private final ExecutorService mExecutor;

    public GenericPromiseFactory(Callable<TResult> callable, ExecutorService executorService) {
        if (callable != null) {
            if (executorService != null) {
                this.mAction = callable;
                this.mExecutor = executorService;
                return;
            }
            throw new IllegalArgumentException("executor can not be null");
        }
        throw new IllegalArgumentException("action can not be null");
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.radios.PromiseFactory
    public Future<TResult> getFuture() {
        return this.mExecutor.submit(this.mAction);
    }
}
