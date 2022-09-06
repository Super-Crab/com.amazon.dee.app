package com.amazon.alexa.client.core.messages;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Message {
    private transient MessageMetadata messageMetadata;

    public static Message create(Header header) {
        return create(header, EmptyPayload.create());
    }

    public DialogRequestIdentifier getDialogRequestIdentifier() {
        return hasDialogRequestIdentifier() ? getHeader().getDialogRequestIdentifier() : DialogRequestIdentifier.NONE;
    }

    public abstract Header getHeader();

    public MessageIdentifier getMessageIdentifier() {
        return getHeader().getMessageIdentifier();
    }

    public MessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    public DialogRequestIdentifier getOriginatingDialogRequestIdentifier() {
        if (hasDialogRequestIdentifier()) {
            return getDialogRequestIdentifier();
        }
        return this.messageMetadata.getOriginatingDialogRequestIdentifier();
    }

    public abstract Payload getPayload();

    public boolean hasDialogRequestIdentifier() {
        return getHeader().hasDialogRequestIdentifier();
    }

    public void setMessageMetadata(MessageMetadata messageMetadata) {
        this.messageMetadata = messageMetadata;
    }

    public static Message create(Header header, Payload payload) {
        return create(header, payload, MessageMetadata.EMPTY);
    }

    public static Message create(Header header, Payload payload, MessageMetadata messageMetadata) {
        AutoValue_Message autoValue_Message = new AutoValue_Message(header, payload);
        ((Message) autoValue_Message).messageMetadata = messageMetadata;
        return autoValue_Message;
    }
}
