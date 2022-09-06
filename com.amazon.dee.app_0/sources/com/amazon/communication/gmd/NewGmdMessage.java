package com.amazon.communication.gmd;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public class NewGmdMessage {
    private final Message mBody;
    private final int mChannel;
    private final EndpointIdentity mEndpoint;

    public NewGmdMessage(EndpointIdentity endpointIdentity, int i, Message message) {
        this.mEndpoint = endpointIdentity;
        this.mChannel = i;
        this.mBody = message;
    }

    public Message getBody() {
        return this.mBody;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public EndpointIdentity getEndpoint() {
        return this.mEndpoint;
    }

    public String toString() {
        return String.format("GmdMessage channel: %d", Integer.valueOf(this.mChannel));
    }
}
