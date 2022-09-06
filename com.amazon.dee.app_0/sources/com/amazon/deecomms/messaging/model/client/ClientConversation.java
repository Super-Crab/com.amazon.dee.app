package com.amazon.deecomms.messaging.model.client;

import com.amazon.deecomms.messaging.model.Conversation;
import com.fasterxml.jackson.annotation.JsonIgnore;
/* loaded from: classes12.dex */
public class ClientConversation extends Conversation {
    @JsonIgnore
    private String lastMsgSender;
    @JsonIgnore
    private String lastMsgStr;
    @JsonIgnore
    private String lastMsgType;
    @JsonIgnore
    private long lastReadMsgId;
    @JsonIgnore
    private long lastSyncMsgId;
    @JsonIgnore
    private long readMsgLocallyOnly;
    @JsonIgnore
    private String recipientId;
    @JsonIgnore
    private String serverLastModifiedTime;
    @JsonIgnore
    private long serverLastMsgId;
    @JsonIgnore
    private long serverLastSequenceId;
    @JsonIgnore
    private long uniqueId;

    public String getLastMsgSender() {
        return this.lastMsgSender;
    }

    public String getLastMsgStr() {
        return this.lastMsgStr;
    }

    public String getLastMsgType() {
        return this.lastMsgType;
    }

    public long getLastReadMsgId() {
        return this.lastReadMsgId;
    }

    public long getLastSyncMsgId() {
        return this.lastSyncMsgId;
    }

    public long getReadMsgLocallyOnly() {
        return this.readMsgLocallyOnly;
    }

    public String getRecipientId() {
        return this.recipientId;
    }

    public String getServerLastModifiedTime() {
        return this.serverLastModifiedTime;
    }

    public long getServerLastMsgId() {
        return this.serverLastMsgId;
    }

    public long getServerLastSequenceId() {
        return this.serverLastSequenceId;
    }

    public long getUniqueId() {
        return this.uniqueId;
    }

    public void setLastMsgSender(String str) {
        this.lastMsgSender = str;
    }

    public void setLastMsgStr(String str) {
        this.lastMsgStr = str;
    }

    public void setLastMsgType(String str) {
        this.lastMsgType = str;
    }

    public void setLastReadMsgId(long j) {
        this.lastReadMsgId = j;
    }

    public void setLastSyncMsgId(long j) {
        this.lastSyncMsgId = j;
    }

    public void setReadMsgLocallyOnly(long j) {
        this.readMsgLocallyOnly = j;
    }

    public void setRecipientId(String str) {
        this.recipientId = str;
    }

    public void setServerLastModifiedTime(String str) {
        this.serverLastModifiedTime = str;
    }

    public void setServerLastMsgId(long j) {
        this.serverLastMsgId = j;
    }

    public void setServerLastSequenceId(long j) {
        this.serverLastSequenceId = j;
    }

    public void setUniqueId(long j) {
        this.uniqueId = j;
    }
}
