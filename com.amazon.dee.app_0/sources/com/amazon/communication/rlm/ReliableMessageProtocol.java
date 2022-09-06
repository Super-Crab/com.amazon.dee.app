package com.amazon.communication.rlm;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.rlm.AckCodes;
import amazon.communication.rlm.NackErrorCodes;
import amazon.communication.rlm.PackCodes;
import amazon.communication.rlm.RlmCodes;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public abstract class ReliableMessageProtocol {
    public static final String ACK_MESSAGE_TYPE = "ACK";
    public static final String NACK_MESSAGE_TYPE = "NAK";
    public static final String PACK_MESSAGE_TYPE = "PAK";
    public static final String RELIABLE_MESSAGE_TYPE = "RLM";
    private static final int SIZE_OF_MESSAGE_TYPE = 3;
    private final int mSizeOfDelimiter;
    private final int mSizeOfInt;
    private final int mSizeOfLengthEncoding;
    private final int mSizeOfLong;
    private final StreamCodec mStreamCodec;
    private static final DPLogger log = new DPLogger("TComm.ReliableMessageProtocol");
    public static final Set<String> RELIABLE_MESSAGE_RESPONSE_TYPES = createResponseSet();

    public ReliableMessageProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
        this.mSizeOfInt = this.mStreamCodec.getSizeOfInt();
        this.mSizeOfLong = this.mStreamCodec.getSizeOfLong();
        this.mSizeOfDelimiter = this.mStreamCodec.getSizeOfDelimiter();
        this.mSizeOfLengthEncoding = this.mStreamCodec.getSizeOfLengthEncoding();
    }

    private String byteArrayToString(byte[] bArr) throws ProtocolException {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ProtocolException(e);
        }
    }

    private static Set<String> createResponseSet() {
        HashSet hashSet = new HashSet();
        hashSet.add(ACK_MESSAGE_TYPE);
        hashSet.add(NACK_MESSAGE_TYPE);
        hashSet.add(PACK_MESSAGE_TYPE);
        return hashSet;
    }

    private byte[] stringToByteArray(String str) throws ProtocolException {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ProtocolException(e);
        }
    }

    private void validate(ReliableMessage reliableMessage) {
        int i = reliableMessage.reliableMessageCode;
        Message message = reliableMessage.message;
        if (message != null) {
            if (message.getPayloadSize() != 0) {
                String str = reliableMessage.messageType;
                if (str != null) {
                    if (reliableMessage.clientIdentifier != null) {
                        if (str.equals(RELIABLE_MESSAGE_TYPE) && !RlmCodes.isRlmCode(i)) {
                            throw new IllegalArgumentException("RELIABLE_MESSAGE_TYPE but not an RlmCode");
                        }
                        if (reliableMessage.messageType.equals(ACK_MESSAGE_TYPE) && !AckCodes.isAckCode(i)) {
                            throw new IllegalArgumentException("ACK_MESSAGE_TYPE but not an AckCode");
                        }
                        if (reliableMessage.messageType.equals(NACK_MESSAGE_TYPE) && !NackErrorCodes.isNackErrorCode(i)) {
                            throw new IllegalArgumentException("NACK_MESSAGE_TYPE but not a NackErrorCode");
                        }
                        if (reliableMessage.messageType.equals(PACK_MESSAGE_TYPE) && !PackCodes.isPackCode(i)) {
                            throw new IllegalArgumentException("PACK_MESSAGE_TYPE but not a PackCode");
                        }
                        return;
                    }
                    throw new IllegalArgumentException("Client Identifier cannot be null");
                }
                throw new IllegalArgumentException("Message type cannot be null");
            }
            throw new IllegalArgumentException("Message cannot contain 0 bytes");
        }
        throw new IllegalArgumentException("Message cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Message createMessageFromString(String str) throws ProtocolException {
        byte[] stringToByteArray = stringToByteArray(str);
        int i = this.mSizeOfLengthEncoding;
        int i2 = this.mSizeOfDelimiter;
        ByteBuffer allocate = ByteBuffer.allocate(i + i2 + stringToByteArray.length + i2);
        try {
            this.mStreamCodec.encodeByteArray(stringToByteArray, new ByteBufferOutputStream(allocate));
            allocate.rewind();
            return MessageFactory.createMessage(allocate);
        } catch (CodecException e) {
            log.error("createMessageFromString", "CodecException should not be thrown as we are controlling header size and encoding", e);
            throw new ProtocolException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReliableMessage createResponseMessage(ReliableMessage reliableMessage, String str, Message message, int i) {
        ReliableMessage reliableMessage2 = new ReliableMessage();
        reliableMessage2.message = message;
        reliableMessage2.messageType = str;
        reliableMessage2.messageId = reliableMessage.messageId;
        reliableMessage2.channel = reliableMessage.channel;
        reliableMessage2.clientIdentifier = reliableMessage.clientIdentifier;
        reliableMessage2.reliableMessageCode = i;
        reliableMessage2.timeStart = reliableMessage.timeStart;
        return reliableMessage2;
    }

    public ReliableMessage decipher(Message message) throws ProtocolException {
        try {
            InputStream payload = message.getPayload();
            String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, 3);
            int decodeInt = this.mStreamCodec.decodeInt(payload);
            int decodeInt2 = this.mStreamCodec.decodeInt(payload);
            int decodeInt3 = this.mStreamCodec.decodeInt(payload);
            String byteArrayToString = byteArrayToString(this.mStreamCodec.decodeByteArray(payload));
            long decodeLong = this.mStreamCodec.decodeLong(payload);
            Message extractPayload = message.extractPayload();
            log.verbose("decipher", "deciphered the message", "message", message, "source", "reliableMessageType", decodeAsciiString, "messageId", Integer.valueOf(decodeInt), "channel", Integer.valueOf(decodeInt3), "clientIdentifier", byteArrayToString, "message", extractPayload);
            return new ReliableMessage(extractPayload, decodeAsciiString, decodeInt, decodeInt3, byteArrayToString, decodeInt2, decodeLong);
        } catch (CodecException e) {
            log.error("decode", "CodecException unable to decode received Message", e);
            throw new ProtocolException(e);
        }
    }

    public abstract void decode(Message message, EndpointIdentity endpointIdentity, int i) throws ProtocolException;

    /* JADX INFO: Access modifiers changed from: protected */
    public String decodeStringFromMessage(Message message) throws ProtocolException {
        try {
            String byteArrayToString = byteArrayToString(this.mStreamCodec.decodeByteArray(message.getPayload()));
            log.verbose("decodeStringFromMessage", "decoded the string", "message", message, "the String", byteArrayToString);
            return byteArrayToString;
        } catch (CodecException e) {
            log.error("decodeStringFromMessage", "CodecException unable to decode string from Message", e);
            throw new ProtocolException(e);
        }
    }

    public Message encode(ReliableMessage reliableMessage) throws ProtocolException {
        log.verbose("encode", "encoding a reliable message", "message type", reliableMessage.messageType, "messageId", Integer.valueOf(reliableMessage.messageId), "channel", Integer.valueOf(reliableMessage.channel), AuthorizationResponseParser.CLIENT_ID_STATE, reliableMessage.clientIdentifier, "ReliableMessageCode", Integer.valueOf(reliableMessage.reliableMessageCode));
        validate(reliableMessage);
        byte[] stringToByteArray = stringToByteArray(reliableMessage.clientIdentifier);
        int length = stringToByteArray.length;
        int i = this.mSizeOfDelimiter;
        int i2 = this.mSizeOfInt;
        ByteBuffer allocate = ByteBuffer.allocate(i + 3 + i2 + i + i2 + i + i2 + i + this.mSizeOfLengthEncoding + i + length + i + this.mSizeOfLong + i);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        try {
            this.mStreamCodec.encodeAsciiString(reliableMessage.messageType, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(reliableMessage.messageId, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(reliableMessage.reliableMessageCode, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(reliableMessage.channel, byteBufferOutputStream);
            this.mStreamCodec.encodeByteArray(stringToByteArray, byteBufferOutputStream);
            this.mStreamCodec.encodeLong(reliableMessage.timeStart, byteBufferOutputStream);
            allocate.rewind();
            reliableMessage.message.prependPayload(allocate);
            return reliableMessage.message;
        } catch (CodecException e) {
            log.error("encodeReliableMessage", "CodecException should not be thrown as we are controlling header size and encoding", e);
            throw new ProtocolException(e);
        }
    }

    public abstract void sendAckResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity) throws ProtocolException;

    public abstract void sendNackResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i, String str) throws ProtocolException;

    public abstract void sendPackResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i, String str) throws ProtocolException;
}
