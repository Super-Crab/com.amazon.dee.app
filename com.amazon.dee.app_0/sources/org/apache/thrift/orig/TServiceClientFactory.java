package org.apache.thrift.orig;

import org.apache.thrift.orig.TServiceClient;
import org.apache.thrift.orig.protocol.TProtocol;
/* loaded from: classes4.dex */
public interface TServiceClientFactory<T extends TServiceClient> {
    /* renamed from: getClient */
    T mo3849getClient(TProtocol tProtocol);

    /* renamed from: getClient */
    T mo3850getClient(TProtocol tProtocol, TProtocol tProtocol2);
}
