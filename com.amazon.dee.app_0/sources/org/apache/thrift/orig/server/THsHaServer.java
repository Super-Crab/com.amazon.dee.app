package org.apache.thrift.orig.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.orig.server.AbstractNonblockingServer;
import org.apache.thrift.orig.transport.TNonblockingServerTransport;
/* loaded from: classes4.dex */
public class THsHaServer extends TNonblockingServer {
    private final Args args;
    private final ExecutorService invoker;

    /* loaded from: classes4.dex */
    public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs<Args> {
        private ExecutorService executorService;
        private TimeUnit stopTimeoutUnit;
        private int stopTimeoutVal;
        private int workerThreads;

        public Args(TNonblockingServerTransport tNonblockingServerTransport) {
            super(tNonblockingServerTransport);
            this.workerThreads = 5;
            this.stopTimeoutVal = 60;
            this.stopTimeoutUnit = TimeUnit.SECONDS;
            this.executorService = null;
        }

        public Args executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public ExecutorService getExecutorService() {
            return this.executorService;
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

        public Args stopTimeoutUnit(TimeUnit timeUnit) {
            this.stopTimeoutUnit = timeUnit;
            return this;
        }

        public Args stopTimeoutVal(int i) {
            this.stopTimeoutVal = i;
            return this;
        }

        public Args workerThreads(int i) {
            this.workerThreads = i;
            return this;
        }
    }

    public THsHaServer(Args args) {
        super(args);
        this.invoker = args.executorService == null ? createInvokerPool(args) : args.executorService;
        this.args = args;
    }

    protected static ExecutorService createInvokerPool(Args args) {
        int i = args.workerThreads;
        return new ThreadPoolExecutor(i, i, args.stopTimeoutVal, args.stopTimeoutUnit, new LinkedBlockingQueue());
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

    @Override // org.apache.thrift.orig.server.TNonblockingServer, org.apache.thrift.orig.server.AbstractNonblockingServer
    protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
        try {
            this.invoker.execute(getRunnable(frameBuffer));
            return true;
        } catch (RejectedExecutionException e) {
            this.LOGGER.warn("ExecutorService rejected execution!", (Throwable) e);
            return false;
        }
    }

    @Override // org.apache.thrift.orig.server.TNonblockingServer, org.apache.thrift.orig.server.AbstractNonblockingServer
    protected void waitForShutdown() {
        joinSelector();
        gracefullyShutdownInvokerPool();
    }
}
