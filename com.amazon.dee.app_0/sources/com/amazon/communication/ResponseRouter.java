package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface ResponseRouter {
    int registerResponseHandler(ServiceSideResponseHandler serviceSideResponseHandler);

    void routeResponse(EndpointIdentity endpointIdentity, Message message, int i);

    void unregisterResponseHandler(int i);
}
