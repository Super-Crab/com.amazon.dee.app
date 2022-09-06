package com.amazon.communication.rlm;

import amazon.communication.rlm.AckHandler;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ServiceSideAckHandlerProxy implements AckHandler, IBinder.DeathRecipient {
    private static final DPLogger log = new DPLogger("TComm.ServiceSideAckHandlerProxy");
    private IAckHandler mClientAckHandler;
    private final Object mLock = new Object();

    public ServiceSideAckHandlerProxy(IAckHandler iAckHandler) throws RemoteException {
        IBinder asBinder = iAckHandler.asBinder();
        this.mClientAckHandler = iAckHandler;
        try {
            asBinder.linkToDeath(this, 0);
        } catch (RemoteException e) {
            binderDied();
            throw e;
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        synchronized (this.mLock) {
            log.debug("binderDied", "binder died", new Object[0]);
            this.mClientAckHandler = null;
        }
    }

    @Override // amazon.communication.rlm.AckHandler
    public void onAck(int i) {
        synchronized (this.mLock) {
            if (this.mClientAckHandler == null) {
                log.warn("onAck", "Client-side callback object is now stale", new Object[0]);
                return;
            }
            IAckHandler iAckHandler = this.mClientAckHandler;
            try {
                iAckHandler.onAck(i);
            } catch (RemoteException e) {
                log.warn("onSuccess", "Client-side callback object is now stale", e);
            }
        }
    }

    @Override // amazon.communication.rlm.AckHandler
    public void onNack(int i, int i2, String str) {
        synchronized (this.mLock) {
            if (this.mClientAckHandler == null) {
                log.warn("onNack", "Client-side callback object is now stale", new Object[0]);
                return;
            }
            IAckHandler iAckHandler = this.mClientAckHandler;
            try {
                iAckHandler.onNack(i, i2, str);
            } catch (RemoteException e) {
                log.warn("onNack", "Client-side callback object is now stale", e);
            }
        }
    }

    @Override // amazon.communication.rlm.AckHandler
    public void onPack(int i, int i2, String str) {
        synchronized (this.mLock) {
            if (this.mClientAckHandler == null) {
                log.warn("onPack", "Client-side callback object is now stale", new Object[0]);
                return;
            }
            IAckHandler iAckHandler = this.mClientAckHandler;
            try {
                iAckHandler.onPack(i, i2, str);
            } catch (RemoteException e) {
                log.warn("onPack", "Client-side callback object is now stale", e);
            }
        }
    }
}
