package com.amazon.deecomms.messaging.model;

import amazon.speech.model.DirectiveIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.model.payload.CallEventPayload;
import com.amazon.deecomms.messaging.model.payload.ContactConnectionSuccessPayload;
import com.amazon.deecomms.messaging.model.payload.ContactInvitationPayload;
import com.amazon.deecomms.messaging.model.payload.MediaMessagePayload;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.model.payload.MissedCallEventPayload;
import com.amazon.deecomms.messaging.model.payload.SharingMessagePayload;
import com.amazon.deecomms.messaging.model.payload.TextMessagePayload;
import com.amazon.deecomms.notifications.model.announcement.AudioAnnouncementPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/* loaded from: classes12.dex */
public class Message {
    @JsonProperty("clientMessageId")
    private String clientMessageId;
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("globalMessageId")
    private String globalMessageId;
    @JsonProperty("messageId")
    private long messageId;
    @JsonProperty("parentGlobalMessageId")
    private String parentGlobalMessageId;
    @JsonProperty("parentMessageType")
    private String parentMessageType;
    @JsonProperty("payload")
    @JsonTypeInfo(include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type", use = JsonTypeInfo.Id.NAME, visible = true)
    @Nullable
    @JsonSubTypes({@JsonSubTypes.Type(name = "event/call", value = CallEventPayload.class), @JsonSubTypes.Type(name = "event/missed-call", value = MissedCallEventPayload.class), @JsonSubTypes.Type(name = "message/text", value = TextMessagePayload.class), @JsonSubTypes.Type(name = "message/contact-invitation", value = ContactInvitationPayload.class), @JsonSubTypes.Type(name = "message/contact-connection-success", value = ContactConnectionSuccessPayload.class), @JsonSubTypes.Type(name = "message/audio", value = AudioMessagePayload.class), @JsonSubTypes.Type(name = "announcement/audio", value = AudioAnnouncementPayload.class), @JsonSubTypes.Type(name = "message/media", value = MediaMessagePayload.class), @JsonSubTypes.Type(name = "message/shared-content", value = SharingMessagePayload.class)})
    private MessagePayload payload;
    @JsonProperty("reactionTopic")
    private String reactionTopic;
    @JsonProperty("sender")
    private String senderCommsId;
    @JsonProperty(DirectiveIntent.INTENT_KEY_SEQUENCE_ID)
    private long sequenceId;
    @JsonProperty("time")
    private String time;
    @NonNull
    @JsonProperty("type")
    private String type;

    public Message(String str, long j, String str2, String str3, String str4, MessagePayload messagePayload) {
        this.conversationId = str;
        this.messageId = j;
        this.time = str2;
        this.senderCommsId = str3;
        this.type = str4;
        this.payload = messagePayload;
        this.globalMessageId = null;
        this.parentGlobalMessageId = null;
        this.parentMessageType = null;
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
    public MessagePayload getPayload() {
        return this.payload;
    }

    @Nullable
    public String getPayloadNotificationText(@NonNull Context context) {
        MessagePayload payload = getPayload();
        if (payload != null) {
            return payload.getNotificationText(context);
        }
        return null;
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

    public void setPayload(@Nullable MessagePayload messagePayload) {
        this.payload = messagePayload;
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

    public Message(Message message) {
        this.conversationId = message.conversationId;
        this.senderCommsId = message.senderCommsId;
        this.messageId = message.messageId;
        this.clientMessageId = message.clientMessageId;
        this.payload = message.payload;
        this.time = message.time;
        this.type = message.type;
        this.sequenceId = message.sequenceId;
        this.globalMessageId = message.globalMessageId;
        this.parentGlobalMessageId = message.parentGlobalMessageId;
        this.parentMessageType = message.parentMessageType;
    }

    public Message() {
    }
}
