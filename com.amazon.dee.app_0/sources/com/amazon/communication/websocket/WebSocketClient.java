package com.amazon.communication.websocket;

import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.RequestSigner;
import amazon.communication.authentication.SigningException;
import com.amazon.communication.utils.HttpUtils;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.Base64;
import com.dp.utils.FailFast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class WebSocketClient {
    private static final byte BIGDATA_BYTE = Byte.MAX_VALUE;
    private static final int BIGDATA_CUTOFF = 65535;
    private static final byte CR = 13;
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";
    private static final String KEY_APPEND = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final byte LF = 10;
    private static final byte MASK_BIT = Byte.MIN_VALUE;
    public static final int MASK_LENGTH = 4;
    private static final int MAX_CONTROL_FRAME_SIZE = 125;
    public static final long PEER_CLOSE_RECEIVE_TIMEOUT_MS = 2000;
    private static final String WEBSOCKET_OPERATION_NAME = "WebSocket";
    private long mBytesToRead;
    private long mBytesToWrite;
    private ConnectionStatus mConnectionStatus;
    private final ByteBuffer mControlFrameBuffer;
    private final ByteBuffer mFrameHeaderBuffer;
    private final ByteBuffer[] mFramingBuffersToSend;
    private ByteBuffer mHandshakeBuffer;
    private final String mHttpMethod;
    private byte[] mKeyExpected;
    private final ByteBuffer mLargeFrameLengthBuffer;
    private ReadStatus mReadStatus;
    private ReadableByteChannel mReadableChannel;
    private final String mServiceName;
    private final SocketChannel mSocketChannel;
    private GatheringByteChannel mWritableChannel;
    private WriteStatus mWriteStatus;
    private final WebSocketListener mWsl;
    private static final DPLogger log = new DPLogger("TComm.WebSocketClient");
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final byte[] CRLF = {13, 10};
    private static final byte DATAFRAME_BYTE = -126;
    private static final byte DATA_BYTE = 126;
    private static final byte[] START_OF_DATA_FRAME = {DATAFRAME_BYTE, DATA_BYTE};
    private static final byte[] START_OF_CLOSE_FRAME = {-120};
    private static final byte[] START_OF_PING_FRAME = {-119};
    private static final byte[] START_OF_PONG_FRAME = {-118};
    private static final byte[] HTTP_PROTOCOL = " HTTP/1.1\r\n".getBytes(UTF8_CHARSET);
    private static final byte[] UPGRADE_HEADER_AND_VALUE = "Upgrade: WebSocket\r\n".getBytes(UTF8_CHARSET);
    private static final byte[] CONNECTION_UPDATE_HEADER_AND_VALUE = "Connection: Upgrade\r\n".getBytes(UTF8_CHARSET);
    private static final byte[] HOST_HEADER = "Host: ".getBytes(UTF8_CHARSET);
    private static final byte[] WEBSOCKET_VERSION_HEADER_AND_VALUE = "Sec-WebSocket-Version: 13\r\n".getBytes(UTF8_CHARSET);
    private static final byte[] WEBSOCKET_KEY_HEADER = "Sec-WebSocket-Key: ".getBytes(UTF8_CHARSET);
    private static final byte[] COLON = RealTimeTextConstants.COLON_SPACE.getBytes(UTF8_CHARSET);
    private static final byte[] UPGRADE_HEADER = "Upgrade: ".getBytes(UTF8_CHARSET);
    private static final byte[] CONNECTION_HEADER = "Connection: ".getBytes(UTF8_CHARSET);
    private static final byte[] ACCEPT_HEADER = "Sec-WebSocket-Accept: ".getBytes(UTF8_CHARSET);
    private static final byte[] HTTP_RESPONSE_EXPECTED = "HTTP/1.1 101".getBytes(UTF8_CHARSET);
    private static final byte[] UPGRADE_EXPECTED = "websocket".getBytes(UTF8_CHARSET);
    private static final byte[] CONNECTION_EXPECTED = "upgrade".getBytes(UTF8_CHARSET);
    private static final String CLOSE_CALLER_STRING = "The WebSocket connection was closed by the caller";
    private static final CloseDetail CLIENT_CLOSE_DETAIL = new CloseDetail(CloseStatusCodes.CLIENT_ERROR, CLOSE_CALLER_STRING);
    private static final CloseDetail SERVER_CLOSE_NO_DETAIL = new CloseDetail(4000, "Server sent the close command with no additional data");
    private static final CloseDetail EOF_DETAIL = new CloseDetail(CloseStatusCodes.EOF_ERROR, "The SocketChannel received end-of-stream");
    private static final CloseDetail PINGPONG_FAILURE = new CloseDetail(CloseStatusCodes.PINGPONG_FAILURE, "The Ping request could not be handled correctly");
    private static final Random RANDOM = new Random();

    /* renamed from: com.amazon.communication.websocket.WebSocketClient$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader = new int[WebSocketHeader.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus;

        static {
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.OTHER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.UPGRADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.CONNECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.ACCEPT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WebSocketHeader[WebSocketHeader.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus = new int[ReadStatus.values().length];
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.NOT_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.WAIT_FOR_FRAME_HEADER.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.PROCESS_PING_FRAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.PROCESS_CLOSE_FRAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.PROCESS_DATA_FRAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.WAIT_FOR_LARGE_SIZE_FRAME_LENGTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$ReadStatus[ReadStatus.CONSUME_DATA_FRAME_PAYLOAD.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus = new int[WriteStatus.values().length];
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus[WriteStatus.BEFORE_UPGRADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus[WriteStatus.NOT_STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus[WriteStatus.WRITE_DATA_FRAMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$communication$websocket$WebSocketClient$WriteStatus[WriteStatus.WRITE_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum ConnectionStatus {
        BEFORE_UPGRADE,
        UPGRADED,
        CLOSING,
        CLOSED
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum ReadStatus {
        NOT_STARTED,
        WAIT_FOR_FRAME_HEADER,
        PROCESS_PING_FRAME,
        PROCESS_CLOSE_FRAME,
        PROCESS_DATA_FRAME,
        WAIT_FOR_LARGE_SIZE_FRAME_LENGTH,
        CONSUME_DATA_FRAME_PAYLOAD,
        WEBSOCKET_CLOSED
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum WebSocketHeader {
        UPGRADE,
        CONNECTION,
        ACCEPT,
        OTHER,
        END,
        ERROR
    }

    /* loaded from: classes12.dex */
    public interface WebSocketListener {
        void onClosed(CloseReason closeReason, CloseDetail closeDetail);
    }

    /* loaded from: classes12.dex */
    private enum WriteStatus {
        BEFORE_UPGRADE,
        NOT_STARTED,
        WRITE_DATA_FRAMING,
        WRITE_DATA
    }

    public WebSocketClient(SocketChannel socketChannel, WebSocketListener webSocketListener, String str, String str2) {
        this.mControlFrameBuffer = ByteBuffer.allocate(125);
        this.mFrameHeaderBuffer = ByteBuffer.allocate(START_OF_DATA_FRAME.length);
        this.mLargeFrameLengthBuffer = ByteBuffer.allocate(8);
        this.mFramingBuffersToSend = new ByteBuffer[3];
        log.verbose("WebSocketClient", "constructor", new Object[0]);
        if (socketChannel != null && socketChannel.isBlocking()) {
            throw new IllegalArgumentException("WebSocketClient must be instantiated with a non-blocking SocketChannel");
        }
        if (str2 != null && (str2.equals("GET") || str2.equals("POST"))) {
            this.mSocketChannel = socketChannel;
            setWritableChannel(socketChannel);
            setReadableChannel(socketChannel);
            this.mHandshakeBuffer = null;
            this.mWsl = webSocketListener;
            this.mServiceName = str;
            this.mHttpMethod = str2;
            this.mBytesToWrite = 0L;
            this.mBytesToRead = 0L;
            this.mKeyExpected = null;
            this.mConnectionStatus = ConnectionStatus.BEFORE_UPGRADE;
            this.mReadStatus = ReadStatus.NOT_STARTED;
            this.mWriteStatus = WriteStatus.BEFORE_UPGRADE;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unsupported http method: ", str2));
    }

    private void appendSignatureHeadersAndBody(ByteArrayOutputStream byteArrayOutputStream, URI uri, HttpRequestBase httpRequestBase, RequestSigner requestSigner, RequestContext requestContext) throws SigningException, MissingCredentialsException {
        Header[] allHeaders;
        HttpEntity entity;
        for (Header header : httpRequestBase.getAllHeaders()) {
            try {
                byteArrayOutputStream.write(header.getName().getBytes(UTF8_CHARSET));
                byteArrayOutputStream.write(COLON);
                byteArrayOutputStream.write(header.getValue().getBytes(UTF8_CHARSET));
                byteArrayOutputStream.write(CRLF);
            } catch (IOException e) {
                throw new SigningException(e);
            }
        }
        byteArrayOutputStream.write(CRLF);
        if (!(httpRequestBase instanceof HttpEntityEnclosingRequest) || (entity = ((HttpEntityEnclosingRequest) httpRequestBase).getEntity()) == null) {
            return;
        }
        log.debug("appendSignatureHeadersAndBody", "write to request", "entity.getContentLength", Long.valueOf(entity.getContentLength()));
        entity.writeTo(byteArrayOutputStream);
    }

    private void closeAndCleanup(CloseReason closeReason, CloseDetail closeDetail) {
        try {
            log.info("closeAndCleanup", "about to close the socket channel", "closeReason", closeReason, "closeDetails", closeDetail);
            this.mSocketChannel.close();
        } catch (IOException e) {
            log.error("closeAndCleanup", "failed to close SocketChannel", e);
        }
        this.mHandshakeBuffer = null;
        this.mBytesToWrite = 0L;
        this.mBytesToRead = 0L;
        this.mKeyExpected = null;
        this.mConnectionStatus = ConnectionStatus.CLOSED;
        this.mReadStatus = ReadStatus.WEBSOCKET_CLOSED;
        this.mWsl.onClosed(closeReason, closeDetail);
    }

    private boolean compareBuffersAndMovePosition(ByteBuffer byteBuffer, byte[] bArr, boolean z) {
        int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + bArr.length);
        boolean equals = byteBuffer.equals(ByteBuffer.wrap(bArr));
        if (equals || z) {
            byteBuffer.position(byteBuffer.position() + bArr.length);
        }
        byteBuffer.limit(limit);
        return equals;
    }

    private String generateBase64Key() {
        byte[] bArr = new byte[16];
        RANDOM.nextBytes(bArr);
        return new String(Base64.encodeNoWrap(bArr), UTF8_CHARSET);
    }

    public static byte[][] generateFramingBufferStartAndSizeBytes(long j) throws IOException {
        byte[] bArr;
        byte[] bArr2;
        if (j > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            if ((j >>> 63) != 0) {
                throw new IOException("WebSocketClient implementation cannot send more than 2^63 -1 bytes at once");
            }
            bArr = new byte[]{(byte) (j >> 56), (byte) (j >> 48), (byte) (j >> 40), (byte) (j >> 32), (byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) j};
            bArr2 = new byte[]{DATAFRAME_BYTE, -1};
        } else if (j >= 126) {
            bArr = new byte[]{(byte) (j >> 8), (byte) j};
            bArr2 = new byte[]{DATAFRAME_BYTE, -2};
        } else {
            bArr = new byte[]{(byte) ((j & 127) | (-128))};
            bArr2 = new byte[]{DATAFRAME_BYTE};
        }
        return new byte[][]{bArr2, bArr};
    }

    private void generateMask(byte[] bArr) {
        for (int i = 0; i > bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    private String getByteBufferContent(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("getByteBufferContent", "UTF-8 encoding is not supported", e);
            return null;
        }
    }

    private void handleClose(ByteBuffer byteBuffer) throws IOException {
        log.verbose("handleClose", "close received", new Object[0]);
        byte b = byteBuffer.get();
        if (b > 125) {
            log.verbose("handleClose", "close frame reported length > MAX_CONTROL_FRAME_SIZE", "MAX_CONTROL_FRAME_SIZE", 125, "closeLength", Integer.valueOf(b));
        }
        this.mControlFrameBuffer.clear();
        if (readFromSocketChannel(this.mControlFrameBuffer) > 0) {
            this.mControlFrameBuffer.flip();
            short s = this.mControlFrameBuffer.getShort();
            byte[] bArr = new byte[this.mControlFrameBuffer.remaining()];
            this.mControlFrameBuffer.get(bArr);
            close(CloseReason.CLOSE_COMMAND, new CloseDetail(s, new String(bArr, UTF8_CHARSET)));
            return;
        }
        close(CloseReason.CLOSE_COMMAND, SERVER_CLOSE_NO_DETAIL);
    }

    private int handleContinuedRead(ByteBuffer byteBuffer) throws IOException {
        log.verbose("handleContinuedRead", "handleContinuedRead", new Object[0]);
        int readFromSocketChannel = readFromSocketChannel(byteBuffer);
        if (readFromSocketChannel == -1) {
            close(CloseReason.CLOSE_ERROR, EOF_DETAIL);
        } else if (readFromSocketChannel == 0) {
            log.warn("handleContinuedRead", "0-byte read occurred", new Object[0]);
        } else {
            long j = readFromSocketChannel;
            long j2 = this.mBytesToRead;
            if (j > j2) {
                close(CloseReason.CLOSE_ERROR, new CloseDetail(CloseStatusCodes.EXTRA_DATA_RECEIVED, "Data frame length did not match data received from server. Expected: " + this.mBytesToRead + " Received: " + readFromSocketChannel));
            } else {
                this.mBytesToRead = j2 - j;
            }
        }
        return readFromSocketChannel;
    }

    private boolean handlePing(ByteBuffer byteBuffer) throws IOException {
        log.verbose("handlePing", "ping received", new Object[0]);
        int i = byteBuffer.get() & 255;
        if (i <= 125) {
            this.mControlFrameBuffer.clear();
            if (readFromSocketChannel(this.mControlFrameBuffer) == i) {
                boolean sendPong = sendPong(this.mControlFrameBuffer, i);
                if (!sendPong) {
                    log.verbose("handlePing", "pong failed", new Object[0]);
                }
                return sendPong;
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Invalid Ping frame. Expected ping length: ", i, ", bytes got: ");
            outline109.append(getByteBufferContent(this.mControlFrameBuffer));
            throw new IOException(outline109.toString());
        }
        throw new IOException("Ping message reported length > 125 bytes: " + i);
    }

    private WebSocketHeader identifyHeader(ByteBuffer byteBuffer) {
        try {
            if (byteBuffer.get() == 13) {
                WebSocketHeader webSocketHeader = WebSocketHeader.ERROR;
                return (byteBuffer.get() == 10 && byteBuffer.position() == byteBuffer.limit()) ? WebSocketHeader.END : webSocketHeader;
            }
            byteBuffer.position(byteBuffer.position() - 1);
            WebSocketHeader webSocketHeader2 = WebSocketHeader.OTHER;
            if (isHeader(UPGRADE_HEADER, byteBuffer)) {
                return WebSocketHeader.UPGRADE;
            }
            if (isHeader(CONNECTION_HEADER, byteBuffer)) {
                return WebSocketHeader.CONNECTION;
            }
            if (isHeader(ACCEPT_HEADER, byteBuffer)) {
                return WebSocketHeader.ACCEPT;
            }
            return WebSocketHeader.OTHER;
        } catch (BufferUnderflowException unused) {
            return WebSocketHeader.ERROR;
        }
    }

    private boolean isHeader(byte[] bArr, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() >= bArr.length) {
            return compareBuffersAndMovePosition(byteBuffer, bArr, false);
        }
        return false;
    }

    private boolean moveToEndOfHeader(ByteBuffer byteBuffer) {
        try {
            byte b = byteBuffer.get();
            while (b != 13) {
                b = byteBuffer.get();
            }
            return byteBuffer.get() == 10;
        } catch (BufferUnderflowException unused) {
            return false;
        }
    }

    private int readFromSocketChannel(ByteBuffer byteBuffer) throws IOException {
        try {
            return this.mReadableChannel.read(byteBuffer);
        } catch (NotYetConnectedException e) {
            log.error("readFromSocketChannel", "exception when receiving data", e);
            throw new IOException("Exception when receiving data", e);
        }
    }

    private void readPotentialHttpResponse(ByteBuffer byteBuffer) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(1);
        while (true) {
            boolean z = false;
            while (!z) {
                int readFromSocketChannel = readFromSocketChannel(allocate);
                if (readFromSocketChannel > 0) {
                    allocate.position(0);
                    byte b = allocate.get(0);
                    byteBuffer.put(b);
                    if (z) {
                        if (!z) {
                            if (!z) {
                                if (!z) {
                                    continue;
                                } else if (b == 10) {
                                    z = true;
                                }
                            } else if (b == 13) {
                                z = true;
                            }
                        } else if (b == 10) {
                            z = true;
                        }
                    } else if (b == 13) {
                        z = true;
                    }
                } else if (readFromSocketChannel == 0) {
                    log.debug("readPotentialHttpResponse", "zero bytes read", new Object[0]);
                } else if (readFromSocketChannel == -1) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot read complete http response. Bytes read: ");
                    outline107.append(getByteBufferContent(byteBuffer));
                    throw new IOException(outline107.toString());
                } else {
                    FailFast.expectTrue(false, GeneratedOutlineSupport1.outline49("bytesRead: ", readFromSocketChannel));
                }
            }
            return;
        }
    }

    private boolean sendClose(int i) throws IOException {
        byte[] bArr = {DATAFRAME_BYTE};
        byte[] bArr2 = {(byte) ((65280 & i) >> 8), (byte) (i & 255)};
        byte[] bArr3 = new byte[4];
        generateMask(bArr3);
        return ((long) (((bArr3.length + START_OF_CLOSE_FRAME.length) + bArr.length) + bArr2.length)) == writeToSocketChannel(new ByteBuffer[]{ByteBuffer.wrap(START_OF_CLOSE_FRAME), ByteBuffer.wrap(bArr), ByteBuffer.wrap(bArr3), ByteBuffer.wrap(bArr2)});
    }

    private boolean sendPong(ByteBuffer byteBuffer, int i) throws IOException {
        byteBuffer.flip();
        byte[] bArr = {(byte) i};
        byte[] bArr2 = new byte[4];
        generateMask(bArr2);
        return ((long) (((i + bArr2.length) + START_OF_PONG_FRAME.length) + bArr.length)) == writeToSocketChannel(new ByteBuffer[]{ByteBuffer.wrap(bArr2), ByteBuffer.wrap(START_OF_PONG_FRAME), ByteBuffer.wrap(bArr), byteBuffer});
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0215, code lost:
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long startFreshRead() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.websocket.WebSocketClient.startFreshRead():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean verifyHeader(java.nio.ByteBuffer r7, com.amazon.communication.websocket.WebSocketClient.WebSocketHeader r8) {
        /*
            r6 = this;
            r0 = 0
            byte[] r1 = new byte[r0]
            int r8 = r8.ordinal()
            r2 = 1
            if (r8 == 0) goto L19
            if (r8 == r2) goto L16
            r3 = 2
            if (r8 == r3) goto L12
            r8 = r0
        L10:
            r3 = r8
            goto L1d
        L12:
            byte[] r1 = r6.mKeyExpected
            r8 = r2
            goto L10
        L16:
            byte[] r1 = com.amazon.communication.websocket.WebSocketClient.CONNECTION_EXPECTED
            goto L1b
        L19:
            byte[] r1 = com.amazon.communication.websocket.WebSocketClient.UPGRADE_EXPECTED
        L1b:
            r3 = r0
            r8 = r2
        L1d:
            if (r8 == 0) goto L56
            int r4 = r7.remaining()
            int r5 = r1.length
            if (r4 < r5) goto L57
            if (r3 == 0) goto L2d
            boolean r8 = r6.compareBuffersAndMovePosition(r7, r1, r2)
            goto L42
        L2d:
            r3 = r0
        L2e:
            int r4 = r1.length
            if (r3 >= r4) goto L42
            r4 = r1[r3]
            byte r5 = r7.get()
            int r5 = java.lang.Character.toLowerCase(r5)
            if (r4 == r5) goto L3f
            r8 = r0
            goto L42
        L3f:
            int r3 = r3 + 1
            goto L2e
        L42:
            if (r8 == 0) goto L57
            byte r8 = r7.get()
            r1 = 13
            if (r8 != r1) goto L57
            byte r7 = r7.get()
            r8 = 10
            if (r7 != r8) goto L57
            r0 = r2
            goto L57
        L56:
            r0 = r8
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.websocket.WebSocketClient.verifyHeader(java.nio.ByteBuffer, com.amazon.communication.websocket.WebSocketClient$WebSocketHeader):boolean");
    }

    private boolean verifyHeaders(ByteBuffer byteBuffer) {
        WebSocketHeader webSocketHeader = WebSocketHeader.OTHER;
        boolean moveToEndOfHeader = moveToEndOfHeader(byteBuffer);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (moveToEndOfHeader) {
            try {
                int ordinal = identifyHeader(byteBuffer).ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            if (ordinal == 3) {
                                moveToEndOfHeader = moveToEndOfHeader(byteBuffer);
                            } else if (ordinal == 4) {
                                return z && z2 && z3;
                            } else if (ordinal == 5) {
                                moveToEndOfHeader = false;
                            }
                        } else if (z3) {
                            moveToEndOfHeader = false;
                        } else {
                            moveToEndOfHeader = verifyHeader(byteBuffer, WebSocketHeader.ACCEPT);
                            z3 = moveToEndOfHeader;
                        }
                    } else if (z2) {
                        moveToEndOfHeader = false;
                    } else {
                        moveToEndOfHeader = verifyHeader(byteBuffer, WebSocketHeader.CONNECTION);
                        z2 = moveToEndOfHeader;
                    }
                } else if (z) {
                    moveToEndOfHeader = false;
                } else {
                    moveToEndOfHeader = verifyHeader(byteBuffer, WebSocketHeader.UPGRADE);
                    z = moveToEndOfHeader;
                }
            } catch (BufferUnderflowException unused) {
                return z && z2 && z3;
            }
        }
        return moveToEndOfHeader;
    }

    private boolean verifyResponseCode(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        byte[] bArr = HTTP_RESPONSE_EXPECTED;
        if (remaining < bArr.length) {
            return false;
        }
        return compareBuffersAndMovePosition(byteBuffer, bArr, true);
    }

    private int writeToSocketChannel(ByteBuffer byteBuffer) throws IOException {
        try {
            return this.mWritableChannel.write(byteBuffer);
        } catch (NotYetConnectedException e) {
            log.error("writeToSocketChannel", "exception when sending data", "buffer.remaining", Integer.valueOf(byteBuffer.remaining()), e);
            throw new IOException("Exception when sending data", e);
        }
    }

    @Deprecated
    public void close() throws IOException {
        log.verbose("close", "close", new Object[0]);
        close(CloseReason.CLOSE_CALLER, CLIENT_CLOSE_DETAIL);
    }

    public void finishClose() throws IOException {
        log.verbose("finishClose", "expecting to read the close frame", new Object[0]);
        getDataBytesAvailable();
        if (this.mConnectionStatus != ConnectionStatus.CLOSED) {
            log.info("finishClose", "At this point we expected the connection to be closed. Force closing", new Object[0]);
            closeAndCleanup(CloseReason.CLOSE_CALLER, CLIENT_CLOSE_DETAIL);
        }
    }

    public boolean finishUpgrade() throws IOException {
        if (this.mWriteStatus == WriteStatus.BEFORE_UPGRADE) {
            readPotentialHttpResponse(this.mHandshakeBuffer);
            this.mHandshakeBuffer.flip();
            if (receiveHandshake(this.mHandshakeBuffer)) {
                this.mHandshakeBuffer.clear();
                this.mWriteStatus = WriteStatus.NOT_STARTED;
            }
        }
        return this.mWriteStatus != WriteStatus.BEFORE_UPGRADE;
    }

    public long getDataBytesAvailable() throws IOException {
        if (this.mBytesToRead == 0) {
            this.mBytesToRead = startFreshRead();
        }
        return this.mBytesToRead;
    }

    public long getReadBytesExpectedCount() {
        return this.mBytesToRead;
    }

    public int getWriteBytesExpectedCount() {
        return (int) this.mBytesToWrite;
    }

    public void initiateClose() throws IOException {
        this.mConnectionStatus = ConnectionStatus.CLOSING;
        sendClose(1000);
    }

    public boolean isClosed() {
        ConnectionStatus connectionStatus = this.mConnectionStatus;
        return connectionStatus == ConnectionStatus.CLOSING || connectionStatus == ConnectionStatus.CLOSED;
    }

    public int read(ByteBuffer byteBuffer) throws ClosedChannelException, IOException, NotYetConnectedException {
        if (!isClosed()) {
            if (this.mWriteStatus != WriteStatus.BEFORE_UPGRADE) {
                if (this.mBytesToRead == 0) {
                    log.warn("read", "0-byte read occurred", new Object[0]);
                    return 0;
                }
                return handleContinuedRead(byteBuffer);
            }
            throw new NotYetConnectedException();
        }
        throw new ClosedChannelException();
    }

    protected boolean receiveHandshake(ByteBuffer byteBuffer) throws IOException {
        log.verbose("receiveHandshake", "receiveHandshake", new Object[0]);
        if (!verifyResponseCode(byteBuffer)) {
            log.verbose("receiveHandshake", "handshake received incorrect response code", new Object[0]);
            return false;
        } else if (verifyHeaders(byteBuffer)) {
            return true;
        } else {
            log.verbose("receiveHandshake", "handshake received incorrect headers", new Object[0]);
            return false;
        }
    }

    public void setReadableChannel(ReadableByteChannel readableByteChannel) {
        this.mReadableChannel = readableByteChannel;
    }

    public void setWritableChannel(GatheringByteChannel gatheringByteChannel) {
        this.mWritableChannel = gatheringByteChannel;
    }

    public boolean upgradeConnection(URI uri, String[] strArr, String[] strArr2, RequestSigner requestSigner, RequestContext requestContext) throws IOException, SigningException, MissingCredentialsException {
        URI uri2;
        log.verbose("upgradeConnection", "upgradeConnection", new Object[0]);
        if (this.mWriteStatus == WriteStatus.BEFORE_UPGRADE) {
            int port = uri.getPort();
            if (port == -1) {
                port = this.mSocketChannel.socket().getPort();
            }
            HttpGet httpGet = null;
            if (requestSigner != null) {
                if (this.mHttpMethod.equals("GET")) {
                    httpGet = new HttpGet(uri);
                } else if (this.mHttpMethod.equals("POST")) {
                    httpGet = new HttpPost(uri);
                }
                String str = this.mServiceName;
                if (str != null && !str.trim().equals("")) {
                    log.debug("appendSignatureHeadersAndBody", "add service name and operation name headers", new Object[0]);
                    httpGet.addHeader(RequestSigner.SERVICE_NAME_HEADER, this.mServiceName);
                    httpGet.addHeader(RequestSigner.SERVICE_OPERATION_NAME_HEADER, WEBSOCKET_OPERATION_NAME);
                }
                requestSigner.signRequest(httpGet, requestContext);
                uri2 = httpGet.getURI();
            } else {
                uri2 = uri;
            }
            String rawFullUri = HttpUtils.getRawFullUri(uri2);
            log.debug("upgradeConnection", "upgrading connection with signed and encoded URI", "requestUriString", rawFullUri);
            String str2 = uri2.getHost() + port;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.mHttpMethod, " ").getBytes(UTF8_CHARSET));
            byteArrayOutputStream.write(rawFullUri.getBytes());
            byteArrayOutputStream.write(HTTP_PROTOCOL);
            byteArrayOutputStream.write(UPGRADE_HEADER_AND_VALUE);
            byteArrayOutputStream.write(CONNECTION_UPDATE_HEADER_AND_VALUE);
            byteArrayOutputStream.write(HOST_HEADER);
            byteArrayOutputStream.write(str2.getBytes(UTF8_CHARSET));
            byteArrayOutputStream.write(CRLF);
            byteArrayOutputStream.write(WEBSOCKET_VERSION_HEADER_AND_VALUE);
            String generateBase64Key = generateBase64Key();
            byteArrayOutputStream.write(WEBSOCKET_KEY_HEADER);
            byteArrayOutputStream.write(generateBase64Key.getBytes(UTF8_CHARSET));
            byteArrayOutputStream.write(CRLF);
            if (strArr != null) {
                if (strArr.length == strArr2.length) {
                    for (int i = 0; i < strArr.length; i++) {
                        byteArrayOutputStream.write(strArr[i].getBytes(UTF8_CHARSET));
                        byteArrayOutputStream.write(COLON);
                        byteArrayOutputStream.write(strArr2[i].getBytes(UTF8_CHARSET));
                        byteArrayOutputStream.write(CRLF);
                    }
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Number of additional header values did not match number of additional header names. Number of values: ");
                    outline107.append(strArr2.length);
                    outline107.append(", number of names: ");
                    outline107.append(strArr.length);
                    throw new IOException(outline107.toString());
                }
            }
            if (httpGet != null) {
                appendSignatureHeadersAndBody(byteArrayOutputStream, uri, httpGet, requestSigner, requestContext);
            } else {
                byteArrayOutputStream.write(CRLF);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (writeToSocketChannel(ByteBuffer.wrap(byteArray)) != byteArray.length) {
                log.verbose("upgradeConnection", "not all the handshake was written", new Object[0]);
                return false;
            }
            this.mHandshakeBuffer = ByteBuffer.allocate(512);
            try {
                this.mKeyExpected = Base64.encodeNoWrap(MessageDigest.getInstance("SHA").digest((generateBase64Key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(UTF8_CHARSET)));
                return true;
            } catch (NoSuchAlgorithmException e) {
                log.error("upgradeConnection", "error encoding request", e);
                return false;
            }
        }
        throw new IOException("WebSocket upgrade has already completed");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cc A[LOOP:0: B:26:0x00c7->B:28:0x00cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00dc A[LOOP:1: B:30:0x00d5->B:32:0x00dc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d3 A[EDGE_INSN: B:52:0x00d3->B:29:0x00d3 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int write(com.amazon.communication.ByteBufferChain r18) throws java.nio.channels.ClosedChannelException, java.io.IOException, java.nio.channels.NotYetConnectedException {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.websocket.WebSocketClient.write(com.amazon.communication.ByteBufferChain):int");
    }

    private void close(CloseReason closeReason, CloseDetail closeDetail) {
        log.debug("close", "close", "closeReason", closeReason, "closeDetails", closeDetail);
        try {
            try {
                if (this.mConnectionStatus != ConnectionStatus.CLOSING) {
                    sendClose(1000);
                    this.mConnectionStatus = ConnectionStatus.CLOSING;
                }
            } catch (IOException e) {
                log.warn("close", "failed to send close frame to the other side", e);
            }
        } finally {
            closeAndCleanup(closeReason, closeDetail);
        }
    }

    private long writeToSocketChannel(ByteBuffer[] byteBufferArr) throws IOException {
        try {
            return this.mWritableChannel.write(byteBufferArr);
        } catch (NotYetConnectedException e) {
            log.error("writeToSocketChannel", "exception when sending data", e);
            throw new IOException("Exception when sending data", e);
        }
    }

    public WebSocketClient(WebSocketListener webSocketListener) {
        this(null, webSocketListener, null, "GET");
    }

    public WebSocketClient(SocketChannel socketChannel, WebSocketListener webSocketListener) {
        this(socketChannel, webSocketListener, null, "GET");
    }
}
