package org.apache.thrift.orig.server;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.thrift.orig.TByteArrayOutputStream;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.server.TServer;
import org.apache.thrift.orig.transport.TFramedTransport;
import org.apache.thrift.orig.transport.TIOStreamTransport;
import org.apache.thrift.orig.transport.TMemoryInputTransport;
import org.apache.thrift.orig.transport.TNonblockingServerTransport;
import org.apache.thrift.orig.transport.TNonblockingTransport;
import org.apache.thrift.orig.transport.TTransport;
import org.apache.thrift.orig.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public abstract class AbstractNonblockingServer extends TServer {
    protected final Logger LOGGER;
    private final long MAX_READ_BUFFER_BYTES;
    private final AtomicLong readBufferBytesAllocated;

    /* loaded from: classes4.dex */
    public static abstract class AbstractNonblockingServerArgs<T extends AbstractNonblockingServerArgs<T>> extends TServer.AbstractServerArgs<T> {
        public long maxReadBufferBytes;

        public AbstractNonblockingServerArgs(TNonblockingServerTransport tNonblockingServerTransport) {
            super(tNonblockingServerTransport);
            this.maxReadBufferBytes = Long.MAX_VALUE;
            transportFactory(new TFramedTransport.Factory());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public abstract class AbstractSelectThread extends Thread {
        protected final Set<FrameBuffer> selectInterestChanges = new HashSet();
        protected final Selector selector = SelectorProvider.provider().openSelector();

        public AbstractSelectThread() throws IOException {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void cleanupSelectionKey(SelectionKey selectionKey) {
            FrameBuffer frameBuffer = (FrameBuffer) selectionKey.attachment();
            if (frameBuffer != null) {
                frameBuffer.close();
            }
            selectionKey.cancel();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void handleRead(SelectionKey selectionKey) {
            FrameBuffer frameBuffer = (FrameBuffer) selectionKey.attachment();
            if (!frameBuffer.read()) {
                cleanupSelectionKey(selectionKey);
            } else if (!frameBuffer.isFrameFullyRead() || AbstractNonblockingServer.this.requestInvoke(frameBuffer)) {
            } else {
                cleanupSelectionKey(selectionKey);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void handleWrite(SelectionKey selectionKey) {
            if (!((FrameBuffer) selectionKey.attachment()).write()) {
                cleanupSelectionKey(selectionKey);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void processInterestChanges() {
            synchronized (this.selectInterestChanges) {
                for (FrameBuffer frameBuffer : this.selectInterestChanges) {
                    frameBuffer.changeSelectInterests();
                }
                this.selectInterestChanges.clear();
            }
        }

        public void requestSelectInterestChange(FrameBuffer frameBuffer) {
            synchronized (this.selectInterestChanges) {
                this.selectInterestChanges.add(frameBuffer);
            }
            this.selector.wakeup();
        }

        public void wakeupSelector() {
            this.selector.wakeup();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class FrameBuffer {
        private TByteArrayOutputStream response_;
        private final AbstractSelectThread selectThread_;
        private final SelectionKey selectionKey_;
        public final TNonblockingTransport trans_;
        private FrameBufferState state_ = FrameBufferState.READING_FRAME_SIZE;
        private ByteBuffer buffer_ = ByteBuffer.allocate(4);

        public FrameBuffer(TNonblockingTransport tNonblockingTransport, SelectionKey selectionKey, AbstractSelectThread abstractSelectThread) {
            this.trans_ = tNonblockingTransport;
            this.selectionKey_ = selectionKey;
            this.selectThread_ = abstractSelectThread;
        }

        private TTransport getInputTransport() {
            return AbstractNonblockingServer.this.inputTransportFactory_.getTransport(new TMemoryInputTransport(this.buffer_.array()));
        }

        private TTransport getOutputTransport() {
            this.response_ = new TByteArrayOutputStream();
            return AbstractNonblockingServer.this.outputTransportFactory_.getTransport(new TIOStreamTransport(this.response_));
        }

        private boolean internalRead() {
            try {
                return this.trans_.read(this.buffer_) >= 0;
            } catch (IOException e) {
                AbstractNonblockingServer.this.LOGGER.warn("Got an IOException in internalRead!", (Throwable) e);
                return false;
            }
        }

        private void prepareRead() {
            this.selectionKey_.interestOps(1);
            this.buffer_ = ByteBuffer.allocate(4);
            this.state_ = FrameBufferState.READING_FRAME_SIZE;
        }

        private void requestSelectInterestChange() {
            Thread currentThread = Thread.currentThread();
            AbstractSelectThread abstractSelectThread = this.selectThread_;
            if (currentThread == abstractSelectThread) {
                changeSelectInterests();
            } else {
                abstractSelectThread.requestSelectInterestChange(this);
            }
        }

        public void changeSelectInterests() {
            FrameBufferState frameBufferState = this.state_;
            if (frameBufferState == FrameBufferState.AWAITING_REGISTER_WRITE) {
                this.selectionKey_.interestOps(4);
                this.state_ = FrameBufferState.WRITING;
            } else if (frameBufferState == FrameBufferState.AWAITING_REGISTER_READ) {
                prepareRead();
            } else if (frameBufferState == FrameBufferState.AWAITING_CLOSE) {
                close();
                this.selectionKey_.cancel();
            } else {
                Logger logger = AbstractNonblockingServer.this.LOGGER;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("changeSelectInterest was called, but state is invalid (");
                outline107.append(this.state_);
                outline107.append(")");
                logger.error(outline107.toString());
            }
        }

        public void close() {
            FrameBufferState frameBufferState = this.state_;
            if (frameBufferState == FrameBufferState.READING_FRAME || frameBufferState == FrameBufferState.READ_FRAME_COMPLETE) {
                AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet(-this.buffer_.array().length);
            }
            this.trans_.close();
        }

        public void invoke() {
            TTransport inputTransport = getInputTransport();
            try {
                AbstractNonblockingServer.this.processorFactory_.getProcessor(inputTransport).process(AbstractNonblockingServer.this.inputProtocolFactory_.getProtocol(inputTransport), AbstractNonblockingServer.this.outputProtocolFactory_.getProtocol(getOutputTransport()));
                responseReady();
            } catch (TException e) {
                AbstractNonblockingServer.this.LOGGER.warn("Exception while invoking!", (Throwable) e);
                this.state_ = FrameBufferState.AWAITING_CLOSE;
                requestSelectInterestChange();
            } catch (Throwable th) {
                AbstractNonblockingServer.this.LOGGER.error("Unexpected throwable while invoking!", th);
                this.state_ = FrameBufferState.AWAITING_CLOSE;
                requestSelectInterestChange();
            }
        }

        public boolean isFrameFullyRead() {
            return this.state_ == FrameBufferState.READ_FRAME_COMPLETE;
        }

        public boolean read() {
            if (this.state_ == FrameBufferState.READING_FRAME_SIZE) {
                if (!internalRead()) {
                    return false;
                }
                if (this.buffer_.remaining() != 0) {
                    return true;
                }
                int i = this.buffer_.getInt(0);
                if (i > 0) {
                    long j = i;
                    if (j <= AbstractNonblockingServer.this.MAX_READ_BUFFER_BYTES) {
                        if (AbstractNonblockingServer.this.readBufferBytesAllocated.get() + j > AbstractNonblockingServer.this.MAX_READ_BUFFER_BYTES) {
                            return true;
                        }
                        int i2 = i + 4;
                        AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet(i2);
                        this.buffer_ = ByteBuffer.allocate(i2);
                        this.buffer_.putInt(i);
                        this.state_ = FrameBufferState.READING_FRAME;
                    } else {
                        AbstractNonblockingServer.this.LOGGER.error("Read a frame size of " + i + ", which is bigger than the maximum allowable buffer size for ALL connections.");
                        return false;
                    }
                } else {
                    AbstractNonblockingServer.this.LOGGER.error("Read an invalid frame size of " + i + ". Are you using TFramedTransport on the client side?");
                    return false;
                }
            }
            if (this.state_ == FrameBufferState.READING_FRAME) {
                if (!internalRead()) {
                    return false;
                }
                if (this.buffer_.remaining() == 0) {
                    this.selectionKey_.interestOps(0);
                    this.state_ = FrameBufferState.READ_FRAME_COMPLETE;
                }
                return true;
            }
            Logger logger = AbstractNonblockingServer.this.LOGGER;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Read was called but state is invalid (");
            outline107.append(this.state_);
            outline107.append(")");
            logger.error(outline107.toString());
            return false;
        }

        public void responseReady() {
            AbstractNonblockingServer.this.readBufferBytesAllocated.addAndGet(-this.buffer_.array().length);
            if (this.response_.len() == 0) {
                this.state_ = FrameBufferState.AWAITING_REGISTER_READ;
                this.buffer_ = null;
            } else {
                this.buffer_ = ByteBuffer.wrap(this.response_.get(), 0, this.response_.len());
                this.state_ = FrameBufferState.AWAITING_REGISTER_WRITE;
            }
            requestSelectInterestChange();
        }

        public boolean write() {
            if (this.state_ == FrameBufferState.WRITING) {
                try {
                    if (this.trans_.write(this.buffer_) < 0) {
                        return false;
                    }
                    if (this.buffer_.remaining() != 0) {
                        return true;
                    }
                    prepareRead();
                    return true;
                } catch (IOException e) {
                    AbstractNonblockingServer.this.LOGGER.warn("Got an IOException during write!", (Throwable) e);
                    return false;
                }
            }
            Logger logger = AbstractNonblockingServer.this.LOGGER;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Write was called, but state is invalid (");
            outline107.append(this.state_);
            outline107.append(")");
            logger.error(outline107.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum FrameBufferState {
        READING_FRAME_SIZE,
        READING_FRAME,
        READ_FRAME_COMPLETE,
        AWAITING_REGISTER_WRITE,
        WRITING,
        AWAITING_REGISTER_READ,
        AWAITING_CLOSE
    }

    public AbstractNonblockingServer(AbstractNonblockingServerArgs abstractNonblockingServerArgs) {
        super(abstractNonblockingServerArgs);
        this.LOGGER = LoggerFactory.getLogger(getClass().getName());
        this.readBufferBytesAllocated = new AtomicLong(0L);
        this.MAX_READ_BUFFER_BYTES = abstractNonblockingServerArgs.maxReadBufferBytes;
    }

    @Override // org.apache.thrift.orig.server.TServer
    public TServerEventHandler getEventHandler() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected abstract boolean requestInvoke(FrameBuffer frameBuffer);

    @Override // org.apache.thrift.orig.server.TServer
    public void serve() {
        if (startThreads() && startListening()) {
            setServing(true);
            waitForShutdown();
            setServing(false);
            stopListening();
        }
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void setServerEventHandler(TServerEventHandler tServerEventHandler) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected boolean startListening() {
        try {
            this.serverTransport_.listen();
            return true;
        } catch (TTransportException e) {
            this.LOGGER.error("Failed to start listening on server socket!", (Throwable) e);
            return false;
        }
    }

    protected abstract boolean startThreads();

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopListening() {
        this.serverTransport_.close();
    }

    protected abstract void waitForShutdown();
}
