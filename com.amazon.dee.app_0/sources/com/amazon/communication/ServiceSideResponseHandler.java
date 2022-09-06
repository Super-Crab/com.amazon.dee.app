package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface ServiceSideResponseHandler {
    void onResponse(EndpointIdentity endpointIdentity, Message message, int i);
}
