package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class TailAppendingInputStream extends FilterInputStream {
    private int mMarkedTailOffset;
    private final byte[] mTail;
    private int mTailOffset;

    public TailAppendingInputStream(InputStream inputStream, byte[] tail) {
        super(inputStream);
        if (inputStream != null) {
            if (tail != null) {
                this.mTail = tail;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    private int readNextTailByte() {
        int i = this.mTailOffset;
        byte[] bArr = this.mTail;
        if (i >= bArr.length) {
            return -1;
        }
        this.mTailOffset = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int readLimit) {
        if (((FilterInputStream) this).in.markSupported()) {
            super.mark(readLimit);
            this.mMarkedTailOffset = this.mTailOffset;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = ((FilterInputStream) this).in.read();
        return read != -1 ? read : readNextTailByte();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        if (((FilterInputStream) this).in.markSupported()) {
            ((FilterInputStream) this).in.reset();
            this.mTailOffset = this.mMarkedTailOffset;
            return;
        }
        throw new IOException("mark is not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int count) throws IOException {
        int read = ((FilterInputStream) this).in.read(buffer, offset, count);
        if (read != -1) {
            return read;
        }
        int i = 0;
        if (count == 0) {
            return 0;
        }
        while (i < count) {
            int readNextTailByte = readNextTailByte();
            if (readNextTailByte == -1) {
                break;
            }
            buffer[offset + i] = (byte) readNextTailByte;
            i++;
        }
        if (i <= 0) {
            return -1;
        }
        return i;
    }
}
