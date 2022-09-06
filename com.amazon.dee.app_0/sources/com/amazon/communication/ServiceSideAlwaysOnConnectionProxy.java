package com.amazon.communication;

import amazon.communication.connection.ConnectionClosedDetails;
import android.os.RemoteException;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.AlwaysOnSocketWatchdog;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class ServiceSideAlwaysOnConnectionProxy extends ServiceSideConnectionProxyBase implements AlwaysOnSocketWatchdog.WatchdogStateListener {
    private static final String METRICS_SOURCE = "ServiceSideAlwaysOnConnectionProxy";
    private static final DPLogger log = new DPLogger("TComm.ServiceSideAlwaysOnConnectionProxy");
    private final AtomicReference<AlwaysOnSocketWatchdog> mProbingWatchdog;
    private final AtomicReference<AlwaysOnSocketWatchdog> mWatchdog;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceSideAlwaysOnConnectionProxy(ResponseRouter responseRouter, IConnectionListener iConnectionListener, BandwidthToolByteAccountant bandwidthToolByteAccountant, PeriodicMetricReporter periodicMetricReporter) throws RemoteException {
        super(responseRouter, iConnectionListener, bandwidthToolByteAccountant, periodicMetricReporter);
        this.mWatchdog = new AtomicReference<>();
        this.mProbingWatchdog = new AtomicReference<>();
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase
    protected ConnectionClosedDetails getConnectionCloseDetails(ProtocolSocket protocolSocket) {
        if (protocolSocket.socketState().toConnectionState() == 4) {
            return new ConnectionClosedDetails(2, "Connection failure, reconnecting");
        }
        return null;
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase
    protected String getMetricsSource() {
        return METRICS_SOURCE;
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase
    protected ProtocolSocket getProtocolSocket() {
        AlwaysOnSocketWatchdog alwaysOnSocketWatchdog = this.mWatchdog.get();
        if (alwaysOnSocketWatchdog != null) {
            return alwaysOnSocketWatchdog.getProtocolSocket();
        }
        return null;
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase, com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        if (protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
            ProtocolSocket protocolSocket2 = this.mWatchdog.get() == null ? null : this.mWatchdog.get().getProtocolSocket();
            if (protocolSocket2 != null && protocolSocket2.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                log.debug("notifyStateChanged", "received DISCONNECTED state, we already have a new socket in CONNECTED state", new Object[0]);
                return;
            }
        }
        super.notifyStateChanged(protocolSocket);
    }

    @Override // com.amazon.communication.AlwaysOnSocketWatchdog.WatchdogStateListener
    public void onWatchdogClosed() {
        log.debug("onWatchdogClosed", "Got SocketWatchdog callback, notifying caller of termination.", new Object[0]);
        IConnectionListener listenerCallback = getListenerCallback();
        if (listenerCallback != null) {
            try {
                try {
                    listenerCallback.onConnectionStateChanged(4, 3, "Connection terminated, will no longer attempt to reconnect");
                } catch (RemoteException e) {
                    log.warn("onWatchdogClosed", "Error notifying client of watchdog closing", e);
                }
            } catch (Throwable th) {
                try {
                    release();
                } catch (Exception unused) {
                }
                throw th;
            }
        }
        try {
            release();
        } catch (Exception unused2) {
        }
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase, com.amazon.communication.IConnection
    public void release() throws RemoteException {
        AlwaysOnSocketWatchdog andSet = this.mWatchdog.getAndSet(null);
        AlwaysOnSocketWatchdog alwaysOnSocketWatchdog = this.mProbingWatchdog.get();
        log.info("release", "releasing watchdog", "watchdog", andSet);
        try {
            super.release();
            if (andSet != null) {
                andSet.removeSocketStateListener(this);
                andSet.removeWatchdogStateListener(this);
                andSet.release();
            }
            if (alwaysOnSocketWatchdog == null) {
                return;
            }
            alwaysOnSocketWatchdog.release();
        } catch (RuntimeException e) {
            log.warn("release", "Error releasing ServiceSideAlwaysOnConnectionProxy", e);
            throw e;
        }
    }

    public void setProbingWatchdog(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog) {
        AlwaysOnSocketWatchdog alwaysOnSocketWatchdog2 = this.mWatchdog.get();
        FailFast.expectNotNull(alwaysOnSocketWatchdog2, "Watchdog has not yet been set on this Proxy");
        boolean equals = GatewayConnectionService.GATEWAY_ENDPOINT.equals(alwaysOnSocketWatchdog2.getWatchedEndpoint());
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Only Gateway connection proxy can be associated with a probing watchdog. This proxy: ");
        outline107.append(alwaysOnSocketWatchdog2.getWatchedEndpoint());
        FailFast.expectTrue(equals, outline107.toString());
        boolean equals2 = GatewayConnectionService.GATEWAY_PROBING_ENDPOINT.equals(alwaysOnSocketWatchdog.getWatchedEndpoint());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Only the Probing endpoint can be used as a probing watchdog. This endpoint: ");
        outline1072.append(alwaysOnSocketWatchdog.getWatchedEndpoint());
        FailFast.expectTrue(equals2, outline1072.toString());
        this.mProbingWatchdog.set(alwaysOnSocketWatchdog);
    }

    public void setWatchdog(AlwaysOnSocketWatchdog alwaysOnSocketWatchdog) {
        alwaysOnSocketWatchdog.addSocketStateListener(this);
        alwaysOnSocketWatchdog.addWatchdogStateListener(this);
        this.mWatchdog.set(alwaysOnSocketWatchdog);
        ProtocolSocket protocolSocket = alwaysOnSocketWatchdog.getProtocolSocket();
        if (protocolSocket != null) {
            notifyInitialState(protocolSocket);
        }
    }
}
