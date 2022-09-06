package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEventPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class SharingMessagePayload implements MessagePayload {
    public static final String TYPE = "message/shared-content";
    private String parentMessageType;
    @JsonProperty("sharingMessage")
    private GenericSharingMessageEventPayload sharingMessage;

    public SharingMessagePayload() {
        this(null);
    }

    public void extractMetadataFromMessage(Message message) {
        setParentMessageType(message.getParentMessageType());
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(@NonNull Context context) {
        return getSummaryText(context);
    }

    public String getParentMessageType() {
        return this.parentMessageType;
    }

    public GenericSharingMessageEventPayload getSharingMessage() {
        return this.sharingMessage;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(@NonNull Context context) {
        GenericSharingMessageEventPayload genericSharingMessageEventPayload = this.sharingMessage;
        if (genericSharingMessageEventPayload == null) {
            return context.getString(R.string.notification_text_new_shared_message);
        }
        String messageType = genericSharingMessageEventPayload.getMessageType();
        if ("reaction/default".equals(messageType)) {
            if ("alexa-music/song".equals(this.parentMessageType)) {
                return context.getString(R.string.notification_text_new_shared_song_reaction);
            }
            return context.getString(R.string.notification_text_new_shared_reaction);
        } else if ("alexa-music/song".equals(messageType)) {
            return context.getString(R.string.notification_text_new_shared_song_message);
        } else {
            if ("alexa-shopping/shoppingList".equals(messageType)) {
                return context.getString(R.string.notification_text_new_shared_shoppingList_message);
            }
            return context.getString(R.string.notification_text_new_shared_message);
        }
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "message/shared-content";
    }

    public void setParentMessageType(String str) {
        this.parentMessageType = str;
    }

    public void setSharingMessage(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
    }

    public SharingMessagePayload(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
        this.parentMessageType = null;
    }
}
