package org.apache.thrift.orig.scheme;

import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.protocol.TProtocol;
/* loaded from: classes4.dex */
public interface IScheme<T extends TBase> {
    void read(TProtocol tProtocol, T t) throws TException;

    void write(TProtocol tProtocol, T t) throws TException;
}
