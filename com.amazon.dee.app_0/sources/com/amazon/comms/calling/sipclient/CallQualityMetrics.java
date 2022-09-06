package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class CallQualityMetrics {
    BaseQualityMetrics audio;
    CodecData codecData;
    VideoQualityMetrics video;

    /* loaded from: classes11.dex */
    public static class BaseQualityMetrics {
        long callRoundTripDelayUsec;
        long callRxAudioDecodeDelayUsec;
        long callRxAudioDepacketizationDelayUsec;
        long callRxAudioOutputLevel;
        long callRxAudioPacketBufferMs;
        long callRxAudioPlayoutBufferMs;
        long callRxAvgJitterUsec;
        long callRxEncodedAudioToFrameDelayMs;
        long callRxJitterBufferMs;
        long callRxMaxBurstPacketLoss;
        long callRxPacketLoss;
        long callRxTotalBytes;
        long callRxTotalPackets;
        long callTxAudioEncodeDelayUsec;
        long callTxAudioEncodePreprocessingDelayUsec;
        long callTxAudioFrameToPacketDelayMs;
        long callTxAudioInputLevel;
        long callTxAudioPacketizationDelayUsec;
        long callTxAvgJitterUsec;
        long callTxMaxBurstPacketLoss;
        long callTxPacketLoss;
        long callTxTotalBytes;
        long callTxTotalPackets;

        public long getCallRoundTripDelayUsec() {
            return this.callRoundTripDelayUsec;
        }

        public long getCallRxAudioDecodeDelayUsec() {
            return this.callRxAudioDecodeDelayUsec;
        }

        public long getCallRxAudioDepacketizationDelayUsec() {
            return this.callRxAudioDepacketizationDelayUsec;
        }

        public long getCallRxAudioOutputLevel() {
            return this.callRxAudioOutputLevel;
        }

        public long getCallRxAudioPacketBufferMs() {
            return this.callRxAudioPacketBufferMs;
        }

        public long getCallRxAudioPlayoutBufferMs() {
            return this.callRxAudioPlayoutBufferMs;
        }

        public long getCallRxAvgJitterUsec() {
            return this.callRxAvgJitterUsec;
        }

        public long getCallRxEncodedAudioToFrameDelayMs() {
            return this.callRxEncodedAudioToFrameDelayMs;
        }

        public long getCallRxJitterBufferMs() {
            return this.callRxJitterBufferMs;
        }

        public long getCallRxMaxBurstPacketLoss() {
            return this.callRxMaxBurstPacketLoss;
        }

        public long getCallRxPacketLoss() {
            return this.callRxPacketLoss;
        }

        public long getCallRxTotalBytes() {
            return this.callRxTotalBytes;
        }

        public long getCallRxTotalPackets() {
            return this.callRxTotalPackets;
        }

        public long getCallTxAudioEncodeDelayUsec() {
            return this.callTxAudioEncodeDelayUsec;
        }

        public long getCallTxAudioEncodePreprocessingDelayUsec() {
            return this.callTxAudioEncodePreprocessingDelayUsec;
        }

        public long getCallTxAudioFrameToPacketDelayMs() {
            return this.callTxAudioFrameToPacketDelayMs;
        }

        public long getCallTxAudioInputLevel() {
            return this.callTxAudioInputLevel;
        }

        public long getCallTxAudioPacketizationDelayUsec() {
            return this.callTxAudioPacketizationDelayUsec;
        }

        public long getCallTxAvgJitterUsec() {
            return this.callTxAvgJitterUsec;
        }

        public long getCallTxMaxBurstPacketLoss() {
            return this.callTxMaxBurstPacketLoss;
        }

        public long getCallTxPacketLoss() {
            return this.callTxPacketLoss;
        }

        public long getCallTxTotalBytes() {
            return this.callTxTotalBytes;
        }

        public long getCallTxTotalPackets() {
            return this.callTxTotalPackets;
        }

        public void setCallRoundTripDelayUsec(long j) {
            this.callRoundTripDelayUsec = j;
        }

        public void setCallRxAudioDecodeDelayUsec(long j) {
            this.callRxAudioDecodeDelayUsec = j;
        }

        public void setCallRxAudioDepacketizationDelayUsec(long j) {
            this.callRxAudioDepacketizationDelayUsec = j;
        }

        public void setCallRxAudioOutputLevel(long j) {
            this.callRxAudioOutputLevel = j;
        }

        public void setCallRxAudioPacketBufferMs(long j) {
            this.callRxAudioPacketBufferMs = j;
        }

        public void setCallRxAudioPlayoutBufferMs(long j) {
            this.callRxAudioPlayoutBufferMs = j;
        }

        public void setCallRxAvgJitterUsec(long j) {
            this.callRxAvgJitterUsec = j;
        }

        public void setCallRxEncodedAudioToFrameDelayMs(long j) {
            this.callRxEncodedAudioToFrameDelayMs = j;
        }

        public void setCallRxJitterBufferMs(long j) {
            this.callRxJitterBufferMs = j;
        }

        public void setCallRxMaxBurstPacketLoss(long j) {
            this.callRxMaxBurstPacketLoss = j;
        }

        public void setCallRxPacketLoss(long j) {
            this.callRxPacketLoss = j;
        }

        public void setCallRxTotalBytes(long j) {
            this.callRxTotalBytes = j;
        }

        public void setCallRxTotalPackets(long j) {
            this.callRxTotalPackets = j;
        }

        public void setCallTxAudioEncodeDelayUsec(long j) {
            this.callTxAudioEncodeDelayUsec = j;
        }

        public void setCallTxAudioEncodePreprocessingDelayUsec(long j) {
            this.callTxAudioEncodePreprocessingDelayUsec = j;
        }

        public void setCallTxAudioFrameToPacketDelayMs(long j) {
            this.callTxAudioFrameToPacketDelayMs = j;
        }

        public void setCallTxAudioInputLevel(long j) {
            this.callTxAudioInputLevel = j;
        }

        public void setCallTxAudioPacketizationDelayUsec(long j) {
            this.callTxAudioPacketizationDelayUsec = j;
        }

        public void setCallTxAvgJitterUsec(long j) {
            this.callTxAvgJitterUsec = j;
        }

        public void setCallTxMaxBurstPacketLoss(long j) {
            this.callTxMaxBurstPacketLoss = j;
        }

        public void setCallTxPacketLoss(long j) {
            this.callTxPacketLoss = j;
        }

        public void setCallTxTotalBytes(long j) {
            this.callTxTotalBytes = j;
        }

        public void setCallTxTotalPackets(long j) {
            this.callTxTotalPackets = j;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallQualityMetrics.BaseQualityMetrics(callRxTotalPackets=");
            outline107.append(getCallRxTotalPackets());
            outline107.append(", callRxTotalBytes=");
            outline107.append(getCallRxTotalBytes());
            outline107.append(", callRxPacketLoss=");
            outline107.append(getCallRxPacketLoss());
            outline107.append(", callRxMaxBurstPacketLoss=");
            outline107.append(getCallRxMaxBurstPacketLoss());
            outline107.append(", callRxAvgJitterUsec=");
            outline107.append(getCallRxAvgJitterUsec());
            outline107.append(", callRxJitterBufferMs=");
            outline107.append(getCallRxJitterBufferMs());
            outline107.append(", callRxEncodedAudioToFrameDelayMs=");
            outline107.append(getCallRxEncodedAudioToFrameDelayMs());
            outline107.append(", callTxTotalPackets=");
            outline107.append(getCallTxTotalPackets());
            outline107.append(", callTxTotalBytes=");
            outline107.append(getCallTxTotalBytes());
            outline107.append(", callTxPacketLoss=");
            outline107.append(getCallTxPacketLoss());
            outline107.append(", callTxMaxBurstPacketLoss=");
            outline107.append(getCallTxMaxBurstPacketLoss());
            outline107.append(", callTxAvgJitterUsec=");
            outline107.append(getCallTxAvgJitterUsec());
            outline107.append(", callTxAudioFrameToPacketDelayMs=");
            outline107.append(getCallTxAudioFrameToPacketDelayMs());
            outline107.append(", callRoundTripDelayUsec=");
            outline107.append(getCallRoundTripDelayUsec());
            outline107.append(", callTxAudioEncodePreprocessingDelayUsec=");
            outline107.append(getCallTxAudioEncodePreprocessingDelayUsec());
            outline107.append(", callTxAudioEncodeDelayUsec=");
            outline107.append(getCallTxAudioEncodeDelayUsec());
            outline107.append(", callTxAudioPacketizationDelayUsec=");
            outline107.append(getCallTxAudioPacketizationDelayUsec());
            outline107.append(", callRxAudioPacketBufferMs=");
            outline107.append(getCallRxAudioPacketBufferMs());
            outline107.append(", callRxAudioPlayoutBufferMs=");
            outline107.append(getCallRxAudioPlayoutBufferMs());
            outline107.append(", callRxAudioDepacketizationDelayUsec=");
            outline107.append(getCallRxAudioDepacketizationDelayUsec());
            outline107.append(", callRxAudioDecodeDelayUsec=");
            outline107.append(getCallRxAudioDecodeDelayUsec());
            outline107.append(", callTxAudioInputLevel=");
            outline107.append(getCallTxAudioInputLevel());
            outline107.append(", callRxAudioOutputLevel=");
            outline107.append(getCallRxAudioOutputLevel());
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* loaded from: classes11.dex */
    public static class CodecData {
        String audioCodec;
        String videoCodec;

        public CodecData(String str, String str2) {
            this.videoCodec = str;
            this.audioCodec = str2;
        }

        public String getAudioCodec() {
            return this.audioCodec;
        }

        public String getVideoCodec() {
            return this.videoCodec;
        }
    }

    /* loaded from: classes11.dex */
    public static class VideoQualityMetrics extends BaseQualityMetrics {
        long callActualEncBitrate;
        long callAdaptationChanges;
        long callAvailableReceiveBandwidth;
        long callAvailableSendBandwidth;
        long callBucketDelay;
        long callRetransmitBitrate;
        long callRxAvSyncAvgDelayMs;
        long callRxAvSyncAvgRelativeDelayMs;
        long callRxDecodeVideoMs;
        long callRxEncodedVideoToFrameDelayMs;
        long callRxEndToEndVideoLatencyMs;
        long callRxFirs;
        long callRxFrameHeight;
        long callRxFrameRate;
        long callRxFrameWidth;
        long callRxNacks;
        long callRxPlis;
        long callRxQpAvg;
        long callTargetEncBitrate;
        long callTransmitBitrate;
        Boolean callTxBandwidthLimited;
        Boolean callTxCpuLimited;
        long callTxFirs;
        long callTxFrameHeight;
        long callTxFrameRate;
        long callTxFrameRateInput;
        long callTxFrameWidth;
        long callTxNacks;
        long callTxPlis;
        long callTxQpAvg;
        long callTxVideoFrameToPacketDelayMs;

        public long getCallActualEncBitrate() {
            return this.callActualEncBitrate;
        }

        public long getCallAdaptationChanges() {
            return this.callAdaptationChanges;
        }

        public long getCallAvailableReceiveBandwidth() {
            return this.callAvailableReceiveBandwidth;
        }

        public long getCallAvailableSendBandwidth() {
            return this.callAvailableSendBandwidth;
        }

        public long getCallBucketDelay() {
            return this.callBucketDelay;
        }

        public long getCallRetransmitBitrate() {
            return this.callRetransmitBitrate;
        }

        public long getCallRxAvSyncAvgDelayMs() {
            return this.callRxAvSyncAvgDelayMs;
        }

        public long getCallRxAvSyncAvgRelativeDelayMs() {
            return this.callRxAvSyncAvgRelativeDelayMs;
        }

        public long getCallRxDecodeVideoMs() {
            return this.callRxDecodeVideoMs;
        }

        public long getCallRxEncodedVideoToFrameDelayMs() {
            return this.callRxEncodedVideoToFrameDelayMs;
        }

        public long getCallRxEndToEndVideoLatencyMs() {
            return this.callRxEndToEndVideoLatencyMs;
        }

        public long getCallRxFirs() {
            return this.callRxFirs;
        }

        public long getCallRxFrameHeight() {
            return this.callRxFrameHeight;
        }

        public long getCallRxFrameRate() {
            return this.callRxFrameRate;
        }

        public long getCallRxFrameWidth() {
            return this.callRxFrameWidth;
        }

        public long getCallRxNacks() {
            return this.callRxNacks;
        }

        public long getCallRxPlis() {
            return this.callRxPlis;
        }

        public long getCallRxQpAvg() {
            return this.callRxQpAvg;
        }

        public long getCallTargetEncBitrate() {
            return this.callTargetEncBitrate;
        }

        public long getCallTransmitBitrate() {
            return this.callTransmitBitrate;
        }

        public Boolean getCallTxBandwidthLimited() {
            return this.callTxBandwidthLimited;
        }

        public Boolean getCallTxCpuLimited() {
            return this.callTxCpuLimited;
        }

        public long getCallTxFirs() {
            return this.callTxFirs;
        }

        public long getCallTxFrameHeight() {
            return this.callTxFrameHeight;
        }

        public long getCallTxFrameRate() {
            return this.callTxFrameRate;
        }

        public long getCallTxFrameRateInput() {
            return this.callTxFrameRateInput;
        }

        public long getCallTxFrameWidth() {
            return this.callTxFrameWidth;
        }

        public long getCallTxNacks() {
            return this.callTxNacks;
        }

        public long getCallTxPlis() {
            return this.callTxPlis;
        }

        public long getCallTxQpAvg() {
            return this.callTxQpAvg;
        }

        public long getCallTxVideoFrameToPacketDelayMs() {
            return this.callTxVideoFrameToPacketDelayMs;
        }

        public void setCallActualEncBitrate(long j) {
            this.callActualEncBitrate = j;
        }

        public void setCallAdaptationChanges(long j) {
            this.callAdaptationChanges = j;
        }

        public void setCallAvailableReceiveBandwidth(long j) {
            this.callAvailableReceiveBandwidth = j;
        }

        public void setCallAvailableSendBandwidth(long j) {
            this.callAvailableSendBandwidth = j;
        }

        public void setCallBucketDelay(long j) {
            this.callBucketDelay = j;
        }

        public void setCallRetransmitBitrate(long j) {
            this.callRetransmitBitrate = j;
        }

        public void setCallRxAvSyncAvgDelayMs(long j) {
            this.callRxAvSyncAvgDelayMs = j;
        }

        public void setCallRxAvSyncAvgRelativeDelayMs(long j) {
            this.callRxAvSyncAvgRelativeDelayMs = j;
        }

        public void setCallRxDecodeVideoMs(long j) {
            this.callRxDecodeVideoMs = j;
        }

        public void setCallRxEncodedVideoToFrameDelayMs(long j) {
            this.callRxEncodedVideoToFrameDelayMs = j;
        }

        public void setCallRxEndToEndVideoLatencyMs(long j) {
            this.callRxEndToEndVideoLatencyMs = j;
        }

        public void setCallRxFirs(long j) {
            this.callRxFirs = j;
        }

        public void setCallRxFrameHeight(long j) {
            this.callRxFrameHeight = j;
        }

        public void setCallRxFrameRate(long j) {
            this.callRxFrameRate = j;
        }

        public void setCallRxFrameWidth(long j) {
            this.callRxFrameWidth = j;
        }

        public void setCallRxNacks(long j) {
            this.callRxNacks = j;
        }

        public void setCallRxPlis(long j) {
            this.callRxPlis = j;
        }

        public void setCallRxQpAvg(long j) {
            this.callRxQpAvg = j;
        }

        public void setCallTargetEncBitrate(long j) {
            this.callTargetEncBitrate = j;
        }

        public void setCallTransmitBitrate(long j) {
            this.callTransmitBitrate = j;
        }

        public void setCallTxBandwidthLimited(Boolean bool) {
            this.callTxBandwidthLimited = bool;
        }

        public void setCallTxCpuLimited(Boolean bool) {
            this.callTxCpuLimited = bool;
        }

        public void setCallTxFirs(long j) {
            this.callTxFirs = j;
        }

        public void setCallTxFrameHeight(long j) {
            this.callTxFrameHeight = j;
        }

        public void setCallTxFrameRate(long j) {
            this.callTxFrameRate = j;
        }

        public void setCallTxFrameRateInput(long j) {
            this.callTxFrameRateInput = j;
        }

        public void setCallTxFrameWidth(long j) {
            this.callTxFrameWidth = j;
        }

        public void setCallTxNacks(long j) {
            this.callTxNacks = j;
        }

        public void setCallTxPlis(long j) {
            this.callTxPlis = j;
        }

        public void setCallTxQpAvg(long j) {
            this.callTxQpAvg = j;
        }

        public void setCallTxVideoFrameToPacketDelayMs(long j) {
            this.callTxVideoFrameToPacketDelayMs = j;
        }

        @Override // com.amazon.comms.calling.sipclient.CallQualityMetrics.BaseQualityMetrics
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallQualityMetrics.VideoQualityMetrics(callRxFrameWidth=");
            outline107.append(getCallRxFrameWidth());
            outline107.append(", callRxFrameHeight=");
            outline107.append(getCallRxFrameHeight());
            outline107.append(", callRxFrameRate=");
            outline107.append(getCallRxFrameRate());
            outline107.append(", callRxEncodedVideoToFrameDelayMs=");
            outline107.append(getCallRxEncodedVideoToFrameDelayMs());
            outline107.append(", callRxEndToEndVideoLatencyMs=");
            outline107.append(getCallRxEndToEndVideoLatencyMs());
            outline107.append(", callRxAvSyncAvgDelayMs=");
            outline107.append(getCallRxAvSyncAvgDelayMs());
            outline107.append(", callRxAvSyncAvgRelativeDelayMs=");
            outline107.append(getCallRxAvSyncAvgRelativeDelayMs());
            outline107.append(", callRxDecodeVideoMs=");
            outline107.append(getCallRxDecodeVideoMs());
            outline107.append(", callTxFrameWidth=");
            outline107.append(getCallTxFrameWidth());
            outline107.append(", callTxFrameHeight=");
            outline107.append(getCallTxFrameHeight());
            outline107.append(", callTxFrameRate=");
            outline107.append(getCallTxFrameRate());
            outline107.append(", callTxFrameRateInput=");
            outline107.append(getCallTxFrameRateInput());
            outline107.append(", callTxVideoFrameToPacketDelayMs=");
            outline107.append(getCallTxVideoFrameToPacketDelayMs());
            outline107.append(", callTargetEncBitrate=");
            outline107.append(getCallTargetEncBitrate());
            outline107.append(", callActualEncBitrate=");
            outline107.append(getCallActualEncBitrate());
            outline107.append(", callTransmitBitrate=");
            outline107.append(getCallTransmitBitrate());
            outline107.append(", callRetransmitBitrate=");
            outline107.append(getCallRetransmitBitrate());
            outline107.append(", callBucketDelay=");
            outline107.append(getCallBucketDelay());
            outline107.append(", callAvailableReceiveBandwidth=");
            outline107.append(getCallAvailableReceiveBandwidth());
            outline107.append(", callAvailableSendBandwidth=");
            outline107.append(getCallAvailableSendBandwidth());
            outline107.append(", callRxNacks=");
            outline107.append(getCallRxNacks());
            outline107.append(", callRxFirs=");
            outline107.append(getCallRxFirs());
            outline107.append(", callRxPlis=");
            outline107.append(getCallRxPlis());
            outline107.append(", callTxNacks=");
            outline107.append(getCallTxNacks());
            outline107.append(", callTxFirs=");
            outline107.append(getCallTxFirs());
            outline107.append(", callTxPlis=");
            outline107.append(getCallTxPlis());
            outline107.append(", callAdaptationChanges=");
            outline107.append(getCallAdaptationChanges());
            outline107.append(", callTxCpuLimited=");
            outline107.append(getCallTxCpuLimited());
            outline107.append(", callTxBandwidthLimited=");
            outline107.append(getCallTxBandwidthLimited());
            outline107.append(", callTxQpAvg=");
            outline107.append(getCallTxQpAvg());
            outline107.append(", callRxQpAvg=");
            outline107.append(getCallRxQpAvg());
            outline107.append(")");
            return outline107.toString();
        }
    }

    public BaseQualityMetrics getAudio() {
        return this.audio;
    }

    public CodecData getCodecData() {
        return this.codecData;
    }

    public VideoQualityMetrics getVideo() {
        return this.video;
    }

    public void setAudio(BaseQualityMetrics baseQualityMetrics) {
        this.audio = baseQualityMetrics;
    }

    public void setCodecData(CodecData codecData) {
        this.codecData = codecData;
    }

    public void setVideo(VideoQualityMetrics videoQualityMetrics) {
        this.video = videoQualityMetrics;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallQualityMetrics(video=");
        outline107.append(getVideo());
        outline107.append(", audio=");
        outline107.append(getAudio());
        outline107.append(", codecData=");
        outline107.append(getCodecData());
        outline107.append(")");
        return outline107.toString();
    }
}
