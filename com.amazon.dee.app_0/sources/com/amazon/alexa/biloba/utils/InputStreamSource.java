package com.amazon.alexa.biloba.utils;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes6.dex */
public final class InputStreamSource implements Source {
    private final InputStream inputStream;

    public InputStreamSource(@NonNull InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override // com.amazon.alexa.biloba.utils.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.inputStream.read(bArr, i, i2);
    }
}
