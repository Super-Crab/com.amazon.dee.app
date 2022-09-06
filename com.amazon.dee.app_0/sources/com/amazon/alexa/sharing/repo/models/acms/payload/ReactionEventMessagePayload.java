package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class ReactionEventMessagePayload implements MessagePayload {
    public static final String TYPE = "event/reaction";
    @SerializedName("parentMessageId")
    private long parentMessageId;
    @SerializedName("reactionType")
    private String reactionType;
    @SerializedName("wasRemoved")
    private Boolean wasRemoved;

    public long getParentMessageId() {
        return this.parentMessageId;
    }

    public String getReactionType() {
        return this.reactionType;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
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
