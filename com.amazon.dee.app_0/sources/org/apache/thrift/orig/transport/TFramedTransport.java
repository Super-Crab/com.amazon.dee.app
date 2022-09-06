package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.thrift.orig.TByteArrayOutputStream;
/* loaded from: classes4.dex */
public class TFramedTransport extends TTransport {
    protected static final int DEFAULT_MAX_LENGTH = 16384000;
    private final byte[] i32buf;
    private int maxLength_;
    private TMemoryInputTransport readBuffer_;
    private TTransport transport_;
    private final TByteArrayOutputStream writeBuffer_;

    public TFramedTransport(TTransport tTransport, int i) {
        this.transport_ = null;
        this.writeBuffer_ = new TByteArrayOutputStream(1024);
        this.readBuffer_ = new TMemoryInputTransport(new byte[0]);
        this.i32buf = new byte[4];
        this.transport_ = tTransport;
        this.maxLength_ = i;
    }

    public static final int decodeFrameSize(byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static final void encodeFrameSize(int i, byte[] bArr) {
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
    }

    private void readFrame() throws TTransportException {
        this.transport_.readAll(this.i32buf, 0, 4);
        int decodeFrameSize = decodeFrameSize(this.i32buf);
        if (decodeFrameSize >= 0) {
            if (decodeFrameSize <= this.maxLength_) {
                byte[] bArr = new byte[decodeFrameSize];
                this.transport_.readAll(bArr, 0, decodeFrameSize);
                this.readBuffer_.reset(bArr);
                return;
            }
            throw new TTransportException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline109("Frame size (", decodeFrameSize, ") larger than max length ("), this.maxLength_, ")!"));
        }
        throw new TTransportException(GeneratedOutlineSupport1.outline52("Read a negative frame size (", decodeFrameSize, ")!"));
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        this.transport_.close();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void consumeBuffer(int i) {
        this.readBuffer_.consumeBuffer(i);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        byte[] bArr = this.writeBuffer_.get();
        int len = this.writeBuffer_.len();
        this.writeBuffer_.reset();
        encodeFrameSize(len, this.i32buf);
        this.transport_.write(this.i32buf, 0, 4);
        this.transport_.write(bArr, 0, len);
        this.transport_.flush();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public byte[] getBuffer() {
        return this.readBuffer_.getBuffer();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBufferPosition() {
        return this.readBuffer_.getBufferPosition();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int getBytesRemainingInBuffer() {
        return this.readBuffer_.getBytesRemainingInBuffer();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return this.transport_.isOpen();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        this.transport_.open();
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        int read;
        TMemoryInputTransport tMemoryInputTransport = this.readBuffer_;
        if (tMemoryInputTransport == null || (read = tMemoryInputTransport.read(bArr, i, i2)) <= 0) {
            readFrame();
            return this.readBuffer_.read(bArr, i, i2);
        }
        return read;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        this.writeBuffer_.write(bArr, i, i2);
    }

    /* loaded from: classes4.dex */
    public static class Factory extends TTransportFactory {
        private int maxLength_;

        public Factory() {
            this.maxLength_ = 16384000;
        }

        @Override // org.apache.thrift.orig.transport.TTransportFactory
        public TTransport getTransport(TTransport tTransport) {
            return new TFramedTransport(tTransport, this.maxLength_);
        }

        public Factory(int i) {
            this.maxLength_ = i;
        }
    }

    public TFramedTransport(TTransport tTransport) {
        this.transport_ = null;
        this.writeBuffer_ = new TByteArrayOutputStream(1024);
        this.readBuffer_ = new TMemoryInputTransport(new byte[0]);
        this.i32buf = new byte[4];
        this.transport_ = tTransport;
        this.maxLength_ = 16384000;
    }
}
