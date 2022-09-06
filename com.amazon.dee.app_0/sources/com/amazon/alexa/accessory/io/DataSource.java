package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.EOFException;
import java.io.IOException;
/* loaded from: classes.dex */
public final class DataSource implements Source {
    private int bitsOffset;
    private volatile boolean closed;
    private final Buffer peekBuffer;
    private final byte[] single;
    private final Source source;

    public DataSource(Source source) {
        Preconditions.notNull(source, "source");
        this.source = source;
        this.single = new byte[1];
        this.peekBuffer = new Buffer();
    }

    private void checkClosed() throws IOException {
        if (!this.closed) {
            return;
        }
        throw new IOException("Source is closed!");
    }

    private void checkResult(int i) throws IOException {
        if (i >= 0) {
            return;
        }
        throw new EOFException("Expected a data found end of stream");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.source.close();
        this.closed = true;
    }

    public int get() throws IOException {
        checkResult(read(this.single));
        return this.single[0] & 255;
    }

    public int getBit() throws IOException {
        int i = this.bitsOffset;
        if (i == 8 || i == 0) {
            get();
        }
        int i2 = this.bitsOffset;
        this.bitsOffset = i2 + 1;
        return ((this.single[0] & 255) >> (7 - i2)) & 1;
    }

    public int getBits(int i) throws IOException {
        Preconditions.elementIndex(i, 33, "count");
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 1) | getBit();
        }
        return i2;
    }

    public int getInteger() throws IOException {
        return (get() << 24) | (get() << 16) | (get() << 8) | get();
    }

    public int getWord() throws IOException {
        return (get() << 8) | get();
    }

    public void peek(byte[] bArr, int i, int i2) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        checkClosed();
        this.peekBuffer.ensureCapacity(i2);
        int size = i2 - this.peekBuffer.size();
        while (size > 0) {
            int read = this.source.read(this.peekBuffer.data(), this.peekBuffer.size(), size);
            checkResult(read);
            Buffer buffer = this.peekBuffer;
            buffer.resize(buffer.size() + read);
            size -= read;
        }
        System.arraycopy(this.peekBuffer.data(), 0, bArr, i, i2);
    }

    public int read(byte[] bArr) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        return read(bArr, 0, bArr.length);
    }

    public void get(byte[] bArr) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        get(bArr, 0, bArr.length);
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        this.bitsOffset = 0;
        checkClosed();
        return this.peekBuffer.size() > 0 ? this.peekBuffer.read(bArr, i, i2) : this.source.read(bArr, i, i2);
    }

    public void get(byte[] bArr, int i, int i2) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        int i3 = i2;
        while (i3 > 0) {
            int read = read(bArr, (i + i2) - i3, i3);
            checkResult(read);
            i3 -= read;
        }
    }
}
