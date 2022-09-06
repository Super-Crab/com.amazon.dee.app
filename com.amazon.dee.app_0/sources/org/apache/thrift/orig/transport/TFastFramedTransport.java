package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class TFastFramedTransport extends TTransport {
    public static final int DEFAULT_BUF_CAPACITY = 1024;
    public static final int DEFAULT_MAX_LENGTH = 16384000;
    private final byte[] i32buf;
    private final int maxLength;
    private final AutoExpandingBufferReadTransport readBuffer;
    private final TTransport underlying;
    private final AutoExpandingBufferWriteTransport writeBuffer;

    /* loaded from: classes4.dex */
    public static class Factory extends TTransportFactory {
        private final int initialCapacity;
        private final int maxLength;

        public Factory() {
            this(1024, TFastFramedTransport.DEFAULT_MAX_LENGTH);
        }

        @Override // org.apache.thrift.orig.transport.TTransportFactory
        public TTransport getTransport(TTransport tTransport) {
            return new TFastFramedTransport(tTransport, this.initialCapacity, this.maxLength);
        }

        public Factory(int i) {
            this(i, TFastFramedTransport.DEFAULT_MAX_LENGTH);
        }

        public Factory(int i, int i2) {
            this.initialCapacity = i;
            this.maxLength = i2;
        }
    }

    public TFastFramedTransport(TTransport tTransport) {
        this(tTransport, 1024, DEFAULT_MAX_LENGTH);
    }

    private void readFrame() throws TTransportException {
        this.underlying.readAll(this.i32buf, 0, 4);
        int decodeFrameSize = TFramedTransport.decodeFrameSize(this.i32buf);
        if (decodeFrameSize >= 0) {
            if (decodeFrameSize <= this.maxLength) {
                this.readBuffer.fill(this.underlying, decodeFrameSize);
                return;
            }
            throw new TTransportException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline109("Frame size (", decodeFrameSize, ") larger than max length ("), this.maxLength, ")!"));
        }
        throw new TTransportException(GeneratedOutlineSupport1.outline52("Read a negative frame size (", decodeFrameSize, ")!"));
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        this.underlying.close();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void consumeBuffer(int i) {
        this.readBuffer.consumeBuffer(i);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        int pos = this.writeBuffer.getPos();
        TFramedTransport.encodeFrameSize(pos, this.i32buf);
        this.underlying.write(this.i32buf, 0, 4);
        this.underlying.write(this.writeBuffer.getBuf().array(), 0, pos);
        this.writeBuffer.reset();
        this.underlying.flush();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public byte[] getBuffer() {
        return this.readBuffer.getBuffer();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBufferPosition() {
        return this.readBuffer.getBufferPosition();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBytesRemainingInBuffer() {
        return this.readBuffer.getBytesRemainingInBuffer();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return this.underlying.isOpen();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        this.underlying.open();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        int read = this.readBuffer.read(bArr, i, i2);
        if (read > 0) {
            return read;
        }
        readFrame();
        return this.readBuffer.read(bArr, i, i2);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        this.writeBuffer.write(bArr, i, i2);
    }

    public TFastFramedTransport(TTransport tTransport, int i) {
        this(tTransport, i, DEFAULT_MAX_LENGTH);
    }

    public TFastFramedTransport(TTransport tTransport, int i, int i2) {
        this.i32buf = new byte[4];
        this.underlying = tTransport;
        this.maxLength = i2;
        this.writeBuffer = new AutoExpandingBufferWriteTransport(i, 1.5d);
        this.readBuffer = new AutoExpandingBufferReadTransport(i, 1.5d);
    }
}
