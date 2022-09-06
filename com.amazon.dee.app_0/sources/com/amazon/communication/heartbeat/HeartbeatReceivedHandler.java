package com.amazon.communication.heartbeat;

import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface HeartbeatReceivedHandler {
    void onHeartbeatReceived(EndpointIdentity endpointIdentity, int i);
}
