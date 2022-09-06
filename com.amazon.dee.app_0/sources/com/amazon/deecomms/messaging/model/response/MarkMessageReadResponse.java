package com.amazon.deecomms.messaging.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class MarkMessageReadResponse {
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("messageId")
    private long messageId;

    public String getConversationId() {
        return this.conversationId;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setMessageId(long j) {
        this.messageId = j;
    }
}
