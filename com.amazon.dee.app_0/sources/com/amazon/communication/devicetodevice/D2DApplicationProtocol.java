package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public abstract class D2DApplicationProtocol {
    protected static final String D2D_MESSAGE_MESSAGE_TYPE = "DDM";
    public static final String D2D_MESSAGE_WITH_CHANNEL_MESSAGE_TYPE = "DMC";
    protected static final String D2D_NOTIFICATION_MESSAGE_TYPE = "NOT";
    protected static final int SIZE_OF_MESSAGE_TYPE = 3;
    private static final DPLogger log = new DPLogger("TComm.D2DApplicationProtocol");
    protected final int mDelimiterSize;
    protected final StreamCodec mStreamCodec;

    public D2DApplicationProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
        this.mDelimiterSize = streamCodec.getSizeOfDelimiter();
    }

    private void validate(D2DMessage d2DMessage) {
        if (d2DMessage.destination != null) {
            if (d2DMessage.messageType != null) {
                if (d2DMessage.message == null) {
                    throw new IllegalArgumentException("Message cannot be null");
                }
                return;
            }
            throw new IllegalArgumentException("Message type cannot be null");
        }
        throw new IllegalArgumentException("Destination cannot be null");
    }

    public abstract void decode(Message message, EndpointIdentity endpointIdentity) throws ProtocolException;

    public Message encode(D2DMessage d2DMessage) {
        int sizeOfEncodedMaxInteger = this.mStreamCodec.getSizeOfEncodedMaxInteger();
        int i = this.mDelimiterSize;
        int i2 = i + 3 + sizeOfEncodedMaxInteger + i + i + sizeOfEncodedMaxInteger + i + i + sizeOfEncodedMaxInteger + i + i + sizeOfEncodedMaxInteger + i + i + sizeOfEncodedMaxInteger + i;
        validate(d2DMessage);
        EndpointIdentity endpointIdentity = d2DMessage.origin;
        String endpointIdentity2 = endpointIdentity == null ? "" : endpointIdentity.toString();
        String str = d2DMessage.originApp;
        String endpointIdentity3 = d2DMessage.destination.toString();
        String str2 = d2DMessage.destinationApp;
        int i3 = d2DMessage.messageType.equals(D2D_MESSAGE_WITH_CHANNEL_MESSAGE_TYPE) ? d2DMessage.channel : -1;
        ByteBuffer allocate = ByteBuffer.allocate(str2.length() + endpointIdentity3.length() + str.length() + endpointIdentity2.length() + i2);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        try {
            this.mStreamCodec.encodeAsciiString(d2DMessage.messageType, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(endpointIdentity2.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(endpointIdentity2, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(str.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(str, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(endpointIdentity3.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(endpointIdentity3, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(str2.length(), byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(str2, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i3, byteBufferOutputStream);
        } catch (CodecException e) {
            log.error("encode", "CodecException should not be thrown as we are controling header size and encoding", e);
        }
        allocate.rewind();
        d2DMessage.message.prependPayload(allocate);
        return d2DMessage.message;
    }
}
