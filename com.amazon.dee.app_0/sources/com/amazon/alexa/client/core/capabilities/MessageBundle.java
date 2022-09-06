package com.amazon.alexa.client.core.capabilities;

import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class MessageBundle {
    public static MessageBundle create(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        return new AutoValue_MessageBundle(message, messageProcessingCallbacks);
    }

    public abstract MessageProcessingCallbacks getCallbacks();

    public abstract Message getMessage();
}
