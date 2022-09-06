package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
/* loaded from: classes12.dex */
public interface D2DNotificationRouter {
    void routeMessageAsNotification(EndpointIdentity endpointIdentity, String str, Message message, String str2, int i);

    void routeNotificationMessage(D2DMessage d2DMessage) throws ProtocolException;
}
