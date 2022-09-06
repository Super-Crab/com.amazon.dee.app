package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
import amazon.communication.ConnectionInterruptedException;
import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.ByteBufferOutputStream;
import com.dp.framework.CodecException;
import com.dp.framework.StreamCodec;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public abstract class AlphaProtocolHandlerBase extends ProtocolHandlerBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String CHOSEN_ENCODING_KEY = "AlphaProtocolHandler.chosenEncoding";
    public static final int DEFAULT_MAX_FRAGMENT_SIZE = 16000;
    public static final int DEFAULT_MAX_FRAGMENT_SIZE_LOWER_BOUND = 1024;
    public static final int DEFAULT_MAX_FRAGMENT_SIZE_UPPER_BOUND = 51200;
    public static final int DEFAULT_RECEIVE_WINDOW_SIZE = 16;
    public static final String MAX_FRAGMENT_SIZE_KEY = "AlphaProtocolHandler.maxFragmentSize";
    protected static final int MAX_OUTSTANDING_ON_BYTE_BUFFER_CHAIN_CALLS = 5;
    protected static final String NULL_MESSAGE_TYPE = "NUL";
    public static final String RECEIVE_WINDOW_SIZE_KEY = "AlphaProtocolHandler.receiveWindowSize";
    protected static final String SEQUENCE_MESSAGE_TYPE = "SEQ";
    protected static final int SIZE_OF_MESSAGE_TYPE = 3;
    protected static final int START_SEQUENCE_NUMBER = 1;
    public static final String SUPPORTED_ENCODINGS_KEY = "AlphaProtocolHandler.supportedEncodings";
    private final int mBoolSize;
    private final ByteBufferChainHandler mByteBufferChainHandler;
    private final ChannelRestrictor mChannelRestrictor;
    private final int mChecksumEndPosition;
    private final int mChecksumStartPosition;
    private ByteBufferChainProcessor mCompressionProcessor;
    private ByteBufferChainProcessor mDecompressionProcessor;
    private final int mDelimiterSize;
    private Encoding mEncoding;
    private final int mIntSize;
    private final boolean mIsCompressionNeeded;
    private Map<String, String> mLocalProtocolParameters;
    private final Map<Integer, LongLivedMessageLifeCycleTracker> mLongLivedTrackers;
    protected int mMaxFragmentSize;
    protected final int mMessageHeaderSize;
    protected final int mOverheadSize;
    private final ProtocolSocket mProtocolSocket;
    protected int mReceiveWindowSize;
    private Map<String, String> mRemoteProtocolParameters;
    private final ByteBufferChainHandlerNotificationSink mSimpleMessageSink;
    private final WorkExecutor mWorkExecutor;
    private static final DPLogger log = new DPLogger("TComm.AlphaProtocolHandlerBase");
    private static final Set<String> simpleMessageTypes = new HashSet();
    private static final byte[] FOOTER = {70, 65, 66, 69};
    private static final int FOOTER_SIZE = FOOTER.length;
    private static final AtomicInteger sNextMessageId = new AtomicInteger(new Random().nextInt(1000000000));

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class MetricsLoggingNotificationSink implements ByteBufferChainHandlerNotificationSink {
        /* JADX INFO: Access modifiers changed from: protected */
        public MetricsLoggingNotificationSink() {
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainHandled(ByteBufferChain byteBufferChain) {
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void chainRejected(ByteBufferChain byteBufferChain, boolean z) {
        }

        @Override // com.amazon.communication.ByteBufferChainHandlerNotificationSink
        public void okToResubmitRejectedChain(ByteBufferChain byteBufferChain) {
            AlphaProtocolHandlerBase.log.verbose("mSimpleMessageSink.okToResubmitRejectedChain", "simple message sink notified to resubmit a rejected chain; ignoring.", new Object[0]);
        }
    }

    public AlphaProtocolHandlerBase(StreamCodec streamCodec, ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, boolean z, ChannelRestrictor channelRestrictor) {
        super(FOOTER, streamCodec);
        log.debug("AlphaProtocolHandlerBase", "constructor", "streamCodec", streamCodec);
        simpleMessageTypes.add(ProtocolHandler.MESSAGE_MESSAGE_TYPE);
        simpleMessageTypes.add(ProtocolHandler.REQUEST_MESSAGE_TYPE);
        simpleMessageTypes.add(ProtocolHandler.RESPONSE_MESSAGE_TYPE);
        simpleMessageTypes.add(ProtocolHandler.ERROR_MESSAGE_TYPE);
        simpleMessageTypes.add(NULL_MESSAGE_TYPE);
        this.mDelimiterSize = streamCodec.getSizeOfDelimiter();
        this.mIntSize = this.mStreamCodec.getSizeOfEncodedMaxInteger();
        this.mBoolSize = this.mStreamCodec.getSizeOfBool();
        int i = this.mDelimiterSize;
        int i2 = this.mIntSize;
        this.mChecksumStartPosition = i + 3 + i2 + i + i2 + i + this.mBoolSize + i + i2 + i;
        this.mChecksumEndPosition = this.mChecksumStartPosition + i2 + i;
        this.mMessageHeaderSize = this.mChecksumEndPosition + i2 + i;
        this.mOverheadSize = this.mMessageHeaderSize + FOOTER_SIZE;
        this.mByteBufferChainHandler = byteBufferChainHandler;
        this.mProtocolSocket = protocolSocket;
        this.mWorkExecutor = workExecutor;
        this.mLongLivedTrackers = new HashMap();
        this.mIsCompressionNeeded = z;
        this.mChannelRestrictor = channelRestrictor;
        this.mSimpleMessageSink = createNotificationSink();
    }

    private int getProtocolParameter(Map<String, String> map, String str, int i) {
        try {
            return Integer.parseInt(this.mRemoteProtocolParameters.get(str));
        } catch (NumberFormatException unused) {
            log.error("getProtocolParameter", "remote protocol parameter is not an integer", "key", str, "value", this.mRemoteProtocolParameters.get(str));
            return i;
        }
    }

    private void setupCompressionComponent(Map<String, String> map) throws TuningFailedException {
        if (this.mIsCompressionNeeded) {
            log.verbose("setEncoding", "compression is needed; search for supported encodings", new Object[0]);
            String str = map.get(SUPPORTED_ENCODINGS_KEY);
            if (str != null) {
                this.mEncoding = Encoding.chooseFirstSupportedEncoding(str);
                log.verbose("setEncoding", "protocol parameters have supported encodings entry", "supportedEncodings", str);
            } else {
                throw new TuningFailedException("Supported encodings list is not specified in protocol parameters");
            }
        } else {
            log.verbose("setEncoding", "compression is not needed.", new Object[0]);
        }
        String str2 = map.get(CHOSEN_ENCODING_KEY);
        if (str2 != null) {
            if (this.mEncoding == null) {
                this.mEncoding = Encoding.getEncodingFromName(str2);
                log.verbose("setEncoding", "protocol parameters have chosen encoding entry", "chosenEncodingName", str2);
            } else {
                throw new TuningFailedException("Supported encodings should not be specified when chosen encoding is available");
            }
        }
        Encoding encoding = this.mEncoding;
        if (encoding != null) {
            log.verbose("setEncoding", "chosen encoding for ProtocolHandler", "mEncoding", encoding);
            this.mCompressionProcessor = new CompressionByteBufferChainProcessor(this.mEncoding);
            this.mDecompressionProcessor = new DecompressionByteBufferChainProcessor(this.mEncoding);
        }
    }

    protected void confirmChecksum(ByteBuffer[] byteBufferArr, int i, int i2, int i3) throws ProtocolException {
        int computeDigest = RFC1071LikeDigest.computeDigest(byteBufferArr, i, i2);
        if (i3 == computeDigest) {
            return;
        }
        throw new ProtocolException(GeneratedOutlineSupport1.outline53("checksum mismatch. Expected: ", i3, ", Actual: ", computeDigest));
    }

    protected void confirmFooterAndShrink(ByteBufferChain byteBufferChain) throws ProtocolException {
        byteBufferChain.mark();
        if (byteBufferChain.skipBytes(byteBufferChain.getDataSize() - FOOTER_SIZE) && confirmFooter(byteBufferChain.getInputStream())) {
            byteBufferChain.reset();
            byteBufferChain.shrinkLimitFromEnd(FOOTER_SIZE);
            return;
        }
        throw new ProtocolException("Incorrect footer");
    }

    protected LongLivedMessageLifeCycleTracker createLongLivedMessageLifeCycleTracker(ByteBufferChainHandler byteBufferChainHandler, WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i, InputStream inputStream, String str, int i2, int i3) {
        return new LongLivedMessageLifeCycleTracker(this, byteBufferChainHandler, workExecutor, protocolSocket, i, inputStream, str, i2, i3);
    }

    protected ByteBufferChainHandlerNotificationSink createNotificationSink() {
        return new MetricsLoggingNotificationSink();
    }

    @Override // com.amazon.communication.ProtocolHandler
    public void decodeMessage(EndpointIdentity endpointIdentity, ByteBufferBackedMessage byteBufferBackedMessage) throws CommunicationBaseException {
        byteBufferBackedMessage.getByteBufferChain().mark();
        InputStream inputStream = byteBufferBackedMessage.getInputStream();
        try {
            try {
                String decodeAsciiString = this.mStreamCodec.decodeAsciiString(inputStream, 3);
                log.verbose("decodeMessage", "decoded message", "messageType", decodeAsciiString, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
                if (simpleMessageTypes.contains(decodeAsciiString)) {
                    handleSimpleMessageHeader(endpointIdentity, decodeAsciiString, inputStream, byteBufferBackedMessage);
                } else if (decodeAsciiString.equals(SEQUENCE_MESSAGE_TYPE)) {
                    handleSEQMessage(inputStream, byteBufferBackedMessage);
                } else {
                    throw new ProtocolException("Unknown message type: " + decodeAsciiString);
                }
                if (inputStream == null) {
                    return;
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.warn("decodeMessage", "error closing message InputStream", e);
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        log.warn("decodeMessage", "error closing message InputStream", e2);
                    }
                }
                throw th;
            }
        } catch (CodecException e3) {
            throw new ProtocolException(e3);
        } catch (IOException e4) {
            throw new ProtocolException(e4);
        }
    }

    @Override // com.amazon.communication.ProtocolHandler
    public void encodeMessage(Message message, String str, int i) throws ProtocolException, IOException {
        log.verbose("encodeMessage", "beginning execution", "message.getPayloadSize", Integer.valueOf(message.getPayloadSize()), "mOverheadSize", Integer.valueOf(this.mOverheadSize), "mMaxFragmentSize", Integer.valueOf(this.mMaxFragmentSize));
        if (message.getPayloadSize() == -1) {
            trackLargeMessage(message.getPayload(), str, i);
        } else if (message.getPayloadSize() + this.mOverheadSize > this.mMaxFragmentSize) {
            trackLargeMessage(BetterInputStreamDecorator.ensureBetterInputStream(message.getPayload()), str, i);
        } else {
            trackSimpleMessage(message, str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBufferChain encodeMessageFragment(ByteBufferChain byteBufferChain, boolean z, String str, int i, int i2, int i3) throws ProtocolException, IOException {
        log.verbose("encodeMessageFragment", "beginning execution", AccessoryMetricsConstants.SEQUENCE_NUMBER, Integer.valueOf(i3), "messageId", Integer.valueOf(i2), "channel", Integer.valueOf(i), "messageType", str);
        ByteBuffer allocate = ByteBuffer.allocate(this.mMessageHeaderSize);
        ByteBuffer allocate2 = ByteBuffer.allocate(FOOTER_SIZE);
        if (this.mCompressionProcessor != null && byteBufferChain.getDataSize() > 0) {
            byteBufferChain = this.mCompressionProcessor.process(byteBufferChain);
        }
        ByteBufferChainMessageImpl message = new ByteBufferChainMessageBuilder(allocate).append(byteBufferChain).append(allocate2).getMessage();
        ByteBufferChain byteBufferChain2 = message.getByteBufferChain();
        try {
            ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(allocate);
            this.mStreamCodec.encodeAsciiString(str, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i2, byteBufferOutputStream);
            this.mStreamCodec.encodeBool(z, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(i3, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(0, byteBufferOutputStream);
            this.mStreamCodec.encodeInt(message.getPayloadSize(), byteBufferOutputStream);
            allocate.flip();
            encodeFooter(new ByteBufferOutputStream(allocate2));
            allocate2.flip();
            int computeDigest = RFC1071LikeDigest.computeDigest(message.getByteBuffers(), this.mChecksumStartPosition, this.mChecksumEndPosition);
            allocate.position(this.mChecksumStartPosition);
            this.mStreamCodec.encodeInt(computeDigest, byteBufferOutputStream);
            allocate.rewind();
            return byteBufferChain2;
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBufferChain extractByteBufferChainFromInputStream(InputStream inputStream, int i) throws IOException {
        ByteBufferChain byteBufferChain = new ByteBufferChain();
        if (byteBufferChain.append(inputStream, i) == -1) {
            return null;
        }
        return byteBufferChain;
    }

    @Override // com.amazon.communication.ProtocolHandler
    public Encoding getEncodingType() {
        return this.mEncoding;
    }

    public int getMaxFragmentSize() {
        return this.mMaxFragmentSize;
    }

    public int getOverheadSize() {
        return this.mOverheadSize;
    }

    protected abstract void handleSEQMessage(int i, int i2) throws ProtocolException;

    protected void handleSEQMessage(InputStream inputStream, ByteBufferBackedMessage byteBufferBackedMessage) throws ProtocolException, IOException {
        try {
            ByteBufferChain byteBufferChain = byteBufferBackedMessage.getByteBufferChain();
            int dataSize = 3 + this.mDelimiterSize + byteBufferChain.getDataSize();
            int decodeInt = this.mStreamCodec.decodeInt(inputStream);
            int decodeInt2 = this.mStreamCodec.decodeInt(inputStream);
            int dataSize2 = dataSize - byteBufferChain.getDataSize();
            int decodeInt3 = this.mStreamCodec.decodeInt(inputStream);
            int dataSize3 = dataSize - byteBufferChain.getDataSize();
            int decodeInt4 = this.mStreamCodec.decodeInt(inputStream);
            ByteBuffer[] byteBuffers = byteBufferChain.getByteBuffers();
            int[] iArr = new int[byteBuffers.length];
            for (int i = 0; i < byteBuffers.length; i++) {
                iArr[i] = byteBuffers[i].position();
            }
            byteBufferChain.reset();
            confirmChecksum(byteBuffers, dataSize2, dataSize3, decodeInt3);
            for (int i2 = 0; i2 < byteBuffers.length; i2++) {
                byteBuffers[i2].position(iArr[i2]);
            }
            if (decodeInt4 == dataSize) {
                confirmFooterAndShrink(byteBufferChain);
                handleSEQMessage(decodeInt, decodeInt2);
                return;
            }
            throw new ProtocolException("Unexpected size of the SEQ message.  size: " + decodeInt4);
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    protected abstract void handleSimpleMessage(EndpointIdentity endpointIdentity, String str, int i, int i2, boolean z, int i3, boolean z2, Message message) throws CommunicationBaseException;

    protected void handleSimpleMessageHeader(EndpointIdentity endpointIdentity, String str, InputStream inputStream, ByteBufferBackedMessage byteBufferBackedMessage) throws CommunicationBaseException, IOException {
        ByteBufferChainInputStream byteBufferChainInputStream = inputStream;
        try {
            ByteBufferChain byteBufferChain = byteBufferBackedMessage.getByteBufferChain();
            int length = str.length() + this.mDelimiterSize + byteBufferChain.getDataSize();
            int decodeInt = this.mStreamCodec.decodeInt(byteBufferChainInputStream);
            boolean z = true;
            if (!this.mChannelRestrictor.isAuthorized(decodeInt, endpointIdentity)) {
                log.error("handleSimpleMessageHeader", "message received on restricted channel from prohibited sender", "sender", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(decodeInt));
                return;
            }
            int decodeInt2 = this.mStreamCodec.decodeInt(byteBufferChainInputStream);
            boolean decodeBool = this.mStreamCodec.decodeBool(byteBufferChainInputStream);
            int decodeInt3 = this.mStreamCodec.decodeInt(byteBufferChainInputStream);
            int decodeInt4 = this.mStreamCodec.decodeInt(byteBufferChainInputStream);
            int decodeInt5 = this.mStreamCodec.decodeInt(byteBufferChainInputStream);
            ByteBuffer[] byteBuffers = byteBufferChain.getByteBuffers();
            byteBufferChain.savePositions();
            byteBufferChain.reset();
            confirmChecksum(byteBuffers, length - byteBufferChain.getDataSize(), length - byteBufferChain.getDataSize(), decodeInt4);
            byteBufferChain.loadSavedPositions();
            if (decodeInt5 == length) {
                confirmFooterAndShrink(byteBufferChain);
                if (this.mDecompressionProcessor != null && byteBufferChain.getDataSize() > 0) {
                    byteBufferChain = this.mDecompressionProcessor.process(byteBufferChain);
                    byteBufferChainInputStream = byteBufferChain.getInputStream();
                }
                if (str.equals(NULL_MESSAGE_TYPE)) {
                    return;
                }
                if (str.equals(ProtocolHandler.ERROR_MESSAGE_TYPE)) {
                    StringBuilder sb = new StringBuilder(byteBufferChainInputStream.available());
                    for (int capacity = sb.capacity() - 1; capacity >= 0; capacity--) {
                        sb.append((char) ((byte) byteBufferChainInputStream.read()));
                    }
                    throw new ConnectionInterruptedException("ERR received.  Message: " + sb.toString());
                }
                if (!decodeBool && decodeInt3 <= 1) {
                    z = false;
                }
                handleSimpleMessage(endpointIdentity, str, decodeInt, decodeInt2, decodeBool, decodeInt3, z, new ByteBufferChainMessageImpl(byteBufferChain));
                return;
            }
            throw new ProtocolException("Incorrect size - expected: " + decodeInt5 + ", actual: " + length);
        } catch (CodecException e) {
            throw new ProtocolException(e);
        }
    }

    public void setLocalProtocolParameters(Map<String, String> map) {
        this.mLocalProtocolParameters = map;
    }

    public void setRemoteProtocolParameters(Map<String, String> map) throws TuningFailedException {
        this.mRemoteProtocolParameters = map;
        this.mMaxFragmentSize = getProtocolParameter(map, MAX_FRAGMENT_SIZE_KEY, 16000);
        int i = this.mMaxFragmentSize;
        if (i <= 51200 && i >= 1024) {
            this.mReceiveWindowSize = getProtocolParameter(map, RECEIVE_WINDOW_SIZE_KEY, 16);
            setupCompressionComponent(map);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid MaxFragmentSize: ");
        GeneratedOutlineSupport1.outline175(outline107, this.mMaxFragmentSize, ", lower bound is ", 1024, ", upper bound is:");
        outline107.append(DEFAULT_MAX_FRAGMENT_SIZE_UPPER_BOUND);
        throw new TuningFailedException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopTrackingLongLivedMessage(int i) {
        this.mLongLivedTrackers.remove(Integer.valueOf(i));
    }

    protected void trackLargeMessage(InputStream inputStream, String str, int i) {
        log.verbose("trackLargeMessage", "sending large message", "channel", Integer.valueOf(i), "messageType", str);
        int incrementAndGet = sNextMessageId.incrementAndGet();
        LongLivedMessageLifeCycleTracker createLongLivedMessageLifeCycleTracker = createLongLivedMessageLifeCycleTracker(this.mByteBufferChainHandler, this.mWorkExecutor, this.mProtocolSocket, this.mMaxFragmentSize - this.mOverheadSize, inputStream, str, i, incrementAndGet);
        this.mLongLivedTrackers.put(Integer.valueOf(incrementAndGet), createLongLivedMessageLifeCycleTracker);
        createLongLivedMessageLifeCycleTracker.transmitMessage();
    }

    protected void trackSimpleMessage(Message message, String str, int i) throws ProtocolException, IOException {
        log.verbose("trackSimpleMessage", "sending simple message", "channel", Integer.valueOf(i), "messageType", str);
        ByteBufferChain extractByteBufferChainFromInputStream = extractByteBufferChainFromInputStream(message.getPayload(), message.getPayloadSize());
        if (extractByteBufferChainFromInputStream != null && extractByteBufferChainFromInputStream.getDataSize() > 0) {
            this.mByteBufferChainHandler.onByteBufferChain(encodeMessageFragment(extractByteBufferChainFromInputStream, false, str, i, sNextMessageId.incrementAndGet(), 1), this.mSimpleMessageSink);
        } else {
            log.warn("trackSimpleMessage", "no data in payload; don't send it", "messageChain", extractByteBufferChainFromInputStream);
        }
    }
}
