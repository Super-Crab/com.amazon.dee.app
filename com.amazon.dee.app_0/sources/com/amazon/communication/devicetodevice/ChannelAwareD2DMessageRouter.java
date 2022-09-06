package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public interface ChannelAwareD2DMessageRouter {
    void routeMessage(EndpointIdentity endpointIdentity, String str, Message message, String str2, int i);
}
