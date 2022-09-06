package org.apache.thrift.orig.server;

import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.transport.TTransport;
/* loaded from: classes4.dex */
public interface TServerEventHandler {
    ServerContext createContext(TProtocol tProtocol, TProtocol tProtocol2);

    void deleteContext(ServerContext serverContext, TProtocol tProtocol, TProtocol tProtocol2);

    void preServe();

    void processContext(ServerContext serverContext, TTransport tTransport, TTransport tTransport2);
}
