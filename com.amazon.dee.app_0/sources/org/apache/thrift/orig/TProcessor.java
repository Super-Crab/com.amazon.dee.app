package org.apache.thrift.orig;

import org.apache.thrift.orig.protocol.TProtocol;
/* loaded from: classes4.dex */
public interface TProcessor {
    boolean process(TProtocol tProtocol, TProtocol tProtocol2) throws TException;
}
