package org.apache.thrift.orig.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.orig.server.TServer;
import org.apache.thrift.orig.transport.TServerTransport;
import org.apache.thrift.orig.transport.TTransport;
import org.apache.thrift.orig.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TThreadPoolServer extends TServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TThreadPoolServer.class.getName());
    private ExecutorService executorService_;
    private final TimeUnit stopTimeoutUnit;
    private final long stopTimeoutVal;
    private volatile boolean stopped_;

    /* loaded from: classes4.dex */
    public static class Args extends TServer.AbstractServerArgs<Args> {
        public ExecutorService executorService;
        public int maxWorkerThreads;
        public int minWorkerThreads;
        public TimeUnit stopTimeoutUnit;
        public int stopTimeoutVal;

        public Args(TServerTransport tServerTransport) {
            super(tServerTransport);
            this.minWorkerThreads = 5;
            this.maxWorkerThreads = Integer.MAX_VALUE;
            this.stopTimeoutVal = 60;
            this.stopTimeoutUnit = TimeUnit.SECONDS;
        }

        public Args executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Args maxWorkerThreads(int i) {
            this.maxWorkerThreads = i;
            return this;
        }

        public Args minWorkerThreads(int i) {
            this.minWorkerThreads = i;
            return this;
        }
    }

    /* loaded from: classes4.dex */
    private class WorkerProcess implements Runnable {
        private TTransport client_;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r9 = this;
                r0 = 0
                org.apache.thrift.orig.server.TThreadPoolServer r1 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.TProcessorFactory r1 = r1.processorFactory_     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.transport.TTransport r2 = r9.client_     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.TProcessor r1 = r1.getProcessor(r2)     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.server.TThreadPoolServer r2 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.transport.TTransportFactory r2 = r2.inputTransportFactory_     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.transport.TTransport r3 = r9.client_     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.transport.TTransport r2 = r2.getTransport(r3)     // Catch: java.lang.Exception -> L78 org.apache.thrift.orig.TException -> L88 org.apache.thrift.orig.transport.TTransportException -> L98
                org.apache.thrift.orig.server.TThreadPoolServer r3 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L70 org.apache.thrift.orig.TException -> L73 org.apache.thrift.orig.transport.TTransportException -> L76
                org.apache.thrift.orig.transport.TTransportFactory r3 = r3.outputTransportFactory_     // Catch: java.lang.Exception -> L70 org.apache.thrift.orig.TException -> L73 org.apache.thrift.orig.transport.TTransportException -> L76
                org.apache.thrift.orig.transport.TTransport r4 = r9.client_     // Catch: java.lang.Exception -> L70 org.apache.thrift.orig.TException -> L73 org.apache.thrift.orig.transport.TTransportException -> L76
                org.apache.thrift.orig.transport.TTransport r3 = r3.getTransport(r4)     // Catch: java.lang.Exception -> L70 org.apache.thrift.orig.TException -> L73 org.apache.thrift.orig.transport.TTransportException -> L76
                org.apache.thrift.orig.server.TThreadPoolServer r4 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L68 org.apache.thrift.orig.TException -> L6b org.apache.thrift.orig.transport.TTransportException -> L6e
                org.apache.thrift.orig.protocol.TProtocolFactory r4 = r4.inputProtocolFactory_     // Catch: java.lang.Exception -> L68 org.apache.thrift.orig.TException -> L6b org.apache.thrift.orig.transport.TTransportException -> L6e
                org.apache.thrift.orig.protocol.TProtocol r4 = r4.getProtocol(r2)     // Catch: java.lang.Exception -> L68 org.apache.thrift.orig.TException -> L6b org.apache.thrift.orig.transport.TTransportException -> L6e
                org.apache.thrift.orig.server.TThreadPoolServer r5 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L60 org.apache.thrift.orig.TException -> L63 org.apache.thrift.orig.transport.TTransportException -> L66
                org.apache.thrift.orig.protocol.TProtocolFactory r5 = r5.outputProtocolFactory_     // Catch: java.lang.Exception -> L60 org.apache.thrift.orig.TException -> L63 org.apache.thrift.orig.transport.TTransportException -> L66
                org.apache.thrift.orig.protocol.TProtocol r5 = r5.getProtocol(r3)     // Catch: java.lang.Exception -> L60 org.apache.thrift.orig.TException -> L63 org.apache.thrift.orig.transport.TTransportException -> L66
                org.apache.thrift.orig.server.TThreadPoolServer r6 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L57 org.apache.thrift.orig.TException -> L5a org.apache.thrift.orig.transport.TTransportException -> L5d
                org.apache.thrift.orig.server.TServerEventHandler r6 = r6.getEventHandler()     // Catch: java.lang.Exception -> L57 org.apache.thrift.orig.TException -> L5a org.apache.thrift.orig.transport.TTransportException -> L5d
                if (r6 == 0) goto L42
                org.apache.thrift.orig.server.ServerContext r0 = r6.createContext(r4, r5)     // Catch: java.lang.Exception -> L3c org.apache.thrift.orig.TException -> L3f org.apache.thrift.orig.transport.TTransportException -> L9d
                goto L42
            L3c:
                r1 = move-exception
                goto L7e
            L3f:
                r1 = move-exception
                goto L8e
            L42:
                if (r6 == 0) goto L47
                r6.processContext(r0, r2, r3)     // Catch: java.lang.Exception -> L3c org.apache.thrift.orig.TException -> L3f org.apache.thrift.orig.transport.TTransportException -> L9d
            L47:
                org.apache.thrift.orig.server.TThreadPoolServer r7 = org.apache.thrift.orig.server.TThreadPoolServer.this     // Catch: java.lang.Exception -> L3c org.apache.thrift.orig.TException -> L3f org.apache.thrift.orig.transport.TTransportException -> L9d
                boolean r7 = org.apache.thrift.orig.server.TThreadPoolServer.access$100(r7)     // Catch: java.lang.Exception -> L3c org.apache.thrift.orig.TException -> L3f org.apache.thrift.orig.transport.TTransportException -> L9d
                if (r7 != 0) goto L9d
                boolean r7 = r1.process(r4, r5)     // Catch: java.lang.Exception -> L3c org.apache.thrift.orig.TException -> L3f org.apache.thrift.orig.transport.TTransportException -> L9d
                if (r7 != 0) goto L42
                goto L9d
            L57:
                r1 = move-exception
                r6 = r0
                goto L7e
            L5a:
                r1 = move-exception
                r6 = r0
                goto L8e
            L5d:
                r6 = r0
                goto L9d
            L60:
                r1 = move-exception
                r5 = r0
                goto L7d
            L63:
                r1 = move-exception
                r5 = r0
                goto L8d
            L66:
                r5 = r0
                goto L9c
            L68:
                r1 = move-exception
                r4 = r0
                goto L7c
            L6b:
                r1 = move-exception
                r4 = r0
                goto L8c
            L6e:
                r4 = r0
                goto L9b
            L70:
                r1 = move-exception
                r3 = r0
                goto L7b
            L73:
                r1 = move-exception
                r3 = r0
                goto L8b
            L76:
                r3 = r0
                goto L9a
            L78:
                r1 = move-exception
                r2 = r0
                r3 = r2
            L7b:
                r4 = r3
            L7c:
                r5 = r4
            L7d:
                r6 = r5
            L7e:
                org.slf4j.Logger r7 = org.apache.thrift.orig.server.TThreadPoolServer.access$200()
                java.lang.String r8 = "Error occurred during processing of message."
                r7.error(r8, r1)
                goto L9d
            L88:
                r1 = move-exception
                r2 = r0
                r3 = r2
            L8b:
                r4 = r3
            L8c:
                r5 = r4
            L8d:
                r6 = r5
            L8e:
                org.slf4j.Logger r7 = org.apache.thrift.orig.server.TThreadPoolServer.access$200()
                java.lang.String r8 = "Thrift error occurred during processing of message."
                r7.error(r8, r1)
                goto L9d
            L98:
                r2 = r0
                r3 = r2
            L9a:
                r4 = r3
            L9b:
                r5 = r4
            L9c:
                r6 = r5
            L9d:
                if (r6 == 0) goto La2
                r6.deleteContext(r0, r4, r5)
            La2:
                if (r2 == 0) goto La7
                r2.close()
            La7:
                if (r3 == 0) goto Lac
                r3.close()
            Lac:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.thrift.orig.server.TThreadPoolServer.WorkerProcess.run():void");
        }

        private WorkerProcess(TTransport tTransport) {
            this.client_ = tTransport;
        }
    }

    public TThreadPoolServer(Args args) {
        super(args);
        this.stopTimeoutUnit = args.stopTimeoutUnit;
        this.stopTimeoutVal = args.stopTimeoutVal;
        ExecutorService executorService = args.executorService;
        this.executorService_ = executorService == null ? createDefaultExecutorService(args) : executorService;
    }

    private static ExecutorService createDefaultExecutorService(Args args) {
        return new ThreadPoolExecutor(args.minWorkerThreads, args.maxWorkerThreads, 60L, TimeUnit.SECONDS, new SynchronousQueue());
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void serve() {
        try {
            this.serverTransport_.listen();
            TServerEventHandler tServerEventHandler = this.eventHandler_;
            if (tServerEventHandler != null) {
                tServerEventHandler.preServe();
            }
            this.stopped_ = false;
            setServing(true);
            while (!this.stopped_) {
                try {
                    this.executorService_.execute(new WorkerProcess(this.serverTransport_.accept()));
                } catch (TTransportException e) {
                    if (!this.stopped_) {
                        LOGGER.warn("Transport error occurred during acceptance of message.", (Throwable) e);
                    }
                }
            }
            this.executorService_.shutdown();
            long millis = this.stopTimeoutUnit.toMillis(this.stopTimeoutVal);
            long currentTimeMillis = System.currentTimeMillis();
            while (millis >= 0) {
                try {
                    this.executorService_.awaitTermination(millis, TimeUnit.MILLISECONDS);
                    break;
                } catch (InterruptedException unused) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    millis -= currentTimeMillis2 - currentTimeMillis;
                    currentTimeMillis = currentTimeMillis2;
                }
            }
            setServing(false);
        } catch (TTransportException e2) {
            LOGGER.error("Error occurred during listening.", (Throwable) e2);
        }
    }

    @Override // org.apache.thrift.orig.server.TServer
    public void stop() {
        this.stopped_ = true;
        this.serverTransport_.interrupt();
    }
}
