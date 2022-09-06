package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperjoin.provisionerSDK.radios.PromiseFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class GattConnectionPromiseFactory implements PromiseFactory<Void> {
    private static final String TAG = PromiseFactory.class.getName();
    private Callable<GattClientCallback> mCreateConnection;
    private final ExecutorService mExecutor;

    public GattConnectionPromiseFactory(Callable<GattClientCallback> callable, ExecutorService executorService) {
        if (callable != null) {
            if (executorService != null) {
                this.mCreateConnection = callable;
                this.mExecutor = executorService;
                return;
            }
            throw new IllegalArgumentException("executor can not be null");
        }
        throw new IllegalArgumentException("createConnection can not be null");
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.radios.PromiseFactory
    public Future<Void> getFuture() {
        return this.mExecutor.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.GattConnectionPromiseFactory.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                return ((GattClientCallback) GattConnectionPromiseFactory.this.mCreateConnection.call()).getFuture().get();
            }
        });
    }
}
