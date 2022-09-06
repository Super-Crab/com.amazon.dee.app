package com.amazon.communication.heartbeat;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ConservativeHeartbeatIntervalDeterminer extends HeartbeatIntervalDeterminerBase {
    private static final long LOWEST_POSSIBLE_HEARTBEAT_INTERVAL_MILLIS = 10000;
    private final HeartbeatIntervalDeterminer mDelegate;

    public ConservativeHeartbeatIntervalDeterminer(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        this.mDelegate = heartbeatIntervalDeterminer;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getLastKnownGoodHeartbeatIntervalMillis() {
        return Math.max(this.mDelegate.getLastKnownGoodHeartbeatIntervalMillis() - HeartbeatSettings.getLongValue(HeartbeatSettings.CONSERVATIVE_INTERVAL_MILLIS_KEY).longValue(), 10000L);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMaximumHeartbeatIntervalMillis() {
        return getLastKnownGoodHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMinimumHeartbeatIntervalMillis() {
        return Math.max(getMaximumHeartbeatIntervalMillis() - HeartbeatSettings.getLongValue(HeartbeatSettings.HEARTBEAT_INTERVAL_RANGE_MILLIS_KEY).longValue(), 10000L);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConservativeHID: lastKnownGoodIntervalMillis: ");
        outline107.append(getLastKnownGoodHeartbeatIntervalMillis());
        return outline107.toString();
    }
}
