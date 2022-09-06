package com.amazon.communication;

import android.os.RemoteException;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
class ServiceSideConnectionProxy extends ServiceSideConnectionProxyBase {
    private static final String METRICS_SOURCE = "ServiceSideConnectionProxy";
    private static final DPLogger log = new DPLogger("TComm.ServiceSideConnectionProxy");
    private final AtomicReference<ProtocolSocket> mProtocolSocket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceSideConnectionProxy(ResponseRouter responseRouter, IConnectionListener iConnectionListener, BandwidthToolByteAccountant bandwidthToolByteAccountant, PeriodicMetricReporter periodicMetricReporter) throws RemoteException {
        super(responseRouter, iConnectionListener, bandwidthToolByteAccountant, periodicMetricReporter);
        this.mProtocolSocket = new AtomicReference<>();
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase
    protected String getMetricsSource() {
        return METRICS_SOURCE;
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase
    protected ProtocolSocket getProtocolSocket() {
        return this.mProtocolSocket.get();
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase, com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        boolean z = true;
        log.verbose("notifyStateChanged", "state changed", "socket", protocolSocket);
        ProtocolSocket protocolSocket2 = getProtocolSocket();
        if (protocolSocket != protocolSocket2) {
            log.error("notifyStateChanged", "unexpected callback - received notification for a socket different than what I have.", "expectedSocket", protocolSocket2, "actualSocket", protocolSocket);
            return;
        }
        super.notifyStateChanged(protocolSocket);
        if (protocolSocket.socketState().toConnectionState() != 4) {
            z = false;
        }
        if (!z) {
            return;
        }
        log.info("notifyStateChanged", "socket has closed; doing the cleanup by calling release", new Object[0]);
        try {
            release();
        } catch (RemoteException unused) {
        }
    }

    @Override // com.amazon.communication.ServiceSideConnectionProxyBase, com.amazon.communication.IConnection
    public void release() throws RemoteException {
        try {
            log.verbose("release", "releasing ServiceSideConnectionProxy", new Object[0]);
            super.release();
            ProtocolSocket protocolSocket = getProtocolSocket();
            if (protocolSocket == null) {
                return;
            }
            protocolSocket.removeStateListener(this);
            protocolSocket.release();
        } catch (RuntimeException e) {
            log.warn("release", "Exception occurred!", e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProtocolSocket(ProtocolSocket protocolSocket) {
        protocolSocket.retain();
        this.mProtocolSocket.set(protocolSocket);
        protocolSocket.addStateListener(this);
        notifyInitialState(protocolSocket);
    }
}
