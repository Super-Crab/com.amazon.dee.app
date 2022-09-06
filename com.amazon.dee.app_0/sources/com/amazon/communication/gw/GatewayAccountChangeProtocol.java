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
public class GatewayAccountChangeProtocol {
    public static final String GATEWAY_ACCOUNT_CHANGE_MESSAGE_TYPE = "GAC";
    private static final int SIZE_OF_MESSAGE_TYPE = 3;
    private static final DPLogger log = new DPLogger("TComm.GatewayAccountChangeProtocol");
    private final ObjectMapper mJsonObjectMapper = ObjectMapperFactory.newObjectMapper(ObjectMapperFactory.ContentType.JSON);
    private final StreamCodec mStreamCodec;

    public GatewayAccountChangeProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
    }

    public GatewayAccountChangeMessage decode(Message message) throws ProtocolException {
        try {
            InputStream payload = message.getPayload();
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
            if (GATEWAY_ACCOUNT_CHANGE_MESSAGE_TYPE.equals(decodeAsciiString)) {
                try {
                    return (GatewayAccountChangeMessage) this.mJsonObjectMapper.deserialize(payload, GatewayAccountChangeMessage.class);
                } catch (IOException e) {
                    throw new ProtocolException(e);
                }
            }
            throw new ProtocolException("Unknown message type: " + decodeAsciiString);
        } catch (Exception e2) {
            throw new ProtocolException(e2);
        }
    }

    public Message encode(GatewayAccountChangeMessage gatewayAccountChangeMessage) {
        Message createMessage = MessageFactory.createMessage(this.mJsonObjectMapper.serialize(gatewayAccountChangeMessage));
        ByteBuffer allocate = ByteBuffer.allocate(this.mStreamCodec.getSizeOfDelimiter() + GATEWAY_ACCOUNT_CHANGE_MESSAGE_TYPE.getBytes().length);
        try {
            this.mStreamCodec.encodeAsciiString(GATEWAY_ACCOUNT_CHANGE_MESSAGE_TYPE, new ByteBufferOutputStream(allocate));
            allocate.rewind();
            createMessage.prependPayload(allocate);
        } catch (CodecException e) {
            log.error("encode", "ProtocolException should not be thrown as we are controling header size and encoding", e);
        }
        return createMessage;
    }
}
