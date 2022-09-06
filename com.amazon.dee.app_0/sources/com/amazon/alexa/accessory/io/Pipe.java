package com.amazon.alexa.accessory.io;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import java.io.IOException;
/* loaded from: classes.dex */
public final class Pipe implements Source, Sink {
    private final byte[] buffer;
    private final int capacity;
    private volatile boolean closed;
    private volatile int size;
    private final long timeoutMillis;

    public Pipe(int i, long j) {
        this.capacity = i;
        this.timeoutMillis = j;
        this.buffer = new byte[i];
    }

    private void checkClosed() throws IOException {
        if (!this.closed) {
            return;
        }
        throw new IOException("Pipe is closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        synchronized (this.buffer) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.buffer.notifyAll();
        }
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() throws IOException {
        synchronized (this.buffer) {
            while (this.size > 0) {
                checkClosed();
                IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.buffer) {
            while (this.size == 0) {
                if (this.closed) {
                    return -1;
                }
                IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
            }
            int min = Math.min(i2, this.size);
            System.arraycopy(this.buffer, 0, bArr, i, min);
            if (this.size > min) {
                System.arraycopy(this.buffer, min, this.buffer, 0, this.size - min);
            }
            this.size -= min;
            this.buffer.notifyAll();
            return min;
        }
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.buffer) {
            int i3 = 0;
            while (true) {
                int i4 = i2 - i3;
                if (i4 > 0) {
                    checkClosed();
                    int i5 = this.capacity - this.size;
                    if (i5 == 0) {
                        IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
                    } else {
                        int min = Math.min(i5, i4);
                        System.arraycopy(bArr, i + i3, this.buffer, this.size, min);
                        i3 += min;
                        this.size += min;
                        this.buffer.notifyAll();
                    }
                }
            }
        }
    }
}
