package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ef extends bk {
    private static final Executor ln = Executors.newFixedThreadPool(4, ji.dG("MAP-SyncBoundServiceCallerThreadPool"));
    private final df lo;

    public ef(Context context, Intent intent, int i) {
        this.lo = new df(context, intent, i) { // from class: com.amazon.identity.auth.device.ef.1
            @Override // com.amazon.identity.auth.device.df
            protected void useService(final ComponentName componentName, final IBinder iBinder) throws RemoteException {
                ef.ln.execute(new Runnable() { // from class: com.amazon.identity.auth.device.ef.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ef.this.useService(componentName, iBinder);
                    }
                });
            }

            @Override // com.amazon.identity.auth.device.df
            protected void useService(IBinder iBinder) throws RemoteException {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doneUsingService() {
        this.lo.unbind();
        asyncOperationComplete();
    }

    @Override // com.amazon.identity.auth.device.bk
    public void startAsyncOperation() {
        if (!this.lo.call()) {
            doneUsingService();
        }
    }

    protected void useService(ComponentName componentName, IBinder iBinder) {
        useService(iBinder);
    }

    protected void useService(IBinder iBinder) {
    }
}
