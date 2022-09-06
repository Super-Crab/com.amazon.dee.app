package com.amazon.communication.socket.decisionengine;

import amazon.communication.connection.Policy;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.SocketDecisionEngineBase;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.GatewaySocket;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.ProtocolSocketBase;
import com.amazon.communication.socket.RemoteDeviceGatewaySocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class CsmSocketDecisionEngine extends SocketDecisionEngineBase {
    private static final DPLogger log = new DPLogger("TComm.CsmSocketDecisionEngine");
    private final Object mDecisionLock = new Object();
    private final GatewayControlProtocol mGatewayControlProtocol;
    private final GatewayApplicationProtocol mGatewayProtocol;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    protected final SocketManager mSocketManager;
    private final WorkExecutor mWorkExecutor;

    /* renamed from: com.amazon.communication.socket.decisionengine.CsmSocketDecisionEngine$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$communication$identity$EndpointIdentity$Type = new int[EndpointIdentity.Type.values().length];

        static {
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.URL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public CsmSocketDecisionEngine(SocketManager socketManager, WorkExecutor workExecutor, GatewayApplicationProtocol gatewayApplicationProtocol, GatewayControlProtocol gatewayControlProtocol, PeriodicMetricReporter periodicMetricReporter) {
        this.mSocketManager = socketManager;
        this.mWorkExecutor = workExecutor;
        this.mGatewayProtocol = gatewayApplicationProtocol;
        this.mGatewayControlProtocol = gatewayControlProtocol;
        this.mPeriodicMetricReporter = periodicMetricReporter;
    }

    @Override // com.amazon.communication.SocketDecisionEngine
    public ProtocolSocket acquireSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException {
        ProtocolSocketBase protocolSocketBase;
        if (endpointIdentity != null) {
            if (endpointIdentity.getType() != null) {
                synchronized (this.mDecisionLock) {
                    int ordinal = endpointIdentity.getType().ordinal();
                    if (ordinal == 0) {
                        log.debug("acquireSocket", "Wrapping the CsmProtocolSocket in a RemoteDeviceGatewaySocket", new Object[0]);
                        ProtocolSocket createProtocolSocket = this.mSocketManager.createProtocolSocket(endpointIdentity, ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES, null, null, null);
                        FailFast.expectNotNull(createProtocolSocket);
                        ProtocolSocketBase remoteDeviceGatewaySocket = new RemoteDeviceGatewaySocket((DeviceIdentity) endpointIdentity, this.mWorkExecutor, this.mGatewayProtocol, createProtocolSocket, null, this.mGatewayControlProtocol, this.mPeriodicMetricReporter);
                        this.mSocketManager.connect(remoteDeviceGatewaySocket);
                        protocolSocketBase = remoteDeviceGatewaySocket;
                    } else if (ordinal != 1 && ordinal != 2) {
                        log.warn("acquireSocket", "Unexpected condition, DecisionEngine is unsure what type ofsocket to provide for the current situation.", "destination", endpointIdentity);
                        protocolSocketBase = null;
                    } else {
                        log.debug("acquireSocket", "Destination is a not device, returning the CsmProtocolSocket", new Object[0]);
                        ProtocolSocket createProtocolSocket2 = this.mSocketManager.createProtocolSocket(endpointIdentity, ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES, null, null, null);
                        FailFast.expectNotNull(createProtocolSocket2);
                        protocolSocketBase = new GatewaySocket(endpointIdentity, this.mGatewayProtocol, createProtocolSocket2);
                    }
                    FailFast.expectNotNull(protocolSocketBase);
                    log.info("acquireSocket", "returning", "socket", protocolSocketBase);
                }
                return protocolSocketBase;
            }
            throw new IllegalArgumentException("Argument: destination type must not be null");
        }
        throw new IllegalArgumentException("Argument: destination must not be null");
    }
}
