package org.apache.thrift.orig.async;

import org.apache.thrift.orig.async.TAsyncClient;
import org.apache.thrift.orig.transport.TNonblockingTransport;
/* loaded from: classes4.dex */
public interface TAsyncClientFactory<T extends TAsyncClient> {
    /* renamed from: getAsyncClient */
    T mo3848getAsyncClient(TNonblockingTransport tNonblockingTransport);
}
