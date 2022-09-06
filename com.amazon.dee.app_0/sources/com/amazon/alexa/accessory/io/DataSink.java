package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.IOException;
/* loaded from: classes.dex */
public final class DataSink implements Sink {
    private int bitsOffset;
    private final byte[] single;
    private final Sink sink;

    public DataSink(Sink sink) {
        Preconditions.notNull(sink, "sink");
        this.sink = sink;
        this.single = new byte[1];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sink.close();
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() throws IOException {
        if (this.bitsOffset > 0) {
            this.bitsOffset = 0;
            write(this.single);
        }
        this.sink.flush();
    }

    public void write(int i) throws IOException {
        if (this.bitsOffset > 0) {
            this.bitsOffset = 0;
            write(this.single);
        }
        byte[] bArr = this.single;
        bArr[0] = (byte) i;
        write(bArr);
    }

    public void writeBit(int i) throws IOException {
        if (this.bitsOffset == 0) {
            this.single[0] = 0;
        }
        byte[] bArr = this.single;
        int i2 = this.bitsOffset;
        bArr[0] = (byte) (((i & 1) << (7 - i2)) | (bArr[0] & 255 & (~(1 << (7 - i2)))));
        int i3 = i2 + 1;
        this.bitsOffset = i3;
        if (i3 == 8) {
            this.bitsOffset = 0;
            write(bArr);
        }
    }

    public void writeBits(int i, int i2) throws IOException {
        Preconditions.elementIndex(i2, 33, "count");
        for (int i3 = 0; i3 < i2; i3++) {
            writeBit(i >> ((i2 - 1) - i3));
        }
    }

    public void writeInteger(int i) throws IOException {
        write(i >> 24);
        write(i >> 16);
        write(i >> 8);
        write(i);
    }

    public void write(byte[] bArr) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        write(bArr, 0, bArr.length);
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        Preconditions.notNull(bArr, "buffer");
        this.sink.write(bArr, i, i2);
    }

    public void write(SizedSource sizedSource) throws IOException {
        Preconditions.notNull(sizedSource, "source");
        int min = Math.min(1024, sizedSource.size());
        byte[] bArr = new byte[min];
        while (true) {
            int read = sizedSource.read(bArr, 0, min);
            if (read < 0) {
                return;
            }
            write(bArr, 0, read);
        }
    }
}
