package com.amazon.deecomms.messaging.model.response;

import androidx.annotation.NonNull;
import com.amazon.deecomms.messaging.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class GetMessagesResponse {
    @NonNull
    @JsonProperty("conversationId")
    public String conversationId;
    @NonNull
    @JsonProperty("messages")
    public List<Message> messages;
}
