package com.amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class MessageIdentity {
    protected final EndpointIdentity mEndpoint;
    private final int mMessageId;

    public MessageIdentity(EndpointIdentity endpointIdentity, int i) {
        this.mEndpoint = endpointIdentity;
        this.mMessageId = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageIdentity messageIdentity = (MessageIdentity) obj;
        return this.mMessageId == messageIdentity.mMessageId && this.mEndpoint.equals(messageIdentity.mEndpoint);
    }

    public int hashCode() {
        return this.mEndpoint.hashCode() + this.mMessageId;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MessageIdentity [endpoint=");
        outline107.append(EndpointIdentity.logSafe(this.mEndpoint));
        outline107.append(", messageId=");
        return GeneratedOutlineSupport1.outline86(outline107, this.mMessageId, "]");
    }
}
