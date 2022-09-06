package org.apache.thrift.orig.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.orig.server.AbstractNonblockingServer;
import org.apache.thrift.orig.transport.TNonblockingServerTransport;
import org.apache.thrift.orig.transport.TNonblockingTransport;
import org.apache.thrift.orig.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TThreadedSelectorServer extends AbstractNonblockingServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TThreadedSelectorServer.class.getName());
    private AcceptThread acceptThread;
    private final Args args;
    private final ExecutorService invoker;
    private final Set<SelectorThread> selectorThreads;
    private volatile boolean stopped_;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class AcceptThread extends Thread {
        private final Selector acceptSelector = SelectorProvider.provider().openSelector();
        private final TNonblockingServerTransport serverTransport;
        private final SelectorThreadLoadBalancer threadChooser;

        public AcceptThread(TNonblockingServerTransport tNonblockingServerTransport, SelectorThreadLoadBalancer selectorThreadLoadBalancer) throws IOException {
            this.serverTransport = tNonblockingServerTransport;
            this.threadChooser = selectorThreadLoadBalancer;
            this.serverTransport.registerSelector(this.acceptSelector);
        }

        private TNonblockingTransport doAccept() {
            try {
                return (TNonblockingTransport) this.serverTransport.accept();
            } catch (TTransportException e) {
                TThreadedSelectorServer.LOGGER.warn("Exception trying to accept!", (Throwable) e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doAddAccept(SelectorThread selectorThread, TNonblockingTransport tNonblockingTransport) {
            if (!selectorThread.addAcceptedConnection(tNonblockingTransport)) {
                tNonblockingTransport.close();
            }
        }

        private void handleAccept() {
            final TNonblockingTransport doAccept = doAccept();
            if (doAccept != null) {
                final SelectorThread nextThread = this.threadChooser.nextThread();
                if (TThreadedSelectorServer.this.args.acceptPolicy != Args.AcceptPolicy.FAST_ACCEPT && TThreadedSelectorServer.this.invoker != null) {
                    try {
                        TThreadedSelectorServer.this.invoker.submit(new Runnable() { // from class: org.apache.thrift.orig.server.TThreadedSelectorServer.AcceptThread.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AcceptThread.this.doAddAccept(nextThread, doAccept);
                            }
                        });
                        return;
                    } catch (RejectedExecutionException e) {
                        TThreadedSelectorServer.LOGGER.warn("ExecutorService rejected accept registration!", (Throwable) e);
                        doAccept.close();
                        return;
                    }
                }
                doAddAccept(nextThread, doAccept);
            }
        }

        private void select() {
            try {
                this.acceptSelector.select();
                Iterator<SelectionKey> it2 = this.acceptSelector.selectedKeys().iterator();
                while (!TThreadedSelectorServer.this.stopped_ && it2.hasNext()) {
                    SelectionKey next = it2.next();
                    it2.remove();
                    if (next.isValid()) {
                        if (!next.isAcceptable()) {
                            Logger logger = TThreadedSelectorServer.LOGGER;
                            logger.warn("Unexpected state in select! " + next.interestOps());
                        } else {
                            handleAccept();
                        }
                    }
                }
            } catch (IOException e) {
                TThreadedSelectorServer.LOGGER.warn("Got an IOException while selecting!", (Throwable) e);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!TThreadedSelectorServer.this.stopped_) {
                try {
                    select();
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        public void wakeupSelector() {
            this.acceptSelector.wakeup();
        }
    }

    /* loaded from: classes4.dex */
    public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs<Args> {
        private AcceptPolicy acceptPolicy;
        private int acceptQueueSizePerThread;
        private ExecutorService executorService;
        public int selectorThreads;
        private TimeUnit stopTimeoutUnit;
        private int stopTimeoutVal;
        private int workerThreads;

        /* loaded from: classes4.dex */
        public enum AcceptPolicy {
            FAIR_ACCEPT,
            FAST_ACCEPT
        }

        public Args(TNonblockingServerTransport tNonblockingServerTransport) {
            super(tNonblockingServerTransport);
            this.selectorThreads = 2;
            this.workerThreads = 5;
            this.stopTimeoutVal = 60;
            this.stopTimeoutUnit = TimeUnit.SECONDS;
            this.executorService = null;
            this.acceptQueueSizePerThread = 4;
            this.acceptPolicy = AcceptPolicy.FAST_ACCEPT;
        }

        public Args acceptPolicy(AcceptPolicy acceptPolicy) {
            this.acceptPolicy = acceptPolicy;
            return this;
        }

        public Args acceptQueueSizePerThread(int i) {
            this.acceptQueueSizePerThread = i;
            return this;
        }

        public Args executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public AcceptPolicy getAcceptPolicy() {
            return this.acceptPolicy;
        }

        public int getAcceptQueueSizePerThread() {
            return this.acceptQueueSizePerThread;
        }

        public ExecutorService getExecutorService() {
            return this.executorService;
        }

        public int getSelectorThreads() {
            return this.selectorThreads;
        }

        public TimeUnit getStopTimeoutUnit() {
            return this.stopTimeoutUnit;
        }

        public int getStopTimeoutVal() {
            return this.stopTimeoutVal;
        }

        public int getWorkerThreads() {
            return this.workerThreads;
        }

        public Args selectorThreads(int i) {
            this.selectorThreads = i;
            return this;
        }

        public Args stopTimeoutUnit(TimeUnit timeUnit) {
            this.stopTimeoutUnit = timeUnit;
            return this;
        }

        public Args stopTimeoutVal(int i) {
            this.stopTimeoutVal = i;
            return this;
        }

        public void validate() {
            if (this.selectorThreads > 0) {
                if (this.workerThreads >= 0) {
                    if (this.acceptQueueSizePerThread <= 0) {
                        throw new IllegalArgumentException("acceptQueueSizePerThread must be positive.");
                    }
                    return;
                }
                throw new IllegalArgumentException("workerThreads must be non-negative.");
            }
            throw new IllegalArgumentException("selectorThreads must be positive.");
        }

        public Args workerThreads(int i) {
            this.workerThreads = i;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class SelectorThread extends AbstractNonblockingServer.AbstractSelectThread {
        private final BlockingQueue<TNonblockingTransport> acceptedQueue;

        public SelectorThread(TThreadedSelectorServer tThreadedSelectorServer) throws IOException {
            this(new LinkedBlockingQueue());
        }

        private void processAcceptedConnections() {
            TNonblockingTransport poll;
            while (!TThreadedSelectorServer.this.stopped_ && (poll = this.acceptedQueue.poll()) != null) {
                registerAccepted(poll);
            }
        }

        private void registerAccepted(TNonblockingTransport tNonblockingTransport) {
            SelectionKey selectionKey = null;
            try {
                selectionKey = tNonblockingTransport.registerSelector(this.selector, 1);
                selectionKey.attach(new AbstractNonblockingServer.FrameBuffer(tNonblockingTransport, selectionKey, this));
            } catch (IOException e) {
                TThreadedSelectorServer.LOGGER.warn("Failed to register accepted connection to selector!", (Throwable) e);
                if (selectionKey != null) {
                    cleanupSelectionKey(selectionKey);
                }
                tNonblockingTransport.close();
            }
        }

        private void select() {
            try {
                this.selector.select();
                Iterator<SelectionKey> it2 = this.selector.selectedKeys().iterator();
                while (!TThreadedSelectorServer.this.stopped_ && it2.hasNext()) {
                    SelectionKey next = it2.next();
                    it2.remove();
                    if (!next.isValid()) {
                        cleanupSelectionKey(next);
                    } else if (next.isReadable()) {
                        handleRead(next);
                    } else if (!next.isWritable()) {
                        Logger logger = TThreadedSelectorServer.LOGGER;
                        logger.warn("Unexpected state in select! " + next.interestOps());
                    } else {
                        handleWrite(next);
                    }
                }
            } catch (IOException e) {
                TThreadedSelectorServer.LOGGER.warn("Got an IOException while selecting!", (Throwable) e);
            }
        }

        public boolean addAcceptedConnection(TNonblockingTransport tNonblockingTransport) {
            try {
                this.acceptedQueue.put(tNonblockingTransport);
                this.selector.wakeup();
                return true;
            } catch (InterruptedException e) {
                TThreadedSelectorServer.LOGGER.warn("Interrupted while adding accepted connection!", (Throwable) e);
                return false;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!TThreadedSelectorServer.this.stopped_) {
                try {
                    select();
                    processAcceptedConnections();
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

        public SelectorThread(TThreadedSelectorServer tThreadedSelectorServer, int i) throws IOException {
            this(TThreadedSelectorServer.createDefaultAcceptQueue(i));
        }

        public SelectorThread(BlockingQueue<TNonblockingTransport> blockingQueue) throws IOException {
            super();
            this.acceptedQueue = blockingQueue;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class SelectorThreadLoadBalancer {
        private Iterator<? extends SelectorThread> nextThreadIterator;
        private final Collection<? extends SelectorThread> threads;

        public <T extends SelectorThread> SelectorThreadLoadBalancer(Collection<T> collection) {
            if (!collection.isEmpty()) {
                this.threads = Collections.unmodifiableList(new ArrayList(collection));
                this.nextThreadIterator = this.threads.iterator();
                return;
            }
            throw new IllegalArgumentException("At least one selector thread is required");
        }

        public SelectorThread nextThread() {
            if (!this.nextThreadIterator.hasNext()) {
                this.nextThreadIterator = this.threads.iterator();
            }
            return this.nextThreadIterator.next();
        }
    }

    public TThreadedSelectorServer(Args args) {
        super(args);
        this.stopped_ = true;
        this.selectorThreads = new HashSet();
        args.validate();
        this.invoker = args.executorService == null ? createDefaultExecutor(args) : args.executorService;
        this.args = args;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BlockingQueue<TNonblockingTransport> createDefaultAcceptQueue(int i) {
        if (i == 0) {
            return new LinkedBlockingQueue();
        }
        return new ArrayBlockingQueue(i);
    }

    protected static ExecutorService createDefaultExecutor(Args args) {
        if (args.workerThreads > 0) {
            return Executors.newFixedThreadPool(args.workerThreads);
        }
        return null;
    }

    protected SelectorThreadLoadBalancer createSelectorThreadLoadBalancer(Collection<? extends SelectorThread> collection) {
        return new SelectorThreadLoadBalancer(collection);
    }

    protected Runnable getRunnable(AbstractNonblockingServer.FrameBuffer frameBuffer) {
        return new Invocation(frameBuffer);
    }

    protected void gracefullyShutdownInvokerPool() {
        this.invoker.shutdown();
        long millis = this.args.stopTimeoutUnit.toMillis(this.args.stopTimeoutVal);
        long currentTimeMillis = System.currentTimeMillis();
        while (millis >= 0) {
            try {
                this.invoker.awaitTermination(millis, TimeUnit.MILLISECONDS);
                return;
            } catch (InterruptedException unused) {
                long currentTimeMillis2 = System.currentTimeMillis();
                millis -= currentTimeMillis2 - currentTimeMillis;
                currentTimeMillis = currentTimeMillis2;
            }
        }
    }

    protected void joinThreads() throws InterruptedException {
        this.acceptThread.join();
        for (SelectorThread selectorThread : this.selectorThreads) {
            selectorThread.join();
        }
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
        Runnable runnable = getRunnable(frameBuffer);
        ExecutorService executorService = this.invoker;
        if (executorService != null) {
            try {
                executorService.execute(runnable);
                return true;
            } catch (RejectedExecutionException e) {
                LOGGER.warn("ExecutorService rejected execution!", (Throwable) e);
                return false;
            }
        }
        runnable.run();
        return true;
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected boolean startThreads() {
        for (int i = 0; i < this.args.selectorThreads; i++) {
            try {
                this.selectorThreads.add(new SelectorThread(this, this.args.acceptQueueSizePerThread));
            } catch (IOException e) {
                LOGGER.error("Failed to start threads!", (Throwable) e);
                return false;
            }
        }
        this.acceptThread = new AcceptThread((TNonblockingServerTransport) this.serverTransport_, createSelectorThreadLoadBalancer(this.selectorThreads));
        this.stopped_ = false;
        for (SelectorThread selectorThread : this.selectorThreads) {
            selectorThread.start();
        }
        this.acceptThread.start();
        return true;
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void stop() {
        this.stopped_ = true;
        stopListening();
        AcceptThread acceptThread = this.acceptThread;
        if (acceptThread != null) {
            acceptThread.wakeupSelector();
        }
        Set<SelectorThread> set = this.selectorThreads;
        if (set != null) {
            for (SelectorThread selectorThread : set) {
                if (selectorThread != null) {
                    selectorThread.wakeupSelector();
                }
            }
        }
    }

    @Override // org.apache.thrift.orig.server.AbstractNonblockingServer
    protected void waitForShutdown() {
        try {
            joinThreads();
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while joining threads!", (Throwable) e);
        }
        gracefullyShutdownInvokerPool();
    }
}
