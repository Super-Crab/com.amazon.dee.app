package org.apache.thrift.orig.transport;

import org.apache.commons.lang.NotImplementedException;
/* loaded from: classes4.dex */
public class AutoExpandingBufferReadTransport extends TTransport {
    private final AutoExpandingBuffer buf;
    private int pos = 0;
    private int limit = 0;

    public AutoExpandingBufferReadTransport(int i, double d) {
        this.buf = new AutoExpandingBuffer(i, d);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public final void consumeBuffer(int i) {
        this.pos += i;
    }

    public void fill(TTransport tTransport, int i) throws TTransportException {
        this.buf.resizeIfNecessary(i);
        tTransport.readAll(this.buf.array(), 0, i);
        this.pos = 0;
        this.limit = i;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public final byte[] getBuffer() {
        return this.buf.array();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public final int getBufferPosition() {
        return this.pos;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public final int getBytesRemainingInBuffer() {
        return this.limit - this.pos;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return true;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public final int read(byte[] bArr, int i, int i2) throws TTransportException {
        int min = Math.min(i2, getBytesRemainingInBuffer());
        System.arraycopy(this.buf.array(), this.pos, bArr, i, min);
        consumeBuffer(min);
        return min;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        throw new NotImplementedException();
    }
}
