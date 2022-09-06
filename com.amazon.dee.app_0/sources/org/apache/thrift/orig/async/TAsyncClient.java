package org.apache.thrift.orig.async;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.thrift.orig.protocol.TProtocolFactory;
import org.apache.thrift.orig.transport.TNonblockingTransport;
/* loaded from: classes4.dex */
public abstract class TAsyncClient {
    protected TAsyncMethodCall ___currentMethod;
    private Exception ___error;
    protected final TAsyncClientManager ___manager;
    protected final TProtocolFactory ___protocolFactory;
    private long ___timeout;
    protected final TNonblockingTransport ___transport;

    public TAsyncClient(TProtocolFactory tProtocolFactory, TAsyncClientManager tAsyncClientManager, TNonblockingTransport tNonblockingTransport) {
        this(tProtocolFactory, tAsyncClientManager, tNonblockingTransport, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkReady() {
        if (this.___currentMethod == null) {
            Exception exc = this.___error;
            if (exc != null) {
                throw new IllegalStateException("Client has an error!", exc);
            }
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Client is currently executing another method: ");
        outline107.append(this.___currentMethod.getClass().getName());
        throw new IllegalStateException(outline107.toString());
    }

    public Exception getError() {
        return this.___error;
    }

    public TProtocolFactory getProtocolFactory() {
        return this.___protocolFactory;
    }

    public long getTimeout() {
        return this.___timeout;
    }

    public boolean hasError() {
        return this.___error != null;
    }

    public boolean hasTimeout() {
        return this.___timeout > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onComplete() {
        this.___currentMethod = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onError(Exception exc) {
        this.___transport.close();
        this.___currentMethod = null;
        this.___error = exc;
    }

    public void setTimeout(long j) {
        this.___timeout = j;
    }

    public TAsyncClient(TProtocolFactory tProtocolFactory, TAsyncClientManager tAsyncClientManager, TNonblockingTransport tNonblockingTransport, long j) {
        this.___protocolFactory = tProtocolFactory;
        this.___manager = tAsyncClientManager;
        this.___transport = tNonblockingTransport;
        this.___timeout = j;
    }
}
