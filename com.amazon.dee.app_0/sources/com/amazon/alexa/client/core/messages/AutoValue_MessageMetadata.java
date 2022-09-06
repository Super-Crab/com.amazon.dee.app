package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_MessageMetadata extends MessageMetadata {
    private final DialogRequestIdentifier originatingDialogRequestIdentifier;
    private final String unparsedMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MessageMetadata(String str, DialogRequestIdentifier dialogRequestIdentifier) {
        if (str != null) {
            this.unparsedMessage = str;
            if (dialogRequestIdentifier != null) {
                this.originatingDialogRequestIdentifier = dialogRequestIdentifier;
                return;
            }
            throw new NullPointerException("Null originatingDialogRequestIdentifier");
        }
        throw new NullPointerException("Null unparsedMessage");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageMetadata)) {
            return false;
        }
        MessageMetadata messageMetadata = (MessageMetadata) obj;
        return this.unparsedMessage.equals(messageMetadata.getUnparsedMessage()) && this.originatingDialogRequestIdentifier.equals(messageMetadata.getOriginatingDialogRequestIdentifier());
    }

    @Override // com.amazon.alexa.client.core.messages.MessageMetadata
    public DialogRequestIdentifier getOriginatingDialogRequestIdentifier() {
        return this.originatingDialogRequestIdentifier;
    }

    @Override // com.amazon.alexa.client.core.messages.MessageMetadata
    public String getUnparsedMessage() {
        return this.unparsedMessage;
    }

    public int hashCode() {
        return ((this.unparsedMessage.hashCode() ^ 1000003) * 1000003) ^ this.originatingDialogRequestIdentifier.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MessageMetadata{unparsedMessage=");
        outline107.append(this.unparsedMessage);
        outline107.append(", originatingDialogRequestIdentifier=");
        outline107.append(this.originatingDialogRequestIdentifier);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
