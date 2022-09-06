package com.amazon.alexa.accessory.io;

import java.io.IOException;
/* loaded from: classes.dex */
public interface SizedSource extends Source {
    public static final SizedSource EMPTY = new SizedSource() { // from class: com.amazon.alexa.accessory.io.SizedSource.1
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // com.amazon.alexa.accessory.io.Source
        public int read(byte[] bArr, int i, int i2) throws IOException {
            return -1;
        }

        @Override // com.amazon.alexa.accessory.io.SizedSource
        public void reset() throws IOException {
        }

        @Override // com.amazon.alexa.accessory.io.SizedSource
        public int size() throws IOException {
            return 0;
        }
    };

    void reset() throws IOException;

    int size() throws IOException;
}
