package org.apache.thrift.orig.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import org.apache.thrift.orig.server.AbstractNonblockingServer;
import org.apache.thrift.orig.transport.TNonblockingServerTransport;
import org.apache.thrift.orig.transport.TNonblockingTransport;
import org.apache.thrift.orig.transport.TTransportException;
import org.slf4j.Logger;
/* loaded from: classes4.dex */
public class TNonblockingServer extends AbstractNonblockingServer {
    private SelectAcceptThread selectAcceptThread_;
    private volatile boolean stopped_;

    /* loaded from: classes4.dex */
    public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs<Args> {
        public Args(TNonblockingServerTransport tNonblockingServerTransport) {
            super(tNonblockingServerTransport);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class SelectAcceptThread extends AbstractNonblockingServer.AbstractSelectThread {
        private final TNonblockingServerTransport serverTransport;

        public SelectAcceptThread(TNonblockingServerTransport tNonblockingServerTransport) throws IOException {
            super();
            this.serverTransport = tNonblockingServerTransport;
            tNonblockingServerTransport.registerSelector(this.selector);
        }

        private void handleAccept() throws IOException {
            TNonblockingTransport tNonblockingTransport;
            SelectionKey selectionKey = null;
            try {
                tNonblockingTransport = (TNonblockingTransport) this.serverTransport.accept();
            } catch (TTransportException e) {
                e = e;
                tNonblockingTransport = null;
            }
            try {
                selectionKey = tNonblockingTransport.registerSelector(this.selector, 1);
                selectionKey.attach(new AbstractNonblockingServer.FrameBuffer(tNonblockingTransport, selectionKey, this));
            } catch (TTransportException e2) {
                e = e2;
                TNonblockingServer.this.LOGGER.warn("Exception trying to accept!", (Throwable) e);
                e.printStackTrace();
                if (selectionKey != null) {
                    cleanupSelectionKey(selectionKey);
                }
                if (tNonblockingTransport == null) {
                    return;
                }
                tNonblockingTransport.close();
            }
        }

        private void select() {
            try {
                this.selector.select();
                Iterator<SelectionKey> it2 = this.selector.selectedKeys().iterator();
                while (!TNonblockingServer.this.stopped_ && it2.hasNext()) {
                    SelectionKey next = it2.next();
                    it2.remove();
                    if (!next.isValid()) {
                        cleanupSelectionKey(next);
                    } else if (next.isAcceptable()) {
                        handleAccept();
                    } else if (next.isReadable()) {
                        handleRead(next);
                    } else if (next.isWritable()) {
                        handleWrite(next);
                    } else {
                        Logger logger = TNonblockingServer.this.LOGGER;
                        logger.warn("Unexpected state in select! " + next.interestOps());
                    }
                }
            } catch (IOException e) {
                TNonblockingServer.this.LOGGER.warn("Got an IOException while selecting!", (Throwable) e);
            }
        }

        public boolean isStopped() {
            return TNonblockingServer.this.stopped_;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    if (TNonblockingServer.this.stopped_) {
                        break;
                    }
                    select();
                    processInterestChanges();
                } finally {
                    try {
                    } finally {
                    }
                }
            }
            for (SelectionKey selectionKey : this.selector.keys()) {
                cleanupSelectionKey(selectionKey);
            }
        }
    }

    public TNonblockingServer(AbstractNonblockingServer.AbstractNonblockingServerArgs abstractNonblockingServerArgs) {
        super(abstractNonblockingServerArgs);
        this.stopped_ = true;
    }

    public boolean isStopped() {
        return this.selectAcceptThread_.isStopped();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void joinSelector() {
        try {
            this.selectAcceptThread_.join();
        } catch (InterruptedException unused) {
        }
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
        frameBuffer.invoke();
        return true;
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected boolean startThreads() {
        try {
            this.selectAcceptThread_ = new SelectAcceptThread((TNonblockingServerTransport) this.serverTransport_);
            this.stopped_ = false;
            this.selectAcceptThread_.start();
            return true;
        } catch (IOException e) {
            this.LOGGER.error("Failed to start selector thread!", (Throwable) e);
            return false;
        }
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void stop() {
        this.stopped_ = true;
        SelectAcceptThread selectAcceptThread = this.selectAcceptThread_;
        if (selectAcceptThread != null) {
            selectAcceptThread.wakeupSelector();
        }
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected void waitForShutdown() {
        joinSelector();
    }
}
