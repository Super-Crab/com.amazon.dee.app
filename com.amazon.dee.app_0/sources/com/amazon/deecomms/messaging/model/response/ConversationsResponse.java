package com.amazon.deecomms.messaging.model.response;

import com.amazon.deecomms.messaging.model.Conversation;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class ConversationsResponse {
    @JsonProperty("conversations")
    private Conversation[] conversations;
    @JsonProperty("lastPage")
    private boolean lastPage;

    public Conversation[] getConversations() {
        return this.conversations;
    }

    public boolean getLastPage() {
        return this.lastPage;
    }

    public void setConversations(Conversation[] conversationArr) {
        this.conversations = conversationArr;
    }

    public void setLastPage(boolean z) {
        this.lastPage = z;
    }
}
