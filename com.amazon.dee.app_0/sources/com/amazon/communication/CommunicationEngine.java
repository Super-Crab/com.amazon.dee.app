package com.amazon.communication;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
/* loaded from: classes12.dex */
public interface CommunicationEngine {
    ProtocolSocket acquireProtocolSocket(EndpointIdentity endpointIdentity, Policy policy, ConnectReason connectReason, String str) throws SocketAcquisitionFailedException;

    void shutdown();
}
