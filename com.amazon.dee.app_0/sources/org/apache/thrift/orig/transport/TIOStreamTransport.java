package org.apache.thrift.orig.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TIOStreamTransport extends TTransport {
    private static final Logger LOGGER = LoggerFactory.getLogger(TIOStreamTransport.class.getName());
    protected InputStream inputStream_;
    protected OutputStream outputStream_;

    /* JADX INFO: Access modifiers changed from: protected */
    public TIOStreamTransport() {
        this.inputStream_ = null;
        this.outputStream_ = null;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        InputStream inputStream = this.inputStream_;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.warn("Error closing input stream.", (Throwable) e);
            }
            this.inputStream_ = null;
        }
        OutputStream outputStream = this.outputStream_;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                LOGGER.warn("Error closing output stream.", (Throwable) e2);
            }
            this.outputStream_ = null;
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        OutputStream outputStream = this.outputStream_;
        if (outputStream != null) {
            try {
                outputStream.flush();
                return;
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        }
        throw new TTransportException(1, "Cannot flush null outputStream");
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
        InputStream inputStream = this.inputStream_;
        if (inputStream != null) {
            try {
                int read = inputStream.read(bArr, i, i2);
                if (read < 0) {
                    throw new TTransportException(4);
                }
                return read;
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        }
        throw new TTransportException(1, "Cannot read from null inputStream");
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        OutputStream outputStream = this.outputStream_;
        if (outputStream != null) {
            try {
                outputStream.write(bArr, i, i2);
                return;
            } catch (IOException e) {
                throw new TTransportException(0, e);
            }
        }
        throw new TTransportException(1, "Cannot write to null outputStream");
    }

    public TIOStreamTransport(InputStream inputStream) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.inputStream_ = inputStream;
    }

    public TIOStreamTransport(OutputStream outputStream) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.outputStream_ = outputStream;
    }

    public TIOStreamTransport(InputStream inputStream, OutputStream outputStream) {
        this.inputStream_ = null;
        this.outputStream_ = null;
        this.inputStream_ = inputStream;
        this.outputStream_ = outputStream;
    }
}
