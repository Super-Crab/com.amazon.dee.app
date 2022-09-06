package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public final class OutputStreamSink implements Sink {
    private final OutputStream outputStream;

    public OutputStreamSink(OutputStream outputStream) {
        Preconditions.notNull(outputStream, "outputStream");
        this.outputStream = outputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.outputStream.close();
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() throws IOException {
        this.outputStream.flush();
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.outputStream.write(bArr, i, i2);
    }
}
