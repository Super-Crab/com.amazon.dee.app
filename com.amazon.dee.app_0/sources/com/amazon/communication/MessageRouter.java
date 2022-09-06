package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface MessageRouter {
    MessageHandler deregisterMessageHandler(int i);

    MessageHandler getMessageHandler(int i);

    void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException;

    void routeMessage(EndpointIdentity endpointIdentity, Message message, int i);

    void routeMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z, int i2);
}
