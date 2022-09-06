package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface UndeliverableMessageHandler {
    void onMessage(EndpointIdentity endpointIdentity, Message message);
}
