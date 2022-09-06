package com.amazon.communication.heartbeat;

import com.amazon.communication.NetworkType;
/* loaded from: classes12.dex */
public interface HeartbeatIntervalUpdatesListener {

    /* loaded from: classes12.dex */
    public enum SwitchingReason {
        INTERVAL_VALIDITY_EXPIRED,
        CONSECUTIVE_FAILURE,
        REMOTE_SETTINGS_DRASTIC_CHANGE,
        FORCED_LEARNING_MODE,
        EXPECTED_LEARNING_ATTEMPTS_REACHED,
        INTERVAL_STABILIZED
    }

    void changedHeartbeatInterval(NetworkType networkType, long j, long j2);

    void switchedToLearningMode(NetworkType networkType, SwitchingReason switchingReason);

    void switchedToLearntMode(NetworkType networkType, SwitchingReason switchingReason, long j);
}
