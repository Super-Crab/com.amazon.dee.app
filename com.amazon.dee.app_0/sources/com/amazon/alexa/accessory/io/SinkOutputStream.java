package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public final class SinkOutputStream extends OutputStream {
    private final byte[] single;
    private final Sink sink;

    public SinkOutputStream(Sink sink) {
        Preconditions.notNull(sink, "sink");
        this.sink = sink;
        this.single = new byte[1];
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sink.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.sink.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.single;
        bArr[0] = (byte) i;
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.sink.write(bArr, i, i2);
    }
}
