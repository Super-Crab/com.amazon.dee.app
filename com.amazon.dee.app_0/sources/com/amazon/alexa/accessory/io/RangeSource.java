package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.io.IOException;
/* loaded from: classes.dex */
public final class RangeSource implements Source {
    private final int length;
    private final int offset;
    private volatile boolean skipped;
    private final Source source;
    private volatile int totalRead;

    public RangeSource(Source source, int i) {
        this(source, 0, i);
    }

    private void skipToOffset() throws IOException {
        if (this.skipped) {
            return;
        }
        this.skipped = true;
        int min = Math.min(4096, this.offset);
        byte[] bArr = new byte[min];
        int i = 0;
        while (true) {
            int i2 = this.offset;
            if (i >= i2) {
                return;
            }
            int read = this.source.read(bArr, 0, Math.min(i2 - i, min));
            if (read == -1) {
                return;
            }
            i += read;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        skipToOffset();
        int i3 = this.totalRead;
        int i4 = this.length;
        if (i3 == i4) {
            return -1;
        }
        int read = this.source.read(bArr, i, Math.min(i4 - this.totalRead, i2));
        if (read > 0) {
            this.totalRead += read;
        }
        return read;
    }

    public RangeSource(Source source, int i, int i2) {
        Preconditions.notNull(source, "source");
        Preconditions.notNegative(i2, "length");
        this.offset = i;
        this.length = i2;
        this.source = source;
    }
}
