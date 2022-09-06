package com.amazon.communication.socket;

import com.amazon.communication.identity.UniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public interface SocketCollection {
    void addSocket(UniqueEndpointIdentifier uniqueEndpointIdentifier, ProtocolSocket protocolSocket);

    void clear();

    List<ProtocolSocket> getAllSockets();

    List<ProtocolSocket> getSocketsForEndpoint(UniqueEndpointIdentifier uniqueEndpointIdentifier, Set<ProtocolSocket.ProtocolSocketAttribute> set) throws EndpointNotAuthenticatedException;

    void removeSocket(ProtocolSocket protocolSocket);
}
