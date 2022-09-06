package com.amazon.communication.heartbeat;
/* loaded from: classes12.dex */
public interface HeartbeatIntervalConfidenceComputer {
    double getConfidence(long j);

    void reportInterval(long j);
}
