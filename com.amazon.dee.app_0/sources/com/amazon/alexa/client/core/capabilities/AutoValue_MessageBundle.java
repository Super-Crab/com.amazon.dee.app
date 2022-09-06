package com.amazon.alexa.client.core.capabilities;

import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_MessageBundle extends MessageBundle {
    private final MessageProcessingCallbacks callbacks;
    private final Message message;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MessageBundle(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (message != null) {
            this.message = message;
            if (messageProcessingCallbacks != null) {
                this.callbacks = messageProcessingCallbacks;
                return;
            }
            throw new NullPointerException("Null callbacks");
        }
        throw new NullPointerException("Null message");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageBundle)) {
            return false;
        }
        MessageBundle messageBundle = (MessageBundle) obj;
        return this.message.equals(messageBundle.getMessage()) && this.callbacks.equals(messageBundle.getCallbacks());
    }

    @Override // com.amazon.alexa.client.core.capabilities.MessageBundle
    public MessageProcessingCallbacks getCallbacks() {
        return this.callbacks;
    }

    @Override // com.amazon.alexa.client.core.capabilities.MessageBundle
    public Message getMessage() {
        return this.message;
    }

    public int hashCode() {
        return ((this.message.hashCode() ^ 1000003) * 1000003) ^ this.callbacks.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MessageBundle{message=");
        outline107.append(this.message);
        outline107.append(", callbacks=");
        outline107.append(this.callbacks);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
