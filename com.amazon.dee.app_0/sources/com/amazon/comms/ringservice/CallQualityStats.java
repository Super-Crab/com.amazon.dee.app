package com.amazon.comms.ringservice;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.Priority;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.sipclient.CallQualityMetrics;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.metrics.MetricsDestination;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.comms.ringservice.webrtc.StatsReportParser;
import com.amazon.comms.ringservice.webrtc.utils.WebRTCMediaStatsUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import org.webrtc.StatsReport;
/* loaded from: classes12.dex */
public class CallQualityStats {
    private static final CallQualityStats gInstance = new CallQualityStats();
    private static final CommsLogger log = CommsLogger.getLogger(CallQualityStats.class);
    private final Map<String, CallQualityMetrics> callQualityMap = GeneratedOutlineSupport1.outline136();

    private CallQualityStats() {
    }

    public static CallQualityStats getInstance() {
        return gInstance;
    }

    private void recordConnectionType(MetricsSession metricsSession, StatsReportParser statsReportParser, String str) {
        List<Map<String, String>> activeCandidates = statsReportParser.getActiveCandidates();
        for (Map<String, String> map : activeCandidates) {
            String localConnectionType = WebRTCMediaStatsUtils.getLocalConnectionType(map);
            String remoteConnectionType = WebRTCMediaStatsUtils.getRemoteConnectionType(map);
            MetricEvent createEvent = metricsSession.createEvent("PeerConnectionType");
            createEvent.addCounter(GeneratedOutlineSupport1.outline76("PeerConnType.", localConnectionType, "-to-", remoteConnectionType), 1.0d);
            createEvent.addString("CallId", str);
            metricsSession.recordEvent(createEvent);
        }
        metricsSession.recordCount("PeerConnectionClient", "ActivePeerConnections", activeCandidates.size(), str);
    }

    private void recordWebRTCStatsAsTrace(@Nonnull MetricsSession metricsSession, String str, StatsReportParser statsReportParser) {
        metricsSession.recordTrace("CallQualityStats", str, "WebRTCAllStats", "application/json", statsReportParser.getWebRTCStatsReportsWithOnlyActiveIce());
    }

    private HashMap<String, String> statsReportToHashMap(StatsReport statsReport) {
        StatsReport.Value[] valueArr;
        HashMap<String, String> hashMap = new HashMap<>();
        for (StatsReport.Value value : statsReport.values) {
            hashMap.put(value.name, value.value);
        }
        hashMap.put("id", statsReport.id);
        hashMap.put("type", statsReport.type);
        hashMap.put("timestamp", Double.toString(statsReport.timestamp));
        return hashMap;
    }

    public void clearCallQualityMetrics(String str) {
        if (str != null) {
            this.callQualityMap.remove(str);
        }
    }

    public CallQualityMetrics getCallQualityMetrics(String str) {
        return this.callQualityMap.get(str);
    }

    public boolean hasCallQualityMetrics(String str) {
        return this.callQualityMap.containsKey(str);
    }

