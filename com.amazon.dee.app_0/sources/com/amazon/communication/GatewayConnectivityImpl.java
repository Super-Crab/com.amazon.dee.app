package com.amazon.communication;

import amazon.communication.GatewayConnectivity;
import amazon.communication.connection.ConnectionClosedDetails;
import android.os.RemoteException;
import com.amazon.communication.IConnectionListener;
import com.amazon.communication.connection.ClosedConnectionReasonFactory;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public class GatewayConnectivityImpl extends IConnectionListener.Stub implements GatewayConnectivity {
    private static final DPLogger log = new DPLogger("TComm.GatewayConnectivityImpl");
    private IGatewayConnectivity mGatewayConnectivity;
    private final Set<GatewayConnectivity.GatewayConnectivityMonitor> mListeners = Collections.synchronizedSet(new HashSet(2));
    protected final AtomicInteger mState = new AtomicInteger(0);

    private boolean isValidState(int i) {
        return i >= 0 && i <= 4;
    }

    private void notifyStateClosed(int i, String str) {
        log.info("notifyStateClosed", "gateway connection closed", "statusCode", Integer.valueOf(i), "closeReason", ClosedConnectionReasonFactory.getReasonForStatusCode(i), "message", str, "number of listeners", Integer.valueOf(this.mListeners.size()));
        synchronized (this.mListeners) {
            for (GatewayConnectivity.GatewayConnectivityMonitor gatewayConnectivityMonitor : this.mListeners) {
                gatewayConnectivityMonitor.onGatewayConnectionClosed(new ConnectionClosedDetails(i, str));
                log.debug("notifyStateClosed", "Notifying state Closed", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, gatewayConnectivityMonitor);
            }
        }
    }

    private void notifyStateOpened() {
        log.info("notifyStateOpened", "gateway connection opened", "number of listeners", Integer.valueOf(this.mListeners.size()));
        synchronized (this.mListeners) {
            for (GatewayConnectivity.GatewayConnectivityMonitor gatewayConnectivityMonitor : this.mListeners) {
                gatewayConnectivityMonitor.onGatewayConnectionEstablished();
                log.debug("notifyStateOpened", "Notifying state Opened", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, gatewayConnectivityMonitor);
            }
        }
    }

    private boolean shouldNotifyOnClosure(int i, int i2) {
        return i2 == 4 ? i != 3 : i2 == 3;
    }

    @Override // amazon.communication.GatewayConnectivity
    public void deregisterGatewayConnectivityMonitor(GatewayConnectivity.GatewayConnectivityMonitor gatewayConnectivityMonitor) {
        log.debug("deregisterGatewayConnectivityMonitor", "monitor", gatewayConnectivityMonitor);
        if (gatewayConnectivityMonitor != null) {
            synchronized (this.mListeners) {
                if (this.mListeners.contains(gatewayConnectivityMonitor)) {
                    this.mListeners.remove(gatewayConnectivityMonitor);
                } else {
                    throw new IllegalArgumentException("GatewayConnectivityMonitor isn't registered");
                }
            }
            return;
        }
        throw new IllegalArgumentException("GatewayConnectivityMonitor must not be null");
    }

    @Override // amazon.communication.GatewayConnectivity
    public int getGatewayConnectionState() throws amazon.communication.TCommServiceDownException {
        try {
            int gatewayConnectionState = this.mGatewayConnectivity.getGatewayConnectionState();
            this.mState.set(gatewayConnectionState);
            return gatewayConnectionState;
        } catch (RemoteException e) {
            throw new amazon.communication.TCommServiceDownException(e);
        }
    }

    @Override // com.amazon.communication.IConnectionListener
    public void onConnectionSetInitialState(int i) throws RemoteException {
        log.verbose("onConnectionSetInitialState", "Setting initial connection state", "state", Integer.valueOf(i));
        try {
            FailFast.expectTrue(isValidState(i));
            this.mState.set(i);
        } catch (RuntimeException e) {
            log.warn("onConnectionSetInitialState", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.IConnectionListener
    public void onConnectionStateChanged(int i, int i2, String str) throws RemoteException {
        log.verbose("onConnectionStateChanged", "Connection state changed", "state", Integer.valueOf(i), "statusCode", Integer.valueOf(i2), "message", str);
        try {
            FailFast.expectTrue(isValidState(i));
            int andSet = this.mState.getAndSet(i);
            log.debug("onConnectionStateChanged", "Socket states", "state", this.mState, "oldState", Integer.valueOf(andSet));
            if (i == andSet) {
                return;
            }
            if (shouldNotifyOnClosure(andSet, i)) {
                notifyStateClosed(i2, str);
            } else if (i != 2) {
            } else {
                notifyStateOpened();
            }
        } catch (RuntimeException e) {
            log.warn("onConnectionStateChanged", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // amazon.communication.GatewayConnectivity
    public int registerGatewayConnectivityMonitor(GatewayConnectivity.GatewayConnectivityMonitor gatewayConnectivityMonitor) {
        log.debug("registerGatewayConnectivityMonitor", "monitor", gatewayConnectivityMonitor);
        if (gatewayConnectivityMonitor != null) {
            this.mListeners.add(gatewayConnectivityMonitor);
            return this.mState.get();
        }
        throw new IllegalArgumentException("GatewayConnectivityMonitor must not be null");
    }

    public void setGatewayConnectivityInterface(IGatewayConnectivity iGatewayConnectivity) throws amazon.communication.TCommServiceDownException {
        if (iGatewayConnectivity != null) {
            if (this.mGatewayConnectivity == null) {
                this.mGatewayConnectivity = iGatewayConnectivity;
                try {
                    this.mState.set(this.mGatewayConnectivity.getGatewayConnectionState());
                    return;
                } catch (RemoteException unused) {
                    throw new amazon.communication.TCommServiceDownException("Failed to get connection state");
                }
            }
            throw new IllegalStateException("mGatewayConnectivity is already set");
        }
        throw new IllegalArgumentException("connectivity must not be null");
    }
}
