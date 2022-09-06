package com.amazon.communication.gmd;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class GmdProtocolCodec {
    private static final String GMD_MESSAGE_TYPE = "GMD";
    private static final int SIZE_OF_MESSAGE_TYPE = 3;
    private static final DPLogger log = new DPLogger("TComm.GmdProtocolCodec");
    private final int mDelimiterSize;
    protected final StreamCodec mStreamCodec;

    public GmdProtocolCodec(StreamCodec streamCodec) {
        if (streamCodec != null) {
            this.mStreamCodec = streamCodec;
            this.mDelimiterSize = streamCodec.getSizeOfDelimiter();
            return;
        }
        throw new IllegalArgumentException("Codec cannot be null");
    }

    public NewGmdMessage decodeMessage(Message message) throws ProtocolException {
        try {
            if (message != null) {
                InputStream payload = message.getPayload();
                String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
                if (decodeAsciiString.equals(GMD_MESSAGE_TYPE)) {
                    return new NewGmdMessage(EndpointIdentityFactory.createFromUrn(this.mStreamCodec.decodeString(payload)), this.mStreamCodec.decodeInt(payload), message.extractPayload());
                }
                throw new ProtocolException("Unknown message type received (" + decodeAsciiString + ")");
            }
            throw new IllegalArgumentException("Message cannot be null");
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    public ByteBuffer encodeMessage(EndpointIdentity endpointIdentity, int i, ByteBuffer byteBuffer) throws ProtocolException {
        if (byteBuffer != null && byteBuffer.remaining() != 0) {
            String endpointIdentity2 = endpointIdentity.toString();
            int sizeOfEncodedMaxInteger = this.mStreamCodec.getSizeOfEncodedMaxInteger() + endpointIdentity2.length() + this.mStreamCodec.getSizeOfEncodedMaxInteger() + this.mDelimiterSize + 3 + this.mDelimiterSize + this.mDelimiterSize + this.mDelimiterSize;
            int remaining = byteBuffer.remaining();
            ByteBuffer allocate = ByteBuffer.allocate(sizeOfEncodedMaxInteger + remaining);
            ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
            try {
                this.mStreamCodec.encodeAsciiString(GMD_MESSAGE_TYPE, byteBufferOutputStream);
                this.mStreamCodec.encodeString(endpointIdentity2, byteBufferOutputStream);
                this.mStreamCodec.encodeInt(i, byteBufferOutputStream);
                byteBuffer.mark();
                allocate.put(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), remaining);
                byteBuffer.reset();
                allocate.flip();
                return allocate;
            } catch (CodecException e) {
                throw new ProtocolException(e);
            }
        }
        throw new IllegalArgumentException("Payload cannot be null or contain 0-byte");
    }

    public String getProtocolName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GMD:");
        outline107.append(this.mStreamCodec.getName());
        return outline107.toString();
    }
}
