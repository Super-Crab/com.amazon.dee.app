package com.amazon.communication.heartbeat;
/* loaded from: classes12.dex */
public interface HeartbeatNotificationHandler {

    /* loaded from: classes12.dex */
    public enum HeartbeatNotificationAttribute {
        INIT,
        DEFAULT,
        ROAMING_ACTIVE,
        NO_CONNECTION_POSSIBLE
    }

    void onHeartbeatNotification(HeartbeatNotificationAttribute heartbeatNotificationAttribute, int i);
}
