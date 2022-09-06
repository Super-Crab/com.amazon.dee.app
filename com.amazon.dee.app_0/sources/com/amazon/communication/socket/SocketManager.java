package com.amazon.communication.socket;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public interface SocketManager {
    void connect(ProtocolSocket protocolSocket) throws SocketAcquisitionFailedException;

    ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, Purpose purpose, ConnectReason connectReason, String str);

    ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, ConnectReason connectReason, String str);

    List<ProtocolSocket> getActiveProtocolSockets();

    List<ProtocolSocket> getExistingProtocolSockets(UniqueEndpointIdentifier uniqueEndpointIdentifier, Set<ProtocolSocket.ProtocolSocketAttribute> set) throws EndpointNotAuthenticatedException;
}
