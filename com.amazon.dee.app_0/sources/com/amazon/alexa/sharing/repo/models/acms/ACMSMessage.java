package com.amazon.alexa.sharing.repo.models.acms;

import amazon.speech.model.DirectiveIntent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.api.models.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class ACMSMessage {
    @SerializedName("clientMessageId")
    private String clientMessageId;
    @SerializedName("conversationId")
    private String conversationId;
    @SerializedName("messageId")
    private long messageId;
    @NonNull
    @SerializedName("payload")
    private JsonObject payload;
    @SerializedName("reactionTopic")
    private String reactionTopic;
    @SerializedName("sender")
    private String senderCommsId;
    @SerializedName(DirectiveIntent.INTENT_KEY_SEQUENCE_ID)
    private long sequenceId;
    @SerializedName("time")
    private String time;
    @NonNull
    @SerializedName("type")
    private String type;
    @SerializedName("globalMessageId")
    private String globalMessageId = null;
    @SerializedName("parentGlobalMessageId")
    private String parentGlobalMessageId = null;
    @SerializedName("parentMessageType")
    private String parentMessageType = null;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private String clientMessageId;
        private String conversationId;
        private String globalMessageId;
        private long messageId;
        private String parentGlobalMessageId;
        private String parentMessageType;
        private JsonObject payload;
        private String reactionTopic;
        private String senderCommsId;
        private long sequenceId;
        private String time;
        private String type;

        public ACMSMessage build() {
            ACMSMessage aCMSMessage = new ACMSMessage(this.conversationId, this.messageId, this.time, this.senderCommsId, this.type, this.payload);
            aCMSMessage.setSequenceId(this.sequenceId);
            aCMSMessage.setClientMessageId(this.clientMessageId);
            aCMSMessage.setGlobalMessageId(this.globalMessageId);
            aCMSMessage.setParentGlobalMessageId(this.parentGlobalMessageId);
            aCMSMessage.setParentMessageType(this.parentMessageType);
            aCMSMessage.reactionTopic = this.reactionTopic;
            return aCMSMessage;
        }

        public Builder withClientMessageId(String str) {
            this.clientMessageId = str;
            return this;
        }

        public Builder withConversationId(String str) {
            this.conversationId = str;
            return this;
        }

        public Builder withGlobalMessageId(String str) {
            this.globalMessageId = str;
            return this;
        }

        public Builder withMessageId(long j) {
            this.messageId = j;
            return this;
        }

        public Builder withParentGlobalMessageId(String str) {
            this.parentGlobalMessageId = str;
            return this;
        }

        public Builder withParentMessageType(String str) {
            this.parentMessageType = str;
            return this;
        }

        public Builder withPayload(JsonObject jsonObject) {
            this.payload = jsonObject;
            return this;
        }

        public Builder withReactionTopic(String str) {
            this.reactionTopic = str;
            return this;
        }

        public Builder withSenderCommsId(String str) {
            this.senderCommsId = str;
            return this;
        }

        public Builder withSequenceId(long j) {
            this.sequenceId = j;
            return this;
        }

        public Builder withTime(String str) {
            this.time = str;
            return this;
        }

        public Builder withType(String str) {
            this.type = str;
            return this;
        }
    }

    public ACMSMessage(String str, long j, String str2, String str3, String str4, JsonObject jsonObject) {
        this.conversationId = str;
        this.messageId = j;
        this.time = str2;
        this.senderCommsId = str3;
        this.type = str4;
        this.payload = jsonObject;
    }

    public static ACMSMessage fromNewMessageResponse(Message message, String str) {
        return new Builder().withConversationId(message.getConversationId()).withMessageId(message.getSortId().longValue()).withSequenceId(message.getLastUpdateId().longValue()).withClientMessageId(str).withGlobalMessageId(message.getId()).withParentGlobalMessageId(message.getParentId()).withParentMessageType(null).withReactionTopic(null).withTime(message.getSentDate()).withSenderCommsId(message.getSenderId()).withType(message.getType().getValue()).withPayload(null).build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ACMSMessage.class != obj.getClass()) {
            return false;
        }
        ACMSMessage aCMSMessage = (ACMSMessage) obj;
        return this.messageId == aCMSMessage.messageId && this.sequenceId == aCMSMessage.sequenceId && Objects.equals(this.type, aCMSMessage.type) && Objects.equals(this.payload, aCMSMessage.payload) && Objects.equals(this.time, aCMSMessage.time) && Objects.equals(this.conversationId, aCMSMessage.conversationId) && Objects.equals(this.clientMessageId, aCMSMessage.clientMessageId) && Objects.equals(this.globalMessageId, aCMSMessage.globalMessageId) && Objects.equals(this.parentGlobalMessageId, aCMSMessage.parentGlobalMessageId) && Objects.equals(this.parentMessageType, aCMSMessage.parentMessageType) && Objects.equals(this.reactionTopic, aCMSMessage.reactionTopic) && Objects.equals(this.senderCommsId, aCMSMessage.senderCommsId);
    }

    public String getClientMessageId() {
        return this.clientMessageId;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    @Nullable
    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    public long getMessageId() {
        return this.messageId;
    }

    @Nullable
    public String getParentGlobalMessageId() {
        return this.parentGlobalMessageId;
    }

    @Nullable
    public String getParentMessageType() {
        return this.parentMessageType;
    }

    @Nullable
    public JsonObject getPayload() {
        return this.payload;
    }

    @Nullable
    public String getReactionTopic() {
        return this.reactionTopic;
    }

    public String getSenderCommsId() {
        return this.senderCommsId;
    }

    public long getSequenceId() {
        return this.sequenceId;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.conversationId, Long.valueOf(this.messageId), Long.valueOf(this.sequenceId), this.clientMessageId, this.globalMessageId, this.parentGlobalMessageId, this.parentMessageType, this.reactionTopic, this.time, this.senderCommsId, this.type, this.payload);
    }

    public void setClientMessageId(String str) {
        this.clientMessageId = str;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setGlobalMessageId(@Nullable String str) {
        this.globalMessageId = str;
    }

    public void setMessageId(long j) {
        this.messageId = j;
    }

    public void setParentGlobalMessageId(@Nullable String str) {
        this.parentGlobalMessageId = str;
    }

    public void setParentMessageType(@Nullable String str) {
        this.parentMessageType = str;
    }

    public void setPayload(@Nullable JsonObject jsonObject) {
        this.payload = jsonObject;
    }

    public void setSenderCommsId(String str) {
        this.senderCommsId = str;
    }

    public void setSequenceId(long j) {
        this.sequenceId = j;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ACMSMessage {conversationId='");
        GeneratedOutlineSupport1.outline176(outline107, this.conversationId, Chars.QUOTE, ", messageId=");
        outline107.append(this.messageId);
        outline107.append(", sequenceId=");
        outline107.append(this.sequenceId);
        outline107.append(", clientMessageId='");
        GeneratedOutlineSupport1.outline176(outline107, this.clientMessageId, Chars.QUOTE, ", globalMessageId='");
        GeneratedOutlineSupport1.outline176(outline107, this.globalMessageId, Chars.QUOTE, ", parentGlobalMessageId='");
        GeneratedOutlineSupport1.outline176(outline107, this.parentGlobalMessageId, Chars.QUOTE, ", parentMessageType='");
        GeneratedOutlineSupport1.outline176(outline107, this.parentMessageType, Chars.QUOTE, ", reactionTopic='");
        GeneratedOutlineSupport1.outline176(outline107, this.reactionTopic, Chars.QUOTE, ", time='");
        GeneratedOutlineSupport1.outline176(outline107, this.time, Chars.QUOTE, ", senderCommsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.senderCommsId, Chars.QUOTE, ", type='");
        GeneratedOutlineSupport1.outline176(outline107, this.type, Chars.QUOTE, ", payload=");
        outline107.append(this.payload);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
