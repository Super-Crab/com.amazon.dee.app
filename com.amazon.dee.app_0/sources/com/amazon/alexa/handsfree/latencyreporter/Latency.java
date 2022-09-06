package com.amazon.alexa.handsfree.latencyreporter;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes8.dex */
public enum Latency {
    PARTNER_WAKE_WORD_DETECTION_LATENCY("PartnerWakeWordDetectionLatency", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.END_BEFORE_START),
    PARTNER_WAKE_WORD_DETECTION_LATENCY_ANY("PartnerWakeWordDetectionLatency", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.ANY),
    PARTNER_WAKE_WORD_FIRST_STAGE_LATENCY("PartnerWakeWordFirstStageLatency", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.ANY),
    PARTNER_WAKE_WORD_SECOND_STAGE_LATENCY("PartnerWakeWordSecondStageLatency", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.ANY),
    ALEXA_SERVICE_WAKE_WORD_DETECTION_LATENCY("AlexaServiceWakeWordLatency", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    PARTNER_VOICE_CHROME_LATENCY("VoiceChromeLatency:Partner", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.END_BEFORE_START),
    ALEXA_SERVICE_VOICE_CHROME_LATENCY_API("VoiceChromeLatency:AlexaService:Api", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    ALEXA_SERVICE_VOICE_CHROME_LATENCY_PRYON("VoiceChromeLatency:AlexaService:Pryon", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    ALEXA_SERVICE_VOICE_CHROME_LATENCY_UI("VoiceChromeLatency:AlexaService:UI", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    OVERALL_VOICE_CHROME_LATENCY("VoiceChromeLatency:Partner:UI", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    HANDS_FREE_QS_TILE_SYNC_LATENCY("QSSyncLatency:SetupComplete", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END),
    HANDS_FREE_QS_TILE_SYNC_INCOMPLETE_SETUP_LATENCY("QSSyncLatency:SetupNotComplete", LatencyTimeout.ONE_MINUTE.getValue(), TimestampReceiveOrder.START_BEFORE_END);
    
    private final String mMetricName;
    private final long mTimeout;
    private final TimestampReceiveOrder mTimestampReceiveOrder;

    /* loaded from: classes8.dex */
    enum LatencyTimeout {
        NO_TIMEOUT(-1),
        ONE_MINUTE(60000),
        ONE_HOUR(3600000),
        ONE_DAY(86400000);
        
        private final long mValue;

        LatencyTimeout(long j) {
            this.mValue = j;
        }

        long getValue() {
            return this.mValue;
        }
    }

    /* loaded from: classes8.dex */
    public enum TimestampReceiveOrder {
        START_BEFORE_END,
        END_BEFORE_START,
        ANY
    }

    Latency(@NonNull String str, long j, @NonNull TimestampReceiveOrder timestampReceiveOrder) {
        this.mMetricName = str;
        this.mTimeout = j;
        this.mTimestampReceiveOrder = timestampReceiveOrder;
    }

    public String getMetricName() {
        return this.mMetricName;
    }

    public long getTimeout() {
        return this.mTimeout;
    }

    public TimestampReceiveOrder getTimestampReceiveOrder() {
        return this.mTimestampReceiveOrder;
    }

    public boolean isTimeoutSet() {
        return this.mTimeout != LatencyTimeout.NO_TIMEOUT.getValue();
    }
}