    public void recordCallQualityMetricEvent(@Nonnull MetricsSession metricsSession, String str, String str2, StatsReportParser statsReportParser, boolean z) {
        CallQualityMetrics callQualityMetrics = statsReportParser.getCallQualityMetrics();
        if (callQualityMetrics == null) {
            callQualityMetrics = new CallQualityMetrics();
        }
        if (log.isLoggable(LogLevel.Debug)) {
            CommsLogger commsLogger = log;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("CallQualityStats.recordCallQualityMetricEvent( eventLabel=", str, ", callId=");
            outline115.append(log.sensitiveCallId(str2));
            outline115.append(", metrics=");
            outline115.append(callQualityMetrics.toString());
            outline115.append(")");
            commsLogger.d(outline115.toString());
        }
        CallQualityMetrics.VideoQualityMetrics video = callQualityMetrics.getVideo();
        CallQualityMetrics.BaseQualityMetrics audio = callQualityMetrics.getAudio();
        if (audio == null) {
            audio = new CallQualityMetrics.BaseQualityMetrics();
            callQualityMetrics.setAudio(audio);
        }
        CallQualityMetrics callQualityMetrics2 = getCallQualityMetrics(str2);
        boolean z2 = callQualityMetrics2 == null;
        if (z2) {
            callQualityMetrics2 = new CallQualityMetrics();
        }
        callQualityMetrics.setAudio(updateMaxBurstPacketLoss(callQualityMetrics2.getAudio(), audio));
        callQualityMetrics.setVideo((CallQualityMetrics.VideoQualityMetrics) updateMaxBurstPacketLoss(callQualityMetrics2.getVideo(), video));
        if (z) {
            recordConnectionType(metricsSession, statsReportParser, str2);
        }
        setCallQualityMetrics(str2, callQualityMetrics);
        long max = Math.max(video == null ? 0L : video.getCallRoundTripDelayUsec(), audio.getCallRoundTripDelayUsec()) / 1000;
        MetricEvent createEvent = metricsSession.createEvent(str);
        if (video != null) {
            createEvent.addCounter("VideoCallRxTotalPackets", video.getCallRxTotalPackets());
            createEvent.addCounter("VideoCallRxPacketLoss", video.getCallRxPacketLoss());
            createEvent.addCounter("VideoCallRxTotalBytes", video.getCallRxTotalBytes());
            createEvent.addTimer("VideoCallRxAvgJitterMs", video.getCallRxAvgJitterUsec() / 1000.0d);
            createEvent.addCounter("VideoCallTxTotalPackets", video.getCallTxTotalPackets());
            createEvent.addCounter("VideoCallTxTotalBytes", video.getCallTxTotalBytes());
            createEvent.addCounter("VideoCallTxPacketLoss", video.getCallTxPacketLoss());
            createEvent.addTimer("VideoCallTxAvgJitterMs", video.getCallTxAvgJitterUsec() / 1000.0d);
            createEvent.addTimer("VideoCallRxAvSyncAvgDelayMs", video.getCallRxAvSyncAvgDelayMs());
            createEvent.addTimer("VideoCallRxAvSyncAvgRelativeDelayMs", video.getCallRxAvSyncAvgRelativeDelayMs());
            createEvent.addCounter("VideoCallRxFrameWidth", video.getCallRxFrameWidth());
            createEvent.addCounter("VideoCallRxFrameHeight", video.getCallRxFrameHeight());
            createEvent.addCounter("VideoCallRxFrameRate", video.getCallRxFrameRate());
            createEvent.addCounter("VideoCallTxFrameWidth", video.getCallTxFrameWidth());
            createEvent.addCounter("VideoCallTxFrameHeight", video.getCallTxFrameHeight());
            createEvent.addCounter("VideoCallTxFrameRate", video.getCallTxFrameRate());
            createEvent.addCounter("VideoCallTargetEncBitrate", video.getCallTargetEncBitrate());
            createEvent.addCounter("VideoCallActualEncBitrate", video.getCallActualEncBitrate());
            createEvent.addCounter("VideoCallTransmitBitrate", video.getCallTransmitBitrate());
            createEvent.addCounter("VideoCallRetransmitBitrate", video.getCallRetransmitBitrate());
            createEvent.addCounter("VideoCallBucketDelay", video.getCallBucketDelay());
            createEvent.addCounter("VideoCallAvailableReceiveBandwidth", video.getCallAvailableReceiveBandwidth());
            createEvent.addCounter("VideoCallAvailableSendBandwidth", video.getCallAvailableSendBandwidth());
            createEvent.addCounter("VideoCallTxFrameRateInput", video.getCallTxFrameRateInput());
            createEvent.addCounter("VideoCallTxCpuLimited", video.getCallTxCpuLimited().booleanValue() ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
            createEvent.addCounter("VideoCallTxBandwidthLimited", video.getCallTxBandwidthLimited().booleanValue() ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
            createEvent.addCounter("VideoCallRxNacks", video.getCallRxNacks());
            createEvent.addCounter("VideoCallRxFirs", video.getCallRxFirs());
            createEvent.addCounter("VideoCallRxPlis", video.getCallRxPlis());
            createEvent.addCounter("VideoCallTxNacks", video.getCallTxNacks());
            createEvent.addCounter("VideoCallTxFirs", video.getCallTxFirs());
            createEvent.addCounter("VideoCallTxPlis", video.getCallTxPlis());
            createEvent.addCounter("VideoCallAdaptationChanges", video.getCallAdaptationChanges());
            createEvent.addTimer("VideoCallRxEncodedVideoToFrameDelayMs", video.getCallRxEncodedVideoToFrameDelayMs());
            createEvent.addTimer("VideoCallTxVideoFrameToPacketDelayMs", video.getCallTxVideoFrameToPacketDelayMs());
            createEvent.addTimer("VideoCallRxEndToEndVideoLatencyMs", video.getCallRxEndToEndVideoLatencyMs());
            createEvent.addCounter("VideoCallRxQpAvg", video.getCallRxQpAvg());
            createEvent.addCounter("VideoCallTxQpAvg", video.getCallTxQpAvg());
        }
        createEvent.addCounter("AudioCallRxTotalPackets", audio.getCallRxTotalPackets());
        createEvent.addCounter("AudioCallRxPacketLoss", audio.getCallRxPacketLoss());
        createEvent.addCounter("AudioCallRxTotalBytes", audio.getCallRxTotalBytes());
        createEvent.addTimer("AudioCallRxAvgJitterMs", audio.getCallRxAvgJitterUsec() / 1000.0d);
        createEvent.addCounter("AudioCallTxTotalPackets", audio.getCallTxTotalPackets());
        createEvent.addCounter("AudioCallTxPacketLoss", audio.getCallTxPacketLoss());
        createEvent.addCounter("AudioCallTxTotalBytes", audio.getCallTxTotalBytes());
        createEvent.addCounter("AudioCallRxAudioOutputLevel", audio.getCallRxAudioOutputLevel());
        createEvent.addCounter("AudioCallTxAudioInputLevel", audio.getCallTxAudioInputLevel());
        createEvent.addTimer("AudioCallTxAvgJitterMs", audio.getCallTxAvgJitterUsec() / 1000.0d);
        createEvent.addTimer("AudioCallRxEncodedAudioToFrameDelayMs", audio.getCallRxEncodedAudioToFrameDelayMs());
        createEvent.addTimer("AudioCallTxAudioFrameToPacketDelayMs", audio.getCallTxAudioFrameToPacketDelayMs());
        createEvent.addTimer("AudioCallTxAudioEncodePreprocessingDelayUs", audio.getCallTxAudioEncodePreprocessingDelayUsec() / 1000.0d);
        createEvent.addTimer("AudioCallTxAudioEncodeDelayUs", audio.getCallTxAudioEncodeDelayUsec() / 1000.0d);
        createEvent.addTimer("AudioCallTxAudioPacketizationDelayUs", audio.getCallTxAudioPacketizationDelayUsec() / 1000.0d);
        createEvent.addTimer("AudioCallRxAudioPacketBufferMs", audio.getCallRxAudioPacketBufferMs());
        createEvent.addTimer("AudioCallRxAudioPlayoutBufferMs", audio.getCallRxAudioPlayoutBufferMs());
        createEvent.addTimer("AudioCallRxAudioDepacketizationDelayUsec", audio.getCallRxAudioDepacketizationDelayUsec() / 1000.0d);
        createEvent.addTimer("AudioCallRxAudioDecodeDelayUs", audio.getCallRxAudioDecodeDelayUsec() / 1000.0d);
        if (max > 0 && !z2) {
            createEvent.addTimer("CallRoundTripDelayMs", max);
        }
        createEvent.addString("CallId", str2);
        metricsSession.recordEvent(createEvent, MetricsDestination.ALL, Priority.NORMAL);
    }

    public MediaStats recordPeerConnectionStats(@Nonnull MetricsSession metricsSession, StatsReport[] statsReportArr, String str, boolean z, boolean z2) {
        StatsReportParser createStatsReportParser = StatsReportParser.createStatsReportParser(statsReportArr, z2);
        recordCallQualityMetricEvent(metricsSession, "PeerConnectionStats", str, createStatsReportParser, z);
        recordWebRTCStatsAsTrace(metricsSession, str, createStatsReportParser);
        return createStatsReportParser.getMediaStats();
    }

    public void setCallQualityMetrics(String str, CallQualityMetrics callQualityMetrics) {
        if (callQualityMetrics == null || str == null) {
            return;
        }
        this.callQualityMap.put(str, callQualityMetrics);
    }

    @VisibleForTesting
    CallQualityMetrics.BaseQualityMetrics updateMaxBurstPacketLoss(CallQualityMetrics.BaseQualityMetrics baseQualityMetrics, CallQualityMetrics.BaseQualityMetrics baseQualityMetrics2) {
        if (baseQualityMetrics2 == null) {
            return baseQualityMetrics;
        }
        if (baseQualityMetrics == null) {
            baseQualityMetrics = new CallQualityMetrics.BaseQualityMetrics();
        }
        long callRxPacketLoss = baseQualityMetrics2.getCallRxPacketLoss() - baseQualityMetrics.getCallRxPacketLoss();
        long callTxPacketLoss = baseQualityMetrics2.getCallTxPacketLoss() - baseQualityMetrics.getCallTxPacketLoss();
        long max = Math.max(callRxPacketLoss, baseQualityMetrics.getCallRxMaxBurstPacketLoss());
        long max2 = Math.max(callTxPacketLoss, baseQualityMetrics.getCallTxMaxBurstPacketLoss());
        baseQualityMetrics2.setCallRxMaxBurstPacketLoss(max);
        baseQualityMetrics2.setCallTxMaxBurstPacketLoss(max2);
        return baseQualityMetrics2;
    }
}
