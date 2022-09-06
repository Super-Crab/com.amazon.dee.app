package com.amazon.communication.rlm;

import amazon.communication.Message;
/* loaded from: classes12.dex */
public class ReliableMessage {
    public int channel;
    public String clientIdentifier;
    public Message message;
    public int messageId;
    public String messageType;
    public int reliableMessageCode;
    public long timeStart;

    public ReliableMessage(Message message, String str, int i, int i2, String str2, int i3, long j) {
        this.message = message;
        this.messageType = str;
        this.messageId = i;
        this.channel = i2;
        this.clientIdentifier = str2;
        this.reliableMessageCode = i3;
        this.timeStart = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReliableMessage)) {
            return false;
        }
        ReliableMessage reliableMessage = (ReliableMessage) obj;
        Message message = this.message;
        if (message == null) {
            if (reliableMessage.message != null) {
                return false;
            }
        } else if (!message.equals(reliableMessage.message)) {
            return false;
        }
        String str = this.clientIdentifier;
        if (str == null) {
            if (reliableMessage.clientIdentifier != null) {
                return false;
            }
        } else if (!str.equals(reliableMessage.clientIdentifier)) {
            return false;
        }
        String str2 = this.messageType;
        if (str2 == null) {
            if (reliableMessage.messageType != null) {
                return false;
            }
        } else if (!str2.equals(reliableMessage.messageType)) {
            return false;
        }
        return this.messageId == reliableMessage.messageId && this.channel == reliableMessage.channel && this.timeStart == reliableMessage.timeStart && this.reliableMessageCode == reliableMessage.reliableMessageCode;
    }

    public int hashCode() {
        Message message = this.message;
        int i = 0;
        int hashCode = ((message == null ? 0 : message.hashCode()) + 31) * 31;
        String str = this.messageType;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.messageId) * 31) + this.channel) * 31;
        String str2 = this.clientIdentifier;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((((hashCode2 + i) * 31) + this.reliableMessageCode) * 31) + ((int) this.timeStart);
    }

    public ReliableMessage() {
    }
}
