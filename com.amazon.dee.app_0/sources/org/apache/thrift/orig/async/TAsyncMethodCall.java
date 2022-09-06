package org.apache.thrift.orig.async;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolFactory;
import org.apache.thrift.orig.transport.TFramedTransport;
import org.apache.thrift.orig.transport.TMemoryBuffer;
import org.apache.thrift.orig.transport.TNonblockingTransport;
import org.apache.thrift.orig.transport.TTransportException;
/* loaded from: classes4.dex */
public abstract class TAsyncMethodCall<T> {
    private static final int INITIAL_MEMORY_BUFFER_SIZE = 128;
    private static AtomicLong sequenceIdCounter = new AtomicLong(0);
    private final AsyncMethodCallback<T> callback;
    protected final TAsyncClient client;
    private ByteBuffer frameBuffer;
    private final boolean isOneway;
    private final TProtocolFactory protocolFactory;
    private ByteBuffer sizeBuffer;
    protected final TNonblockingTransport transport;
    private State state = null;
    private final byte[] sizeBufferArray = new byte[4];
    private long startTime = System.currentTimeMillis();
    private long sequenceId = sequenceIdCounter.getAndIncrement();

    /* renamed from: org.apache.thrift.orig.async.TAsyncMethodCall$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State[State.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State[State.WRITING_REQUEST_SIZE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State[State.WRITING_REQUEST_BODY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State[State.READING_RESPONSE_SIZE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$thrift$async$TAsyncMethodCall$State[State.READING_RESPONSE_BODY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum State {
        CONNECTING,
        WRITING_REQUEST_SIZE,
        WRITING_REQUEST_BODY,
        READING_RESPONSE_SIZE,
        READING_RESPONSE_BODY,
        RESPONSE_READ,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TAsyncMethodCall(TAsyncClient tAsyncClient, TProtocolFactory tProtocolFactory, TNonblockingTransport tNonblockingTransport, AsyncMethodCallback<T> asyncMethodCallback, boolean z) {
        this.transport = tNonblockingTransport;
        this.callback = asyncMethodCallback;
        this.protocolFactory = tProtocolFactory;
        this.client = tAsyncClient;
        this.isOneway = z;
    }

    private void cleanUpAndFireCallback(SelectionKey selectionKey) {
        this.state = State.RESPONSE_READ;
        selectionKey.interestOps(0);
        selectionKey.attach(null);
        this.client.onComplete();
        this.callback.onComplete(this);
    }

    private void doConnecting(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable() && this.transport.finishConnect()) {
            registerForFirstWrite(selectionKey);
            return;
        }
        throw new IOException("not connectable or finishConnect returned false after we got an OP_CONNECT");
    }

    private void doReadingResponseBody(SelectionKey selectionKey) throws IOException {
        if (this.transport.read(this.frameBuffer) >= 0) {
            if (this.frameBuffer.remaining() != 0) {
                return;
            }
            cleanUpAndFireCallback(selectionKey);
            return;
        }
        throw new IOException("Read call frame failed");
    }

    private void doReadingResponseSize() throws IOException {
        if (this.transport.read(this.sizeBuffer) >= 0) {
            if (this.sizeBuffer.remaining() != 0) {
                return;
            }
            this.state = State.READING_RESPONSE_BODY;
            this.frameBuffer = ByteBuffer.allocate(TFramedTransport.decodeFrameSize(this.sizeBufferArray));
            return;
        }
        throw new IOException("Read call frame size failed");
    }

    private void doWritingRequestBody(SelectionKey selectionKey) throws IOException {
        if (this.transport.write(this.frameBuffer) >= 0) {
            if (this.frameBuffer.remaining() != 0) {
                return;
            }
            if (this.isOneway) {
                cleanUpAndFireCallback(selectionKey);
                return;
            }
            this.state = State.READING_RESPONSE_SIZE;
            this.sizeBuffer.rewind();
            selectionKey.interestOps(1);
            return;
        }
        throw new IOException("Write call frame failed");
    }

    private void doWritingRequestSize() throws IOException {
        if (this.transport.write(this.sizeBuffer) >= 0) {
            if (this.sizeBuffer.remaining() != 0) {
                return;
            }
            this.state = State.WRITING_REQUEST_BODY;
            return;
        }
        throw new IOException("Write call frame size failed");
    }

    public TAsyncClient getClient() {
        return this.client;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer getFrameBuffer() {
        return this.frameBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getSequenceId() {
        return this.sequenceId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public State getState() {
        return this.state;
    }

    public long getTimeoutTimestamp() {
        return this.client.getTimeout() + this.startTime;
    }

    public boolean hasTimeout() {
        return this.client.hasTimeout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFinished() {
        return this.state == State.RESPONSE_READ;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onError(Exception exc) {
        this.client.onError(exc);
        this.callback.onError(exc);
        this.state = State.ERROR;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void prepareMethodCall() throws TException {
        TMemoryBuffer tMemoryBuffer = new TMemoryBuffer(128);
        write_args(this.protocolFactory.getProtocol(tMemoryBuffer));
        int length = tMemoryBuffer.length();
        this.frameBuffer = ByteBuffer.wrap(tMemoryBuffer.getArray(), 0, length);
        TFramedTransport.encodeFrameSize(length, this.sizeBufferArray);
        this.sizeBuffer = ByteBuffer.wrap(this.sizeBufferArray);
    }

    void registerForFirstWrite(SelectionKey selectionKey) throws IOException {
        this.state = State.WRITING_REQUEST_SIZE;
        selectionKey.interestOps(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(Selector selector) throws IOException {
        SelectionKey registerSelector;
        if (this.transport.isOpen()) {
            this.state = State.WRITING_REQUEST_SIZE;
            registerSelector = this.transport.registerSelector(selector, 4);
        } else {
            this.state = State.CONNECTING;
            registerSelector = this.transport.registerSelector(selector, 8);
            if (this.transport.startConnect()) {
                registerForFirstWrite(registerSelector);
            }
        }
        registerSelector.attach(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void transition(SelectionKey selectionKey) {
        if (!selectionKey.isValid()) {
            selectionKey.cancel();
            onError(new TTransportException("Selection key not valid!"));
            return;
        }
        try {
            int ordinal = this.state.ordinal();
            if (ordinal == 0) {
                doConnecting(selectionKey);
            } else if (ordinal == 1) {
                doWritingRequestSize();
            } else if (ordinal == 2) {
                doWritingRequestBody(selectionKey);
            } else if (ordinal == 3) {
                doReadingResponseSize();
            } else if (ordinal == 4) {
                doReadingResponseBody(selectionKey);
            } else {
                throw new IllegalStateException("Method call in state " + this.state + " but selector called transition method. Seems like a bug...");
            }
        } catch (Exception e) {
            selectionKey.cancel();
            selectionKey.attach(null);
            onError(e);
        }
    }

    protected abstract void write_args(TProtocol tProtocol) throws TException;
}
