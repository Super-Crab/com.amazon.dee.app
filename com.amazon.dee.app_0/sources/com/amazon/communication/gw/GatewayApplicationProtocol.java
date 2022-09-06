package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public abstract class GatewayApplicationProtocol {
    private static final String GATEWAY_MESSAGE_TYPE = "GWM";
    private static final int SIZE_OF_MESSAGE_TYPE = 3;
    private static final DPLogger log = new DPLogger("TComm.GatewayApplicationProtocol");
    private final int mDelimiterSize;
    private final StreamCodec mStreamCodec;

    public GatewayApplicationProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
        this.mDelimiterSize = streamCodec.getSizeOfDelimiter();
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

    private void validate(GatewayMessage gatewayMessage) {
        if (gatewayMessage.destination != null) {
            if (gatewayMessage.messageType != null) {
                Message message = gatewayMessage.message;
                if (message != null && message.getPayloadSize() != 0) {
                    return;
                }
                throw new IllegalArgumentException("Message cannot be null or contain 0 byte");
            }
            throw new IllegalArgumentException("Message type cannot be null");
        }
        throw new IllegalArgumentException("Destination cannot be null");
    }

    public void decode(Message message, EndpointIdentity endpointIdentity) throws ProtocolException {
        log.debug("decode", "decoding message", "message", message, "source", EndpointIdentity.logSafe(endpointIdentity));
        try {
            InputStream payload = message.getPayload();
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
            if (GATEWAY_MESSAGE_TYPE.equals(decodeAsciiString)) {
                GatewayMessage gatewayMessage = new GatewayMessage();
                gatewayMessage.messageType = this.mStreamCodec.decodeAsciiString(payload, 3);
                gatewayMessage.channel = this.mStreamCodec.decodeInt(payload);
                gatewayMessage.origin = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                gatewayMessage.destination = decodeEndpointIdentity(payload, this.mStreamCodec.decodeInt(payload));
                gatewayMessage.message = message.extractPayload();
                log.debug("decode", "decoded message", "source", EndpointIdentity.logSafe(endpointIdentity), "dest", EndpointIdentity.logSafe(gatewayMessage.destination), "channel", Integer.valueOf(gatewayMessage.channel));
                handleGatewayMessage(gatewayMessage, endpointIdentity);
                return;
            }
            throw new ProtocolException("Unknown message type: " + decodeAsciiString);
        } catch (Exception e) {
            throw new ProtocolException(e);
        }
    }

    public Message encode(GatewayMessage gatewayMessage) {
        int sizeOfEncodedMaxInteger = this.mStreamCodec.getSizeOfEncodedMaxInteger();
        int i = this.mDelimiterSize;
        int i2 = i + 3 + 3 + i + sizeOfEncodedMaxInteger + i + sizeOfEncodedMaxInteger + i + i + sizeOfEncodedMaxInteger + i + i;
        validate(gatewayMessage);
        EndpointIdentity endpointIdentity = gatewayMessage.origin;
        String endpointIdentity2 = endpointIdentity == null ? "" : endpointIdentity.toString();
        String endpointIdentity3 = gatewayMessage.destination.toString();
        ByteBuffer allocate = ByteBuffer.allocate(endpointIdentity3.length() + endpointIdentity2.length() + i2);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        try {
            this.mStreamCodec.encodeAsciiString(GATEWAY_MESSAGE_TYPE, byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(gatewayMessage.messageType, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(gatewayMessage.channel, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(endpointIdentity2.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(endpointIdentity2, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(endpointIdentity3.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(endpointIdentity3, byteBufferOutputStream);
        } catch (CodecException e) {
            log.error("encode", "CodecException should not be thrown as we are controling header size and encoding", e);
        }
        allocate.rewind();
        gatewayMessage.message.prependPayload(allocate);
        return gatewayMessage.message;
    }

    protected abstract void handleGatewayMessage(GatewayMessage gatewayMessage, EndpointIdentity endpointIdentity);
}
