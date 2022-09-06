package com.amazon.communication;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer;
import com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public interface AlwaysOnSocketWatchdog {

    /* loaded from: classes12.dex */
    public static class ProtocolSocketAcquisitionTimeout extends Exception {
        private static final long serialVersionUID = -815637354594716939L;

        public ProtocolSocketAcquisitionTimeout(String str) {
            super(str);
        }

        public ProtocolSocketAcquisitionTimeout(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes12.dex */
    public interface WatchdogStateListener {
        void onWatchdogClosed();
    }

    void addSocketStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener);

    void addWatchdogStateListener(WatchdogStateListener watchdogStateListener);

    ProtocolSocket getNonNullProtocolSocket(int i, TimeUnit timeUnit) throws ProtocolSocketAcquisitionTimeout;

    Policy getPolicy();

    ProtocolSocket getProtocolSocket();

    EndpointIdentity getWatchedEndpoint();

    String getWatchedUrl();

    void release();

    void removeSocketStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener);

    void removeWatchdogStateListener(WatchdogStateListener watchdogStateListener);

    int retain();

    void setHeartbeatIntervalDeterminer(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer);

    void setProbingConnectionLifetimeManager(ProbingConnectionLifetimeManager probingConnectionLifetimeManager);

    void shutdown();

    void startWatching(ConnectReason.ReasonString reasonString);

    void stopWatching();

    void updateWatchdogAndReconnect(Policy policy, EndpointIdentity endpointIdentity);
}
