package org.apache.thrift.orig.transport;

import org.apache.commons.lang.NotImplementedException;
/* loaded from: classes4.dex */
public final class AutoExpandingBufferWriteTransport extends TTransport {
    private final AutoExpandingBuffer buf;
    private int pos = 0;

    public AutoExpandingBufferWriteTransport(int i, double d) {
        this.buf = new AutoExpandingBuffer(i, d);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
    }

    public AutoExpandingBuffer getBuf() {
        return this.buf;
    }

    public int getPos() {
        return this.pos;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return true;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        throw new NotImplementedException();
    }

    public void reset() {
        this.pos = 0;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        this.buf.resizeIfNecessary(this.pos + i2);
        System.arraycopy(bArr, i, this.buf.array(), this.pos, i2);
        this.pos += i2;
    }
}
