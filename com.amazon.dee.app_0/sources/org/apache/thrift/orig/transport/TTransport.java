package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public abstract class TTransport {
    public abstract void close();

    public void consumeBuffer(int i) {
    }

    public void flush() throws TTransportException {
    }

    public byte[] getBuffer() {
        return null;
    }

    public int getBufferPosition() {
        return 0;
    }

    public int getBytesRemainingInBuffer() {
        return -1;
    }

    public abstract boolean isOpen();

    public abstract void open() throws TTransportException;

    public boolean peek() {
        return isOpen();
    }

    public abstract int read(byte[] bArr, int i, int i2) throws TTransportException;

    public int readAll(byte[] bArr, int i, int i2) throws TTransportException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read(bArr, i + i3, i2 - i3);
            if (read <= 0) {
                throw new TTransportException(GeneratedOutlineSupport1.outline54("Cannot read. Remote side has closed. Tried to read ", i2, " bytes, but only got ", i3, " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)"));
            }
            i3 += read;
        }
        return i3;
    }

    public void write(byte[] bArr) throws TTransportException {
        write(bArr, 0, bArr.length);
    }

    public abstract void write(byte[] bArr, int i, int i2) throws TTransportException;
}
