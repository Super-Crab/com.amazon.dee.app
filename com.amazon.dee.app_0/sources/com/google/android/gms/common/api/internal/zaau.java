package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import java.util.concurrent.locks.Lock;
/* loaded from: classes2.dex */
abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagj;

    private zaau(zaak zaakVar) {
        this.zagj = zaakVar;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public void run() {
        Lock lock;
        Lock lock2;
        zabe zabeVar;
        lock = this.zagj.zaeo;
        lock.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zaan();
        } catch (RuntimeException e) {
            zabeVar = this.zagj.zaft;
            zabeVar.zab(e);
        } finally {
            lock2 = this.zagj.zaeo;
            lock2.unlock();
        }
    }

    @WorkerThread
    protected abstract void zaan();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaau(zaak zaakVar, zaal zaalVar) {
        this(zaakVar);
    }
}
