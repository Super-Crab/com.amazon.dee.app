package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_Message extends Message {
    private final Header header;
    private final Payload payload;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Message(Header header, Payload payload) {
        if (header != null) {
            this.header = header;
            if (payload != null) {
                this.payload = payload;
                return;
            }
            throw new NullPointerException("Null payload");
        }
        throw new NullPointerException("Null header");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return this.header.equals(message.getHeader()) && this.payload.equals(message.getPayload());
    }

    @Override // com.amazon.alexa.client.core.messages.Message
    public Header getHeader() {
        return this.header;
    }

    @Override // com.amazon.alexa.client.core.messages.Message
    public Payload getPayload() {
        return this.payload;
    }

    public int hashCode() {
        return ((this.header.hashCode() ^ 1000003) * 1000003) ^ this.payload.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Message{header=");
        outline107.append(this.header);
        outline107.append(", payload=");
        outline107.append(this.payload);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
