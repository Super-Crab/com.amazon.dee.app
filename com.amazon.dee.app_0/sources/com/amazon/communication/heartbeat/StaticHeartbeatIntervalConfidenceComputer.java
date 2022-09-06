package com.amazon.communication.heartbeat;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dp.logger.DPLogger;
import com.magiear.handsfree.util.Common;
/* loaded from: classes12.dex */
public class StaticHeartbeatIntervalConfidenceComputer implements HeartbeatIntervalConfidenceComputer {
    private static final DPLogger log = new DPLogger("TComm.StaticHeartbeatConfidenceComputer");
    private final long mIdealHeartbeatIntervalMillis;
    private final long mIntervalRangeSquare;
    private final long mLowestHeartbeatIntervalMillis;

    public StaticHeartbeatIntervalConfidenceComputer(long j, long j2) {
        log.info("StaticHeartbeatIntervalConfidenceComputer", "constructor", "lowestHeartbeatIntervalMillis", Long.valueOf(j), "idealHeartbeatIntervalMillis", Long.valueOf(j2));
        this.mLowestHeartbeatIntervalMillis = j;
        this.mIdealHeartbeatIntervalMillis = j2;
        long j3 = this.mIdealHeartbeatIntervalMillis - this.mLowestHeartbeatIntervalMillis;
        this.mIntervalRangeSquare = j3 * j3;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalConfidenceComputer
    public double getConfidence(long j) {
        double d = this.mIdealHeartbeatIntervalMillis - j;
        double max = Math.max(1.0d - ((d * d) / this.mIntervalRangeSquare), (double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        log.debug("getConfidence", "computed confidence score for given heartbeat interval", "intervalMillis", Long.valueOf(j), "mIdealHeartbeatIntervalMillis", Long.valueOf(this.mIdealHeartbeatIntervalMillis), "diff", Double.valueOf(d), Common.EXTRA_CONFIDENCE, Double.valueOf(max));
        return max;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalConfidenceComputer
    public void reportInterval(long j) {
    }
}
