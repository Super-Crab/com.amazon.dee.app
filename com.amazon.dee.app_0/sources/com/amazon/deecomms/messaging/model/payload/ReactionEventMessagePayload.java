package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import com.amazon.deecomms.R;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class ReactionEventMessagePayload implements MessagePayload {
    public static final String TYPE = "event/reaction";
    @JsonProperty("parentMessageId")
    private long parentMessageId;
    @JsonProperty("reactionType")
    private String reactionType;
    @JsonProperty("wasRemoved")
    private Boolean wasRemoved;

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    public long getParentMessageId() {
        return this.parentMessageId;
    }

    public String getReactionType() {
        return this.reactionType;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        return context.getString(R.string.notification_text_new_shared_media_reaction);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "event/reaction";
    }

    public Boolean getWasRemoved() {
        return this.wasRemoved;
    }

    public void setParentMessageId(long j) {
        this.parentMessageId = j;
    }

    public void setReactionType(String str) {
        this.reactionType = str;
    }

    public void setWasRemoved(Boolean bool) {
        this.wasRemoved = bool;
    }
}
