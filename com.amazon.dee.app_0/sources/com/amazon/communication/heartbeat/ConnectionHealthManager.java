package com.amazon.communication.heartbeat;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.socket.ProtocolSocket;
/* loaded from: classes12.dex */
public interface ConnectionHealthManager {
    boolean closeIfStale(ProtocolSocket protocolSocket);

    void maintainConnection(EndpointIdentity endpointIdentity, HeartbeatIntervalDeterminer heartbeatIntervalDeterminer, ConnectionHealthStatisticsAggregator connectionHealthStatisticsAggregator);

    void pause(EndpointIdentity endpointIdentity);

    void resume(EndpointIdentity endpointIdentity);

    void shutdown();

    void stop(EndpointIdentity endpointIdentity);
}
