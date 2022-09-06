package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.BufferedMessageManagerBase;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public class BufferedMessageToInputStreamResponseRouter extends BufferedMessageManagerBase {
    protected static final int SIZE_LIMIT_BEFORE_USING_INPUT_STREAM = 102400;
    private static final DPLogger log = new DPLogger("TComm.BufferedMessageToInputStreamResponseRouter");
    private final ResponseRouter mResponseRouter;

    public BufferedMessageToInputStreamResponseRouter(ResponseRouter responseRouter) {
        this.mResponseRouter = responseRouter;
    }

    @Override // com.amazon.communication.BufferedMessageManagerBase
    protected void handleCompletedMessage(EndpointIdentity endpointIdentity, BufferedMessageManagerBase.MessageEntry messageEntry) throws IllegalArgumentException {
        Message message;
        log.verbose("handleCompletedMessage", "received a completed message; dispatching to response router", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageEntry", messageEntry);
        FailFast.expectTrue(messageEntry.getChannel() != -1, "A completed message didn't have a channel specified.");
        Message message2 = messageEntry.getMessage();
        int payloadSize = message2.getPayloadSize();
        if (payloadSize > 102400) {
            log.verbose("handleCompletedMessage", "converting message to KnownSizeInputStreamMessage", "payloadSize", Integer.valueOf(payloadSize), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageEntry", messageEntry);
            message = new KnownSizeInputStreamMessage(message2.getPayload(), payloadSize);
        } else {
            log.verbose("handleCompletedMessage", "not converting message to KnownSizeInputStreamMessage", "payloadSize", Integer.valueOf(payloadSize), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageEntry", messageEntry);
            message = message2;
        }
        this.mResponseRouter.routeResponse(endpointIdentity, message, messageEntry.getChannel());
    }

    public void routeMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z, int i2) throws IllegalArgumentException {
        log.verbose("routeMessageFragment", "routing message fragment", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "moreToCome", Boolean.valueOf(z), "channel", Integer.valueOf(i2));
        if (i2 != -1) {
            handleMessageFragment(endpointIdentity, i, message, z, i2);
        } else {
            log.error("routeMessageFragment", "a message fragment had no channel specified", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message, "moreToCome", Boolean.valueOf(z), "channel", Integer.valueOf(i2));
            throw new IllegalArgumentException("The message fragment must have a valid channel specified");
        }
    }
}
