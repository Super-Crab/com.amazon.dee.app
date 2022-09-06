package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class InputStreamSource implements Source {
    private final InputStream inputStream;

    public InputStreamSource(InputStream inputStream) {
        Preconditions.notNull(inputStream, "inputStream");
        this.inputStream = inputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.inputStream.read(bArr, i, i2);
    }
}
