package com.amazon.communication.gw;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.MessageRouter;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.ResponseRouter;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.StreamCodec;
/* loaded from: classes12.dex */
public class DeviceGatewayApplicationProtocol extends GatewayApplicationProtocol {
    private static final DPLogger log = new DPLogger("TComm.DeviceGatewayApplicationProtocol");
    private final MessageRouter mMessageRouter;
    private final ResponseRouter mResponseRouter;

    public DeviceGatewayApplicationProtocol(StreamCodec streamCodec, MessageRouter messageRouter, ResponseRouter responseRouter) {
        super(streamCodec);
        this.mMessageRouter = messageRouter;
        this.mResponseRouter = responseRouter;
    }

    @Override // com.amazon.communication.gw.GatewayApplicationProtocol
    public void handleGatewayMessage(GatewayMessage gatewayMessage, EndpointIdentity endpointIdentity) {
        String str = gatewayMessage.messageType;
        if (ProtocolHandler.MESSAGE_MESSAGE_TYPE.equals(str)) {
            this.mMessageRouter.routeMessage(gatewayMessage.origin, gatewayMessage.message, gatewayMessage.channel);
        } else if (ProtocolHandler.RESPONSE_MESSAGE_TYPE.equals(str)) {
            this.mResponseRouter.routeResponse(gatewayMessage.origin, gatewayMessage.message, gatewayMessage.channel);
        } else {
            log.warn("handleGatewayMessage", "Unsupported message type received", "messageType", str);
        }
    }
}
