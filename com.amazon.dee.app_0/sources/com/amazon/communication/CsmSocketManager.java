package com.amazon.communication;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.CsmProtocolSocket;
import com.amazon.communication.socket.EndpointNotAuthenticatedException;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.RemoteDeviceGatewaySocket;
import com.amazon.communication.socket.SocketManager;
import com.amazon.communication.socket.SocketManagerBase;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes12.dex */
public class CsmSocketManager implements SocketManager, ProtocolSocket.ProtocolSocketStateListener {
    private static final DPLogger log = new DPLogger("TComm.CsmSocketManager");
    private CsmProtocolSocket mCsmProtocolSocket;
    PeriodicMetricReporter mPeriodicMetricReporter;
    protected ProtocolHandlerManager mProtocolHandlerManager;
    protected WorkExecutor mWorkExecutor;
    private final Set<ProtocolSocket.ProtocolSocketStateListener> mListenerSet = new CopyOnWriteArraySet();
    private final SocketManagerBase.BaseSocketCollection mD2DSockets = new SocketManagerBase.BaseSocketCollection();

    public CsmSocketManager(WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, PeriodicMetricReporter periodicMetricReporter) throws IOException {
        this.mWorkExecutor = workExecutor;
        this.mProtocolHandlerManager = protocolHandlerManager;
        this.mPeriodicMetricReporter = periodicMetricReporter;
    }

    public void addSocketStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        log.verbose("addSocketStateListener", "adding listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener.toString());
        this.mListenerSet.add(protocolSocketStateListener);
        CsmProtocolSocket csmProtocolSocket = this.mCsmProtocolSocket;
        if (csmProtocolSocket != null) {
            csmProtocolSocket.addStateListener(protocolSocketStateListener);
        }
    }

    @Override // com.amazon.communication.socket.SocketManager
    public void connect(ProtocolSocket protocolSocket) {
        if (protocolSocket instanceof RemoteDeviceGatewaySocket) {
            this.mD2DSockets.addSocket(protocolSocket.getUniqueEndpointIdentifier(), protocolSocket);
            log.debug(EmulateConnection.EXTRA_CONNECT, "added socket to active D2D list", "identifier", protocolSocket.getUniqueEndpointIdentifier());
            protocolSocket.addStateListener(this);
        }
    }

    @Override // com.amazon.communication.socket.SocketManager
    public ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, ConnectReason connectReason, String str) {
        return getCsmProtocolSocket();
    }

    @Override // com.amazon.communication.socket.SocketManager
    public synchronized List<ProtocolSocket> getActiveProtocolSockets() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.add(getCsmProtocolSocket());
        arrayList.addAll(this.mD2DSockets.getAllSockets());
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized CsmProtocolSocket getCsmProtocolSocket() {
        if (this.mCsmProtocolSocket == null) {
            CsmProtocolSocket csmProtocolSocket = new CsmProtocolSocket(this.mProtocolHandlerManager, ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES, this.mWorkExecutor, this.mPeriodicMetricReporter);
            for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : this.mListenerSet) {
                csmProtocolSocket.addStateListener(protocolSocketStateListener);
            }
            connect(csmProtocolSocket);
            log.info("getCsmProtocolSocket", "created a new CsmProtocolSocket because none exist", "socket", csmProtocolSocket);
            this.mCsmProtocolSocket = csmProtocolSocket;
        }
        log.info("getCsmProtocolSocket", "retrieved CsmProtocolSocket", "socket", this.mCsmProtocolSocket);
        return this.mCsmProtocolSocket;
    }

    @Override // com.amazon.communication.socket.SocketManager
    public List<ProtocolSocket> getExistingProtocolSockets(UniqueEndpointIdentifier uniqueEndpointIdentifier, Set<ProtocolSocket.ProtocolSocketAttribute> set) throws EndpointNotAuthenticatedException {
        return this.mD2DSockets.getSocketsForEndpoint(uniqueEndpointIdentifier, set);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        if (protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
            log.verbose("notifyStateChanged", "removing DISCONNECTED socket from SocketManager if it's a D2D socket", "protocolSocket", protocolSocket);
            this.mD2DSockets.removeSocket(protocolSocket);
            protocolSocket.removeStateListener(this);
        }
    }

    public void shutdown() {
        CsmProtocolSocket csmProtocolSocket = this.mCsmProtocolSocket;
        if (csmProtocolSocket != null) {
            csmProtocolSocket.close();
            this.mCsmProtocolSocket = null;
        }
        for (ProtocolSocket protocolSocket : this.mD2DSockets.getAllSockets()) {
            log.verbose("shutdown", "releasing socket", "socket", protocolSocket);
            protocolSocket.removeStateListener(this);
            protocolSocket.close(new CloseDetail(CloseStatusCodes.SOCKET_MANAGER_SHUTTING_DOWN, "SocketManager shutting down"));
        }
        this.mD2DSockets.clear();
        this.mListenerSet.clear();
        this.mWorkExecutor.shutdown();
        this.mProtocolHandlerManager = null;
        this.mPeriodicMetricReporter.shutdown();
    }

    @Override // com.amazon.communication.socket.SocketManager
    public ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, Purpose purpose, ConnectReason connectReason, String str) {
        return getCsmProtocolSocket();
    }
}
