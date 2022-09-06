package com.amazon.communication.heartbeat;

import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class StaticHeartbeatIntervalDeterminer extends HeartbeatIntervalDeterminerBase implements ConnectionHealthStatisticsAggregator {
    public static final long MAX_INTERVAL_MS = 180000;
    public static final long MIN_INTERVAL_MS = 180000;

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getLastKnownGoodHeartbeatIntervalMillis() {
        return 180000L;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMaximumHeartbeatIntervalMillis() {
        FailFast.expectTrue(true);
        return 180000L;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMinimumHeartbeatIntervalMillis() {
        FailFast.expectTrue(true);
        return 180000L;
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onHealthyConnection(long j) {
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onUnhealthyConnection(long j) {
    }
}
