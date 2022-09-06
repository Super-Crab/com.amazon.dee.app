package com.amazon.deecomms.calling.model;

import amazon.speech.simclient.settings.Settings;
import com.amazon.comms.calling.sipclient.CallQualityMetrics;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class CallQualityMetricsModel extends AbstractCallModel {
    @JsonProperty("answeredCall")
    private boolean answeredCall;
    @JsonProperty("audioStats")
    private CallQualityMetrics.BaseQualityMetrics audio;
    @JsonProperty("callConnectDurationInMillis")
    private long callConnectDurationInMillis;
    @JsonProperty("callTotalDurationInMillis")
    private long callTotalDurationInMillis;
    @JsonProperty("deviceType")
    private String deviceType;
    @JsonProperty("isCaller")
    private boolean isCaller;
    @JsonProperty(Settings.ListeningSettings.EXTRA_REASON)
    private String reason;
    @JsonProperty("statusCode")
    private String statusCode;
    @JsonProperty("userEndedCall")
    private boolean userEndedCall;
    @JsonProperty("videoStats")
    private CallQualityMetrics.VideoQualityMetrics video;

    public CallQualityMetricsModel(CallQualityMetrics callQualityMetrics) {
        if (callQualityMetrics != null) {
            setAudio(callQualityMetrics.getAudio());
            setVideo(callQualityMetrics.getVideo());
        }
    }

    public CallQualityMetrics.BaseQualityMetrics getAudio() {
        return this.audio;
    }

    public long getCallConnectDurationInMillis() {
        return this.callConnectDurationInMillis;
    }

    public long getCallTotalDurationInMillis() {
        return this.callTotalDurationInMillis;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getReason() {
        return this.reason;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public CallQualityMetrics.VideoQualityMetrics getVideo() {
        return this.video;
    }

    public boolean isAnsweredCall() {
        return this.answeredCall;
    }

    public boolean isCaller() {
        return this.isCaller;
    }

    public boolean isUserEndedCall() {
        return this.userEndedCall;
    }

    public void setAnsweredCall(boolean z) {
        this.answeredCall = z;
    }

    public void setAudio(CallQualityMetrics.BaseQualityMetrics baseQualityMetrics) {
        this.audio = baseQualityMetrics;
    }

    public void setCallConnectDurationInMillis(long j) {
        this.callConnectDurationInMillis = j;
    }

    public void setCallTotalDurationInMillis(long j) {
        this.callTotalDurationInMillis = j;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setIsCaller(boolean z) {
        this.isCaller = z;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public void setUserEndedCall(boolean z) {
        this.userEndedCall = z;
    }

    public void setVideo(CallQualityMetrics.VideoQualityMetrics videoQualityMetrics) {
        this.video = videoQualityMetrics;
    }
}
