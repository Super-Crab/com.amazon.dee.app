package com.amazon.communication;

import amazon.communication.Message;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferChainMessageImpl implements ByteBufferBackedMessage, Message {
    public static final int MAX_BYTE_BUFFER_BYTES_FROM_INPUTSTREAM = 16384;
    private static final DPLogger log = new DPLogger("TComm.ByteBufferChainMessageImpl");
    private final ByteBufferChain mByteBufferChain;
    private int mPayloadSize;
    private InputStream mPreviousPayload;
    private boolean mSavedPositions;

    public ByteBufferChainMessageImpl(ByteBuffer byteBuffer) {
        this.mSavedPositions = false;
        this.mByteBufferChain = new ByteBufferChain(byteBuffer);
        calculatePayloadSize();
    }

    private void calculatePayloadSize() {
        this.mPayloadSize = this.mByteBufferChain.getDataSize();
    }

    private void enforceSavedPositions() {
        if (!this.mSavedPositions) {
            return;
        }
        throw new IllegalStateException("Message can't be modified once getPayload is called.");
    }

    private void resetByteBufferChain() {
        if (this.mSavedPositions) {
            this.mByteBufferChain.loadSavedPositions();
        }
        this.mByteBufferChain.savePositions();
        this.mSavedPositions = true;
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public void appendPayload(Message message) {
        enforceSavedPositions();
        if (message.getPayloadSize() != 0) {
            if (message instanceof ByteBufferBackedMessage) {
                this.mByteBufferChain.append(((ByteBufferBackedMessage) message).getByteBufferChain());
            } else if (message instanceof InputStreamMessageImpl) {
                InputStream payload = message.getPayload();
                try {
                    if (appendPayload(payload, 16384) == 16384 && payload.available() > 0 && payload.read() != -1) {
                        throw new IllegalArgumentException("InputStreamMessageImpl has more bytes than our limit. Can't make copy");
                    }
                } catch (IOException e) {
                    throw new UnsupportedOperationException("IOException when reading data from InputStreamMessageImpl", e);
                }
            } else {
                throw new IllegalArgumentException("Message is not backed up by ByteBuffers or InputStream. Something wrong here.");
            }
            calculatePayloadSize();
            return;
        }
        log.verbose("appendPayload", "attempting to append a Message with zero content", new Object[0]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ByteBufferChainMessageImpl.class != obj.getClass()) {
            return false;
        }
        ByteBufferChainMessageImpl byteBufferChainMessageImpl = (ByteBufferChainMessageImpl) obj;
        ByteBufferChain byteBufferChain = this.mByteBufferChain;
        if (byteBufferChain == null) {
            if (byteBufferChainMessageImpl.mByteBufferChain != null) {
                return false;
            }
        } else if (!byteBufferChain.equals(byteBufferChainMessageImpl.mByteBufferChain)) {
            return false;
        }
        return true;
    }

    @Override // amazon.communication.Message
    public Message extractPayload() {
        return new ByteBufferChainMessageImpl(new ByteBufferChain(this.mByteBufferChain));
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public ByteBufferChain getByteBufferChain() {
        return this.mByteBufferChain;
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public ByteBuffer[] getByteBuffers() {
        return this.mByteBufferChain.getByteBuffers();
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public InputStream getInputStream() {
        return this.mByteBufferChain.getInputStream();
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public OutputStream getOutputStream() {
        return this.mByteBufferChain.getOutputStream();
    }

    @Override // amazon.communication.Message
    public InputStream getPayload() {
        InputStream inputStream = this.mPreviousPayload;
        if (inputStream != null) {
            try {
                if (inputStream.available() > 0) {
                    throw new IllegalStateException("Unconsumed bytes found in previously returned payload. Not allowed to process multiple InputStreams returned from getPayload concurrently.");
                }
            } catch (IOException e) {
                log.warn("getPayload", "unexpected exception while trying to find the size of previously returned payload", e);
            }
        }
        resetByteBufferChain();
        this.mPreviousPayload = this.mByteBufferChain.getInputStream();
        return this.mPreviousPayload;
    }

    @Override // amazon.communication.Message
    public int getPayloadSize() {
        return this.mPayloadSize;
    }

    public int hashCode() {
        ByteBufferChain byteBufferChain = this.mByteBufferChain;
        return 31 + (byteBufferChain == null ? 0 : byteBufferChain.hashCode());
    }

    @Override // amazon.communication.Message
    public void prependPayload(ByteBuffer byteBuffer) {
        enforceSavedPositions();
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            this.mByteBufferChain.prepend(byteBuffer);
            calculatePayloadSize();
            return;
        }
        log.verbose("prependPayload", "attempting to prepend a ByteBuffer with zero content", new Object[0]);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ByteBufferChainMessageImpl [mByteBufferChain=");
        outline107.append(this.mByteBufferChain);
        outline107.append("]");
        return outline107.toString();
    }

    public ByteBufferChainMessageImpl(ByteBuffer[] byteBufferArr) {
        this.mSavedPositions = false;
        this.mByteBufferChain = new ByteBufferChain(byteBufferArr);
        calculatePayloadSize();
    }

    public ByteBufferChainMessageImpl(ByteBufferChain byteBufferChain) {
        this.mSavedPositions = false;
        if (byteBufferChain != null) {
            this.mByteBufferChain = byteBufferChain;
            calculatePayloadSize();
            return;
        }
        throw new IllegalArgumentException("ByteBufferChain cannot be null");
    }

    public ByteBufferChainMessageImpl(Message message) {
        this.mSavedPositions = false;
        this.mByteBufferChain = new ByteBufferChain();
        appendPayload(message);
        calculatePayloadSize();
    }

    @Override // com.amazon.communication.ByteBufferBackedMessage
    public int appendPayload(InputStream inputStream, int i) throws IOException {
        enforceSavedPositions();
        if (inputStream != null) {
            if (i >= 1) {
                try {
                    int available = inputStream.available();
                    int append = this.mByteBufferChain.append(inputStream, i);
                    if (append > 0) {
                        calculatePayloadSize();
                    }
                    log.debug("appendPayload", "finished appending", "bytesRead", Integer.valueOf(append), "availableBytes", Integer.valueOf(available), "mByteBufferChain.getDataSize", Integer.valueOf(this.mByteBufferChain.getDataSize()), "payloadBytes", Integer.valueOf(i));
                    return append;
                } catch (IOException e) {
                    log.error("appendPayload", "IOException when reading data from InputStream", "inputStream", inputStream, "payloadBytes", Integer.valueOf(i), e);
                    throw e;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Payload bytes can't be 0 or negative. payloadBytes: ", i));
        }
        throw new IllegalArgumentException("InputStream can't be null");
    }

    public ByteBufferChainMessageImpl(InputStream inputStream, int i) throws IOException {
        this.mSavedPositions = false;
        this.mByteBufferChain = new ByteBufferChain();
        appendPayload(inputStream, i);
        calculatePayloadSize();
    }

    public ByteBufferChainMessageImpl() {
        this(new ByteBufferChain());
    }

    @Override // amazon.communication.Message
    public void appendPayload(ByteBuffer byteBuffer) {
        enforceSavedPositions();
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            this.mByteBufferChain.append(byteBuffer);
            calculatePayloadSize();
            return;
        }
        log.verbose("appendPayload", "attempting to append a ByteBuffer with zero content", new Object[0]);
    }
}
