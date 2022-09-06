package com.amazon.communication;

import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
/* loaded from: classes12.dex */
public interface SocketDecisionEngine {
    public static final Purpose HEARTBEAT_PROBING = new Purpose("HeartbeatProbing");

    ProtocolSocket acquireSocket(EndpointIdentity endpointIdentity, IRServiceEndpoint iRServiceEndpoint, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException;
}
