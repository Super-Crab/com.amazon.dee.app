package com.amazon.deecomms.notifications.model;

import amazon.speech.model.DirectiveIntent;
import com.amazon.deecomms.messaging.model.status.TranscriptStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class TranscriptUpdatePush {
    public static final String TYPE = "transcript-update";
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("messageId")
    private long messageId;
    @JsonProperty(DirectiveIntent.INTENT_KEY_SEQUENCE_ID)
    private long sequenceId;
    @JsonProperty("transcript")
    private String transcript;
    @JsonProperty("transcriptStatus")
    private String transcriptStatus;

    public String getConversationId() {
        return this.conversationId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public long getSequenceId() {
        return this.sequenceId;
    }

    public String getTranscript() {
        return this.transcript;
    }

    public TranscriptStatus getTranscriptStatus() {
        return TranscriptStatus.fromString(this.transcriptStatus);
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setMessageId(long j) {
        this.messageId = j;
    }

    public void setSequenceId(long j) {
        this.sequenceId = j;
    }

    public void setTranscript(String str) {
        this.transcript = str;
    }

    public void setTranscriptStatus(String str) {
        this.transcriptStatus = str;
    }

    @JsonIgnore
    public void setTranscriptStatus(TranscriptStatus transcriptStatus) {
        if (transcriptStatus == null) {
            this.transcriptStatus = null;
        } else {
            this.transcriptStatus = transcriptStatus.toString();
        }
    }
}
