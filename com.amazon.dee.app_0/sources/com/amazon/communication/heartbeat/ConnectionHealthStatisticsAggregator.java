package com.amazon.communication.heartbeat;
/* loaded from: classes12.dex */
public interface ConnectionHealthStatisticsAggregator {
    void onHealthyConnection(long j);

    void onUnhealthyConnection(long j);
}
