package com.amazon.communication;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.gw.DeviceGatewayHandshakeHandler;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.amazon.communication.socket.SocketUsageAggregatedReader;
import com.amazon.communication.socket.decisionengine.DeviceIdentitySocketDecisionEngine;
import com.amazon.communication.socket.decisionengine.ServiceIdentitySocketDecisionEngine;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class DeviceSocketDecisionEngine extends SocketDecisionEngineBase {
    private static final DPLogger log = new DPLogger("TComm.DeviceSocketDecisionEngine");
    private final Object mDecisionLock;
    protected final DeviceIdentitySocketDecisionEngine mDeviceIdentitySocketDecisionEngine;
    protected final ServiceIdentitySocketDecisionEngine mServiceIdentitySocketDecisionEngine;

    /* renamed from: com.amazon.communication.DeviceSocketDecisionEngine$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$communication$identity$EndpointIdentity$Type = new int[EndpointIdentity.Type.values().length];

        static {
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.DEVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$communication$identity$EndpointIdentity$Type[EndpointIdentity.Type.URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DeviceSocketDecisionEngine(ConnectivityManagerWrapper connectivityManagerWrapper, GatewayApplicationProtocol gatewayApplicationProtocol, GatewayControlProtocol gatewayControlProtocol, PeriodicMetricReporter periodicMetricReporter, SocketManager socketManager, SocketUsageAggregatedReader socketUsageAggregatedReader, IdentityResolver identityResolver, WorkExecutor workExecutor, AlwaysOnSocketWatchdogManager alwaysOnSocketWatchdogManager, DeviceGatewayHandshakeHandler deviceGatewayHandshakeHandler, GatewayConnectivityListener gatewayConnectivityListener) {
        this(new DeviceIdentitySocketDecisionEngine(connectivityManagerWrapper, gatewayApplicationProtocol, gatewayControlProtocol, periodicMetricReporter, socketManager, socketUsageAggregatedReader, identityResolver, workExecutor, alwaysOnSocketWatchdogManager, deviceGatewayHandshakeHandler, gatewayConnectivityListener), new ServiceIdentitySocketDecisionEngine(connectivityManagerWrapper, gatewayApplicationProtocol, gatewayControlProtocol, socketManager, socketUsageAggregatedReader, identityResolver, workExecutor));
    }

    @Override // com.amazon.communication.SocketDecisionEngine
    public ProtocolSocket acquireSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException {
        ProtocolSocket acquireSocket;
        if (endpointIdentity != null) {
            if (policy != null) {
                log.info("acquireSocket", "entering", "destination", EndpointIdentity.logSafe(endpointIdentity), "irDestination", iRServiceEndpoint, "policy", policy, "connectReason", connectReason, "directedIdHash", Integer.valueOf(str != null ? str.hashCode() : 0));
                synchronized (this.mDecisionLock) {
                    int ordinal = endpointIdentity.getType().ordinal();
                    if (ordinal != 0) {
                        if (ordinal != 1 && ordinal != 2) {
                            throw new IllegalArgumentException("Unhandled endpoint identity: " + endpointIdentity);
                        }
                        acquireSocket = this.mServiceIdentitySocketDecisionEngine.acquireSocket(endpointIdentity, iRServiceEndpoint, policy, connectReason, str);
                    } else {
                        acquireSocket = this.mDeviceIdentitySocketDecisionEngine.acquireSocket(endpointIdentity, iRServiceEndpoint, policy, connectReason, str);
                    }
                }
                FailFast.expectNotNull(acquireSocket);
                log.info("acquireSocket", "returning", "socket", acquireSocket);
                return acquireSocket;
            }
            throw new IllegalArgumentException("Argument: policy must not be null");
        }
        throw new IllegalArgumentException("Argument: destination must not be null");
    }

    public DeviceSocketDecisionEngine(DeviceIdentitySocketDecisionEngine deviceIdentitySocketDecisionEngine, ServiceIdentitySocketDecisionEngine serviceIdentitySocketDecisionEngine) {
        this.mDecisionLock = new Object();
        this.mDeviceIdentitySocketDecisionEngine = deviceIdentitySocketDecisionEngine;
        this.mServiceIdentitySocketDecisionEngine = serviceIdentitySocketDecisionEngine;
    }
}
