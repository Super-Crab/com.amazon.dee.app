package com.amazon.deecomms.smsmessaging.model;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class MessagePayload {
    public String text;
    public MessageType type = MessageType.text;

    /* loaded from: classes12.dex */
    public enum MessageType {
        text
    }

    public MessagePayload(@NonNull String str) {
        this.text = str;
    }
}
