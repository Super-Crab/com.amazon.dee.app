package com.amazon.communication.heartbeat;
/* loaded from: classes12.dex */
public interface HeartbeatIntervalDeterminer {
    void addHeartbeatIntervalUpdatesListener(HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener);

    void forceLearningMode(TriggerLearningCommand triggerLearningCommand);

    long getLastKnownGoodHeartbeatIntervalMillis();

    long getMaximumHeartbeatIntervalMillis();

    long getMinimumHeartbeatIntervalMillis();

    boolean hasLearntHeartbeatInterval();

    void shutdown();
}
