package com.amazon.communication.heartbeat;
/* loaded from: classes12.dex */
public interface HeartbeatNotificationScheduler {
    void cancelScheduledNotification();

    void cancelScheduledNotification(int i);

    boolean scheduleHeartbeatNotification();

    boolean scheduleHeartbeatNotification(int i, long j, long j2);
}
