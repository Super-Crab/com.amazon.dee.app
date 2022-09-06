package com.amazon.deecomms.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes12.dex */
public class Conversation {
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("firstVisibleMessageId")
    private long firstVisibleMessageId;
    @JsonProperty("lastModified")
    private String lastModifiedTimestamp;
    @JsonProperty("lastMessage")
    private Message lastMsg;
    @JsonProperty("lastMessageId")
    private long lastMsgId;
    @JsonProperty("readStatus")
    private Map<String, Integer> lastReadStatusMap;
    @JsonProperty("lastSequenceId")
    private long lastSequenceId;
    @JsonProperty("participants")
    private String[] participants;
    @JsonProperty("sendAsCommsId")
    private String sendAsCommsId;
    @JsonProperty("unreadMessages")
    private int unreadMsgCount;
    @JsonProperty("unreadNotifications")
    private int unreadNotificationCount;
    @JsonProperty("viewAsCommsId")
    private String viewAsCommsId;

    public String getConversationId() {
        return this.conversationId;
    }

    public long getFirstVisibleMessageId() {
        return this.firstVisibleMessageId;
    }

    public String getLastModifiedTimestamp() {
        return this.lastModifiedTimestamp;
    }

    public Message getLastMsg() {
        return this.lastMsg;
    }

    public long getLastMsgId() {
        return this.lastMsgId;
    }

    public Map<String, Integer> getLastReadStatusMap() {
        return this.lastReadStatusMap;
    }

    public long getLastSequenceId() {
        return this.lastSequenceId;
    }

    public String[] getParticipants() {
        return this.participants;
    }

    public String getSendAsCommsId() {
        return this.sendAsCommsId;
    }

    public int getUnreadMsgCount() {
        return this.unreadMsgCount;
    }

    public int getUnreadNotificationCount() {
        return this.unreadNotificationCount;
    }

    public String getViewAsCommsId() {
        return this.viewAsCommsId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setFirstVisibleMessageId(long j) {
        this.firstVisibleMessageId = j;
    }

    public void setLastModifiedTimestamp(String str) {
        this.lastModifiedTimestamp = str;
    }

    public void setLastMsg(Message message) {
        this.lastMsg = message;
    }

    public void setLastMsgId(long j) {
        this.lastMsgId = j;
    }

    public void setLastReadStatusMap(Map<String, Integer> map) {
        this.lastReadStatusMap = map;
    }

    public void setLastSequenceId(long j) {
        this.lastSequenceId = j;
    }

    public void setParticipants(String[] strArr) {
        this.participants = strArr;
    }

    public void setSendAsCommsId(String str) {
        this.sendAsCommsId = str;
    }

    public void setUnreadMsgCount(int i) {
        this.unreadMsgCount = i;
    }

    public void setUnreadNotificationCount(int i) {
        this.unreadNotificationCount = i;
    }

    public void setViewAsCommsId(String str) {
        this.viewAsCommsId = str;
    }
}
