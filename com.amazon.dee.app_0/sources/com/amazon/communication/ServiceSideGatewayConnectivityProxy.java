package com.amazon.communication;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.communication.IGatewayConnectivity;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.gw.GatewayStateListener;
import com.amazon.dp.logger.DPLogger;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
class ServiceSideGatewayConnectivityProxy extends IGatewayConnectivity.Stub implements IBinder.DeathRecipient, GatewayStateListener {
    private static final DPLogger log = new DPLogger("TComm.ServiceSideGatewayConnectivityProxy");
    private GatewayConnectivityListener mGatewayConnectivityListener;
    private AtomicReference<IConnectionListener> mListenerCallback = new AtomicReference<>(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceSideGatewayConnectivityProxy(GatewayConnectivityListener gatewayConnectivityListener, IConnectionListener iConnectionListener) throws RemoteException {
        log.verbose("[constructor]", "creating ServiceSideGatewayConnectivityProxy", "client pid", Integer.valueOf(Binder.getCallingPid()), "this", this);
        this.mListenerCallback.set(iConnectionListener);
        this.mGatewayConnectivityListener = gatewayConnectivityListener;
        try {
            iConnectionListener.asBinder().linkToDeath(this, 0);
        } catch (RemoteException e) {
            binderDied();
            throw e;
        }
    }

    private void deregisterSelf() {
        log.verbose("deregisterSelf", "deregistering from Gateway socket notifications", new Object[0]);
        this.mGatewayConnectivityListener.removeListener(this);
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        IConnectionListener andSet = this.mListenerCallback.getAndSet(null);
        if (andSet != null) {
            try {
                log.info("binderDied", "unlinked binder", "result", Boolean.valueOf(andSet.asBinder().unlinkToDeath(this, 0)));
            } catch (NoSuchElementException e) {
                log.warn("binderDied", "caught exception unlinking", e);
            }
            deregisterSelf();
        }
    }

    @Override // com.amazon.communication.IGatewayConnectivity
    public int getGatewayConnectionState() {
        try {
            return this.mGatewayConnectivityListener.getGatewayConnectivityState();
        } catch (RuntimeException e) {
            log.warn("getGatewayConnectionState", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.gw.GatewayStateListener
    public void onConnectionStateChanged(int i, int i2, String str) {
        log.debug("onConnectionStateChanged", "state", Integer.valueOf(i), "this", this);
        IConnectionListener iConnectionListener = this.mListenerCallback.get();
        if (iConnectionListener != null) {
            try {
                iConnectionListener.onConnectionStateChanged(i, i2, str);
                return;
            } catch (RemoteException unused) {
                log.warn("onConnectionStateChanged", "client-side callback object is now stale. Deregistering self", new Object[0]);
                deregisterSelf();
                return;
            }
        }
        log.warn("onConnectionStateChanged", "null callback, deregistering self", new Object[0]);
        deregisterSelf();
    }
}
