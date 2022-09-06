package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public abstract class GatewayControlMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger();
    protected final GatewayControlProtocol mGatewayControlProtocol;

    public GatewayControlMessageHandler(GatewayControlProtocol gatewayControlProtocol) {
        this.mGatewayControlProtocol = gatewayControlProtocol;
    }

    protected abstract void onGatewayControlMessage(EndpointIdentity endpointIdentity, GatewayControlMessage gatewayControlMessage);

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.debug("onMessage", "Gateway control message received", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, endpointIdentity, "message", message);
        try {
            onGatewayControlMessage(endpointIdentity, this.mGatewayControlProtocol.decode(message));
        } catch (ProtocolException e) {
            log.error("onMessage", "error occurred while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("onMessageFragment is not supported for control messages.");
    }
}
