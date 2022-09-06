package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.serialize.ObjectMapper;
import amazon.communication.serialize.ObjectMapperFactory;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class GatewayControlProtocol {
    public static final String GATEWAY_CTRL_MESSAGE_TYPE = "CTL";
    private static final int SIZE_OF_MESSAGE_TYPE = 3;
    private static final DPLogger log = new DPLogger("TComm.GatewayControlProtocol");
    private final ObjectMapper mJsonObjectMapper = ObjectMapperFactory.newObjectMapper(ObjectMapperFactory.ContentType.JSON);
    private final StreamCodec mStreamCodec;

    public GatewayControlProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
    }

    public GatewayControlMessage decode(Message message) throws ProtocolException {
        try {
            InputStream payload = message.getPayload();
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
            if (GATEWAY_CTRL_MESSAGE_TYPE.equals(decodeAsciiString)) {
                try {
                    return (GatewayControlMessage) this.mJsonObjectMapper.deserialize(payload, GatewayControlMessage.class);
                } catch (IOException e) {
                    throw new ProtocolException(e);
                }
            }
            throw new ProtocolException("Unknown message type: " + decodeAsciiString);
        } catch (Exception e2) {
            throw new ProtocolException(e2);
        }
    }

    public Message encode(GatewayControlMessage gatewayControlMessage) {
        Message createMessage = MessageFactory.createMessage(this.mJsonObjectMapper.serialize(gatewayControlMessage));
        ByteBuffer allocate = ByteBuffer.allocate(this.mStreamCodec.getSizeOfDelimiter() + GATEWAY_CTRL_MESSAGE_TYPE.getBytes().length);
        try {
            this.mStreamCodec.encodeAsciiString(GATEWAY_CTRL_MESSAGE_TYPE, new ByteBufferOutputStream(allocate));
            allocate.rewind();
            createMessage.prependPayload(allocate);
        } catch (CodecException e) {
            log.error("encode", "CodecException should not be thrown as we are controling header size and encoding", e);
        }
        return createMessage;
    }
}
