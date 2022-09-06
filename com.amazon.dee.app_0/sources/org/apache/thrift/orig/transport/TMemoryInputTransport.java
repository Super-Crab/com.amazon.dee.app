package org.apache.thrift.orig.transport;
/* loaded from: classes4.dex */
public final class TMemoryInputTransport extends TTransport {
    private byte[] buf_;
    private int endPos_;
    private int pos_;

    public TMemoryInputTransport() {
    }

    public void clear() {
        this.buf_ = null;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void consumeBuffer(int i) {
        this.pos_ += i;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public byte[] getBuffer() {
        return this.buf_;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBufferPosition() {
        return this.pos_;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBytesRemainingInBuffer() {
        return this.endPos_ - this.pos_;
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
        int bytesRemainingInBuffer = getBytesRemainingInBuffer();
        if (i2 > bytesRemainingInBuffer) {
            i2 = bytesRemainingInBuffer;
        }
        if (i2 > 0) {
            System.arraycopy(this.buf_, this.pos_, bArr, i, i2);
            consumeBuffer(i2);
        }
        return i2;
    }

    public void reset(byte[] bArr) {
        reset(bArr, 0, bArr.length);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public TMemoryInputTransport(byte[] bArr) {
        reset(bArr);
    }

    public void reset(byte[] bArr, int i, int i2) {
        this.buf_ = bArr;
        this.pos_ = i;
        this.endPos_ = i + i2;
    }

    public TMemoryInputTransport(byte[] bArr, int i, int i2) {
        reset(bArr, i, i2);
    }
}
