package com.amazon.communication.socket;

import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface SocketUsageAggregatedReader {
    int countClosedSocketsToEndpoint(EndpointIdentity endpointIdentity);

    int countOpenedSocketsToEndpoint(EndpointIdentity endpointIdentity);
}
