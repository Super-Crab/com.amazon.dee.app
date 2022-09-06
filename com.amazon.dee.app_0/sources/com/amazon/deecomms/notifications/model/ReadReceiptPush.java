package com.amazon.deecomms.notifications.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class ReadReceiptPush {
    public static final String TYPE = "read-message";
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("messageId")
    private long messageId;
    @JsonProperty("userCommsId")
    private String userCommsId;

    public String getConversationId() {
        return this.conversationId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public String getUserCommsId() {
        return this.userCommsId;
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

    public void setUserCommsId(String str) {
        this.userCommsId = str;
    }
}
