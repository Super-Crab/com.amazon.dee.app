package org.apache.thrift.orig.transport;

import java.nio.channels.Selector;
/* loaded from: classes4.dex */
public abstract class TNonblockingServerTransport extends TServerTransport {
    public abstract void registerSelector(Selector selector);
}
