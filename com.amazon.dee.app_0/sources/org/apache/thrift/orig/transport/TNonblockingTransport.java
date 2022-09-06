package org.apache.thrift.orig.transport;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
/* loaded from: classes4.dex */
public abstract class TNonblockingTransport extends TTransport {
    public abstract boolean finishConnect() throws IOException;

    public abstract int read(ByteBuffer byteBuffer) throws IOException;

    public abstract SelectionKey registerSelector(Selector selector, int i) throws IOException;

    public abstract boolean startConnect() throws IOException;

    public abstract int write(ByteBuffer byteBuffer) throws IOException;
}
