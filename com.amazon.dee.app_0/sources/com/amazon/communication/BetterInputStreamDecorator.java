package com.amazon.communication;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class BetterInputStreamDecorator extends InputStream implements BetterInputStream {
    private final InputStream mDecorateInputStream;

    private BetterInputStreamDecorator(InputStream inputStream) {
        this.mDecorateInputStream = inputStream;
    }

    public static InputStream ensureBetterInputStream(InputStream inputStream) {
        return inputStream instanceof BetterInputStream ? inputStream : new BetterInputStreamDecorator(inputStream);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int available = this.mDecorateInputStream.available();
        if (available == 0) {
            return 1;
        }
        return available;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDecorateInputStream.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.mDecorateInputStream.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.mDecorateInputStream.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.mDecorateInputStream.read();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.mDecorateInputStream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.mDecorateInputStream.skip(j);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.mDecorateInputStream.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.mDecorateInputStream.read(bArr, i, i2);
    }
}
