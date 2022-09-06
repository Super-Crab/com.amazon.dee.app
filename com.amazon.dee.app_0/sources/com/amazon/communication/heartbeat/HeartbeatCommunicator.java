package com.amazon.communication.heartbeat;

import com.amazon.communication.socket.ProtocolSocket;
/* loaded from: classes12.dex */
public interface HeartbeatCommunicator {
    void registerHeartbeatReceivedHandler(HeartbeatReceivedHandler heartbeatReceivedHandler);

    boolean sendHeartbeat(ProtocolSocket protocolSocket);

    void shutdown();
}
