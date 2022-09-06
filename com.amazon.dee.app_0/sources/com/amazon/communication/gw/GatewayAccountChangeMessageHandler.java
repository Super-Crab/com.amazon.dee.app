package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public class GatewayAccountChangeMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger();
    protected final GatewayAccountChangeProtocol mGatewayAccountChangeProtocol;

    public GatewayAccountChangeMessageHandler(GatewayAccountChangeProtocol gatewayAccountChangeProtocol) {
        this.mGatewayAccountChangeProtocol = gatewayAccountChangeProtocol;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.info("onMessage", "Gateway account message received", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, endpointIdentity, "message", message);
        try {
            this.mGatewayAccountChangeProtocol.decode(message);
        } catch (ProtocolException e) {
            log.error("onMessage", "error occurred while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("onMessageFragment is not supported for control messages.");
    }
}
