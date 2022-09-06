package com.amazon.deecomms.media.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class MediaCreateResponse {
    @JsonProperty("id")
    private String arn;
    @JsonProperty("transcript")
    private String transcript;
    @JsonProperty("transcriptStatus")
    private String transcriptStatus;

    public String getArn() {
        return this.arn;
    }

    public String getTranscript() {
        return this.transcript;
    }

    public String getTranscriptStatus() {
        return this.transcriptStatus;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setTranscript(String str) {
        this.transcript = str;
    }

    public void setTranscriptStatus(String str) {
        this.transcriptStatus = str;
    }
}
