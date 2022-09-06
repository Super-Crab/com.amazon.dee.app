package com.amazon.communication.socket.decisionengine;

import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.AlwaysOnSocketWatchdog;
import com.amazon.communication.AlwaysOnSocketWatchdogManager;
import com.amazon.communication.ConnectivityManagerWrapper;
import com.amazon.communication.GatewayConnectionService;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.gw.DeviceGatewayHandshakeHandler;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.RemoteDeviceGatewaySocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.amazon.communication.socket.SocketUsageAggregatedReader;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class DeviceIdentitySocketDecisionEngine extends EndpointIdentitySocketDecisionEngine {
    private static final int WAIT_FOR_NONNULL_SOCKET_SECONDS = 5;
    private static final DPLogger log = new DPLogger("TComm.DeviceIdentitySocketDecisionEngine");
    protected final AlwaysOnSocketWatchdogManager mAlwaysOnSocketWatchdogManager;
    private final Object mDecisionLock;
    private final GatewayConnectivityListener mGatewayConnectivityListener;
    protected final GatewayControlProtocol mGatewayControlProtocol;
    protected final DeviceGatewayHandshakeHandler mGatewayHandshakeHandler;
    protected final GatewayApplicationProtocol mGatewayProtocol;
    protected final IdentityResolver mIdentityResolver;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    protected final SocketManager mSocketManager;
    protected final SocketUsageAggregatedReader mSocketUsageAggregatedReader;
    protected final WorkExecutor mWorkExecutor;

    public DeviceIdentitySocketDecisionEngine(ConnectivityManagerWrapper connectivityManagerWrapper, GatewayApplicationProtocol gatewayApplicationProtocol, GatewayControlProtocol gatewayControlProtocol, PeriodicMetricReporter periodicMetricReporter, SocketManager socketManager, SocketUsageAggregatedReader socketUsageAggregatedReader, IdentityResolver identityResolver, WorkExecutor workExecutor, AlwaysOnSocketWatchdogManager alwaysOnSocketWatchdogManager, DeviceGatewayHandshakeHandler deviceGatewayHandshakeHandler, GatewayConnectivityListener gatewayConnectivityListener) {
        super(connectivityManagerWrapper);
        this.mDecisionLock = new Object();
        this.mGatewayProtocol = gatewayApplicationProtocol;
        this.mGatewayControlProtocol = gatewayControlProtocol;
        this.mSocketManager = socketManager;
        this.mSocketUsageAggregatedReader = socketUsageAggregatedReader;
        this.mIdentityResolver = identityResolver;
        this.mWorkExecutor = workExecutor;
        this.mAlwaysOnSocketWatchdogManager = alwaysOnSocketWatchdogManager;
        this.mGatewayHandshakeHandler = deviceGatewayHandshakeHandler;
        this.mGatewayConnectivityListener = gatewayConnectivityListener;
        this.mPeriodicMetricReporter = periodicMetricReporter;
    }

    private AlwaysOnSocketWatchdog getD2DGatewayWatchdog() throws SocketAcquisitionFailedException {
        try {
            IRServiceEndpoint resolveServiceEndpoint = this.mIdentityResolver.resolveServiceEndpoint(GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, Purpose.D2D_MESSAGING);
            if (resolveServiceEndpoint != null) {
                AlwaysOnSocketWatchdog andRetain = this.mAlwaysOnSocketWatchdogManager.getAndRetain(resolveServiceEndpoint, GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, GatewayConnectionService.D2D_GW_POLICY);
                andRetain.addSocketStateListener(this.mGatewayHandshakeHandler);
                andRetain.addSocketStateListener(this.mGatewayConnectivityListener);
                andRetain.startWatching(ConnectReason.ReasonString.ClientInitiated);
                log.debug("getD2DGatewayWatchdog", "found watchdog for D2D traffic", "irGwEndpoint", resolveServiceEndpoint, "policy", GatewayConnectionService.D2D_GW_POLICY, "watchdog", andRetain);
                return andRetain;
            }
            throw new SocketAcquisitionFailedException(DPFormattedMessage.toDPFormat("Error resolving directed gateway endpoint", "endpoint", GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, "purpose", Purpose.D2D_MESSAGING));
        } catch (RuntimeException e) {
            throw new SocketAcquisitionFailedException(DPFormattedMessage.toDPFormat("Error resolving directed gateway endpoint", "endpoint", GatewayConnectionService.DIRECTED_GATEWAY_ENDPOINT, "purpose", Purpose.D2D_MESSAGING), e);
        }
    }

    @Override // com.amazon.communication.SocketDecisionEngine
    public ProtocolSocket acquireSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException {
        if (!(endpointIdentity instanceof DeviceIdentity)) {
            throw new IllegalArgumentException("Argument: destination must be an instance of DeviceIdentity. Destination = " + endpointIdentity);
        } else if (policy != null) {
            ProtocolSocket protocolSocket = null;
            synchronized (this.mDecisionLock) {
                toggleNetworkPreference(policy);
                if (doesExistingProtocolSocketMeetRequirements(iRServiceEndpoint, policy)) {
                    log.verbose("acquireSocket", "Attempting to re-use an existing protocol socket", new Object[0]);
                    protocolSocket = reUseExistingProtocolSocket(this.mSocketManager, endpointIdentity, policy, iRServiceEndpoint);
                }
                if (protocolSocket == null) {
                    AlwaysOnSocketWatchdog d2DGatewayWatchdog = getD2DGatewayWatchdog();
                    try {
                        ProtocolSocket nonNullProtocolSocket = d2DGatewayWatchdog.getNonNullProtocolSocket(5, TimeUnit.SECONDS);
                        log.debug("acquireSocket", "got socket from wathdog", "socket", nonNullProtocolSocket, "destination", endpointIdentity);
                        protocolSocket = new RemoteDeviceGatewaySocket((DeviceIdentity) endpointIdentity, this.mWorkExecutor, this.mGatewayProtocol, nonNullProtocolSocket, d2DGatewayWatchdog, this.mGatewayControlProtocol, this.mPeriodicMetricReporter);
                        this.mSocketManager.connect(protocolSocket);
                    } catch (AlwaysOnSocketWatchdog.ProtocolSocketAcquisitionTimeout e) {
                        d2DGatewayWatchdog.release();
                        throw new SocketAcquisitionFailedException("Failed to acquire D2D gateway socket", e);
                    }
                }
            }
            FailFast.expectNotNull(protocolSocket);
            log.info("acquireSocket", "returning", "socket", protocolSocket);
            return protocolSocket;
        } else {
            throw new IllegalArgumentException("Argument: policy must not be null");
        }
    }
}
