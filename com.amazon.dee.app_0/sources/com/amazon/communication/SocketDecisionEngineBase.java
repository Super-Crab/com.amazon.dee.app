package com.amazon.communication;

import amazon.communication.connection.CompressionOption;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.communication.identity.DeviceUniqueEndpointIdentifier;
import com.amazon.communication.identity.ServiceUniqueEndpointIdentifier;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public abstract class SocketDecisionEngineBase implements SocketDecisionEngine {
    private static final DPLogger log = new DPLogger("TComm.SocketDecisionEngineBase");
    protected static final Set<ProtocolSocket.ProtocolSocketAttribute> MANDATORY_ATTRIBUTES_TO_BE_REUSEABLE = EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.REUSABLE);

    private static boolean doesSocketMatchPolicy(ProtocolSocket protocolSocket, Policy policy) {
        if (protocolSocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.REQUEST_RESPONSE_ONLY) && !policy.isRequestResponseOnly()) {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of REQUEST_RESPONSE_ONLY mismatch", new Object[0]);
            return false;
        } else if (protocolSocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.COMPRESSED) && policy.getCompressionOption().equals(CompressionOption.NOT_ALLOWED)) {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of COMPRESSED mismatch", new Object[0]);
            return false;
        } else if (protocolSocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS) && !policy.isAnonymousCredentialsAllowed()) {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of ANONYMOUS mismatch", new Object[0]);
            return false;
        } else if (policy.isDedicated() != protocolSocket.isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.DEDICATED)) {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of DEDICATED mismatch", new Object[0]);
            return false;
        } else if (policy.isDedicated() && !policy.getPurpose().equals(protocolSocket.getPurpose())) {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of purpose mismatch", new Object[0]);
            return false;
        } else if (!Purpose.D2D_MESSAGING.equals(policy.getPurpose()) || Purpose.D2D_MESSAGING.equals(protocolSocket.getPurpose())) {
            return true;
        } else {
            log.debug("doesSocketMatchPolicy", "Skipping this socket because of D2D_MESSAGING purpose mismatch", "policy", policy, "socket", protocolSocket);
            return false;
        }
    }

    protected static ProtocolSocket findProtocolSocketThatMatchesPolicy(List<ProtocolSocket> list, Policy policy) {
        log.debug("findProtocolSocketThatMachesPolicy", "got socket list", "socketSize", Integer.valueOf(list.size()));
        for (ProtocolSocket protocolSocket : list) {
            if (protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED || protocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTING) {
                Purpose purpose = protocolSocket.getPurpose();
                if (purpose == null) {
                    log.warn("findProtocolSocketThatMachesPolicy", "socket has null purpose", new Object[0]);
                }
                if (policy != null) {
                    if (doesSocketMatchPolicy(protocolSocket, policy)) {
                        return protocolSocket;
                    }
                } else if (!SocketDecisionEngine.HEARTBEAT_PROBING.equals(purpose)) {
                    return protocolSocket;
                } else {
                    log.debug("findProtocolSocketThatMachesPolicy", "discarding heartbeat probing socket", new Object[0]);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isCompressedConnectionRequired(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        return policy.getCompressionOption().equals(CompressionOption.REQUIRED) || (policy.getCompressionOption().equals(CompressionOption.ALLOWED) && isCompressionRequiredByServiceEndpoint(iRServiceEndpoint));
    }

    private static boolean isCompressionRequiredByServiceEndpoint(IRServiceEndpoint iRServiceEndpoint) {
        boolean z = true;
        if (iRServiceEndpoint != null) {
            if (iRServiceEndpoint.getDataCompression() == IRServiceEndpoint.DataCompression.NEEDED) {
                return true;
            }
            if (iRServiceEndpoint.getDataCompression() != IRServiceEndpoint.DataCompression.NOT_NEEDED) {
                z = false;
            }
            FailFast.expectTrue(z);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isSecureConnectionRequired(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        return isSecureRequiredByServiceEndpoint(iRServiceEndpoint) || !policy.isClearText();
    }

    private static boolean isSecureRequiredByServiceEndpoint(IRServiceEndpoint iRServiceEndpoint) {
        boolean z = true;
        if (iRServiceEndpoint != null) {
            if (iRServiceEndpoint.getClearTextConnection() == IRServiceEndpoint.ClearTextConnection.NOT_ALLOWED) {
                return true;
            }
            if (iRServiceEndpoint.getClearTextConnection() != IRServiceEndpoint.ClearTextConnection.ALLOWED) {
                z = false;
            }
            FailFast.expectTrue(z);
        }
        return false;
    }

    public Set<ProtocolSocket.ProtocolSocketAttribute> fillInAttributesFromConnectionPolicy(Policy policy, IRServiceEndpoint iRServiceEndpoint, Set<ProtocolSocket.ProtocolSocketAttribute> set) {
        EnumSet copyOf = EnumSet.copyOf((Collection) set);
        if (isSecureConnectionRequired(iRServiceEndpoint, policy)) {
            copyOf.add(ProtocolSocket.ProtocolSocketAttribute.SECURE);
        }
        if (isCompressedConnectionRequired(iRServiceEndpoint, policy)) {
            copyOf.add(ProtocolSocket.ProtocolSocketAttribute.COMPRESSED);
        }
        if (policy.isAnonymousCredentialsAllowed()) {
            copyOf.add(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS);
        }
        if (policy.isDedicated()) {
            copyOf.add(ProtocolSocket.ProtocolSocketAttribute.DEDICATED);
        }
        return copyOf;
    }

    public ProtocolSocket getValidExistingProtocolSocket(SocketManager socketManager, EndpointIdentity endpointIdentity, Policy policy, IRServiceEndpoint iRServiceEndpoint) throws SocketAcquisitionFailedException {
        Set<ProtocolSocket.ProtocolSocketAttribute> set;
        UniqueEndpointIdentifier serviceUniqueEndpointIdentifier;
        log.verbose("getValidExistingProtocolSocket", "getting existing connected or connecting socket", "socketManager", socketManager, "endpoint", EndpointIdentity.logSafe(endpointIdentity), "policy", policy, "irEndpoint", iRServiceEndpoint);
        if (policy != null) {
            set = fillInAttributesFromConnectionPolicy(policy, iRServiceEndpoint, MANDATORY_ATTRIBUTES_TO_BE_REUSEABLE);
            set.remove(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS);
        } else {
            set = MANDATORY_ATTRIBUTES_TO_BE_REUSEABLE;
        }
        if (endpointIdentity instanceof DeviceIdentity) {
            serviceUniqueEndpointIdentifier = new DeviceUniqueEndpointIdentifier((DeviceIdentity) endpointIdentity);
        } else if (iRServiceEndpoint == null) {
            serviceUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(endpointIdentity);
        } else {
            serviceUniqueEndpointIdentifier = new ServiceUniqueEndpointIdentifier(iRServiceEndpoint);
        }
        log.debug("getValidExistingProtocolSocket", "getting", "uniqueEndpointIdentifier", serviceUniqueEndpointIdentifier);
        ProtocolSocket findProtocolSocketThatMatchesPolicy = findProtocolSocketThatMatchesPolicy(socketManager.getExistingProtocolSockets(serviceUniqueEndpointIdentifier, set), policy);
        if (findProtocolSocketThatMatchesPolicy != null) {
            log.verbose("getValidExistingProtocolSocket", "got existing socket", "socket", findProtocolSocketThatMatchesPolicy);
        } else {
            log.verbose("getValidExistingProtocolSocket", "no valid socket exists", new Object[0]);
        }
        return findProtocolSocketThatMatchesPolicy;
    }
}
