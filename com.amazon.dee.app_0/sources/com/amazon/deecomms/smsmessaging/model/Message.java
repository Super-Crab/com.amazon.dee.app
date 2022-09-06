package com.amazon.deecomms.smsmessaging.model;
/* loaded from: classes12.dex */
public class Message {
    public String id;
    public Long lastStatusChangedTime;
    public MessagePayload payload;
    public CommunicableEntity sender;
    public MessageStatus status;

    /* loaded from: classes12.dex */
    public enum MessageStatus {
        unread,
        read
    }
}
