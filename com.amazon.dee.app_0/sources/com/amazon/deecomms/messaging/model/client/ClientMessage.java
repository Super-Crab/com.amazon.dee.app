package com.amazon.deecomms.messaging.model.client;

import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.payload.AudioMessagePayload;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.notifications.model.announcement.AudioAnnouncementPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
/* loaded from: classes12.dex */
public class ClientMessage extends Message {
    public static final int MESSAGE_MEDIA_SYNC_FAILED = 3;
    public static final int MESSAGE_SYNCED = 0;
    public static final int MESSAGE_SYNCING = 2;
    public static final int MESSAGE_SYNCING_FAILED = 1;
    @JsonIgnore
    private long clientID;
    @JsonIgnore
    private ClientMessageIdentifier clientMessageIdentifier;
    @JsonIgnore
    private long rawTimestamp = -1;
    @JsonIgnore
    private String recipientID;
    @JsonIgnore
    private boolean rightSideMessage;
    @JsonIgnore
    private String senderDisplayName;
    @JsonIgnore
    private int syncStatus;
    @JsonIgnore
    private long uniqueID;
    @JsonIgnore
    private String viewAsCommsId;

    public long getClientID() {
        return this.clientID;
    }

    public ClientMessageIdentifier getClientMessageIdentifier() {
        if (this.clientMessageIdentifier == null) {
            this.clientMessageIdentifier = new ClientMessageIdentifier(getMediaId(), getClientID(), getMessageId());
        }
        return this.clientMessageIdentifier;
    }

    public String getMediaId() {
        MessagePayload payload = getPayload();
        if (payload == null) {
            return null;
        }
        if ("announcement/audio".equalsIgnoreCase(getType())) {
            return ((AudioAnnouncementPayload) payload).getMediaId();
        }
        if (!"message/audio".equalsIgnoreCase(getType())) {
            return null;
        }
        return ((AudioMessagePayload) payload).getMediaId();
    }

    public long getRawTimestamp() {
        return this.rawTimestamp;
    }

    public String getRecipientID() {
        return this.recipientID;
    }

    public String getSenderDisplayName() {
        return this.senderDisplayName;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public long getUniqueID() {
        return this.uniqueID;
    }

    public String getViewAsCommsId() {
        return this.viewAsCommsId;
    }

    public boolean isRightSideMessage() {
        return this.rightSideMessage;
    }

    public void setClientID(long j) {
        this.clientID = j;
    }

    public void setRawTimestamp(long j) {
        this.rawTimestamp = j;
    }

    public void setRecipientID(String str) {
        this.recipientID = str;
    }

    public void setRightSideMessage(boolean z) {
        this.rightSideMessage = z;
    }

    public void setSenderDisplayName(String str) {
        this.senderDisplayName = str;
    }

    public void setSyncStatus(int i) {
        this.syncStatus = i;
    }

    public void setUniqueID(long j) {
        this.uniqueID = j;
    }

    public void setViewAsCommsId(String str) {
        this.viewAsCommsId = str;
    }
}
