package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.MessageRouter;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public class ChannelAwareD2DMessageRouterImpl implements ChannelAwareD2DMessageRouter {
    private static final DPLogger log = new DPLogger("TComm.ChannelAwareD2DMessageRouterImpl");
    private final MessageRouter mMessageRouter;
    private D2DNotificationRouter mNotificationRouter;

    public ChannelAwareD2DMessageRouterImpl(MessageRouter messageRouter, D2DNotificationRouter d2DNotificationRouter) {
        this.mMessageRouter = messageRouter;
        this.mNotificationRouter = d2DNotificationRouter;
    }

    private MessageHandler getMessageHandler(int i) {
        return this.mMessageRouter.getMessageHandler(i);
    }

    @Override // com.amazon.communication.devicetodevice.ChannelAwareD2DMessageRouter
    public void routeMessage(EndpointIdentity endpointIdentity, String str, Message message, String str2, int i) {
        MessageHandler messageHandler = getMessageHandler(i);
        if (messageHandler != null) {
            log.verbose("routeMessage", "routing message", "targetApp", str2, "handler", messageHandler);
            messageHandler.onMessage(endpointIdentity, message);
            return;
        }
        log.info("routeMessage", "no registered listener", "targetApp", str2, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
        this.mNotificationRouter.routeMessageAsNotification(endpointIdentity, str, message, str2, i);
    }
}
