package com.amazon.communication.socket.decisionengine;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import android.net.NetworkInfo;
import com.amazon.communication.ConnectivityManagerWrapper;
import com.amazon.communication.GatewayConnectionService;
import com.amazon.communication.SocketDecisionEngineBase;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.identity.ServiceUniqueEndpointIdentifier;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.EndpointNotAuthenticatedException;
import com.amazon.communication.socket.GatewaySocket;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.amazon.communication.socket.SocketUsageAggregatedReader;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class ServiceIdentitySocketDecisionEngine extends EndpointIdentitySocketDecisionEngine {
    public static final int MAXIMUM_SOCKETS_CLOSED_TO_ENDPOINT = 10;
    private static final DPLogger log = new DPLogger("TComm.ServiceIdentitySocketDecisionEngine");
    private final Object mDecisionLock;
    protected final GatewayControlProtocol mGatewayControlProtocol;
    protected final GatewayApplicationProtocol mGatewayProtocol;
    protected final IdentityResolver mIdentityResolver;
    protected final SocketManager mSocketManager;
    protected final SocketUsageAggregatedReader mSocketUsageAggregatedReader;
    protected final WorkExecutor mWorkExecutor;

    public ServiceIdentitySocketDecisionEngine(ConnectivityManagerWrapper connectivityManagerWrapper, GatewayApplicationProtocol gatewayApplicationProtocol, GatewayControlProtocol gatewayControlProtocol, SocketManager socketManager, SocketUsageAggregatedReader socketUsageAggregatedReader, IdentityResolver identityResolver, WorkExecutor workExecutor) {
        super(connectivityManagerWrapper);
        this.mDecisionLock = new Object();
        this.mGatewayProtocol = gatewayApplicationProtocol;
        this.mGatewayControlProtocol = gatewayControlProtocol;
        this.mSocketManager = socketManager;
        this.mSocketUsageAggregatedReader = socketUsageAggregatedReader;
        this.mIdentityResolver = identityResolver;
        this.mWorkExecutor = workExecutor;
    }

    private ProtocolSocket createBiDirectionalSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException {
        log.verbose("createBiDirectionalSocket", "Attempting to create a new bidi socket", new Object[0]);
        Set<ProtocolSocket.ProtocolSocketAttribute> fillInAttributesFromConnectionPolicy = fillInAttributesFromConnectionPolicy(policy, iRServiceEndpoint, EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.REUSABLE));
        ProtocolSocket createProtocolSocket = this.mSocketManager.createProtocolSocket(endpointIdentity, fillInAttributesFromConnectionPolicy, policy.getPurpose(), connectReason, str);
        log.verbose("createBiDirectionalSocket", "Initiating connection through connection manager", "endpoint", EndpointIdentity.logSafe(endpointIdentity), "requiredAttributes", fillInAttributesFromConnectionPolicy, "policy.getPurpose()", policy.getPurpose());
        this.mSocketManager.connect(createProtocolSocket);
        return createProtocolSocket;
    }

    private ProtocolSocket createRawHttpSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, String str) throws SocketAcquisitionFailedException {
        log.verbose("createRawHttpSocket", "Attempting to create a new RawHttp socket", new Object[0]);
        Set<ProtocolSocket.ProtocolSocketAttribute> fillInAttributesFromConnectionPolicy = fillInAttributesFromConnectionPolicy(policy, iRServiceEndpoint, EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.REQUEST_RESPONSE_ONLY));
        ProtocolSocket createProtocolSocket = this.mSocketManager.createProtocolSocket(endpointIdentity, fillInAttributesFromConnectionPolicy, policy.getPurpose(), new ConnectReason(ConnectReason.ReasonString.ClientInitiated, 1), str);
        log.verbose("createRawHttpSocket", "Initiating connection through connection manager", "endpoint", EndpointIdentity.logSafe(endpointIdentity), "requiredAttributes", fillInAttributesFromConnectionPolicy, "policy", policy);
        this.mSocketManager.connect(createProtocolSocket);
        return createProtocolSocket;
    }

    private boolean doesDirectBiDiSocketMeetRequirements(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        FailFast.expectNotNull(policy);
        return isDirectConnectionAllowed(iRServiceEndpoint);
    }

    private boolean doesRawHttpSocketMeetRequirements(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        FailFast.expectNotNull(policy);
        return policy.isRequestResponseOnly() && isDirectConnectionAllowed(iRServiceEndpoint);
    }

    private boolean doesRawHttpSocketMeetRequirementsGivenHistory(EndpointIdentity endpointIdentity) {
        return this.mSocketUsageAggregatedReader.countClosedSocketsToEndpoint(endpointIdentity) < 10;
    }

    private boolean doesTheGatewaySocketMeetRequirements(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        FailFast.expectNotNull(policy);
        ProtocolSocket theGatewaySocket = getTheGatewaySocket();
        if (theGatewaySocket == null) {
            return false;
        }
        return (theGatewaySocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.SECURE) || !SocketDecisionEngineBase.isSecureConnectionRequired(iRServiceEndpoint, policy)) && (theGatewaySocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.COMPRESSED) || !SocketDecisionEngineBase.isCompressedConnectionRequired(iRServiceEndpoint, policy)) && (iRServiceEndpoint == null || iRServiceEndpoint.getDirectConnection() != IRServiceEndpoint.DirectConnection.REQUIRED) && (policy.isLowLatencyNecessary() ^ true);
    }

    private boolean doesWiFiAvailabilityMeetRequirements(Policy policy) {
        return !policy.isWiFiNecessary() || (this.mConnectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED);
    }

    private List<ProtocolSocket> getActiveNonDedicatedSockets(List<ProtocolSocket> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ProtocolSocket protocolSocket = list.get(i);
                if ((protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED || protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTING) && !protocolSocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.DEDICATED)) {
                    arrayList.add(protocolSocket);
                }
            }
        }
        return arrayList;
    }

    private ProtocolSocket getTheGatewaySocket() throws IllegalStateException {
        IRServiceEndpoint resolveServiceEndpoint = this.mIdentityResolver.resolveServiceEndpoint(GatewayConnectionService.GATEWAY_ENDPOINT);
        if (resolveServiceEndpoint == null) {
            log.verbose("getTheGatwaySocket", "No IR mapping for the Gateway exists", "GATEWAY_ENDPOINT", GatewayConnectionService.GATEWAY_ENDPOINT);
            return null;
        }
        ServiceUniqueEndpointIdentifier serviceUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(resolveServiceEndpoint);
        log.debug("getTheGatewaySocket", "checking for Gateway socket", "gwUniqueEndpointIdentifier", serviceUniqueEndpointIdentifier);
        List<ProtocolSocket> emptyList = Collections.emptyList();
        try {
            emptyList = this.mSocketManager.getExistingProtocolSockets(serviceUniqueEndpointIdentifier, EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.REUSABLE));
        } catch (EndpointNotAuthenticatedException e) {
            log.warn("getTheGatewaySocket", "not authenticated for Gateway socket", e);
        }
        List<ProtocolSocket> activeNonDedicatedSockets = getActiveNonDedicatedSockets(emptyList);
        int size = activeNonDedicatedSockets.size();
        if (size == 0) {
            log.verbose("getTheGatewaySocket", "No connection to gateway exists", new Object[0]);
            return null;
        } else if (size <= 1) {
            return activeNonDedicatedSockets.get(0);
        } else {
            log.error("getTheGatewaySocket", "Expecting at most 1 regular-purpose socket to gateway", "socketListSize", Integer.valueOf(size));
            throw new IllegalStateException(size + " regular-purpose sockets exist to GatewayService. Expecting at most 1");
        }
    }

    private boolean isDirectConnectionAllowed(IRServiceEndpoint iRServiceEndpoint) {
        return iRServiceEndpoint == null || iRServiceEndpoint.getDirectConnection() != IRServiceEndpoint.DirectConnection.NOT_ALLOWED;
    }

    private ProtocolSocket useGatewaySocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        log.verbose("useGatewaySocket", "Getting gateway socket", "endpoint", EndpointIdentity.logSafe(endpointIdentity), "irEndpoint", iRServiceEndpoint);
        ProtocolSocket theGatewaySocket = getTheGatewaySocket();
        if (theGatewaySocket != null) {
            ProtocolSocket.ProtocolSocketState socketState = theGatewaySocket.socketState();
            if (socketState == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                return new GatewaySocket(endpointIdentity, this.mGatewayProtocol, theGatewaySocket);
            }
            log.verbose("useGatewaySocket", "Current gateway socket is not yet connected", "state", socketState);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cf, code lost:
        if (doesRawHttpSocketMeetRequirements(r12, r13) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d1, code lost:
        com.amazon.communication.socket.decisionengine.ServiceIdentitySocketDecisionEngine.log.verbose("acquireSocket", "Attempting to create a new raw HTTP socket", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
        if (doesRawHttpSocketMeetRequirementsGivenHistory(r11) == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e2, code lost:
        r0 = createRawHttpSocket(r11, r12, r13, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e7, code lost:
        com.amazon.communication.socket.decisionengine.ServiceIdentitySocketDecisionEngine.log.verbose("acquireSocket", "Will not create RawHttpSocket because a large number of sockets has been closed", new java.lang.Object[0]);
     */
    @Override // com.amazon.communication.SocketDecisionEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.communication.socket.ProtocolSocket acquireSocket(amazon.communication.identity.EndpointIdentity r11, amazon.communication.identity.IRServiceEndpoint r12, amazon.communication.connection.Policy r13, com.amazon.communication.socket.ConnectReason r14, java.lang.String r15) throws com.amazon.communication.socket.SocketAcquisitionFailedException {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.socket.decisionengine.ServiceIdentitySocketDecisionEngine.acquireSocket(amazon.communication.identity.EndpointIdentity, amazon.communication.identity.IRServiceEndpoint, amazon.communication.connection.Policy, com.amazon.communication.socket.ConnectReason, java.lang.String):com.amazon.communication.socket.ProtocolSocket");
    }
}
