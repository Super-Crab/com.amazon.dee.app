package com.amazon.communication.heartbeat;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import com.amazon.communication.NetworkType;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.heartbeat.HeartbeatControlMessage;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class HeartbeatControlApplicationProtocol {
    public static final String HEARTBEAT_CONTROL_MESSAGE_TYPE = "HBCTL";
    public static final int PROTOCOL_VERSION = 1;
    private final StreamCodec mStreamCodec;
    private static final DPLogger log = new DPLogger("TComm.HeartbeatControlApplicationProtocol");
    public static final int SIZE_OF_MESSAGE_TYPE = 5;

    /* renamed from: com.amazon.communication.heartbeat.HeartbeatControlApplicationProtocol$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$heartbeat$HeartbeatControlMessage$Type = new int[HeartbeatControlMessage.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$heartbeat$HeartbeatControlMessage$Type[HeartbeatControlMessage.Type.TriggerLearning.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public HeartbeatControlApplicationProtocol(StreamCodec streamCodec) {
        this.mStreamCodec = streamCodec;
    }

    private TriggerLearningCommand decodeTriggerLearningCommand(InputStream inputStream) throws CodecException {
        return new TriggerLearningCommand(NetworkType.valueOf(this.mStreamCodec.decodeString(inputStream)), this.mStreamCodec.decodeString(inputStream), this.mStreamCodec.decodeLong(inputStream), this.mStreamCodec.decodeLong(inputStream));
    }

    private Message encodeTriggerLearningCommand(TriggerLearningCommand triggerLearningCommand) throws CodecException {
        int sizeOfInt = this.mStreamCodec.getSizeOfInt();
        int sizeOfLong = this.mStreamCodec.getSizeOfLong();
        ByteBuffer allocate = ByteBuffer.allocate((this.mStreamCodec.getSizeOfDelimiter() * 6) + triggerLearningCommand.getNetworkIdentifier().length() + triggerLearningCommand.getNetworkType().toString().length() + sizeOfInt + sizeOfInt + sizeOfLong + sizeOfLong);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        this.mStreamCodec.encodeString(triggerLearningCommand.getNetworkType().toString(), byteBufferOutputStream);
        this.mStreamCodec.encodeString(triggerLearningCommand.getNetworkIdentifier(), byteBufferOutputStream);
        this.mStreamCodec.encodeLong(triggerLearningCommand.getLowerBoundMillis(), byteBufferOutputStream);
        this.mStreamCodec.encodeLong(triggerLearningCommand.getUpperBoundMillis(), byteBufferOutputStream);
        allocate.rewind();
        return MessageFactory.createMessage(allocate);
    }

    public HeartbeatControlMessage decode(Message message) throws ProtocolException {
        try {
            InputStream payload = message.getPayload();
            int decodeInt = this.mStreamCodec.decodeInt(payload);
            if (decodeInt == 1) {
                String decodeAsciiString = this.mStreamCodec.decodeAsciiString(payload, SIZE_OF_MESSAGE_TYPE);
                if (HEARTBEAT_CONTROL_MESSAGE_TYPE.equals(decodeAsciiString)) {
                    HeartbeatControlMessage.Type forMessageType = HeartbeatControlMessage.Type.forMessageType(this.mStreamCodec.decodeAsciiString(payload, 3));
                    if (forMessageType.ordinal() == 0) {
                        return decodeTriggerLearningCommand(payload);
                    }
                    throw new ProtocolException("Unknown control message type: " + forMessageType);
                }
                throw new ProtocolException("Unexpected message type: " + decodeAsciiString);
            }
            throw new ProtocolException("Unknown protocol version: " + decodeInt);
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    public Message encode(HeartbeatControlMessage heartbeatControlMessage) throws ProtocolException {
        ByteBuffer allocate = ByteBuffer.allocate((this.mStreamCodec.getSizeOfDelimiter() * 3) + heartbeatControlMessage.getType().getMessageType().length() + this.mStreamCodec.getSizeOfInt() + 5);
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
        try {
            this.mStreamCodec.encodeInt(1, byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(HEARTBEAT_CONTROL_MESSAGE_TYPE, byteBufferOutputStream);
            this.mStreamCodec.encodeAsciiString(heartbeatControlMessage.getType().getMessageType(), byteBufferOutputStream);
            allocate.rewind();
            try {
                if (heartbeatControlMessage.getType().ordinal() == 0) {
                    Message encodeTriggerLearningCommand = encodeTriggerLearningCommand((TriggerLearningCommand) heartbeatControlMessage);
                    encodeTriggerLearningCommand.prependPayload(allocate);
                    return encodeTriggerLearningCommand;
                }
                throw new IllegalArgumentException("Unknown heartbeat control message type passed for encoding: " + heartbeatControlMessage.getType());
            } catch (CodecException e) {
                throw new ProtocolException(e);
            }
        } catch (CodecException e2) {
            throw new ProtocolException(e2);
        }
    }
}
