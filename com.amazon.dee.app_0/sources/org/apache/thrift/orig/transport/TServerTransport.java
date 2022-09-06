package org.apache.thrift.orig.transport;
/* loaded from: classes4.dex */
public abstract class TServerTransport {
    public final TTransport accept() throws TTransportException {
        TTransport mo12848acceptImpl = mo12848acceptImpl();
        if (mo12848acceptImpl != null) {
            return mo12848acceptImpl;
        }
        throw new TTransportException("accept() may not return NULL");
    }

    /* renamed from: acceptImpl */
    protected abstract TTransport mo12848acceptImpl() throws TTransportException;

    public abstract void close();

    public void interrupt() {
    }

    public abstract void listen() throws TTransportException;
}
