package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
/* loaded from: classes12.dex */
public class GatewayMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm.GatewayMessageHandler");
    private final GatewayApplicationProtocol mGatewayApplicationProtocol;

    public GatewayMessageHandler(GatewayApplicationProtocol gatewayApplicationProtocol) throws RegistrationFailedException {
        if (gatewayApplicationProtocol != null) {
            this.mGatewayApplicationProtocol = gatewayApplicationProtocol;
            return;
        }
        throw new IllegalArgumentException("Gateway protocol can't be null");
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.debug("onMessage", "message received", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "message", message);
        try {
            this.mGatewayApplicationProtocol.decode(message, endpointIdentity);
        } catch (ProtocolException e) {
            log.error("onMessage", "error occurred while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("onMessageFragment is not yet supported.");
    }
}
