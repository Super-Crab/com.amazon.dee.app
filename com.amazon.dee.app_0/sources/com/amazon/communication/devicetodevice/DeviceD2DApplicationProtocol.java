package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class DeviceD2DApplicationProtocol extends D2DApplicationProtocol {
    private static final DPLogger log = new DPLogger("TComm.DeviceD2DApplicationProtocol");
    private final ChannelAwareD2DMessageRouter mMessageRouter;
    private final D2DNotificationRouter mNotificationRouter;

    public DeviceD2DApplicationProtocol(StreamCodec streamCodec, D2DNotificationRouter d2DNotificationRouter, ChannelAwareD2DMessageRouter channelAwareD2DMessageRouter) {
        super(streamCodec);
        this.mNotificationRouter = d2DNotificationRouter;
        this.mMessageRouter = channelAwareD2DMessageRouter;
    }

    private EndpointIdentity decodeEndpointIdentity(InputStream inputStream, int i) throws ProtocolException {
        try {
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(inputStream, i);
            if (!"".equals(decodeAsciiString)) {
                return EndpointIdentityFactory.createFromUrn(decodeAsciiString);
            }
            return null;
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    @Override // com.amazon.communication.devicetodevice.D2DApplicationProtocol
    public void decode(Message message, EndpointIdentity endpointIdentity) throws ProtocolException {
        log.debug("decode", "decoding message", "message", message, "source", EndpointIdentity.logSafe(endpointIdentity));
        try {
            InputStream payload = message.getPayload();
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
            log.debug("decode", "decoding message", "d2dMessageType", decodeAsciiString);
            if ("NOT".equals(decodeAsciiString)) {
                D2DMessage d2DMessage = new D2DMessage();
                d2DMessage.messageType = decodeAsciiString;
                d2DMessage.origin = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                if (d2DMessage.origin == null || d2DMessage.origin.equals("")) {
                    d2DMessage.origin = endpointIdentity;
                }
                d2DMessage.originApp = this.mStreamCodec.decodeAsciiString(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage.destination = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage.destinationApp = this.mStreamCodec.decodeAsciiString(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage.message = message.extractPayload();
                this.mNotificationRouter.routeNotificationMessage(d2DMessage);
            } else if (D2DApplicationProtocol.D2D_MESSAGE_WITH_CHANNEL_MESSAGE_TYPE.equals(decodeAsciiString)) {
                D2DMessage d2DMessage2 = new D2DMessage();
                d2DMessage2.messageType = decodeAsciiString;
                d2DMessage2.origin = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                if (d2DMessage2.origin == null || d2DMessage2.origin.equals("")) {
                    d2DMessage2.origin = endpointIdentity;
                }
                d2DMessage2.originApp = this.mStreamCodec.decodeAsciiString(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage2.destination = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage2.destinationApp = this.mStreamCodec.decodeAsciiString(payload, this.mStreamCodec.decodeInt(payload));
                d2DMessage2.channel = this.mStreamCodec.decodeInt(payload);
                d2DMessage2.message = message.extractPayload();
                this.mMessageRouter.routeMessage(d2DMessage2.origin, d2DMessage2.originApp, d2DMessage2.message, d2DMessage2.destinationApp, d2DMessage2.channel);
            } else {
                throw new ProtocolException("Unknown message type: " + decodeAsciiString);
            }
        } catch (Exception e) {
            throw new ProtocolException(e);
        }
    }
}
