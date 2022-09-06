package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class TuningProtocolHandler extends ProtocolHandlerBase {
    private final ByteBufferChainHandler mByteBufferChainHandler;
    private final int mDelimiterSize;
    protected final ByteBufferChainHandlerNotificationSink mNotificationSink;
    private final ProtocolSocket mSocket;
    private static final DPLogger log = new DPLogger("TComm.TuningProtocolHandler");
    private static final byte[] FOOTER = {84, 85, 78, 69};
    private static final int FOOTER_SIZE = FOOTER.length;

    public TuningProtocolHandler(final ProtocolSocket protocolSocket, StreamCodec streamCodec, ByteBufferChainHandler byteBufferChainHandler) {
        super(FOOTER, streamCodec);
        if (protocolSocket != null) {
            this.mDelimiterSize = streamCodec.getSizeOfDelimiter();
            this.mSocket = protocolSocket;
            this.mByteBufferChainHandler = byteBufferChainHandler;
            this.mNotificationSink = new ByteBufferChainHandlerNotificationSink() { // from class: com.amazon.communication.TuningProtocolHandler.1
                @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
                public void chainHandled(ByteBufferChain byteBufferChain) {
                    TuningProtocolHandler.log.verbose("chainHandled", "finished", "socket", protocolSocket);
                }

                @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
                public void chainRejected(ByteBufferChain byteBufferChain, boolean z) {
                    TuningProtocolHandler.log.info("chainRejected", "chain rejected during tuning", "socket", protocolSocket);
                    TuningProtocolHandler.this.mSocket.close(new CloseDetail(CloseStatusCodes.TUNING_FAILED, "Chain rejected during tuning"));
                }

                @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
                public void okToResubmitRejectedChain(ByteBufferChain byteBufferChain) {
                    TuningProtocolHandler.log.warn("okToResubmitRejectedChain", "ready to resubmit rejected chain, but rejected chains shouldn't happen during tuning", "socket", protocolSocket);
                    TuningProtocolHandler.this.mSocket.close(new CloseDetail(CloseStatusCodes.TUNING_FAILED, "Chain rejected during tuning"));
                }
            };
            return;
        }
        throw new IllegalArgumentException("ProtocolSocket can't be null for TuningProtocolHandler");
    }

    private void onMessageReceived(ByteBufferBackedMessage byteBufferBackedMessage) throws TuningFailedException {
        ProtocolSocket protocolSocket = this.mSocket;
        if (protocolSocket != null) {
            protocolSocket.verifyTuningResult(byteBufferBackedMessage);
        }
    }

    @Override // com.amazon.communication.ProtocolHandler
    public void decodeMessage(EndpointIdentity endpointIdentity, ByteBufferBackedMessage byteBufferBackedMessage) throws CommunicationBaseException {
        ByteBufferChain byteBufferChain = byteBufferBackedMessage.getByteBufferChain();
        byteBufferChain.mark();
        InputStream inputStream = byteBufferBackedMessage.getInputStream();
        try {
            try {
                int available = inputStream.available();
                int decodeInt = this.mStreamCodec.decodeInt(inputStream);
                int available2 = available - inputStream.available();
                byteBufferChain.savePositions();
                byteBufferChain.reset();
                int computeDigest = RFC1071LikeDigest.computeDigest(byteBufferChain.getByteBuffers(), 0, available2);
                byteBufferChain.loadSavedPositions();
                if (decodeInt == computeDigest) {
                    int sizeOfEncodedMaxInteger = this.mStreamCodec.getSizeOfEncodedMaxInteger();
                    int i = this.mDelimiterSize + sizeOfEncodedMaxInteger + sizeOfEncodedMaxInteger + this.mDelimiterSize;
                    int decodeInt2 = this.mStreamCodec.decodeInt(inputStream);
                    if (inputStream.available() == decodeInt2 - i) {
                        byteBufferChain.mark();
                        byteBufferChain.skipBytes(inputStream.available() - FOOTER_SIZE);
                        if (confirmFooter(byteBufferChain.getInputStream())) {
                            byteBufferChain.reset();
                            byteBufferChain.shrinkLimitFromEnd(FOOTER_SIZE);
                            onMessageReceived(byteBufferBackedMessage);
                            try {
                                inputStream.close();
                                return;
                            } catch (IOException e) {
                                log.error("decodeMessage", "IOException when closing message inputstream", e);
                                return;
                            }
                        }
                        throw new ProtocolException("Incorrect footer.");
                    }
                    throw new ProtocolException("Unexpected size of message. size: " + decodeInt2);
                }
                throw new ProtocolException("checksum mismatch. Expected: " + decodeInt + ", Actual: " + computeDigest);
            } catch (CodecException e2) {
                throw new ProtocolException(e2);
            } catch (IOException e3) {
                throw new ProtocolException(e3);
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                log.error("decodeMessage", "IOException when closing message inputstream", e4);
            }
            throw th;
        }
    }

    @Override // com.amazon.communication.ProtocolHandler
    public void encodeMessage(Message message, String str, int i) throws ProtocolException, IOException {
        FailFast.expectTrue(message.getPayloadSize() > 0, "Tuning messages must know their total size");
        try {
            int sizeOfEncodedMaxInteger = this.mStreamCodec.getSizeOfEncodedMaxInteger();
            int i2 = this.mDelimiterSize + sizeOfEncodedMaxInteger + sizeOfEncodedMaxInteger + this.mDelimiterSize;
            ByteBuffer allocate = ByteBuffer.allocate(i2);
            ByteBuffer allocate2 = ByteBuffer.allocate(FOOTER_SIZE);
            ByteBufferChainMessageImpl byteBufferChainMessageImpl = new ByteBufferChainMessageImpl(allocate);
            byteBufferChainMessageImpl.appendPayload(message);
            byteBufferChainMessageImpl.appendPayload(allocate2);
            ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
            int payloadSize = i2 + message.getPayloadSize() + FOOTER_SIZE;
            this.mStreamCodec.encodeInt(0, byteBufferOutputStream);
            int position = allocate.position();
            this.mStreamCodec.encodeInt(payloadSize, byteBufferOutputStream);
            allocate.rewind();
            encodeFooter(new ByteBufferOutputStream(allocate2));
            allocate2.rewind();
            int computeDigest = RFC1071LikeDigest.computeDigest(byteBufferChainMessageImpl.getByteBuffers(), 0, position);
            allocate.position(0);
            this.mStreamCodec.encodeInt(computeDigest, byteBufferOutputStream);
            allocate.rewind();
            log.verbose("encodeMessage", "sending encoded message to ByteBufferChainHandler", new Object[0]);
            this.mByteBufferChainHandler.onByteBufferChain(byteBufferChainMessageImpl.getByteBufferChain(), this.mNotificationSink);
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    @Override // com.amazon.communication.ProtocolHandler
    public Encoding getEncodingType() {
        return null;
    }
}
